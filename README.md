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
1. I found out about 'interface, extend, and implements'. An interface is a class that does not have method bodies and must be implemented in its subclasses. Variable declarations in an interface have the final attribute, making them absolute. The output is final unless overridden.
An interface is a technique for polymorphism.
The implements keyword is used to implement more than one interface in a subclass.
Reference: https://randy-arba.medium.com/interface-java-a0a8d53e6cb7
Since this task involves similar functions but with two different methods (array and linked list), using an interface can simplify and streamline the code.
2. After completing the main program, I gradually added input validation to enhance the code. Here, I started testing the program to see if it runs correctly. If not, I will fix it menu by menu. This approach is easier than my previous method because using an interface makes the code more organized. I tested whether the data structure was correct, then checked if add, delete, update, mark completed, undo, and exit worked properly. During testing, I found many issues. For example:
- The predefined tasks did not appear because I mistakenly input the wrong data structure.
- After deleting a task and then performing undo, the task count increased, but the deleted tasks did not reappear.
- And many other issues.
I may not be able to fix every validation issue, but I am quite satisfied with what I have accomplished—especially because I discovered the interface method. Of course, my understanding wouldn't have been possible without ChatGPT, which allowed me to ask questions and helped me grasp the concept.

## Program Workflow

At the beginning of the program, the user will be asked whether they have a lot of tasks for today. If they do, the program will use a linked list; otherwise, it will use an array with predefined tasks (basic tasks). <br>
After selecting an option and the program returns the corresponding data structure, it will display the task list and the main menu, which consists of the following options: Add Task, Delete Task, Update Task, Mark Completed Task, Undo, and Exit (using numerical input 1-6). <br>
### Menu Explanation
1. Add Task:
- If using an array, a task will be added to the array as long as the number of tasks does not exceed the array's size. If the array is full, the add task request will be rejected.
- If using a linked list, there is no limit to the number of tasks that can be added.
2. Delete Task:
- This option is only available if the task list is not empty and the selected task has not been marked as completed.
- The user will be asked to enter the task number they want to delete.
3. Update Task:
- This option is only available if the task list is not empty and the selected task has not been marked as completed.
- The user will be asked to enter the task number they want to update, along with the new task description.
4. Mark Completed Task:
- This option is only available if the task list is not empty and the task has not yet been marked as completed (-Done).
- The user will be asked to enter the task number they want to mark as completed.
5. Undo:
- This option is only available if the task list is not empty.
- If an action has been performed previously, the undo function will restore the state before the last action was taken.
6. Exit:
- If all tasks have been marked as completed, the program will display:
"Thank you for using Daily To-Do List!" and "Exiting Program . . .", then the program will terminate.
- If there are unfinished tasks, the user will be asked whether they want to give up.
If they choose yes, a warning will be displayed stating that all changes will be lost. If they confirm, the program will terminate.
If they choose no, the program will return to the main menu.