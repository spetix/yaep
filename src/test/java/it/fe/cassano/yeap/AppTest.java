package it.fe.cassano.yeap;

import it.fe.cassano.yeap.ast.AssignmentTest;
import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SimpleExpressionsTest;
import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;
import it.fe.cassano.yeap.parser.AssignmentParserTest;
import it.fe.cassano.yeap.parser.ExpressionParserTest;
import it.fe.cassano.yeap.parser.FactorParserTest;
import it.fe.cassano.yeap.parser.FunctionDefineParserTest;
import it.fe.cassano.yeap.parser.OtherParserTest;
import it.fe.cassano.yeap.parser.TermParserTest;
import it.fe.cassano.yeap.tokenizer.TokenizerExpressionsTest;
import it.fe.cassano.yeap.tokenizer.TokenizerFunctionNameTest;
import it.fe.cassano.yeap.tokenizer.TokenizerIdentTest;
import it.fe.cassano.yeap.tokenizer.TokenizerNumbersTest;
import it.fe.cassano.yeap.visitor.EvalWithAssignVisitorTest;
import it.fe.cassano.yeap.visitor.LispOutputVisitorTest;
import it.fe.cassano.yeap.visitors.EvalVisitor;
import it.fe.cassano.yeap.visitors.LispOutputVisitor;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	/* TOKENIZER AND PARSER */
    	ts.addTestSuite(TokenizerNumbersTest.class);
    	ts.addTestSuite(TokenizerIdentTest.class);
    	ts.addTestSuite(TokenizerFunctionNameTest.class);  
    	ts.addTestSuite(TokenizerExpressionsTest.class);  
    	ts.addTestSuite(ExpressionParserTest.class);
    	ts.addTestSuite(TermParserTest.class);
    	ts.addTestSuite(FactorParserTest.class);
    	ts.addTestSuite(AssignmentParserTest.class);
    	ts.addTestSuite(FunctionDefineParserTest.class);   	  
    	ts.addTestSuite(OtherParserTest.class);
    	/* AST */
    	ts.addTestSuite(SimpleExpressionsTest.class);
    	ts.addTestSuite(AssignmentTest.class);
    	/* VISITORS */
    	ts.addTestSuite(EvalWithAssignVisitorTest.class);
    	ts.addTestSuite(LispOutputVisitorTest.class);
    	/* OTHER */
    	ts.addTestSuite(OtherParserTest.class);
    	ts.addTestSuite(TokenizerNumbersTest.class);
    	ts.addTestSuite(AppTest.class);
     
      // After adding assignment extension to the App:
    	
  
        return ts;
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws ParseException 
     */
    public void testSimpleSum() throws IOException, ParseException
    {
        Reader r = new StringReader(" 1 + 1;");
         
        ExpressionParser p = new ExpressionParser(r);
        Exp expressionEval = p.s();
        assertEquals(expressionEval.toString(),"((1)+(1))");
        r.close();     
    }
    
    
    public void testProd() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader(" 1 * 1;");
         
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.expr();
        assertEquals(expressionEval.toString(),"((1)*(1))");
    }
    
    public void testCplx() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("1 * ( 1 + 1 ) ;");
         
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((1)*((1)+(1)))",expressionEval.toString());
    }
    
    public void testCplx2() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader(" 12 * ( 5 + 1 );");
         
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*((5)+(1)))",expressionEval.toString());
    }

    public void testLispOutputVisitor() throws Exception
    {
    	Reader valThis = new StringReader(" 7 * ( 1 + 1 );");
         
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.s();
        final LispOutputVisitor v = new LispOutputVisitor();
        expressionEval.accept(v);
        assertEquals(v.getVal(),"(* 7 (+ 1 1))");
        
    }
    
    public void testLispOutputVisitor2() throws Exception
    {
    	Reader valThis = new StringReader(" 7 * ( 1 + 1 ) - 3;");
         
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.s();
        final LispOutputVisitor v = new LispOutputVisitor();
        expressionEval.accept(v);
        assertEquals(v.getVal(),"(- (* 7 (+ 1 1)) 3)");
        
    }
    
    
    public void testLispOutputVisitor3() throws Exception
    {
    	Reader valThis = new StringReader(" 7 * ( sin(-0.75) + max(3,5 ) )- 3;");
         
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.s();
        final LispOutputVisitor v = new LispOutputVisitor();
        expressionEval.accept(v);
        assertEquals(v.getVal(),"(- (* 7 (+ (sin (- 0.75)) (max 3 5))) 3)");
        
    }
    
    public void testEvalVisitor() throws Exception
    {
    	Reader valThis = new StringReader(" ( 7 * ( 1 + 1 ) ) - 3;");
        
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.s();
        final EvalVisitor v = new EvalVisitor(new MockEnvironment(), new MockEnvironment());
        v.visit(expressionEval);
        assertEquals(v.getVal(),new Integer(11));
        
    }
    
    public void testCasting()
    {
    	NumExp a = new NumExp(7);
    	RealExp b = new RealExp(5.5d);
    	
    	assertEquals(11.0d, sum(b,b));
    	assertEquals(12.5d, sum(a,b));
    	assertEquals(12.5d, sum(b,a));
    	assertEquals(14l, sum(a,a));
    	
    }
    

    
    protected double sum(RealExp a, RealExp b)
    {
    	return a.getValue()+b.getValue();
    }
    
    protected double sum(RealExp a,NumExp b)
    {
    	return a.getValue()+b.getValue();
    }
    
    protected double sum(NumExp a, RealExp b)
    {
    	return a.getValue()+b.getValue();
    }
    
    protected long sum(NumExp a, NumExp b)
    {
    	return (long) (a.getValue()+b.getValue());
    }
    

    
    
    
}
