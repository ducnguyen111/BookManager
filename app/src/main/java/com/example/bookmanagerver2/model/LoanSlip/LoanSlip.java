package com.example.bookmanagerver2.model.LoanSlip;

import java.io.Serializable;

public class LoanSlip implements Serializable {
    private int id;
    private int maTHuThu;
    private int maThanhVien;
    private int maSach;
    private float tienThue;
    private String ngayThue;
    private int trangThaiMuon;
    private String tenNGuoiMuon, nguoiChoMuon, tenSachMuon;

    public LoanSlip(int id, int maTHuThu, int maThanhVien, int maSach, float tienThue, String ngayThue, int trangThaiMuon, String tenNGuoiMuon, String nguoiChoMuon, String tenSachMuon) {
        this.id = id;
        this.maTHuThu = maTHuThu;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.ngayThue = ngayThue;
        this.trangThaiMuon = trangThaiMuon;
        this.tenNGuoiMuon = tenNGuoiMuon;
        this.nguoiChoMuon = nguoiChoMuon;
        this.tenSachMuon = tenSachMuon;
    }

    public int getId() {
        return id;
    }

    public int getMaTHuThu() {
        return maTHuThu;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public int getMaSach() {
        return maSach;
    }

    public float getTienThue() {
        return tienThue;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public int getTrangThaiMuon() {
        return trangThaiMuon;
    }

    public String getTenNGuoiMuon() {
        return tenNGuoiMuon;
    }

    public String getNguoiChoMuon() {
        return nguoiChoMuon;
    }

    public String getTenSachMuon() {
        return tenSachMuon;
    }
}
