package cool;

import java.util.*;

public class Visitor
{
	private ScopeTable<String> scopeTable;
	private String filename;

	//Constructor
	public Visitor()
	{
		scopeTable = new ScopeTable<String>();
	}

	//Program Visitor
	public void Visit(AST.program program)
	{
		for(AST.class_ newClass: program.classes)
		{
			Semantic.inheritance.InsertClass(newClass);
		}

		Semantic.inheritance.CheckClass();
		if(Semantic.GetErrorFlagInProgram())
			return;
		Semantic.inheritance.CheckCycle();
		if(Semantic.GetErrorFlagInProgram())
			return;
		Semantic.inheritance.FuncMangledNames();

		for(AST.class_ newClass: program.classes)
			Visit(newClass);
	}

	//Class Visitor
	public void Visit(AST.class_ cl)
	{
		//New scope for each Class
		scopeTable.enterScope();

		//Insert all declared Class attributes into Scope Table
		for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs(cl.name).entrySet())
			scopeTable.insert(entry.getKey(),entry.getValue().typeid);

		//Visit all the Class methods
		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
		{
			filename = cl.filename;
			Visit(entry.getValue());
		}

		//Exit scope before leaving Class
		scopeTable.exitScope();
	}

	//Method Visitor
	public void Visit(AST.method md)
	{
		//New scope for each Method
		scopeTable.enterScope();

		//Inserting Formal Parameters into the Scope Table
		for(AST.formal fl: md.formals)
		{
			//Checking for multiple declarations of formal parameters
			if(scopeTable.lookUpLocal(fl.name)==null)
				scopeTable.insert(fl.name,fl.typeid);
			else
				Semantic.reportError(filename,fl.lineNo,"Multiple declarations of the same formal parameter '"+fl.name+"'");
		}

		//Visit Body of Method
		Visit(md.body);

		//Exit scope before leaving Method
		scopeTable.exitScope();
	}

	//Expression Visitor
	public void Visit(AST.expression exp)
	{

	}
}
