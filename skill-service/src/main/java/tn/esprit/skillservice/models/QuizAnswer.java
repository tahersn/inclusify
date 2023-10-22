package tn.esprit.skillservice.models;


import java.util.Map;

public class QuizAnswer {

    private Map<Integer, Boolean> data;

    public QuizAnswer() {
    }

    public QuizAnswer(Map<Integer, Boolean> data) {
        this.data = data;
    }

    public Map<Integer, Boolean> getData() {
        return data;
    }

    public void setData(Map<Integer, Boolean> data) {
        this.data = data;
    }

    /*
    private int id;
    private boolean isChecked;

    public QuizAnswer() {
    }

    public QuizAnswer(int id, boolean isChecked) {
        this.id = id;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }*/
}
