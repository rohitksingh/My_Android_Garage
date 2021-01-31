package com.rohitksingh.rxjavademo.flatmap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rohitksingh.rxjavademo.R;
import com.rohitksingh.rxjavademo.flatmap.models.Post;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PostViewHolder> {

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.description.setText(post.getBody());
        holder.numComments.setText(post.getComments().size());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
    }

    public void updatePost(Post post){
        posts.set(posts.indexOf(post), post);
        notifyItemChanged(posts.indexOf(post), post);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView description, numComments, progressBar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.post_desc);
            numComments = itemView.findViewById(R.id.numComments);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }
}
