/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv.opencsv;

import com.kairolia.parsers.csv.CsvParserBaseTest;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Gael Marziou
 */
public class OpenCsvParserTest extends CsvParserBaseTest {

    @Test
    public void testCsvParser() throws IOException, SAXException {

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(FRENCH_DELIMITER)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .build();

        List<String[]> entries = csvReader.readAll();
        final String[] header = entries.get(0);
        assertHeader(header);

        assertEquals(3, entries.size());

        for (int i = 1; i < entries.size(); i++) {
            String[] row = entries.get(i);
            assertTrue(row.length >= 4);
            log.info(Arrays.toString(row));
        }
    }

    /**
     * Tes CSV parsing an annotated bean.
     */
    @Test
    public void testBeanCsvParser() throws IOException, SAXException {

        CsvToBean<Rubber> csvToBean = new CsvToBeanBuilder<Rubber>(reader)
                .withSeparator(FRENCH_DELIMITER)
                .withType(Rubber.class).build();

        List<Rubber> rubbers = csvToBean.parse();

        assertEquals(2, rubbers.size());

        for (Rubber rubber : rubbers) {
            log.info(rubber.toString());
        }
    }


}