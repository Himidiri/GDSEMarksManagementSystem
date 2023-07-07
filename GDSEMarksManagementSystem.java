import java.util.*;

public class GDSEMarksManagementSystem {
    private static final int noOfStudents = 100;
    private static final int noOfModules = 2;

    private static String[] studentIds = new String[noOfStudents];
    private static String[] studentNames = new String[noOfStudents];
    private static int[][] marks = new int[noOfStudents][noOfModules];
    private static int studentCount = 0;
    private static final String[] moduleNames = {"Programming Fundamentals", "Database Management Systems"};
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
        clearConsole();
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
            int moduleMarks = getValidMarks(moduleNames[i]);
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
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addMarks() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD +"------------------------------------------------------------------------------------"+ RESET);
        System.out.println(BOLD +"|\t\t\t\t      ADD MARKS\t\t\t\t\t   |"+ RESET);
        System.out.println(BOLD +"------------------------------------------------------------------------------------"+ RESET);
        System.out.println();

        do {
            System.out.print("Enter Student ID : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();
                if (choice.equalsIgnoreCase("n")) {
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name   : " + studentName);

            if (marks[studentIndex][0] != 0 || marks[studentIndex][1] != 0) {
                System.out.println("This student's marks have been already added.");
                System.out.println("If you want to update the marks, please use [5] Update Marks option\n");
                System.out.print("\nDo you want to add marks for another student? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();

                if (choice.equalsIgnoreCase("Y")) {
                    addMarks();
                } else if (choice.equalsIgnoreCase("n")) {
                    main(null);
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } else {
                int[] studentMarks = new int[noOfModules];
                System.out.println();
                for (int i = 0; i < noOfModules; i++) {
                    int moduleMarks = getValidMarks(moduleNames[i]);
                    studentMarks[i] = moduleMarks;
                }

                marks[studentIndex] = studentMarks;

                System.out.print("\nMarks have been added. Do you want to add marks for another student? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();

                if (choice.equalsIgnoreCase("Y")) {
                    addMarks();
                } else if (choice.equalsIgnoreCase("n")) {
                    main(null);
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static void updateStudentDetails() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD +"---------------------------------------------------------------------------------"+ RESET);
        System.out.println(BOLD +"|\t\t\t     UPDATE STUDENT DETAILS\t\t\t\t|"+ RESET);
        System.out.println(BOLD +"---------------------------------------------------------------------------------"+ RESET);
        System.out.println();

        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();
                if (choice.equalsIgnoreCase("n")) {
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name : " + studentName);

            System.out.println();
            System.out.print("Enter the new student name: ");
            String newStudentName = input.nextLine().trim();

            studentNames[studentIndex] = newStudentName;

            System.out.println("\nStudent details have been updated successfully.");
            System.out.print("Do you want to update another student's details? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("Y")) {
                updateStudentDetails();
            } else if (choice.equalsIgnoreCase("n")) {
                main(null);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updateMarks() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD + "--------------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t\t     UPDATE MARKS\t\t\t\t     |" + RESET);
        System.out.println(BOLD + "--------------------------------------------------------------------------------------" + RESET);
        System.out.println();

        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                if (choice.equalsIgnoreCase("n")) {
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name : " + studentName);
            System.out.println();

            if (marks[studentIndex][0] != 0 || marks[studentIndex][1] != 0) {
                System.out.println("Programming Fundamentals Marks    : " + marks[studentIndex][0]);
                System.out.println("Database Management Systems Marks : " + marks[studentIndex][1]);
                System.out.println();

                int[] studentMarks = new int[noOfModules];
                System.out.println();
                for (int i = 0; i < noOfModules; i++) {
                    int moduleMarks = getValidMarks("Enter new " + moduleNames[i] + " Marks");
                    studentMarks[i] = moduleMarks;
                    marks[studentIndex] = studentMarks;
                }
                System.out.println("Marks have been updated successfully.");
            } else {
                System.out.println("This student's marks yet to be added.");
            }

            System.out.print("Do you want to update marks for another student? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("Y")) {
                updateMarks();
            } else if (choice.equalsIgnoreCase("n")) {
                main(null);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void deleteStudent() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD + "--------------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t\t     DELETE STUDENT\t\t\t\t     |" + RESET);
        System.out.println(BOLD + "--------------------------------------------------------------------------------------" + RESET);
        System.out.println();

        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                if (choice.equalsIgnoreCase("n")) {
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            System.out.println("Student has been deleted successfully.");

            for (int i = studentIndex; i < studentCount - 1; i++) {
                studentIds[i] = studentIds[i + 1];
                studentNames[i] = studentNames[i + 1];
                marks[i] = marks[i + 1];
            }

            studentCount--;

            System.out.print("Do you want to delete another student? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("Y")) {
                deleteStudent();
            } else if (choice.equalsIgnoreCase("n")) {
                main(null);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printStudentDetails() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD +"---------------------------------------------------------------------------------"+ RESET);
        System.out.println(BOLD +"|\t\t\t     PRINT STUDENT DETAILS \t\t\t\t|"+ RESET);
        System.out.println(BOLD +"---------------------------------------------------------------------------------"+ RESET);
        System.out.println();

        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                if (choice.equalsIgnoreCase("n")) {
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name : " + studentName);

            if (marks[studentIndex][0] == 0 && marks[studentIndex][1] == 0) {
                System.out.println();
                System.out.println("Marks yet to be added\n");
            } else {
                System.out.println("+-----------------------------------+---------------+");
                System.out.printf("|%-35s|%15s|%n", "Programming Fundamentals Marks", marks[studentIndex][0]);
                System.out.printf("|%-35s|%15s|%n", "Database Management Systems Marks", marks[studentIndex][1]);
                int totalMarks = marks[studentIndex][0] + marks[studentIndex][1];
                double averageMarks = (double) totalMarks / noOfModules;
                int rank = findRank(studentIndex);
                String rankPosition = getRankPosition(rank);
                System.out.printf("|%-35s|%15s|%n", "Total Marks", totalMarks);
                System.out.printf("|%-35s|%15.2f|%n", "Avg. Marks", averageMarks);
                System.out.printf("|%-35s|%15s|%n", "Rank", rank + " (" + rankPosition + ")");
                System.out.println("+-----------------------------------+---------------+\n");
            }

            System.out.print("Do you want to search another student details? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("Y")) {
                printStudentDetails();
            } else if (choice.equalsIgnoreCase("n")) {
                main(null);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printStudentRanks() {
        clearConsole();
        sortStudentsByTotalMarks();

        System.out.println(BOLD +"--------------------------------------------------------------------"+ RESET);
        System.out.println(BOLD +"|\t\t      PRINT STUDENT'S RANKS\t\t\t   |"+ RESET);
        System.out.println(BOLD +"--------------------------------------------------------------------\n"+ RESET);

        System.out.printf("+------+--------+--------------------+--------------+--------------+%n");
        System.out.printf("| Rank | ID     | Name               | Total Marks  | Avg.Marks    |%n");
        System.out.printf("+------+--------+--------------------+--------------+--------------+%n");

        for (int rank = 1; rank <= studentCount; rank++) {
            int index = rank - 1;
            String studentId = studentIds[index];
            String studentName = studentNames[index];
            int totalMarks = marks[index][0] + marks[index][1];
            double averageMarks = (double) totalMarks / noOfModules;

            if (totalMarks > 0) {
                System.out.printf("| %-4s | %-6s | %-18s | %12s | %12.2f |%n",
                        rank, studentId, studentName, totalMarks, averageMarks);
            }
        }

        System.out.printf("+------+--------+--------------------+--------------+--------------+%n");
        System.out.println();

        System.out.print("Do you want to go back to the main menu? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equalsIgnoreCase("Y")) {
            main(null);
        } else if (choice.equalsIgnoreCase("n")) {
            printStudentRanks();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
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
    private static int getValidMarks(String moduleName) {
        while (true) {
            System.out.print(moduleName + " Marks    : ");
            try {
                int moduleMarks = Integer.parseInt(input.nextLine().trim());
                if (moduleMarks < 0 || moduleMarks > 100) {
                    System.out.println("Invalid marks, please enter correct marks.");
                    System.out.println();
                    continue;
                }
                return moduleMarks;
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks, please enter correct marks.");
                System.out.println();
            }
        }
    }
    private static int findStudentIndexById(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(studentId)) {
                return i;
            }
        }
        return -1;
    }
    private static int findRank(int studentIndex) {
        int totalMarks = marks[studentIndex][0] + marks[studentIndex][1];
        int rank = 1;

        for (int i = 0; i < studentCount; i++) {
            if (i != studentIndex) {
                int otherTotalMarks = marks[i][0] + marks[i][1];
                if (otherTotalMarks > totalMarks) {
                    rank++;
                }
            }
        }

        return rank;
    }
    private static String getRankPosition(int rank) {
        String rankPosition;
        switch (rank) {
            case 1:
                rankPosition = "First";
                break;
            case 2:
                rankPosition = "Second";
                break;
            case 3:
                rankPosition = "Third";
                break;
            default:
                rankPosition = rank + "th";
                break;
        }
        return rankPosition;
    }
    private static void sortStudentsByTotalMarks() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                int totalMarks1 = marks[j][0] + marks[j][1];
                int totalMarks2 = marks[j + 1][0] + marks[j + 1][1];

                if (totalMarks1 < totalMarks2) {
                    String tempId = studentIds[j];
                    studentIds[j] = studentIds[j + 1];
                    studentIds[j + 1] = tempId;

                    String tempName = studentNames[j];
                    studentNames[j] = studentNames[j + 1];
                    studentNames[j + 1] = tempName;

                    int[] tempMarks = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = tempMarks;
                }
            }
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
