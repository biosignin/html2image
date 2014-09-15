package gui.ava.html.parser;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.Map;

/**
 * @author Yoav Aharoni
 */
public interface HtmlParser extends DocumentHolder {

	DOMParser getDomParser();

	void setDomParser(DOMParser domParser);

	void setDocument(Document document);

	void load(URL url,Map<String, String> placeholder);

	void load(URI uri,Map<String, String> placeholder);

	void load(File file,Map<String, String> placeholder);

	void load(Reader reader,Map<String, String> placeholder);

	void load(InputStream inputStream,Map<String, String> placeholder);

	void loadHtml(String html,Map<String, String> placeholder);

	void loadURI(String uri, Map<String, String> placeholder);
}
