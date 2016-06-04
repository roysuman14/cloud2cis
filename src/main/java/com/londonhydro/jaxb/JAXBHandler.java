package com.londonhydro.jaxb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

/**
 * JAXB Handler
 * 
 * @author Daniel I. Khan Ramiro (di.khan@affsys.com)
 */
public class JAXBHandler {

	public static enum CONTENT_TYPE {
		JSON, XML
	};

	/**
	 * Generic method which will overwrite the file passed as a parameter with
	 * the corresponding XML extracted from the clazz.
	 * 
	 * @param clazz
	 *            containing the proper XML annotations.
	 * @param file
	 *            which will be overwritten with the XML content.
	 * @return String containing the XML generated out of the parameter passed
	 *         to the method.
	 * @throws JAXBException
	 */
	public static <T> void marshal(T clazz, File file) throws IOException, JAXBException {
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(file));

		JAXBContext context;
		context = JAXBContext.newInstance(clazz.getClass());

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(clazz, writer);
		writer.close();
	}

	/**
	 * Generic method which will return the corresponding XML
	 * 
	 * @param clazz
	 *            containing the proper XML annotations.
	 * @return String containing the XML generated out of the parameter passed
	 *         to the method.
	 * @throws JAXBException
	 * @throws XMLStreamException 
	 */
	public static <T> String marshal(T clazz, CONTENT_TYPE contentType) throws JAXBException, XMLStreamException {
		StringWriter writer = new StringWriter();

		JAXBContext jaxbContext;
		jaxbContext = JAXBContext.newInstance(clazz.getClass());

		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		if (contentType == CONTENT_TYPE.JSON) {
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			Configuration config = new Configuration();
			MappedNamespaceConvention con = new MappedNamespaceConvention(config);
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);

			jaxbMarshaller.marshal(clazz, xmlStreamWriter);
		} else {
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");/**Added to remove schema location and name spaces*/
			// Filter the output to remove namespaces.
			//jaxbMarshaller.marshal(clazz, NoNamesWriter.filter(writer));
			jaxbMarshaller.marshal(clazz, writer);
		}

		return writer.getBuffer().toString();

	}

	/**
	 * Generic method to unmarshal the importFile
	 * 
	 * @param importFile
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unmarshal(File importFile, T clazz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz.getClass());
		Unmarshaller um = context.createUnmarshaller();

		return (T) um.unmarshal(importFile);
	}

}