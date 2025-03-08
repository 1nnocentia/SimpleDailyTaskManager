import java.util.Stack;

interface TaskStorage {
    void addTask(String task);
    void deleteTask(int index);
    void updateTask(int index, String newTask);
    void printTask();
    int countTasks();    
}

class ArrayTask implements TaskStorage {
    private String tasksArray;
    private int size;

    public ArrayTask(int cap) {
        tasksArray = new String[cap];
        this.size = 0;
    }

    @Override
    public void addTask(String task) {
        if (size < tasksArray.length) {
            saveState();
            tasksArray[size++] = task;
            System.out.println("Adding \"" + task + "\" to the Array");
        } else {
            System.out.println("Array of tasks is full");
        }
    }

    
}

public class Coba2 {
    
}
