package it.fe.cassano.astvisassignsample;

import it.fe.cassano.astvisassignsample.ast.AssignmentTest;
import it.fe.cassano.astvisassignsample.ast.Exp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;
import it.fe.cassano.astvisassignsample.ast.SimpleExpressionsTest;
import it.fe.cassano.astvisassignsample.ccparser.ExpressionParser;
import it.fe.cassano.astvisassignsample.ccparser.ParseException;
import it.fe.cassano.astvisassignsample.parser.ExpressionParserTest;
import it.fe.cassano.astvisassignsample.parser.OtherParserTest;
import it.fe.cassano.astvisassignsample.parser.ParserValidityTest;
import it.fe.cassano.astvisassignsample.tokenizer.TokenizerNumbersTest;
import it.fe.cassano.astvisassignsample.visitor.EvalWithAssignVisitorTest;
import it.fe.cassano.astvisassignsample.visitor.LispOutputVisitor;

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
//    	/* TOKENIZER AND PARSER */
//    	ts.addTestSuite(ParserValidityTest.class);
//    	ts.addTestSuite(ExpressionParserTest.class);
//    	/* AST */
//    	ts.addTestSuite(SimpleExpressionsTest.class);
//    	/* VISITORS */
//    	ts.addTestSuite(EvalWithAssignVisitorTest.class);
//    	/* OTHER */
//    	ts.addTestSuite(OtherParserTest.class);
//    	ts.addTestSuite(TokenizerNumbersTest.class);
   	ts.addTestSuite(AppTest.class);
     
    	// After adding assignment extension to the App:
    	
   // 	ts.addTestSuite(AssignmentTest.class);
  
        return ts;
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws ParseException 
     */
    public void testSimpleSum() throws IOException, ParseException
    {
        Reader r = new StringReader(" 1 + 1");
         
        ExpressionParser p = new ExpressionParser(r);
        Exp expressionEval = p.expr();
        assertEquals(expressionEval.toString(),"((1)+(1))");
        r.close();     
    }
    
    
    public void testProd() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader(" 1 * 1");
         
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.expr();
        assertEquals(expressionEval.toString(),"((1)*(1))");
    }
    
    public void testCplx() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("1 * ( 1 + 1 ) ");
         
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.expr();
        assertEquals("((1)*((1)+(1)))",expressionEval.toString());
    }
    
    public void testCplx2() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader(" 12 * ( 5 + 1 )");
         
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.expr();
        assertEquals("((12)*((5)+(1)))",expressionEval.toString());
    }
//    public void testEvalCplx()
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader(" 3 * ( 1 + 1 )");
//         
//        ExpressionParser p = new ExpressionParser(valThis);
//        Exp expressionEval = p.parseExp();
//  //      assertEquals(expressionEval.eval(),6);
//    }
    public void testLispOutputVisitor() throws IOException, ParseException
    {
    	Reader valThis = new StringReader(" 7 * ( 1 + 1 )");
         
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.expr();
        final LispOutputVisitor v = new LispOutputVisitor();
        expressionEval.accept(v);
        assertEquals(v.getVal(),"(* 7 (+ 1 1))");
        
    }
    
    public void testLispOutputVisitor2() throws IOException, ParseException
    {
    	Reader valThis = new StringReader(" 7 * ( 1 + 1 ) - 3");
         
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.expr();
        final LispOutputVisitor v = new LispOutputVisitor();
        expressionEval.accept(v);
        assertEquals(v.getVal(),"(- (* 7 (+ 1 1)) 3)");
        
    }
    
    
    public void testLispOutputVisitor3() throws IOException, ParseException
    {
    	Reader valThis = new StringReader(" 7 * ( sin(-0.75) + max(3,5 ) )- 3");
         
        ExpressionParser p = new ExpressionParser(valThis);
        final Exp expressionEval = p.expr();
        final LispOutputVisitor v = new LispOutputVisitor();
        expressionEval.accept(v);
        assertEquals(v.getVal(),"(- (* 7 (+ (sin (- 0.75)) (max 3 5))) 3)");
        
    }
    
//    public void testEvalVisitor() throws IOException, ParseException
//    {
//    	Reader valThis = new StringReader(" ( 7 * ( 1 + 1 ) ) - 3");
//        
//        ExpressionParser p = new ExpressionParser(valThis);
//        final Exp expressionEval = p.expr();
//        final EvalVisitor v = new EvalVisitor();
//        expressionEval.accept(v);
//        assertEquals(v.getVal(),new Integer(11));
//        
//    }
    
    public void testCasting()
    {
    	NumExp a = new NumExp(7);
    	RealExp b = new RealExp(5.5f);
    	
    	assertEquals(11.0f, sum(b,b));
    	assertEquals(12.5f, sum(a,b));
    	assertEquals(12.5f, sum(b,a));
    	assertEquals(14l, sum(a,a));
    	
    }
    

    
    protected Float sum(RealExp a, RealExp b)
    {
    	return a.getValue()+b.getValue();
    }
    
    protected Float sum(RealExp a,NumExp b)
    {
    	return a.getValue()+b.getValue();
    }
    
    protected Float sum(NumExp a, RealExp b)
    {
    	return a.getValue()+b.getValue();
    }
    
    protected long sum(NumExp a, NumExp b)
    {
    	return (long) (a.getValue()+b.getValue());
    }
    

    
    
    
}
