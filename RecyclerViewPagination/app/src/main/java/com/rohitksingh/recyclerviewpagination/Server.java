package com.rohitksingh.recyclerviewpagination;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public List<Player> getPlayersFromPage(int pageNum){

        List<Player> playerList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Player player = new Player();
            player.setName("Player "+(i*pageNum));
            playerList.add(player);
        }

        return playerList;
    }

}
