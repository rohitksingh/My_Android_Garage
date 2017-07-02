package com.omdb.rohksin.transitionapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Illuminati on 7/1/2017.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.PersonHolder> {

    private List<String> names;
    private Context context;

    public ListAdapter(List<String> names, Context context)
    {
        this.names = names;
        this.context = context;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
        PersonHolder holder = new PersonHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {

        holder.name.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public PersonHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
