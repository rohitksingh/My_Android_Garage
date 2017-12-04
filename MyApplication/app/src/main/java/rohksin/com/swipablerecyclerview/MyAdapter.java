package rohksin.com.swipablerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Illuminati on 11/18/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    private Context context;
    private List<Model> list;

    public  MyAdapter(Context context, List<Model> list)
    {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Model model = list.get(position);
        holder.textView.setText(model.getName());



    }

    public void removeItem(int position) {
        list.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public RelativeLayout foregroundView;
        public RelativeLayout backgroundView;
        public ImageView delete;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.text);
            foregroundView = (RelativeLayout)itemView.findViewById(R.id.foreGroundView);
            backgroundView = (RelativeLayout)itemView.findViewById(R.id.backgroundView);
            delete = (ImageView)itemView.findViewById(R.id.deleteItem);

            backgroundView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Delete Back",Toast.LENGTH_LONG).show();
                }
            });

            foregroundView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Foreground Back",Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}
