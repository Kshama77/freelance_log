import java.util.ArrayList;
import java.util.Scanner;

public class FreelanceLog {
    private static final ArrayList<FreelanceProject> projects = new ArrayList<FreelanceProject>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=================================");
        System.out.println("       Welcome to FreelanceLog");
        System.out.println("=================================");

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    viewProjects();
                    break;
                case 3:
                    markProjectPaid();
                    break;
                case 4:
                    showSummary();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thanks for using FreelanceLog!");
                    break;
                default:
                    System.out.println("Please choose a number from 1 to 5.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a freelance project");
        System.out.println("2. View all projects");
        System.out.println("3. Mark a project as paid");
        System.out.println("4. View payment summary");
        System.out.println("5. Exit");
    }

    private static void addProject() {
        System.out.print("Client name: ");
        String client = scanner.nextLine().trim();

        System.out.print("Project name: ");
        String project = scanner.nextLine().trim();

        if (client.isEmpty() || project.isEmpty()) {
            System.out.println("Client name and project name cannot be empty.");
            return;
        }

        double fee = readDouble("Project fee ($): ");
        if (fee < 0) {
            System.out.println("Fee cannot be negative. Project was not added.");
            return;
        }

        projects.add(new FreelanceProject(client, project, fee));
        System.out.println("Project added successfully!");
    }

    private static void viewProjects() {
        if (projects.isEmpty()) {
            System.out.println("No projects yet. Add your first project!");
            return;
        }

        System.out.println("\n--- Your Projects ---");
        for (int i = 0; i < projects.size(); i++) {
            projects.get(i).display(i + 1);
        }
    }

    private static void markProjectPaid() {
        if (projects.isEmpty()) {
            System.out.println("There are no projects to update.");
            return;
        }

        viewProjects();
        int number = readInt("Enter the project number to mark as paid: ");

        if (number < 1 || number > projects.size()) {
            System.out.println("Invalid project number.");
            return;
        }

        FreelanceProject project = projects.get(number - 1);
        if (project.isPaid()) {
            System.out.println("This project is already marked as paid.");
        } else {
            project.markPaid();
            System.out.println("Payment marked as received for: " + project.getProjectName());
        }
    }

    private static void showSummary() {
        double total = 0;
        double paid = 0;
        double outstanding = 0;

        for (FreelanceProject project : projects) {
            total += project.getFee();
            if (project.isPaid()) {
                paid += project.getFee();
            } else {
                outstanding += project.getFee();
            }
        }

        System.out.println("\n--- Payment Summary ---");
        System.out.printf("Number of projects: %d%n", projects.size());
        System.out.printf("Total project fees: $%.2f%n", total);
        System.out.printf("Payments received:  $%.2f%n", paid);
        System.out.printf("Outstanding:        $%.2f%n", outstanding);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount, such as 250 or 250.50.");
            }
        }
    }
}
