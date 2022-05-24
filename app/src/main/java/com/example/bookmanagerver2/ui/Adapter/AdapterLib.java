package com.example.bookmanagerver2.ui.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.bookmanagerver2.model.ThuThu;


import java.util.List;

public class AdapterLib extends RecyclerView.Adapter<AdapterLib.ViewHolder>{
    private DaoController mDaoThuTHu;
    private List<ThuThu> list;
    private Listener listener;
    private Context mContext;
    public AdapterLib (Context context){
        this.mContext = context;
        this.mDaoThuTHu = new DaoController(context);

    }
    public void setData(List<ThuThu> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener {
        void onClickEdit(View view, int position);
        void onClickDelete(View view, int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lib , parent , false);
                return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThuThu thuthu = this.list.get(position);
        if(thuthu !=null){
            byte[] imageuuuu = thuthu.getThuthuPhoto();
            if(imageuuuu !=null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageuuuu, 0, imageuuuu.length);
                holder.imageLib.setImageBitmap(bitmap);
            }
            holder.ivDelete.setOnClickListener(view ->{
                listener.onClickDelete(view , position);
            });
            holder.ivEdit.setOnClickListener(view ->{
                listener.onClickEdit(view , position);
            });

            holder.username.setText(thuthu.getTaiKhoan());
            holder.nameLib.setText(thuthu.getHoTenThuThu());
            if(position % 2 ==0){
                holder.username.setTextColor(Color.RED);
            }else {
                holder.username.setTextColor(Color.GREEN);
            }
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView idMember;
        private TextView nameLib;
        private TextView username;
        private ImageView ivEdit;
        private ImageView ivDelete;
        private ImageView imageLib;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            idMember = (CardView) itemView.findViewById(R.id.idMember);
            nameLib = (TextView) itemView.findViewById(R.id.nameLib);
            username = (TextView) itemView.findViewById(R.id.username);
            ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);
            ivDelete = (ImageView)itemView. findViewById(R.id.ivDelete);
            imageLib = (ImageView) itemView.findViewById(R.id.imageLib);


        }
    }
}
