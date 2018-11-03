package cool;
import java.util.*;
public class ScopeTable<T> {
	private int scope;
	private int size;
	private String lastVar;
	private ArrayList<HashMap<String, T>> maps=new ArrayList<HashMap<String, T>>();
	public ScopeTable(){
		scope = 0;
		size = 1;
		lastVar = "";
		maps.add(new HashMap<String, T>());
	}
	void insert(String s, T t){
		maps.get(scope).put(s,t);
		if(t instanceof String)
		{
			String ts = (String) t;
			if(ts.charAt(0)=='%' && ts.charAt(1)=='v')
				size++;
		}
		lastVar = s;
	}
	void enterScope(){
		scope++;
		maps.add(new HashMap<String, T>());
	}
	void exitScope(){
		if (scope>0){
			maps.remove(scope);
			scope--;
		}
	}	
	T lookUpLocal(String t){
		return maps.get(scope).get(t);
	}
	T lookUpGlobal(String t){
		for ( int i = scope; i>=0 ; i--){
			if (maps.get(i).containsKey(t))
				return maps.get(i).get(t);
		}
		return null;
	}
	int getSize(){
		return size;
	}
	String getLastVar(){
		return lastVar;
	}
}
