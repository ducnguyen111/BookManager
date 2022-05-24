package com.example.bookmanagerver2.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.dao.DaoKindofbook;
import com.example.bookmanagerver2.model.Kindofbook;

import java.util.List;

public class AdapterKindofbook extends RecyclerView.Adapter<AdapterKindofbook.ViewHolder> {
    private DaoKindofbook mDaoKindofbook;
    private List<Kindofbook> list;
    private Listener listener;
    private Context mContext;
    public AdapterKindofbook (Context context){
        this.mContext = context;
        this.mDaoKindofbook = new DaoKindofbook(context);

    }
    public void setData(List<Kindofbook> list){
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

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kindofbook , parent , false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kindofbook kind = list.get(position);
        if(kind !=null){
            holder.nameKindofBookItem.setText(kind.getTenloaiSach());
            holder.ivDelete.setOnClickListener(view ->{
                listener.onClickDelete(view , position);
            });
            holder.ivEdit.setOnClickListener(view ->{
                listener.onClickEdit(view , position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameKindofBookItem;
        private ImageView ivEdit;
        private ImageView ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            nameKindofBookItem = (TextView) itemView.findViewById(R.id.nameKindofBookItem);
            ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);

        }
    }
}
