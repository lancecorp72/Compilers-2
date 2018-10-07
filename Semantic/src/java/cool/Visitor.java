package cool;

public class Visitor
{

	public void visit(AST.program prog)
	{
		Semantic.inheritance = new Inheritance();
	}

}