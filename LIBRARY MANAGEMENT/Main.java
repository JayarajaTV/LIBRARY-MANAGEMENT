import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        displayOptions();
    }


    static void displayOptions() throws Exception{
        int menuChoice;
        Scanner scanner = new Scanner(System.in);
        Administrator admin = new Administrator();
        
            do {

                System.out.println("=======================================");
                System.out.println("        VJ NATIONAL LIBRARY            ");
                System.out.println("=======================================");
                System.out.println("\nPLEASE SELECT THE OPTION :\n" +
                        "[1] ADMIN LOGIN                      [2] LIBRARIAN LOGIN\n" +
                        "[3] STUDENT LOGIN                    [4] EXIT");
                menuChoice = scanner.nextInt();

                switch (menuChoice) {
                    case 1:
                        adminLogin(scanner);
                        break;
                    case 2:
                        librarianLogin(scanner);
                        break;
                    case 3:
                        studentLogin(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting ...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (menuChoice != 4);
       
    }


    
     static void adminLogin(Scanner scanner)throws Exception{

        System.out.println(" *** ADMINISTRATOR LOGIN ***");
        System.out.println("USER NAME : ");
        String username = scanner.next();
        System.out.println("PASSWORD : ");
        String password = scanner.next();

        if (Administrator.adminLogin(username, password).equals("1")) {
            System.out.println();
            System.out.println("* * * ADMIN LOGINED SUCESSFULLY * * *");
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            System.out.println("        ADMINISTRATOR MENU            ");
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            System.out.println();
            System.out.println("ENTER YOUR OPTION : ");
            int adminChoice;
            do {
               
                System.out.println("\n[1] Register a new librarian                           [2] Delete a librarian\n" +
                           "[3] Print all librarians                               [4] Register a new student\n" +
                           "[5] Delete a student                                   [6] Print all students\n" +
                           "[7] Back");
                adminChoice = scanner.nextInt();

                switch (adminChoice) {
                    case 1:
                       Administrator.registerLibrarian();
                       break;
                    case 2:
                        Administrator.deleteLibrarian();
                        break;
                    case 3:
                        Administrator.printAllLibrarians();
                        break;
                    case 4:
                        Administrator.registerStudent();
                        break;
                    case 5:
                        Administrator.deleteStudent();
                        break;
                    case 6:
                        Administrator.printAllStudents();
                        break;
                    case 7:
                        displayOptions();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        displayOptions();
                 }
            } while (adminChoice != 7);
        }

        else{
            System.out.println();
            System.out.println("* * * ADMIN LOGIN FAILED  * * * ");
            System.out.println();
             displayOptions();
        }
    }

    static void librarianLogin(Scanner scanner) throws Exception {
        System.out.println("\n*** LIBRARIAN LOGIN***");

        System.out.println("Librarian - ID : ");
        int id = scanner.nextInt();
        System.out.print("Password: ");
        String password = scanner.next();

        if (Librarian.validateLibrarianLogin(id, password).equals("1")) {
            System.out.println();
            System.out.println("* * * LIBRARIAN LOGINED SUCESSFULLY * * *");
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            System.out.println("        LIBRARIAN  MENU            ");
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            System.out.println();
            System.out.println("ENTER YOUR OPTION : ");
            int librarianChoice;
            
            do {
             
                System.out.println(
                        "[1] Search Book                    [2] Add Book\n" +
                        "[3] Remove Book                    [4] View Books\n" +
                        "[5] View Payment Details           [6] Back");
    
                librarianChoice = scanner.nextInt();
    
                switch (librarianChoice) {
                    case 1:
                        Librarian.searchBook();
                        break;
                    case 2:
                        Librarian.addBook();
                        break;
                    case 3:
                        Librarian.removeBook();
                        break;
                    case 4:
                        Librarian.viewAllBooks();
                        break;
                    case 5 :
                        Librarian.viewPaymentDetails();
                        break;
                    case 6:
                        displayOptions();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        displayOptions();
                        break;
                 }
            } while (librarianChoice != 6);
        }
        
        else {
            System.out.println();
            System.out.println("* * * LIBRARIAN LOGIN FAILED  * * * ");
            System.out.println();
             displayOptions();
        }
    }

    static void studentLogin(Scanner scanner)throws Exception{

        System.out.println(" *** STUDENT LOGIN ***");
        System.out.println("Student - Id : ");
        int id = scanner.nextInt();
        System.out.println("Username : ");
        String username = scanner.next();
        System.out.println("Password : ");
        String password = scanner.next();


        if(Student.studentLogin(id, username, password).equals("1")){
            System.out.println();
            System.out.println("* * * STUDENT LOGINED SUCESSFULLY * * *");
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            System.out.println("        STUDENT  MENU            ");
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            
            System.out.println("ENTER YOUR OPTION: ");
            int studentChoice;
            
            do {
              
                System.out.println(
                "[1] View Book                           [2] Search Book\n" +
                "[3] Borrow Book                         [4] Return Book\n" +
                "[5] Exit");

                studentChoice = scanner.nextInt();
    
                switch (studentChoice) {
                    case 1 : 
                         Student.viewBooks();
                         break;
                    case 2:
                        Student.searchBook();
                        break;
                    case 3:
                        Student.borrowBook(id);
                        break;
                    case 4:
                    Student.returnBook(id);
                        break;
                    case 5:
                        displayOptions();
                        break;
                    
                    default:
                        System.out.println("Invalid choice.");
                        displayOptions();
                        break;
                 }
            } while (studentChoice != 5);
        }
        
        else {
            System.out.println();
            System.out.println("* * *  STUDENT LOGIN FAILED  * * * ");
            System.out.println();
             displayOptions();
        }
        }

    }

