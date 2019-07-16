package rohiksingh.com.mvvmdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rohiksingh.com.mvvmdemo.ListCallBack;
import rohiksingh.com.mvvmdemo.R;
import rohiksingh.com.mvvmdemo.models.Player;
import rohiksingh.com.mvvmdemo.views.DetailActivity;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private Context context;
    private List<Player> players;
    private ListCallBack callBack;

    public PlayerListAdapter(Context context, List<Player> players){
        this.context = context;
        this.players = players;
        callBack = (ListCallBack)context;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_player, viewGroup, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i) {

        Player player = players.get(i);
        playerViewHolder.name.setText(player.getName());
        playerViewHolder.score.setText(player.getScore()+"");
        playerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.startActivity(new Intent(context, DetailActivity.class));
                callBack.click();
            }
        });

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView score;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            score = (TextView)itemView.findViewById(R.id.score);
        }
    }

}
