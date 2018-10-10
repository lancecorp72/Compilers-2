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
  
	public void Visit(AST.no_expr expr)
	{
		expr.type = "_no_type";
	}
	
	public void Visit(AST.bool_const expr)
	{
		expr.type = "Bool";
	}
	
	public void Visit(AST.string_const expr)
	{
		expr.type = "String";
	}
	
	public void Visit(AST.int_const expr)
	{
		expr.type = "Int";
	}
	
	public void Visit(AST.comp expr)
	{
		if(!"Bool".equals(expr.e1.type))
		{
			Semantic.reportError(filename,expr.getLineNo(),"Expression not of bool type");	
		}
		expr.type = "Bool";
	}
	
	public void Visit(AST.eq expr)
	{
		if(!expr.e1.type.equals(expr.e2.type))
		{
			Semantic.reportError(filename,expr.getLineno(),"Expressions on either side of = different");
		}
		else
		{
			expr.type = "Bool";
		}
	
	}
	
	public void Visit(AST.leq expr)
	{
		if(!expr.e1.type.equals("Int") || !expr.e2.type.equals("Int"))
			Semantic.reportError(filename,expr.getLineno(),"Expression(s) for <= not of Int type");
		expr.type = "Bool";
	}
	
	public void Visit(AST.lt expr)
	{
		if(!expr.e1.type.equals("Int") || !expr.e2.type.equals("Int"))
			Semantic.reportError(filename,expr.getLineno(),"Expression(s) for < not of Int type");
		expr.type = "Bool";
	}
	
	public void Visit(AST.neg expr)
	{
		if(!expr.e1.type.equals("Int"))
			Semantic.reportError(filename,expr.getLineno(),"Cannot negate non-integer expressions");
		expr.type = "Int";
	}
	
	public void Visit(AST.divide expr) 
	{
        if(!expr.e1.type.equals("Int") || !expr.e2.type.equals("Int")) {
            Semantic.reportError(filename, expr.getLineNo(), "Division cannot be done for non integers");
        }
        expr.type = "Int";
	}
	
	public void Visit(AST.mul expr) 
	{
        if(!expr.e1.type.equals("Int") || !expr.e2.type.equals("Int")) {
            Semantic.reportError(filename, expr.getLineNo(), "Multiplication cannot be done for non integers");
        }
        expr.type = "Int";
	}
	
	public void Visit(AST.sub expr) 
	{
        if(!expr.e1.type.equals("Int") || !expr.e2.type.equals("Int")) {
            Semantic.reportError(filename, expr.getLineNo(), "Subtraction cannot be done for non integers");
        }
        expr.type = "Int";
	}
	
	public void Visit(AST.plus expr) 
	{
        if(!expr.e1.type.equals("Int") || !expr.e2.type.equals("Int")) {
            Semantic.reportError(filename, expr.getLineNo(), "Addition cannot be done for non integers");
        }
        expr.type = "Int";
	}
	
	public void Visit(AST.isvoid expr) 
	{
		expr.type = "Bool";
	}
	
	public void Visit(AST.new_ expr)
	{
		if(Semantic.inheritance.classList.containsKey(expr.typeid))
			expr.type = expr.typeid;
		else
		{
			Semantic.reportError(filename,expr.getLineno(),"Undefined type" + expr.typeid + "for new");
			expr.type = "Object";
		}
	}
	
	public void Visit(AST.assign expr)
	{
		//update scope table
	}
	
	public void Visit(Ast.block bl)
	{
		bl.type = bl.l1.get(bl.l1.size()-1).type;
	}
	
	public void Visit(AST.loop lp)
	{
		if(!lp.predicate.type.equals("Bool"))
			Semantic.reportError(filename,lp.getLineno(),"Loop condition not of Bool type");
		lp.type = "Object";
	}
	
	public void Visit(AST.cond expr)
	{
		if(!expr.predicate.type.equals("Bool"))
			Semantic.reportError(filename,expr.getLineno(),"if condition not of Bool type");
		String type1 = expr.ifbody.type;
		String type2 = expr.elsebody.type;	
		if(type1.equals(type2))
			expr.type = type1;
		else if(type1.equals("Int")||type1.equals("String")||type1.equals("Bool") || type2.equals("Int")||type2.equals("String")||type2.equals("Bool"))
		{
			expr.type = "Object";
		}
		else
		{
			Node t1 = Semantic.inheritance.graph.get(classList.get(type1));
			Node t2 = Semantic.inheritance.graph.get(classList.get(type2));
			Node lca = LCA(t1,t2);
			expr.type = lca.name;
		}
	}

}
