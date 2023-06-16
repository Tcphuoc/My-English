package com.example.myenglish.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myenglish.R;
import com.example.myenglish.activity.MainActivity;
import com.example.myenglish.adapter.LearnAdapter;
import com.example.myenglish.enntity.WordEntity;

import java.util.List;

public class M005_Learn extends Fragment {
    private final List<WordEntity> list;
    private Context mContext;

    public M005_Learn(List<WordEntity> list){
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
        View rootView = inflater.inflate(R.layout.fragment_m005_learn, container, false);
        initView(rootView);
        return rootView;
    }

    public void initView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.rv_learn_words);
        LearnAdapter learnAdapter = new LearnAdapter(list, mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(learnAdapter);

        Button button = view.findViewById(R.id.bt_learn_submit);
        button.setOnClickListener(v -> {
            int numberTrue = 0;
            boolean[] results = learnAdapter.getResults();
            for (boolean result : results) {
                if(result) numberTrue++;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Result: " + numberTrue + "/" + results.length);
            builder.setPositiveButton("OK", (dialog, which) -> ((MainActivity)mContext).gotoM000());
            Dialog dialog = builder.create();
            dialog.show();
        });
    }
}