package com.example.bookmanagerver2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerver2.databinding.ActivityMainBinding;
import com.example.bookmanagerver2.model.ThuThu;
import com.example.bookmanagerver2.ui.Fragment.BookFragment;
import com.example.bookmanagerver2.ui.Fragment.HomeFragment;
import com.example.bookmanagerver2.ui.Fragment.KindofbookFragment;
import com.example.bookmanagerver2.ui.LoanSlipActivity;
import com.example.bookmanagerver2.ui.LoginActivity;
import com.example.bookmanagerver2.ui.MemberActivity;
import com.example.bookmanagerver2.ui.RegisterActivity;
import com.example.bookmanagerver2.ui.RevenueActivity;
import com.example.bookmanagerver2.ui.TopMostActivity;
import com.google.android.material.appbar.MaterialToolbar;

import de.hdodenhof.circleimageview.CircleImageView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private MaterialToolbar toolBar;
    private LinearLayout idPhieuMuon;
    private LinearLayout idLoaiSach;
    private LinearLayout idSach;
    private LinearLayout idThanhVien;
    private LinearLayout idTop10;
    private LinearLayout idDoanhThu;
    private LinearLayout idDoiMatKhau;
    private LinearLayout idLogout;
    private LinearLayout idHome;
    private ThuThu thuthu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, binding.drawer, toolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        binding.drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        View contentview = binding.drawer.getContentView();
        View menuView = binding.drawer.getMenuView();
        idPhieuMuon = (LinearLayout) menuView.findViewById(R.id.idPhieuMuon);
        idLoaiSach = (LinearLayout) menuView.findViewById(R.id.idLoaiSach);
        idSach = (LinearLayout) menuView.findViewById(R.id.idSach);
        idThanhVien = (LinearLayout) menuView.findViewById(R.id.idThanhVien);
        idTop10 = (LinearLayout) menuView.findViewById(R.id.idTop10);
        idDoanhThu = (LinearLayout) menuView.findViewById(R.id.idDoanhThu);
        idDoiMatKhau = (LinearLayout) menuView.findViewById(R.id.idDoiMatKhau);
        idLogout = (LinearLayout) menuView.findViewById(R.id.idLogout);
        idHome = (LinearLayout) menuView.findViewById(R.id.idHome);
        idPhieuMuon.setOnClickListener(this);
        idLoaiSach.setOnClickListener(this);
        idSach.setOnClickListener(this);
        idThanhVien.setOnClickListener(this);
        idTop10.setOnClickListener(this);
        idDoanhThu.setOnClickListener(this);
        idDoiMatKhau.setOnClickListener(this);
        idLogout.setOnClickListener(this);
        idHome.setOnClickListener(this);
        replaceFragment(new HomeFragment());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idHome:
                getSupportActionBar().setTitle("");
                replaceFragment(new HomeFragment());
                break;
            case R.id.idPhieuMuon:
                startActivity(new Intent(this, LoanSlipActivity.class));
                break;
            case R.id.idLoaiSach:
                getSupportActionBar().setTitle("Kind of book");
                KindofbookFragment kindofbookFragment = new KindofbookFragment();
                replaceFragment(kindofbookFragment);
                break;
            case R.id.idTop10:
                startActivity(new Intent(this, TopMostActivity.class));
                break;
            case R.id.idDoanhThu:
                startActivity(new Intent(this, RevenueActivity.class));
                break;
            case R.id.idDoiMatKhau:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.idThanhVien:

                startActivity(new Intent(this, MemberActivity.class));

                break;
            case R.id.idSach:
                getSupportActionBar().setTitle("Book");
                BookFragment bookFragment = new BookFragment();
                replaceFragment(bookFragment);
                break;


            case R.id.idLogout:
                comFirmExit();
                break;
        }
        binding.drawer.closeDrawer();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    public void comFirmExit() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Notification").setMessage("Are you sure you want to escape").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    public void showInfo(){
        int id = LoginActivity.id;
        Log.e("Login", String.valueOf(id));
        if (id != 1) {
            idThanhVien.setVisibility(View.GONE);
        } else {
            idThanhVien.setVisibility(View.VISIBLE);
        }
        thuthu = (ThuThu) getIntent().getSerializableExtra("thuthu");
        if (thuthu.getTaiKhoan().equals("dinhthanhminh")) {
            binding.idUser.setText(thuthu.getHoTenThuThu() + " (Chủ Tịch)");
            byte[] imageuuuu = thuthu.getThuthuPhoto();
            if (imageuuuu != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageuuuu, 0, imageuuuu.length);
                binding.idImageUser.setImageBitmap(bitmap);
            }
        } else {
            binding.idUser.setText(thuthu.getHoTenThuThu());
            byte[] imageuuuu = thuthu.getThuthuPhoto();
            if (imageuuuu != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageuuuu, 0, imageuuuu.length);
                binding.idImageUser.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showInfo();
    }
}