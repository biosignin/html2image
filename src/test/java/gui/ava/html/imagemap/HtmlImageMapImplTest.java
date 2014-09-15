package gui.ava.html.imagemap;

import gui.ava.html.BaseTest;
import gui.ava.html.Html2Image;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRendererImpl;

import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * @author Yoav Aharoni
 */
public class HtmlImageMapImplTest extends BaseTest {
	
	@Before
	public void createParser() {
		
		new File("test").mkdir();
		
	}
	
	@After
	public void deleteFolder() {
		FileUtils.deleteQuietly(new File("test"));
	}

	
	public static byte[] getResourceUsingFileStreams(InputStream source)
	        throws IOException {
	    
		ByteArrayOutputStream output = null;
	    try {
	        
	        output = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = source.read(buf)) > 0) {
	            output.write(buf, 0, bytesRead);
	        }
	        output.flush();
	        return output.toByteArray();
	    } finally {
	    	try{
	    	source.close();
	        output.close();
	    	}catch (Exception ex){
	    		ex.printStackTrace();
	    	}
	    }
	}
	
	@Test
	public void test1ImageMapDocument() throws Exception {
		
		
		String string = new String(getResourceUsingFileStreams(this.getClass().getClassLoader().getResourceAsStream("test1.html")));
		final Html2Image html2Image = Html2Image.fromHtml(string,null);
//		final Html2Image html2Image = Html2Image.fromURL(ResourceUtils.getURL("classpath:emanuele.html"));
		html2Image.getImageRenderer().setWidth(100);
		html2Image.getImageRenderer().saveImage("./test/test1.png");
		html2Image.getHtmlImageMap().getClickableBoxes().entrySet();
		
		for (Entry<Element, Collection<ElementBox>> e: html2Image.getHtmlImageMap().getClickableBoxes().entrySet()){
			System.out.println("------------");
//			System.out.println(e.getKey());
			for (ElementBox box:e.getValue())
			{
				System.out.println(box.getElement().getAttribute("href"));;
				System.out.println(new Rectangle(box.getLeft(), box.getTop(), box.getWidth(), box.getHeight()));
			}
		}
//		html2Image.getImageRenderer().setImageType(imageType)
		html2Image.getHtmlImageMap().saveImageMapDocument("./test/test1.html", "./test/test1.png");
	}



	@Test
	public void googleImageMapDocument() throws Exception {
		final Html2Image html2Image = Html2Image.fromURL(new URL("http://www.google.com"),null);
		html2Image.getImageRenderer().saveImage("./test/google.png");
		html2Image.getHtmlImageMap().saveImageMapDocument("./test/google.html", "./test/google.png");
		getTest1Url();
	}

	@Test
	public void hebImageMapDocument() throws Exception {
		final Html2Image html2Image = Html2Image.fromHtml("<div>text<div style='text-align: right'><a onclick='alert(1)'>שלום!</a></div></div>",null);
		html2Image.getImageRenderer().saveImage("./test/heb.png");
		html2Image.getHtmlImageMap().saveImageMapDocument("./test/heb.html", "./test/heb.png");
	}

	@Test
	public void imageImageMapDocument() throws Exception {
		final Html2Image html2Image = Html2Image.fromHtml("<div>HELLO!<a href='javascript: alert(1);'><img src='http://www.google.co.il/intl/en_com/images/srpr/logo1w.png'/></a></div>",null);
		html2Image.getImageRenderer().saveImage("./test/image.png");
		html2Image.getHtmlImageMap().saveImageMapDocument("./test/image.html", "./test/heb.png");
	}
}
