package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {

    String kword;
    ImageView sear;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.listRecyclerViewSearch);
        final ListAdaSearch listAdapter = new ListAdaSearch();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        sear=root.findViewById(R.id.searchbuttonimg);
        sear.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText keyword;
                keyword=root.findViewById(R.id.keyword_search);
                String a =keyword.getText().toString().trim();
             listAdapter.getFilter().filter(a);
            }
        });
        Button tr=root.findViewById(R.id.Tragedy);
        tr.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                kword="tragedy";
                listAdapter.getFilter().filter(kword);
            }
        });
        Button rom=root.findViewById(R.id.Romance);
        rom.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                kword="Romance";
                listAdapter.getFilter().filter(kword);
            }
        });
        Button det=root.findViewById(R.id.Detective);
        det.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                kword="Detective";
                listAdapter.getFilter().filter(kword);
            }
        });
        Button fant=root.findViewById(R.id.Fantasy);
        fant.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                kword="Fantasy";
                listAdapter.getFilter().filter(kword);
            }
        });
        return root;
    }
}
