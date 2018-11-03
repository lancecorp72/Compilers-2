package cool;

import java.util.*;

public class PrintNode
{
    private int varCnt;
    private HashMap<String,String> clNames;
    private HashMap <int, String> varNames;
    private String className;
    private String indent;

	//Constructor
	public PrintNode()
	{
        varCnt = 0;
        className = "";
        indent = "  ";
        varNames = new HashMap<int,String>();
        clNames = new HashMap<String,String>();
	}

	//Program Visitor
	public void Visit(AST.program program)
	{
        clNames.put("Int","i32");
        clNames.put("String","i8*");
        clNames.put("Bool","i8");

		for(AST.class_ cl: program.classes)
        {
            if(!cl.name.equals("Main"))
            {
                Codegen.progOut += "%struct."+cl.name+" = type {";
                clNames.put(cl.name,"%struct."+cl.name);

                for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs(cl.name).entrySet())
                {
                    if(entry.getValue().name.equals("self"))
                        continue;

                    Codegen.progOut += " "+clNames.get(entry.getValue().typeid)+",";
                }
                Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-1);
                Codegen.progOut += " }\n";
            }
        }
        Codegen.progOut += "\n";

		for(AST.class_ cl: program.classes)
        {
            if(!cl.name.equals("Main"))
            {
                className = cl.name;
                Visit(cl);
            }
        }
        Codegen.progOut += "\n";

        for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs("Main").entrySet())
        {
            if(entry.getValue().name.equals("self"))
                continue;
            Codegen.progOut += "@"+entry.getValue().name+" = global "+clNames.get(entry.getValue().typeid)+"\n";
        }
        Codegen.progOut += "\n";

		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods("Main").entrySet())
        {
            if(entry.getValue().name.equals("main"))
                continue;
            Visit(entry.getValue());
        }

        Codegen.progOut += "define "+clNames.get(Semantic.inheritance.GetClassMethods("Main").get("main").typeid)+" @main () {\n}";
    }

	public void Visit(AST.class_ cl)
    {
		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
            Visit(entry.getValue());
    }

    public void Visit(AST.method md)
    {
        Codegen.progOut += "define "+clNames.get(md.typeid)+" @"+Semantic.inheritance.GetMangledName(className,md)+" (";
        Codegen.progOut += ") {\n";
        Codegen.progOut += "}\n";
    }
    public void Visit(AST.expression expr)
    {
        if(expr instanceof AST.bool_const)
        {
            varNames.put(varCnt,"%" + varCnt);
            Codegen.progOut = indent + varNames.get(varCnt) + " = alloca " + clNames.get("Bool") + "\n";
            Codegen.progOut = indent +  "store" + clNames.get("Bool") + 
        }
    }
}
