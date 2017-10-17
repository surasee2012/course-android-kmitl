package kmitl.lab07.surasee2012.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kmitl.lab07.surasee2012.mylazyinstagram.R;
import kmitl.lab07.surasee2012.mylazyinstagram.api.UserProfile;

/**
 * Created by Gun on 10/6/2017.
 */

class Holder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView textLike;
    public TextView textComment;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        textLike = itemView.findViewById(R.id.textLike);
        textComment = itemView.findViewById(R.id.textComment);
    }

}

public class PostAdapter extends RecyclerView.Adapter<Holder> {

    ArrayList<String> data;
    ArrayList<String> data_like;
    ArrayList<String> data_comment;

    Context context;

    String type_show;

    public PostAdapter(Context context, UserProfile userProfile) {
        this.context = context;
        data = new ArrayList<>();
        data_like = new ArrayList<>();
        data_comment = new ArrayList<>();
        for (int i=0;i<userProfile.getPosts().length;i++) {
            data.add(userProfile.getPosts()[i].getUrl());
            data_like.add(userProfile.getPosts()[i].getLike());
            data_comment.add(userProfile.getPosts()[i].getComment());
        }
    }

    public String getType_show() {
        return type_show;
    }

    public void setType_show(String type_show) {
        this.type_show = type_show;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(getType_show().equals("grid")){
            View ItemView = inflater.inflate(R.layout.post_item, null, false);
            Holder holder = new Holder(ItemView);
            return holder;
        }
        else{
            View ItemView = inflater.inflate(R.layout.list_item, null, false);
            Holder holder = new Holder(ItemView);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data.get(position)).into(image);
        if(getType_show().equals("list")){
            holder.textComment.setText(data_comment.get(position));
            holder.textLike.setText(data_like.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
