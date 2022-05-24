package com.example.bookmanagerver2.model.LoanSlip;

public class LoanSlipBuilder implements BuilderLoanSlipInterface {
    private int id;
    private int maTHuThu;
    private int maThanhVien;
    private int maSach;
    private float tienThue;
    private String ngayThue;
    private int trangThaiMuon;
    private String tenNGuoiMuon, nguoiChoMuon, tenSachMuon;

    @Override
    public BuilderLoanSlipInterface builderId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderMaThuThu(int maThuThu) {
        this.maTHuThu = maThuThu;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderMaSach(int maSach) {
        this.maSach = maSach;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderTienThue(float tienThue) {
        this.tienThue = tienThue;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderTrangThaiMuong(int trangThaiMuon) {
        this.trangThaiMuon = trangThaiMuon;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderTenNguoiMuong(String tenNGuoiMuon) {
        this.tenNGuoiMuon = tenNGuoiMuon;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderNguoiChoMuon(String nguoiChoMuon) {
        this.nguoiChoMuon = nguoiChoMuon;
        return this;
    }

    @Override
    public BuilderLoanSlipInterface builderTenSachMuong(String tenSachMuon) {
        this.tenSachMuon = tenSachMuon;
        return this;
    }

    @Override
    public LoanSlip build() {
        return new LoanSlip(id, maTHuThu ,maThanhVien,maSach , tienThue , ngayThue ,trangThaiMuon ,tenNGuoiMuon , nguoiChoMuon , tenSachMuon);
    }
}
