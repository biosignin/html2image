package gui.ava.html.renderer;

import gui.ava.html.BaseTest;
import gui.ava.html.parser.HtmlParserImpl;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yoav Aharoni
 */
public class ImageRendererImplTest extends BaseTest {
	private HtmlParserImpl parser;
	private ImageRendererImpl renderer;

	
	
	@Before
	public void createParser() {
		
		new File("test").mkdir();
		parser = new HtmlParserImpl();
		renderer = new ImageRendererImpl(parser);
	}
	
	@After
	public void deleteFolder() {
		FileUtils.deleteQuietly(new File("test"));
	}

	@Test
	public void testSaveStream() throws Exception {
		parser.load(getTest1Url(),null);
		renderer.saveImage(new FileOutputStream("./test/file1.png"), true);
	}

	@Test
	public void testSaveFile() throws Exception {
		parser.load(getTest1Url(),null);
		renderer.saveImage("./test/test.gif");
		renderer.saveImage("./test/test.png");
		renderer.saveImage("./test/test.jpg");
		renderer.saveImage("./test/test.bmp");
	}
}
