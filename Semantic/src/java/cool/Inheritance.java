package cool;

import java.util.*;

class Node
{
	public String name;
	public int index;
	public int parentIndex;
	public HashMap<String,AST.attr> variables;
	public HashMap<String,AST.method> functions;

	Node(String n, int i, int pi, HashMap<String,AST.attr> v, HashMap<String,AST.method> f)
	{
		name = n;
		index = i;
		parentIndex = pi;
		variables = new HashMap<String,AST.attr>();
		variables.putAll(v);
		functions = new HashMap<String,AST.method>();
		functions.putAll(f);
	}
}

public class Inheritance
{
	private static Node ROOT = new Node("Object",0,-1,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());

	private ArrayList<Node> graph;
	private HashMap<String,Integer> ClassList;

	public Inheritance()
	{
		graph = new ArrayList<Node>();
		ClassList = new HashMap<String,Integer>();
		graphInitialize();
	}

	void graphInitialize()
	{
		//Object Class
		ROOT.functions.put("abort", new AST.method("abort",new ArrayList<AST.formal>(),"Object",new AST.no_expr(0),0));
		ROOT.functions.put("type_name", new AST.method("type_name",new ArrayList<AST.formal>(),"String",new AST.no_expr(0),0));
		ROOT.functions.put("copy", new AST.method("copy",new ArrayList<AST.formal>(),"Object",new AST.no_expr(0),0));
		graph.add(ROOT);
		ClassList.put("Object",0);

		//IO Class
		Node IOClass = new Node("IO",1,0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		IOClass.functions.put("in_string", new AST.method("in_string",new ArrayList<AST.formal>(),"String",new AST.no_expr(0),0));
		IOClass.functions.put("in_int", new AST.method("in_int",new ArrayList<AST.formal>(),"Int",new AST.no_expr(0),0));
		IOClass.functions.put("out_string", new AST.method("out_string",Arrays.asList(new AST.formal("x","String",0)),"IO",new AST.no_expr(0),0));
		IOClass.functions.put("out_int", new AST.method("out_int",Arrays.asList(new AST.formal("x","Int",0)),"IO",new AST.no_expr(0),0));
		graph.add(IOClass);
		ClassList.put("IO",1);

		//Int Class
		Node IntClass = new Node("Int",2,0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		graph.add(IntClass);
		ClassList.put("Int",2);

		//Bool Class
		Node BoolClass = new Node("Bool",3,0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		graph.add(BoolClass);
		ClassList.put("Bool",3);
	}

	int getIndex(String name)
	{
		return ClassList.get(name);
	}
}