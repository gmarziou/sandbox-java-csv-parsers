/*
 *  Copyright (c) 2010 Kairolia
 */
package com.kairolia.parsers.csv.supercsv;

import com.kairolia.parsers.csv.CsvParserBaseTest;
import org.junit.Test;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.text.DecimalFormatSymbols.getInstance;
import static org.supercsv.prefs.CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;

/**
 * @author Gael Marziou
 */
public class SuperCsvParserTest extends CsvParserBaseTest {


    private static final DecimalFormatSymbols FRENCH_DECIMAL_SYMBOL = getInstance(Locale.FRANCE);

    @Test
    public void testCsvParser() throws IOException, SAXException {
        CsvBeanReader beanReader = new CsvBeanReader(reader, EXCEL_NORTH_EUROPE_PREFERENCE);

        String[] header = beanReader.getHeader(true);
        assertHeader(header);

        final CellProcessor[] processors = new CellProcessor[]{
                new NotNull(), // Brand
                new NotNull(), // Model
                new NotNull(new ParseEnum(Rubber.Color.class, true)), // Color
                new ParseBigDecimal(FRENCH_DECIMAL_SYMBOL), // Thickness
                new ParseBigDecimal(FRENCH_DECIMAL_SYMBOL), // Price
                new Optional(new ParseDate("yyyy-MM-dd"))  // Date
        };
        try {
            Rubber rubber;
            while ((rubber = beanReader.read(Rubber.class, header, processors)) != null) {
                log.info(String.format("lineNo=%s, rowNo=%s, rubber=%s", beanReader.getLineNumber(),
                        beanReader.getRowNumber(), rubber));
            }
        } finally {
            beanReader.close();
        }
    }

}