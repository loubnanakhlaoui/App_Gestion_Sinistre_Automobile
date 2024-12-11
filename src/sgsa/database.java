
package sgsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database {
    
    public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/insureds", "root", "");
            return connect;
        }catch(ClassNotFoundException | SQLException e){}
        return null;
    }
    
}
