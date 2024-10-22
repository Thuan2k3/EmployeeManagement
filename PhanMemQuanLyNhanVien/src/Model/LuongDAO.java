/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import database.DatabaseUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranm
 */
public class LuongDAO {
    List<Luong> ls = new ArrayList<>();
    //Them phan tu luong su dung CTDL
//    public int add(Luong l){
//        ls.add(l);
//        return 1;
//    }
    //Them phan tu luong su dung Database
    public int add(Luong l){
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "insert into Luong(MaNV,LuongNgay,NgayCong,PhuCap,BaoHiem) values(?,?,?,?,?)";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, l.getNv().getMaNV());
            sttm.setDouble(2, l.getLuongNgay());
            sttm.setDouble(3, l.getNgayCong());
            sttm.setDouble(4, l.getPhuCap());
            sttm.setDouble(5, l.getBaoHiem());
            
            
            if(sttm.executeUpdate()>0){
                System.out.println("Thêm thành công");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }finally{
            try{
                conn.close();
                sttm.close();
            }catch(Exception e){
                
            }
        }
        return -1; //Neu them khong thanh cong
    }
    //Lay tat ca phan tu luong (CTDL)
//    public List<Luong> getAllLuong(){
//        return ls;
//    }
    //Lay tat ca phan tu luong (Database)
    public List<Luong> getAllLuong(){
        List<Luong> ls = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT dbo.Luong.MaNV, dbo.NhanVien.HoTen, dbo.Luong.LuongNgay, dbo.Luong.NgayCong, dbo.Luong.PhuCap, dbo.Luong.BaoHiem FROM dbo.Luong INNER JOIN dbo.NhanVien ON dbo.Luong.MaNV = dbo.NhanVien.MaNV";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while(rs.next()){
                Luong l = new Luong();
                l.setNv(new NhanVien(rs.getString(1),rs.getString(2)));
                l.setLuongNgay(rs.getDouble(3));
                l.setNgayCong(rs.getDouble(4));
                l.setPhuCap(rs.getDouble(5));
                l.setBaoHiem(rs.getDouble(6));
                ls.add(l);
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
        return ls;
    }
    //Lay mot phan tu luong khi biet maNV (CTDL)
//    public Luong getOneLuongByMaNV(String manv){
//        for (Luong l : ls) {
//            if(l.getNv().getMaNV().compareToIgnoreCase(manv)==0){
//                return l;
//            }
//        }
//        return null;
//    }
    //Lay mot phan tu luong khi biet maNV (Database)
    public Luong getOneLuongByMaNV(String manv){
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT dbo.Luong.MaNV, dbo.NhanVien.HoTen, dbo.Luong.LuongNgay, dbo.Luong.NgayCong, dbo.Luong.PhuCap, dbo.Luong.BaoHiem FROM dbo.Luong INNER JOIN dbo.NhanVien ON dbo.Luong.MaNV = dbo.NhanVien.MaNV where Luong.MaNV=?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, manv);
            rs = sttm.executeQuery();
            while(rs.next()){
                Luong l = new Luong();
                l.setNv(new NhanVien(rs.getString(1),rs.getString(2)));
                l.setLuongNgay(rs.getDouble(3));
                l.setNgayCong(rs.getDouble(4));
                l.setPhuCap(rs.getDouble(5));
                l.setBaoHiem(rs.getDouble(6));
                return l;
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
    
    //Sua 1 phan tu luong khi biet lNew (CTDL)
//    public int updateLuong(Luong lNew){
//        for (Luong l : ls) {
//            if(l.getNv().getMaNV().compareToIgnoreCase(lNew.getNv().getMaNV())==0){
//                l.setLuongNgay(lNew.getLuongNgay());
//                l.setNgayCong(lNew.getNgayCong());
//                l.setPhuCap(lNew.getPhuCap());
//                l.setBaoHiem(lNew.getBaoHiem());
//                return 1;
//            }
//        }
//        return -1;
//    }
    
    //Sua 1 phan tu luong khi biet lNew (Database)
    public int updateLuong(Luong l){
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "update Luong set LuongNgay=?, NgayCong=?, PhuCap=?, BaoHiem=? where MaNV=?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(5, l.getNv().getMaNV());
            sttm.setDouble(1, l.getLuongNgay());
            sttm.setDouble(2, l.getNgayCong());
            sttm.setDouble(3, l.getPhuCap());
            sttm.setDouble(4, l.getBaoHiem());
            
            
            if(sttm.executeUpdate()>0){
                System.out.println("Cập nhật thành công");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }finally{
            try{
                conn.close();
                sttm.close();
            }catch(Exception e){
                
            }
        }
        return -1; //Neu cap nhat khong thanh cong
    }
    
    //Xoa mot phan tu luong khi biet manv (CTDL)
//    public int delLuongByMaNV(String manv){
//        Luong l = getOneLuongByMaNV(manv);
//        if(l != null){
//            ls.remove(l);
//            return 1;
//        }
//        return -1;
//    }
    
    //Xoa mot phan tu luong khi biet manv (Database)
    public int delLuongByMaNV(String manv){
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "delete Luong where MaNV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, manv);
            
            
            if(sttm.executeUpdate()>0){
                System.out.println("Xóa thành công");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }finally{
            try{
                conn.close();
                sttm.close();
            }catch(Exception e){
                
            }
        }
        return -1; //Neu xoa khong thanh cong
    }
    //Lay phan tu luong tai vi tri pos
    public Luong getAtPosition(int pos){
        return ls.get(pos);
    }

    
}
