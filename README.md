java-csv-parsers
================

Evaluation of several open source CSV file parsers written in java:

* [flatpack](http://flatpack.sourceforge.net/)
* [javacsv](http://sourceforge.net/projects/javacsv/)
* [opencsv](http://sourceforge.net/projects/opencsv/)
* [supercsv](http://supercsv.sourceforge.net/)

See JUnit tests in src/test/java/com/kairolia/parsers/csv/flatpack

Finally, we picked up opencsv.

Few experiments with [Apache Tika](http://tika.apache.org/) for content parsing and extraction of many file formats except CSV.

* Tika uses [Apache POI](http://poi.apache.org/) for parsing Office documents.
* The parsing extracts file meta data and data/structure using SAX events.
* See TikaParsersTest
