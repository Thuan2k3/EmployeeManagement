/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import database.DatabaseUtils;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author tranm
 */
public class NhanVienDAO {
    SimpleDateFormat format_date =  new SimpleDateFormat("yyyy/MM/dd");
    public static List<NhanVien> ls = new ArrayList<>();

    //Them phan tu nhan vien (CTDL)
//    public int add(NhanVien nv){
//        ls.add(nv);
//        return 1;
//    }
    
    //Them phan tu nhan vien (Database)
    public int add(NhanVien nv){
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "insert into NhanVien(MaNV,HoTen,GioiTinh,NgaySinh,DiaChi,SDT,Email,HinhAnh) values(?,?,?,?,?,?,?,?)";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, nv.getMaNV());
            sttm.setString(2, nv.getHoTen());
            sttm.setBoolean(3, nv.isGioiTinh());
            sttm.setString(4, format_date.format(nv.getNgaySinh()));
            sttm.setString(5, nv.getDiaChi());
            sttm.setString(6, nv.getSDT());
            sttm.setString(7, nv.getEmail());
            sttm.setString(8, nv.getHinhAnh());
            
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
    
    //Sua mot nhan vien khi biet nv (Database)
    public int updateNhanVienByID(NhanVien nv){
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "update NhanVien set HoTen=?, GioiTinh=?, NgaySinh = ?, DiaChi=?, SDT = ?,Email = ?, HinhAnh = ? where MaNV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(8, nv.getMaNV());
            sttm.setString(1, nv.getHoTen());
            sttm.setBoolean(2, nv.isGioiTinh());
            sttm.setString(3, format_date.format(nv.getNgaySinh()));
            sttm.setString(4, nv.getDiaChi());
            sttm.setString(5, nv.getSDT());
            sttm.setString(6, nv.getEmail());
            sttm.setString(7, nv.getHinhAnh());
            
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
    
    //Lay toan bo nhan vien (CTDL)
//    public List<NhanVien> getAllNhanVien(){
//        return ls;
//    }
    //Lay toan bo nhan vien (Database)
    public List<NhanVien> getAllNhanVien(){
        List<NhanVien> ls = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "select * from NhanVien";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setDiaChi(rs.getString(5));
                nv.setSDT(rs.getString(6));
                nv.setEmail(rs.getString(7));
                nv.setHinhAnh(rs.getString(8));
                ls.add(nv);
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
    
    //Xoa mot nhan vien khi biet id (CTDL)
//    public int delNhanVienByID(String id){
//        for (NhanVien nv : ls) {
//            if(nv.getMaNV().compareToIgnoreCase(id)==0){
//                ls.remove(nv);
//                return 1;
//            }
//        }
//        return -1;
//    }
    
    //Xoa mot nhan vien khi biet id (Database)
    public int delNhanVienByID(String maNV){
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "delete NhanVien where MaNV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, maNV);
            
            
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
    
    //Lay mot nhan vien khi biet id (CTDL)
//    public NhanVien getNhanVienByID(String id){
//        for(NhanVien nv : ls){
//            if(nv.getMaNV().compareToIgnoreCase(id)==0){
//                return nv;
//            }
//        }
//        return null;
//    }
    
    //Lay mot nhan vien khi biet id (Database)
    public NhanVien getNhanVienByID(String id){
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        NhanVien nv = new NhanVien();
        try {
            String sSQL = "select * from NhanVien where MaNV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, id);
            rs = sttm.executeQuery();
            while(rs.next()){
                nv.setMaNV(rs.getString(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setDiaChi(rs.getString(5));
                nv.setSDT(rs.getString(6));
                nv.setEmail(rs.getString(7));
                nv.setHinhAnh(rs.getString(8));
                return nv;
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
    
    //Sua mot nhan vien khi biet nvNew (CTDL)
//    public int updateNhanVienByID(NhanVien nvNew){
//        for(NhanVien nv : ls){
//            if(nv.getMaNV().compareToIgnoreCase(nvNew.getMaNV())==0){
//                nv.setHoTen(nvNew.getHoTen());
//                nv.setGioiTinh(nvNew.isGioiTinh());
//                nv.setNgaySinh(nvNew.getNgaySinh());
//                nv.setDiaChi(nvNew.getDiaChi());
//                nv.setSDT(nvNew.getSDT());
//                nv.setEmail(nvNew.getEmail());
//                nv.setHinhAnh(nvNew.getHinhAnh());
//                return 1;
//            } 
//        }
//        return -1;
//    }
    
}

