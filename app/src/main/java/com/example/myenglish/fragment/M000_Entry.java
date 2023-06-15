package com.example.myenglish.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myenglish.R;
import com.example.myenglish.enntity.WordEntity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class M000_Entry extends Fragment {
    private final List<WordEntity> list;

    public M000_Entry(List<WordEntity> list){
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_m000_entry, container, false);
        initView(rootView);
        return rootView;
    }

    public void initView(View view){
        getChildFragmentManager().beginTransaction().replace(R.id.ln_sub, new M001_List(list), null).commit();

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home));
        tabLayout.addTab(tabLayout.newTab().setText("Practice").setIcon(R.drawable.ic_book));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.ln_sub, new M001_List(list), null).commit();
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.ln_sub, new M005_Practice(), null).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}