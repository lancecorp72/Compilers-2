class Main inherits IO {
	i : Int <- 0;
	j : Int;
	s : String;
	main() : Int {
		{
			j = 5 / i;
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
	foo(i : Int) : Int { 1 };

};
