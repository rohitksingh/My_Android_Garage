package rohiksingh.com.recyclerviewdemo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rohiksingh.com.recyclerviewdemo.Callbacks.ListCallback;
import rohiksingh.com.recyclerviewdemo.Models.Person;
import rohiksingh.com.recyclerviewdemo.R;

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder> {

    private Context context;
    private List<Person> people;
    private ListCallback callback;

    public PeopleListAdapter(Context context, List<Person> people){
        this.context =context;
        this.people = people;
        callback = (ListCallback)context;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_people_item, viewGroup, false);
        return new PeopleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder peopleViewHolder, int i) {

        final Person person = people.get(i);
        peopleViewHolder.name.setText(person.getName());
        peopleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.click(person);
            }
        });

    }

    @Override
    public int getItemCount() {
        return people.size();
    }


    public class PeopleViewHolder extends RecyclerView.ViewHolder{

        public TextView name;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

    public void deleteItem(int position) {
        Toast.makeText(context, "Items remained"+ people.size(), Toast.LENGTH_LONG).show();
        people.remove(position);
        notifyItemRemoved(position);
    }
}
