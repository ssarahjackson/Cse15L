# Lab Report 3
---

# Part 1 - Bugs
### Failure Inducing Input For `reversed` Method 
```Java
 @Test
  public void testReversedFail() {
    int[] input1 = {1,2,3,4,5};
    assertArrayEquals(new int[]{5,4,3,2,1}, ArrayExamples.reversed(input1));
  }
```
Any non-empty array should produce an error due to the nature of the bug. 
### Input For `reversed` Method That Does Not Produce Failure
```Java
   @Test
  public void testReversedPass() {
    int[] input1 = {};
    assertArrayEquals(new int[]{}, ArrayExamples.reversed(input1));
  }
```
### Symptom Screenshot (Output of JUnit Test)

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/8c915861-d039-45cd-9811-c4c4236bcc7d)

This JUnit failure output shows us that discrepancies are showing up as soon as index 0. Where we expect the value to be the last value of the original array, which is 5 in this example, we're actually seeing 0. This symptom doesn't show up when we test with an empty array, which implies the bug is probably that the `reversed` method is actually returning an empty array. 

### The Bug 

Before code:
```Java
static int[] reversed(int[] arr) {
    int[] newArray = new int[arr.length];
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = newArray[arr.length - i - 1];
    }
    return arr;
  }
```

After code: 
```Java
 static int[] reversed(int[] arr) {
    int[] newArray = new int[arr.length];
    for(int i = 0; i < arr.length; i += 1) {
      newArray[i] = arr[arr.length - i - 1]; 
    }
    return newArray; 
  }
```

The bug in the `reversed` method is because we're setting the passed in `arr` array to equal the values of `newArray` and then returning the `arr` whose values got messed up by being set to those of an empty array. However, `newArray` just got instantiated and is thus an empty array. To fix this I made sure to swap `arr` with `newArray` so that we're creating a new array variable and giving it the original array's values just in reverse order. Finally, I had to return the correct array which made everything work. 

# Part 2 - Researching Commands

### `-exec` option
 source: https://www.baeldung.com/linux/find-exec-command,
https://www.geeksforgeeks.org/find-command-in-linux-with-examples/#

example 1: 
```
$ find technical/911report -exec file {} \;
technical/911report: directory
technical/911report/chapter-1.txt: ASCII text, with very long lines (1022), with CRLF line terminators
technical/911report/chapter-10.txt: Unicode text, UTF-8 text, with CRLF line terminators
technical/911report/chapter-11.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-12.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-13.1.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-13.2.txt: Unicode text, UTF-8 text, with CRLF line terminators
technical/911report/chapter-13.3.txt: Unicode text, UTF-8 text, with CRLF line terminators
technical/911report/chapter-13.4.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-13.5.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-2.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-3.txt: Unicode text, UTF-8 text, with CRLF line terminators
technical/911report/chapter-5.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-6.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-7.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-8.txt: ASCII text, with CRLF line terminators
technical/911report/chapter-9.txt: ASCII text, with CRLF line terminators
technical/911report/preface.txt: ASCII text, with CRLF line terminators
```
The `-exec` option allows us to execute a command or shell script on the files being found with the `find` command. In this example, I combined `find` with the `file` command to get the file types of all the files in the directory I specified with find (`./technical/911report`). This could be really helpful when navigating a really large directory and you want to get more information about what it contains, or if you just remember the file's name but don't know anything about it. The `-file` command gives a surprisingly detailed description of what kind of file you're dealing with, and it becomes even more useful paired with the convenience of `-find`.

example 2:
```
$ find technical/911report -name chapter-1.txt -exec rm -i {} \;
rm: remove regular file 'technical/911report/chapter-1.txt'? Y
```
This time I paired `-find` with `-rm` which allowed me to remove the files I found with the specified name using `-name`. I think this would be really useful in removing a ton of files all at once that share a similar name or file extension, but I'm sure there are a lot of uses for this. I really enjoy how much added functionality this `-exec` command gave the `-find` one.

### `-size +/-Nc` option
source: https://www.geeksforgeeks.org/find-command-in-linux-with-examples/#

example 1: 
```
$ find technical/911report -size +100000c
technical/911report/chapter-12.txt
technical/911report/chapter-13.2.txt
technical/911report/chapter-13.3.txt
technical/911report/chapter-13.4.txt
technical/911report/chapter-13.5.txt
technical/911report/chapter-3.txt
technical/911report/chapter-5.txt
technical/911report/chapter-6.txt
technical/911report/chapter-7.txt
technical/911report/chapter-9.txt
```
In this example I used the `-size +Nc` command (where N=100000) to find every file in `./technical/911report` with >100000 characters. This would be helpful if you were trying to find some large datasets you stored in files and didn't care about the files with less data in them.
example 2:
```
$ find technical/911report -size -100000c
technical/911report
technical/911report/chapter-10.txt
technical/911report/chapter-11.txt
technical/911report/chapter-13.1.txt
technical/911report/chapter-2.txt
technical/911report/chapter-8.txt
technical/911report/preface.txt
```
Similar to the previous example, I used the `size` command to find anything in the same directory with <100000 characters. This basically has the exact opposite use case of the + version, where it will be helpful when you know you're trying to find small text files. 

### `-newer`/`-newermt` option
source: https://en.wikipedia.org/wiki/Find_(Unix)

example 1:
```
$ find technical/911report -newer technical/911report/chapter-2.txt
technical/911report
technical/911report/chapter-3.txt
technical/911report/chapter-5.txt
technical/911report/chapter-6.txt
technical/911report/chapter-7.txt
technical/911report/chapter-8.txt
technical/911report/chapter-9.txt
technical/911report/preface.txt
```
This time I used the `-newer` option followed by a file name (`technical/911report/chapter-2.txt` in this case) to find every file that was edited more recently than the `chapter-2.txt` file. If you've stored multiple iterations of a file, you could find the newer versions by using this option with the original iteration, or use the negation of this option on the newest one to see your previous iterations. 

example 2: 
```
$ find technical/911report -newermt 2023-11-03
technical/911report
technical/911report/chapter-10.txt
technical/911report/chapter-11.txt
technical/911report/chapter-12.txt
technical/911report/chapter-13.1.txt
technical/911report/chapter-13.2.txt
technical/911report/chapter-13.3.txt
technical/911report/chapter-13.4.txt
technical/911report/chapter-13.5.txt
technical/911report/chapter-2.txt
technical/911report/chapter-3.txt
technical/911report/chapter-5.txt
technical/911report/chapter-6.txt
technical/911report/chapter-7.txt
technical/911report/chapter-8.txt
technical/911report/chapter-9.txt
technical/911report/preface.txt
```
`-newermt` allows us to specify a time in our search for a file. In this example, I used it to search for files in the `technical/911report` directory newer than yesterday's date. Obviously, this gave me every file since I cloned it yesterday, but in a more realistic setting (like your work computer) this could be really helpful for finding something like .java files you were working on in a specific month (you can use the negation `-not` in conjunction with this one to specify a group of dates).

### `-perm` option
source: https://en.wikipedia.org/wiki/Find_(Unix)

example 1:
```
$ find technical/911report -perm 644
technical/911report/chapter-10.txt
technical/911report/chapter-11.txt
technical/911report/chapter-12.txt
technical/911report/chapter-13.1.txt
technical/911report/chapter-13.2.txt
technical/911report/chapter-13.3.txt
technical/911report/chapter-13.4.txt
technical/911report/chapter-13.5.txt
technical/911report/chapter-2.txt
technical/911report/chapter-3.txt
technical/911report/chapter-5.txt
technical/911report/chapter-6.txt
technical/911report/chapter-7.txt
technical/911report/chapter-8.txt
technical/911report/chapter-9.txt
technical/911report/preface.txt
```
The `-perm` option allows us to search for files based on the octal representation of file permissions that Unix uses. In this example, I used the "read write, read, read" representation which is the most common kind of permissions so every file in the `technical/911report` directory was shown to me. I could see using this to find all the normal files I have that I can access but not execute.

example 2:
```
$ find technical/911report -perm 764

```
This time I used the octal representation for "read write execute, read write, read", which returned nothing for the given directory. This would be a very useful command for sorting through the files you're able to execute from those you can't.
