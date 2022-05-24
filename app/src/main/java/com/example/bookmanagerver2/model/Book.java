package com.example.bookmanagerver2.model;

public class Book {
    private int id;
    private String tenSach;
    private String tenLoaiSach;
    private float money;
    private int moneyKhuyenmai;
    private int idLoaiSach;

    public Book() {
    }

    public Book(int id, String tenSach, String tenLoaiSach, float money, int moneyKhuyenmai, int idLoaiSach) {
        this.id = id;
        this.tenSach = tenSach;
        this.tenLoaiSach = tenLoaiSach;
        this.money = money;
        this.moneyKhuyenmai = moneyKhuyenmai;
        this.idLoaiSach = idLoaiSach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getMoneyKhuyenmai() {
        return moneyKhuyenmai;
    }

    public void setMoneyKhuyenmai(int moneyKhuyenmai) {
        this.moneyKhuyenmai = moneyKhuyenmai;
    }


    public int getIdLoaiSach() {
        return idLoaiSach;
    }

    public void setIdLoaiSach(int idLoaiSach) {
        this.idLoaiSach = idLoaiSach;
    }
}
