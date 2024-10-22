/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tranm
 */
public class Luong {
    private int id;
    private NhanVien nv;
    private double luongNgay;
    private double ngayCong;
    private double phuCap;
    private double baoHiem;

    public Luong() {
    }

    public Luong(int id, NhanVien nv, double luongNgay, double ngayCong, double phuCap, double baoHiem) {
        this.id = id;
        this.nv = nv;
        this.luongNgay = luongNgay;
        this.ngayCong = ngayCong;
        this.phuCap = phuCap;
        this.baoHiem = baoHiem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public double getLuongNgay() {
        return luongNgay;
    }

    public void setLuongNgay(double luongNgay) {
        this.luongNgay = luongNgay;
    }

    public double getNgayCong() {
        return ngayCong;
    }

    public void setNgayCong(double ngayCong) {
        this.ngayCong = ngayCong;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getBaoHiem() {
        return baoHiem;
    }

    public void setBaoHiem(double baoHiem) {
        this.baoHiem = baoHiem;
    }
    
    public double getLuongNhanDuoc(){
        return getLuongNgay()*getNgayCong()+getPhuCap()-getBaoHiem();
    }
    
}
