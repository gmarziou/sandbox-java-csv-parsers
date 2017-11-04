java-csv-parsers
================

Evaluation of several open source CSV file parsers written in java:

See JUnit tests in respective modules.

Initially in 2012, we evaluated [javacsv](http://sourceforge.net/projects/javacsv/), [flatpack](http://flatpack.sourceforge.net/), [opencsv](http://sourceforge.net/projects/opencsv/) and [supercsv](https://github.com/super-csv/super-csv) and chose opencsv.

In 2017, I updated this project to catch up with latest versions and removed javacsv and flatpack and I chose SuperCSV over OpenCSV because:
- SuperCSV does not have dependencies compared to OpenCSV which requires commons-beanutils, commons-lang3 and commons-text.
- SuperCSV supports Enums

Few experiments with [Apache Tika](http://tika.apache.org/) for content parsing and extraction of many file formats.
In version 0.7 (in 2012), it did not support CSV, now it does but I haven't tested it.

* Tika uses [Apache POI](http://poi.apache.org/) for parsing Office documents.
* The parsing extracts file meta data and data/structure using SAX events.
* See [com.kairolia.parsers.tika.ParserTest](src/test/java/com/kairolia/parsers/tika/ParserTest.java)

