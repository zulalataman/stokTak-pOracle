
package DatabaseBaglanti;

import com.islemler.musteri;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.islemler.satis;
import com.islemler.urun;


public class satisIslemleri extends AbstractDatabase{

    
    
    public ArrayList<satis> satisGetir(){
        
        ArrayList<satis> cikti5=new ArrayList<satis>();
        
        try {
            statement= connection.createStatement();
            String sorgu= "Select * From SATIS";
            ResultSet rs= statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                int id=rs.getInt("ID");
                String musteri_adi= rs.getString("MUSTERIADI");
                String musteri_soyadi=rs.getString("MUSTERISOYADI");
                String urun_stokKodu=rs.getString("URUNSTOKKODU");
                String urun_adi=rs.getString("URUNADI");
                int satılan_adet=rs.getInt("SATILANADET");
                double urun_fiyati=rs.getDouble("URUNBIRIMFIYATI");
                double toplam_tutar=rs.getDouble("TOPLAMTUTAR");
                
                cikti5.add(new satis(id, musteri_adi, musteri_soyadi, urun_stokKodu, urun_adi, satılan_adet, urun_fiyati, toplam_tutar));
                
                        
            }
            return cikti5;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
           public ArrayList<urun> urunGetir2(){
        
        ArrayList<urun> cikti3=new ArrayList<urun>();
        
        try {
            statement= connection.createStatement();
            String sorgu= "Select * From URUNLER";
            ResultSet rs= statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                int id=rs.getInt("ID");
                String stok_kodu= rs.getString("STOKKODU");
                String kitap_adı=rs.getString("KITAPADI");
                int adet=rs.getInt("ADET");
                double fiyat=rs.getDouble("FIYAT");
                
                cikti3.add(new urun(id,stok_kodu, kitap_adı, adet, fiyat));
                
                        
            }
            return cikti3;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
           }
           public ArrayList<musteri> musteriGetir2(){
        
        ArrayList<musteri> cikti4=new ArrayList<musteri>();
        
        try {
            statement= connection.createStatement();
            String sorgu= "Select * From MUSTERILER";
            ResultSet rs= statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                int id=rs.getInt("ID");
                String ad= rs.getString("AD");
                String soyad=rs.getString("SOYAD");
               
                
                cikti4.add(new musteri(id, ad, soyad));
                
                        
            }
            return cikti4;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
      public void satisEkle(String musteri_adi, String musteri_soyadi, String urun_stokKodu, String urun_adi,int satılan_adet, double urun_fiyati, double toplam_tutar ){
 
         String sorgu= "Insert Into SATIS (MUSTERIADI, MUSTERISOYADI, URUNSTOKKODU, URUNADI, SATILANADET, URUNBIRIMFIYATI, TOPLAMTUTAR) VALUES(?,?,?,?,?,?,?)";
         
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            
            preparedStatement.setString(1, musteri_adi);
            preparedStatement.setString(2, musteri_soyadi);
            preparedStatement.setString(3, urun_stokKodu);
            preparedStatement.setString(4, urun_adi);
            preparedStatement.setInt(5, satılan_adet);
            preparedStatement.setDouble(6, urun_fiyati);
            preparedStatement.setDouble(7, toplam_tutar);
            
            
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      public void stokGuncelle(int id, int yeni_adet){
          
          String sorgu="Update URUNLER set ADET = ? where ID = ?";
          
          try {
            preparedStatement = connection.prepareStatement(sorgu);
            
            preparedStatement.setInt(1, yeni_adet);
            preparedStatement.setInt(2,id);
           
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }

    @Override
    void abstractMetodYaz() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
