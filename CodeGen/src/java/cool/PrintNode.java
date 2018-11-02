package cool;

import java.util.*;

public class PrintNode
{
	private String filename;
    private int varCnt;

	//Constructor
	public PrintNode()
	{
    	filename = "";
        varCnt = 0;
	}

    String getType(String st)
    {
        if(st.equals("Int"))
            return "i32";
        if(st.equals("Bool"))
            return "i1";
        if(st.equals("String"))
            return "i8*";

        return "void";
    }

	//Program Visitor
	public void Visit(AST.program program)
	{
		for(AST.class_ cl: program.classes)
        {
            if(cl.name.equals("Main"))
            {
                for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs(cl.name).entrySet())
                {
                    if(entry.getValue().name.equals("self"))
                        continue;

                    Codegen.progOut += "@"+entry.getValue().name+" = global ";
                    Visit(entry.getValue());
                    Codegen.progOut += "\n";
                }

                for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
                    Visit(entry.getValue());

            }
            else
                Visit(cl);
        }
    }

	public void Visit(AST.class_ cl)
    {
        Codegen.progOut += "%struct."+cl.name+" = type {";
		for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs(cl.name).entrySet())
		{
            if(entry.getValue().name.equals("self"))
                continue;

            Codegen.progOut += " ";
            Visit(entry.getValue());
            Codegen.progOut += ",";
        }
        Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-1);
        Codegen.progOut += " }\n";

		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
			Visit(entry.getValue());
    }

	public void Visit(AST.attr at)
    {
        Codegen.progOut += getType(at.typeid);
    }

	public void Visit(AST.method md)
    {
        Codegen.progOut += "define "+getType(md.typeid)+" @"+md.name+" (";
        Codegen.progOut += ")\n";
    }
}
