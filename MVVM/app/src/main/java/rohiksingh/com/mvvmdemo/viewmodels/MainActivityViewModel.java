package rohiksingh.com.mvvmdemo.viewmodels;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import rohiksingh.com.mvvmdemo.models.Player;
import rohiksingh.com.mvvmdemo.repositories.PlayersRepository;

public class MainActivityViewModel extends ViewModel {


    private MutableLiveData<List<Player>> players;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    private PlayersRepository playersRepository;

    public void init(){

        playersRepository = PlayersRepository.getInstance();
        players = playersRepository.getPlayers();
    }

    public LiveData<List<Player>> getPlayers(){
        return players;
    }


    // What is setValues, getValues, postValues
    public void addPlayer(Player player){
        List<Player> dataSet = players.getValue();
        dataSet.add(player);
        players.postValue(dataSet);
    }

    public void updateScore(){
        List<Player> dataSet = players.getValue();
        for(Player player: dataSet){
            int score = player.getScore();
            score = score+ 1000;
            player.setScore(score);
        }
        players.postValue(dataSet);
    }

}
