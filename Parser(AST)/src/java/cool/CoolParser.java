// Generated from CoolParser.g4 by ANTLR 4.5
package cool;

	import cool.AST;
	import java.util.List;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, TYPEID=2, OBJECTID=3, BOOL_CONST=4, INT_CONST=5, STR_CONST=6, 
		LPAREN=7, RPAREN=8, COLON=9, ATSYM=10, SEMICOLON=11, COMMA=12, PLUS=13, 
		MINUS=14, STAR=15, SLASH=16, TILDE=17, LT=18, EQUALS=19, LBRACE=20, RBRACE=21, 
		DOT=22, DARROW=23, LE=24, ASSIGN=25, CLASS=26, ELSE=27, FI=28, IF=29, 
		IN=30, INHERITS=31, LET=32, LOOP=33, POOL=34, THEN=35, WHILE=36, CASE=37, 
		ESAC=38, OF=39, NEW=40, ISVOID=41, NOT=42, WS=43, THEEND=44, SINGLE_COMMENT=45, 
		COMMENT_CLOSE=46, CLOSED=47, COM_EOF=48, NEWLINE=49, ESC=50, ESC_NULL=51, 
		STR_EOF=52, ERR1=53, ERR2=54, ERR3=55, LQUOTE=56, NL=57, TAB=58, BACKSPAC=59, 
		LINEFEED=60, SLASHN=61, ESC_NL=62;
	public static final int
		RULE_program = 0, RULE_class_list = 1, RULE_class_expr = 2, RULE_feature_list = 3, 
		RULE_feature = 4, RULE_attr = 5, RULE_formal_list = 6, RULE_formal = 7, 
		RULE_expr_list = 8, RULE_block_expr_list = 9, RULE_let_expr_list = 10, 
		RULE_case_expr_list = 11, RULE_branch = 12, RULE_expression = 13;
	public static final String[] ruleNames = {
		"program", "class_list", "class_expr", "feature_list", "feature", "attr", 
		"formal_list", "formal", "expr_list", "block_expr_list", "let_expr_list", 
		"case_expr_list", "branch", "expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'('", "')'", "':'", "'@'", 
		"';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", 
		"'}'", "'.'", "'=>'", "'<='", "'<-'", null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "'*)'", null, null, null, null, null, null, null, null, null, 
		null, null, "'\\t'", "'\\b'", "'\\f'", "'\\n'", "'\\\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", 
		"LPAREN", "RPAREN", "COLON", "ATSYM", "SEMICOLON", "COMMA", "PLUS", "MINUS", 
		"STAR", "SLASH", "TILDE", "LT", "EQUALS", "LBRACE", "RBRACE", "DOT", "DARROW", 
		"LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", 
		"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", 
		"NOT", "WS", "THEEND", "SINGLE_COMMENT", "COMMENT_CLOSE", "CLOSED", "COM_EOF", 
		"NEWLINE", "ESC", "ESC_NULL", "STR_EOF", "ERR1", "ERR2", "ERR3", "LQUOTE", 
		"NL", "TAB", "BACKSPAC", "LINEFEED", "SLASHN", "ESC_NL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		String filename;
		public void setFilename(String f){
			filename = f;
		}

	/*
		DO NOT EDIT THE FILE ABOVE THIS LINE
		Add member functions, variables below.
	*/


	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public AST.program value;
		public Class_listContext cl;
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public Class_listContext class_list() {
			return getRuleContext(Class_listContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			((ProgramContext)_localctx).cl = class_list();
			setState(29);
			match(EOF);

							((ProgramContext)_localctx).value =  new AST.program(((ProgramContext)_localctx).cl.value, ((ProgramContext)_localctx).cl.value.get(0).lineNo);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_listContext extends ParserRuleContext {
		public ArrayList<AST.class_> value;
		public Class_exprContext class_expr;
		public List<Class_exprContext> class_expr() {
			return getRuleContexts(Class_exprContext.class);
		}
		public Class_exprContext class_expr(int i) {
			return getRuleContext(Class_exprContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public Class_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClass_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_listContext class_list() throws RecognitionException {
		Class_listContext _localctx = new Class_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_class_list);

				((Class_listContext)_localctx).value =  new ArrayList<AST.class_>();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				((Class_listContext)_localctx).class_expr = class_expr();
				setState(33);
				match(SEMICOLON);
				_localctx.value.add(((Class_listContext)_localctx).class_expr.value);
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_exprContext extends ParserRuleContext {
		public AST.class_ value;
		public Token CLASS;
		public Token clType;
		public Token inType;
		public Feature_listContext feature_list;
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public Feature_listContext feature_list() {
			return getRuleContext(Feature_listContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> TYPEID() { return getTokens(CoolParser.TYPEID); }
		public TerminalNode TYPEID(int i) {
			return getToken(CoolParser.TYPEID, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public Class_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClass_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_exprContext class_expr() throws RecognitionException {
		Class_exprContext _localctx = new Class_exprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_class_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			((Class_exprContext)_localctx).CLASS = match(CLASS);
			setState(41);
			((Class_exprContext)_localctx).clType = match(TYPEID);
			setState(44);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(42);
				match(INHERITS);
				setState(43);
				((Class_exprContext)_localctx).inType = match(TYPEID);
				}
			}

			setState(46);
			match(LBRACE);
			setState(47);
			((Class_exprContext)_localctx).feature_list = feature_list();
			setState(48);
			match(RBRACE);

					if(((Class_exprContext)_localctx).inType==null)	{
						((Class_exprContext)_localctx).value =  new AST.class_(((Class_exprContext)_localctx).clType.getText(), filename, "Object", ((Class_exprContext)_localctx).feature_list.value, ((Class_exprContext)_localctx).CLASS.getLine());
					}
					else	{
						((Class_exprContext)_localctx).value =  new AST.class_(((Class_exprContext)_localctx).clType.getText(), filename, ((Class_exprContext)_localctx).inType.getText(), ((Class_exprContext)_localctx).feature_list.value, ((Class_exprContext)_localctx).CLASS.getLine());
					}
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Feature_listContext extends ParserRuleContext {
		public ArrayList<AST.feature> value;
		public FeatureContext feature;
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public Feature_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFeature_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Feature_listContext feature_list() throws RecognitionException {
		Feature_listContext _localctx = new Feature_listContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_feature_list);

				((Feature_listContext)_localctx).value =  new ArrayList<AST.feature>();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBJECTID) {
				{
				{
				setState(51);
				((Feature_listContext)_localctx).feature = feature();
				setState(52);
				match(SEMICOLON);
				_localctx.value.add(((Feature_listContext)_localctx).feature.value);
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FeatureContext extends ParserRuleContext {
		public AST.feature value;
		public AttrContext attr;
		public Token OBJECTID;
		public Token TYPEID;
		public ExpressionContext expression;
		public Formal_listContext formal_list;
		public AttrContext attr() {
			return getRuleContext(AttrContext.class,0);
		}
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public Formal_listContext formal_list() {
			return getRuleContext(Formal_listContext.class,0);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFeature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_feature);
		try {
			setState(84);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				((FeatureContext)_localctx).attr = attr();

						((FeatureContext)_localctx).value =  ((FeatureContext)_localctx).attr.value;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				((FeatureContext)_localctx).OBJECTID = match(OBJECTID);
				setState(64);
				match(LPAREN);
				setState(65);
				match(RPAREN);
				setState(66);
				match(COLON);
				setState(67);
				((FeatureContext)_localctx).TYPEID = match(TYPEID);
				setState(68);
				match(LBRACE);
				setState(69);
				((FeatureContext)_localctx).expression = expression(0);
				setState(70);
				match(RBRACE);

						((FeatureContext)_localctx).value =  new AST.method(((FeatureContext)_localctx).OBJECTID.getText(),new ArrayList<AST.formal>(),((FeatureContext)_localctx).TYPEID.getText(),((FeatureContext)_localctx).expression.value,((FeatureContext)_localctx).OBJECTID.getLine());
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				((FeatureContext)_localctx).OBJECTID = match(OBJECTID);
				setState(74);
				match(LPAREN);
				setState(75);
				((FeatureContext)_localctx).formal_list = formal_list();
				setState(76);
				match(RPAREN);
				setState(77);
				match(COLON);
				setState(78);
				((FeatureContext)_localctx).TYPEID = match(TYPEID);
				setState(79);
				match(LBRACE);
				setState(80);
				((FeatureContext)_localctx).expression = expression(0);
				setState(81);
				match(RBRACE);

						((FeatureContext)_localctx).value =  new AST.method(((FeatureContext)_localctx).OBJECTID.getText(),((FeatureContext)_localctx).formal_list.value,((FeatureContext)_localctx).TYPEID.getText(),((FeatureContext)_localctx).expression.value,((FeatureContext)_localctx).OBJECTID.getLine());
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrContext extends ParserRuleContext {
		public AST.attr value;
		public Token OBJECTID;
		public Token TYPEID;
		public Token ASSIGN;
		public ExpressionContext exp;
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttrContext attr() throws RecognitionException {
		AttrContext _localctx = new AttrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((AttrContext)_localctx).OBJECTID = match(OBJECTID);
			setState(87);
			match(COLON);
			setState(88);
			((AttrContext)_localctx).TYPEID = match(TYPEID);
			setState(91);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(89);
				((AttrContext)_localctx).ASSIGN = match(ASSIGN);
				setState(90);
				((AttrContext)_localctx).exp = expression(0);
				}
			}


						if(((AttrContext)_localctx).ASSIGN==null)
							((AttrContext)_localctx).value =  new AST.attr(((AttrContext)_localctx).OBJECTID.getText(),((AttrContext)_localctx).TYPEID.getText(),new AST.no_expr(((AttrContext)_localctx).OBJECTID.getLine()),((AttrContext)_localctx).OBJECTID.getLine());
						else
							((AttrContext)_localctx).value =  new AST.attr(((AttrContext)_localctx).OBJECTID.getText(),((AttrContext)_localctx).TYPEID.getText(),((AttrContext)_localctx).exp.value,((AttrContext)_localctx).OBJECTID.getLine());
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_listContext extends ParserRuleContext {
		public ArrayList<AST.formal> value;
		public FormalContext f1;
		public FormalContext f2;
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Formal_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFormal_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_listContext formal_list() throws RecognitionException {
		Formal_listContext _localctx = new Formal_listContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formal_list);

					((Formal_listContext)_localctx).value =  new ArrayList<AST.formal>();
				
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(95);
			((Formal_listContext)_localctx).f1 = formal();
			_localctx.value.add(((Formal_listContext)_localctx).f1.value);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(97);
				match(COMMA);
				setState(98);
				((Formal_listContext)_localctx).f2 = formal();
				_localctx.value.add(((Formal_listContext)_localctx).f2.value);
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalContext extends ParserRuleContext {
		public AST.formal value;
		public Token OBJECTID;
		public Token TYPEID;
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			((FormalContext)_localctx).OBJECTID = match(OBJECTID);
			setState(107);
			match(COLON);
			setState(108);
			((FormalContext)_localctx).TYPEID = match(TYPEID);

						((FormalContext)_localctx).value =  new AST.formal(((FormalContext)_localctx).OBJECTID.getText(),((FormalContext)_localctx).TYPEID.getText(),((FormalContext)_localctx).OBJECTID.getLine());
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public ArrayList<AST.expression> value;
		public ExpressionContext expr;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitExpr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr_list);

					((Expr_listContext)_localctx).value =  new ArrayList<AST.expression>();
				
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OBJECTID) | (1L << BOOL_CONST) | (1L << INT_CONST) | (1L << STR_CONST) | (1L << LPAREN) | (1L << TILDE) | (1L << LBRACE) | (1L << IF) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << ISVOID) | (1L << NOT))) != 0)) {
				{
				setState(111);
				((Expr_listContext)_localctx).expr = expression(0);
				_localctx.value.add(((Expr_listContext)_localctx).expr.value);
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(113);
					match(COMMA);
					setState(114);
					((Expr_listContext)_localctx).expr = expression(0);
					_localctx.value.add(((Expr_listContext)_localctx).expr.value);
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Block_expr_listContext extends ParserRuleContext {
		public ArrayList<AST.expression> value;
		public ExpressionContext expr;
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Block_expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_expr_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBlock_expr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Block_expr_listContext block_expr_list() throws RecognitionException {
		Block_expr_listContext _localctx = new Block_expr_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_block_expr_list);

					((Block_expr_listContext)_localctx).value =  new ArrayList<AST.expression>();
				
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				((Block_expr_listContext)_localctx).expr = expression(0);
				setState(125);
				match(SEMICOLON);
				_localctx.value.add(((Block_expr_listContext)_localctx).expr.value);
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OBJECTID) | (1L << BOOL_CONST) | (1L << INT_CONST) | (1L << STR_CONST) | (1L << LPAREN) | (1L << TILDE) | (1L << LBRACE) | (1L << IF) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << ISVOID) | (1L << NOT))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Let_expr_listContext extends ParserRuleContext {
		public ArrayList<AST.attr> value;
		public AttrContext attr;
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Let_expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_expr_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLet_expr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Let_expr_listContext let_expr_list() throws RecognitionException {
		Let_expr_listContext _localctx = new Let_expr_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_let_expr_list);

					((Let_expr_listContext)_localctx).value =  new ArrayList<AST.attr>();
				
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			((Let_expr_listContext)_localctx).attr = attr();
			_localctx.value.add(((Let_expr_listContext)_localctx).attr.value);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(134);
				match(COMMA);
				setState(135);
				((Let_expr_listContext)_localctx).attr = attr();
				_localctx.value.add(((Let_expr_listContext)_localctx).attr.value);
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Case_expr_listContext extends ParserRuleContext {
		public ArrayList<AST.branch> value;
		public BranchContext branch;
		public List<BranchContext> branch() {
			return getRuleContexts(BranchContext.class);
		}
		public BranchContext branch(int i) {
			return getRuleContext(BranchContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public Case_expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_expr_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCase_expr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Case_expr_listContext case_expr_list() throws RecognitionException {
		Case_expr_listContext _localctx = new Case_expr_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_case_expr_list);

					((Case_expr_listContext)_localctx).value =  new ArrayList<AST.branch>();
				
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(143);
				((Case_expr_listContext)_localctx).branch = branch();
				setState(144);
				match(SEMICOLON);
				_localctx.value.add(((Case_expr_listContext)_localctx).branch.value);
				}
				}
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OBJECTID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchContext extends ParserRuleContext {
		public AST.branch value;
		public Token OBJECTID;
		public Token TYPEID;
		public ExpressionContext expr;
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public TerminalNode DARROW() { return getToken(CoolParser.DARROW, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_branch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((BranchContext)_localctx).OBJECTID = match(OBJECTID);
			setState(152);
			match(COLON);
			setState(153);
			((BranchContext)_localctx).TYPEID = match(TYPEID);
			setState(154);
			match(DARROW);
			setState(155);
			((BranchContext)_localctx).expr = expression(0);

						((BranchContext)_localctx).value =  new AST.branch(((BranchContext)_localctx).OBJECTID.getText(),((BranchContext)_localctx).TYPEID.getText(),((BranchContext)_localctx).expr.value,((BranchContext)_localctx).OBJECTID.getLine());
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AST.expression value;
		public ExpressionContext expr;
		public ExpressionContext e1;
		public Token OBJECTID;
		public Token LET;
		public Let_expr_listContext let_expr_list;
		public Token ISVOID;
		public Token TILDE;
		public Token NOT;
		public Expr_listContext expr_list;
		public Token IF;
		public ExpressionContext ifExpr;
		public ExpressionContext ifBody;
		public ExpressionContext elseBody;
		public Token WHILE;
		public ExpressionContext cond;
		public ExpressionContext body;
		public Token LBRACE;
		public Block_expr_listContext block_expr_list;
		public Token CASE;
		public Case_expr_listContext case_expr_list;
		public Token TYPEID;
		public Token INT_CONST;
		public Token STR_CONST;
		public Token BOOL_CONST;
		public ExpressionContext e2;
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public Let_expr_listContext let_expr_list() {
			return getRuleContext(Let_expr_listContext.class,0);
		}
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public TerminalNode TILDE() { return getToken(CoolParser.TILDE, 0); }
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public Block_expr_listContext block_expr_list() {
			return getRuleContext(Block_expr_listContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public Case_expr_listContext case_expr_list() {
			return getRuleContext(Case_expr_listContext.class,0);
		}
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public TerminalNode INT_CONST() { return getToken(CoolParser.INT_CONST, 0); }
		public TerminalNode STR_CONST() { return getToken(CoolParser.STR_CONST, 0); }
		public TerminalNode BOOL_CONST() { return getToken(CoolParser.BOOL_CONST, 0); }
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public TerminalNode STAR() { return getToken(CoolParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(CoolParser.SLASH, 0); }
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public TerminalNode EQUALS() { return getToken(CoolParser.EQUALS, 0); }
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode ATSYM() { return getToken(CoolParser.ATSYM, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(159);
				((ExpressionContext)_localctx).OBJECTID = match(OBJECTID);
				setState(160);
				match(ASSIGN);
				setState(161);
				((ExpressionContext)_localctx).expr = expression(24);

							((ExpressionContext)_localctx).value =  new AST.assign(((ExpressionContext)_localctx).OBJECTID.getText(),((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).OBJECTID.getLine());
						
				}
				break;
			case 2:
				{
				setState(164);
				((ExpressionContext)_localctx).LET = match(LET);
				setState(165);
				((ExpressionContext)_localctx).let_expr_list = let_expr_list();
				setState(166);
				match(IN);
				setState(167);
				((ExpressionContext)_localctx).expr = expression(18);

							((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).expr.value;
							for(int i=((ExpressionContext)_localctx).let_expr_list.value.size()-1; i>=0; i--)
							{
								AST.attr let_attr = ((ExpressionContext)_localctx).let_expr_list.value.get(i);
								((ExpressionContext)_localctx).value =  new AST.let(let_attr.name,let_attr.typeid, let_attr.value,_localctx.value,((ExpressionContext)_localctx).LET.getLine());
							}
						
				}
				break;
			case 3:
				{
				setState(170);
				((ExpressionContext)_localctx).ISVOID = match(ISVOID);
				setState(171);
				((ExpressionContext)_localctx).expr = expression(15);

							((ExpressionContext)_localctx).value =  new AST.isvoid(((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).ISVOID.getLine());
						
				}
				break;
			case 4:
				{
				setState(174);
				((ExpressionContext)_localctx).TILDE = match(TILDE);
				setState(175);
				((ExpressionContext)_localctx).expr = expression(10);

							((ExpressionContext)_localctx).value =  new AST.comp(((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).TILDE.getLine());
						
				}
				break;
			case 5:
				{
				setState(178);
				((ExpressionContext)_localctx).NOT = match(NOT);
				setState(179);
				((ExpressionContext)_localctx).expr = expression(6);

							((ExpressionContext)_localctx).value =  new AST.neg(((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).NOT.getLine());
						
				}
				break;
			case 6:
				{
				setState(182);
				((ExpressionContext)_localctx).OBJECTID = match(OBJECTID);
				setState(183);
				match(LPAREN);
				setState(184);
				((ExpressionContext)_localctx).expr_list = expr_list();
				setState(185);
				match(RPAREN);

							((ExpressionContext)_localctx).value =  new AST.dispatch(new AST.object("self",((ExpressionContext)_localctx).OBJECTID.getLine()),((ExpressionContext)_localctx).OBJECTID.getText(),((ExpressionContext)_localctx).expr_list.value,((ExpressionContext)_localctx).OBJECTID.getLine());
						
				}
				break;
			case 7:
				{
				setState(188);
				((ExpressionContext)_localctx).IF = match(IF);
				setState(189);
				((ExpressionContext)_localctx).ifExpr = expression(0);
				setState(190);
				match(THEN);
				setState(191);
				((ExpressionContext)_localctx).ifBody = expression(0);
				setState(192);
				match(ELSE);
				setState(193);
				((ExpressionContext)_localctx).elseBody = expression(0);
				setState(194);
				match(FI);

							((ExpressionContext)_localctx).value =  new AST.cond(((ExpressionContext)_localctx).ifExpr.value,((ExpressionContext)_localctx).ifBody.value,((ExpressionContext)_localctx).elseBody.value,((ExpressionContext)_localctx).IF.getLine());
						
				}
				break;
			case 8:
				{
				setState(197);
				((ExpressionContext)_localctx).WHILE = match(WHILE);
				setState(198);
				((ExpressionContext)_localctx).cond = expression(0);
				setState(199);
				match(LOOP);
				setState(200);
				((ExpressionContext)_localctx).body = expression(0);
				setState(201);
				match(POOL);

							((ExpressionContext)_localctx).value =  new AST.loop(((ExpressionContext)_localctx).cond.value,((ExpressionContext)_localctx).body.value,((ExpressionContext)_localctx).WHILE.getLine());
						
				}
				break;
			case 9:
				{
				setState(204);
				((ExpressionContext)_localctx).LBRACE = match(LBRACE);
				setState(205);
				((ExpressionContext)_localctx).block_expr_list = block_expr_list();
				setState(206);
				match(RBRACE);

							((ExpressionContext)_localctx).value =  new AST.block(((ExpressionContext)_localctx).block_expr_list.value,((ExpressionContext)_localctx).LBRACE.getLine());
						
				}
				break;
			case 10:
				{
				setState(209);
				((ExpressionContext)_localctx).CASE = match(CASE);
				setState(210);
				((ExpressionContext)_localctx).expr = expression(0);
				setState(211);
				match(OF);
				setState(212);
				((ExpressionContext)_localctx).case_expr_list = case_expr_list();
				setState(213);
				match(ESAC);

							((ExpressionContext)_localctx).value =  new AST.typcase(((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).case_expr_list.value,((ExpressionContext)_localctx).CASE.getLine());
						
				}
				break;
			case 11:
				{
				setState(216);
				match(NEW);
				setState(217);
				((ExpressionContext)_localctx).TYPEID = match(TYPEID);

							((ExpressionContext)_localctx).value =  new AST.new_(((ExpressionContext)_localctx).TYPEID.getText(),((ExpressionContext)_localctx).TYPEID.getLine());
						
				}
				break;
			case 12:
				{
				setState(219);
				match(LPAREN);
				setState(220);
				((ExpressionContext)_localctx).expr = expression(0);
				setState(221);
				match(RPAREN);

							((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).expr.value;
						
				}
				break;
			case 13:
				{
				setState(224);
				((ExpressionContext)_localctx).OBJECTID = match(OBJECTID);

							((ExpressionContext)_localctx).value =  new AST.object(((ExpressionContext)_localctx).OBJECTID.getText(),((ExpressionContext)_localctx).OBJECTID.getLine());
						
				}
				break;
			case 14:
				{
				setState(226);
				((ExpressionContext)_localctx).INT_CONST = match(INT_CONST);

							((ExpressionContext)_localctx).value =  new AST.int_const(Integer.parseInt(((ExpressionContext)_localctx).INT_CONST.getText()),((ExpressionContext)_localctx).INT_CONST.getLine());
						
				}
				break;
			case 15:
				{
				setState(228);
				((ExpressionContext)_localctx).STR_CONST = match(STR_CONST);

							((ExpressionContext)_localctx).value =  new AST.string_const(((ExpressionContext)_localctx).STR_CONST.getText(),((ExpressionContext)_localctx).STR_CONST.getLine());
						
				}
				break;
			case 16:
				{
				setState(230);
				((ExpressionContext)_localctx).BOOL_CONST = match(BOOL_CONST);

							String boolValue = ((ExpressionContext)_localctx).BOOL_CONST.getText().toLowerCase();
							if(boolValue.equals("true"))
								((ExpressionContext)_localctx).value =  new AST.bool_const(true,((ExpressionContext)_localctx).BOOL_CONST.getLine());
							else
								((ExpressionContext)_localctx).value =  new AST.bool_const(false,((ExpressionContext)_localctx).BOOL_CONST.getLine());
						
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(283);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(281);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(234);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(235);
						match(PLUS);
						setState(236);
						((ExpressionContext)_localctx).e2 = expression(15);

						          			((ExpressionContext)_localctx).value =  new AST.plus(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(239);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(240);
						match(MINUS);
						setState(241);
						((ExpressionContext)_localctx).e2 = expression(14);

						          			((ExpressionContext)_localctx).value =  new AST.sub(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(245);
						match(STAR);
						setState(246);
						((ExpressionContext)_localctx).e2 = expression(13);

						          			((ExpressionContext)_localctx).value =  new AST.mul(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(249);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(250);
						match(SLASH);
						setState(251);
						((ExpressionContext)_localctx).e2 = expression(12);

						          			((ExpressionContext)_localctx).value =  new AST.divide(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(254);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(255);
						match(LT);
						setState(256);
						((ExpressionContext)_localctx).e2 = expression(10);

						          			((ExpressionContext)_localctx).value =  new AST.lt(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(260);
						match(LE);
						setState(261);
						((ExpressionContext)_localctx).e2 = expression(9);

						          			((ExpressionContext)_localctx).value =  new AST.leq(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(264);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(265);
						match(EQUALS);
						setState(266);
						((ExpressionContext)_localctx).e2 = expression(8);

						          			((ExpressionContext)_localctx).value =  new AST.eq(((ExpressionContext)_localctx).e1.value,((ExpressionContext)_localctx).e2.value,((ExpressionContext)_localctx).e1.value.lineNo);
						          		
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.expr = _prevctx;
						_localctx.expr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(269);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(272);
						_la = _input.LA(1);
						if (_la==ATSYM) {
							{
							setState(270);
							match(ATSYM);
							setState(271);
							((ExpressionContext)_localctx).TYPEID = match(TYPEID);
							}
						}

						setState(274);
						match(DOT);
						setState(275);
						((ExpressionContext)_localctx).OBJECTID = match(OBJECTID);
						setState(276);
						match(LPAREN);
						setState(277);
						((ExpressionContext)_localctx).expr_list = expr_list();
						setState(278);
						match(RPAREN);

						          			if(((ExpressionContext)_localctx).TYPEID==null){
						          				((ExpressionContext)_localctx).value =  new AST.dispatch(((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).OBJECTID.getText(),((ExpressionContext)_localctx).expr_list.value,((ExpressionContext)_localctx).expr.value.lineNo);
						          			}
						          			else
						          				((ExpressionContext)_localctx).value =  new AST.static_dispatch(((ExpressionContext)_localctx).expr.value,((ExpressionContext)_localctx).TYPEID.getText(),((ExpressionContext)_localctx).OBJECTID.getText(),((ExpressionContext)_localctx).expr_list.value,((ExpressionContext)_localctx).expr.value.lineNo);
						          		
						}
						break;
					}
					} 
				}
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 23);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3@\u0121\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\6\3\'\n\3\r\3\16\3(\3\4\3\4\3\4\3\4\5\4/\n\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\7\5:\n\5\f\5\16\5=\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6W"+
		"\n\6\3\7\3\7\3\7\3\7\3\7\5\7^\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b"+
		"h\n\b\f\b\16\bk\13\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\nx"+
		"\n\n\f\n\16\n{\13\n\5\n}\n\n\3\13\3\13\3\13\3\13\6\13\u0083\n\13\r\13"+
		"\16\13\u0084\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u008d\n\f\f\f\16\f\u0090\13\f"+
		"\3\r\3\r\3\r\3\r\6\r\u0096\n\r\r\r\16\r\u0097\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00eb\n\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u0113\n\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\7\17\u011c\n\17\f\17\16\17\u011f\13\17\3\17\2\3\34\20\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\2\2\u0136\2\36\3\2\2\2\4&\3\2\2\2\6*\3\2\2"+
		"\2\b;\3\2\2\2\nV\3\2\2\2\fX\3\2\2\2\16a\3\2\2\2\20l\3\2\2\2\22|\3\2\2"+
		"\2\24\u0082\3\2\2\2\26\u0086\3\2\2\2\30\u0095\3\2\2\2\32\u0099\3\2\2\2"+
		"\34\u00ea\3\2\2\2\36\37\5\4\3\2\37 \7\2\2\3 !\b\2\1\2!\3\3\2\2\2\"#\5"+
		"\6\4\2#$\7\r\2\2$%\b\3\1\2%\'\3\2\2\2&\"\3\2\2\2\'(\3\2\2\2(&\3\2\2\2"+
		"()\3\2\2\2)\5\3\2\2\2*+\7\34\2\2+.\7\4\2\2,-\7!\2\2-/\7\4\2\2.,\3\2\2"+
		"\2./\3\2\2\2/\60\3\2\2\2\60\61\7\26\2\2\61\62\5\b\5\2\62\63\7\27\2\2\63"+
		"\64\b\4\1\2\64\7\3\2\2\2\65\66\5\n\6\2\66\67\7\r\2\2\678\b\5\1\28:\3\2"+
		"\2\29\65\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\t\3\2\2\2=;\3\2\2\2>?"+
		"\5\f\7\2?@\b\6\1\2@W\3\2\2\2AB\7\5\2\2BC\7\t\2\2CD\7\n\2\2DE\7\13\2\2"+
		"EF\7\4\2\2FG\7\26\2\2GH\5\34\17\2HI\7\27\2\2IJ\b\6\1\2JW\3\2\2\2KL\7\5"+
		"\2\2LM\7\t\2\2MN\5\16\b\2NO\7\n\2\2OP\7\13\2\2PQ\7\4\2\2QR\7\26\2\2RS"+
		"\5\34\17\2ST\7\27\2\2TU\b\6\1\2UW\3\2\2\2V>\3\2\2\2VA\3\2\2\2VK\3\2\2"+
		"\2W\13\3\2\2\2XY\7\5\2\2YZ\7\13\2\2Z]\7\4\2\2[\\\7\33\2\2\\^\5\34\17\2"+
		"][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\b\7\1\2`\r\3\2\2\2ab\5\20\t\2bi\b\b\1"+
		"\2cd\7\16\2\2de\5\20\t\2ef\b\b\1\2fh\3\2\2\2gc\3\2\2\2hk\3\2\2\2ig\3\2"+
		"\2\2ij\3\2\2\2j\17\3\2\2\2ki\3\2\2\2lm\7\5\2\2mn\7\13\2\2no\7\4\2\2op"+
		"\b\t\1\2p\21\3\2\2\2qr\5\34\17\2ry\b\n\1\2st\7\16\2\2tu\5\34\17\2uv\b"+
		"\n\1\2vx\3\2\2\2ws\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z}\3\2\2\2{y\3"+
		"\2\2\2|q\3\2\2\2|}\3\2\2\2}\23\3\2\2\2~\177\5\34\17\2\177\u0080\7\r\2"+
		"\2\u0080\u0081\b\13\1\2\u0081\u0083\3\2\2\2\u0082~\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\25\3\2\2\2\u0086"+
		"\u0087\5\f\7\2\u0087\u008e\b\f\1\2\u0088\u0089\7\16\2\2\u0089\u008a\5"+
		"\f\7\2\u008a\u008b\b\f\1\2\u008b\u008d\3\2\2\2\u008c\u0088\3\2\2\2\u008d"+
		"\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\27\3\2\2"+
		"\2\u0090\u008e\3\2\2\2\u0091\u0092\5\32\16\2\u0092\u0093\7\r\2\2\u0093"+
		"\u0094\b\r\1\2\u0094\u0096\3\2\2\2\u0095\u0091\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\31\3\2\2\2\u0099\u009a"+
		"\7\5\2\2\u009a\u009b\7\13\2\2\u009b\u009c\7\4\2\2\u009c\u009d\7\31\2\2"+
		"\u009d\u009e\5\34\17\2\u009e\u009f\b\16\1\2\u009f\33\3\2\2\2\u00a0\u00a1"+
		"\b\17\1\2\u00a1\u00a2\7\5\2\2\u00a2\u00a3\7\33\2\2\u00a3\u00a4\5\34\17"+
		"\32\u00a4\u00a5\b\17\1\2\u00a5\u00eb\3\2\2\2\u00a6\u00a7\7\"\2\2\u00a7"+
		"\u00a8\5\26\f\2\u00a8\u00a9\7 \2\2\u00a9\u00aa\5\34\17\24\u00aa\u00ab"+
		"\b\17\1\2\u00ab\u00eb\3\2\2\2\u00ac\u00ad\7+\2\2\u00ad\u00ae\5\34\17\21"+
		"\u00ae\u00af\b\17\1\2\u00af\u00eb\3\2\2\2\u00b0\u00b1\7\23\2\2\u00b1\u00b2"+
		"\5\34\17\f\u00b2\u00b3\b\17\1\2\u00b3\u00eb\3\2\2\2\u00b4\u00b5\7,\2\2"+
		"\u00b5\u00b6\5\34\17\b\u00b6\u00b7\b\17\1\2\u00b7\u00eb\3\2\2\2\u00b8"+
		"\u00b9\7\5\2\2\u00b9\u00ba\7\t\2\2\u00ba\u00bb\5\22\n\2\u00bb\u00bc\7"+
		"\n\2\2\u00bc\u00bd\b\17\1\2\u00bd\u00eb\3\2\2\2\u00be\u00bf\7\37\2\2\u00bf"+
		"\u00c0\5\34\17\2\u00c0\u00c1\7%\2\2\u00c1\u00c2\5\34\17\2\u00c2\u00c3"+
		"\7\35\2\2\u00c3\u00c4\5\34\17\2\u00c4\u00c5\7\36\2\2\u00c5\u00c6\b\17"+
		"\1\2\u00c6\u00eb\3\2\2\2\u00c7\u00c8\7&\2\2\u00c8\u00c9\5\34\17\2\u00c9"+
		"\u00ca\7#\2\2\u00ca\u00cb\5\34\17\2\u00cb\u00cc\7$\2\2\u00cc\u00cd\b\17"+
		"\1\2\u00cd\u00eb\3\2\2\2\u00ce\u00cf\7\26\2\2\u00cf\u00d0\5\24\13\2\u00d0"+
		"\u00d1\7\27\2\2\u00d1\u00d2\b\17\1\2\u00d2\u00eb\3\2\2\2\u00d3\u00d4\7"+
		"\'\2\2\u00d4\u00d5\5\34\17\2\u00d5\u00d6\7)\2\2\u00d6\u00d7\5\30\r\2\u00d7"+
		"\u00d8\7(\2\2\u00d8\u00d9\b\17\1\2\u00d9\u00eb\3\2\2\2\u00da\u00db\7*"+
		"\2\2\u00db\u00dc\7\4\2\2\u00dc\u00eb\b\17\1\2\u00dd\u00de\7\t\2\2\u00de"+
		"\u00df\5\34\17\2\u00df\u00e0\7\n\2\2\u00e0\u00e1\b\17\1\2\u00e1\u00eb"+
		"\3\2\2\2\u00e2\u00e3\7\5\2\2\u00e3\u00eb\b\17\1\2\u00e4\u00e5\7\7\2\2"+
		"\u00e5\u00eb\b\17\1\2\u00e6\u00e7\7\b\2\2\u00e7\u00eb\b\17\1\2\u00e8\u00e9"+
		"\7\6\2\2\u00e9\u00eb\b\17\1\2\u00ea\u00a0\3\2\2\2\u00ea\u00a6\3\2\2\2"+
		"\u00ea\u00ac\3\2\2\2\u00ea\u00b0\3\2\2\2\u00ea\u00b4\3\2\2\2\u00ea\u00b8"+
		"\3\2\2\2\u00ea\u00be\3\2\2\2\u00ea\u00c7\3\2\2\2\u00ea\u00ce\3\2\2\2\u00ea"+
		"\u00d3\3\2\2\2\u00ea\u00da\3\2\2\2\u00ea\u00dd\3\2\2\2\u00ea\u00e2\3\2"+
		"\2\2\u00ea\u00e4\3\2\2\2\u00ea\u00e6\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u011d\3\2\2\2\u00ec\u00ed\f\20\2\2\u00ed\u00ee\7\17\2\2\u00ee\u00ef\5"+
		"\34\17\21\u00ef\u00f0\b\17\1\2\u00f0\u011c\3\2\2\2\u00f1\u00f2\f\17\2"+
		"\2\u00f2\u00f3\7\20\2\2\u00f3\u00f4\5\34\17\20\u00f4\u00f5\b\17\1\2\u00f5"+
		"\u011c\3\2\2\2\u00f6\u00f7\f\16\2\2\u00f7\u00f8\7\21\2\2\u00f8\u00f9\5"+
		"\34\17\17\u00f9\u00fa\b\17\1\2\u00fa\u011c\3\2\2\2\u00fb\u00fc\f\r\2\2"+
		"\u00fc\u00fd\7\22\2\2\u00fd\u00fe\5\34\17\16\u00fe\u00ff\b\17\1\2\u00ff"+
		"\u011c\3\2\2\2\u0100\u0101\f\13\2\2\u0101\u0102\7\24\2\2\u0102\u0103\5"+
		"\34\17\f\u0103\u0104\b\17\1\2\u0104\u011c\3\2\2\2\u0105\u0106\f\n\2\2"+
		"\u0106\u0107\7\32\2\2\u0107\u0108\5\34\17\13\u0108\u0109\b\17\1\2\u0109"+
		"\u011c\3\2\2\2\u010a\u010b\f\t\2\2\u010b\u010c\7\25\2\2\u010c\u010d\5"+
		"\34\17\n\u010d\u010e\b\17\1\2\u010e\u011c\3\2\2\2\u010f\u0112\f\31\2\2"+
		"\u0110\u0111\7\f\2\2\u0111\u0113\7\4\2\2\u0112\u0110\3\2\2\2\u0112\u0113"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\7\30\2\2\u0115\u0116\7\5\2\2"+
		"\u0116\u0117\7\t\2\2\u0117\u0118\5\22\n\2\u0118\u0119\7\n\2\2\u0119\u011a"+
		"\b\17\1\2\u011a\u011c\3\2\2\2\u011b\u00ec\3\2\2\2\u011b\u00f1\3\2\2\2"+
		"\u011b\u00f6\3\2\2\2\u011b\u00fb\3\2\2\2\u011b\u0100\3\2\2\2\u011b\u0105"+
		"\3\2\2\2\u011b\u010a\3\2\2\2\u011b\u010f\3\2\2\2\u011c\u011f\3\2\2\2\u011d"+
		"\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\35\3\2\2\2\u011f\u011d\3\2\2"+
		"\2\21(.;V]iy|\u0084\u008e\u0097\u00ea\u0112\u011b\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}