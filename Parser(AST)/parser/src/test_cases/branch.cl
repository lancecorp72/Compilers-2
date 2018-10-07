-- Program to illustrate the AST containing IF, WHILE and LET
class Main inherits IO {
	i : Int;
	j : Int;
	b : Bool;
	main() : Object 
	{
		{
			i <- 10;

			--Generated AST becomes deep
			b = if i < 5 then
					if i < 6 then
						if i < 7 then
							if i < 8 then true
							else false fi
						else false fi
					else false fi
				else false fi;

			i <- 5;
			--sample double while loop
			while i < 10 loop
				(let j : Int <- 0 in
					while j < 3 loop
						{
							j <- j + 1;
							i <- i + 1;
						}
					pool
				)
			pool;
		}
	};
};
