package com.example.bookmanagerver2.model;

public class Kindofbook {
    private int idLoaiSach;
    private String tenloaiSach;

    public Kindofbook() {
    }

    public Kindofbook(int idLoaiSach, String tenloaiSach) {
        this.idLoaiSach = idLoaiSach;
        this.tenloaiSach = tenloaiSach;
    }

    public int getIdLoaiSach() {
        return idLoaiSach;
    }

    public void setIdLoaiSach(int idLoaiSach) {
        this.idLoaiSach = idLoaiSach;
    }

    public String getTenloaiSach() {
        return tenloaiSach;
    }

    public void setTenloaiSach(String tenloaiSach) {
        this.tenloaiSach = tenloaiSach;
    }
}
