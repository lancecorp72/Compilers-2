class Main {
	i : Int;

	main() : Int {
		{
			i <- 1;
			1;
		}
	};
	
	ok(a : A) : Int {
		1
	};
};

--Multiple different Redefinitons of foo()
class A {
	j : Int;
	foo(i : Int) : Int { 2+2+1 };
	fo(h : Int,i : Int) : Int { i };
};

class B inherits A {
	foo(i : Int) : Int { 1 };

};

class C inherits A {
	foo(i : Int) : Int { 1 };

};
