package rohiksingh.com.mvvmdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import rohiksingh.com.mvvmdemo.models.Player;

public class PlayersRepository {

    private static PlayersRepository instance;

    private MutableLiveData<List<Player>> players;


    public static PlayersRepository getInstance(){

        if(instance == null){
            instance = new PlayersRepository();
        }
        return instance;
    }



    public MutableLiveData<List<Player>> getPlayers(){
        setPlayers();
        return players;
    }


    private void setPlayers(){

        players = new MutableLiveData<List<Player>>();

        List<Player> dataSet = new ArrayList<Player>();

        Player rohit = new Player("Rohit Sharma");
        Player kohli = new Player("Virat Kohli");
        Player dhawan = new Player("Shikhar Dhawan");
        Player jadhav = new Player("Kedar Jadhav");
        Player pandya = new Player("Hardik Pandya");
        Player dhoni = new Player("M.S Dhoni");
        Player pant = new Player("Rishabh Pant");
        Player dk = new Player("Dinesh Karthik");
        Player bhuvi = new Player("Buvi Kumar");
        Player shami = new Player("MO Shami");
        Player kuldeep = new Player("Kuldeep Yadav");
        Player chahal = new Player("Yujevendra Chahal");
        Player jadeja = new Player("Sir Ravindra Jadega");

        dataSet.add(rohit);
        dataSet.add(kohli);
        dataSet.add(dhawan);
        dataSet.add(jadeja);
        dataSet.add(jadhav);
        dataSet.add(pandya);
        dataSet.add(dhoni);
        dataSet.add(pant);
        dataSet.add(dk);
        dataSet.add(bhuvi);
        dataSet.add(shami);
        dataSet.add(kuldeep);
        dataSet.add(chahal);

        players.setValue(dataSet);

    }

}
