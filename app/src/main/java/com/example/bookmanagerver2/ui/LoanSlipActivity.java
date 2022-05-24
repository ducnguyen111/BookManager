package com.example.bookmanagerver2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoLib;
import com.example.bookmanagerver2.model.LoanSlip.LoanSlip;
import com.example.bookmanagerver2.ui.Adapter.AdapterLoanSlip;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class LoanSlipActivity extends AppCompatActivity implements AdapterLoanSlip.Listener{
    private MaterialToolbar toolBar;
    private NeumorphCardView contenSearch;
    private EditText etSearch;
    private RecyclerView recyclerview;
    private Button btnAddKindofbook;
    private DaoLib mDaoLib;
    private AdapterLoanSlip mAdapterLoanSlip;
    private List<LoanSlip> mListOfLoanSlip;
    private TextView thongtinGD;
    private TextView tvMember;
    private TextView tvLib;
    private TextView tvBook;
    private TextView tvPrice;
    private TextView tvDate;
    private TextView tvTrangThai;
    private CheckBox cbChuaTra;
    private RadioButton radiochuacha;
    private RadioButton radiodacha;
    private TextView btnClear;
    private CheckBox cbDaTra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_slip);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        contenSearch = (NeumorphCardView) findViewById(R.id.contenSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        btnAddKindofbook = (Button) findViewById(R.id.btnAddKindofbook);



        radiochuacha = (RadioButton) findViewById(R.id.radiochuacha);
        radiodacha = (RadioButton) findViewById(R.id.radiodacha);
        btnClear = (TextView) findViewById(R.id.btnClear);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);


        this.mAdapterLoanSlip = new AdapterLoanSlip(this);
        this.mDaoLib = new DaoLib(this);
        this.mAdapterLoanSlip.setListener(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.mListOfLoanSlip = new ArrayList<>();

        showData();

        btnAddKindofbook.setOnClickListener(view ->{
            startActivity(new Intent(this , AddLoanSlipActivity.class));
        });
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            this.mListOfLoanSlip.clear();
            this.mListOfLoanSlip = this.mDaoLib.getListofLoanSlipsSearch(radiochuacha.isChecked() ,radiodacha.isChecked() , etSearch.getText().toString()) ;
            this.mAdapterLoanSlip.setData(this.mListOfLoanSlip);
            this.recyclerview.setAdapter(this.mAdapterLoanSlip);
            return false;
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radiochuacha:
                        mListOfLoanSlip.clear();
                        mListOfLoanSlip = mDaoLib.getListofLoanSlipsSearch(radiochuacha.isChecked() ,radiodacha.isChecked() , etSearch.getText().toString().trim());
                        mAdapterLoanSlip.setData(mListOfLoanSlip);
                        recyclerview.setAdapter(mAdapterLoanSlip);
                        break;
                    case R.id.radiodacha:
                        mListOfLoanSlip.clear();
                        mListOfLoanSlip = mDaoLib.getListofLoanSlipsSearch(radiochuacha.isChecked() ,radiodacha.isChecked() , etSearch.getText().toString().trim()) ;
                        mAdapterLoanSlip.setData(mListOfLoanSlip);
                        recyclerview.setAdapter(mAdapterLoanSlip);
                        break;

                }
            }
        });


    }
    public void showData()  {

        this.mListOfLoanSlip =this.mDaoLib.getListofLoanSlips();
        this.mAdapterLoanSlip.setData(mListOfLoanSlip);
        recyclerview.setAdapter(mAdapterLoanSlip);
    }
    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Loan Slip");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClickEdit(View view, int position) {
        LoanSlip loan = this.mListOfLoanSlip.get(position);
        Intent intent = new Intent(LoanSlipActivity.this , UpDateLoanSlipActivity.class);
        intent.putExtra("loanslip", loan);
        startActivity(intent);
    }

    @Override
    public void onClickDelete(View view, int position) {
        LoanSlip loan = this.mListOfLoanSlip.get(position);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Notification").setMessage("Are you sure you want to delete?").setPositiveButton("Ok" , null).setNegativeButton("Cancel" , null).show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDaoLib.deleteTitle(loan.getId());
                Toast.makeText(LoanSlipActivity.this, "Delete Successfully" + loan.getTenNGuoiMuon(), Toast.LENGTH_SHORT).show();
                showData();
                dialog.cancel();
            }
        });
    }

    @Override
    public void showData(View view, int position) {
        LoanSlip loan = this.mListOfLoanSlip.get(position);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogshowloanslip);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT ,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        thongtinGD = (TextView) dialog.findViewById(R.id.thongtinGD);
        tvMember = (TextView) dialog.findViewById(R.id.tvMember);
        tvLib = (TextView)dialog. findViewById(R.id.tvLib);
        tvBook = (TextView) dialog.findViewById(R.id.tvBook);
        tvPrice = (TextView)dialog. findViewById(R.id.tvPrice);
        tvDate = (TextView) dialog.findViewById(R.id.tvDate);
        tvTrangThai = (TextView) dialog.findViewById(R.id.tvTrangThai);


        tvMember.setText(loan.getTenNGuoiMuon());
        tvLib.setText(loan.getNguoiChoMuon());
        tvBook.setText(loan.getTenSachMuon());
        tvMember.setText(loan.getTenNGuoiMuon());
        tvPrice.setText(loan.getTienThue() + " $");
        tvDate.setText(loan.getNgayThue());
        if(loan.getTrangThaiMuon() == 1){
            tvTrangThai.setText("Paid");
            tvTrangThai.setTextColor(Color.GREEN);
        }else{
            tvTrangThai.setText("Unpaid");
            tvTrangThai.setTextColor(Color.RED);
        }
        dialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
    public TextWatcher textchange  = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String endDate = etSearch.getText().toString().trim();
            if(endDate.isEmpty() ){

                btnClear.setVisibility(View.INVISIBLE);
            }else {
                btnClear.setVisibility(View.VISIBLE);

            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        radiochuacha.setChecked(false);
        radiodacha.setChecked(false);
    }
}