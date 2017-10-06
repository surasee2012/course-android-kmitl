package kmitl.lab07.surasee2012.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import kmitl.lab07.surasee2012.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.surasee2012.mylazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.surasee2012.mylazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("cartoon");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.list);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(String usrName) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(LazyInstagramApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();

                    TextView textUser = findViewById(R.id.textUser);
                    TextView textPost = findViewById(R.id.textPost);
                    TextView textFollowing = findViewById(R.id.textFollowing);
                    TextView textFollower = findViewById(R.id.textFollower);
                    TextView textBio = findViewById(R.id.textBio);
                    ImageView imageProfile = findViewById(R.id.imageProfile);

                    textUser.setText("@"+userProfile.getUser());
                    textPost.setText("Post\n"+userProfile.getPost());
                    textFollowing.setText("Following\n"+userProfile.getFollowing());
                    textFollower.setText("Follower\n"+userProfile.getFollower());
                    textBio.setText(userProfile.getBio());
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

}
