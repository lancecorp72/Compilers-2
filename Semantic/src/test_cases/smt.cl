class Main {
	i:Bool<- not 1 + "test";
	st : String <- "Foo";
	j : Int;
	
	main():IO {
		{
			new IO.out_string("Hello world!\n");
			new IO.out_string(i);	
			if i = st then "foo" else "bar" fi;
			if st then 2 <= true else ~false fi;
			4 - "something";
			new A;
			while st < 1 loop
			{
			    2 / true;
				3 * "hello";
			}
		    pool;
			
		}
		
		
	};
	
};
class A
{
	q : Int <- 2;
};
class B inherits A
{
	st : String <- 1;
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




