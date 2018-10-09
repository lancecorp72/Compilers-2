package cool;

import java.util.*;

class Node
{
	public String name;
	public int index;
	public int parentIndex;
	public String filename;
	public int lineNo;
	public HashMap<String,AST.feature> features;

	Node(String n, int i, int pi, String f, int l, HashMap<String,AST.feature> fs)
	{
		name = n;
		index = i;
		parentIndex = pi;
		filename = f;
		lineNo = l;
		features = new HashMap<String,AST.feature>();
		features.putAll(fs);
	}
}

public class Inheritance
{
	private static Node ROOT = new Node("Object",0,-1,"Inbuilt Classes",0,new HashMap<String,AST.feature>());

	private ArrayList<Node> graph;
	private HashMap<String,Integer> classList;

	public Inheritance()
	{
		graph = new ArrayList<Node>();
		classList = new HashMap<String,Integer>();
		graphInitialize();
	}

	void graphInitialize()
	{
		//Object Class
		ROOT.features.put("abort", new AST.method("abort",new ArrayList<AST.formal>(),"Object",new AST.no_expr(0),0));
		ROOT.features.put("type_name", new AST.method("type_name",new ArrayList<AST.formal>(),"String",new AST.no_expr(0),0));
		ROOT.features.put("copy", new AST.method("copy",new ArrayList<AST.formal>(),"Object",new AST.no_expr(0),0));
		graph.add(ROOT);
		classList.put("Object",0);

		//IO Class
		Node IOClass = new Node("IO",1,0,"Inbuilt Classes",0,new HashMap<String,AST.feature>());
		IOClass.features.put("in_string", new AST.method("in_string",new ArrayList<AST.formal>(),"String",new AST.no_expr(0),0));
		IOClass.features.put("in_int", new AST.method("in_int",new ArrayList<AST.formal>(),"Int",new AST.no_expr(0),0));
		IOClass.features.put("out_string", new AST.method("out_string",Arrays.asList(new AST.formal("x","String",0)),"IO",new AST.no_expr(0),0));
		IOClass.features.put("out_int", new AST.method("out_int",Arrays.asList(new AST.formal("x","Int",0)),"IO",new AST.no_expr(0),0));
		graph.add(IOClass);
		classList.put("IO",1);

		//Int Class
		Node IntClass = new Node("Int",2,0,"Inbuilt Classes",0,new HashMap<String,AST.feature>());
		graph.add(IntClass);
		classList.put("Int",2);

		//Bool Class
		Node BoolClass = new Node("Bool",3,0,"Inbuilt Classes",0,new HashMap<String,AST.feature>());
		graph.add(BoolClass);
		classList.put("Bool",3);

		//String Class
		Node StringClass = new Node("String",4,0,"Inbuilt Classes",0,new HashMap<String,AST.feature>());
		StringClass.features.put("length", new AST.method("length",new ArrayList<AST.formal>(),"Int",new AST.no_expr(0),0));
		StringClass.features.put("concat", new AST.method("concat",Arrays.asList(new AST.formal("s","String",0)),"String",new AST.no_expr(0),0));
		StringClass.features.put("substr", new AST.method("substr",Arrays.asList(new AST.formal("i","Int",0),new AST.formal("l","Int",0)),"String",new AST.no_expr(0),0));
		graph.add(StringClass);
		classList.put("String",4);
	}

	public Integer getIndex(String name)
	{
		return classList.get(name);
	}

	public void insertClass(AST.class_ newClass)
	{
		//Checking existence of another class with same name
		if(classList.containsKey(newClass.name))
		{
			if(getIndex(newClass.name)<=4)
				Semantic.reportError(newClass.filename,newClass.lineNo,"Basic Class '"+newClass.name+"' redefined");
			else
				Semantic.reportError(newClass.filename,newClass.lineNo,"Second definition of '"+newClass.name+"' class");
		}
		else
		{
			if(classList.containsKey(newClass.parent))
			{
				if(Arrays.asList("String","Int","Bool").contains(newClass.parent))
					Semantic.reportError(newClass.filename,newClass.lineNo,"Class '"+newClass.name+"' derives from non-derivable base class '"+newClass.parent+"'");
				else
				{
					classList.put(newClass.name,graph.size());
					Node newNode = new Node(newClass.name,graph.size(),classList.get(newClass.parent),newClass.filename,newClass.lineNo,new HashMap<String,AST.feature>());
					graph.add(newNode);
				}
			}
			else
				Semantic.reportError(newClass.filename,newClass.lineNo,"'"+newClass.name+"' derives from non-existent class '"+newClass.parent+"'");
		}
	}
}