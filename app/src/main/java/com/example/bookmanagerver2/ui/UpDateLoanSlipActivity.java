package com.example.bookmanagerver2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoLib;
import com.example.bookmanagerver2.model.LoanSlip.LoanSlip;
import com.example.bookmanagerver2.model.LoanSlip.LoanSlipBuilder;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;

public class UpDateLoanSlipActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private TextView createTaiKhoan;
    private TextView tvghichu;
    private LinearLayout contenMember;
    private Spinner spNameMember;
    private LinearLayout contenLib;
    private Spinner spLibrary;
    private LinearLayout contenBook;
    private Spinner spBook;
    private LinearLayout conPrice;
    private TextView tvGiaGoc;
    private LinearLayout contenDate;
    private TextView tvNgay;
    private LinearLayout conTenSatus;
    private Switch trangThaiSatus;
    private Button btDangKi;
    private LinearLayout ll1;
    private TextView textDangNhap;
    private DaoLib mDaoLib;
    private int idMember , idLibrary , idBook , giaKhuyenMai;
    private float giaGoc;
    private DatePickerDialog datePickerDialog;
    private int trangthai =1;
    private float tongtien1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_loan_slip);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        createTaiKhoan = (TextView) findViewById(R.id.createTaiKhoan);
        tvghichu = (TextView) findViewById(R.id.tvghichu);
        contenMember = (LinearLayout) findViewById(R.id.contenMember);
        spNameMember = (Spinner) findViewById(R.id.spNameMember);
        contenLib = (LinearLayout) findViewById(R.id.contenLib);
        spLibrary = (Spinner) findViewById(R.id.spLibrary);
        contenBook = (LinearLayout) findViewById(R.id.contenBook);
        spBook = (Spinner) findViewById(R.id.spBook);
        conPrice = (LinearLayout) findViewById(R.id.conPrice);
        tvGiaGoc = (TextView) findViewById(R.id.tvGiaGoc);
        contenDate = (LinearLayout) findViewById(R.id.contenDate);
        tvNgay = (TextView) findViewById(R.id.tvNgay);
        conTenSatus = (LinearLayout) findViewById(R.id.conTenSatus);
        trangThaiSatus = (Switch) findViewById(R.id.trangThaiSatus);
        btDangKi = (Button) findViewById(R.id.btDangKi);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        textDangNhap = (TextView) findViewById(R.id.textDangNhap);
        mDaoLib = new DaoLib(this);
        LoanSlip loan =(LoanSlip) getIntent().getSerializableExtra("loanslip");
        inispiner();
        iniDate();
        spNameMember.setSelection(mDaoLib.getListMember().indexOf(loan.getTenNGuoiMuon()));
        spLibrary.setSelection(mDaoLib.getListLibrary().indexOf(loan.getNguoiChoMuon()));
        spBook.setSelection(mDaoLib.getListBook().indexOf(loan.getTenSachMuon()));
        tvNgay.setText(loan.getNgayThue());
        tvGiaGoc.setText(String.valueOf(loan.getTienThue()));
        if(loan.getTrangThaiMuon() == 1){
            trangThaiSatus.setChecked(true);
        }else{
            trangThaiSatus.setChecked(false);
        }

        btDangKi.setOnClickListener(view2 ->{

            LoanSlip tc =new LoanSlipBuilder()
                    .builderId(loan.getId())
                    .builderMaThuThu(idLibrary)
                    .builderMaThanhVien(idMember)
                    .builderMaSach(idBook)
                    .builderTienThue(tongtien1)
                    .builderNgayThue(tvNgay.getText().toString())
                    .builderTrangThaiMuong(trangthai)
                    .build();
            if (mDaoLib.editeLoanSlip(tc) == true) {
                Toast.makeText(this, "Edit successful", Toast.LENGTH_SHORT).show();
                this.onBackPressed();

            } else {
                Toast.makeText(this, "Edit failed", Toast.LENGTH_SHORT).show();
            }
        });



    }
    private void inispiner(){
        this.spNameMember.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mDaoLib.getListMember()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(152,4,45));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spNameMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMember = mDaoLib.getidMembers(spNameMember.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.spLibrary.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mDaoLib.getListLibrary()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(152,4,45));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spLibrary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idLibrary = mDaoLib.getIdLibrary(spLibrary.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.spBook.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mDaoLib.getListBook()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(152,4,45));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idBook = mDaoLib.getIdBook(spBook.getSelectedItem().toString());
                giaGoc = mDaoLib.getGiaGocSach(spBook.getSelectedItem().toString());
                giaKhuyenMai = mDaoLib.getGiaKhuyenMai(spBook.getSelectedItem().toString());
                float tongtien =  giaGoc * (Float.parseFloat(String.valueOf(giaKhuyenMai)) / 100);
                tongtien1 = giaGoc - tongtien;
                double tongtien2 =(double) tongtien1;
                double roundOff = (double) Math.round(tongtien2 * 100) / 100;
                tvGiaGoc.setText(roundOff + " $");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.trangThaiSatus.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                trangthai = 1;
            }else{
                trangthai = 0;
            }
        });


    }
    private void iniDate(){
        tvNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(UpDateLoanSlipActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String NgayGD = year+ "-"+  + (month+1) + "-" +dayOfMonth;
                        tvNgay.setText(NgayGD);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }
    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}