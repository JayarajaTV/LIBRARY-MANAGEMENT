import java.sql.*;
import java.util.Date;
import java.util.*;





public class Librarian {
    

    static String validateLibrarianLogin(int id, String password)throws Exception{
        Connection con = ConnectionC.getConnect();
        
        CallableStatement cs = con.prepareCall("{call LibrarianLogin(?,?,?)}");
        cs.setInt(1, id);
        cs.setString(2,password);
        cs.registerOutParameter(3, Types.VARCHAR);
        
        cs.executeUpdate();

        String res = cs.getString(3);
        con.close();
        return res;
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

    static void addBook() throws Exception{

        Scanner get =  new Scanner(System.in);
        Connection con = ConnectionC.getConnect();


        System.out.println("ENTER THE BOOK ISBN ID : ");
        String bookISBN = get.nextLine();
        System.out.println("ENTER THE BOOK TITLE : ");
        String bookTitle = get.nextLine();
        System.out.println("ENTER THE BOOK AUTHOR : ");
        String bookAuthor = get.nextLine();
        System.out.println("ENTER THE BOOK PUBLISHER: ");
        String bookPublisher= get.nextLine();
        System.out.println("ENTER THE BOOK QUANTITY : ");
        int quantity = get.nextInt();

        String query  = "insert into books(isbnid , title , author , publisher ,quantity) values(?,?,?,?,?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,bookISBN);
        ps.setString(2, bookTitle);
        ps.setString(3, bookAuthor);
        ps.setString(4, bookPublisher);
        ps.setInt(5, quantity);

        ps.executeUpdate();

        System.out.println();
        System.out.println("* * * BOOK ADDED SUCESSFULLY * * *");
        System.out.println();
        con.close();

    }

 static void removeBook() throws Exception {
        Scanner get = new Scanner(System.in);
        Connection con = ConnectionC.getConnect();

        System.out.print("ENTER THE BOOK ISBN -ID : ");
        String isbnId = get.nextLine();

        String query = "{call removeBook(?,?)}";

        CallableStatement cal = con.prepareCall(query);
        cal.setString(1, isbnId);
        cal.registerOutParameter(2, Types.VARCHAR);

        cal.executeUpdate();

        String res = cal.getString(2);
        
        if(res.equals("1")){
            System.out.println();
            System.out.println("* * * BOOK  DELETED SUCESSFULLY * * * ");
            System.out.println();
        }
        else{
            System.out.println();
            System.out.println("* * * SORRY !!!BOOK ISBN - ID NOT FIND * * *");
            System.out.println();
        }
        con.close();
        
    }

static void viewAllBooks() throws Exception{
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

    static void viewPaymentDetails()throws Exception{
        Connection con = ConnectionC.getConnect();

        String query = " select * from fineDetails";
     
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        System.out.println("STUDENT_ID       RETURN_DATE    AMOUNT");
        System.out.println("------------     -----------    ------");
        System.out.println();

     while(rs.next()){
     
         int id = rs.getInt(2);
         Date date= rs.getDate(3);
         int amt = rs.getInt(4);
     
         System.out.println(id +"          "+date+"         " + amt);
         System.out.println();
         
           }

    }
}
