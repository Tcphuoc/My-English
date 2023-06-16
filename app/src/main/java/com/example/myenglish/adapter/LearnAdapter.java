package com.example.myenglish.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myenglish.R;
import com.example.myenglish.enntity.WordEntity;

import java.util.ArrayList;
import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.ViewHolder> {
    private final List<WordEntity> list;
    private final boolean[] results;
    private final Context mContext;

    public LearnAdapter (List<WordEntity> list, Context context){
        this.list = list;
        this.mContext = context;
        this.results = new boolean[list.size()];
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        EditText editText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_learn_word);
            editText = itemView.findViewById(R.id.edt_learn_enter);
        }
    }

    @NonNull
    @Override
    public LearnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_learn_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnAdapter.ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getWord());
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                results[position] = holder.editText.getText().toString().equals(list.get(position).getDefine());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public boolean[] getResults(){
        return results;
    }
}
