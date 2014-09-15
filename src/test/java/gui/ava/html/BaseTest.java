package gui.ava.html;

import java.net.URL;

/**
 * @author Yoav Aharoni
 */
public class BaseTest {
	public static final String TEST1_PATH = "classpath:test1.html";

	public static URL getTest1Url() {
//		try {
			return BaseTest.class.getClassLoader().getResource("test1.html");
//			return ResourceUtils.getURL(TEST1_PATH);
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
	}
}
