package com.londonhydro.jaxb;

import java.io.Writer;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class NoNamesWriter extends DelegatingXMLStreamWriter {

	private static final NamespaceContext emptyNamespaceContext = new NamespaceContext() {

	    public String getNamespaceURI(String prefix) {
	      return "";
	    }

	    public String getPrefix(String namespaceURI) {
	      return "";
	    }

	    public Iterator getPrefixes(String namespaceURI) {
	      return null;
	    }

	  };

	  public static XMLStreamWriter filter(Writer writer) throws XMLStreamException {
	    return new NoNamesWriter(XMLOutputFactory.newInstance().createXMLStreamWriter(writer));
	  }

	  public NoNamesWriter(XMLStreamWriter writer) {
	    super(writer);
	  }

	  @Override
	  public NamespaceContext getNamespaceContext() {
	    return emptyNamespaceContext;
	  }
	
}
