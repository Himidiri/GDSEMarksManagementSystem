import java.util.*;

public class GDSEMarksManagementSystem {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int choice = -1;
        while (choice != 0) {
            clearConsole();
            displayHomePage();
            choice = getChoice();

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    addNewStudentWithMarks();
                    break;
                case 3:
                    addMarks();
                    break;
                case 4:
                    updateStudentDetails();
                    break;
                case 5:
                    updateMarks();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    printStudentDetails();
                    break;
                case 8:
                    printStudentRanks();
                    break;
                case 9:
                    bestInProgrammingFundamentals();
                    break;
                case 10:
                    bestInDatabaseManagementSystem();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Exiting the program...");
                    System.out.println();
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }

    private static void displayHomePage() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("|"+"\t\t\t\t\t\t"+"WELCOME TO GDSE MARKS MANAGEMENT SYSTEM"+"\t\t\t\t\t\t"+"|");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("[1] Add New Student                        [2]  Add New Student With Marks");
        System.out.println("[3] Add Marks                              [4]  Update Student Details");
        System.out.println("[5] Update Marks                           [6]  Delete Student");
        System.out.println("[7] Print Student Details                  [8]  Print Student Ranks");
        System.out.println("[9] Best in Programming Fundamentals       [10] Best in Database Management System");
        System.out.println();
        System.out.print("Enter an option to continue > ");
    }

    private static void addNewStudent() {
    }

    private static void addNewStudentWithMarks() {
    }

    private static void addMarks() {
    }

    private static void updateStudentDetails() {
    }

    private static void updateMarks() {
    }

    private static void deleteStudent() {
    }

    private static void printStudentDetails() {
    }

    private static void printStudentRanks() {
    }

    private static void bestInProgrammingFundamentals() {
    }

    private static void bestInDatabaseManagementSystem() {
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
