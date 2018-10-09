package cool;

import java.util.*;

public class Visitor
{
	
	public void visit(AST.program program)
	{
		for(AST.class_ newClass: program.classes)
		{
			Semantic.inheritance.insertClass(newClass);
		}

		Semantic.inheritance.CheckClass();
		if(Semantic.getErrorFlagInProgram())
			return;
		Semantic.inheritance.CheckCycle();
		if(Semantic.getErrorFlagInProgram())
			return;
		Semantic.inheritance.FuncMangledNames();
	}

}
