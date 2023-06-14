package com.example.myenglish.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.myenglish.R;
import com.example.myenglish.enntity.WordEntity;
import com.example.myenglish.fragment.M001_Entry;
import com.example.myenglish.fragment.M002_Detail;
import com.example.myenglish.fragment.M003_Add;
import com.example.myenglish.fragment.M004_Edit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String fileName = "words.txt";
    private final List<WordEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        checkPermission();

        //Tạo hoặc mở file lưu trữ
        File file = new File(getExternalFilesDir(null), fileName);
        if(file.exists()){
            readFile();
        } else {
            createFile();
        }

        gotoM001();
    }

    @Override
    protected void onStop() {
        super.onStop();
        writeFile();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 101){
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)){
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> finish());
        builder.setNegativeButton("No", (dialog, which) -> {});
        Dialog dialog = builder.create();
        dialog.show();
    }

    public void createFile(){
        try {
            FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(null), fileName));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFile(){
        try {
            FileInputStream fis = new FileInputStream(new File(getExternalFilesDir(null), fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String string = br.readLine();
            while (string != null){
                String[] data = string.split("/");
                String[] edit = new String[data.length];

                for (int i = 0; i < data.length; i++) {
                    edit[i] = data[i].substring(data[i].indexOf(":")+1);
                }

                if (edit[6].equals("true")){
                    list.add(new WordEntity(edit[0], edit[1], edit[2], edit[3], edit[4], edit[5], true));
                } else if (edit[6].equals("false")){
                    list.add(new WordEntity(edit[0], edit[1], edit[2], edit[3], edit[4], edit[5], false));
                }

                string = br.readLine();
            }
            br.close();
            inputStreamReader.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.i("readFile", "it's work");
    }

    public void writeFile(){
        try {
            FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(null), fileName));
            for (WordEntity word: list){
                String data = "word:"+word.getWord()+"/type:"+word.getType()+"/synonym:"+word.getSynonym()+
                        "/define:"+word.getDefine()+"/ori:"+word.getOriSen()+"/my:"+word.getMySen()+"/fav:"+ word.isFav() +"\n";
                fos.write(data.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
    }

    public void addWord(WordEntity word){
        this.list.add(word);
    }

    public void removeWord(WordEntity word){
        list.remove(word);
        gotoM001();
    }

    public void showFrg(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, fragment, null).commit();
    }

    public void gotoM001(){
        showFrg(new M001_Entry(list));
    }

    public void gotoM002(WordEntity word, List<WordEntity> list){
        showFrg(new M002_Detail(word, list));
    }
    public void gotoM003(){
        showFrg(new M003_Add());
    }

    public void gotoM004(WordEntity word){
        showFrg(new M004_Edit(word, this.list));
    }
}