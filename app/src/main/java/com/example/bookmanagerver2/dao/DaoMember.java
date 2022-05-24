package com.example.bookmanagerver2.dao;



import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THANHVIEN_HOTEN;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THANHVIEN_MATV;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THANHVIEN_NAMSINH;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_THANHVIEN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.bookmanagerver2.database.MySqlHeper;
import com.example.bookmanagerver2.model.Member;

import java.util.ArrayList;
import java.util.List;

public class DaoMember {
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;
    public DaoMember(Context context){
        mMySqlHeper = new MySqlHeper(context);
    }

    public List<Member> getListMember(){
        List<Member> list = new ArrayList<Member>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_THANHVIEN ;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Member user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenMember = cursor.getString(1);
                int namSinh = Integer.parseInt(cursor.getString(2));
                user = new Member(id , tenMember , namSinh );
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
        return mSQLiteDatabase.delete(TABLE_NAME_THANHVIEN, KEY_THANHVIEN_MATV + "=?", new String[]{String.valueOf(id)}) > 0;
    }
    public  boolean themKind(Member mode){
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THANHVIEN_HOTEN , mode.getName());
        values.put(KEY_THANHVIEN_NAMSINH , mode.getNameSinh());
        long result = this.mSQLiteDatabase.insert(TABLE_NAME_THANHVIEN , null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }
    public boolean editKind(Member mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THANHVIEN_HOTEN , mode.getName());
        values.put(KEY_THANHVIEN_NAMSINH , mode.getNameSinh());
        int r = this.mSQLiteDatabase.update(TABLE_NAME_THANHVIEN, values, KEY_THANHVIEN_MATV+"=?", new String[]{String.valueOf(mode.getId())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public List<Member> getListSearch(String tenSachSearch ) {
        List<Member> list = new ArrayList<Member>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_THANHVIEN + "." +KEY_THANHVIEN_MATV +
                " , " + KEY_THANHVIEN_HOTEN +
                " , " + KEY_THANHVIEN_NAMSINH +
                " FROM " + TABLE_NAME_THANHVIEN +
                " WHERE " + TABLE_NAME_THANHVIEN + "." +KEY_THANHVIEN_HOTEN + " LIKE '%" +tenSachSearch + "%' OR " +KEY_THANHVIEN_NAMSINH  +" = " + "'  "+tenSachSearch+ "'  ";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Member user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenLoaiSach = cursor.getString(1);
                int idLoaiSach = Integer.parseInt(cursor.getString(2));
                user = new Member(id , tenLoaiSach,idLoaiSach);
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
}
