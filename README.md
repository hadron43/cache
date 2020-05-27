# Cache Project: Computer Organisation CSE112

This repo implements cache working simulation project files.

## Problem Statement

A cache of size S with CL as the number of cache lines and block size B is to be built. S, CL, and B are in
powers of 2. Write a program that allows loading into cache and searching cache using:  

1. Direct mapping
2. Associative memory
3. n-way set associative memory where n is a power of 2.

## General Instructions

*Direct.java* implements direct mapping standalone one level cache.  
*Associative.java* implements fully associative standalone one level cache.  
*SetAssociative.java* implements N way Set Associative standalone one level cache.  

To run any of these files, use the below given command:  
**Note: You must have JVM installed on your machine to run this**  

```bash
javac FileName.java
java FileName
```

Alternatively, you can also do:
```bash
java FileName.java
```

The program will ask for the parameters to create cache memory. Below is the definition for each of the parameters used.  

1. S  : It is the size of the cache (in bytes) to be built.
2. CL : It is the number of cache lines (or the number of blocks) in the cache.
3. B  : It is the number of words in a block. Please note, it is not the same as memory occupied by the block.
4. N  : It is the number of blocks in a set.  
***Please note that this parameter is only applicable for n-Way set associative cache memory.***

## Basic Instructions to be Used during Program execution

Four instructions are supported by these program:

1. **Print** : It prints the present status of the cache memory.

```bash
print
```

![Print Format](https://github.com/hadron43/cache/blob/master/samples/Print.png)

2. **Read** : This instruction is used to read something from the cache memory.

```bash
read <targetAddress>
```

Output Format: *Adresss : Value*
3. **Write** : This instruction is used to write something to the cache memory.

```bash
write <targetAddress> <value>
```

4. **Exit** : This print the final status of the cache and quits the program.

```bash
exit
```

## Samples

1. ***Direct.java*** :  
![Sampe Test for Direct.java](https://github.com/hadron43/cache/blob/master/samples/Direct.png)

2. ***Associative.java*** :  
![Sample Test for Associative.java](https://github.com/hadron43/cache/blob/master/samples/Associative.png)

3. ***SetAssociative.java*** :  
![Sample Test for SetAssociative.java](https://github.com/hadron43/cache/blob/master/samples/SetAssociative.png)
