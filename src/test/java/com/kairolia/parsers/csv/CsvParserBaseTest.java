/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author Gael Marziou
 *
 */
public class CsvParserBaseTest {

	protected static final String HEADER_5 = "Date";
	protected static final String HEADER_4 = "Price";
	protected static final String HEADER_3 = "Thickness";
	protected static final String HEADER_2 = "Color";
	protected static final String HEADER_1 = "Model";
	protected static final String HEADER_0 = "Brand";
	protected static final char FRENCH_DELIMITER = ';';
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected InputStream input;
	protected Reader reader;

	public CsvParserBaseTest() {
		super();
	}

	protected void assertHeader(final String[] header) {
		assertEquals(6, header.length);
		assertEquals(HEADER_0, header[0]);
		assertEquals(HEADER_1, header[1]);
		assertEquals(HEADER_2, header[2]);
		assertEquals(HEADER_3, header[3]);
		assertEquals(HEADER_4, header[4]);
		assertEquals(HEADER_5, header[5]);
	}

	@Before
	public void init() throws IOException {
		ClassPathResource resource = new ClassPathResource("SampleDocument.csv");
		input = resource.getInputStream();
		reader = new InputStreamReader(input, "utf-8");
	}

	@After
	public void reset() throws IOException {
		if (input != null)
			input.close();
		if (reader != null)
			reader.close();
	}

}