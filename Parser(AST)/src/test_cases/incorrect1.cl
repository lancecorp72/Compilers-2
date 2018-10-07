-- Program to illustrate parse errors

class Main inherits IO {
	i : Int;
	b : Bool;
	main() : Object 
	{
		{
			i <- 10;
			
		--Parse error flagged here for missing '}'
	};
};
