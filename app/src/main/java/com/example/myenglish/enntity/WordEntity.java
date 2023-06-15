package com.example.myenglish.enntity;

public class WordEntity {
    private final String word;
    private final String type;
    private final String define;
    private final String synonym;
    private boolean fav;

    public WordEntity(String word, String type, String define, String synonym, boolean fav) {
        this.word = word;
        this.type = type;
        this.define = define;
        this.synonym = synonym;
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

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
