<h1>LetterBoxedSolver</h1>
<p>
LetterBoxedSovler is a command line Java application solving NYT Letter Boxed puzzle.
</p>

<h2>How it works</h2>
<p>
LetterBoxedSolver tries to find the best possible solution for the Letter Boxed puzzle. 
It uses business rule engine working on an external dictionary of almost 60k English words. 
The solution is considered to be valid if it contains no more than 3 words, 
and each solution with fewer words is considered to be better than the one with more words. 
Application always returns all best solutions or none if it couldn't find any valid.
</p>
<p>
For more information about NYT Letter Boxed puzzle and how to solve it, see links section below.
</p>

<h2>How to use it</h2>
<p>
Run an application in console and input information about puzzle configuration. 
Each side of a puzzle is called a wall, and each wall contains one ore more letters. 
Input letters (and only letters) as a continuous characters for each wall and confirm with <Enter>. 
Press <Enter> without any characters to start solver.
When solver finishes, all solutions found are printed on console.
</p>

<h2>Configuration</h2>
<ul>
<li>All UI texts and messages are kept in <b>ui.properties</b> file</li>
<li>Link to external online dictionary in kept in <b>application.properties</b> file.</li>
</ul>

<h2>Libraries and technologies</h2>
<ul>
<li>Java 17</li>
<li>Spring Boot</li>
<li>Gradle build tool</li>
<li>Drools business rule engine</li>
<li>Groovy/Spock unit testing framework</li>
</ul>

<h2>Requirements</h2>
<ul>
<li>Java 17 JRE</li>
</ul>

<h2>Additional Links</h2>
<p>
To play NYT Letter Boxed and/or see more information about this puzzle go here:
</p>
<ul>
<li><a href="https://www.nytimes.com/puzzles/letter-boxed">NYT Letter Boxed puzzle</a></li>
</ul>
<p>
If you are looking for English words dictionary used by LetterBoxedSolver, go here:
</p>
<ul>
<li><a href="http://www.mieliestronk.com/corncob_caps.txt">Mieliestronk English words dictionary</a></li>
</ul>
