# Java Grep App
## Introduction
This app is a Java implementation of the widely-used `grep` (global regular expression)
bash program; the app searches for a text pattern recursively in a given directory 
and outputs matched lines to a file.
There are two implementations for reading text from all the files under the 
given directory and writing the evaluated text to the given file:
1. Using data structures (Lists) 
2. Using Java 8 Streams API

Implementation (1) loads all the text (input and output) into Lists.  
Implementation (2) pipes all the text (input and output) through streams.  
Both implementations used I/O buffers for the actual reading and writing.

## Usage
The program takes 3 arguments:
- regex: a special text string for describing a search pattern
- rootPath: root directory path
- outFile: output file name

**USAGE:** 
```bash
JavaGrep regex rootPath outFile  
```
Similar to:  
```bash
egrep -r {regex} {rootPath} > {outFile}
```
**EXAMPLE:** 
```bash
java -jar target/grep-1.0-SNAPSHOT.jar .*Romeo.*Juliet.* ./data ./out/grep.out
```
OR
```bash
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrep .*Romeo.*Juliet.* ./data ./out/grep.out
```
OR use docker image that works as the app
```bash
# Pull docker image from docker hub
docker pull onehoax/grep
# Verify your image
docker image ls | grep "grep"
# Run the container
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log onehoax/grep .*Romeo.*Juliet.* /data /log/grep.out
```

## Pseudocode
**For Implementation (1):**
```bash
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```
**For Implementation (2):**
```bash
files = listFilesRecursively(rootDir)
flattenedFiles = files.flatMap
flattenedLines = flattenedFiles.flatMap
matchedLines = flattenedLines.filter(containsPattern(line))
writeToFile(matchedLines)
```

## Performance Issue
We often want to process big datasets with a limited JVM max. heap size
(~1.5GB for a 32-bit JVM); if, for example, we want to process a file 
that is bigger than our JVM max. heap size, the app will crash using implementation (1) because it would need to load all the data from the file into data structures
residing in its heap memory. 
The same scenario is possible with implementation (2) because Java 8 Streams are not 
data structures, they are simply pipelines that stream data; only the data item
being currently operated on is held in memory.  
Performance is further improved by using I/O buffers for reading and writing
from/to files, which perform those operations in chunks rather than all at once.  

**Note:** Even though implementation (1) also uses buffers for reading and writing, it
still has to load all the data into data structures.

## Improvement
1. Support for non-Unix-like machines
2. Better OOP implementation
3. More robust testing