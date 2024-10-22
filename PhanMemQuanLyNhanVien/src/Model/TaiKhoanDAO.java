/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import database.DatabaseUtils;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author tranm
 */
public class TaiKhoanDAO {
    //Tao 1 ds cac tai khoan (CSDL)
//    List<TaiKhoan> ls = new ArrayList<>();
//
//    public TaiKhoanDAO() {
//        ls.add(new TaiKhoan("admin","1234",true));
//        ls.add(new TaiKhoan("thuan","12345678",true));
//        ls.add(new TaiKhoan("thuan2003","123456",true));
//    }
    
    //Lay 1 tai khoan khi biet taiKhoan (Database)
    public TaiKhoan getTaiKhoanByID(String taiKhoan){
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        TaiKhoan tk = new TaiKhoan();
        try {
            String sSQL = "select * from TaiKhoan where TaiKhoan = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, taiKhoan);
            rs = sttm.executeQuery();
            while(rs.next()){
                tk.setTaiKhoan(rs.getString(1));
                tk.setMatKhau(rs.getString(2));
                tk.setVaiTro(rs.getBoolean(3));
                return tk;
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }finally{
            try{
                conn.close();
                rs.close();
                sttm.close();
            }catch(Exception e){
                
            }
        }
        return null;
    }
    
    //Kiem tra dang nhap (Database)
    public boolean checkLogin(String taiKhoan, String matKhau){
        TaiKhoan tk = getTaiKhoanByID(taiKhoan);
        if(tk!=null){
            //tk ton tai
            if(tk.getMatKhau().compareTo(matKhau)==0){
                return true;
            }
        }
        return false;
    }
    
    //Kiem tra dang nhap (CTDL)
//    public boolean checkLogin(String taiKhoan, String matKhau){
//        for (TaiKhoan t : ls) {
//            if(t.getTaiKhoan().compareTo(taiKhoan)==0 
//                    && t.getMatKhau().compareTo(matKhau)==0){
//                return true;
//            }
//        }
//        return false;
//    }
}
