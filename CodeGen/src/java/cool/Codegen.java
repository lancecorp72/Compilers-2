package cool;

import java.io.PrintWriter;
import java.util.*;

public class Codegen{
	public Codegen(AST.program program, PrintWriter out){
		//Write Code generator code here
        out.println("; Start of Code Generation");
        String codeGen = "";

        for (AST.class_ cl : program.classes)
        {
            if(cl.name.equals("Main"))
            {
                out.println("; Main Class");
            }
        }
	}

}
