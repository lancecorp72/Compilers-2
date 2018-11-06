package cool;

import java.util.*;

public class PrintNode
{
    //Class Variables
    private HashMap<String,String> clNames;
    private ArrayList<String> baseFns;
    private String className;
    private String indent;
    private Integer varCnt;
    private Integer labCnt;
    private Integer globCnt;
    private String globOut;
    private HashMap<String,ArrayList<AST.attr>> classAttrs;

	//Constructor
	public PrintNode()
	{
        className = "";
        indent = "  ";
        globOut = "";
        varCnt = 0;
        labCnt = 0;
        globCnt = 0;
        clNames = new HashMap<String,String>();
        baseFns = new ArrayList<String>();
        classAttrs = new HashMap<String,ArrayList<AST.attr>>();
	}

    //Check if String is a Number by checking First Digit
    private Boolean isFstDgt(String s)
    {
        if(s.charAt(0)>='0' && s.charAt(0)<='9')
            return true;
        else if(s.charAt(0)=='-' && s.charAt(1)>='0' && s.charAt(1)<='9')
            return true;
        return false;
    }

    //Check if First Char of String is Boolean
    int isBool(String s)
    {
        if(s.charAt(0) == '0')
            return 0;
        else if(s.charAt(0) == '1')
            return 1;
        else
            return -1;
    }

	//Program Visitor
	public void Visit(AST.program program)
	{
        clNames.put("Int","i32");
        clNames.put("String","i8*");
        clNames.put("Bool","i8");

        //Insert Base Functions
        DecBaseFns();
        Codegen.progOut += "\n";

        //Declare Class Structs
        Codegen.progOut += "; Class Attributes defined as Structs\n";
		for(AST.class_ cl: program.classes)
        {
            Codegen.progOut += "%struct."+cl.name+" = type { i8,";
            clNames.put(cl.name,"%struct."+cl.name);
            ArrayList<AST.attr> attrs = new ArrayList<AST.attr>();

            for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs(cl.name).entrySet())
            {
                if(entry.getValue().name.equals("self"))
                    continue;

                attrs.add(entry.getValue());
                String tp = clNames.get(entry.getValue().typeid);
                if(tp == null)
                    tp = "%struct."+entry.getValue().typeid;
                Codegen.progOut += " "+tp+",";
            }
            Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-1);
            Codegen.progOut += " }\n";
            classAttrs.put(cl.name,attrs);
        }
        Codegen.progOut += "\n";

        //Add Class Initializers
        AddConstructors(program);
        Codegen.progOut += "\n";

        //Insert Class Methods
        Codegen.progOut += "; Class Methods Definitions\n";
		for(AST.class_ cl: program.classes)
        {
            if(!cl.name.equals("Main"))
            {
                className = cl.name;
                Visit(cl);
            }
        }
        Codegen.progOut += "\n";

        //ScopeTable<String> varNames = new ScopeTable<String>();
        //for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs("Main").entrySet())
        //{
        //    if(entry.getValue().name.equals("self"))
        //        continue;

        //    varNames.insert(entry.getValue().name,"@"+entry.getValue().name);
        //    Codegen.progOut += "@"+entry.getValue().name+" = global "+clNames.get(entry.getValue().typeid)+" 0\n";
        //}
        //Codegen.progOut += "\n";

        //Insert Main Methods
        HashMap<String,AST.method> mainClassMethods = Semantic.inheritance.GetClassMethods("Main");
        className = "Main";         
		for(Map.Entry<String,AST.method> entry: mainClassMethods.entrySet())
        {
            if(entry.getValue().name.equals("main"))
                continue;
            if(baseFns.contains(entry.getKey()) == false)
            {
                Codegen.progOut += "define "+clNames.get(entry.getValue().typeid)+" @"+Semantic.inheritance.GetMangledName(className,entry.getValue())+"(";
                Visit(entry.getValue());
            }
        }

        //main() Function
        Codegen.progOut += "\n; Main Function\n";
        AST.method md = mainClassMethods.get("main");
        Codegen.progOut += "define "+clNames.get(md.typeid)+" @main (";
        Visit(md);

        //Concat Global variables to Output
        Codegen.progOut = globOut + "\n" + Codegen.progOut;
    }

    //Substring Function
    private void emitSubstr()
    {
        Codegen.progOut += "define i8* @substr(i8* %s, i32 %i, i32 %l) #0 {\nentry:\n  %s.addr = alloca i8*, align 8\n  %i.addr = alloca i32, align 4\n"+ 
        " %l.addr = alloca i32, align 4\n  %k = alloca i32, align 4\n  %res = alloca i8*, align 8\n  %j = alloca i32, align 4\n  store i8* %s, i8** %s.addr, align 8\n"+
        "  store i32 %i, i32* %i.addr, align 4\n  store i32 %l, i32* %l.addr, align 4\n  %0 = load i8*, i8** %s.addr, align 8\n  %call = call i32 @strlen(i8* %0) #5\n "+
        " store i32 %call, i32* %k, align 4\n  %1 = load i32, i32* %k, align 4\n  %2 = load i32, i32* %l.addr, align 4\n  %sub = sub nsw i32 %1, %2\n  %3 = load i32, i32* %i.addr, align 4\n"+
        "  %sub1 = sub nsw i32 %sub, %3\n  %cmp = icmp slt i32 %sub1, 0\n  br i1 %cmp, label %if.then, label %if.end\n\nif.then:\n"+
        "  %call3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([37 x i8], [37 x i8]* @.str, i32 0, i32 0))\n  call void @exit(i32 0) #6\n  unreachable\n\nif.end:\n"+
        "  %4 = load i32, i32* %l.addr, align 4\n  %conv4 = sext i32 %4 to i64\n  %mul = mul i64 %conv4, 1\n  %call5 = call noalias i8* @malloc(i64 %mul) #7\n"+
        "  store i8* %call5, i8** %res, align 8\n  store i32 0, i32* %j, align 4\n  br label %while.cond\n\nwhile.cond:\n  %5 = load i32, i32* %j, align 4\n"+
        "  %6 = load i32, i32* %l.addr, align 4\n  %cmp6 = icmp slt i32 %5, %6\n  br i1 %cmp6, label %while.body, label %while.end\n\nwhile.body:\n"+
        "  %7 = load i8*, i8** %s.addr, align 8\n  %8 = load i32, i32* %j, align 4\n  %9 = load i32, i32* %i.addr, align 4\n  %add = add nsw i32 %8, %9\n  %idxprom = sext i32 %add to i64\n"+
        "  %arrayidx = getelementptr inbounds i8, i8* %7, i64 %idxprom\n  %10 = load i8, i8* %arrayidx, align 1\n  %11 = load i8*, i8** %res, align 8\n  %12 = load i32, i32* %j, align 4\n"+
        "  %idxprom8 = sext i32 %12 to i64\n  %arrayidx9 = getelementptr inbounds i8, i8* %11, i64 %idxprom8\n  store i8 %10, i8* %arrayidx9, align 1\n  %13 = load i32, i32* %j, align 4\n"+
        "  %inc = add nsw i32 %13, 1\n  store i32 %inc, i32* %j, align 4\n  br label %while.cond\n\nwhile.end:\n  %14 = load i8*, i8** %res, align 8\n  ret i8* %14\n}\n\n";
    }

    //Add Base Functions
    private void DecBaseFns()
    {
        globOut += "; Global Constants\n";
        globOut += "@dStr = private constant [2 x i8] c\"%d\"\n";
        globOut += "@sStr = private constant [2 x i8] c\"%s\"\n";
        globOut += "@nullStr = private unnamed_addr constant [1 x i8] zeroinitializer\n";

        Codegen.progOut += "; C-functions\n";
        Codegen.progOut += "declare void @exit(i32)\n";
        Codegen.progOut += "declare i32 @printf(i8* , ...)\n";
        Codegen.progOut += "declare i32 @scanf(i8* , ...)\n";
        Codegen.progOut += "declare i32 @strlen(i8*)\n";
        Codegen.progOut += "declare i8* @strcat(i8*, i8*)\n";
        Codegen.progOut += "declare i8* @strcpy(i8*, i8*)\n\n";

        Codegen.progOut += "; Basic Cool Functions\n";
        baseFns.add("abort");
        Codegen.progOut += "define void @abort(i32 %a1) {\n";
        Codegen.progOut += indent + "call void @exit(i32 %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n\n";

        baseFns.add("out_string");
        Codegen.progOut += "define void @out_string(i8* %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @sStr, i32 0, i32 0),i8* %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n\n";

        baseFns.add("in_string");
        Codegen.progOut += "define void @in_string(i8* %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @sStr, i32 0, i32 0),i8* %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n\n";

        baseFns.add("out_int");
        Codegen.progOut += "define void @out_int(i32 %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @dStr, i32 0, i32 0),i32 %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n\n";

        baseFns.add("in_int");
        Codegen.progOut += "define i32 @in_int() {\n";
        Codegen.progOut += indent + "%v1 = alloca i32\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @sStr, i32 0, i32 0),i32* %v1)\n";
        Codegen.progOut += indent + "%v2 = load i32, i32* %v1\n";
        Codegen.progOut += indent + "ret i32 %v2\n}\n\n\n";

        baseFns.add("length");
        Codegen.progOut += "define i32 @length(i8* %a1) {\n";
        Codegen.progOut += indent + "%v1 = call i32 @strlen(i8* %a1)\n";
        Codegen.progOut += indent + "ret i32 %v1\n}\n\n";

        baseFns.add("concat");
        Codegen.progOut += "define i8* @concat(i8* %a1, i8* %a2){\n";
        Codegen.progOut += indent + "call i8* @strcat(i8* %a1, i8* %a2)\n";
        Codegen.progOut += indent + "ret i8* %a1\n}\n\n";

        baseFns.add("substr");
        emitSubstr();
      
        Codegen.progOut += "\n";
    }

    //Class Initializers
    private void AddConstructors(AST.program program)
    {
        Codegen.progOut += "; Class Initializtion Methods\n";
		for(AST.class_ cl: program.classes)
        {
            String clTyp = "%struct." + cl.name;
            Codegen.progOut += "define void @init_" + cl.name + "(" + clTyp + "* %a1) {\n";
            Codegen.progOut += indent + "%v0 = getelementptr " + clTyp + ", " + clTyp + "* %a1, i32 0, i32 0\n";
            Codegen.progOut += indent + "store i8 1, i8* %v0\n";

            Integer idx = 1;
            ArrayList<AST.attr> attrs = classAttrs.get(cl.name);
            for(; idx<=attrs.size(); idx++)
            {
                String atTyp = attrs.get(idx-1).typeid;
                if(atTyp.equals("SELF_TYPE"))
                    continue;

                Codegen.progOut += indent + "%v" + idx + " = getelementptr " + clTyp + ", " + clTyp + "* %a1, i32 0, i32 " + idx + "\n";
                switch(atTyp)
                {
                    case "Bool" :
                        Codegen.progOut += indent + "store i8 0, i8* %v" + idx + "\n";
                        break;
                    case "Int" :
                        Codegen.progOut += indent + "store i32 0, i32* %v" + idx + "\n";
                        break;
                    case "String" :
                        Codegen.progOut += indent + "%str" + idx + " = getelementptr inbounds ([1 x i8], [1 x i8]* @nullStr, i32 0, i32 0)\n";
                        Codegen.progOut += indent + "store i8* %str" + idx + ", i8** %v" + idx + "\n";
                        break;
                    default :
                        Codegen.progOut += indent + "%set" + idx + " = getelementptr %struct." + atTyp + ", %struct." + atTyp + "* %v" + idx + ", i32 0, i32 0\n";
                        Codegen.progOut += indent + "store i8 0, i8* %set" + idx + "\n";
                }
            }

            Codegen.progOut += indent + "ret void\n}\n\n";
        }
    }

    //Class Visitor
	public void Visit(AST.class_ cl)
    {
        //Iterate over Class Methods
		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
        {
            if(entry.getKey().equals("type_name"))
            {
                Codegen.progOut += "define i8* @"+Semantic.inheritance.GetMangledName(className,entry.getValue())+"() {\n";
                Codegen.progOut += indent + cl.name + "\n}\n";
            }
            else if(baseFns.contains(entry.getKey()) == false)
            {
                Codegen.progOut += "define "+clNames.get(entry.getValue().typeid)+" @"+Semantic.inheritance.GetMangledName(className,entry.getValue())+"(";
                Visit(entry.getValue());
            }
        }
    }

    //Method Visitor
    public void Visit(AST.method md)
    {
        //Insert Class Attibutes into Scope Table
        ScopeTable<String> varNames = new ScopeTable<String>();
        Integer idx = 1;
		for(AST.attr at: classAttrs.get(className))
            varNames.insert(at.name,Integer.toString(idx++));

        if(!className.equals("Main") || !md.name.equals("main"))
        {
            idx = 2;
            Codegen.progOut += "%struct." + className + "* %a1, ";
            varNames.insert("self","%a1");
            for(AST.formal fl: md.formals)
            {
                String varId = "%a"+Integer.toString(idx++);
                varNames.insert(fl.name,varId);
                Codegen.progOut += clNames.get(fl.typeid)+" "+varId+", ";
            }
            Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-2);
        }
        Codegen.progOut += ") {\nentry:\n";

        varCnt = 0;
        labCnt = 0;
        if(md.name.equals("main"))
        {
            Codegen.progOut += indent + "%a1 = alloca %struct.Main\n";
            varNames.insert("self","%a1");
            Codegen.progOut += indent + "call void @init_Main(%struct.Main* %a1)\n";
        }

        Visit(md.body,varNames);

        Codegen.progOut += indent + "ret " + clNames.get(md.typeid) + " " + md.body.type + "\n";
        Codegen.progOut += "}\n\n";
    }

    //Expression Visitor
    public void Visit(AST.expression expr, ScopeTable<String> varNames)
    {
        //Bool
        if(expr instanceof AST.bool_const)
        {
            AST.bool_const b = (AST.bool_const)expr;
            int value = 0;
            if(b.value)
                value = 1;
            b.type = Integer.toString(value);
        }

        //Int
        else if(expr instanceof AST.int_const)
        {
            AST.int_const ic = (AST.int_const)expr;
            ic.type = Integer.toString(ic.value);
        }

        //String
        else if(expr instanceof AST.string_const)
        {
            AST.string_const str = (AST.string_const)expr;

            String escStr = "";
            //Escape Characters in 'str'
            for(char c : str.value.toCharArray())
            {
                switch(c)
                {
                    case '\\' :
                        escStr += "\\5C";
                        break;
                    case '\"' :
                        escStr += "\\22";
                        break;
                    case '\n' :
                        escStr += "\\0A";
                        break;
                    case '\t' :
                        escStr += "\\09";
                        break;
                    case '\b' :
                        escStr += "\\08";
                        break;
                    case '\f' :
                        escStr += "\\0C";
                        break;
                    default :
                        escStr += c;
                }
            }

            String arStr = "[" + str.value.length() + " x i8]";
            globOut += "@g" + ++globCnt + " = private unnamed_addr constant " + arStr + " c\"" + escStr + "\"\n"; 
            Codegen.progOut += indent + "%v" + ++varCnt + " = getelementptr inbounds " + arStr + ", " + arStr + "* @g" + globCnt + ", i32 0, i32 0\n";
            str.type = "%v" + varCnt;
        }

        //Object
        else if(expr instanceof AST.object)
        {
            AST.object obj = (AST.object)expr;
            obj.type = varNames.lookUpGlobal(obj.name);
        }

        //Assign
        else if(expr instanceof AST.assign)
        {
            AST.assign asgn = (AST.assign)expr;
            Visit(asgn.e1,varNames);
            String vname = varNames.lookUpGlobal(asgn.name);

            //If Assignment expression is a Class Attribute
            if(isFstDgt(vname) == true)
            {
                String clTyp = "%struct." + className;
                varCnt++;
                Codegen.progOut += indent + "%v" + varCnt + " = getelementptr " + clTyp + ", " + clTyp + "* %a1, i32 0, i32 " + vname + "\n";
                Codegen.progOut += indent + "store " + clNames.get(asgn.type) + " " + asgn.e1.type + ", " + clNames.get(asgn.type) + "* %v" + varCnt + "\n";
            }
            else
                Codegen.progOut += indent + "store " + clNames.get(asgn.type) + " " + asgn.e1.type + ", " + clNames.get(asgn.type) + "* " + vname + "\n";
        }

        //New
        else if(expr instanceof AST.new_)
        {
            AST.new_ nw = (AST.new_)expr;
            varCnt++;
            String vname1 = "%v" + Integer.toString(varCnt);
            varCnt++;
            String vname2 = "%v" + Integer.toString(varCnt);
            
            Codegen.progOut += indent + vname1 + " = alloca %struct." + nw.typeid + "\n";
            Codegen.progOut += indent + "call void @init_" + nw.typeid + "(%struct." + nw.typeid + "* " + vname1 + ")\n";
            
            Codegen.progOut += indent + vname2 + " = load %struct." + nw.typeid + ", %struct." + nw.typeid + "* " + vname1 + "\n";
            nw.type = vname2; 

        }

        else if(expr instanceof AST.isvoid)
        {
            AST.isvoid iv = (AST.isvoid)expr;
            Visit(iv.e1, varNames);
            String type = iv.e1.type;
            
            System.out.println(type);
            /*
            if(iv.e1.type.equals("Int") || iv.e1.type.equals("Bool") || iv.e1.type.equals("String"))
            {
                iv.type = "0";
            }
            else
            {
                Visit(iv.e1, varNames);
                varCnt++;
                String vname = "%v" + Integer.toString(varCnt);    
                Codegen.progOut += indent + vname + " = getelementptr %struct." + type + ", %struct." + type + "* " + iv.e1.type + ", i32 0 i32 0\n";

                varCnt++;
                String vname2 = "%v" + Integer.toString(varCnt);
                Codegen.progOut += indent + vname2 + " = load i8, i8* " + vname + "\n";

                iv.type = vname2;
            }*/


        }

        //Add
        else if(expr instanceof AST.plus)
        {
            AST.plus pl = (AST.plus)expr;
            Visit(pl.e1,varNames);
            Visit(pl.e2,varNames);

            if(isFstDgt(pl.e1.type) && isFstDgt(pl.e2.type))
            {
                pl.type = Integer.toString(Integer.valueOf(pl.e1.type) + Integer.valueOf(pl.e2.type));
                return;
            }
            
            varCnt++;
            String vname = "%v" + Integer.toString(varCnt);
            Codegen.progOut += indent + vname + " = add " +  clNames.get(expr.type);
            Codegen.progOut += " " + pl.e1.type + ", " + pl.e2.type + "\n";
            pl.type = vname;
        }

        //Subtract
        else if(expr instanceof AST.sub)
        {
            AST.sub s = (AST.sub)expr;
            Visit(s.e1,varNames);
            Visit(s.e2,varNames);

            if(isFstDgt(s.e1.type) && isFstDgt(s.e2.type))
            {
                s.type = Integer.toString(Integer.valueOf(s.e1.type) - Integer.valueOf(s.e2.type));
                return;
            }
            
            varCnt++;
            s.type = "%v" + Integer.toString(varCnt);
            Codegen.progOut += indent + s.type + " = sub " +  clNames.get(expr.type);
            Codegen.progOut += " " + s.e1.type + ", " + s.e2.type + "\n";
        }

        //Multiply
        else if(expr instanceof AST.mul)
        {
            AST.mul m = (AST.mul)expr;
            Visit(m.e1,varNames);
            Visit(m.e2,varNames);

            if(isFstDgt(m.e1.type) && isFstDgt(m.e2.type))
            {
                m.type = Integer.toString(Integer.valueOf(m.e1.type) * Integer.valueOf(m.e2.type));
                return;
            }
            
            varCnt++;
            m.type = "%v" + Integer.toString(varCnt);
            Codegen.progOut += indent + m.type + " = mul " +  clNames.get(expr.type);
            Codegen.progOut += " " + m.e1.type + ", " + m.e2.type + "\n";
        }

        //Divide
        else if(expr instanceof AST.divide)
        {
            AST.divide div = (AST.divide)expr;
            Visit(div.e1,varNames);
            Visit(div.e2,varNames);
            
            //handling division by zero
            String vname = "%v" + Integer.toString(varCnt);
            varCnt++;
            labCnt++;
            String abortLabel = "abort" + Integer.toString(labCnt);
            String contLabel = "continue" + Integer.toString(labCnt);

            Codegen.progOut += indent + vname + " = icmp eq i32 " + div.e2.type + ", 0\n";
            Codegen.progOut += indent + "br i1 " + vname + ", label %" + abortLabel + ", label %" + contLabel + "\n";
            Codegen.progOut += abortLabel + ":\n";
            Codegen.progOut += indent + "call void @abort()\n";
            Codegen.progOut += contLabel + ":\n";


            if(isFstDgt(div.e1.type) && isFstDgt(div.e2.type) && Integer.valueOf(div.e2.type) != 0)
            {
                div.type = Integer.toString(Integer.valueOf(div.e1.type) / Integer.valueOf(div.e2.type));
                return;
            }
            
            varCnt++;
            div.type = "%v" + Integer.toString(varCnt);
            Codegen.progOut += indent + div.type + " = sdiv " +  expr.type;
            Codegen.progOut += " " + div.e1.type + ", " + div.e2.type + "\n";
        }

        //Block
        else if(expr instanceof AST.block)
        {
            AST.block bk = (AST.block)expr;

            int idx = 0;
            for(; idx<bk.l1.size()-1; idx++)
                Visit(bk.l1.get(idx),varNames);
            Visit(bk.l1.get(idx),varNames);

            bk.type = bk.l1.get(idx).type;
        }

        //Less Than
        else if(expr instanceof AST.lt)
        {
            AST.lt l = (AST.lt)expr;
            varCnt++;
            String vname = "%v" + Integer.toString(varCnt);
            Visit(l.e1,varNames);
            Visit(l.e2,varNames);

            if(isBool(l.e1.type) >= 0 && isBool(l.e2.type) >= 0)
            {
                Integer value = 0;
                if(Integer.valueOf(l.e1.type) < Integer.valueOf(l.e2.type))
                    value = 1;
                l.type = Integer.toString(value);
            }
            else
            {
                Codegen.progOut += indent + vname + " = icmp " + "slt " + "i32 " + l.e1.type +","+ l.e2.type + "\n";
                l.type = vname;
            }
        }

        //Less Than or Equal To
        else if(expr instanceof AST.leq)
        {
            AST.leq l = (AST.leq)expr;
            varCnt++;
            String vname = "%v" + Integer.toString(varCnt);
            Visit(l.e1,varNames);
            Visit(l.e2,varNames);
            if(isBool(l.e1.type) >= 0 && isBool(l.e2.type) >= 0)
            {
                Integer value = 0;
                if(Integer.valueOf(l.e1.type) <= Integer.valueOf(l.e2.type))
                    value = 1;
                l.type = Integer.toString(value);
            }
            else
            {
                Codegen.progOut += indent + vname + " = icmp " + "sle " + "i32 " + l.e1.type +","+ l.e2.type + "\n";
                l.type = vname;
            }
        }

        //Equal To
        else if(expr instanceof AST.eq)
        {
            AST.eq l = (AST.eq)expr;
            varCnt++;
            String vname = "%v" + Integer.toString(varCnt);
            Visit(l.e1,varNames);
            Visit(l.e2,varNames);
            if(isBool(l.e1.type) >= 0 && isBool(l.e2.type) >= 0)
            {
                Integer value = 0;
                if(Integer.valueOf(l.e1.type) == Integer.valueOf(l.e2.type))
                    value = 1;
                l.type = Integer.toString(value);
            }
            else
            {
                Codegen.progOut += indent + vname + " = icmp " + "eq " + "i32 " + l.e1.type +","+ l.e2.type + "\n";
                l.type = vname;
            }
        }

        //If Else
        else if(expr instanceof AST.cond)
        {
            AST.cond cd = (AST.cond)expr;
            Visit(cd.predicate, varNames);

            //if predicate is always false, execute else body
            if(isBool(cd.predicate.type) == 0)
            {
                Visit(cd.elsebody, varNames);
                cd.type = cd.elsebody.type;
            }
            //if predicate is always ture, execute if body
            else if(isBool(cd.predicate.type) == 1)
            {
                Visit(cd.ifbody, varNames);
                cd.type = cd.ifbody.type;
            }
            else
            {
                labCnt++;
                String ifLabel = "if.then" + Integer.toString(labCnt);
                String elseLabel = "if.else" + Integer.toString(labCnt);
                String endLabel = "if.end" + Integer.toString(labCnt);

                Codegen.progOut += indent + "br i1 " + cd.predicate.type + ", " + "label %" + ifLabel + ", " + "label %" + elseLabel + "\n\n"; 

                Codegen.progOut += ifLabel + ":\n";
                Visit(cd.ifbody, varNames);
                Codegen.progOut += indent + "br label %" + endLabel + "\n\n";
                
                Codegen.progOut += elseLabel + ":\n";
                Visit(cd.elsebody, varNames);
                Codegen.progOut += indent + "br label %" + endLabel + "\n\n";

                Codegen.progOut += endLabel + ":\n";
                varCnt++;
                String vname = "%v" + Integer.toString(varCnt);
                Codegen.progOut += indent + vname + " = phi " + clNames.get(cd.type);
                Codegen.progOut += " [ " + cd.ifbody.type + ", %" + ifLabel + " ], [ " + cd.elsebody.type + ", %" + elseLabel + " ]\n";
                cd.type = vname;
            }
        }

        //While Loop
        else if(expr instanceof AST.loop)
        {
            AST.loop lp = (AST.loop)expr;
            Visit(lp.predicate, varNames);

            //if predicate is always false
            if(isBool(lp.predicate.type) == 0)
            {
                //do nothing
            }
            //if predicate is always true
            else if(isBool(lp.predicate.type) == 1)
            {
                //infinite loop
                labCnt++;
                String body = "while.body" + Integer.toString(labCnt);;
                String end = "return" + Integer.toString(labCnt);

                Codegen.progOut += indent + "br label %" + body + "\n\n";
                Codegen.progOut += body + ":\n";
                Visit(lp.body, varNames);
                Codegen.progOut += indent + "br label %" + body + "\n\n";
                Codegen.progOut += end + ":\n";
            }
            else
            {
                labCnt++;
                String cond = "while.cond" + Integer.toString(labCnt);
                String body = "while.body" + Integer.toString(labCnt);
                String end = "while.end" + Integer.toString(labCnt);

                Codegen.progOut += indent + "br label %" + cond + "\n\n";
                Codegen.progOut += cond + ":\n";
                Visit(lp.predicate, varNames);
                Codegen.progOut += indent + "br i1 " + lp.predicate.type + ", label %" + body + ", label %" + end + "\n\n";
                
                Codegen.progOut += body + ":\n";
                Visit(lp.body, varNames);
                Codegen.progOut += indent + "br label %" + cond + "\n\n";

                Codegen.progOut += end + ":\n";
            }
        }
    } 
}
