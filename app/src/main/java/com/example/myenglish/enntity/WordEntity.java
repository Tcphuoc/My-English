package com.example.myenglish.enntity;

public class WordEntity {
    private String word;
    private String type;
    private String define;
    private String synonym;
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

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
