package com.example.bookmanagerver2.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoBook;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.dao.DaoKindofbook;
import com.example.bookmanagerver2.dao.DaoLib;
import com.example.bookmanagerver2.dao.DaoMember;
import com.example.bookmanagerver2.ui.TopMostActivity;


import java.text.DecimalFormat;
import java.text.NumberFormat;


public class HomeFragment extends Fragment {
    private LottieAnimationView logoHomePageActivity;
    private CardView idMember;
    private CardView idKindofbook;
    private CardView idBook;
    private CardView idloanslip;
    private CardView idrevenue;
    private CardView idTop10;
    private TextView tsKindOfBook ,tsBook ,tsMember ,tsLoanSlip ,tsRene;
    private DaoKindofbook mDaoKindofbook;
    private DaoBook mDaoBook;
    private DaoController mDaoThuThu;
    private DaoMember mDaoMember;
    private DaoLib mDaoLib;
    private NumberFormat fm = new DecimalFormat("#,###");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        logoHomePageActivity = (LottieAnimationView)view. findViewById(R.id.logoHomePageActivity);
        idMember = (CardView) view.findViewById(R.id.idMember);
        idKindofbook = (CardView) view.findViewById(R.id.idKindofbook);
        idBook = (CardView) view.findViewById(R.id.idBook);
        idloanslip = (CardView) view.findViewById(R.id.idloanslip);
        idrevenue = (CardView) view.findViewById(R.id.idrevenue);
        idTop10 = (CardView)view. findViewById(R.id.idTop10);
        tsKindOfBook =(TextView) view.findViewById(R.id.tsKindOfBook);
        tsBook =(TextView) view.findViewById(R.id.tsBook);
        tsMember =(TextView) view.findViewById(R.id.tsMember);
        tsLoanSlip =(TextView) view.findViewById(R.id.tsLoanSlip);
        tsRene =(TextView) view.findViewById(R.id.tsRene);

        this.mDaoKindofbook = new DaoKindofbook(getActivity());
        this.mDaoBook = new DaoBook(getActivity());
        this.mDaoThuThu = new DaoController(getActivity());
        this.mDaoMember = new DaoMember(getActivity());
        this.mDaoLib = new DaoLib(getActivity());

        showData();
        idTop10.setOnClickListener(view1 ->{
            startActivity(new Intent(getActivity(), TopMostActivity.class));
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        showData();

    }
    public void showData(){
        int soLuongMember = Integer.parseInt(String.valueOf(this.mDaoMember.getListMember().size()));
        int soLuongThuTHu = Integer.parseInt(String.valueOf(this.mDaoThuThu.getListThuTHu().size()));
        int tongSoMember = soLuongMember + soLuongThuTHu;
        tsKindOfBook.setText(String.valueOf(this.mDaoKindofbook.getListOfKindofBooks().size()));
        tsBook.setText(String.valueOf(this.mDaoBook.getBookList().size()));
        tsMember.setText(String.valueOf(tongSoMember));
        tsLoanSlip.setText(String.valueOf(this.mDaoLib.getListofLoanSlips().size()));
        tsRene.setText(fm.format((this.mDaoBook.tienDoanhthu() * 23000)) + " VND");


    }
}