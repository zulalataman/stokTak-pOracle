
package DatabaseBaglanti;
import static DatabaseBaglanti.DatabaseBaglanti.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



class DatabaseBaglanti {
    
    public static final String kullanici_adi = "root";
    public static final String parola = "";
 
    public Statement statement = null;
    public PreparedStatement preparedStatement = null;
    public static Connection connection;
    
    
    
        public DatabaseBaglanti(){
        try {
            if (connection == null || connection.isClosed()) {
                connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","Adalet.321");
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
                      
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }   
    }
    
    public void polymorphismMetodu(){
        System.out.println("Polymorphism Metodu");
    }
}

abstract class AbstractDatabase extends DatabaseBaglanti{
    abstract void abstractMetodYaz();
}

class MiniDatabase1 extends DatabaseBaglanti{

    @Override
    public void polymorphismMetodu() {
        System.out.println("Override Edilmiş Polymorphism Metodu");
    }  
}

class MiniDatabase2 extends DatabaseBaglanti{

    @Override
    public void polymorphismMetodu() {
        System.out.println("Override Edilmiş Polymorphism Metodu 2");
    }
}

public class Database{
    public static void main(String[] args) {
        DatabaseBaglanti baglanti = new DatabaseBaglanti();
        MiniDatabase1 mDatabase1 = new MiniDatabase1();
        MiniDatabase2 mDatabase2 = new MiniDatabase2();
        metod(baglanti);
        metod(mDatabase1);
        metod(mDatabase2);       
    }
    public static void metod(DatabaseBaglanti baglanti){
//        baglanti.polymorphismMetodu();
    }
    
        public static void closeConnection() {
        try {
            connection.rollback();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseBaglanti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
            public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","Adalet.321");
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
            
            
    protected void close(PreparedStatement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        
    }

    private void close(ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        protected void close(PreparedStatement stmt) {

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}