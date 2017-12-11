package rohksin.com.sqlitedatabasedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Illuminati on 12/4/2017.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private Context context;
    List<People> people;

    public PeopleAdapter(Context context, List<People> people)
    {
        this.context = context;
        this.people = people;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_item,parent,false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        People person = people.get(position);
        holder.fname.setText(person.getFname());
        holder.lname.setText(person.getLname());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder{

        private TextView fname;
        private TextView lname;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            fname= (TextView)itemView.findViewById(R.id.fname);
            lname = (TextView)itemView.findViewById(R.id.lname);
        }
    }
}
