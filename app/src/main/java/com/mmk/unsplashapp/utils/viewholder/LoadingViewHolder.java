package com.mmk.unsplashapp.utils.viewholder;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mmk.unsplashapp.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder{

    private ProgressBar mProgress;
    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        mProgress=itemView.findViewById(R.id.item_progress);

    }

    public void onBind(){
        mProgress.setIndeterminate(true);
    }
}
