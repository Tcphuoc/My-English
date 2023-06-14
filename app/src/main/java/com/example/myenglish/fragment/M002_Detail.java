package com.example.myenglish.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myenglish.R;
import com.example.myenglish.activity.MainActivity;
import com.example.myenglish.adapter.DetailAdapter;
import com.example.myenglish.enntity.WordEntity;

import java.util.List;


public class M002_Detail extends Fragment {
    private Context mContext;
    private final WordEntity wordEntity;
    private final List<WordEntity> list;

    public M002_Detail(WordEntity wordEntity, List<WordEntity> list) {
        this.wordEntity = wordEntity;
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
        View rootView = inflater.inflate(R.layout.fragment_m002_detail, container, false);
        initView(rootView);
        return rootView;
    }

    public void initView(View v){
        ImageView back = v.findViewById(R.id.iv_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(v1 -> ((MainActivity)mContext).gotoM001());
        TextView title = v.findViewById(R.id.tv_title);
        title.setText(R.string.app_name);

        ImageView edit = v.findViewById(R.id.iv_edit);
        edit.setVisibility(View.VISIBLE);
        edit.setOnClickListener(v1 -> ((MainActivity)mContext).gotoM004(wordEntity));

        ImageView delete = v.findViewById(R.id.iv_delete);
        delete.setOnClickListener(v1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Are you sure to delete this word?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((MainActivity)mContext).removeWord(wordEntity);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });
            Dialog dialog = builder.create();
            dialog.show();
        });

        DetailAdapter adapter = new DetailAdapter(mContext, list);
        ViewPager viewPager = v.findViewById(R.id.vp_detail);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(list.indexOf(wordEntity));
    }
}