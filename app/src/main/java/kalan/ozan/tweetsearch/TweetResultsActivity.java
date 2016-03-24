package kalan.ozan.tweetsearch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.sa90.infiniterecyclerview.InfiniteRecyclerView;
import com.sa90.infiniterecyclerview.listener.OnLoadMoreListener;
import kalan.ozan.tweetsearch.adapters.TweetsAdapter;
import kalan.ozan.tweetsearch.api.TweetSearchApi;
import kalan.ozan.tweetsearch.api.model.MyData;
import kalan.ozan.tweetsearch.api.model.SearchMetaData;
import kalan.ozan.tweetsearch.api.model.statusObj.StatusesObj;
import kalan.ozan.tweetsearch.dialogs.SearchError;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class TweetResultsActivity extends AppCompatActivity {

    public String geo;
    public MyData myData;
    public Gson gson = new Gson();
    public int    mSize;
    public FragmentManager fragmentManager = getSupportFragmentManager();

    ArrayList<StatusesObj> sList;
    ArrayList<StatusesObj> myNewData;
    LinearLayoutManager    layoutManager;

    @Bind(R.id.tweet_recycle_view) InfiniteRecyclerView mRecycleView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_results);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.search_result_title);

        geo = getIntent().getStringExtra(getString(R.string.geo));
        myData = gson.fromJson(getIntent().getStringExtra(getString(R.string.data)), MyData.class);

        sList = myData.getStatuses();

        layoutManager = new LinearLayoutManager(this);
        TweetsAdapter mAdapter = new TweetsAdapter(sList, fragmentManager, this);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setOnLoadMoreListener(mLoadMoreListener);

    }



    private OnLoadMoreListener mLoadMoreListener = new OnLoadMoreListener() {
        @Override public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getMore(myData, geo);
                    mRecycleView.moreDataLoaded();
                    mRecycleView.setShouldLoadMore(false);
                }
            }, 5000);
        }
    };


    private void addMoreData(List<StatusesObj> myNewData) {
        int mSize = myNewData.size();
        for (int i = 0; i < mSize; i++) {
            sList.add(myNewData.get(i));
        }
        myData.statuses = sList;
    }

    public void getMore(MyData myData, String geo) {

        mSize = myData.getStatuses().size() - 1;
        String mMaxId = myData.getStatuses().get(mSize).getId();
        SearchMetaData searchMetaData = myData.getSearchMetaData();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TweetSearchApi twitterApi = restAdapter.create(TweetSearchApi.class);

        Call<MyData> call = twitterApi.getMoreData(searchMetaData.getQuery(), geo, mMaxId);

        call.enqueue(new Callback<MyData>() {

            @Override public void onResponse(Call<MyData> call, Response<MyData> response) {

                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    if (response.body().statuses.isEmpty()) {
                        error(getString(R.string.no_results));
                    } else {
                        myNewData = response.body().getStatuses();
                        myNewData.remove(0);
                        addMoreData(myNewData);
                    }

                } else {
                    //request not successful (like 400,401,403 etc)
                    error(response.errorBody().toString());
                }
            }

            @Override public void onFailure(Call<MyData> call, Throwable t) {
                error(t.getMessage());
            }
        });
    }


    public void error(String msg) {
        SearchError error = new SearchError();
        Bundle args = new Bundle();
        args.putString(getString(R.string.msg_error_key), msg);
        error.setArguments(args);
        error.show(getSupportFragmentManager(), "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
