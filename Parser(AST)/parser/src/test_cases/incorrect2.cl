-- Program to illustrate parse errors

class Main inherits IO {
	i : Int;
	b : Bool;
	main() : Object 
	{
		{
			i <- ;	--Parse error flagged here for missing expression
			i <- i*10;
		}
	};
};
