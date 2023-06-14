package com.example.myenglish.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myenglish.R;
import com.example.myenglish.activity.MainActivity;
import com.example.myenglish.enntity.WordEntity;

public class M003_Add extends Fragment implements View.OnClickListener{
    private Context mContext;
    private EditText word, synonym, define, origional, my;
    private String type;
    private String[] types;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        types = context.getResources().getStringArray(R.array.word_type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_m003_add, container, false);
        initView(rootView);
        return rootView;
    }

    public void initView (View view){
        word = view.findViewById(R.id.edt_word);
        Spinner spinner = view.findViewById(R.id.spinner);
        define = view.findViewById(R.id.edt_define);
        synonym = view.findViewById(R.id.edt_synonym);
        origional = view.findViewById(R.id.edt_ori);
        my = view.findViewById(R.id.edt_my);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = types[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button ok = view.findViewById(R.id.bt_ok);
        Button cancel = view.findViewById(R.id.bt_cancel);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

        ImageView back = view.findViewById(R.id.iv_back);
        back.setVisibility(View.GONE);
        TextView title = view.findViewById(R.id.tv_title);
        title.setText(R.string.add);
        ImageView edit = view.findViewById(R.id.iv_delete);
        edit.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_ok){
            String word = this.word.getText().toString();
            String define = this.define.getText().toString();
            String synonym = this.synonym.getText().toString();
            String origional = this.origional.getText().toString();
            String my = this.my.getText().toString();
            if((word.equals("") || this.type == null || define.equals("") || synonym.equals("") || origional.equals("") || my.equals(""))){
                Toast.makeText(mContext, "Please no empty value", Toast.LENGTH_SHORT).show();
                return;
            }
            WordEntity wordEntity = new WordEntity(word, type, define, synonym, origional, my, false);
            ((MainActivity)mContext).addWord(wordEntity);
        }
        ((MainActivity)mContext).gotoM001();
    }
}