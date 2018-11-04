class Main inherits IO {
	i : Int;
	s : String;
	main() : Int {
		{
			s <- "ok";
			s.concat("ok");
			i <- in_int();
			1;
		}
	};
	
	ok(a : A) : Int {
		1
	};
};

--Multiple different Redefinitons of foo()
class A inherits IO {
	j : Int;
	k : Int;
	foo(i : Int) : Int { 
		{
			j <- in_int();
			2+2+1;
		}
	};
	fo(h : Int,i : Int) : Int { i };
};

class B inherits A {

	foo(i : Int) : Int { 1 };

};

class C inherits B {
	j : Int;
	foo(i : Int) : Int { 1 };

};
