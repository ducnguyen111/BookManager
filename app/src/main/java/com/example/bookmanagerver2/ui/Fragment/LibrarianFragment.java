package com.example.bookmanagerver2.ui.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.model.ThuThu;
import com.example.bookmanagerver2.ui.Adapter.AdapterLib;
import com.example.bookmanagerver2.ui.LibrarianActivity;
import com.example.bookmanagerver2.ui.UpdateLibActivity;


import java.util.ArrayList;
import java.util.List;


public class LibrarianFragment extends Fragment implements AdapterLib.Listener {
    private EditText etSearch;
    private RecyclerView recyclerview;
    private Button btnAddKindofbook;

    public static int countLibri;
    private List<ThuThu> listThuThu;
    private DaoController mDaoThuThu;
    private AdapterLib mAdapterLib;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_librarian, container, false);


        etSearch = (EditText) view.findViewById(R.id.etSearch);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        btnAddKindofbook = (Button) view.findViewById(R.id.btnAddKindofbook);
        this.mDaoThuThu = new DaoController(getActivity());
        this.mAdapterLib = new AdapterLib(getActivity());
        this.listThuThu = new ArrayList<>();
        this.mAdapterLib.setListener(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        showData();


        btnAddKindofbook.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LibrarianActivity.class));
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            this.listThuThu.clear();
            this.listThuThu = this.mDaoThuThu.getListSearch(etSearch.getText().toString());
            this.mAdapterLib.setData(this.listThuThu);
            this.recyclerview.setAdapter(this.mAdapterLib);
            return false;
        });


        return view;
    }


    public void showData() {
        this.listThuThu = this.mDaoThuThu.getListThuTHu();
        countLibri = listThuThu.size();
        this.mAdapterLib.setData(this.listThuThu);
        this.recyclerview.setAdapter(this.mAdapterLib);
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    @Override
    public void onClickEdit(View view, int position) {
        ThuThu thu = this.listThuThu.get(position);
        if (thu.getMaThuThu() == 1) {
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("Unable to edit this character").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        } else {
            Intent intent = new Intent(getActivity(), UpdateLibActivity.class);
            intent.putExtra("thuthu2", thu);
            startActivity(intent);
        }

    }

    @Override
    public void onClickDelete(View view, int position) {
        ThuThu thuthu = this.listThuThu.get(position);
        if (thuthu.getMaThuThu() == 1) {
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("Not delete").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        } else {
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("Do you want to delete this character?").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDaoThuThu.deleteTitle(thuthu.getMaThuThu());
                    Toast.makeText(getActivity(), "Delete successful" + thuthu.getHoTenThuThu(), Toast.LENGTH_SHORT).show();
                    showData();
                    dialog.cancel();
                }
            });
        }
    }
}