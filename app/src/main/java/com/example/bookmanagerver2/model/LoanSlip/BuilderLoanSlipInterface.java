package com.example.bookmanagerver2.model.LoanSlip;

public interface BuilderLoanSlipInterface {
    BuilderLoanSlipInterface builderId(int id);

    BuilderLoanSlipInterface builderMaThuThu(int maThuThu);

    BuilderLoanSlipInterface builderMaThanhVien(int maThanhVien);

    BuilderLoanSlipInterface builderMaSach(int maSach);

    BuilderLoanSlipInterface builderTienThue(float tienThue);

    BuilderLoanSlipInterface builderNgayThue(String ngayThue);

    BuilderLoanSlipInterface builderTrangThaiMuong(int trangThaiMuon);

    BuilderLoanSlipInterface builderTenNguoiMuong(String tenNGuoiMuon);

    BuilderLoanSlipInterface builderNguoiChoMuon(String nguoiChoMuon);

    BuilderLoanSlipInterface builderTenSachMuong(String tenSachMuon);

    LoanSlip build();
}
