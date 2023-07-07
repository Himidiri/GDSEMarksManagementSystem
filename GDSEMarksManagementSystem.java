import java.util.*;

public class GDSEMarksManagementSystem {

    // Constants for the number of students and modules
    private static final int noOfStudents = 100;
    private static final int noOfModules = 2;

    // Arrays to store student information and marks
    private static String[] studentIds = new String[noOfStudents];
    private static String[] studentNames = new String[noOfStudents];
    private static int[][] marks = new int[noOfStudents][noOfModules];

    // Variable to keep track of the number of students
    private static int studentCount = 0;

    // Array to store module names
    private static final String[] moduleNames = {"Programming Fundamentals", "Database Management Systems"};

    // Scanner object for user input
    private static Scanner input = new Scanner(System.in);

    // Constants for console formatting
    private static final String BOLD = "\033[1m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) {

        // Clear console and display home page
        clearConsole();
        displayHomePage();

        // Get user choice
        int choice = getChoice();
        clearConsole();

        // Perform action based on user choice
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

    /**
     * Method to display the home page
     */
    private static void displayHomePage() {
        System.out.println(BOLD + "---------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t   WELCOME TO GDSE MARKS MANAGEMENT SYSTEM\t\t\t|" + RESET);
        System.out.println(BOLD + "---------------------------------------------------------------------------------" + RESET);
        System.out.println();
        System.out.println("[1] Add New Student\t\t\t[2] Add New Student With Marks");
        System.out.println("[3] Add Marks\t\t\t\t[4] Update Student Details");
        System.out.println("[5] Update Marks\t\t\t[6] Delete Student");
        System.out.println("[7] Print Student Details\t\t[8] Print Student Ranks");
        System.out.println("[9] Best in Programming Fundamentals\t[10] Best in Database Management System");
        System.out.println();
        System.out.print(BOLD + "Enter an option to continue > " + RESET);
    }

    /**
     * Method to add a new student
     */
    private static void addNewStudent() {
        clearConsole();
        System.out.println(BOLD + "-------------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t\t   ADD NEW STUDENT\t\t\t\t    |" + RESET);
        System.out.println(BOLD + "-------------------------------------------------------------------------------------" + RESET);
        System.out.println();

        String studentId;
        String studentName;

        // Loop until a valid student ID is entered or user chooses to exit
        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();

            if (isStudentIdExists(studentId)) {
                System.out.println("The Student ID already exists\n");
            }
        } while (isStudentIdExists(studentId));

        System.out.print("Enter Student Name : ");
        studentName = input.nextLine().trim();

        // Add the student ID and name to the respective arrays
        studentIds[studentCount] = studentId;
        studentNames[studentCount] = studentName;
        studentCount++;

        System.out.print("\nStudent has been added successfully. Do you want to add a new student? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        // If the user chooses to add a new student, recursively call the addNewStudent() method
        if (choice.equalsIgnoreCase("Y")) {
            addNewStudent();
        }
        // If the user chooses not to add a new student, go back to the main menu
        else if (choice.equalsIgnoreCase("n")) {
            main(null);
        }
        // If an invalid option is entered, display an error message
        else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Method to add a new student with marks
     */
    private static void addNewStudentWithMarks() {
        clearConsole();
        String studentId;
        String studentName;
        int[] studentMarks = new int[noOfModules];

        System.out.println(BOLD + "-----------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t     ADD NEW STUDENT WITH MARKS\t\t\t\t  |" + RESET);
        System.out.println(BOLD + "-----------------------------------------------------------------------------------" + RESET);
        System.out.println();

        // Loop until a valid student ID is entered or user chooses to exit
        do {
            // Prompt for student ID and check if it already exists
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();

            if (isStudentIdExists(studentId)) {
                System.out.println("The Student ID already exists\n");
            }
        } while (isStudentIdExists(studentId));

        // Prompt for student name
        System.out.print("Enter Student Name : ");
        studentName = input.nextLine().trim();

        System.out.println();

        // Get marks for each module
        for (int i = 0; i < noOfModules; i++) {
            int moduleMarks = getValidMarks(moduleNames[i]);
            studentMarks[i] = moduleMarks;
        }

        // Add student information to arrays
        studentIds[studentCount] = studentId;
        studentNames[studentCount] = studentName;
        marks[studentCount] = studentMarks;
        studentCount++;

        // Prompt for adding another student or exiting
        System.out.print("\nStudent has been added successfully. Do you want to add a new student? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equalsIgnoreCase("Y")) {
            // Recursive call to add new student
            addNewStudentWithMarks();
        } else if (choice.equalsIgnoreCase("n")) {
            // Call the main method to return to the main menu
            main(null);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Method to add marks
     */
    private static void addMarks() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD + "------------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t\t      ADD MARKS\t\t\t\t\t   |" + RESET);
        System.out.println(BOLD + "------------------------------------------------------------------------------------" + RESET);
        System.out.println();

        // Loop until a valid student ID is entered or user chooses to exit
        do {
            System.out.print("Enter Student ID : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            // If the student ID is invalid, ask the user if they want to search again
            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();
                if (choice.equalsIgnoreCase("n")) {
                    main(null); // Return to the main menu if user chooses not to search again
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        // If a valid student ID is found
        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name   : " + studentName);

            // Check if marks have already been added for this student
            if (marks[studentIndex][0] != 0 || marks[studentIndex][1] != 0) {
                System.out.println("This student's marks have already been added.");
                System.out.println("If you want to update the marks, please use [5] Update Marks option\n");
                System.out.print("\nDo you want to add marks for another student? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();

                // If user wants to add marks for another student, recursively call the addMarks() method
                if (choice.equalsIgnoreCase("Y")) {
                    addMarks();
                } else if (choice.equalsIgnoreCase("n")) {
                    main(null); // Return to the main menu if user chooses not to add marks for another student
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } else {
                int[] studentMarks = new int[noOfModules];
                System.out.println();

                // Prompt the user to enter marks for each module
                for (int i = 0; i < noOfModules; i++) {
                    int moduleMarks = getValidMarks(moduleNames[i]);
                    studentMarks[i] = moduleMarks;
                }

                marks[studentIndex] = studentMarks; // Store the marks for the student

                System.out.print("\nMarks have been added. Do you want to add marks for another student? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();

                // If user wants to add marks for another student, recursively call the addMarks() method
                if (choice.equalsIgnoreCase("Y")) {
                    addMarks();
                } else if (choice.equalsIgnoreCase("n")) {
                    main(null); // Return to the main menu if user chooses not to add marks for another student
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    /**
     * Method to update student details
     */
    private static void updateStudentDetails() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD + "---------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t     UPDATE STUDENT DETAILS\t\t\t\t|" + RESET);
        System.out.println(BOLD + "---------------------------------------------------------------------------------" + RESET);
        System.out.println();

        // Prompt for student ID and search for the student
        do {
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            // If student ID is invalid, prompt for retry
            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                // If user chooses not to search again, return to main menu
                if (choice.equalsIgnoreCase("n")) {
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        // If student ID is found, proceed with updating details
        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name : " + studentName);

            System.out.println();
            System.out.print("Enter the new student name: ");
            String newStudentName = input.nextLine().trim();

            // Update the student's name
            studentNames[studentIndex] = newStudentName;

            System.out.println("\nStudent details have been updated successfully.");
            System.out.print("Do you want to update another student's details? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            // If user chooses to update another student, recursively call the updateStudentDetails() method
            if (choice.equalsIgnoreCase("Y")) {
                updateStudentDetails();
            }
            // If user chooses not to update another student, return to main menu
            else if (choice.equalsIgnoreCase("n")) {
                main(null);
            }
            // If user provides an invalid option, display an error message
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Method to update marks
     */
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
            // Prompt for student ID and search for the index
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            // If the student ID is invalid, ask if the user wants to search again
            if (studentIndex == -1) {
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                // If the user chooses not to search again, return to the main menu
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

                // Create an array to store the new marks
                int[] studentMarks = new int[noOfModules];

                System.out.println();

                // Prompt for new marks for each module and store them in the array
                for (int i = 0; i < noOfModules; i++) {
                    int moduleMarks = getValidMarks("Enter new " + moduleNames[i] + " Marks");
                    studentMarks[i] = moduleMarks;
                    marks[studentIndex] = studentMarks;
                }

                System.out.println("Marks have been updated successfully.");
            } else {
                System.out.println("This student's marks are yet to be added.");
            }

            // Ask the user if they want to update marks for another student
            System.out.print("Do you want to update marks for another student? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            // If the user chooses to update marks for another student, recursively call the method
            if (choice.equalsIgnoreCase("Y")) {
                updateMarks();
            }
            // If the user chooses not to update marks for another student, return to the main menu
            else if (choice.equalsIgnoreCase("n")) {
                main(null);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Method to delete student
     */
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
            // Prompt user to enter student ID
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();

            // Find the index of the student in the arrays
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                // Student ID is invalid
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                if (choice.equalsIgnoreCase("n")) {
                    // Go back to the main menu if user chooses not to search again
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            // Student found and can be deleted
            System.out.println("Student has been deleted successfully.");

            // Shift remaining students' data to fill the gap
            for (int i = studentIndex; i < studentCount - 1; i++) {
                studentIds[i] = studentIds[i + 1];
                studentNames[i] = studentNames[i + 1];
                marks[i] = marks[i + 1];
            }

            studentCount--;

            // Ask user if they want to delete another student
            System.out.print("Do you want to delete another student? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("Y")) {
                // Recursive call to delete another student
                deleteStudent();
            } else if (choice.equalsIgnoreCase("n")) {
                // Go back to the main menu if user chooses not to delete another student
                main(null);
            } else {
                // Invalid option
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Method to print student details
     */
    private static void printStudentDetails() {
        clearConsole();
        String studentId;
        int studentIndex;
        String choice = "";

        System.out.println(BOLD + "---------------------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t\t     PRINT STUDENT DETAILS \t\t\t\t|" + RESET);
        System.out.println(BOLD + "---------------------------------------------------------------------------------" + RESET);
        System.out.println();

        do {
            // Prompt for student ID
            System.out.print("Enter Student ID   : ");
            studentId = input.nextLine().trim();
            studentIndex = findStudentIndexById(studentId);

            if (studentIndex == -1) {
                // Invalid student ID
                System.out.print("Invalid Student ID. Do you want to search again? (Y/n) : ");
                choice = input.nextLine().trim().toLowerCase();
                System.out.println();

                if (choice.equalsIgnoreCase("n")) {
                    // Go back to the main menu
                    main(null);
                }
            }
        } while (studentIndex == -1 && choice.equalsIgnoreCase("Y"));

        if (studentIndex != -1) {
            String studentName = studentNames[studentIndex];
            System.out.println("Student Name : " + studentName);

            if (marks[studentIndex][0] == 0 && marks[studentIndex][1] == 0) {
                // Marks have not been added yet
                System.out.println();
                System.out.println("Marks yet to be added\n");
            } else {
                // Print the marks details
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

            // Prompt for searching another student
            System.out.print("Do you want to search another student details? (Y/n) : ");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("Y")) {
                // Recursive call to search another student
                printStudentDetails();
            } else if (choice.equalsIgnoreCase("n")) {
                // Go back to the main menu
                main(null);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Method to print student ranks
     */
    private static void printStudentRanks() {
        clearConsole();
        sortStudentsByTotalMarks();

        System.out.println(BOLD + "--------------------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t\t      PRINT STUDENT'S RANKS\t\t\t   |" + RESET);
        System.out.println(BOLD + "--------------------------------------------------------------------\n" + RESET);

        // Print the table header
        System.out.printf("+------+--------+--------------------+--------------+--------------+%n");
        System.out.printf("| Rank | ID     | Name               | Total Marks  | Avg.Marks    |%n");
        System.out.printf("+------+--------+--------------------+--------------+--------------+%n");

        // Iterate over each rank and print student details
        for (int rank = 1; rank <= studentCount; rank++) {
            int index = rank - 1;
            String studentId = studentIds[index];
            String studentName = studentNames[index];
            int totalMarks = marks[index][0] + marks[index][1];
            double averageMarks = (double) totalMarks / noOfModules;

            // Only print if the student has some total marks
            if (totalMarks > 0) {
                System.out.printf("| %-4s | %-6s | %-18s | %12s | %12.2f |%n",
                        rank, studentId, studentName, totalMarks, averageMarks);
            }
        }

        // Print the table footer
        System.out.printf("+------+--------+--------------------+--------------+--------------+%n");
        System.out.println();

        // Prompt the user to go back to the main menu or print ranks again
        System.out.print("Do you want to go back to the main menu? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equalsIgnoreCase("Y")) {
            // Go back to the main menu
            main(null);
        } else if (choice.equalsIgnoreCase("n")) {
            // Print the student ranks again
            printStudentRanks();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Method to displays the best students in programming fundamentals based on their marks
     */
    private static void bestInProgrammingFundamentals() {
        clearConsole();

        System.out.println(BOLD + "----------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t     BEST IN PROGRAMMING FUNDAMENTALS\t\t |" + RESET);
        System.out.println(BOLD + "----------------------------------------------------------" + RESET);
        System.out.println();

        // Create an array to store sorted indices
        int[] sortedIndices = new int[studentCount];
        for (int i = 0; i < studentCount; i++) {
            sortedIndices[i] = i;
        }

        // Sort the students based on programming fundamentals marks using bubble sort
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                int studentIndex1 = sortedIndices[j];
                int studentIndex2 = sortedIndices[j + 1];
                int marks1 = marks[studentIndex1][0];
                int marks2 = marks[studentIndex2][0];

                if (marks1 < marks2) {
                    int temp = sortedIndices[j];
                    sortedIndices[j] = sortedIndices[j + 1];
                    sortedIndices[j + 1] = temp;
                }
            }
        }

        // Display table header
        boolean studentsFound = false;
        System.out.println("+--------+---------------+--------------+----------------+");
        System.out.println("| ID     | Name          | PF Marks     | DBMS Marks     |");
        System.out.println("+--------+---------------+--------------+----------------+");

        // Display details of students with positive programming fundamentals marks
        for (int studentIndex : sortedIndices) {
            int pfMarks = marks[studentIndex][0];
            if (pfMarks > 0) {
                String studentId = studentIds[studentIndex];
                String studentName = studentNames[studentIndex];
                int dbmsMarks = marks[studentIndex][1];

                // Display student details
                System.out.printf("| %-6s | %-13s | %-12d | %-14d |%n", studentId, studentName, pfMarks, dbmsMarks);
                studentsFound = true;
            }
        }
        System.out.println("+--------+---------------+--------------+----------------+");

        // Display message if no students found
        if (!studentsFound) {
            System.out.println();
            System.out.println("No students found!");
        }

        System.out.println();
        System.out.print("Do you want to go back to the main menu? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        // Handle user's choice
        if (choice.equalsIgnoreCase("Y")) {
            main(null); // Go back to the main menu
        } else if (choice.equalsIgnoreCase("n")) {
            bestInProgrammingFundamentals(); // Show the list again
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Method to displays the best students in database management system based on their marks
     */
    private static void bestInDatabaseManagementSystem() {
        clearConsole();

        System.out.println(BOLD + "----------------------------------------------------------" + RESET);
        System.out.println(BOLD + "|\t   BEST IN DATABASE MANAGEMENT SYSTEM\t\t |" + RESET);
        System.out.println(BOLD + "----------------------------------------------------------" + RESET);
        System.out.println();

        // Create an array of indices to keep track of student positions
        int[] sortedIndices = new int[studentCount];
        for (int i = 0; i < studentCount; i++) {
            sortedIndices[i] = i;
        }

        // Sort the indices based on DBMS marks using bubble sort algorithm
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                int studentIndex1 = sortedIndices[j];
                int studentIndex2 = sortedIndices[j + 1];
                int marks1 = marks[studentIndex1][1];
                int marks2 = marks[studentIndex2][1];

                if (marks1 < marks2) {
                    int temp = sortedIndices[j];
                    sortedIndices[j] = sortedIndices[j + 1];
                    sortedIndices[j + 1] = temp;
                }
            }
        }

        // Display table header
        boolean studentsFound = false;
        System.out.println("+--------+-----------------+----------------+------------+");
        System.out.println("| ID     | Name            | DBMS Marks     | PF Marks   |");
        System.out.println("+--------+-----------------+----------------+------------+");

        // Display student details with highest DBMS marks
        for (int studentIndex : sortedIndices) {
            int dbmsMarks = marks[studentIndex][1];
            if (dbmsMarks > 0) {
                String studentId = studentIds[studentIndex];
                String studentName = studentNames[studentIndex];
                int pfMarks = marks[studentIndex][0];

                System.out.printf("| %-6s | %-15s | %-14d | %-10d |%n", studentId, studentName, dbmsMarks, pfMarks);
                studentsFound = true;
            }
        }
        System.out.println("+--------+-----------------+----------------+------------+");

        // Display message if no students are found
        if (!studentsFound) {
            System.out.println();
            System.out.println("No students found!");
        }

        System.out.println();
        System.out.print("Do you want to go back to the main menu? (Y/n) : ");
        String choice = input.nextLine().trim().toLowerCase();

        // Handle user's choice
        if (choice.equalsIgnoreCase("Y")) {
            main(null); // Go back to the main menu
        } else if (choice.equalsIgnoreCase("n")) {
            bestInDatabaseManagementSystem();
            ; // Show the list again
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    // This method gets user input for a choice and returns it as an integer.
    private static int getChoice() {
        try {
            return Integer.parseInt(input.nextLine()); // Parse the input as an integer
        } catch (NumberFormatException e) {
            return -1; // Return -1 if the input is not a valid integer
        }
    }

    // This method checks if a student ID exists in the studentIds array.
    private static boolean isStudentIdExists(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(studentId)) {
                return true; // Return true if the student ID is found
            }
        }
        return false; // Return false if the student ID is not found
    }

    // This method gets valid marks for a module by repeatedly asking the user until valid marks are entered.
    private static int getValidMarks(String moduleName) {
        while (true) {
            System.out.print(moduleName + " Marks    : "); // Display the module name
            try {
                int moduleMarks = Integer.parseInt(input.nextLine().trim()); // Parse the input as an integer
                if (moduleMarks < 0 || moduleMarks > 100) {
                    System.out.println("Invalid marks, please enter correct marks."); // Display an error message for invalid marks
                    System.out.println();
                    continue; // Ask for input again
                }
                return moduleMarks; // Return the valid module marks
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks, please enter correct marks."); // Display an error message for invalid marks
                System.out.println();
            }
        }
    }

    // This method finds the index of a student in the studentIds array based on the student ID.
    private static int findStudentIndexById(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(studentId)) {
                return i; // Return the index of the student ID if found
            }
        }
        return -1; // Return -1 if the student ID is not found
    }

    // This method finds the rank of a student based on their total marks.
    private static int findRank(int studentIndex) {
        int totalMarks = marks[studentIndex][0] + marks[studentIndex][1];
        int rank = 1;

        for (int i = 0; i < studentCount; i++) {
            if (i != studentIndex) {
                int otherTotalMarks = marks[i][0] + marks[i][1];
                if (otherTotalMarks > totalMarks) {
                    rank++; // Increment the rank if the other student has higher total marks
                }
            }
        }

        return rank; // Return the rank of the student
    }

    // This method returns the rank position as a string based on the given rank.
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
        return rankPosition; // Return the rank position
    }

    // This method sorts the students by their total marks in descending order.
    private static void sortStudentsByTotalMarks() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                int totalMarks1 = marks[j][0] + marks[j][1];
                int totalMarks2 = marks[j + 1][0] + marks[j + 1][1];

                if (totalMarks1 < totalMarks2) {
                    // Swap student IDs
                    String tempId = studentIds[j];
                    studentIds[j] = studentIds[j + 1];
                    studentIds[j + 1] = tempId;

                    // Swap student names
                    String tempName = studentNames[j];
                    studentNames[j] = studentNames[j + 1];
                    studentNames[j + 1] = tempName;

                    // Swap marks
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