# Daily Task Manager in Java
Developing Java CLI application called Daily Task Manager to demonstrate data structures (Array, Stack, and LinkedList).

## Approach to implementing each data structure
<ul> Array-based Approach
<li> Used a fixed-size array to store tasks | String[]</li>
<li> Used boolean array to track completed tasks | boolean[]</li>
<li> Used stack to store tasks for undo feature | Stack String[] </li>
</ul>

<ul>LinkedList-based Approach
<li> Used a linkedlist for dynamic task storage | LinkedList String </li>
<li> Used a linkedlist to track completed tasks | LinkedList boolean </li>
<li> Used a linkedlist to store tasks for undo feature | Stack Linkedlist String</li>


## Challenge Encountered and Solution


## Program Workflow

At the beginning of the program, the user will be asked whether they have a lot of tasks for today. If they do, the program will use a linked list; otherwise, it will use an array with predefined tasks (basic tasks).
After selecting an option and the program returns the corresponding data structure, it will display the task list and the main menu, which consists of the following options: Add Task, Delete Task, Update Task, Mark Completed Task, Undo, and Exit (using numerical input 1-6).