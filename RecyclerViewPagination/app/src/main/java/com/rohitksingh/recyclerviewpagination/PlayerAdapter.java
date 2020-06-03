package com.rohitksingh.recyclerviewpagination;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {

    private Context context;
    private List<Player> playerList;
    private PaginationCallback callback;

    public PlayerAdapter(Context context, List<Player> playerList){
        this.context = context;
        this.playerList = playerList;
        callback = (PaginationCallback)context;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Player player = playerList.get(position);
        holder.playerName.setText(player.getName());

        if(position+1 == playerList.size()){
            callback.callNextPage();
        }
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void updateData(List<Player> newList){

        if(newList!=null){
            playerList = newList;
        }

        notifyDataSetChanged();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder{

        private TextView playerName;

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
        }
    }

}
