package com.example.bookmanagerver2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHeper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_THANHVIEN = "THANHVIEN";
    public static final String KEY_THANHVIEN_MATV = "MATHANHVIEN";
    public static final String KEY_THANHVIEN_HOTEN = "HOTENTHANHVIEN";
    public static final String KEY_THANHVIEN_NAMSINH = "NAMSINHTHANHVIEN";


    public static final String TABLE_NAME_THUTHU = "THUTHU";
    public static final String KEY_THUTHU_MATT = "MATHUTHU";
    public static final String KEY_THUTHU_TAIKHOAN = "TAIKHOANTHUTHU";
    public static final String KEY_THUTHU_HOTEN = "HOTENTHUTHU";
    public static final String KEY_THUTHU_MATKHAU = "MATKHAUTHUTHU";
    public static final String KEY_THUTHU_IMAGE = "IMAGETHUTHU";


    public static final String TABLE_NAME_LOAISACH = "LOAISACH";
    public static final String KEY_LOAISACH_MALOAI = "MALOAISACH";
    public static final String KEY_LOAISACH_TENLOAI = "TENLOAISACH";

    public static final String TABLE_NAME_SACH = "SACH";
    public static final String KEY_SACH_MASACH = "MASACH";
    public static final String KEY_SACH_TENSACH = "TENSACH";
    public static final String KEY_SACH_GIATHUE = "GIATHUE";
    public static final String KEY_SACH_GIATHUEKHUYENMAI = "KHUYENMAI";
    public static final String KEY_SACH_LOAISACH_MALOAI = "LOAISACH";

    public static final String TABLE_NAME_PHIEUMUON = "PHIEUMUON";
    public static final String KEY_PHIEUMUON_MAPHIEUMUON = "MAPHIEUMUON";
    public static final String KEY_PHIEUMUON_THUTHU_MATHUTHU = "MATHUTHU";
    public static final String KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN = "MATHANHVIEN";
    public static final String KEY_PHIEUMUON_SACH_MASACH = "MASACH";
    public static final String KEY_PHIEUMUON_TIENTHUE = "TIENTHUE";
    public static final String KEY_PHIEUMUON_NGAY = "NGAY";
    public static final String KEY_PHIEUMUON_TRASACH = "TRASACH";


    public MySqlHeper(Context context) {
        super(context, "quanlithuvienn2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "CREATE TABLE " + TABLE_NAME_THANHVIEN
                + "("
                + KEY_THANHVIEN_MATV + " INTEGER PRIMARY KEY , "
                + KEY_THANHVIEN_HOTEN + " TEXT NOT NULL, "
                + KEY_THANHVIEN_NAMSINH + " INTEGER NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_NAME_THUTHU
                + "("
                + KEY_THUTHU_MATT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_THUTHU_HOTEN + " TEXT NOT NULL ,"
                + KEY_THUTHU_TAIKHOAN + " TEXT NOT NULL ,"
                + KEY_THUTHU_MATKHAU + " TEXT NOT NULL,"
                + KEY_THUTHU_IMAGE + " BLOB "
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_NAME_LOAISACH
                + "("
                + KEY_LOAISACH_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_LOAISACH_TENLOAI + " TEXT NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_NAME_SACH
                + "("
                + KEY_SACH_MASACH + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_SACH_TENSACH + " TEXT NOT NULL ,"
                + KEY_SACH_GIATHUE + " MONEY NOT NULL ,"
                + KEY_SACH_GIATHUEKHUYENMAI + " MONEY  ,"
                + KEY_SACH_LOAISACH_MALOAI + " INTEGER REFERENCES " + TABLE_NAME_LOAISACH + "( " + KEY_SACH_LOAISACH_MALOAI + ")"
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_NAME_PHIEUMUON
                + "("
                + KEY_PHIEUMUON_MAPHIEUMUON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PHIEUMUON_THUTHU_MATHUTHU + " INTEGER REFERENCES " + TABLE_NAME_THUTHU + "( " + KEY_PHIEUMUON_THUTHU_MATHUTHU + "),"
                + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN + " INTEGER REFERENCES " + TABLE_NAME_THANHVIEN + "( " + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN + "),"
                + KEY_PHIEUMUON_SACH_MASACH + " INTEGER REFERENCES " + TABLE_NAME_SACH + "( " + KEY_PHIEUMUON_SACH_MASACH + "),"
                + KEY_PHIEUMUON_TIENTHUE + " MONEY NOT NULL ,"
                + KEY_PHIEUMUON_NGAY + " DATE NOT NULL ,"
                + KEY_PHIEUMUON_TRASACH + " INTEGER NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_THUTHU + " VALUES ( null ,'Đinh Thanh Minh','dinhthanhminh' ,'123456' , null)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_LOAISACH + " VALUES ( null ,'Python')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_LOAISACH + " VALUES ( null ,'java')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_LOAISACH + " VALUES ( null ,'pormhub')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_SACH + " VALUES ( null ,'lean java' , 15 , 45 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_SACH + " VALUES ( null ,'learn python' , 18 , 50 , 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_SACH + " VALUES ( null ,'learn pormhub' , 20 , 60 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_SACH + " VALUES ( null ,'learn java2' , 25 , 80 , 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_THANHVIEN + " VALUES ( null ,'A Băng', 2000)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_THANHVIEN + " VALUES ( null ,'A dũng', 2000)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_THANHVIEN + " VALUES ( null ,'A duy', 2001)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_THANHVIEN + " VALUES ( null ,'Chiến', 2000)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 3,2 ,8.0 , '2022-9-14' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 4,3 ,9.0 , '2022-9-15' ,0)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 2,2 ,8.0 , '2022-9-16' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 3,2 ,8.0 , '2022-8-14' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 4,3 ,9.0 , '2022-7-15' ,0)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 2,2 ,8.0 , '2022-6-16' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 3,2 ,8.0 , '2022-5-14' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 4,3 ,9.0 , '2022-4-15' ,0)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 2,2 ,8.0 , '2022-3-16' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 2,2 ,8.0 , '2022-2-16' ,1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_NAME_PHIEUMUON + " VALUES ( null , 1, 2,2 ,8.0 , '2022-1-16' ,1)";
        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_THANHVIEN);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_THUTHU);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LOAISACH);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SACH);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PHIEUMUON);
        onCreate(sqLiteDatabase);

    }
}
