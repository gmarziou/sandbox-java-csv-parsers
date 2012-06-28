/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv.flatpack;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import net.sf.flatpack.DataSet;
import net.sf.flatpack.DefaultParserFactory;
import net.sf.flatpack.Parser;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.kairolia.parsers.csv.CsvParserBaseTest;

/**
 * @author Gael Marziou
 *
 */
public class FlatPackParserTest extends CsvParserBaseTest {

	@Test
	public void testCsvParser() throws IOException, SAXException {
		Parser parser = DefaultParserFactory.getInstance().newDelimitedParser(
				reader, FRENCH_DELIMITER, '"');

		DataSet entries = parser.parse();
		assertEquals(0, entries.getErrorCount());
		assertEquals(2, entries.getRowCount());
		String[] header = entries.getColumns();
		assertHeader(header);

		while (entries.next()) {
			String msg = entries.getString(header[0]);
			for (int i = 1; i < header.length; i++) {
				msg = msg + ", " + entries.getString(header[i]);
			}
			log.info(msg);
		}

	}

}