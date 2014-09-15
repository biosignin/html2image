package gui.ava.html.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gui.ava.html.BaseTest;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author Yoav Aharoni
 */
public class HtmlParserImplTest extends BaseTest {
	private HtmlParserImpl parser;

	@Before
	public void createParser() {
		parser = new HtmlParserImpl();
	}

	@Test
	public void testLoadURI() throws Exception {
		parser.load(getTest1Url().toURI(),null);
//		assertTest1();
	}

	@Test
	public void testLoadExternalURL() throws Exception {
		parser.load(new URL("http://www.google.co.il"),null);
		assertTrue(getDocument().getElementsByTagName("div").getLength() > 0);
	}


	@Test
	public void testLoadHtml() throws Exception {
		parser.loadHtml("<b>Hello</b>",null);
		assertEquals(getDocument().getElementsByTagName("b").item(0).getTextContent(), "Hello");
	}

	private void assertTest1() {
		assertEquals(getDocument().getElementsByTagName("strong").item(0).getTextContent(), "Hello");
	}

	private Document getDocument() {
		return parser.getDocument();
	}
}
