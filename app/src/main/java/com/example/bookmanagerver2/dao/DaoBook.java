package com.example.bookmanagerver2.dao;



import static com.example.bookmanagerver2.database.MySqlHeper.KEY_LOAISACH_MALOAI;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_LOAISACH_TENLOAI;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_SACH_MASACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_PHIEUMUON_TRASACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_GIATHUE;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_GIATHUEKHUYENMAI;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_LOAISACH_MALOAI;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_MASACH;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_SACH_TENSACH;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_LOAISACH;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_PHIEUMUON;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_SACH;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagerver2.database.MySqlHeper;
import com.example.bookmanagerver2.model.Book;
import com.example.bookmanagerver2.model.TopMost;

import java.util.ArrayList;
import java.util.List;

public class DaoBook {
    MySqlHeper mMySqlHeper;
     SQLiteDatabase mSQLiteDatabase;
    public DaoBook(Context context){
        mMySqlHeper = new MySqlHeper(context);
    }

    public List<Book> getBookList(){
        List<Book> list = new ArrayList<Book>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." +KEY_SACH_MASACH +
                "," + TABLE_NAME_LOAISACH + "." + KEY_LOAISACH_TENLOAI + " AS TENLOAISACH" +
                " , " + KEY_SACH_TENSACH +
                " , " + KEY_SACH_GIATHUE  +
                ", "  + KEY_SACH_GIATHUEKHUYENMAI +
                ", " + KEY_SACH_LOAISACH_MALOAI + " FROM " + TABLE_NAME_SACH + " JOIN " + TABLE_NAME_LOAISACH + " ON " +TABLE_NAME_LOAISACH + "." + KEY_LOAISACH_MALOAI +" = " + TABLE_NAME_SACH + "." + KEY_SACH_LOAISACH_MALOAI ;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Book user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenLoaiSach = cursor.getString(1);
                String tenSach = cursor.getString(2);
                float giaThue = Float.parseFloat(cursor.getString(3));
                int giaThueKhuyenMai = Integer.parseInt(cursor.getString(4));
                int idLoaiSach = Integer.parseInt(cursor.getString(4));
                user = new Book(id , tenSach , tenLoaiSach , giaThue , giaThueKhuyenMai ,idLoaiSach);
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
    public boolean deleteTitle(int id) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        return mSQLiteDatabase.delete(TABLE_NAME_SACH, KEY_SACH_MASACH + "=?", new String[]{String.valueOf(id)}) > 0;
    }
    public  boolean themBook(Book book){
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SACH_TENSACH , book.getTenSach() );
        values.put(KEY_SACH_GIATHUE , book.getMoney() );
        values.put(KEY_SACH_GIATHUEKHUYENMAI , book.getMoneyKhuyenmai() );
        values.put(KEY_SACH_LOAISACH_MALOAI , book.getIdLoaiSach() );
        long result = this.mSQLiteDatabase.insert(TABLE_NAME_SACH , null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }
    public boolean editBoock(Book book) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SACH_TENSACH , book.getTenSach() );
        values.put(KEY_SACH_GIATHUE , book.getMoney() );
        values.put(KEY_SACH_GIATHUEKHUYENMAI , book.getMoneyKhuyenmai() );
        values.put(KEY_SACH_LOAISACH_MALOAI , book.getIdLoaiSach() );
        int r = this.mSQLiteDatabase.update(TABLE_NAME_SACH, values, KEY_SACH_MASACH+"=?", new String[]{String.valueOf(book.getId())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public List<Book> getListSearch(String tenSachSearch ) {
        List<Book> list = new ArrayList<Book>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." +KEY_SACH_MASACH + "," + TABLE_NAME_LOAISACH + "." + KEY_LOAISACH_TENLOAI + " AS TENLOAISACH" + " , " + KEY_SACH_TENSACH + " , " + KEY_SACH_GIATHUE  + ", "  + KEY_SACH_GIATHUEKHUYENMAI + ", " + KEY_SACH_LOAISACH_MALOAI + " FROM " + TABLE_NAME_SACH + " JOIN " + TABLE_NAME_LOAISACH + " ON " +TABLE_NAME_LOAISACH + "." + KEY_LOAISACH_MALOAI +" = " + TABLE_NAME_SACH + "." + KEY_SACH_LOAISACH_MALOAI + " WHERE "+TABLE_NAME_SACH + "." + KEY_SACH_TENSACH + " LIKE '%" +tenSachSearch+ "%' OR TENLOAISACH LIKE '%" +tenSachSearch+ "%'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Book user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenLoaiSach = cursor.getString(1);
                String tenSach = cursor.getString(2);
                float giaThue = Float.parseFloat(cursor.getString(3));
                int giaThueKhuyenMai = Integer.parseInt(cursor.getString(4));
                int idLoaiSach = Integer.parseInt(cursor.getString(4));
                user = new Book(id , tenSach , tenLoaiSach , giaThue , giaThueKhuyenMai ,idLoaiSach);
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
    public List<TopMost> getListTopMost() {
        List<TopMost> list = new ArrayList<TopMost>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." +KEY_SACH_TENSACH +" AS TENSACHTOPMOST " +
                "," + " COUNT( " +TABLE_NAME_PHIEUMUON + "." +  KEY_PHIEUMUON_SACH_MASACH + " ) AS SOLUONGTOPMOST FROM " + TABLE_NAME_SACH + " INNER JOIN " + TABLE_NAME_PHIEUMUON + " ON "  + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH + " = " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH
                + " GROUP BY " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " ORDER BY SOLUONGTOPMOST DESC LIMIT 10";


        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        TopMost user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String tesnsach = cursor.getString(0);
                int id = Integer.parseInt(cursor.getString(1));

                user = new TopMost(tesnsach ,id );
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
    public ArrayList<TopMost> getListTopMost2() {
        ArrayList<TopMost> list = new ArrayList<TopMost>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_SACH + "." +KEY_SACH_TENSACH +" AS TENSACHTOPMOST " +
                "," + " COUNT( " +TABLE_NAME_PHIEUMUON + "." +  KEY_PHIEUMUON_SACH_MASACH + " ) AS SOLUONGTOPMOST FROM " + TABLE_NAME_SACH + " INNER JOIN " + TABLE_NAME_PHIEUMUON + " ON "  + TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH + " = " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH
                + " GROUP BY " + TABLE_NAME_SACH + "." + KEY_SACH_MASACH + " ORDER BY SOLUONGTOPMOST DESC LIMIT 10";


        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        TopMost user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String tesnsach = cursor.getString(0);
                int id = Integer.parseInt(cursor.getString(1));

                user = new TopMost(tesnsach ,id );
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
    public float tienDoanhthu(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH + ")";
                 ;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                roundOff = Float.parseFloat(cursor.getString(0));

                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienDoanhthuTheoNgay(String ngayBatDau , String ngayKetThuc){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '"+ngayBatDau+" ' AND + '"+ ngayKetThuc +"' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public int soluongPhieuDaTra(String ngayBatDau , String ngayKetThuc){
        int roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_PHIEUMUON +
                " WHERE (PHIEUMUON.NGAY BETWEEN  '"+ngayBatDau+" ' AND + '"+ ngayKetThuc +"') AND "+ TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_TRASACH + " = 1";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        roundOff = cursor.getCount();

        return roundOff;
    }
    public int soluongPhieuChuaTra(String ngayBatDau , String ngayKetThuc){
        int roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_PHIEUMUON +
                " WHERE (PHIEUMUON.NGAY BETWEEN  '"+ngayBatDau+" ' AND + '"+ ngayKetThuc +"') AND "+ TABLE_NAME_PHIEUMUON + "." + KEY_PHIEUMUON_TRASACH + " = 0";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        roundOff = cursor.getCount();

        return roundOff;
    }
    public float tienThuThang1(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-1-1' AND + '2022-1-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang2(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-2-1' AND + '2022-2-29' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang3(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-3-1' AND + '2022-3-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang4(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-4-1' AND + '2022-4-30' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang5(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-5-1' AND + '2022-5-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang6(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-6-1' AND + '2022-6-30' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang7(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-7-1' AND + '2022-7-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang8(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-8-1' AND + '2022-8-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang9(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-9-1' AND + '2022-9-30' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang10(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-10-1' AND + '2022-10-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang11(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-11-1' AND + '2022-11-30' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
    public float tienThuThang12(){
        float roundOff = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT SUM(tongtien) FROM ( SELECT SUM( SACH.GIATHUE - (SACH.GIATHUE *(SACH.KHUYENMAI / 100)) ) AS TONGTIEN FROM " +
                TABLE_NAME_SACH +" JOIN PHIEUMUON ON PHIEUMUON.MASACH = SACH.MASACH "
                +" WHERE PHIEUMUON.NGAY BETWEEN  '2022-12-1' AND + '2022-12-31' )";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if(cursor.getString(0) !=null) {
                    roundOff = Float.parseFloat(cursor.getString(0));
                }else{
                    roundOff = 0;
                    break;
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();

        return roundOff;
    }
}
