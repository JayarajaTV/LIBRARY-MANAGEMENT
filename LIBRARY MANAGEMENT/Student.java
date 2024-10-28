import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.temporal.ChronoUnit;


import java.time.LocalDate;


public class Student {


    static String studentLogin(int id , String userName , String password)throws Exception{
        Connection con = ConnectionC.getConnect();
        
        CallableStatement cs = con.prepareCall("{call studentLogin(?,?,?,?)}");
        cs.setInt(1, id);
        cs.setString(2,userName);
        cs.setString(3,password);
        cs.registerOutParameter(4, Types.VARCHAR);
        
        cs.executeUpdate();

        String res = cs.getString(4);
        con.close();
        return res;
    }

    static void viewBooks()throws Exception{
      Connection con = ConnectionC.getConnect();

       String query = " select * from books";

      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      int count = 1;
      while(rs.next()){

        String bookISBN = rs.getString(2);
        String bookTitle = rs.getString(3);
        String bookPublisher = rs.getString(5);
        int quantity = rs.getInt(6);
    
        System.out.println();
        System.out.println("BOOK NUMBER :"+count++);
        System.out.println();
        System.out.println("BOOK ISBN : " + bookISBN);
        System.out.println("BOOK TITLE : "+ bookTitle);
        System.out.println("BOOK PUBLISHER : "+ bookPublisher);
        System.out.println("BOOK QUANTITY : "+ quantity);

      }

   }



    static void searchBook()throws Exception{

        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();

        System.out.println();
        System.out.println("1. Search by ISBN\n2. Search by Author\n3. Search by Title");
        System.out.println();
        int searchChoice = get.nextInt();
        get.nextLine();

        switch (searchChoice) {
            case 1:{
               
                System.out.println();
                System.out.println("ENTER BOOK ISBN ID : ");
                String isbn = get.nextLine();

                String query = "{call checkISBN(? , ? )}";

                CallableStatement cs = con.prepareCall(query);
                cs.setString(1, isbn);
                cs.registerOutParameter(2, Types.VARCHAR);

                cs.executeUpdate();

                String res = cs.getString(2);

                if(res.equals("1")){
                    String que = "select * from books where ISBNID=  '"+isbn+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(que);
                    rs.next();
                    
                   String bookISBN = rs.getString(2);
                   String bookTitle = rs.getString(3);
                   String bookAuthor = rs.getString(4);
                   String bookPublisher = rs.getString(5);
                   int quantity = rs.getInt(6);
    
                   System.out.println();
                   System.out.println("BOOK ISBN : " + bookISBN);
                   System.out.println("BOOK TITLE : "+ bookTitle);
                   System.out.println("BOOK AUTHOR : "+ bookAuthor);
                   System.out.println("BOOK PUBLISHER : "+ bookPublisher);
                   System.out.println("BOOK QUANTITY : "+ quantity);
                }
                else{
                    System.out.println();
                    System.out.println("* * * BOOK ISBN ID NOT EXISTS * * *");
                    System.out.println();
                }


            }
                
                break;

            case 2 : 
                {
                   
                System.out.println();
                System.out.println("ENTER BOOK AUTHOR NAME : ");
                String authorName = get.nextLine();

                String query1 = "{call checkAuthorName(? , ? )}";

                CallableStatement cs = con.prepareCall(query1);
                cs.setString(1, authorName);
                cs.registerOutParameter(2, Types.VARCHAR);

                cs.executeUpdate();

                String res = cs.getString(2);

                if(res.equals("1")){
                    String query = "select * from books where author  = '"+authorName+"'";
                    Statement st = con.createStatement();
                    ResultSet rs  = st.executeQuery(query);
    
                    int count = 1;
                    while(rs.next()){
    
                        String bookISBN = rs.getString(2);
                        String bookTitle = rs.getString(3);
                        String bookPublisher = rs.getString(5);
                        int quantity = rs.getInt(6);
    
                        System.out.println();
                        System.out.println("BOOK NUMBER :"+count++);
                        System.out.println();
                        System.out.println("BOOK ISBN : " + bookISBN);
                        System.out.println("BOOK TITLE : "+ bookTitle);
                        System.out.println("BOOK PUBLISHER : "+ bookPublisher);
                        System.out.println("BOOK QUANTITY : "+ quantity);
    
                    }
                }
                else{
                    System.out.println();
                    System.out.println("* * * BOOK AUTHOR NAME NOT EXISTS * * *");
                    System.out.println();
                }
               
                }
                break;
            
            case 3 : {
                System.out.println();
                System.out.println("ENTER BOOK TITLE : ");
                String booktitle = get.nextLine();

                String query1 = "{call checkBookTitle(? , ? )}";

                CallableStatement cs = con.prepareCall(query1);
                cs.setString(1, booktitle);
                cs.registerOutParameter(2, Types.VARCHAR);

                cs.executeUpdate();

                String res = cs.getString(2);

                if(res.equals("1")){
                    String que = "select * from books where title=  '"+booktitle+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(que);
                    rs.next();
                    
                   String bookISBN = rs.getString(2);
                   String bookTitle = rs.getString(3);
                   String bookAuthor = rs.getString(4);
                   String bookPublisher = rs.getString(5);
                   int quantity = rs.getInt(6);
    
                   System.out.println("BOOK ISBN : " + bookISBN);
                   System.out.println("BOOK TITLE : "+ bookTitle);
                   System.out.println("BOOK AUTHOR : "+ bookAuthor);
                   System.out.println("BOOK PUBLISHER : "+ bookPublisher);
                   System.out.println("BOOK QUANTITY : "+ quantity);
                }
                

                else{

                    System.out.println();
                    System.out.println("* * * BOOK TITLE NOT EXISTS * * *");
                    System.out.println();
                
                }


            }
               break;
        
            default:
                System.out.println("----> ENTER THE VALID OPTION  <----");
                break;
        }



    }

    static void borrowBook(int stuId)throws Exception{
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();



       

         System.out.println("ENTER THE BOOK TITLE : ");
         String bookTitle = get.nextLine();
         
         // CHECK IF THE STUDENT ALREADY BY IS BOOK OR NOT

         String query0 = "select * from borrowingDetails where student_id = ? and book_title = ?";

                    PreparedStatement ps5 = con.prepareStatement(query0);
                    ps5.setInt(1, stuId);
                    ps5.setString(2, bookTitle);
                    ResultSet rs5 = ps5.executeQuery();

                    if(rs5.next()){
                        System.out.println();
                    System.out.println("* * * BOOK ALREADY BORROWED * * *");
                    System.out.println();
                    }
        else{
         //CHECK IF THE BOOK AVAILABLE OR NOT
         String query1 = "{call checkBook(? , ? )}";

         CallableStatement cs = con.prepareCall(query1);
         cs.setString(1, bookTitle);
         cs.registerOutParameter(2, Types.VARCHAR);

         cs.executeUpdate();

         String res = cs.getString(2);

         

         if(res.equals("1")){

         // CHECKING THE STUDENT ID PRESENT OR NOT
          String query2 = "{call checkStudentId(? , ? )}";

          CallableStatement cs1 = con.prepareCall(query2);
          cs1.setInt(1, stuId);
          cs1.registerOutParameter(2, Types.VARCHAR);

          cs1.executeUpdate();

          String res1 = cs1.getString(2);
          
          //CHECK THE STUDENT LIMIT EXCEEDS OR NOT ( 3 BOOKS MAXIMUM )
           
          String q= "{call checkLimite(?,?)}";

          CallableStatement cal = con.prepareCall(q);
          cal.setInt(1,stuId);
          cal.registerOutParameter(2, Types.VARCHAR);
          cal.executeUpdate();

          String re = cal.getString(2);

           if(re.equals("0")){
              System.out.println();
              System.out.println("* * * MAXIMIM LIMIT REACHED FOR THIS STUDENT - ID * * * ");
              System.out.println();
              return;
            
           }

           if(res1.equals("1") ) {
            LocalDate todayDate = LocalDate.now();
            LocalDate returnDate = todayDate.plusDays(30);
            
            //CONVERTING JAVA DATE TO SQL DATE
            Date sqlBorrowDate = Date.valueOf(todayDate);
            Date sqlRetuDate = Date.valueOf(returnDate);
 
            


             String query = "insert into borrowingDetails(book_title , student_id , borrowing_date , return_date) values(?,?,?,?)";
             
             PreparedStatement pstm = con.prepareStatement(query);
             pstm.setString(1, bookTitle);
             pstm.setInt(2, stuId);
             pstm.setDate(3, sqlBorrowDate);
             pstm.setDate(4, sqlRetuDate);
             
             pstm.executeUpdate();

             System.out.println();
             System.out.println(" * * * Book { "+bookTitle+" }  BORROWED SUCESSFULLY * * * ");
             System.out.println();
             System.out.println("YOU WANT TO RETURN THAT BOOK BEFORE--- > "+returnDate+" <---");
            
             System.out.println();


            

            //CHANGING THE BOOK QUANTITY

             String str = "{ call editBookQuantity(?,?)}";
             CallableStatement ps3 = con.prepareCall(str);
             ps3.setString(1, bookTitle);
             ps3.registerOutParameter(2,Types.VARCHAR);
             ps3.executeUpdate();



           }
            else{
                 System.out.println();
                 System.out.println("* * * STUDENT - ID NOT FOUND* * *");
                 System.out.println();
             }
          }





           
          else{
            System.out.println();
            System.out.println("* * *YOU CAN'T BORROW THIS BOOK * * *");
            System.out.println();
         }
         con.close();


    }
        }
        

    static void returnBook(int stuId)throws Exception{
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();

        //   System.out.println("ENTER THE STUDENT - ID : ");
        //   int stuId = get.nextInt();
        //   get.nextLine();


        ///CHECKING THE STUDENT ID PRESENT OR NOT
          String query = "{call checkStudentId(? , ? )}";

          CallableStatement cs = con.prepareCall(query);
          cs.setInt(1, stuId);
          cs.registerOutParameter(2, Types.VARCHAR);

          cs.executeUpdate();

          String res = cs.getString(2);
          
        if(res.equals("1")){
              
             // CHEAKING IF ANY BOOK BORROWED BY USING THIS ID 
              String query1 = "{call checkBorrowStatue(?,?)}";

              CallableStatement cs1 = con.prepareCall(query1);
              cs1.setInt(1, stuId);
              cs1.registerOutParameter(2, Types.VARCHAR);

              cs1.executeUpdate();

              String res1 = cs1.getString(2);
              
              if(res1.equals("1")){
                   
                String query2 = "select book_title from borrowingDetails where student_id = "+stuId;
                
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(query2);
                
                System.out.println("THE FOLLOWING BOOKS YOU WANT TO RETURN : ");
                 int count = 1 ;

                while(rs.next()){
                    String bookName = rs.getString(1);
                    System.out.println(count++ +" " +  bookName);
                }
                System.out.println();
                System.out.println("ENTER THE BOOK NAME TO RETURN (IF MORE THEN ONE USE (') ) SYMBOL ) :");
                String bookNames = get.nextLine();
                String arr[] = bookNames.split("'"); //TO RETURN MORE THEN ONE BOOK

                //CHECK THE FINE DETAILS
                LocalDate todayDate = LocalDate.now();
                int totalFine = 0;

                for(String bName : arr){

                    String query3 = "select * from borrowingDetails where student_id = ? and book_title = ?";

                    PreparedStatement ps5 = con.prepareStatement(query3);
                    ps5.setInt(1, stuId);
                    ps5.setString(2, bName.trim());
                    ResultSet rs5 = ps5.executeQuery();
                    
                    rs5.next();
                    Date sqldate = rs5.getDate(4);
                    LocalDate ld = sqldate.toLocalDate();
                    long daysDifference = ChronoUnit.DAYS.between(ld, todayDate);

                    if(daysDifference > 0){
                        totalFine = (int)daysDifference * 2 ;
                         }
               
                 }
               
                if(totalFine > 0){

                    System.out.println("TOTAL FINE TO PAY : RS " + totalFine);
                    
                    System.out.println("PAY THE AMOUNT : ");
                    int amount = get.nextInt();
                    Date returnDate = Date.valueOf(todayDate);


                    String query4 = "insert into fineDetails(student_id , return_date , amount) values(?,?,?)";
                    PreparedStatement pst = con.prepareStatement(query4);
                    pst.setInt(1, stuId);
                    pst.setDate(2, returnDate);
                    pst.setInt(3, amount);
                    pst.executeUpdate();
                 
                    System.out.println();
                    System.out.println(" * * * AMOUNT PAYED SUCCESSFULLY AND BOOK RETURNED SUCESSFULLY * * * ");
                    System.out.println();

                }
                else{
                    System.out.println();
                    System.out.println(" * * * BOOK RETURNED SUCESSFULLY * * * ");
                    System.out.println();
                }

                 // AFTER RETURNING THE BOOK  TO DELETE DETAILS IN BORROWING TABLE
                 for(String bName : arr){

                      String query5 = "delete from borrowingDetails where student_id = ? and book_title = ?";

                      PreparedStatement ps6 = con.prepareStatement(query5);
                      ps6.setInt(1, stuId);
                      ps6.setString(2, bName.trim());
                      ps6.executeUpdate();
                    
                      }

              }

              else{
                System.out.println(" * * * THERE IS NO BOOK TO RETURN FOR THIS STUDENT- ID  * * * ");
                System.out.println();
              }

        }
        else{
            System.out.println("* * * STUDENT- ID NOT FOUND ! PLEASE TRY AGAIN ");
            System.out.println();
        }
        

    }

    }

