package kmitl.lab07.surasee2012.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kmitl.lab07.surasee2012.mylazyinstagram.MainActivity;
import kmitl.lab07.surasee2012.mylazyinstagram.R;
import kmitl.lab07.surasee2012.mylazyinstagram.api.UserProfile;

/**
 * Created by Gun on 10/6/2017.
 */

class Holder extends RecyclerView.ViewHolder {

    public ImageView image;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }

}

public class PostAdapter extends RecyclerView.Adapter<Holder> {

//    String[] data = {"http://api.learn2crack.com/android/images/eclair.png"
//            , "http://api.learn2crack.com/android/images/ginger.png"
//            , "http://api.learn2crack.com/android/images/icecream.png"
//            , "http://api.learn2crack.com/android/images/kitkat.png"
//            , "http://api.learn2crack.com/android/images/marshmallow.png"
//    };

    ArrayList<String> data;

    Context context;

    public PostAdapter(Context context) {
        this.context = context;
        UserProfile userProfile = response.body();
        data = new ArrayList<>();
        data.add(userProfile.getPosts()[0].getUrl());
//        for (int i=0;i<3;i++) {
//            data.add(userProfile.getPosts()[i].getUrl());
//        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data.get(position)).into(image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void changeUser(String profile) {
        if (profile.equals("android")) {

        } else if (profile.equals("nature")) {

        } else if (profile.equals("cartoon")) {

        }
    }

}
