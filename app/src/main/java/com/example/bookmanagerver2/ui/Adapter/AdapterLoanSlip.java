package com.example.bookmanagerver2.ui.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.dao.DaoLib;
import com.example.bookmanagerver2.model.LoanSlip.LoanSlip;

import java.util.List;

public class AdapterLoanSlip extends RecyclerView.Adapter<AdapterLoanSlip.ViewHolder>{
    private DaoLib mDaoLib;
    private List<LoanSlip> list;
    private Listener listener;
    private Context mContext;
    public AdapterLoanSlip (Context context){
        this.mContext = context;
        this.mDaoLib = new DaoLib(context);

    }
    public void setData(List<LoanSlip> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onClickEdit(View view, int position);
        void onClickDelete(View view, int position);
        void showData(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loanslip , parent , false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        LoanSlip loan = this.list.get(position);
        if(loan != null) {
            holder.tenNGuoiMuon.setText(loan.getTenNGuoiMuon());
            holder.tenSach.setText(loan.getTenSachMuon());
            if(loan.getTrangThaiMuon() == 1){
                holder.trangThai.setText("Paid");
                holder.trangThai.setTextColor(Color.GREEN);
            }else{
                holder.trangThai.setText("Unpaid");
                holder.trangThai.setTextColor(Color.RED);
            }
            holder.ivDelete.setOnClickListener(view ->{
                listener.onClickDelete(view , position);
            });
            holder.ivEdit.setOnClickListener(view ->{
                listener.onClickEdit(view , position);
            });
            holder.itemView.setOnClickListener(view ->{
                listener.showData(view , position);
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView idMember;
        private TextView tenNGuoiMuon;
        private TextView tenSach;
        private TextView trangThai;
        private ImageView ivEdit;
        private ImageView ivDelete;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);


            idMember = (CardView) itemView.findViewById(R.id.idMember);
            tenNGuoiMuon = (TextView) itemView.findViewById(R.id.tenNGuoiMuon);
            tenSach = (TextView) itemView.findViewById(R.id.tenSach);
            trangThai = (TextView) itemView.findViewById(R.id.trangThai);
            ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);

        }
    }
}
