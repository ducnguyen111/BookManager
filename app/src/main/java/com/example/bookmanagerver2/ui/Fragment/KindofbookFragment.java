package com.example.bookmanagerver2.ui.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoKindofbook;
import com.example.bookmanagerver2.model.Kindofbook;
import com.example.bookmanagerver2.ui.Adapter.AdapterKindofbook;

import java.util.List;


public class KindofbookFragment extends Fragment implements AdapterKindofbook.Listener {
    private TextView titleThemLoai;
    private EditText etLoaiSach;
    private Button btnThemLT;
    private TextView xoaTextLT;
    private TextView tvtongsokindofbook;
    private RecyclerView recyclerview;
    private Button btnAddKindofbook;
    private DaoKindofbook mDaoKindofbook;
    private AdapterKindofbook mAdapterKindofbook;
    private List<Kindofbook> mKindofBookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kindofbook, container, false);

        tvtongsokindofbook = (TextView) view.findViewById(R.id.tvtongsokindofbook);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        btnAddKindofbook = (Button) view.findViewById(R.id.btnAddKindofbook);


        this.mAdapterKindofbook = new AdapterKindofbook(getActivity());
        this.mAdapterKindofbook.setListener(this);
        this.mDaoKindofbook = new DaoKindofbook(getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        showData();
        btnAddKindofbook.setOnClickListener(view1 -> {
            final Dialog dialog = new Dialog(getContext());
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialogaddkindofbook);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }


            titleThemLoai = (TextView) dialog.findViewById(R.id.titleThemLoai);
            etLoaiSach = (EditText) dialog.findViewById(R.id.etLoaiSach);
            btnThemLT = (Button) dialog.findViewById(R.id.btnThemLT);
            xoaTextLT = (TextView) dialog.findViewById(R.id.xoaTextLT);

            btnThemLT.setOnClickListener(view2 -> {
                String themText = etLoaiSach.getText().toString();


                if (themText.isEmpty() || themText.toString() == null) {
                    Toast.makeText(getActivity(), "cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Kindofbook tc = new Kindofbook(0, themText);
                    if (mDaoKindofbook.themKind(tc) == true) {
                        showData();
                        Toast.makeText(getActivity(), "Add successful!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Add failed!", Toast.LENGTH_SHORT).show();
                    }
                }


            });

            xoaTextLT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        });


        return view;
    }

    public void showData() {
        tvtongsokindofbook.setText("There are " + this.mDaoKindofbook.getListOfKindofBooks().size() + " kinds of books");
        this.mKindofBookList = this.mDaoKindofbook.getListOfKindofBooks();
        this.mAdapterKindofbook.setData(mKindofBookList);
        recyclerview.setAdapter(mAdapterKindofbook);
    }

    @Override
    public void onClickEdit(View view, int position) {
        Kindofbook kind = mKindofBookList.get(position);
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogaddkindofbook);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        titleThemLoai = (TextView) dialog.findViewById(R.id.titleThemLoai);
        etLoaiSach = (EditText) dialog.findViewById(R.id.etLoaiSach);
        btnThemLT = (Button) dialog.findViewById(R.id.btnThemLT);
        xoaTextLT = (TextView) dialog.findViewById(R.id.xoaTextLT);
        titleThemLoai.setText("Edit Kindofbook");
        btnThemLT.setText("Edit");
        etLoaiSach.setText(kind.getTenloaiSach());
        btnThemLT.setOnClickListener(view2 -> {
            String themText = etLoaiSach.getText().toString();
            Kindofbook tc = new Kindofbook(kind.getIdLoaiSach(), themText);
            if (mDaoKindofbook.editKind(tc) == true) {
                showData();
                Toast.makeText(getActivity(), "Successful fix!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(getActivity(), "Failed fix!", Toast.LENGTH_SHORT).show();
            }
        });

        xoaTextLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClickDelete(View view, int position) {
        Kindofbook kind = mKindofBookList.get(position);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("\n" +
                "Are you sure you want to remove").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDaoKindofbook.deleteTitle(kind.getIdLoaiSach());
                Toast.makeText(getActivity(), "Delete Thành Công" + kind.getTenloaiSach(), Toast.LENGTH_SHORT).show();
                showData();
                dialog.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();

    }
}