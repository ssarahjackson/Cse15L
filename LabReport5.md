# Lab Report 5
---

# Part 1 – Debugging Scenario

### Student:
Hello TA, I have been having some problem with my `list-examples-grader.sh` grading script. I am attaching a screenshot where I run the script on the provided corrected `ListExamples.java` that should have no errors or trouble compiling. Do you know what this error could be caused by? This error makes it sound like it can't find the class `ListExamples` or the implemented `StringChecker`, or something is the matter with them, but since this is the correct version I believe it's the former.

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/ca2df75e-035d-4b7a-92d0-bc12bcb1cbc4)

### TA:
Your intuition on what the JUnit error output is trying to tell you seems smart. You may want to try adding a couple commands to get it to give you some more clarifying output. `Echo`ing an error code (which is denoted by `$?`in bash) and also the `pwd` could help you figure out if something is going wrong while compiling. After you fix the cause of this symptom, you may want to add some code to check for legitimate errors in the Java code that could cause something similar to happen, provide the user with the error code, and exit since there's no reason to run tests on code that isn't compiling.

### Student:
Okay thanks, I'll try that!

.

.

.

(couple minutes later)

I added those `echo` commands you suggested right after the line where I compiled the Java files and it turns out my working directory wasn't correct (pictured in screenshot), so when I was trying to compile the Java files I was only compiling the `TestListExamples.java` that was outside of the `grading-area` directory. This also means I was running the wrong `TestListExamples.java` (the one in the home directory), so it didn't have access to any of the student's provided files, like the `ListExamples.java` file that `TestListExamples.java` is dependent on. As you hinted at, this bug created a symptom that would also show up when a different bug occured, i.e. a compilation error in the student's code, so while I did address the original bug by adding a `cd grading-area` in the appropriate line, I also added an `if` statement that checked if the error code was anything other than 0, provided the user the error code, and exited the script. 
![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/53d8d15d-8a14-4cf4-b1d6-00f81d2713a4)


# Setup Information:

### File and directory structure:

Before running script:

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/70db14b6-3746-490e-baf6-c4183f1b14fd)

There's a nested `grading-area` directory that was part of the bug, since the student hadn't made this the working directory.


After:

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/7eba4aa0-6524-467b-9c1a-c603d56c7aa6)

### Original code:

Bash: 

`grade.sh`-

```
CPATH=".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar"
rm -rf student-submission
rm -rf grading-area
rm -rf test-output.txt
mkdir grading-area

git clone $1 student-submission
files=`find student-submission`
count=0
for file in $files
do
    if [[ -f $file && $file == 'student-submission/ListExamples.java' ]]
    then
        echo 'Finished cloning'
        count=$(($count+1))
    fi
done
if [[ $count == 0 ]]
then
    echo 'Improper file submission'
    exit
fi

cp -r student-submission/*.java grading-area
rm -rf student-submission
cp -r TestListExamples.java grading-area
cp -r lib grading-area


javac -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" *.java 



java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TestListExamples > test-output.txt
line=`grep 'Tests run:' test-output.txt`
echo $line

```
Java (provided in Lab 6 and unchanged as there were no bugs):

`ListExamples.java`-

```Java
import java.util.ArrayList;
import java.util.List;

interface StringChecker { boolean checkString(String s); }

class ListExamples {

  // Returns a new list that has all the elements of the input list for which
  // the StringChecker returns true, and not the elements that return false, in
  // the same order they appeared in the input list;
  static List<String> filter(List<String> list, StringChecker sc) {
    List<String> result = new ArrayList<>();
    for(String s: list) {
      if(sc.checkString(s)) {
        result.add(s);
      }
    }
    return result;
  }


  // Takes two sorted list of strings (so "a" appears before "b" and so on),
  // and return a new list that has all the strings in both list in sorted order.
  static List<String> merge(List<String> list1, List<String> list2) {
    List<String> result = new ArrayList<>();
    int index1 = 0, index2 = 0;
    while(index1 < list1.size() && index2 < list2.size()) {
      if(list1.get(index1).compareTo(list2.get(index2)) < 0) {
        result.add(list1.get(index1));
        index1 += 1;
      }
      else {
        result.add(list2.get(index2));
        index2 += 1;
      }
    }
    while(index1 < list1.size()) {
      result.add(list1.get(index1));
      index1 += 1;
    }
    while(index2 < list2.size()) {
      result.add(list2.get(index2));
      index2 += 1;
    }
    return result;
  }
```

`ListTestExamples.java`-

```Java
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }
}
```
### Command line to elicit symptom:

`bash grade.sh https://github.com/ucsd-cse15l-f22/list-methods-corrected`

### Description of fix:

To fix the bug we have to either change directories to the `grading-area` or start every reference to a file within it with `./grading-area`. It's much easier to just add a `cd gradng-area` above line 31 where we compile the files. Although this was all that was required to fix the initial bug, we also want to handle compilation errors and provide useful feedback to the student when these occur which is why I added 

```
code=$?
if [[ $code != 0 ]]
then
    echo "error compiling: exit code $code"
    exit
fi
```
directly after compiling.

Updated Bash: 

`grade.sh`-

```
CPATH=".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar"
rm -rf student-submission
rm -rf grading-area
rm -rf test-output.txt
mkdir grading-area

git clone $1 student-submission
files=`find student-submission`
count=0
for file in $files
do
    if [[ -f $file && $file == 'student-submission/ListExamples.java' ]]
    then
        echo 'Finished cloning'
        count=$(($count+1))
    fi
done
if [[ $count == 0 ]]
then
    echo 'Improper file submission'
    exit
fi

cp -r student-submission/*.java grading-area
rm -rf student-submission
cp -r TestListExamples.java grading-area
cp -r lib grading-area

cd grading-area

javac -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" *.java 
code=$?
if [[ $code != 0 ]]
then
    echo "error compiling: exit code $code"
    exit
fi


java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TestListExamples > test-output.txt
line=`grep 'Tests run:' test-output.txt`
echo $line
```

# Part 2 - Reflection 
I had never used Java debugger before, so I found that lab really useful. It was a lot easier for me to pinpoint where an error was occuring, and I enjoyed that you could use the `locals` command to get the current values of all the local variables at the line where the code stopped, either due to a set breakpoint or error in running. It made me reflect on all the times I wrote a lot of `System.out.println` statements in my code and then kept forgetting to remove them since it was a large project and I couldn't figure out where the bug was.
