package rohksin.com.photohider;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Illuminati on 2/12/2018.
 */

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkHolder> {


    private List<PhotoData> savedLinks;
    private Context context;

    public LinkAdapter(Context context, List<PhotoData> savedLinks)
    {
        this.context = context;
        this.savedLinks = savedLinks;
    }


    @Override
    public LinkHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.saved_link_item,parent,false);
        return new LinkHolder(view);
    }

    @Override
    public void onBindViewHolder(LinkHolder holder, int position) {

       final PhotoData data = savedLinks.get(position);
        holder.linkText.setText(data.getPath());
        holder.linkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtility.openLinkInBrowser(context, data.getPath());
            }
        });

    }

    @Override
    public int getItemCount() {
        return savedLinks.size();
    }

    public class LinkHolder extends RecyclerView.ViewHolder{

        TextView linkText;

        public LinkHolder(View itemView) {
            super(itemView);

            linkText = (TextView)itemView.findViewById(R.id.link);
        }
    }

}
