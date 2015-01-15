package com.sgv.geoquiz.activity;

public class TrueFalse {
    
    public TrueFalse(int question, boolean trueQuestion) {
        super();
        this.question = question;
        this.trueQuestion = trueQuestion;
    }
    public int getQuestion() {
        return question;
    }
    public void setQuestion(int question) {
        this.question = question;
    }
    public boolean isTrueQuestion() {
        return trueQuestion;
    }
    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;
    }
    private int question;
    private boolean trueQuestion;
  
    
}
