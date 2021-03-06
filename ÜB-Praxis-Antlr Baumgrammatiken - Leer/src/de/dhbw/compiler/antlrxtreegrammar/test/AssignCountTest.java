/* **********************************************
 * Duale Hochschule Baden-W�rttemberg Karlsruhe
 * Prof. Dr. J�rn Eisenbiegler
 * 
 * Vorlesung �bersetzerbau
 * Praxis ANTLR-Parser f�r X
 * - Testfall-Utility f�r Parser
 * 
 * **********************************************
 */


package de.dhbw.compiler.antlrxtreegrammar.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import de.dhbw.compiler.antlrxtreegrammar.AssignCount;
import de.dhbw.compiler.antlrxtreegrammar.XLexer;
import de.dhbw.compiler.antlrxtreegrammar.XParser;
import de.dhbw.compiler.antlrxtreegrammar.XTreeGrammar;

public abstract class AssignCountTest {
	
	protected void testTreeGrammarTree(String in, int expected) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream(in.getBytes())); 
		XLexer scanner = new XLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(scanner);
		XParser			parser	= new XParser(tokens);
		ParserRuleReturnScope result = parser.program();
		CommonTree out = (CommonTree) result.getTree();
		
	    AssignCount count = new AssignCount(new CommonTreeNodeStream(out));
		out = count.program().getTree();
		
		assertEquals(expected, count.getCount());
	}
	
}
