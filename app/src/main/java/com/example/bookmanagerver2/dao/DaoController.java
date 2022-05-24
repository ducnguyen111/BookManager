package com.example.bookmanagerver2.dao;

import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_HOTEN;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_IMAGE;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_MATKHAU;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_MATT;
import static com.example.bookmanagerver2.database.MySqlHeper.KEY_THUTHU_TAIKHOAN;
import static com.example.bookmanagerver2.database.MySqlHeper.TABLE_NAME_THUTHU;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.bookmanagerver2.database.MySqlHeper;
import com.example.bookmanagerver2.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class DaoController {
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;

    public DaoController(Context context) {
        mMySqlHeper = new MySqlHeper(context);
    }

    public ThuThu getUserLogin(String username, String password) {
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_THUTHU
                + " WHERE " + KEY_THUTHU_TAIKHOAN + " = '" + username + "' and " + KEY_THUTHU_MATKHAU + " = '" + password + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user = new ThuThu();
        if (cursor.moveToFirst()) {
            user.setMaThuThu(Integer.parseInt(cursor.getString(0)));
            user.setHoTenThuThu(cursor.getString(1));
            user.setTaiKhoan(cursor.getString(2));
            user.setMaKhau(cursor.getString(3));
            user.setThuthuPhoto(cursor.getBlob(4));
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return user;
    }

    public Bitmap getPhotoSql(int idLib) {
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_IMAGE + " AS IMAGEUSER FROM " + TABLE_NAME_THUTHU + " WHERE " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT + " = " + idLib;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            byte[] imgByte = cursor.getBlob(0);
            cursor.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return null;
    }

    @SuppressLint("Range")
    public List<ThuThu> getListThuTHu() {
        List<ThuThu> list = new ArrayList<ThuThu>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_THUTHU;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_THUTHU_MATT)));
                String username = cursor.getString(cursor.getColumnIndex(KEY_THUTHU_TAIKHOAN));
                String tenTHuThu = cursor.getString(cursor.getColumnIndex(KEY_THUTHU_HOTEN));
                String password = cursor.getString(cursor.getColumnIndex(KEY_THUTHU_MATKHAU));
                byte[] imgByte = cursor.getBlob(cursor.getColumnIndex(KEY_THUTHU_IMAGE));
                user = new ThuThu(username ,id , password , tenTHuThu  ,imgByte);
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
        return mSQLiteDatabase.delete(TABLE_NAME_THUTHU, KEY_THUTHU_MATT + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean themKind(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THUTHU_HOTEN, thuthu.getHoTenThuThu());
        values.put(KEY_THUTHU_TAIKHOAN, thuthu.getTaiKhoan());
        values.put(KEY_THUTHU_MATKHAU, thuthu.getMaKhau());
        values.put(KEY_THUTHU_IMAGE, thuthu.getThuthuPhoto());
        long result = this.mSQLiteDatabase.insert(TABLE_NAME_THUTHU, null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }

    public boolean editKind(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THUTHU_HOTEN, thuthu.getHoTenThuThu());
        values.put(KEY_THUTHU_TAIKHOAN, thuthu.getTaiKhoan());
        values.put(KEY_THUTHU_MATKHAU, thuthu.getMaKhau());
        values.put(KEY_THUTHU_IMAGE, thuthu.getThuthuPhoto());
        int r = this.mSQLiteDatabase.update(TABLE_NAME_THUTHU, values, KEY_THUTHU_MATT + "=?", new String[]{String.valueOf(thuthu.getMaThuThu())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    @SuppressLint("Range")
    public List<ThuThu> getListSearch(String tenSachSearch) {
        List<ThuThu> list = new ArrayList<ThuThu>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_MATT +
                " , " + KEY_THUTHU_HOTEN +
                " , " + KEY_THUTHU_TAIKHOAN +
                " , " + KEY_THUTHU_MATKHAU +
                " , " + KEY_THUTHU_IMAGE +
                " FROM " + TABLE_NAME_THUTHU +
                " WHERE " + TABLE_NAME_THUTHU + "." + KEY_THUTHU_TAIKHOAN + " LIKE '%" + tenSachSearch + "%'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_THUTHU_MATT)));
                String tenTHuThu = cursor.getString(cursor.getColumnIndex(KEY_THUTHU_HOTEN));
                String username = cursor.getString(cursor.getColumnIndex(KEY_THUTHU_TAIKHOAN));
                String password = cursor.getString(cursor.getColumnIndex(KEY_THUTHU_MATKHAU));
                byte[] imgByte = cursor.getBlob(cursor.getColumnIndex(KEY_THUTHU_IMAGE));
                user = new ThuThu(username ,id , password , tenTHuThu  ,imgByte);
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }

    public boolean changePassword(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THUTHU_MATKHAU, thuthu.getMaKhau());
        int r = this.mSQLiteDatabase.update(TABLE_NAME_THUTHU, values, KEY_THUTHU_MATT + "=?", new String[]{String.valueOf(thuthu.getMaThuThu())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
