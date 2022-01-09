
package DatabaseBaglanti;


import com.islemler.musteri;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class musteriIslemleri extends AbstractDatabase implements ISil{

    
    
    //interface kullanımı
    @Override
    public void sil(int id) {
        String sorgu= "Delete from MUSTERILER where ID = ?";
           
        try {
            preparedStatement= connection.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<musteri> musteriGetir(){
        
        ArrayList<musteri> cikti2=new ArrayList<musteri>();
        
        try {
            statement= connection.createStatement();
            String sorgu= "Select * From MUSTERILER";
            ResultSet rs= statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                int id=rs.getInt("ID");
                String ad= rs.getString("AD");
                String soyad=rs.getString("SOYAD");
                String mail=rs.getString("MAIL");
                String telefon_numarasi=rs.getString("TELEFONNUMARASI");
               
                
                cikti2.add(new musteri(id, ad, soyad, mail, telefon_numarasi));
                
                        
            }
            return cikti2;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
        
         public void musteriGuncelle(int id, String yeniAd,String yeniSoyad,String yeniMail,String yeniTelefon_numarasi){
          String sorgu="Update MUSTERILER set AD = ?, SOYAD = ?, MAIL = ?, TELEFONNUMARASI = ? where ID = ?";
          
        try {
            preparedStatement = connection.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeniAd);
            preparedStatement.setString(2, yeniSoyad);
            preparedStatement.setString(3, yeniMail);
            preparedStatement.setString(4, yeniTelefon_numarasi);
            preparedStatement.setInt(5,id);
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
      }

        public void musteriEkle(String ad, String soyad, String mail, String telefon_numarasi){
 
         String sorgu= "Insert Into MUSTERILER (AD, SOYAD, MAIL, TELEFONNUMARASI) VALUES(?,?,?,?)";
         
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, telefon_numarasi);
            
           
            
            preparedStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Yeni müşteri eklendi");
            
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Böyle bir kayıt zaten var!");
        }
     }

    @Override
    void abstractMetodYaz() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     


    
}
