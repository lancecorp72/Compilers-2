package cool;

import java.util.*;

public class PrintNode
{
    private HashMap<String,String> clNames;
    private String className;

	//Constructor
	public PrintNode()
	{
        className = "";
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

        ScopeTable<Integer> varNames = new ScopeTable<Integer>();
        for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs("Main").entrySet())
        {
            if(entry.getValue().name.equals("self"))
                continue;

            int cnt = varNames.getSize();
            varNames.insert(entry.getValue().name,cnt);
            Codegen.progOut += "@"+entry.getValue().name+" = global "+clNames.get(entry.getValue().typeid)+" 0\n";
        }
        Codegen.progOut += "\n";

        HashMap<String,AST.method> mainClassMethods = Semantic.inheritance.GetClassMethods("Main");
		for(Map.Entry<String,AST.method> entry: mainClassMethods.entrySet())
        {
            if(entry.getValue().name.equals("main"))
                continue;
            Visit(entry.getValue());
        }

        Codegen.progOut += "define "+clNames.get(mainClassMethods.get("main").typeid)+" @main () {\n";
        Visit(mainClassMethods.get("main").body,varNames);
        Codegen.progOut += "}";
    }

	public void Visit(AST.class_ cl)
    {
		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
            Visit(entry.getValue());
    }

    public void Visit(AST.method md)
    {
        Codegen.progOut += "define "+clNames.get(md.typeid)+" @"+Semantic.inheritance.GetMangledName(className,md)+" (";

        ScopeTable<Integer> varNames = new ScopeTable<Integer>();
		for(AST.formal fl: md.formals)
        {
            int cnt = varNames.getSize();
            varNames.insert(fl.name,cnt);
            Codegen.progOut += clNames.get(fl.typeid)+" %"+Integer.toString(cnt)+", ";
        }
        if(!md.formals.isEmpty())
            Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-2);

        Codegen.progOut += ") {\n";
        Visit(md.body,varNames);
        Codegen.progOut += "}\n";
    }

    public void Visit(AST.expression expr,ScopeTable<Integer> varNames)
    {
    
    }
}
