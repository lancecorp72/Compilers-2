package cool;

import java.util.*;


public class Semantic
{
	private boolean errorFlag = false;
	public void reportError(String filename, int lineNo, String error)
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

	public static Inheritance inheritance = new Inheritance();

	public Semantic(AST.program program)
	{
		//Write Semantic analyzer code here
		//Visitor v = new Visitor();
		System.out.println("IO index :" + inheritance.getIndex("IO"));
	}
}
