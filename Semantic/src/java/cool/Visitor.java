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
    filename = "";
	}

	//Program Visitor
	public void Visit(AST.program program)
	{
		for(AST.class_ newClass: program.classes)
		{
			Semantic.inheritance.InsertClass(newClass);
		}

		//Checks Validity of Classes
		Semantic.inheritance.CheckClass();
		if(Semantic.GetErrorFlagInProgram())
			return;
		//Checks for Cycles in Graph
		Semantic.inheritance.CheckCycle();
		if(Semantic.GetErrorFlagInProgram())
			return;
		Semantic.inheritance.FuncMangledNames();

		//Visit all Nodes of AST
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
		{
			Visit(entry.getValue());
			scopeTable.insert(entry.getKey(),entry.getValue().typeid);
		}

		//Visit all the Class methods
		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
		{
			filename = cl.filename;
			Visit(entry.getValue());
		}

		//Exit scope before leaving Class
		scopeTable.exitScope();
	}

	//Attribute Visitor
	public void Visit(AST.attr at)
	{
		Visit(at.value);
		//isConforming()
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
		//No_Expr
		if(exp instanceof AST.no_expr)
		{
			AST.no_expr expr = (AST.no_expr)exp;
			expr.type = "No_type";
		}
		
		//Bool
		else if(exp instanceof AST.bool_const)
		{
			AST.bool_const expr = (AST.bool_const)exp;
			expr.type = "Bool";
		}
		
		//String
		else if(exp instanceof AST.string_const)
		{
			AST.string_const expr = (AST.string_const)exp;
			expr.type = "String";
		}
		
		//Int
		else if(exp instanceof AST.int_const)
		{
			AST.int_const expr = (AST.int_const)exp;
			expr.type = "Int";
		}
		
		//Boolean complement
		else if(exp instanceof AST.comp)
		{
			AST.comp expr = (AST.comp)exp;
			Visit(expr.e1);
			
			if(!"Bool".equals(expr.e1.type))
			{
				Semantic.reportError(filename,expr.lineNo,"Expression for 'not' is not of Bool type");	
			}
			expr.type = "Bool";
		}
		
		//Equal to
		else if(exp instanceof AST.eq)
		{
			AST.eq expr = (AST.eq)exp;
			Visit(expr.e1);
			Visit(expr.e2);

			if(!expr.e1.type.equals(expr.e2.type))
			{
				Semantic.reportError(filename,expr.lineNo,"Type Mismatch of Operands in '=' Expression : "+expr.e1.type+", "+expr.e2.type);
			}
			else
			{
				expr.type = "Bool";
			}
		
		}
		
		//Less than or Equal to
		else if(exp instanceof AST.leq)
		{
			AST.leq expr = (AST.leq)exp;
			Visit(expr.e1);
			Visit(expr.e2);

			if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.e1.lineNo,"Left-hand Expression for '<=' is not of Int type");
			if(!expr.e2.type.equals("Int"))
				Semantic.reportError(filename,expr.e2.lineNo,"Right-hand Expression for '<=' is not of Int type");
			expr.type = "Bool";
		}
		
		//Strictly less than
		else if(exp instanceof AST.lt)
		{
			AST.lt expr = (AST.lt)exp;
			Visit(expr.e1);
			Visit(expr.e2);

			if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.e1.lineNo,"Left-hand Expression for '<' is not of Int type");
			if(!expr.e2.type.equals("Int"))
				Semantic.reportError(filename,expr.e2.lineNo,"Right-hand Expression for '<' is not of Int type");
			expr.type = "Bool";
		}
		
		//Integer Complement
		else if(exp instanceof AST.neg)
		{
			AST.neg expr = (AST.neg)exp;
			Visit(expr.e1);

			if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.lineNo,"Expression for 'not' is not of Int type");
			expr.type = "Int";
		}
		
		//Division
		else if(exp instanceof AST.divide) 
		{
			AST.divide expr = (AST.divide)exp;
			Visit(expr.e1);
			Visit(expr.e2);

	        if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.e1.lineNo,"Dividend is not of Int type");
			if(!expr.e2.type.equals("Int"))
				Semantic.reportError(filename,expr.e2.lineNo,"Divisor is not of Int type");
	        expr.type = "Int";
		}
		
		//Multiplication
		else if(exp instanceof AST.mul) 
		{
			AST.mul expr = (AST.mul)exp;
			Visit(expr.e1);
			Visit(expr.e2);

	        if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.e1.lineNo,"Left-hand Multiplicand is not of Int type");
			if(!expr.e2.type.equals("Int"))
				Semantic.reportError(filename,expr.e2.lineNo,"Right-hand Multiplicand is not of Int type");
	        expr.type = "Int";
		}
		
		//Subtraction
		else if(exp instanceof AST.sub) 
		{
			AST.sub expr = (AST.sub)exp;
			Visit(expr.e1);
			Visit(expr.e2);

	        if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.e1.lineNo,"Left-hand Operand for '-' is not of Int type");
			if(!expr.e2.type.equals("Int"))
				Semantic.reportError(filename,expr.e2.lineNo,"Right-hand Operand for '-' is not of Int type");
	        expr.type = "Int";
		}
		
		//Addition
		else if(exp instanceof AST.plus) 
		{
			AST.plus expr = (AST.plus)exp;
			Visit(expr.e1);
			Visit(expr.e2);

	        if(!expr.e1.type.equals("Int"))
				Semantic.reportError(filename,expr.e1.lineNo,"Left-hand Operand for '+' is not of Int type");
			if(!expr.e2.type.equals("Int"))
				Semantic.reportError(filename,expr.e2.lineNo,"Right-hand Operand for '+' is not of Int type");
	    	expr.type = "Int";
		}
		
		//Isvoid
		else if(exp instanceof AST.isvoid) 
		{
			AST.isvoid expr = (AST.isvoid)exp;
			expr.type = "Bool";
		}

		//New
		else if(exp instanceof AST.new_)
		{
			AST.new_ expr = (AST.new_)exp;
			if(Semantic.inheritance.GetClassIndex(expr.typeid)!=null)
				expr.type = expr.typeid;
			else
			{
				Semantic.reportError(filename,expr.lineNo,"Undefined type '" + expr.typeid + "' for 'new' Expression");
				expr.type = "Object";
			}
		}

		//Block
		else if(exp instanceof AST.block)
		{
			AST.block bl = (AST.block)exp;
			for(AST.expression e: bl.l1)
				Visit(e);

			bl.type = bl.l1.get(bl.l1.size()-1).type;
		}
		
		//Loop
		else if(exp instanceof AST.loop)
		{
			AST.loop lp = (AST.loop)exp;
			Visit(lp.predicate);

			if(!lp.predicate.type.equals("Bool"))
				Semantic.reportError(filename,lp.lineNo,"Loop condition not of Bool type");
			Visit(lp.body);
			lp.type = "Object";
		}

		//Let
		else if(exp instanceof AST.let)
		{
			AST.let lt = (AST.let)exp;
			Visit(lt.value);
			Visit(lt.body);

			scopeTable.enterScope();
			lt.typeid = lt.body.type;
			scopeTable.exitScope();
		}
	}
	

	public void Visit(AST.assign expr)
	{
		//update scope table
	}
	
	public void Visit(AST.cond expr)
	{

	}

}
