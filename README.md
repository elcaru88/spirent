# Phone number normalizer

### Environment needed

To run this project, you need Java 11 and maven installed.

### How to?

To get all the phone numbers on a folder, there is a shell script that
will take a folder path as argument to do the search. Here is an example:
./search.sh "/home/ccaruso/IdeaProjects/spirent/src/main/resources"

### Files requirement

The algorithm will search all the .txt files inside the folder recursively.
The phone numbers in those files should be in separated lines and have 
one of the accepted formats:

#### Phone number accepted formats
+7 812 number | +7 (495) number | +7812number | +7815 number | 1-814-numbe | (812) numbe | 812number | 812 number | 095-number | 123-4567 | 123-45-67 | 1234567

