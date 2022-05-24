package com.example.bookmanagerver2.model;

import java.io.Serializable;

public class ThuThu implements Serializable {
    private String taiKhoan;
    private int maThuThu;
    private String maKhau;
    private String hoTenThuThu;
    private byte[] thuthuPhoto;
    public ThuThu() {
    }


    public ThuThu(int maThuThu, String maKhau) {
        this.maThuThu = maThuThu;
        this.maKhau = maKhau;
    }

    public ThuThu(String taiKhoan, int maThuThu, String maKhau, String hoTenThuThu, byte[] thuthuPhoto) {
        this.taiKhoan = taiKhoan;
        this.maThuThu = maThuThu;
        this.maKhau = maKhau;
        this.hoTenThuThu = hoTenThuThu;
        this.thuthuPhoto = thuthuPhoto;
    }

    public byte[] getThuthuPhoto() {
        return thuthuPhoto;
    }

    public void setThuthuPhoto(byte[] thuthuPhoto) {
        this.thuthuPhoto = thuthuPhoto;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(int maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getHoTenThuThu() {
        return hoTenThuThu;
    }

    public void setHoTenThuThu(String hoTenThuThu) {
        this.hoTenThuThu = hoTenThuThu;
    }
}
