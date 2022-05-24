package com.example.bookmanagerver2.ui.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoBook;
import com.example.bookmanagerver2.dao.DaoController;

import com.example.bookmanagerver2.model.Book;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class Adapterbook extends RecyclerView.Adapter<Adapterbook.ViewHolder>{
    private DaoBook mDaoBook;
    private List<Book> list;
    private Listener listener;
    private Context mContext;
    private TextView txtEdit;
    private TextView txtAddPhieumuon;
    private TextView txtXoa;
    public Adapterbook (Context context){
        this.mContext = context;
        this.mDaoBook = new DaoBook(context);

    }
    public void setData(List<Book> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onClickEdit(View view, int position);
        void onClickDelete(View view, int position);
        void onAddLoanClip(View view, int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book , parent , false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        Book kind = list.get(position);
        if(kind !=null){
            float tongtien =  (kind.getMoney()) * (Float.parseFloat(String.valueOf(kind.getMoneyKhuyenmai())) / 100);
            float tongtien1 = (kind.getMoney()) - tongtien;
            double tongtien2 =(double) tongtien1;
            double roundOff = (double) Math.round(tongtien2 * 100) / 100;
            holder.nameBook.setText(kind.getTenSach());
            holder.nameKindofBookItem.setText(kind.getTenLoaiSach());
            holder.idGiaSachGoc.setText(String.valueOf(kind.getMoney()) +"$");
            holder.idGiaSachGoc.setPaintFlags(holder.idGiaSachGoc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.idGiamGia.setText("-"+String.valueOf(kind.getMoneyKhuyenmai()) +"%");
            holder.idGiaSachDatinh.setText(String.valueOf(roundOff) + "$");
            holder.itemView.setOnClickListener(view ->{
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(mContext).inflate(R.layout.bottomsheet, (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomsheetcontainer));
                txtEdit = (TextView) bottomSheetView.findViewById(R.id.txt_edit);
                txtAddPhieumuon = (TextView) bottomSheetView.findViewById(R.id.txt_addPhieumuon);
                txtXoa = (TextView)bottomSheetView. findViewById(R.id.txt_Xoa);

                txtEdit.setOnClickListener(view1 ->{
                    listener.onClickEdit(view , position);
                    bottomSheetDialog.cancel();
                });
                txtAddPhieumuon.setOnClickListener(view1 ->{
                    listener.onAddLoanClip(view , position);
                    bottomSheetDialog.cancel();
                });
                txtXoa.setOnClickListener(view1 ->{
                    listener.onClickDelete(view , position);
                    bottomSheetDialog.cancel();
                });
                bottomSheetView.findViewById(R.id.txt_Huy).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView idMember;
        private TextView nameBook;
        private TextView nameKindofBookItem;
        private TextView idGiaSachDatinh;
        private TextView idGiaSachGoc;
        private TextView idGiamGia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            idMember = (CardView)itemView. findViewById(R.id.idMember);
            nameBook = (TextView)itemView. findViewById(R.id.nameBook);
            nameKindofBookItem = (TextView)itemView. findViewById(R.id.nameKindofBookItem);
            idGiaSachDatinh = (TextView) itemView.findViewById(R.id.idGiaSachDatinh);
            idGiaSachGoc = (TextView)itemView. findViewById(R.id.idGiaSachGoc);
            idGiamGia = (TextView) itemView.findViewById(R.id.idGiamGia);

        }
    }
}
