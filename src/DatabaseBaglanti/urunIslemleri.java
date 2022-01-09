
package DatabaseBaglanti;

import com.islemler.urun;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class urunIslemleri extends AbstractDatabase implements ISil{

    
    
    @Override
    public void sil(int id) {
        String sorgu= "Delete from URUNLER where ID = ?";
           
        try {
            preparedStatement= connection.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public ArrayList<urun> urunGetir(){
        
        ArrayList<urun> cikti=new ArrayList<urun>();
        
        try {
            statement= connection.createStatement();
            String sorgu= "Select * From URUNLER";
            ResultSet rs= statement.executeQuery(sorgu); 
            
            while(rs.next()){
                int id=rs.getInt("ID");
                String stok_kodu= rs.getString("STOKKODU");
                String kitap_adı=rs.getString("KITAPADI");
                String yazar=rs.getString("YAZAR");
                int adet=rs.getInt("ADET");
                double fiyat=rs.getDouble("FIYAT");
                String tür=rs.getString("TUR");
                
                cikti.add(new urun(id, stok_kodu, kitap_adı, yazar, adet, fiyat, tür));
                
                        
            }
            return cikti;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
      
        
    }
         
      
        public void urunGuncelle(int id, String yeniStok_kodu,String yeniKitap_adı,String yeniYazar,int yeniAdet,double yeniFiyat,String yeniTür){
          String sorgu="Update URUNLER set STOKKODU = ?, KITAPADI = ?, YAZAR = ?, ADET = ?, FIYAT = ?, TUR = ? where ID = ?";
          
        try {
            
           //set kullanımı
            preparedStatement = connection.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeniStok_kodu);
            preparedStatement.setString(2, yeniKitap_adı);
            preparedStatement.setString(3, yeniYazar);
            preparedStatement.setInt(4, yeniAdet);
            preparedStatement.setDouble(5, yeniFiyat);
            preparedStatement.setString(6, yeniTür);
            preparedStatement.setInt(7,id);
            preparedStatement.executeQuery();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      } 

         public void urunEkle(String stok_kodu,String kitap_adı,String yazar,int adet,double fiyat,String tür){
 
         String sorgu= "Insert Into URUNLER (STOKKODU, KITAPADI, YAZAR, ADET, FIYAT, TUR) VALUES(?,?,?,?,?,?)";
         
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            
            preparedStatement.setString(1, stok_kodu);
            preparedStatement.setString(2, kitap_adı);
            preparedStatement.setString(3, yazar);
            preparedStatement.setInt(4, adet);
            preparedStatement.setDouble(5, fiyat);
            preparedStatement.setString(6, tür);
            preparedStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Yeni ürün eklendi");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Böyle bir kayıt zaten var!");
        }
     }

    @Override
    void abstractMetodYaz() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
