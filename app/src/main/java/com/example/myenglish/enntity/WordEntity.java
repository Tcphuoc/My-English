package com.example.myenglish.enntity;

public class WordEntity {
    private String word;
    private String type;
    private String define;
    private String synonym;
    private String oriSen;
    private String mySen;
    private boolean fav;

    public WordEntity(String word, String type, String define, String synonym, String oriSen, String mySen, boolean fav) {
        this.word = word;
        this.type = type;
        this.define = define;
        this.synonym = synonym;
        this.oriSen = oriSen;
        this.mySen = mySen;
        this.fav = fav;
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public String getDefine() {
        return define;
    }

    public String getSynonym() {
        return synonym;
    }

    public String getOriSen() {
        return oriSen;
    }

    public String getMySen() {
        return mySen;
    }

    public boolean isFav() {
        return fav;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public void setOriSen(String oriSen) {
        this.oriSen = oriSen;
    }

    public void setMySen(String mySen) {
        this.mySen = mySen;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
