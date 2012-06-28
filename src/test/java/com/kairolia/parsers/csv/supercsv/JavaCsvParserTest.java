/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv.supercsv;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.csvreader.CsvReader;
import com.kairolia.parsers.csv.CsvParserBaseTest;

/**
 * @author Gael Marziou
 *
 */
public class JavaCsvParserTest extends CsvParserBaseTest {


	@Test
	public void testCsvParser() throws IOException, SAXException, TikaException {
		CsvReader parser = new CsvReader(reader);
		parser.setDelimiter(FRENCH_DELIMITER);

		parser.readHeaders();
		String[] header = parser.getHeaders();
		assertHeader(header);

		try {
			while (parser.readRecord()) {
				String[] values = parser.getValues();
				assertTrue(values.length >= 4);
				String msg = values[0];
				for (int i = 1; i < header.length; i++) {
					msg = msg + ", " + values[i];
				}
				log.info(msg);
			}
		}
		finally {
			parser.close();
		}
	}

}