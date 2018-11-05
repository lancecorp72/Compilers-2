package cool;

import java.util.*;

public class PrintNode
{
    private HashMap<String,String> clNames;
    private ArrayList<String> baseFns;
    private String className;
    private String indent;
    private Integer varCnt;
    private Integer labCnt;

	//Constructor
	public PrintNode()
	{
        className = "";
        indent = "  ";
        varCnt = 0;
        labCnt = 1;
        clNames = new HashMap<String,String>();
        baseFns = new ArrayList<String>();
	}

    private Boolean isFstDgt(String s)
    {
        if(s.charAt(0)>='0' && s.charAt(0)<='9')
            return true;
        else if(s.charAt(0)=='-' && s.charAt(1)>='0' && s.charAt(1)<='9')
            return true;
        return false;
    }

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

        decBaseFns();

		for(AST.class_ cl: program.classes)
        {
            if(!cl.name.equals("Main"))
            {
                Codegen.progOut += "%struct."+cl.name+" = type {";
                clNames.put(cl.name,"%struct."+cl.name);

                for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs(cl.name).entrySet())
                {
                    if(entry.getValue().name.equals("self"))
                        continue;
                    Codegen.progOut += " "+clNames.get(entry.getValue().typeid)+",";
                }
                Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-1);
                Codegen.progOut += " }\n";
            }
        }
        Codegen.progOut += "\n";

		for(AST.class_ cl: program.classes)
        {
            if(!cl.name.equals("Main"))
            {
                className = cl.name;
                Visit(cl);
            }
        }
        Codegen.progOut += "\n";

        ScopeTable<String> varNames = new ScopeTable<String>();
        for(Map.Entry<String,AST.attr> entry: Semantic.inheritance.GetClassAttrs("Main").entrySet())
        {
            if(entry.getValue().name.equals("self"))
                continue;

            varNames.insert(entry.getValue().name,"@"+entry.getValue().name);
            Codegen.progOut += "@"+entry.getValue().name+" = global "+clNames.get(entry.getValue().typeid)+" 0\n";
        }
        Codegen.progOut += "\n";

        HashMap<String,AST.method> mainClassMethods = Semantic.inheritance.GetClassMethods("Main");
		for(Map.Entry<String,AST.method> entry: mainClassMethods.entrySet())
        {
            if(entry.getValue().name.equals("main"))
                continue;
            if(baseFns.contains(entry.getKey()) == false)
                Visit(entry.getValue());
        }

        AST.method md = mainClassMethods.get("main");
        Codegen.progOut += "define "+clNames.get(md.typeid)+" @main () {\n";
        varCnt = 0;
        Visit(md.body,varNames);

        Codegen.progOut += indent + "ret " + clNames.get(md.typeid) + " " + md.body.type + "\n";
        Codegen.progOut += "}";
    }

    private void decBaseFns()
    {
        Codegen.progOut += "@fStr = private constant [2 x i8] c\"%d\"\n";
        Codegen.progOut += "declare i32 @printf(i8* , ...)\n";
        Codegen.progOut += "declare i32 @scanf(i8* , ...)\n\n";

        baseFns.add("out_string");
        Codegen.progOut += "define void @out_string(i8* %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @printf(i8* %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n";

        baseFns.add("in_string");
        Codegen.progOut += "define void @in_string(i8* %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @scanf(i8* %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n";

        baseFns.add("out_int");
        Codegen.progOut += "define void @out_int(i32 %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @fStr, i32 0, i32 0),i32 %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n";

        baseFns.add("in_int");
        Codegen.progOut += "define void @in_int(i32 %a1) {\n";
        Codegen.progOut += indent + "call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @fStr, i32 0, i32 0),i32 %a1)\n";
        Codegen.progOut += indent + "ret void\n}\n\n";
    }

	public void Visit(AST.class_ cl)
    {
		for(Map.Entry<String,AST.method> entry: Semantic.inheritance.GetClassMethods(cl.name).entrySet())
        {
            if(baseFns.contains(entry.getKey()) == false)
                Visit(entry.getValue());
        }
    }

    public void Visit(AST.method md)
    {
        Codegen.progOut += "define "+clNames.get(md.typeid)+" @"+Semantic.inheritance.GetMangledName(className,md)+" (";
        Integer idx = 1;

        ScopeTable<String> varNames = new ScopeTable<String>();
		for(AST.formal fl: md.formals)
        {
            String varId = "%a"+Integer.toString(idx++);
            varNames.insert(fl.name,varId);
            Codegen.progOut += clNames.get(fl.typeid)+" "+varId+", ";
        }
        if(!md.formals.isEmpty())
            Codegen.progOut = Codegen.progOut.substring(0,Codegen.progOut.length()-2);

        Codegen.progOut += ") {\n";

        varCnt = 0;
        Visit(md.body,varNames);

        Codegen.progOut += indent + "ret " + clNames.get(md.typeid) + " " + md.body.type + "\n";
        Codegen.progOut += "}\n";
    }

    public void Visit(AST.expression expr, ScopeTable<String> varNames)
    {
        if(expr instanceof AST.bool_const)
        {
            AST.bool_const b = (AST.bool_const)expr;
            int value = 0;
            if(b.value)
                value = 1;
            b.type = Integer.toString(value);
        }
        if(expr instanceof AST.int_const)
        {
            AST.int_const ic = (AST.int_const)expr;
            ic.type = Integer.toString(ic.value);
        }

        else if(expr instanceof AST.object)
        {
            AST.object obj = (AST.object)expr;
            obj.type = varNames.lookUpGlobal(obj.name);
        }

        else if(expr instanceof AST.assign)
        {
            AST.assign asgn = (AST.assign)expr;
            Visit(asgn.e1,varNames);
            String vname = varNames.lookUpGlobal(asgn.name);

            Codegen.progOut += indent + "store " + clNames.get(asgn.type) + " " + asgn.e1.type + ", " + clNames.get(asgn.type) + "* " + vname + "\n";
        }

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

        else if(expr instanceof AST.divide)
        {
            AST.divide div = (AST.divide)expr;
            Visit(div.e1,varNames);
            Visit(div.e2,varNames);

            /*if(isFstDgt(div.e1.type) && isFstDgt(div.e2.type))
            {
                div.type = Integer.toString(Integer.valueOf(div.e1.type) / Integer.valueOf(div.e2.type));
                return;
            }*/
            
            varCnt++;
            div.type = "%v" + Integer.toString(varCnt);
            Codegen.progOut += indent + div.type + " = div " +  expr.type;
            Codegen.progOut += " " + div.e1.type + ", " + div.e2.type + "\n";
        }

        else if(expr instanceof AST.block)
        {
            AST.block bk = (AST.block)expr;

            int idx = 0;
            for(; idx<bk.l1.size()-1; idx++)
                Visit(bk.l1.get(idx),varNames);
            Visit(bk.l1.get(idx),varNames);

            bk.type = bk.l1.get(idx).type;
        }

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

        else if(expr instanceof AST.cond)
        {
            AST.cond cd = (AST.cond)expr;
            Visit(cd.predicate, varNames);

            if(isBool(cd.predicate.type) == 0)
            {
                // execute else body
                Visit(cd.elsebody, varNames);
            }
            else if(isBool(cd.predicate.type) == 1)
            {
                // execute if body
                Visit(cd.ifbody, varNames);
            }
            else
            {
                String ifLabel = "if.then" + Integer.toString(labCnt);
                String elseLabel = "if.else" + Integer.toString(labCnt);
                String endLabel = "if.end" + Integer.toString(labCnt);
                labCnt++;

                Codegen.progOut += indent + "br i1 " + cd.predicate.type + ", " + "label %" + ifLabel + ", " + "label %" + elseLabel + "\n\n"; 

                Codegen.progOut += ifLabel + ":\n";
                Visit(cd.ifbody, varNames);
                Codegen.progOut += indent + "br label %" + endLabel + "\n\n";
                
                Codegen.progOut += elseLabel + ":\n";
                Visit(cd.elsebody, varNames);
                Codegen.progOut += indent + "br label %" + endLabel + "\n\n";

                Codegen.progOut += endLabel + ":\n";
            }
            // add expr.type
        }
        else if(expr instanceof AST.loop)
        {
            AST.loop lp = (AST.loop)expr;
            Visit(lp.predicate, varNames);

            if(isBool(lp.predicate.type) == 0)
            {
                //Do nothing
            }
            else if(isBool(lp.predicate.type) == 1)
            {
                //infinite loop
                String body = "while.body" + Integer.toString(labCnt);;
                String end = "return" + Integer.toString(labCnt);
                labCnt++;

                Codegen.progOut += indent + "br label %" + body + "\n\n";
                Codegen.progOut += body + ":\n";
                Visit(lp.body, varNames);
                Codegen.progOut += indent + "br label %" + body + "\n\n";
                Codegen.progOut += end + ":\n";
            }
            else
            {

                String cond = "while.cond" + Integer.toString(labCnt);
                String body = "while.body" + Integer.toString(labCnt);
                String end = "while.end" + Integer.toString(labCnt);
                labCnt++;

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
