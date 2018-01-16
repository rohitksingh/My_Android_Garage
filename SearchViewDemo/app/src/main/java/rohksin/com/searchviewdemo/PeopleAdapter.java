package rohksin.com.searchviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 1/16/2018.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private Context context;
    private List<String> list;
    private List<String> copyList;

    public PeopleAdapter(Context context, List<String> list)
    {
        this.context = context;
        this.list = list;
        copyList = new ArrayList<String>();
        copyList.addAll(list);
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_list_item,parent,false);
        return new PeopleViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        String name = list.get(position);
        holder.name.setText(name);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    //***************************************************************************//
    //          FILETER LOGIC
    //***************************************************************************//

    public void filter(String queryText)
    {
        list.clear();

        if(queryText.isEmpty())
        {
            list.addAll(copyList);
        }
        else
        {

            for(String name: copyList)
            {
                if(name.toLowerCase().contains(queryText.toLowerCase()))
                {
                    list.add(name);
                }
            }

        }

        notifyDataSetChanged();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder{

        public TextView name;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
