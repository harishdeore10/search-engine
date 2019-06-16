Search Engine : 
Simple command line driven text search engine in Java 8.

Table of content
a) Introduction
b) Technologies
c) Set up
d) Version
e) Author


Introduction :
Simple command line driven text search engine in Java 8.This read all the text files in the given directory,
building an ​in-memory​ representation of the files and their contents, and then give a command prompt at which
interactive searches can be performed.

An example session might look like:

$ java -jar SimpleSearch.jar Searcher /foo/bar
14 files read in directory /foo/bar
search> to be or not to be
file1.txt:100%
file2.txt:90%
search>
search> cats
no matches found
search> :quit
$

The search should take the words given on the prompt and return a list of the top 10 (maximum)
matching filenames in rank order, giving the rank score against each match.


Technologies:

a) Java 8
b) Apache Maven
c) jUnit


Set Up:

To compile and run these programs, you only need JDK 8 & Apache Maven installed.

To compile and run everything, the command is:

"mvn clean install"

To execute program, use below command

java -jar searchEngine-1.0-SNAPSHOT.jar src/test/resources

Version:
1.0-SNAPSHOT

Author :
Harish Deore
