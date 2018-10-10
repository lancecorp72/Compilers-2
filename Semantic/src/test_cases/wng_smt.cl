class Main {
	i:Bool<- not 1 ;	--argument for not is not of Bool type
	st : String <- 1 + "Foo";		-- non integer type addition	
	j : Int;
	
	main():IO {
		{
			new IO.out_string("Hello world!\n");
			new IO.out_string(i);		
			if i = st then "foo" else "bar" fi;		-- i is bool whereas st is String
		if st then 2 <= true else ~false fi;	-- if condition not of Bool type and ~ used for non-Int type and expressions on either side of <= are of different types
			4 - "something";	--operand not of Int type
			new J;				--class J undefined
			while st < 1 loop	--expressions on either side of < are of different types
			{
			    2 / true;		--operand not of Int type
				3 * "hello";	--operand not of Int type
			}
		    pool;
			--let a : A , b : B <- a  in b;
			let i : Int  in u;	--undefined attribute
		}
		
		
	};
	
};
class A
{
	q : Int <- 2;
};
class B inherits A
{
	st : String <- 1;		--Type Mismatch
	foo(): Object
	{	
		2
	};
};
class C
{
	a : A;
	a1 : A;
	b : B <- new B;
	c : String;
	d : Int;
	foo() : Int {
		{
			--a1 <- a;
			d <- 1;
			--a.boo();
		}
	};
};




