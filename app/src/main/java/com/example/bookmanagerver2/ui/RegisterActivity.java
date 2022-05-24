package com.example.bookmanagerver2.ui;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.model.ThuThu;
import com.google.android.material.appbar.MaterialToolbar;

public class RegisterActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private TextView createTaiKhoan;
    private TextView tvghichu;
    private EditText etmatkhaucu;
    private EditText etmatkhaumoi;
    private EditText nhaplaimatkhaumoi;
    private Button btDangKi;
    private LinearLayout ll1;
    private TextView textDangNhap;
    private DaoController mDaoTHuThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        createTaiKhoan = (TextView) findViewById(R.id.createTaiKhoan);
        tvghichu = (TextView) findViewById(R.id.tvghichu);
        etmatkhaucu = (EditText) findViewById(R.id.etmatkhaucu);
        etmatkhaumoi = (EditText) findViewById(R.id.etmatkhaumoi);
        nhaplaimatkhaumoi = (EditText) findViewById(R.id.nhaplaimatkhaumoi);
        btDangKi = (Button) findViewById(R.id.btDangKi);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        textDangNhap = (TextView) findViewById(R.id.textDangNhap);
        this.mDaoTHuThu = new DaoController(this);

        btDangKi.setOnClickListener(view -> {
            if (!etmatkhaucu.getText().toString().equals(LoginActivity.password)) {
                tvghichu.setText("Old Password Incorrect");
                tvghichu.setTextColor(Color.RED);
                return;

            } else if (isEmpty(etmatkhaucu.getText().toString()) || isEmpty(etmatkhaumoi.getText().toString()) || isEmpty(nhaplaimatkhaumoi.getText().toString())) {
                tvghichu.setText("Fields cannot be left blank");
                tvghichu.setTextColor(Color.RED);
                return;
            } else if (!etmatkhaumoi.getText().toString().equals(nhaplaimatkhaumoi.getText().toString())) {
                tvghichu.setText("Re-enter Unmatched Password");
                tvghichu.setTextColor(Color.RED);
                return;
            } else {
                ThuThu thutthu1 = new ThuThu(LoginActivity.id, nhaplaimatkhaumoi.getText().toString());
                if (mDaoTHuThu.changePassword(thutthu1) == true) {
                    Toast.makeText(this, "Edit successful", Toast.LENGTH_SHORT).show();
                    this.onBackPressed();

                } else {
                    Toast.makeText(this, "Edit failed!", Toast.LENGTH_SHORT).show();
                }
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