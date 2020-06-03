package com.rohitksingh.recyclerviewpagination;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {

    private Context context;
    private List<Player> playerList;

    public PlayerAdapter(Context context, List<Player> playerList){
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder{

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
