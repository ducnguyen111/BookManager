package com.example.bookmanagerver2.ui.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookmanagerver2.R;
import com.example.bookmanagerver2.dao.DaoBook;
import com.example.bookmanagerver2.dao.DaoController;
import com.example.bookmanagerver2.dao.DaoKindofbook;
import com.example.bookmanagerver2.model.Book;
import com.example.bookmanagerver2.ui.Adapter.Adapterbook;
import com.example.bookmanagerver2.ui.AddLoanSlipActivity;

import java.util.ArrayList;
import java.util.List;


public class BookFragment extends Fragment implements Adapterbook.Listener {
    private TextView tvtongsokindofbook;
    private RecyclerView recyclerview;
    private Button btnAddKindofbook;
    private EditText etSearch;
    private DaoBook mDaoBook;
    private DaoKindofbook mDaoKindofbook;
    private Adapterbook mAdapterBook;
    private List<Book> mListBook;
    private TextView titleAddBook;
    private EditText tvNameBook;
    private EditText tvGiaGoc;
    private EditText tvGiaKhuyenmai;
    private Spinner spLoaiSach;
    private Button xoaTextGD;
    private Button btnThemGD;
    private List<String> nameKindOfBook;
    private int idLoaiSach;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);


        tvtongsokindofbook = (TextView) view.findViewById(R.id.tvtongsokindofbook);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        btnAddKindofbook = (Button) view.findViewById(R.id.btnAddKindofbook);


        etSearch = (EditText) view.findViewById(R.id.etSearch);


        this.mAdapterBook = new Adapterbook(getActivity());
        this.mAdapterBook.setListener(this);
        this.mDaoBook = new DaoBook(getActivity());
        this.mDaoKindofbook = new DaoKindofbook(getActivity());
        nameKindOfBook = new ArrayList<String>();
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.HORIZONTAL , false));
        showData();

        btnAddKindofbook.setOnClickListener(view2 -> {
            final Dialog dialog = new Dialog(getContext());
            dialog.setCancelable(false); // nhấn ra ngoài nó mới không chị cancel
            dialog.setContentView(R.layout.dailogthemsach);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            titleAddBook = (TextView) dialog.findViewById(R.id.titleAddBook);
            tvNameBook = (EditText) dialog.findViewById(R.id.tvNameBook);
            tvGiaGoc = (EditText) dialog.findViewById(R.id.tvGiaGoc);
            tvGiaKhuyenmai = (EditText) dialog.findViewById(R.id.tvGiaKhuyenmai);
            spLoaiSach = (Spinner) dialog.findViewById(R.id.spLoaiSach);
            xoaTextGD = (Button) dialog.findViewById(R.id.xoaTextGD);
            btnThemGD = (Button) dialog.findViewById(R.id.btnThemGD);
            initSpinner();
            btnThemGD.setOnClickListener(view3 -> {

                if (tvNameBook.getText().toString().isEmpty() || tvNameBook.getText().toString() == null || String.valueOf(tvGiaGoc.getText().toString()).isEmpty() || String.valueOf(tvGiaGoc.getText().toString()) == null || String.valueOf(tvGiaKhuyenmai.getText().toString()).isEmpty() || String.valueOf(tvGiaKhuyenmai.getText().toString()) == null) {
                    Toast.makeText(getActivity(), "Cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }else if (Integer.parseInt(tvGiaKhuyenmai.getText().toString().trim()) > 100 || Integer.parseInt(tvGiaKhuyenmai.getText().toString().trim()) <0){
                    Toast.makeText(getActivity(), "Discount only from 0 to 100", Toast.LENGTH_SHORT).show();
                    return;
                }


                else {
                    String nameBook = tvNameBook.getText().toString();
                    float giagoc = Float.parseFloat(tvGiaGoc.getText().toString().trim());
                    int khuyenMai = Integer.parseInt(tvGiaKhuyenmai.getText().toString().trim());
                    String tenLoai = spLoaiSach.getSelectedItem().toString();

                    Book tc = new Book(0, nameBook, tenLoai, giagoc, khuyenMai, idLoaiSach);
                    if (mDaoBook.themBook(tc) == true) {
                        showData();
                        Toast.makeText(getActivity(), "Add successful!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Add failed!", Toast.LENGTH_SHORT).show();
                    }
                }


            });


            xoaTextGD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            this.mListBook.clear();
            this.mListBook = this.mDaoBook.getListSearch(etSearch.getText().toString());
            this.mAdapterBook.setData(this.mListBook);
            this.recyclerview.setAdapter(this.mAdapterBook);
            return false;
        });

        return view;
    }

    public void showData() {
        tvtongsokindofbook.setText("There are " + this.mDaoBook.getBookList().size() + " kinds of books");
        this.mListBook = this.mDaoBook.getBookList();
        this.mAdapterBook.setData(this.mListBook);
        recyclerview.setAdapter(mAdapterBook);
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();

    }

    public void initSpinner() {
        this.spLoaiSach.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mDaoKindofbook.getListNameKindOfBook()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(92, 224, 254));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idLoaiSach = mDaoKindofbook.getIdLoaiSach(spLoaiSach.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClickEdit(View view, int position) {
        Book book = mListBook.get(position);
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dailogthemsach);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        titleAddBook = (TextView) dialog.findViewById(R.id.titleAddBook);
        tvNameBook = (EditText) dialog.findViewById(R.id.tvNameBook);
        tvGiaGoc = (EditText) dialog.findViewById(R.id.tvGiaGoc);
        tvGiaKhuyenmai = (EditText) dialog.findViewById(R.id.tvGiaKhuyenmai);
        spLoaiSach = (Spinner) dialog.findViewById(R.id.spLoaiSach);
        xoaTextGD = (Button) dialog.findViewById(R.id.xoaTextGD);
        btnThemGD = (Button) dialog.findViewById(R.id.btnThemGD);
        titleAddBook.setText("Edit Book");
        btnThemGD.setText("Submit");
        tvNameBook.setText(book.getTenSach());
        tvGiaGoc.setText(String.valueOf(book.getMoney()));
        tvGiaKhuyenmai.setText(String.valueOf(book.getMoneyKhuyenmai()));


        initSpinner();
        spLoaiSach.setSelection(mDaoKindofbook.getListNameKindOfBook().indexOf(book.getTenLoaiSach()));

        btnThemGD.setOnClickListener(view2 -> {
            String nameBook = tvNameBook.getText().toString();
            float giagoc = Float.parseFloat(tvGiaGoc.getText().toString());
            int khuyenMai = Integer.parseInt(tvGiaKhuyenmai.getText().toString());
            String tenLoai = spLoaiSach.getSelectedItem().toString();
            Book tc = new Book(book.getId(), nameBook, tenLoai, giagoc, khuyenMai, idLoaiSach);
            if (mDaoBook.editBoock(tc) == true) {
                showData();
                Toast.makeText(getActivity(), "Successful fix!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(getActivity(), "Failed fix!", Toast.LENGTH_SHORT).show();
            }
        });

        xoaTextGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClickDelete(View view, int position) {
        Book kind = mListBook.get(position);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("Are you sure you want to remove").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDaoBook.deleteTitle(kind.getId());
                mDaoBook.deleteTitle(kind.getId());
                Toast.makeText(getActivity(), "Delete Thành Công" + kind.getTenSach(), Toast.LENGTH_SHORT).show();
                showData();
                dialog.cancel();
            }
        });
    }


    @Override
    public void onAddLoanClip(View view, int position) {
        startActivity(new Intent(getActivity(), AddLoanSlipActivity.class));
    }
}