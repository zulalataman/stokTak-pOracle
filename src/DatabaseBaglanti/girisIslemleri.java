
package DatabaseBaglanti;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//inheritance kullanımı
public class girisIslemleri extends AbstractDatabase{

   
    
    public boolean girisYap(String kullanici_adi, String sifre){
        String sorgu= "Select ID From KULLANICI where KULLANICIADI = ? and SIFRE = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sorgu);
            System.out.println(sorgu);
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2, sifre);
            ResultSet rs=preparedStatement.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    } 
    public void kullaniciEkle(String Tckimlik, String kullanici_adi, String sifre){
 
         String sorgu= "Insert Into KULLANICI (TCKIMLIK, KULLANICIADI, SIFRE) VALUES(?,?,?)";
         
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            
            preparedStatement.setString(1, Tckimlik);
            preparedStatement.setString(2, kullanici_adi);
            preparedStatement.setString(3, sifre);
            preparedStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Yeni kullanıcı eklendi");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Böyle bir kayıt zaten var!");
        }
     }

    @Override
    void abstractMetodYaz() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
