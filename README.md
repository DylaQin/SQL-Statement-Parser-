# SQL-Statement-Parser-
Using JAVA to develop a SQL statement parser and maintain a database file system.
### Query statement
This section provides more detail regarding the implementation of some of particular aspects of the query language.

#### SQL keywords
Convention has it that query examples are typically shown with uppercase keywords (to differentiate them from identifiers and literals). However, SQL keywords are in fact case insensitive, so select * from people; is equivalent to SELECT * FROM people;. This is true for all keywords in the BNF (including TRUE/FALSE, AND/OR, LIKE etc.) In addition to this, all SQL keywords are reserved words, therefore you should not allow them to be used as database/table/attribute names.

#### Comparisons
It is not necessary to implement a datatype system within the database - you can just store everything as simple text strings. You should perform >, =>, <, =< comparisons wherever it is possible to do something sensible (e.g. floats, ints, strings etc). In situations where no appropriate comparison is possible (e.g. TRUE > FALSE) just return no data in the results table. The LIKE comparator is just for use with strings and provides a simple substring matcher (NOT the full SQL LIKE operator with % or wildcarding).

#### Response tables
The order of values returned by a SELECT should be the same as specified in the query (e.g. SELECT name, id FROM marks would return the name column first, followed by the id column). Note that SELECT * should return the values in the order that they are stored in the table. The table returned by a JOIN should contain attribute names in the form OriginalTableName.AttributeName (see the query transcript for specific examples). This is so the user can determine which attributes came from which tables (as well as coping with the situation where two tables have attributes of the same name). The joined table should NOT contain the ID columns from the original tables, but rather should include a new ID column containing new sequentially generated IDs.

