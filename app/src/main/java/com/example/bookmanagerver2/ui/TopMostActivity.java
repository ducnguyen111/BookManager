package com.example.bookmanagerver2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoBook;
import com.example.bookmanagerver2.model.TopMost;
import com.example.bookmanagerver2.ui.Adapter.AdapterTopMost;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class TopMostActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private RecyclerView recyclerview;
    private DaoBook mDaoBook;
    private AdapterTopMost mAdapterTopMost;
    private List<TopMost> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_most);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        this.list = new ArrayList<>();
        this.mDaoBook = new DaoBook(this);
        this.mAdapterTopMost = new AdapterTopMost(this);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        showData();
    }
    public void showData(){
        this.list =this.mDaoBook.getListTopMost();
        this.mAdapterTopMost.setData(this.list);
        recyclerview.setAdapter(mAdapterTopMost);
    }
    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Top Most");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}