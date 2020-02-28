package rohiksingh.com.recyclerviewdemo.Callbacks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import rohiksingh.com.recyclerviewdemo.Adapters.PeopleListAdapter;

public class SwipeListener extends ItemTouchHelper.SimpleCallback {

    private PeopleListAdapter adapter;
    private Context context;

    public SwipeListener(Context context, PeopleListAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Toast.makeText(context,"Item "+i+" Swiped", Toast.LENGTH_SHORT).show();
        adapter.deleteItem(viewHolder.getAdapterPosition());
    }
}
