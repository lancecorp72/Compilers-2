-- Program to illustrate the precedence of operators
class Main inherits IO {
	a : Float;
	b : Float;
	c : Float;
	x : Int;
	main() : Object 
	{
		{
			--Initializing variables
			a <- 1;
			b <- 2;

			--Simple assignment with 2 operators
			c <- (a+b)/a;

			--Complex assignment with multiple subnodes
			--in AST according to precedence
			c <- a-b*c+1*a*b;
			
			--Another complex assignment but with ()
			--to override normal precedences
			c <- (a-b)*(c+1)*a+b;

			--Integer complement ranks above MDAS
			x <- ~ 1 + 2;

			--not takes lesser precedence
			a <- not x < y;
			--() used here to apply not on x
			b <- (not x) < y;
			--Note that COOL compiler will flag this a type mismatch error 
			--but the parser executes without errors

			--isvoid > '=' > not
			a <- not a = isvoid b;
		}
	};
};
