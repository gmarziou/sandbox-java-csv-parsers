/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv.opencsv;

import static au.com.bytecode.opencsv.CSVParser.DEFAULT_ESCAPE_CHARACTER;
import static au.com.bytecode.opencsv.CSVParser.DEFAULT_QUOTE_CHARACTER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import au.com.bytecode.opencsv.CSVReader;

import com.kairolia.parsers.csv.CsvParserBaseTest;

/**
 * @author Gael Marziou
 *
 */
public class OpenCsvParserTest extends CsvParserBaseTest {

	@Test
	public void testCsvParser() throws IOException, SAXException {

		CSVReader csvReader = new CSVReader(reader, FRENCH_DELIMITER,
				DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);

		List<String[]> entries = csvReader.readAll();
		final String[] header = entries.get(0);
		assertHeader(header);

		assertEquals(3, entries.size());

		for (int i = 1; i < entries.size(); i++) {
			String[] row = entries.get(i);
			assertTrue(row.length >= 4);
			log.info(row.toString());
		}
	}

}