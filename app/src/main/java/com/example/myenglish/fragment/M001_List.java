package com.example.myenglish.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myenglish.R;
import com.example.myenglish.activity.MainActivity;
import com.example.myenglish.adapter.WordAdapter;
import com.example.myenglish.enntity.WordEntity;

import java.util.ArrayList;
import java.util.List;

public class M001_List extends Fragment {
    private Context mContext;
    private final List<WordEntity> list;
    private WordAdapter adapter;

    public M001_List(List<WordEntity> list){
        this.list = list;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_m001_list, container, false);
        initView(rootView);
        return rootView;
    }

    public void initView(View view){
        ImageView add = view.findViewById(R.id.iv_list_add);
        add.setOnClickListener(v1 -> ((MainActivity)mContext).gotoM003());
        android.widget.SearchView searchView = view.findViewById(R.id.sv_list_search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        //Hiển thị danh sách
        RecyclerView recyclerView = view.findViewById(R.id.rv_list_word);
        this.adapter = new WordAdapter(list, mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(this.adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void filterList(String newText) {
        List<WordEntity> filteredList = new ArrayList<>();
        for(WordEntity word: list){
            if(word.getWord().contains(newText)){
                filteredList.add(word);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(mContext, "No data", Toast.LENGTH_SHORT).show();
        } else {
            this.adapter.setFilteredList(filteredList);
        }
    }
}