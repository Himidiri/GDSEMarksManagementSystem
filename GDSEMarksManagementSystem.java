import java.util.*;

public class GDSEMarksManagementSystem {
    private static final int noOfStudents = 100;
    private static final int noOfModules = 2;

    private static String[] studentIds = new String[noOfStudents];
    private static String[] studentNames = new String[noOfStudents];
    private static int[][] marks = new int[noOfStudents][noOfModules];
    private static int studentCount = 0;
    private static Scanner input = new Scanner(System.in);
    private static final String BOLD = "\033[1m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) {
            clearConsole();
            displayHomePage();
            int choice = getChoice();
            clearConsole();

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
                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }

    private static void displayHomePage() {
        System.out.println(BOLD +"---------------------------------------------------------------------------------"+ RESET);
        System.out.println( BOLD + "|\t\t   WELCOME TO GDSE MARKS MANAGEMENT SYSTEM\t\t\t|"+ RESET);
        System.out.println(BOLD +"---------------------------------------------------------------------------------"+ RESET);
        System.out.println();
        System.out.println("[1] Add New Student\t\t\t[2] Add New Student With Marks");
        System.out.println("[3] Add Marks\t\t\t\t[4] Update Student Details");
        System.out.println("[5] Update Marks\t\t\t[6] Delete Student");
        System.out.println("[7] Print Student Details\t\t[8] Print Student Ranks");
        System.out.println("[9] Best in Programming Fundamentals\t[10] Best in Database Management System");
        System.out.println();
        System.out.print(BOLD+"Enter an option to continue > "+RESET);
    }


    private static void addNewStudent() {
        clearConsole();
        System.out.println(BOLD +"-------------------------------------------------------------------------------------"+ RESET);
        System.out.println(BOLD +"|\t\t\t\t   ADD NEW STUDENT\t\t\t\t    |"+ RESET);
        System.out.println(BOLD +"-------------------------------------------------------------------------------------"+ RESET);
        System.out.println();
        String studentId;
        String studentName;
        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();

            if (isStudentIdExists(studentId)) {
                System.out.println("The Student ID already exists\n");
            }
        } while (isStudentIdExists(studentId));

        System.out.print("Enter Student Name : ");
        studentName = input.nextLine().trim();

        studentIds[studentCount] = studentId;
        studentNames[studentCount] = studentName;
        studentCount++;

        System.out.print("\nStudent has been added successfully. Do you want to add a new student? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equalsIgnoreCase("Y")) {
            addNewStudent();
        }else if(choice.equalsIgnoreCase("n")){
            main(null);
        }else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addNewStudentWithMarks() {
        String studentId;
        String studentName;
        int[] studentMarks = new int[noOfModules];

        System.out.println(BOLD +"-----------------------------------------------------------------------------------"+ RESET);
        System.out.println(BOLD +"|\t\t\t     ADD NEW STUDENT WITH MARKS\t\t\t\t  |"+ RESET);
        System.out.println(BOLD +"-----------------------------------------------------------------------------------"+ RESET);
        System.out.println();

        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();

            if (isStudentIdExists(studentId)) {
                System.out.println("The Student ID already exists\n");
            }
        } while (isStudentIdExists(studentId));

        System.out.print("Enter Student Name : ");
        studentName = input.nextLine().trim();

        System.out.println();

        for (int i = 0; i < noOfModules; i++) {
            String moduleName = getModuleName(i);
            int moduleMarks;

            do {
                System.out.print(moduleName + " Marks    : ");
                try {
                    moduleMarks = Integer.parseInt(input.nextLine().trim());
                    if (moduleMarks < 0 || moduleMarks > 100) {
                        System.out.println("Invalid marks, please enter correct marks.");
                        System.out.println();
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid marks, please enter correct marks.");
                    System.out.println();
                }
            } while (true);

            studentMarks[i] = moduleMarks;
        }

        studentIds[studentCount] = studentId;
        studentNames[studentCount] = studentName;
        marks[studentCount] = studentMarks;
        studentCount++;

        System.out.print("\nStudent has been added successfully. Do you want to add a new student? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equalsIgnoreCase("Y")) {
            addNewStudentWithMarks();
        } else if(choice.equalsIgnoreCase("n")){
            main(null);
        }else {
            System.out.println("Invalid option. Please try again.");
        }
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

    private static boolean isStudentIdExists(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    private static String getModuleName(int moduleIndex) {
        return moduleIndex == 0 ? "Programming Fundamentals" : "Database Management Systems";
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
