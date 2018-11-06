package cool;

import java.io.PrintWriter;
import java.util.*;

public class Codegen{

    public static String progOut;

	public Codegen(AST.program program, PrintWriter out){
		//Write Code generator code here
        out.println("; Start of Code Generation");
        out.println("source_filename = \""+program.classes.get(0).filename+"\"");
        out.println("target triple = \"x86_64-unknown-linux-gnu\"\n");

        progOut = "";
        PrintNode pn = new PrintNode();
        pn.Visit(program);

        out.println(progOut);
	}

}
