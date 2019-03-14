package jdom;

import static org.junit.Assert.*;

import org.apache.batik.dom.GenericDOMImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class SimpleDOMTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		DOMImplementation dom_impl = GenericDOMImplementation.getDOMImplementation();
		
		Document doc = dom_impl.createDocument( "example.com", "test", null );
		
		assertNotNull( doc );
		
		Element e = doc.createElement( "html" );
		doc.getDocumentElement().appendChild( e );
		
		assertNotNull( e );
		
	}

}
