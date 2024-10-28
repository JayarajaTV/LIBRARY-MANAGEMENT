import java.sql.*;

public class ConnectionC{
   private  static final  String url = "jdbc:mysql://localhost:3306/project";
   private static  final String user = "root";
   private static  final String password = "WJ28@krhps";
   
   public  static  Connection getConnect() throws Exception {
   
             return  DriverManager.getConnection(url,user,password);

   }
}
