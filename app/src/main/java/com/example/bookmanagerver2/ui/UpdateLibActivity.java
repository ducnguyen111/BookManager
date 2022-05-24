package com.example.bookmanagerver2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.model.ThuThu;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateLibActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    private MaterialToolbar toolBar;
    private LinearLayout contenImage;
    private CircleImageView idImageUser;
    private Button chonanh;
    private TextView createTaiKhoan;
    private TextView tvghichu;
    private EditText etHoten;
    private EditText etUsernameRegister;
    private EditText etPasswordRegister;
    private EditText etPassworRegisterdAgin;
    private Button btDangKi;
    private LinearLayout ll1;
    private TextView textDangNhap;
    private DaoController mDaoTHuThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lib);

        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        contenImage = (LinearLayout) findViewById(R.id.contenImage);
        idImageUser = (CircleImageView) findViewById(R.id.idImageUser);
        chonanh = (Button) findViewById(R.id.chonanh);
        createTaiKhoan = (TextView) findViewById(R.id.createTaiKhoan);
        tvghichu = (TextView) findViewById(R.id.tvghichu);
        etHoten = (EditText) findViewById(R.id.etHoten);
        etUsernameRegister = (EditText) findViewById(R.id.etUsernameRegister);
        etPasswordRegister = (EditText) findViewById(R.id.etmatkhaumoi);
        etPassworRegisterdAgin = (EditText) findViewById(R.id.nhaplaimatkhaumoi);
        btDangKi = (Button) findViewById(R.id.btDangKi);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        textDangNhap = (TextView) findViewById(R.id.textDangNhap);
        ThuThu thuthu1 = (ThuThu) getIntent().getSerializableExtra("thuthu2");
        etHoten.setText(thuthu1.getHoTenThuThu());
        etUsernameRegister.setText(thuthu1.getTaiKhoan());
        etPasswordRegister.setText(thuthu1.getMaKhau());
        etPassworRegisterdAgin.setText(thuthu1.getMaKhau());
        mDaoTHuThu = new DaoController(this);
        byte[] imageuuuu = thuthu1.getThuthuPhoto();
        if (imageuuuu != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageuuuu, 0, imageuuuu.length);
            idImageUser.setImageBitmap(bitmap);
        }
        chonanh.setOnClickListener(view -> {
            ActivityCompat.requestPermissions(
                    UpdateLibActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_GALLERY
            );
        });
        btDangKi.setOnClickListener(view -> {
            String hoten = etHoten.getText().toString();
            String user = etUsernameRegister.getText().toString();
            String password = etPasswordRegister.getText().toString();

            if (etUsernameRegister.getText().toString().isEmpty() || etUsernameRegister.getText().toString() == null) {
                tvghichu.setText("You Have Not Entered Your Username");
                tvghichu.setTextColor(Color.RED);
                return;
            } else if (etPasswordRegister.getText().toString().isEmpty() || etPasswordRegister.getText().toString() == null) {
                tvghichu.setText("You Have Not Entered Password");
                tvghichu.setTextColor(Color.RED);
                return;
            } else if (etPassworRegisterdAgin.getText().toString().isEmpty() || etPassworRegisterdAgin.getText().toString() == null) {
                tvghichu.setText("You Have Not Re-entered Password");
                tvghichu.setTextColor(Color.RED);
                return;
            } else if (etPasswordRegister.getText().toString().equals(etPassworRegisterdAgin.getText().toString()) == false) {
                tvghichu.setText("Re-enter Password Do Not Duplicate");
                tvghichu.setTextColor(Color.RED);
                etPasswordRegister.setText("");
                etPassworRegisterdAgin.setText("");
                return;
            } else if (imageViewToByte(idImageUser).length > 700000) {
                tvghichu.setText("Please choose an invalid photo again");
                Toast.makeText(this, "Ảnh của bạn quá nặng tôi bó tay", Toast.LENGTH_SHORT).show();
                tvghichu.setTextColor(Color.RED);
                return;
            } else {
                ThuThu thutthu = new ThuThu(user, thuthu1.getMaThuThu(), password, hoten, imageViewToByte(idImageUser));
                if (mDaoTHuThu.editKind(thutthu) == true) {
                    Toast.makeText(this, "Successful fix!", Toast.LENGTH_SHORT).show();
                    this.onBackPressed();

                } else {
                    Toast.makeText(this, "Fix failure!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8), true);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                idImageUser.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}