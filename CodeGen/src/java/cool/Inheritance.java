package cool;

import java.util.*;

//Class which defines each Node in Inheritance Graph
class Node
{
	public String name;
	public int index;
	public String parent;
	public String filename;
	public int lineNo;
	public HashMap<String,AST.attr> attributes;
	public HashMap<String,AST.method> methods;
	public HashMap<String,String> inheritedAttributes;
	public HashMap<AST.method,String> inheritedMethods;

	//Constructor
	Node(String n, int i, String p, String f, int l, HashMap<String,AST.attr> as, HashMap<String,AST.method> ms)
	{
		name = n;
		index = i;
		parent = p;
		filename = f;
		lineNo = l;
		attributes = new HashMap<String,AST.attr>();
		attributes.putAll(as);
		methods = new HashMap<String,AST.method>();
		methods.putAll(ms);
		inheritedAttributes = new HashMap<String,String>();
		inheritedMethods = new HashMap<AST.method,String>();
	}
}

//Class to build an Inheritance Graph
public class Inheritance
{
	private static Node ROOT = new Node("Object",0,null,"Inbuilt Classes",0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());

	private ArrayList<Node> graph;
	private HashMap<String,Integer> classList;
	private HashMap<String,String> mangledNames;

	//Constructor
	public Inheritance()
	{
		graph = new ArrayList<Node>();
		classList = new HashMap<String,Integer>();
		mangledNames = new HashMap<String,String>();
		GraphInitialize();
	}

	//Initialize Inheritance Graph with Base classes
	void GraphInitialize()
	{
		//Object Class
		ROOT.methods.put("abort", new AST.method("abort",new ArrayList<AST.formal>(),"Object",new AST.no_expr(0),0));
		ROOT.methods.put("type_name", new AST.method("type_name",new ArrayList<AST.formal>(),"String",new AST.no_expr(0),0));
		ROOT.methods.put("copy", new AST.method("copy",new ArrayList<AST.formal>(),"SELF_TYPE",new AST.no_expr(0),0));
		ROOT.attributes.put("self", new AST.attr("self","SELF_TYPE",(AST.expression)new AST.no_expr(0),0));
		graph.add(ROOT);
		classList.put("Object",0);

		//IO Class
		Node IOClass = new Node("IO",1,"Object","Inbuilt Classes",0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		IOClass.methods.put("in_string", new AST.method("in_string",new ArrayList<AST.formal>(),"String",new AST.no_expr(0),0));
		IOClass.methods.put("in_int", new AST.method("in_int",new ArrayList<AST.formal>(),"Int",new AST.no_expr(0),0));
		IOClass.methods.put("out_string", new AST.method("out_string",Arrays.asList(new AST.formal("x","String",0)),"IO",new AST.no_expr(0),0));
		IOClass.methods.put("out_int", new AST.method("out_int",Arrays.asList(new AST.formal("x","Int",0)),"IO",new AST.no_expr(0),0));
		graph.add(IOClass);
		classList.put("IO",1);

		//Int Class
		Node IntClass = new Node("Int",2,"Object","Inbuilt Classes",0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		graph.add(IntClass);
		classList.put("Int",2);

		//Bool Class
		Node BoolClass = new Node("Bool",3,"Object","Inbuilt Classes",0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		graph.add(BoolClass);
		classList.put("Bool",3);

		//String Class
		Node StringClass = new Node("String",4,"Object","Inbuilt Classes",0,new HashMap<String,AST.attr>(),new HashMap<String,AST.method>());
		StringClass.methods.put("length", new AST.method("length",new ArrayList<AST.formal>(),"Int",new AST.no_expr(0),0));
		StringClass.methods.put("concat", new AST.method("concat",Arrays.asList(new AST.formal("s","String",0)),"String",new AST.no_expr(0),0));
		StringClass.methods.put("substr", new AST.method("substr",Arrays.asList(new AST.formal("i","Int",0),new AST.formal("l","Int",0)),"String",new AST.no_expr(0),0));
		graph.add(StringClass);
		classList.put("String",4);
	}

	//Returns index of corresponding Class
	public Integer GetParentIndex(String name)
	{
		if(classList.containsKey(name))
			return classList.get(graph.get(classList.get(name)).parent);
		return null;
	}

	//Returns index of corresponding Class
	public Integer GetClassIndex(String name)
	{
		return classList.get(name);
	}

	//Returns filename of corresponding Class
	public String GetClassFilename(String name)
	{
		if(classList.containsKey(name))
			return graph.get(classList.get(name)).filename;
		return "";
	}

	public String GetMangledName(String className,AST.method md)
	{
		String temp = "";
    	temp += "_CN";
    	String funcName = md.name;
    	temp += (Integer.toString(className.length()));
    	temp += "_";
    	temp += (className);
    	temp += ("_FN");
    	temp += (Integer.toString(funcName.length()));
    	temp += (funcName);
    	temp += ("_AL");
    	temp += (Integer.toString(md.formals.size()));
    	if(md.formals.size() == 0)
    		temp += ("_NP_");
    	else
    	{
    		for(int j = 0; j < md.formals.size(); j++)
    		{
    			temp += (Integer.toString(j));
    			temp += ("N");
    			temp += (Integer.toString(md.formals.get(j).typeid.length()));
    			temp.concat(md.formals.get(j).typeid);
    		}
    	}

    	return temp;
	}

	public String GetReturnType(String s)
	{
		if(CheckMangledName(s))
			return mangledNames.get(s);
		return null;
	}
	//Checks if a match exists for given Function signature
	public boolean CheckMangledName(String s)
	{
		return mangledNames.containsKey(s);
	}
	
	//For checking Validity of Assign Operation
	public boolean isConforming(String type1, String type2)
	{
		//type1 <- type2
		if(type1.equals(type2)||"Object".equals(type1))
			return true;
		
		if(type1.equals("Bool")||type1.equals("Int")||type1.equals("String")||type2.equals("Bool")||type2.equals("Int")||type2.equals("String"))
			return false;

		String itr = type2;
		while(GetClassIndex(itr)!=0)
		{
			if(itr.equals(type1))
				return true;

			itr = graph.get(GetParentIndex(itr)).name;
		}
		
		return false;
	}

	//Finds Least Common Ancestor of two given Classes
	public String GetLCA(String cl1, String cl2)
	{
		String itr = cl1;
		ArrayList<String> anc = new ArrayList<>();

		//Add Inheritance path of 'cl1' to 'anc'
		while(GetClassIndex(itr)!= 0)
		{
			anc.add(itr);
			itr = graph.get(GetParentIndex(itr)).name;
		}

		itr = cl2;

		//Iterate till a Common Ancestor Class is found
		while(GetClassIndex(itr)!=0)
		{
			if(anc.contains(itr))
				break;
			itr = graph.get(GetParentIndex(itr)).name;
		}

		if(GetClassIndex(itr)==0)
			return "Object";

		return itr;
	}

	//Returns Attribute Hashmap of corresponding Class
	public HashMap<String,AST.attr> GetClassAttrs(String name)
	{
		return graph.get(classList.get(name)).attributes;
	}

	//Returns Method Hashmap of corresponding Class
	public HashMap<String,AST.method> GetClassMethods(String name)
	{
		HashMap<String,AST.method> mds = new HashMap<String,AST.method>();
		Node nd = graph.get(classList.get(name));
		mds.putAll(nd.methods);

		for(Map.Entry<AST.method,String> entry: nd.inheritedMethods.entrySet())
			mds.put(entry.getKey().name,entry.getKey());

		return mds;
	}

	//Insert non-duplicate class into Inheritance Graph
	public void InsertClass(AST.class_ newClass)
	{
		//Checking existence of another class with same name
		if(classList.containsKey(newClass.name))
		{
			if(GetClassIndex(newClass.name)<=4)
				Semantic.reportError(newClass.filename,newClass.lineNo,"Basic Class '"+newClass.name+"' redefined");
			else
				Semantic.reportError(newClass.filename,newClass.lineNo,"Second definition of '"+newClass.name+"' class");
		}
		else
		{
			classList.put(newClass.name,graph.size());
			HashMap<String,AST.method> nodeMethods = new HashMap<String,AST.method>();
			HashMap<String,AST.attr> nodeAttributes = new HashMap<String,AST.attr>();
			for(AST.feature f: newClass.features)
			{
				//Checking whether if Method or Attribute
				if(f instanceof AST.method)
				{
					AST.method m = (AST.method)f;
					if(!nodeMethods.containsKey(m.name))
						nodeMethods.put(m.name,m);
					else
						Semantic.reportError(newClass.filename,m.lineNo,"Multiple definitions of '"+m.name+"()' Method in Class "+newClass.name);
				}
				else
				{
					AST.attr a = (AST.attr)f;
					if(!nodeAttributes.containsKey(a.name))
						nodeAttributes.put(a.name,a);
					else
						Semantic.reportError(newClass.filename,a.lineNo,"Multiple definitions of '"+a.name+"' Attribute in Class "+newClass.name);
				}
			}
			Node newNode = new Node(newClass.name,graph.size(),newClass.parent,newClass.filename,newClass.lineNo,nodeAttributes,nodeMethods);
			graph.add(newNode);
		}
	}

	//Checking validity of all Classes
	public void CheckClass()
	{
		//Checking if 'Main' class exists and it has a 'main()' method
		if(classList.containsKey("Main"))
		{
			Node n = graph.get(classList.get("Main"));
			if(n.methods.containsKey("main"))
			{
				if(!n.methods.get("main").formals.isEmpty())
					Semantic.reportError((graph.get(5)).filename,1,"main() Method in Main Class should not take any arguments");
			}
			else
				Semantic.reportError((graph.get(5)).filename,1,"Main Class doesn't contain main() Method");
		}
		else
			Semantic.reportError("",0,"Main Class not defined in program");

		for(int i=1; i<graph.size(); i++)
		{
			Node graphNode = graph.get(i);

			//Checking if parent class exists in Graph
			if(classList.containsKey(graphNode.parent))
			{
				//Checking if class derives from a non-derivable class
				if(Arrays.asList("String","Int","Bool").contains(graphNode.parent))
					Semantic.reportError(graphNode.filename,graphNode.lineNo,"Class '"+graphNode.name+"' derives from non-derivable base class '"+graphNode.parent+"'");
			}
			else
				Semantic.reportError(graphNode.filename,graphNode.lineNo,"'"+graphNode.name+"' derives from non-existent class '"+graphNode.parent+"'");
		}
	}

	//Checking existence of Cycles in Graph
	public void CheckCycle()
	{
		boolean[] isVisited = new boolean[graph.size()];
		boolean isCycle=false;

		//Setting all Base Classes as visited
		for(int i=0; i<=4; i++)
			isVisited[i]=true;

		//Visiting rest of the classes
		for(int i=5; i<graph.size(); i++)
		{
			int index=i;
			String cycle="";
			ArrayList<String> path = new ArrayList<String>();

			//Traversing up a chain of inherited classes
			while(!isVisited[index])
			{
				isVisited[index] = true;
				path.add(graph.get(index).name);
				index = GetClassIndex(graph.get(index).parent);
			}

			//Finding cycles in Graph
			if(path.contains(graph.get(index).name))
			{
				isCycle = true;
				cycle += "Cycle encountered in Inheritance Graph : ";
				for(int j=0; j<path.size(); j++)
					cycle += path.get(j)+", ";
				cycle += graph.get(index).name;
				Semantic.reportError(graph.get(index).filename,graph.get(index).lineNo,cycle);
			}
		}
	}

	//Collect Attributes from Inherited Class
	public void CheckInheritedFeatures()
	{
		boolean[] visited = new boolean[graph.size()];
		visited[0] = true;
		int count = graph.size()-1;

		for(int i=1; i<graph.size(); i++)
		{
			if(visited[i]==false)
				visited = CheckInheritedFeaturesHelper(i,visited);
		}
	}

	//Helper Function to wait till Parent Class is visited before Accessing a Class
	private boolean[] CheckInheritedFeaturesHelper(int classId, boolean[] visited)
	{
		Node curNode = graph.get(classId);
		int parentId = GetParentIndex(curNode.name);

		if(visited[parentId]==false)
		{
			visited = CheckInheritedFeaturesHelper(parentId,visited);
		}

		//Inserting Inherited Attributes into 'inheritedAttributes'
		Node parNode = graph.get(GetParentIndex(curNode.name));
		for(Map.Entry<String,AST.attr> entry: parNode.attributes.entrySet())
		{
			if(curNode.attributes.containsKey(entry.getKey()))
			{
				String s = parNode.inheritedAttributes.get(entry.getKey());
				if(s==null)
					s=parNode.name;
				Semantic.reportError(curNode.filename,curNode.lineNo,"Redefinition of '"+entry.getKey()+"' Attribute inherited from Class '"+s+"'");
			}
			else
			{
				curNode.attributes.put(entry.getKey(),entry.getValue());
				String s = parNode.inheritedAttributes.get(entry.getKey());
				if(s==null)
					curNode.inheritedAttributes.put(entry.getKey(),parNode.name);
				else
					curNode.inheritedAttributes.put(entry.getKey(),s);
			}
		}

		//Inserting Methods of current Class into Class-Global 'mangledNames'
		for (Map.Entry<String,AST.method> entry : curNode.methods.entrySet())
		{
			String s = GetMangledName(curNode.name,entry.getValue());
			mangledNames.put(s,entry.getValue().typeid);
		}

		for (Map.Entry<AST.method,String> entry : parNode.inheritedMethods.entrySet())
		{
			String s = GetMangledName(curNode.name,entry.getKey());
			if(mangledNames.containsKey(s)==true)
			{
				if(mangledNames.get(s).equals(entry.getKey().typeid)==false)
					Semantic.reportError(curNode.filename,entry.getKey().lineNo,"Redefintion of Method '"+entry.getValue()+"()' in Class '"+curNode.name+"' should have Return type: '"+entry.getKey().typeid+"' as defined in Class '"+entry.getValue()+"'");
			}
			else if(curNode.methods.containsKey(entry.getKey().name))
				Semantic.reportError(curNode.filename,entry.getKey().lineNo,"Redefintion of Method '"+entry.getValue()+"()' in Class '"+curNode.name+"' should have same Arguments as in Class '"+entry.getValue()+"'");
			else
			{
				mangledNames.put(s,entry.getKey().typeid);
				curNode.inheritedMethods.put(entry.getKey(),entry.getValue());
			}
		}

		for (Map.Entry<String,AST.method> entry : parNode.methods.entrySet())
		{
			String s = GetMangledName(curNode.name,entry.getValue());
			if(mangledNames.containsKey(s)==true)
			{
				if(mangledNames.get(s).equals(entry.getValue().typeid)==false)
					Semantic.reportError(curNode.filename,entry.getValue().lineNo,"Redefintion of Method '"+entry.getKey()+"()' in Class '"+curNode.name+"' should have Return type: '"+entry.getValue().typeid+"' as defined in Class '"+parNode.name+"'");
			}
			else if(curNode.methods.containsKey(entry.getValue().name)==true)
				Semantic.reportError(curNode.filename,entry.getValue().lineNo,"Redefintion of Method '"+entry.getKey()+"()' in Class '"+curNode.name+"' should have same Arguments as in Class '"+parNode.name+"'");
			else
			{
				mangledNames.put(s,entry.getValue().typeid);
				curNode.inheritedMethods.put(entry.getValue(),parNode.name);
			}
		}

		graph.set(classId,curNode);
		visited[classId] = true;

		return visited;
	}
}
