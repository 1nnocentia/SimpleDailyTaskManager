import java.util.Stack;

interface TaskStorage {
    void addTask(String task);
    void deleteTask(int index);
    void updateTask(int index, String newTask);
    void printTask();
    int countTasks();    
}

class ArrayTask implements TaskStorage {
    private String[] tasksArray;
    private int size;
    private Stack<String[]> hist;

    public ArrayTask(int cap) {
        tasksArray = new String[cap];
        this.size = 0;
        this.hist = new Stack<>();
    }

    private boolean isValidIndex (int index) {
        if (index < 0 || index >= size) {
            System.out.println("Out of range!");
            return false;
        }
        return true;
    }

    private void saveState() {
        hist.push(tasksArray.clone());
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

    @Override
    public void deleteTask (int index) {
        if (!isValidIndex(index)) return;
        saveState();
        System.out.println("Deleting \"" + tasksArray[index] + "\" from the tasks list");

        for (int i = index; i < size - 1; i++) {
            tasksArray[i] = tasksArray[i+1];
        }
        tasksArray[--size] = null;
    }

    public void updateTask (int index, String newTask) {
        
    }
    
}

public class Coba2 {
    public static void main(String[] args) {
        
    }
}
