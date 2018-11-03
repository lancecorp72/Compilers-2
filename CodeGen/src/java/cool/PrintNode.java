package cool;

import java.util.*;

public class PrintNode
{
    private HashMap<String,String> clNames;
    private String className;
    private String indent;
    private String val;
    private int varCnt;

	//Constructor
	public PrintNode()
	{
        className = "";
        varCnt = 0;
        val = "";
        indent = "  ";
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

    public void Visit(AST.expression expr, ScopeTable<String> varNames)
    {
        if(expr instanceof AST.bool_const)
        {
            int value = 0;
            if(((AST.bool_const)expr).value)
                value = 1;
            varCnt += 1;
            val = "" + varCnt;
            Codegen.progOut += indent + "store " + clNames.get(expr.type) + " " + value + "," + clNames.get(expr.type) + "* " + val + "\n";
        }
        if(expr instanceof AST.int_const)
        {
            int value = ((AST.int_const)expr).value;
            varCnt += 1;
            val = "" + varCnt;
            Codegen.progOut += indent + "store " + clNames.get(expr.type) + " " + value + "," + clNames.get(expr.type) + "* " + val + "\n";
        }
        if(expr instanceof AST.object)
        {
            if(expr.type.equals("Int") || expr.type.equals("Bool"))
                val = "%" + ((AST.object)expr).name;
        }
        if(expr instanceof AST.assign)
        {
            String vname = "%" + ((AST.assign)expr).name;
            Visit(((AST.assign)expr).e1,varNames);

            //assuming attributes are handled beforehand i.e allocated before
            Codegen.progOut += indent + "store " + clNames.get(((AST.assign)expr).e1.type) + val + ", " + clNames.get(((AST.assign)expr).e1.type) + "* " + vname + "\n";
        }
        if(expr instanceof AST.plus)
        {
            varCnt += 1;
            val  = "%add" + varCnt;
            Codegen.progOut += val + " = add nsw" +  clNames.get(expr.type);
            Visit(((AST.plus)expr).e1,varNames);
            Codegen.progOut += val;
            Visit(((AST.plus)expr).e2,varNames);
            Codegen.progOut += val;
        }
        if(expr instanceof AST.mul)
        {
            varCnt += 1;
            val  = "%mul" + varCnt;
            Codegen.progOut += val + " = mul nsw" +  clNames.get(expr.type);
            Visit(((AST.mul)expr).e1,varNames);
            Codegen.progOut += val;
            Visit(((AST.mul)expr).e2,varNames);
            Codegen.progOut += val;
        }
        if(expr instanceof AST.sub)
        {
            varCnt += 1;
            val  = "%sub" + varCnt;
            Codegen.progOut += val + " = sub nsw" +  clNames.get(expr.type);
            Visit(((AST.sub)expr).e1,varNames);
            Codegen.progOut += val;
            Visit(((AST.sub)expr).e2,varNames);
            Codegen.progOut += val;
        }
        if(expr instanceof AST.divide)
        {
            varCnt += 1;
            val  = "%divide" + varCnt;
            Codegen.progOut += val + " = sub nsw" +  clNames.get(expr.type);
            Visit(((AST.divide)expr).e1,varNames);
            Codegen.progOut += val;
            Visit(((AST.divide)expr).e2,varNames);
            Codegen.progOut += val;
        }
    } 
}
