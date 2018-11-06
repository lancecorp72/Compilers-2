-- Description in Readme.md
class Main inherits IO{
	i : Int;
	s : String;
	t : String;
	main(): Int {
		{
			i <- in_int();
			out_int(i);
			s <- in_string();
			out_string(s);
			i <- s.length();
			out_int(i);
			t <- s.substr(0,1);
			t <- s.concat(t);
			out_string(t);
			1;
		}
	};
};

