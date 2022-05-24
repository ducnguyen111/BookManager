package com.example.bookmanagerver2.dao;


import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_MAPHIEUMUON;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_NGAY;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_SACH_MASACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_THUTHU_MATHUTHU;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_TIENTHUE;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_TRASACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_GIATHUE;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_GIATHUEKHUYENMAI;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_MASACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_TENSACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THANHVIEN_HOTEN;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THANHVIEN_MATV;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_HOTEN;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_MATT;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_PHIEUMUON;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_SACH;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_THANHVIEN;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_THUTHU;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagerver2.database.MySqlHeper;
import com.example.bookmanagerver2.model.LoanSlip.LoanSlip;
import com.example.bookmanagerver2.model.LoanSlip.LoanSlipBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DaoLib {
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;

    public DaoLib(Context context) {
        mMySqlHeper = new MySqlHeper(context);
    }


    public int getidMembers(String tenMember) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV +
                " AS idMember FROM " + TABLE_NAME_THANHVIEN +
                " WHERE " + TABLE_NAME_THANHVIEN + "."
                + KEY_THANHVIEN_HOTEN + " ='" + tenMember + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return id;
    }

    public int getIdLibrary(String tenLibrary) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT +
                " AS idLibrary FROM " + TABLE_NAME_THUTHU +
                " WHERE " + TABLE_NAME_THUTHU + "."
                + KEY_THUTHU_HOTEN + " ='" + tenLibrary + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return id;
    }

    public int getIdBook(String tenBook) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH +
                " AS idBook FROM " + TABLE_NAME_SACH +
                " WHERE " + TABLE_NAME_SACH + "."
                + KEY_SACH_TENSACH + " ='" + tenBook + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return id;
    }

    public List<String> getListMember() {
        List<String> result = new ArrayList<String>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_THANHVIEN;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(1);
                result.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return result;
    }

    public List<String> getListLibrary() {
        List<String> result = new ArrayList<String>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_THUTHU;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(1);
                result.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();


        return result;
    }

    public List<String> getListBook() {
        List<String> result = new ArrayList<String>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_SACH;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(1);
                result.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();


        return result;
    }

    public int getGiaKhuyenMai(String tenSach) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." + KEY_SACH_GIATHUEKHUYENMAI +
                " AS moneykhuyenmai FROM " + TABLE_NAME_SACH +
                " WHERE " + TABLE_NAME_SACH + "."
                + KEY_SACH_TENSACH + " ='" + tenSach + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return id;
    }

    public float getGiaGocSach(String tenSach) {
        float id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." + KEY_SACH_GIATHUE +
                " AS moneygoc FROM " + TABLE_NAME_SACH +
                " WHERE " + TABLE_NAME_SACH + "."
                + KEY_SACH_TENSACH + " ='" + tenSach + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Float.parseFloat(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return id;
    }

    public List<LoanSlip> getListofLoanSlips() {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_MAPHIEUMUON +
                "," + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " AS TENMEMBERMUON" +
                "," + TABLE_NAME_THUTHU + "." + KEY_THUTHU_HOTEN + " AS TENNGUOICHOMUON" +
                "," + TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " AS TENSACHMUON" +
                " , " + KEY_PHIEUMUON_TIENTHUE +
                " , " + KEY_PHIEUMUON_NGAY +
                ", " + KEY_PHIEUMUON_TRASACH
                + " FROM " + TABLE_NAME_PHIEUMUON +
                " JOIN " + TABLE_NAME_THANHVIEN + " ON " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                + " JOIN " + TABLE_NAME_THUTHU + " ON " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                + " JOIN " + TABLE_NAME_SACH + " ON " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        LoanSlip user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                int id = Integer.parseInt(cursor.getString(0));
                String tenThanhVien = cursor.getString(1);
                String tenLIb = cursor.getString(2);
                String tenBook = cursor.getString(3);
                float giaThue = Float.parseFloat(cursor.getString(4));
                String ngayMuon = cursor.getString(5);
                int trangthai = Integer.parseInt(cursor.getString(6));
                user = new LoanSlipBuilder()
                        .builderId(id)
                        .builderTienThue(giaThue)
                        .builderNgayThue(ngayMuon)
                        .builderTrangThaiMuong(trangthai)
                        .builderTenNguoiMuong(tenThanhVien)
                        .builderNguoiChoMuon(tenLIb)
                        .builderTenSachMuong(tenBook)
                        .build();
                list.add(user);
                cursor.moveToNext();

            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }

    public boolean themKind(LoanSlip mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHIEUMUON_THUTHU_MATHUTHU, mode.getMaTHuThu());
        values.put(KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN, mode.getMaThanhVien());
        values.put(KEY_PHIEUMUON_SACH_MASACH, mode.getMaSach());
        values.put(KEY_PHIEUMUON_TIENTHUE, mode.getTienThue());
        values.put(KEY_PHIEUMUON_NGAY, mode.getNgayThue());
        values.put(KEY_PHIEUMUON_TRASACH, mode.getTrangThaiMuon());
        long result = this.mSQLiteDatabase.insert(TABLE_NAME_PHIEUMUON, null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteTitle(int id) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        return mSQLiteDatabase.delete(TABLE_NAME_PHIEUMUON, KEY_PHIEUMUON_MAPHIEUMUON + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean editeLoanSlip(LoanSlip mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHIEUMUON_THUTHU_MATHUTHU, mode.getMaTHuThu());
        values.put(KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN, mode.getMaThanhVien());
        values.put(KEY_PHIEUMUON_SACH_MASACH, mode.getMaSach());
        values.put(KEY_PHIEUMUON_TIENTHUE, mode.getTienThue());
        values.put(KEY_PHIEUMUON_NGAY, mode.getNgayThue());
        values.put(KEY_PHIEUMUON_TRASACH, mode.getTrangThaiMuon());
        int r = this.mSQLiteDatabase.update(TABLE_NAME_PHIEUMUON, values, KEY_PHIEUMUON_MAPHIEUMUON + "=?", new String[]{String.valueOf(mode.getId())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    public List<LoanSlip> getListofLoanSlipsSearch(boolean chuacha, boolean dacha, String etSearch) {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "";
        if (chuacha == true && dacha == false && etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_MAPHIEUMUON +
                    "," + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " AS TENMEMBERMUON" +
                    "," + TABLE_NAME_THUTHU + "." + KEY_THUTHU_HOTEN + " AS TENNGUOICHOMUON" +
                    "," + TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_PHIEUMUON_TIENTHUE +
                    " , " + KEY_PHIEUMUON_NGAY +
                    ", " + KEY_PHIEUMUON_TRASACH
                    + " FROM " + TABLE_NAME_PHIEUMUON +
                    " JOIN " + TABLE_NAME_THANHVIEN + " ON " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_NAME_THUTHU + " ON " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_NAME_SACH + " ON " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_TRASACH + " = 0";
        } else if (chuacha == false && dacha == true && etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_MAPHIEUMUON +
                    "," + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " AS TENMEMBERMUON" +
                    "," + TABLE_NAME_THUTHU + "." + KEY_THUTHU_HOTEN + " AS TENNGUOICHOMUON" +
                    "," + TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_PHIEUMUON_TIENTHUE +
                    " , " + KEY_PHIEUMUON_NGAY +
                    ", " + KEY_PHIEUMUON_TRASACH
                    + " FROM " + TABLE_NAME_PHIEUMUON +
                    " JOIN " + TABLE_NAME_THANHVIEN + " ON " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_NAME_THUTHU + " ON " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_NAME_SACH + " ON " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_TRASACH + " = 1";
        } else if (chuacha == false && dacha == false && !etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_MAPHIEUMUON +
                    "," + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " AS TENMEMBERMUON" +
                    "," + TABLE_NAME_THUTHU + "." + KEY_THUTHU_HOTEN + " AS TENNGUOICHOMUON" +
                    "," + TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_PHIEUMUON_TIENTHUE +
                    " , " + KEY_PHIEUMUON_NGAY +
                    ", " + KEY_PHIEUMUON_TRASACH
                    + " FROM " + TABLE_NAME_PHIEUMUON +
                    " JOIN " + TABLE_NAME_THANHVIEN + " ON " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_NAME_THUTHU + " ON " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_NAME_SACH + " ON " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + " TENMEMBERMUON = '" + etSearch + "'";
        } else if (chuacha == true && !etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_MAPHIEUMUON +
                    "," + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " AS TENMEMBERMUON" +
                    "," + TABLE_NAME_THUTHU + "." + KEY_THUTHU_HOTEN + " AS TENNGUOICHOMUON" +
                    "," + TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_PHIEUMUON_TIENTHUE +
                    " , " + KEY_PHIEUMUON_NGAY +
                    ", " + KEY_PHIEUMUON_TRASACH
                    + " FROM " + TABLE_NAME_PHIEUMUON +
                    " JOIN " + TABLE_NAME_THANHVIEN + " ON " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_NAME_THUTHU + " ON " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_NAME_SACH + " ON " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_TRASACH + " = 0 AND " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " = '" + etSearch + "'";
        } else if (dacha == true && !etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_MAPHIEUMUON +
                    "," + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " AS TENMEMBERMUON" +
                    "," + TABLE_NAME_THUTHU + "." + KEY_THUTHU_HOTEN + " AS TENNGUOICHOMUON" +
                    "," + TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_PHIEUMUON_TIENTHUE +
                    " , " + KEY_PHIEUMUON_NGAY +
                    ", " + KEY_PHIEUMUON_TRASACH
                    + " FROM " + TABLE_NAME_PHIEUMUON +
                    " JOIN " + TABLE_NAME_THANHVIEN + " ON " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_MATV + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_NAME_THUTHU + " ON " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_NAME_SACH + " ON " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " = " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_TRASACH + " = 1 AND  " + TABLE_NAME_THANHVIEN + "." + KEY_THANHVIEN_HOTEN + " = '" + etSearch + "'";
        }

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        LoanSlip user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenThanhVien = cursor.getString(1);
                String tenLIb = cursor.getString(2);
                String tenBook = cursor.getString(3);
                float giaThue = Float.parseFloat(cursor.getString(4));
                String ngayMuon = cursor.getString(5);
                int trangthai = Integer.parseInt(cursor.getString(6));
                user = new LoanSlipBuilder()
                        .builderId(id)
                        .builderTienThue(giaThue)
                        .builderNgayThue(ngayMuon)
                        .builderTrangThaiMuong(trangthai)
                        .builderTenNguoiMuong(tenThanhVien)
                        .builderNguoiChoMuon(tenLIb)
                        .builderTenSachMuong(tenBook)
                        .build();
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
}
