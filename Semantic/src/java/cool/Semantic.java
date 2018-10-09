package cool;

import java.util.*;


public class Semantic
{
	private static boolean errorFlag = false;
	public static void reportError(String filename, int lineNo, String error)
	{
		errorFlag = true;
		System.err.println(filename+":"+lineNo+": "+error);
	}
	public boolean getErrorFlag()
	{
		return errorFlag;
	}

/*
	Don't change code above this line
*/

	public static Inheritance inheritance;

	public Semantic(AST.program program)
	{
		//Write Semantic analyzer code here
		inheritance = new Inheritance();
		Visitor v = new Visitor();
		v.visit(program);
	}
}
