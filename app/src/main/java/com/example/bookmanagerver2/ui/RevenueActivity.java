package com.example.bookmanagerver2.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoBook;
import com.example.bookmanagerver2.model.TopMost;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class RevenueActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private BarChart pieChart;
    private List<TopMost> list;
    private DaoBook mDaoBook;
    private NeumorphCardView btnStartDate;
    private TextView dayStart;
    private TextView monthAndYearStart;
    private NeumorphCardView btnEndDate;
    private TextView dayEnd;
    private TextView monthAndYearEnd;
    private NeumorphCardView btnSubmit;
    private TextView tvRevenue;
    private TextView tvDaTra;
    private TextView tvChuaTra;

    private DatePickerDialog mDatePickerDialog;
    private String tvStartDate , tvEndDate;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);


        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        btnStartDate = (NeumorphCardView) findViewById(R.id.btnStartDate);
        dayStart = (TextView) findViewById(R.id.dayStart);
        monthAndYearStart = (TextView) findViewById(R.id.monthAndYearStart);
        btnEndDate = (NeumorphCardView) findViewById(R.id.btnEndDate);
        dayEnd = (TextView) findViewById(R.id.dayEnd);
        monthAndYearEnd = (TextView) findViewById(R.id.monthAndYearEnd);
        btnSubmit = (NeumorphCardView) findViewById(R.id.btnSubmit);
        tvRevenue = (TextView) findViewById(R.id.tvRevenue);
        tvDaTra = (TextView) findViewById(R.id.tvDaTra);
        tvChuaTra = (TextView) findViewById(R.id.tvChuaTra);
        pieChart = (BarChart) findViewById(R.id.pieChart);


        this.mDaoBook = new DaoBook(this);
        initToolBar();
        this.mDaoBook = new DaoBook(this);
        ArrayList<BarEntry> pieChart1 =new ArrayList<>();
        pieChart1.add(new BarEntry(1 , mDaoBook.tienThuThang1()));
        pieChart1.add(new BarEntry(2 , mDaoBook.tienThuThang2()));
        pieChart1.add(new BarEntry(3 , mDaoBook.tienThuThang3()));
        pieChart1.add(new BarEntry(4 , mDaoBook.tienThuThang4()));
        pieChart1.add(new BarEntry(5 , mDaoBook.tienThuThang5()));
        pieChart1.add(new BarEntry(6 , mDaoBook.tienThuThang6()));
        pieChart1.add(new BarEntry(7 , mDaoBook.tienThuThang7()));
        pieChart1.add(new BarEntry(8 , mDaoBook.tienThuThang8()));
        pieChart1.add(new BarEntry(9 , mDaoBook.tienThuThang9()));
        pieChart1.add(new BarEntry(10 , mDaoBook.tienThuThang10()));
        pieChart1.add(new BarEntry(11 , mDaoBook.tienThuThang11()));
        pieChart1.add(new BarEntry(12 , mDaoBook.tienThuThang12()));

        BarDataSet bar_data_set = new BarDataSet(pieChart1 , "LoanSlip");
        bar_data_set.setColors(ColorTemplate.MATERIAL_COLORS);
        bar_data_set.setValueTextColor(Color.rgb(152,4,45));
        bar_data_set.setValueTextSize(16f);
        BarData barData = new BarData(bar_data_set);
        pieChart.setFitBars(true);
        pieChart.setData(barData);
        pieChart.getDescription().setText("Dola");
        pieChart.animateY(3000);
        datepiker();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datepiker(){
        btnEndDate.setOnClickListener(view ->{
            final Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int yeat = cal.get(Calendar.YEAR);
            mDatePickerDialog = new DatePickerDialog(this , new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    final int date = i2 ;
                    String monthandYear = i + "-" +(i1 +1 )  ;
                    dayEnd.setText(String.valueOf(date));
                    monthAndYearEnd.setText(monthandYear);
                    tvEndDate = i + "-" + i1 + "-" + i2;
                    checkDate(tvStartDate , tvEndDate);
                    //   Toast.makeText(RevenueActivity.this, tvEndDate, Toast.LENGTH_SHORT).show();
                }
            },yeat , month , day);
            mDatePickerDialog.show();


        });
        btnStartDate.setOnClickListener(view ->{
            final Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int yeat = cal.get(Calendar.YEAR);
            mDatePickerDialog = new DatePickerDialog(this , new DatePickerDialog.OnDateSetListener(){

                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    final int date = i2 ;
                    String monthandYear = i + "-" +(i1 +1 )  ;
                    dayStart.setText(String.valueOf(date));
                    monthAndYearStart.setText(monthandYear);
                    tvStartDate = i + "-" + i1 + "-" + i2;

                    checkDate(tvStartDate , tvEndDate);
                }
            },yeat , month , day);

            mDatePickerDialog.show();

        });
    }
    private void checkDate(String dateStart , String dateEnd){

        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (dfm.parse(dateStart).before(dfm.parse(dateEnd))){
                String ngay1 =monthAndYearStart.getText().toString()+ "-" +dayStart.getText().toString();
                String ngay2 =  monthAndYearEnd.getText().toString()+ "-" +dayEnd.getText().toString() ;
                float t = mDaoBook.tienDoanhthuTheoNgay(ngay1.trim(),ngay2.trim());
                if(t == 0.0 ){
                    tvRevenue.setText("Revenue:  No Hope");
                    tvDaTra.setText("Paid: "+mDaoBook.soluongPhieuDaTra(ngay1.trim(),ngay2.trim()));
                    tvChuaTra.setText("Unpaid: "+mDaoBook.soluongPhieuChuaTra(ngay1.trim(),ngay2.trim()));
                }else{
                    tvRevenue.setText("Revenue: "+t+" $(no discount)");
                    tvDaTra.setText("Paid: "+mDaoBook.soluongPhieuDaTra(ngay1.trim(),ngay2.trim()));
                    tvChuaTra.setText("Unpaid: "+mDaoBook.soluongPhieuChuaTra(ngay1.trim(),ngay2.trim()));
                }
                dayEnd.setTextColor(Color.rgb(152,4,45));
                monthAndYearEnd.setTextColor(Color.rgb(152,4,45));
                dayStart.setTextColor(Color.rgb(152,4,45));
                monthAndYearStart.setTextColor(Color.rgb(152,4,45));


            }else if( dfm.parse(dateEnd).before(dfm.parse(dateStart))){
                Toast.makeText(this, "End Date Must Not Be Less Than Start Date", Toast.LENGTH_SHORT).show();
                dayEnd.setTextColor(Color.RED);
                monthAndYearEnd.setTextColor(Color.RED);
                return;
            }else if( dfm.parse(dateStart).equals(dfm.parse(dateEnd))){
                Toast.makeText(this, "The End Date Can't Be The Same As The Start Date", Toast.LENGTH_SHORT).show();
                dayStart.setTextColor(Color.RED);
                monthAndYearStart.setTextColor(Color.RED);
                dayEnd.setTextColor(Color.RED);
                monthAndYearEnd.setTextColor(Color.RED);
                return;
            }
        }catch(Exception e) {

        }
    }


    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Revenue");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}