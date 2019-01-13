package com.hub.gui.wag.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hub.gui.wag.R;
import com.hub.gui.wag.model.BadgeCount;
import com.hub.gui.wag.model.User;
import com.hub.gui.wag.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mUserList ;

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextName;
        public TextView mTextReputation;
        public TextView mTextGold;
        public TextView mTextSilver;
        public TextView mTextBronze;
        public ImageView mImageGravatar;
        public ProgressBar mProgressBar;
        public Target mGravatarTarget;

        public UserViewHolder(View v) {
            super(v);
            mTextName = v.findViewById(R.id.txt_name);
            mTextReputation = v.findViewById(R.id.txt_reputation);
            mTextGold = v.findViewById(R.id.txt_badge_gold);
            mTextSilver = v.findViewById(R.id.txt_badge_silver);
            mTextBronze = v.findViewById(R.id.txt_badge_bronze);
            mImageGravatar = v.findViewById(R.id.img_gravatar);
            mProgressBar = v.findViewById(R.id.progressBar);

            mGravatarTarget = new Target(){
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    mProgressBar.setVisibility(View.GONE);
                    mImageGravatar.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    mProgressBar.setVisibility(View.GONE);
                    mImageGravatar.setImageResource(R.drawable.ic_person_black_24dp);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mImageGravatar.setImageResource(R.drawable.ic_person_black_24dp);
                }
            };

        }

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);

        UserViewHolder viewHolder = new UserViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUserList.get(position);

        if(user == null)
            return;

        Picasso.with(holder.itemView.getContext())
                .load(user.getProfileImage())
                .into(holder.mGravatarTarget);

        holder.mTextName.setText(user.getDisplayName());

        holder.mTextReputation.setText(Utils.formatNumberWithComma(user.getReputation()));

        BadgeCount badges = user.getBadgeCounts();

        if(badges != null) {
            holder.mTextGold.setText(Utils.formatNumberWithComma(badges.getGold()));
            holder.mTextSilver.setText(Utils.formatNumberWithComma(badges.getSilver()));
            holder.mTextBronze.setText(Utils.formatNumberWithComma(badges.getBronze()));
        }else{
            holder.mTextGold.setText("");
            holder.mTextSilver.setText("");
            holder.mTextBronze.setText("");
        }
    }



    @Override
    public int getItemCount() {
        if(mUserList == null)
            return 0;

        return mUserList.size();
    }

    public void setUserList(List<User> newList){
        mUserList = newList;
        Timber.i("list? %s",newList);
        notifyDataSetChanged();
    }

    public List<User> getUserList(){
        return  mUserList;
    }
}
