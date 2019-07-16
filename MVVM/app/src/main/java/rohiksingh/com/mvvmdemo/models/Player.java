package rohiksingh.com.mvvmdemo.models;

import java.util.Random;

public class Player {

    private String name;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(){

    }

    public Player(String name){
        this.name = name;
        Random r = new Random();
        this.score = r.nextInt(10);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
