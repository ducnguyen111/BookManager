package com.example.bookmanagerver2.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoBook;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.model.TopMost;


import java.util.List;

public class AdapterTopMost extends RecyclerView.Adapter<AdapterTopMost.ViewHolder>{
    private DaoBook mDaoBook;
    private List<TopMost> list;
    private Context mContext;
    private TextView txtEdit;
    private TextView txtAddPhieumuon;
    private TextView txtXoa;
    public AdapterTopMost (Context context){
        this.mContext = context;
        this.mDaoBook = new DaoBook(context);

    }
    public void setData(List<TopMost> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topmost , parent , false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        TopMost top = this.list.get(position);
        if(top != null) {
            holder.nameBook.setText(top.getTenSach());
            holder.soLuong.setText(String.valueOf(top.getSoLuong()) + " Pass");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView idMember;
        private TextView nameBook;
        private TextView soLuong;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            idMember = (CardView)itemView. findViewById(R.id.idMember);
            nameBook = (TextView)itemView. findViewById(R.id.nameBook);
            soLuong = (TextView) itemView.findViewById(R.id.soLuong);

        }
    }
}
