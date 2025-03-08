import java.util.Stack;
import java.util.LinkedList;
import java.util.Scanner;
//import javax.sound.sampled.Line;

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

    public ArrayTask(int cap) {
        tasksArray = new String[cap];
        completed = new boolean[cap];
        this.size = 0;
        this.taskHist = new Stack<>();
        this.completedHist = new Stack<>();
    }

    private boolean isValidIndex (int index) {
        if (index < 0 || index >= size) {
            System.out.println("Out of range!");
            return false;
        }
        return true;
    }

    private void saveState () {
        taskHist.push(tasksArray.clone());
        completedHist.push(completed.clone());
    }

    /*private void saveState () {
     * taskHist.push(Arrays.copyOf(tasksArray, tasksArray.length));
        completedHist.push(Arrays.copyOf(completed, completed.length));
    } */

    public void completedTask (int index) {
        if (!isValidIndex(index)) return;
        if (completed[index]) {
            System.out.println("Task already completed!");
            return;
        }
        saveState();
        completed[index] = true;
        System.out.println("Task \"" + tasksArray[index] + "\" completed");
    }

    @Override
    public void addTask (String task) {
        if (size < tasksArray.length) {
            saveState();
            tasksArray[size] = task;
            completed[size] = false;
            size++;
            System.out.println("Adding \"" + task + "\" to the Array");
        } else {
            System.out.println("Array of tasks is full");
        }
    }

    @Override
    public void deleteTask (int index) {
        if (!isValidIndex(index)) return;
        if (completed[index]) {
            System.out.println("Cannot delete completed tasks!");
            return;
        }
        saveState();
        System.out.println("Deleting \"" + tasksArray[index] + "\" from the tasks list");

        for (int i = index; i < size - 1; i++) {
            tasksArray[i] = tasksArray[i+1];
            completed[i] = completed[i+1];
        }
        tasksArray[--size] = null;
        completed[size] = false; //?
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
            String status = completed[i] ? " ✔" : "";
            System.out.println("[" + (i+1) + "]" + (tasksArray[i] != null ? tasksArray[i] : " ") + status);

        }
    }

    @Override
    public int countTasks () {
        int count = 0;
        for (String task: tasksArray) {
            if (task != null) count++;
        }
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
        if (!taskHist.isEmpty() && !completedHist.isEmpty()) {
            tasksArray = taskHist.pop();
            completed = completedHist.pop();
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

class LinkedListTask implements TaskStorage {
    private LinkedList<String> tasksLL;
    private Stack<LinkedList<String>> taskHist;
    private LinkedList<Boolean> completedLL;
    private Stack<LinkedList<Boolean>> completedHist;

    public LinkedListTask () {
        this.tasksLL = new LinkedList<>();
        this.taskHist = new Stack<>();
        this.completedLL = new LinkedList<>();
        this.completedHist = new Stack<>();
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
        if (isValidIndex(index)) return;
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
            String status = completedLL.get(i) ? " ✔" : "";
            System.out.println("[" + (i+1) + "]" + (tasksLL.get(i) != null ? tasksLL.get(i) : " "));
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
public class Coba2 {
    public static void main(String[] args) {
        
    }


}
