package kalan.ozan.tweetsearch.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import kalan.ozan.tweetsearch.R;
import kalan.ozan.tweetsearch.api.model.statusObj.StatusesObj;
import kalan.ozan.tweetsearch.dialogs.ShowTweet;

public class TweetsAdapter extends com.sa90.infiniterecyclerview.InfiniteAdapter {

    private Activity               mContext;
    private ArrayList<StatusesObj> statues;

    public ShowTweet       showTweet;
    public FragmentManager fragManager;
    public String          mPostTime;

    public TweetsAdapter(ArrayList<StatusesObj> data, FragmentManager fragmentManager, Activity parent) {
        mContext = parent;
        statues = data;
        fragManager = fragmentManager;
    }

    public String time(Date now, Date past) {
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
        long mins = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
        long secs = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());


        if (hours < 24 && hours >= 1) {
            mPostTime = String.valueOf(hours) + mContext.getString(R.string.hrs);
            return mPostTime;
        } else if (secs < 60) {
            mPostTime = String.valueOf(secs) + mContext.getString(R.string.sec);
            return mPostTime;
        } else if (mins >= 1 && mins < 60) {
            mPostTime = String.valueOf(mins) + mContext.getString(R.string.mins);
            return mPostTime;
        } else if (days >= 1 && days < 7) {
            mPostTime = String.valueOf(days) + mContext.getString(R.string.days);
            return mPostTime;
        } else {
            long week = days % 7;
            mPostTime = String.valueOf(week) + mContext.getString(R.string.weeks);
        }
        return mPostTime;
    }


    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof VL) {
            VL mLoader = ((VL) holder);


        } else {
            VH mHolder = ((VH) holder);
            StatusesObj mStatus = statues.get(position);

            mHolder.mRplyBtn.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    showTweet = new ShowTweet();

                    Bundle args = new Bundle();
                    args.putString(mContext.getString(R.string.twt_id), mStatus.getIdStr());
                    args.putString(mContext.getString(R.string.twt_txt), mStatus.getTweetText());
                    args.putString(mContext.getString(R.string.user_id), mStatus.getTweetUser().getIdStr());

                    showTweet.setArguments(args);
                    showTweet.show(fragManager, "");
                }
            });

            mHolder.mText.setText(statues.get(position).getTweetText());

            mHolder.mName.setText(mStatus.getTweetUser()
                                          .getScreenName());

            Picasso.with(mContext).load(mStatus.getTweetUser()
                                                .getProfileImageHttpsUrl())
                    .into(mHolder.mAvatar);

            if (statues.get(position).getPlace() != null) {
                mHolder.mOrgin.setText(statues.get(position).getPlace().getFullName());
            } else {
                mHolder.mOrgin.setVisibility(View.INVISIBLE);
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat(mContext.getString(R.string.date_time_format));
                Date past = format.parse(statues.get(position).timeStamp);
                Date now = new Date();
                now = format.parse(now.toString());

                mHolder.mTime.setText(time(now, past));
            } catch (Exception j) {
                j.printStackTrace();
            }

            if (statues.get(position).getEntitites().getTweetMedia() != null) {
                Picasso.with(mContext).load(statues.get(position)
                                                    .getEntitites()
                                                    .getTweetMedia().get(0)
                                                    .getMediaUrlHttps()
                ).into(mHolder.mImg);
            } else {mHolder.mImg.setVisibility(View.GONE);}
        }


    }

    @Override public RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.progress, parent, false);
        return new VL(v);
    }

    @Override public int getCount() {
        return statues.size();
    }

    @Override public int getViewType(int position) {
        return 1;
    }

    @Override public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.tweet_item, parent, false);
        return new VH(v);
    }


    public class VL extends RecyclerView.ViewHolder {

        @Bind(R.id.progress) ProgressBar progressBar;

        public VL(View load) {
            super(load);
            ButterKnife.bind(this, load);
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        @Bind(R.id.reply_btn)      Button    mRplyBtn;
        @Bind(R.id.tweet_name)     TextView  mName;
        @Bind(R.id.tweet_avatar)   ImageView mAvatar;
        @Bind(R.id.tweet_img)      ImageView mImg;
        @Bind(R.id.tweet_time)     TextView  mTime;
        @Bind(R.id.tweet_text)     TextView  mText;
        @Bind(R.id.tweet_loaction) TextView  mOrgin;

        public VH(View summary) {
            super(summary);
            ButterKnife.bind(this, summary);
        }
    }
}
