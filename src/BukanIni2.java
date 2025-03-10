
public class BukanIni2 {
    static Scanner scanner = new Scanner(System.in);
    
    public static int getValidOption (int min, int max) {
        while (true) {
            try {
                System.out.print("What you want to do: ");
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < min || choice > max) {
                    throw new IllegalArgumentException("Out of range! Please enter a number between " + min + " and " + max + ".");
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Please input a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getValidTask () {
        while (true) {
            System.out.print("New Task: ");
            String task = scanner.nextLine().trim();
            if (!task.isEmpty()) {
                return task;
            }
            System.out.println("Enter the task!");
        }
    }
}