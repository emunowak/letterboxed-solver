# LetterBoxedSolver

LetterBoxedSovler is a command line Java application solving NYT Letter Boxed puzzle.

###How it works
LetterBoxedSolver tries to find the best possible solution for the Letter Boxed puzzle. 
It uses business rule engine working on an external dictionary of almost 60k English words. 
The solution is considered to be valid if it contains no more than 3 words, 
and each solution with fewer words is considered to be better than the one with more words. 
Application always returns all best solutions or none if it couldn't find any valid.

For more information about NYT Letter Boxed puzzle and how to solve it, see links section below.

###How to use it
Run an application in console and input information about puzzle configuration. 
Each side of a puzzle is called a wall, and each wall contains one ore more letters. 
Input letters (and only letters) as a continuous characters for each wall and confirm with <Enter>. 
Press <Enter> without any characters to start solver.
When solver finishes, all solutions found are printed on console.

###Configuration
* All UI texts and messages are kept in **ui.properties** file
* Link to external online dictionary in kept in **application.properties** file.

###Libraries and technologies
* Java 17
* Spring Boot
* Gradle build tool
* Drools business rule engine
* Groovy/Spock unit testing framework

###Requirements
* Java 17 JRE

### Additional Links
To play NYT Letter Boxed and/or see more information about this puzzle go here:
* [NYT Letter Boxed puzzle](https://www.nytimes.com/puzzles/letter-boxed)

If you are looking for English words dictionary used by LetterBoxedSolver, go here:
* [Mieliestronk English words dictionary](http://www.mieliestronk.com/corncob_caps.txt)