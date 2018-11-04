class Main {
	i : Int <- 1;
	
	main() : Int {
		{
			if(i=1) then i <- 2 else i <- 3 fi;
			1 < 2;
		}
	};
	
	ok(a : A) : Int {
		1
	};
};

--Multiple different Redefinitons of foo()
class A {
	j : Int;
	foo(i : Int) : Int { 10/5 };
	fo(h : Int,i : Int) : Int { i };
};

class B inherits A {
	foo(i : Int) : Int { 1 };

};

class C inherits A {
	foo(i : Int) : Int { 1 };

};
