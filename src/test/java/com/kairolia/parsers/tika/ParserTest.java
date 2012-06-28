/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.tika;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerConfigurationException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.OfficeParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Gael Marziou
 *
 */
public class ParserTest {

	final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testPDFParser() throws IOException, SAXException, TikaException {
		ClassPathResource resource = new ClassPathResource("SampleDocument.pdf");
		InputStream input = resource.getInputStream();
		ContentHandler textHandler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		PDFParser parser = new PDFParser();
		ParseContext context = new ParseContext();
		parser.parse(input, textHandler, metadata, context);
		input.close();
		assertEquals("Title", "Example PDF document", metadata.get("title"));
		assertEquals("Author", "Sami Siren", metadata.get("Author"));
		log.info("content: " + textHandler.toString());
	}

	@Test
	public void testExcel2003Parser() throws IOException, SAXException,
			TikaException {
		ClassPathResource resource = new ClassPathResource("SampleDocument.xls");
		InputStream input = resource.getInputStream();
		ContentHandler textHandler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		Parser parser = new OfficeParser();
		ParseContext context = new ParseContext();
		parser.parse(input, textHandler, metadata, context);
		input.close();
		assertNull("Title", metadata.get("title"));
		assertEquals("Author", "Gael", metadata.get("Author"));
		log.info("content: " + textHandler.toString());
	}

	/**
	 * Show how to use the SAX events to print the content extracted from an Excel spreadsheet.
	 */
	@Test
	public void testExcel2003XHTMLParser() throws IOException, SAXException,
			TikaException, TransformerConfigurationException {
		ClassPathResource resource = new ClassPathResource("SampleDocument.xls");
		InputStream input = resource.getInputStream();

		ContentHandler output = new LoggingTableContentHandler();
		Metadata metadata = new Metadata();

		XHTMLContentHandler xhtml = new XHTMLContentHandler(output, metadata);
		Parser parser = new OfficeParser();
		ParseContext context = new ParseContext();
		parser.parse(input, xhtml, metadata, context);
		input.close();
		assertNull("Title", metadata.get("title"));
		assertEquals("Author", "Gael", metadata.get("Author"));
		log.info("content: \n" + xhtml.toString());
	}

	@Test
	public void testExcel2007Parser() throws IOException, SAXException,
			TikaException {
		ClassPathResource resource = new ClassPathResource(
				"SampleDocument.xlsx");
		InputStream input = resource.getInputStream();
		ContentHandler textHandler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		Parser parser = new OOXMLParser();
		ParseContext context = new ParseContext();
		parser.parse(input, textHandler, metadata, context);
		input.close();
		assertNull("Title", metadata.get("title"));
		assertEquals("Author", "Gael", metadata.get("Author"));
		log.info("content: " + textHandler.toString());
	}

	/**
	 * SAX ContentHandler that logs table content as Wiki markup.
	 *
	 */
	public class LoggingTableContentHandler extends DefaultHandler {

		private StringBuilder buffer;

		private boolean inCell;

		@Override
		public void startDocument() throws SAXException {
			buffer = new StringBuilder();
			inCell = false;
		}

		@Override
		public void startElement(String namespaceURI, String localName,
				String qName, Attributes atts) throws SAXException {
			if (localName.equalsIgnoreCase("td")) {
				buffer.append('|');
				inCell = true;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if (inCell)
				buffer.append(ch);
		}

		@Override
		public void endElement(String namespaceURI, String localName,
				String qName) throws SAXException {
			if (localName.equalsIgnoreCase("td"))
				inCell = false;
			if (localName.equalsIgnoreCase("tr"))
				buffer.append("|\n");
		}

		@Override
		public String toString() {
			return buffer.toString();
		}

	}

}