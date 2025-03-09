import java.util.Scanner;

public class BukanIni {
    static Scanner scanner = new Scanner (System.in);
    static String[] tasksArray = new String[5];
    static {
        tasksArray[0] = "Task 1";
        tasksArray[1] = "Task 2";
        tasksArray[2] = "Task 3";
        tasksArray[3] = "Task 4";
        tasksArray[4] = "Task 5";
    }
    static String reset = "\u001B[0m";
    static String red = "\u001B[31m";
    static String blueWhiteBold = "\033[34;47;1m";
    static int size = tasksArray.length;

    public static void startingMessage() throws InterruptedException {
        System.out.println(blueWhiteBold + "\nWelcome " + "to Daily Task Manager!" + reset);
        System.out.println("*****************************\n");
        System.out.print("How do you want to use this?");
        System.out.println("\n1. Array");
        System.out.println("2. LinkedList");

    }

    public static void printTask (String[] tasksArray) {
        System.out.println("          Tasks List: ");
        System.out.println("          ***********");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + (i+1) + "]" + (tasksArray[i] != null ? tasksArray[i] : " ")); //display tasks, but if value is null, change it to " "
        }
        int tasksCount = countTasks(tasksArray);
        System.out.println("Total filled tasks : " + tasksCount + "/" + tasksArray.length);
    }

    public static int countTasks (String[] taskStrings) {
        int count = 0;
        for (String task : tasksArray) {
            if (task != null) {
                count++;
            }
        }
        return count;
    }
    public static void deleteTask (String[] tasksArray, int size) {
        System.out.print("Which Task do you want to DELETE? ");
        int choiceDel = scanner.nextInt() - 1;
        scanner.nextLine();
        if (choiceDel < 0 || choiceDel >= size || tasksArray[choiceDel] == null) { 
            System.out.println(red + "Out of range." + reset);
            return;
        }


        for (int i = choiceDel; i < size - 1; i++) {
            tasksArray[i] = tasksArray[i + 1];
        }

        tasksArray[size - 1 ] = null;
    }

    public static void addTask (String[] tasksArray, int size) {
        System.out.print("Type your new task: ");
        String newTask = scanner.nextLine();
        tasksArray[size - 1] = newTask;
    }

    public static void updateTask (String[] tasksArray, int size) {
        System.out.print("Which task do you want to update? ");
        int choiceUp = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Type your new task: ");
        String newTask = scanner.nextLine();
        tasksArray[size-1] = newTask;
    }
    public static void main(String[] args) throws Exception {
        String userInput;

        MaybeWeNeedThis.clearScreen();
        startingMessage();
        int choice1 = scanner.nextInt();
        while (true) {
            printTask(tasksArray);
            userInput = scanner.nextLine();
            deleteTask(tasksArray, choice1);
            printTask(tasksArray);
            addTask(tasksArray, choice1);
            printTask(tasksArray);
            updateTask(tasksArray, choice1);
            printTask(tasksArray);

            if (userInput.equalsIgnoreCase("q")){
                break;
            }
        }
            
    }
}
