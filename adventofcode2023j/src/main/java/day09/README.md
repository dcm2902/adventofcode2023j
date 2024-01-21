# Advent of Code 2023 - Day 09 Solution
## Introduction
Welcome to my solution for Day 09 of the Advent of Code 2023 (see below original problem description).  
This puzzle, like many others in the series, is divided into two parts: Part 1 and Part 2. My approach for this day's challenge is to provide a unified solution that effectively addresses both parts of the puzzle.

In this repository, you will find the main solution that includes four different executions based on a combination of two parameters: ElabType and the input file. These parameters are crucial in understanding how the solution adapts to the different requirements of each part of the puzzle.

### Solution Parameters
__ElabType Parameter__  

__ElabType=ForwardWise:__  
This setting is designed to solve the problem as outlined in Day 09 Part 1.  
__ElabType=BackwardWise:__  
This configuration is tailored for the problem presented in Day 09 Part 2.  
#### Input Files:  
__adventofcode09Input_example.txt:__  
This file contains only the example provided in the puzzle description. It's great for initial testing and understanding the problem.  
__adventofcode09Input.txt:__  
This file includes the actual input for the problem. It's used to run the solution against the real challenge data.

## Running the Solution
To execute the solution, you can adjust the ElabType parameter and choose the appropriate input file based on what aspect of the problem you're focusing on. The combination of these parameters ensures that the same codebase efficiently tackles both the initial and advanced aspects of Day 09's challenge.

Feel free to explore the solution and see how the same logic is applied effectively to both parts of the puzzle, thanks to the flexible parameterization and input handling.  

# Problem description
--- Day 9: Mirage Maintenance ---  
[https://adventofcode.com/2023/day/9](https://adventofcode.com/2023/day/9)  
You ride the camel through the sandstorm and stop where the ghost's maps told you to stop. The sandstorm subsequently subsides, somehow seeing you standing at an oasis!

The camel goes to get some water and you stretch your neck. As you look up, you discover what must be yet another giant floating island, this one made of metal! That must be where the parts to fix the sand machines come from.

There's even a hang glider partially buried in the sand here; once the sun rises and heats up the sand, you might be able to use the glider and the hot air to get all the way up to the metal island!

While you wait for the sun to rise, you admire the oasis hidden here in the middle of Desert Island. It must have a delicate ecosystem; you might as well take some ecological readings while you wait. Maybe you can report any environmental instabilities you find to someone so the oasis can be around for the next sandstorm-worn traveler.

You pull out your handy Oasis And Sand Instability Sensor and analyze your surroundings. The OASIS produces a report of many values and how they are changing over time (your puzzle input). Each line in the report contains the history of a single value. For example:

<pre><code>0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45
</code></pre>

To best protect the oasis, your environmental report should include a prediction of the next value in each history. To do this, start by making a new sequence from the difference at each step of your history. If that sequence is not all zeroes, repeat this process, using the sequence you just generated as the input sequence. Once all of the values in your latest sequence are zeroes, you can extrapolate what the next value of the original history should be.

In the above dataset, the first history is 0 3 6 9 12 15. Because the values increase by 3 each step, the first sequence of differences that you generate will be 3 3 3 3 3. Note that this sequence has one fewer value than the input sequence because at each step it considers two numbers from the input. Since these values aren't all zero, repeat the process: the values differ by 0 at each step, so the next sequence is 0 0 0 0. This means you have enough information to extrapolate the history! Visually, these sequences can be arranged like this:

<pre><code>0   3   6   9  12  15
  3   3   3   3   3
    0   0   0   0
</code></pre>
    
To extrapolate, start by adding a new zero to the end of your list of zeroes; because the zeroes represent differences between the two values above them, this also means there is now a placeholder in every sequence above it:

<pre><code>0   3   6   9  12  15   <em>B</em>
  3   3   3   3   3   <em>A</em>
    0   0   0   0   <em>0</em>
</code></pre>
    
You can then start filling in placeholders from the bottom up. A needs to be the result of increasing 3 (the value to its left) by 0 (the value below it); this means A must be 3:

<pre><code>0   3   6   9  12  15   B
  3   3   3   3   <em>3</em>   <em>3</em>
    0   0   0   0   <em>0</em>
</code></pre>
    
Finally, you can fill in B, which needs to be the result of increasing 15 (the value to its left) by 3 (the value below it), or 18:

<pre><code>0   3   6   9  12  <em>15</em>  <em>18</em>
  3   3   3   3   3   <em>3</em>
    0   0   0   0   0
</code></pre>
    
So, the next value of the first history is 18.

Finding all-zero differences for the second history requires an additional sequence:

<pre><code>1   3   6  10  15  21
  2   3   4   5   6
    1   1   1   1
      0   0   0
</code></pre>

Then, following the same process as before, work out the next value in each sequence from the bottom up:

<pre><code>1   3   6  10  15  21  <em>28</em>
  2   3   4   5   6   <em>7</em>
    1   1   1   1   <em>1</em>
      0   0   0   <em>0</em>
</code></pre>

So, the next value of the second history is 28.

The third history requires even more sequences, but its next value can be found the same way:

<pre><code>10  13  16  21  30  45  <em>68</em>
   3   3   5   9  15  <em>23</em>
     0   2   4   6   <em>8</em>
       2   2   2   <em>2</em>
         0   0   <em>0</em>
</code></pre>

So, the next value of the third history is 68.

If you find the next value for each history in this example and add them together, you get 114.

Analyze your OASIS report and extrapolate the next value for each history. What is the sum of these extrapolated values?

Your puzzle answer was 1884768153.

--- Part Two ---
Of course, it would be nice to have even more history included in your report. Surely it's safe to just extrapolate backwards as well, right?

For each history, repeat the process of finding differences until the sequence of differences is entirely zero. Then, rather than adding a zero to the end and filling in the next values of each previous sequence, you should instead add a zero to the beginning of your sequence of zeroes, then fill in new first values for each previous sequence.

In particular, here is what the third example history looks like when extrapolating back in time:

<pre><code><em>5</em>  10  13  16  21  30  45
  <em>5</em>   3   3   5   9  15
   <em>-2</em>   0   2   4   6
      <em>2</em>   2   2   2
        <em>0</em>   0   0
</code></pre>
        
Adding the new values on the left side of each sequence from bottom to top eventually reveals the new left-most history value: 5.

Doing this for the remaining example data above results in previous values of -3 for the first history and 0 for the second history. Adding all three new values together produces 2.

Analyze your OASIS report again, this time extrapolating the previous value for each history. What is the sum of these extrapolated values?

Your puzzle answer was 1031.
If you still want to see it, you can <a href="adventofcode.com/2023/day/9/input" target="_blank">get your puzzle input</a>

Both parts of this puzzle are complete! They provide two gold stars: **  
