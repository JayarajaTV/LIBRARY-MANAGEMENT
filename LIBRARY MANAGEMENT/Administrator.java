import java.sql.*;
import java.util.*;

public class Administrator {
    

    
     static String adminLogin(String userName, String password ) throws Exception{
            Connection con = ConnectionC.getConnect();
        
            CallableStatement cs = con.prepareCall("{call adminLogin(?,?,?)}");
            cs.setString(1,userName);
            cs.setString(2,password);
            cs.registerOutParameter(3, Types.VARCHAR);
            
            cs.executeUpdate();

            String res = cs.getString(3);
            con.close();
            return res;

    }


     static void registerLibrarian() throws Exception {
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();
       
        System.out.print("Enter the librarian's name: ");
        String librarianName = get.next();
        System.out.print("Enter the librarian's Email : ");
        String librarianEmail = get.next();
        System.out.print("Enter the librarian's User id: ");
        int librarianUserId = get.nextInt();
        System.out.print("Enter the librarian's Password: ");
        String librarianPassword = get.next();

       String query = "insert into librarians(name , email , librarianId , password) values(?,?,?,?);";
       
       PreparedStatement pre = con.prepareStatement(query);
       pre.setString(1, librarianName);
       pre.setString(2, librarianEmail);
       pre.setInt(3, librarianUserId);
       pre.setString(4, librarianPassword);

       pre.executeUpdate();
       
       System.out.println();
       System.out.println("* * * NEW LIBRARIAN  CREATED SUCESSFULLY * * * ");
       System.out.println();
       con.close();
    }


    static void deleteLibrarian() throws Exception {
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();

        System.out.print("Enter the librarian's ID Number: ");
        int librarianIDNumber = get.nextInt();

        String query = "{call deleteLibrarian(?,?)}";

        CallableStatement cal = con.prepareCall(query);
        cal.setInt(1, librarianIDNumber);
        cal.registerOutParameter(2, Types.VARCHAR);

        cal.executeUpdate();

        String res = cal.getString(2);
        
        if(res.equals("1")){
            System.out.println();
            System.out.println("* * * LIBRARIAN  DELETED SUCESSFULLY * * * ");
            System.out.println();
        }
        else{
            System.out.println();
            System.out.println("* * * SORRY !!! LIBRARIAN ID NOT FIND * * *");
            System.out.println();
        }
        con.close();
        
    }

     static void printAllLibrarians() throws Exception {

            String sql = "SELECT  * FROM librarians";
            Connection con = ConnectionC.getConnect();

            Statement st = con.createStatement();
            ResultSet rs  = st.executeQuery(sql);
            int count = 1 ;
            while(rs.next()){

              
                String librarianName = rs.getString(2);
                String librarianEmail = rs.getString(3);
                int librarianId = rs.getInt(4);
                String librarianPassword = rs.getString(5);
                
                System.out.println();
                System.out.println("LIBRARIAN "+count++);
                System.out.println();
               
                System.out.println("LIBRARIAN NAME : "+ librarianName);
                System.out.println("LIBRARIAN EMAIL : "+librarianEmail);
                System.out.println("LIBRARIAN - ID : "+ librarianId);
                System.out.println("PASSWORD : "+librarianPassword);
                System.out.println();

            }
            con.close();

    }

     static void registerStudent() throws Exception {
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();

        System.out.println();
        System.out.println("Register a new student ...");
        System.out.println();       


        
        System.out.print("Enter the student's name: ");
        String studentName = get.next();
        System.out.print("Enter the student's Email Address: ");
        String studentEmail = get.next();
        System.out.print("Enter the student's username: ");
        String studentUserName = get.next();
        System.out.print("Enter the student's password: ");
        String studentPassword = get.next();

        String sql = "INSERT INTO students ( name, email, username, password) VALUES ( ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, studentName);
        pstmt.setString(2, studentEmail);
        pstmt.setString(3, studentUserName);
        pstmt.setString(4, studentPassword);
        pstmt.executeUpdate();

        System.out.println();
        System.out.println("Student successfully registered!");
        System.out.println();

        con.close();
    }

    static void deleteStudent() throws Exception {
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();

        System.out.print("Enter the student's  ID Number: ");
        int studentIDNumber = get.nextInt();

        String query = "{call deleteStudent(?,?)}";

        CallableStatement cal = con.prepareCall(query);
        cal.setInt(1, studentIDNumber);
        cal.registerOutParameter(2, Types.VARCHAR);

        cal.executeUpdate();

        String res = cal.getString(2);
        
        if(res.equals("1")){
            System.out.println();
            System.out.println("* * * STUDENT  DELETED SUCESSFULLY * * * ");
            System.out.println();
        }
        else{
            System.out.println();
            System.out.println("* * * SORRY !!! USER_ID NOT FIND * * *");
            System.out.println();
        }
        con.close();
        
    }

     static void printAllStudents() throws Exception {

        String sql = "SELECT  * FROM students";
        Connection con = ConnectionC.getConnect();

        Statement st = con.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        int count = 1 ;
        while(rs.next()){

            int studentId = rs.getInt(1);
            String studentName = rs.getString(2);
            String studentEmail = rs.getString(3);
            String userName = rs.getString(4);
            String userPassword = rs.getString(5);
            
            System.out.println();
            System.out.println("STUDENT "+count++);
            System.out.println();
            System.out.println("USER - ID : "+ studentId);
            System.out.println("STUDENT NAME : "+ studentName);
            System.out.println("STUDENT EMAIL : "+studentEmail);
            System.out.println("USER-NAME : "+userName);
            System.out.println("PASSWORD : "+userPassword);
            System.out.println();

        }
        con.close();
    }   


    }



