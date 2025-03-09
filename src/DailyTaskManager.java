import java.util.Stack;
import java.util.LinkedList;
import java.util.Scanner;

interface TaskStorage { //Declare method
    void addTask(String task); //method for add Task to tasklist
    void deleteTask(int index); //method for delete Task in tasklist
    void updateTask(int index, String newTask); //method for update Task in tasklist
    void printTask(); //print all task in tasklist
    int countTasks(); //count available task
    void undo(); //undo the last task
}

class ArrayTask implements TaskStorage { //method implement with array
    private String[] tasksArray; //array for store task
    private int size; //size of array
    private Stack<String[]> taskHist; //stack to store task for undo action
    private boolean[] completed; //array to mark task as completed
    private Stack<boolean[]> completedHist; //stack to store completed task for undo action
    private Stack<Integer> sizeHist; //stack to store size of array for undo action

    private static final String[] defaultTask = {
        "Checking Homework", 
        "Do OOP Assignment", 
        "Do Database Assignment", 
        "Do Calculus Assignment", 
        "Do Web Programming Assignment"
    }; //predefined task array

    public ArrayTask() { //function to initialize array
        tasksArray = new String[5];
        completed = new boolean[5];
        this.size = defaultTask.length;
        this.taskHist = new Stack<>();
        this.completedHist = new Stack<>();
        this.sizeHist = new Stack<>();

        System.arraycopy(defaultTask, 0, tasksArray, 0, defaultTask.length); //copy predefined task to array

        for (int i = 0; i < size; i++) {
            completed[i] = false;
        } //initialize completed array (false)

        /*String[] predefinedTasks = {
            "Checking Homework", 
            "Do OOP Assignment", 
            "Do Database Assignment", 
            "Do Calculus Assignment", 
            "Do Web Programming Assignment"
        };*/

        /*for (String task: predefinedTasks) {
            if (size < cap) {
                tasksArray[size] = task;
                completed[size] = false;
                size++;
            }
        }*/
    }

    public boolean isAllCompleted () {
        for (int i = 0; i < size; i++) {
            if (!completed[i]) {
                return false;
            }
        }
        return true;
    } //checking if all tasks are completed (for exit conditional)

    private boolean isValidIndex (int index) {
        if (index < 0 || index >= size) {
            System.out.println("Out of range!");
            return false;
        }
        return true;
    } //checking if index is valid (for error handling) (from user input)

    private void saveState () {
        taskHist.push(tasksArray.clone());
        completedHist.push(completed.clone());
        sizeHist.push(size);
    } //save current state of tasks (for undo functionality)

    /*private void saveState () {
     * taskHist.push(Arrays.copyOf(tasksArray, tasksArray.length));
        completedHist.push(Arrays.copyOf(completed, completed.length));
    } */

    public void completedTask (int index) {
        if (!isValidIndex(index)) return; //checking index
        if (completed[index]) {
            System.out.println("Task already completed!");
            return;
        }  //checking if task is already completed
        saveState();
        completed[index] = true; //change to true if task not completed yet
        System.out.println("Task \"" + tasksArray[index] + "\" completed");
    }

    @Override //override method from interface
    public void addTask (String task) {
        if (size < tasksArray.length) { //checking if array is not full
            saveState();
            tasksArray[size] = task; //add new task
            completed[size] = false; //completed status is false
            size++;
            System.out.println("Adding \"" + task + "\" to the Array");
        } else {
            System.out.println("Array of tasks is full");
        }
    }

    @Override
    public void deleteTask (int index) {
        if (!isValidIndex(index)) return; //checking index
        if (completed[index]) {
            System.out.println("Cannot delete completed tasks!");
            return;
        } //could not delete completed task
        saveState();
        System.out.println("Deleting \"" + tasksArray[index] + "\" from the tasks list");

        for (int i = index; i < size - 1; i++) {
            tasksArray[i] = tasksArray[i+1];
            completed[i] = completed[i+1];
        } //shift element to the left
        tasksArray[--size] = null;
        completed[size] = false; 
    }

    public void updateTask (int index, String newTask) {
        if (!isValidIndex(index)) return;
        if (completed[index]) {
            System.out.println("Cannot update completed task!");
            return;
        }
        saveState();
        System.out.println("Updating task: from \"" + tasksArray[index] + "\" to " + newTask);
        tasksArray[index] = newTask;
    }

    public void printTask () {
        System.out.println("    Tasks To-Do: ");
        System.out.println("    ************");
        for (int i = 0; i < size; i++) {
            String status = completed[i] ? " -Done" : ""; //check if completed = true, add -Done to the end of the task
            System.out.println("[" + (i+1) + "]" + (tasksArray[i] != null ? tasksArray[i] : " ") + status);

        }
    }

    @Override
    public int countTasks () {
        int count = 0;
        for (String task: tasksArray) {
            if (task != null) count++;
        } // count task that is not null
        return count;
    }

    /*public int countTasks () {
     * int count = 0;
     * for (int i = 0; i < size; i++) {
     * if (tasksArray[i] != null) count++;
     * }
     * return count;
    } */

    @Override
    public void undo () {
        if (!taskHist.isEmpty() && !completedHist.isEmpty() && !sizeHist.isEmpty()) { //check if there are tasks to undo
            tasksArray = taskHist.pop();
            completed = completedHist.pop();
            size = sizeHist.pop();
            System.out.println("Undo last action.");
        } else {
            System.out.println("Do something first!");
        }
    }
    /*@Override
    public void undo() {
        if (!taskHist.isEmpty() && !completedHist.isEmpty()) {
            System.arraycopy(taskHist.pop(), 0, tasksArray, 0, tasksArray.length);
            System.arraycopy(completedHist.pop(), 0, completed, 0, completed.length);
            System.out.println("Undo last action.");
        } else {
            System.out.println("Do something first!");
        }
    } */
    
}

class LinkedListTask implements TaskStorage { //method implement with linked list
    private LinkedList<String> tasksLL;
    private Stack<LinkedList<String>> taskHist;
    private LinkedList<Boolean> completedLL;
    private Stack<LinkedList<Boolean>> completedHist;

    public LinkedListTask () { //function to initialize linkedlisttask
        this.tasksLL = new LinkedList<>();
        this.taskHist = new Stack<>();
        this.completedLL = new LinkedList<>();
        this.completedHist = new Stack<>();
    }

    public boolean isAllCompleted () {
        for (boolean status: completedLL) { //for-each
            if (!status) {
                return false;
            }
        } //return false if there any false in completedLL
        return true;
    }

    public void completedTask(int index) {
        if (!isValidIndex(index)) return;
        if (completedLL.get(index)) {
            System.out.println("Task already completed!");
            return;
        }
        saveState();
        completedLL.set(index, true);
        System.out.println("Task \"" + tasksLL.get(index) + "\" completed");
    }

    private boolean isValidIndex (int index) {
        if (index < 0 || index >= tasksLL.size()) {
            System.out.println("Out of range!");
            return false;
        }
        return true;
    }

    private void saveState () {
        taskHist.push(new LinkedList<>(tasksLL));
        completedHist.push(new LinkedList<>(completedLL));
    }

    @Override
    public void addTask (String task) {
        saveState();
        tasksLL.add(task);
        completedLL.add(false);
        System.out.println("Add \"" + task + "\" to the tasks");
    }

    @Override
    public void deleteTask (int index) {
        if (!isValidIndex(index)) return;
        if (completedLL.get(index)) {
            System.out.println("Cannot delete completed tasks!");
            return;
        }
        saveState();
        System.out.println("Remove \"" + tasksLL.get(index) + "\" from tasks list");
        tasksLL.remove(index);
        completedLL.remove(index);
    }

    @Override
    public void updateTask (int index, String newTask) {
        if (!isValidIndex(index)) return;
        if (completedLL.get(index)) {
            System.out.println("Cannot update completed task!");
            return;
        }
        saveState();
        System.out.println("Updating task: from \"" + tasksLL.get(index) + "\" to " + newTask);
        tasksLL.set(index, newTask);
    }

    @Override
    public void printTask () {
        System.out.println("    Tasks To-Do: ");
        System.out.println("    ************");
        for (int i = 0; i < tasksLL.size() ; i++) {
            String status = completedLL.get(i) ? " -Done" : "";
            System.out.println("[" + (i+1) + "] " + (tasksLL.get(i) != null ? tasksLL.get(i) : " " ) + status);
        }
    }

    @Override
    public int countTasks () {
        return tasksLL.size();
    }

    @Override
    public void undo() {
        if (!taskHist.isEmpty() && !completedHist.isEmpty()) {
            tasksLL = taskHist.pop();
            completedLL = completedHist.pop();
            System.out.println("Undo last action.");
        } else {
            System.out.println("Do something first!");
        }
    }
}
public class DailyTaskManager {
    private static boolean allTaskCompleted (TaskStorage taskStorage) {
        if (taskStorage instanceof ArrayTask) { //alltaskcompleted for arraytask
            return ((ArrayTask) taskStorage).isAllCompleted();
        } else if (taskStorage instanceof LinkedListTask) {
            return ((LinkedListTask) taskStorage).isAllCompleted();
        } //alltaskcompleted for linked list
        return false;
    }

    private static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } //clear screen

    private static void exitProgram () {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        cls();
        System.out.println("Have a nice rest!!!");
        System.exit(0); //terminate
    }
    public static void main(String[] args) throws InterruptedException { //main program
        Scanner scanner = new Scanner(System.in); //initialize scanner
        TaskStorage taskManager;
        System.out.println("******************************");
        System.out.println("Welcome to Daily To-Do List!!!");
        System.out.println("******************************");
        System.out.print("Do you have a lot of tasks for today? [1] Yes [2] No, just basic : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            taskManager = new LinkedListTask();
            
        } else {
            taskManager = new ArrayTask();
        }
        cls();
        while (true) { //main loop
            System.out.println();
            taskManager.printTask();
            System.out.println("Task count: " + taskManager.countTasks());
            System.out.println();
            System.out.println("[1] Add Task [2] Delete Task [3] Update Task \n[4] Mark Complete Task [5] Undo [6] Exit");
            System.out.print("What you want to do?: ");
            int choices = scanner.nextInt();
            scanner.nextLine();

            boolean couldAddTask = !(taskManager instanceof ArrayTask) || ((ArrayTask) taskManager).countTasks() <5; //check if array is not full (validation)

            switch (choices) {
                case 1 -> { //add task
                    if (!couldAddTask) {
                        System.out.println("Task is full. Couldn't add more");
                        Thread.sleep(900);
                        cls();
                        continue;
                    }
                    System.out.print("New Task: ");
                    String task = scanner.nextLine();
                    taskManager.addTask(task);
                }
                case 2 -> { //delete task
                    if (taskManager.countTasks() == 0) {
                        System.out.println("No Tasks to delete");
                        Thread.sleep(900);
                        cls();
                        continue;
                    }
                    System.out.print("Task number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    taskManager.deleteTask(deleteIndex);
                }
                case 3 -> {
                    if (taskManager.countTasks() == 0) {
                        System.out.println("No Tasks to update");
                        Thread.sleep(900);
                        cls();
                        continue;
                    }
                    System.out.print("Task number to update: ");
                    int updateIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.print("New Task: ");
                    String updateTask = scanner.nextLine();
                    taskManager.updateTask(updateIndex, updateTask);
                }
                case 4 -> {
                    if (taskManager.countTasks() == 0) {
                        System.out.println("No Tasks to mark as completed");
                        Thread.sleep(900);
                        cls();
                        continue;
                    }
                    System.out.print("Task number to mark as completed: ");
                    int completeIndex = scanner.nextInt() - 1;
                    if (taskManager instanceof ArrayTask) {
                        ((ArrayTask) taskManager).completedTask(completeIndex);
                    } else if (taskManager instanceof LinkedListTask) {
                        ((LinkedListTask) taskManager).completedTask(completeIndex);
                    }

                }
                case 5 -> {
                    if (taskManager.countTasks() == 0) {
                        System.out.println("No Tasks to undo");
                        Thread.sleep(900);
                        cls();
                        continue;
                    }
                    taskManager.undo();
                }
                case 6 -> {
                    if (allTaskCompleted(taskManager)) {
                        System.out.println("Thank you for using Daily To-Do List!");
                        System.out.println("Exiting Program . . .");
                        exitProgram();
                    } else {
                        System.out.print("Do you give up alreay? (yes/no): ");
                        String giveUp = scanner.nextLine().trim();
                        if (giveUp.equalsIgnoreCase("yes")) {
                            System.out.println("Warning: All task will be lost!");
                            exitProgram();
                        } else {
                            continue;
                        };
                    }
                }
                default -> System.out.println("Out of range!");
            }
            Thread.sleep(900);
            cls();
        }
    }
}