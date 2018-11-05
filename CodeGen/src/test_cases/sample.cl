class Main inherits IO {
	i : Int;
	c : C;
	main() : Int {
		{
			c <- new C;
			c.set(11);
			out_int(c.foo(1));
			--if (i<5) then 2+2+1 else 2-2-1 fi;
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
			1;
		}
	};
	fo(h : Int,i : Int) : Int { i };
};

class B inherits A {

	foo(i : Int) : Int { 1 };

};

class C inherits B {
	foo(i : Int) : Int { j };
	set(i : Int) : Object { j <- i };

};
