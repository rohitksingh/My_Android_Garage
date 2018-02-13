package rohksin.com.photohider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Illuminati on 2/11/2018.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private Context context;
    private List<PhotoData> list;

    public PhotoAdapter(Context context, List<PhotoData> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.test_list_item,parent,false);
        return new PhotoHolder(view);

    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {

        PhotoData data = list.get(position);
        //holder.image.setImageResource(android.R.drawable.btn_star);
        Log.d("ImageName",data.getPath());
        Picasso.with(context)
                .load(data.getPath())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class PhotoHolder extends RecyclerView.ViewHolder{

        ImageView image;



        public PhotoHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.gallertImage);
        }
    }
}
