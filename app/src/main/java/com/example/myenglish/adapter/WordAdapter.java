package com.example.myenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myenglish.R;
import com.example.myenglish.activity.MainActivity;
import com.example.myenglish.enntity.WordEntity;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<WordEntity> list;
    private final Context mContext;

    public WordAdapter (List<WordEntity> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView word;
        private final TextView define;
        private final ImageView fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.word = itemView.findViewById(R.id.tv_word);
            this.define = itemView.findViewById(R.id.tv_define);
            this.fav = itemView.findViewById(R.id.iv_fav);
            itemView.setOnClickListener(v -> ((MainActivity)mContext).gotoM002((WordEntity) word.getTag(), list));
        }
    }

    public void setFilteredList(List<WordEntity> wordEntities){
        this.list = wordEntities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.ViewHolder holder, int position) {
        WordEntity word = list.get(position);
        holder.word.setText(word.getWord());
        holder.define.setText(word.getDefine());
        holder.word.setTag(word);
        if(word.isFav()){
            holder.fav.setVisibility(View.VISIBLE);
        } else {
            holder.fav.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
