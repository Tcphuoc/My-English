package com.example.myenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myenglish.R;
import com.example.myenglish.enntity.WordEntity;

import java.util.List;

public class DetailAdapter extends PagerAdapter {
    private final Context mContext;
    private final List<WordEntity> list;

    public DetailAdapter(Context context, List<WordEntity> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_word, container, false);
        container.addView(view);

        TextView word = view.findViewById(R.id.tv_detail_word);
        TextView type = view.findViewById(R.id.tv_detail_type);
        TextView define = view.findViewById(R.id.tv_detail_define);
        TextView synonym = view.findViewById(R.id.tv_detail_synonym);
        ImageView fav = view.findViewById(R.id.iv_fav);
        ImageView unfav = view.findViewById(R.id.iv_unfav);

        word.setText(list.get(position).getWord());
        type.setText(list.get(position).getType());
        define.setText(list.get(position).getDefine());
        synonym.setText(list.get(position).getSynonym());

        if(!list.get(position).isFav()){
            fav.setVisibility(View.VISIBLE);
            unfav.setVisibility(View.GONE);
        } else {
            fav.setVisibility(View.GONE);
            unfav.setVisibility(View.VISIBLE);
        }

        fav.setOnClickListener(v -> {
            fav.setVisibility(View.GONE);
            unfav.setVisibility(View.VISIBLE);
            list.get(position).setFav(true);
        });

        unfav.setOnClickListener(v -> {
            fav.setVisibility(View.VISIBLE);
            unfav.setVisibility(View.GONE);
            list.get(position).setFav(false);
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
