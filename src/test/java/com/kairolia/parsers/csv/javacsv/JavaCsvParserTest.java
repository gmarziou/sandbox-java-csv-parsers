/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv.javacsv;

import static org.junit.Assert.assertTrue;
import static org.supercsv.prefs.CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.supercsv.io.CsvListReader;
import org.xml.sax.SAXException;

import com.kairolia.parsers.csv.CsvParserBaseTest;

/**
 * @author Gael Marziou
 *
 */
public class JavaCsvParserTest extends CsvParserBaseTest {


	@Test
	public void testCsvParser() throws IOException, SAXException {
		CsvListReader inFile = new CsvListReader(reader,
				EXCEL_NORTH_EUROPE_PREFERENCE);
		try {
			final String[] header = inFile.getCSVHeader(true);
			assertHeader(header);

			List<String> values;
			while ((values = inFile.read()) != null) {
				assertTrue(values.size() >= 4);
				log.info(values.toString());
			}
		}
		finally {
			inFile.close();
		}
	}

}