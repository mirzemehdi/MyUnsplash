package com.mmk.unsplashapp.ui.fragments.picturelist;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.pojo.PicturePOJO;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PictureItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private Context mContext;
        private PictureItemClickListener pictureItemClickListener;
        private ShimmerFrameLayout shimmerFrameLayout;

        public PictureItemViewHolder(@NonNull View itemView, Context context, PictureItemClickListener pictureItemClickListener) {
            super(itemView);
            mContext=context;
            this.pictureItemClickListener = pictureItemClickListener;
            imageView =itemView.findViewById(R.id.image_item);
            shimmerFrameLayout=itemView.findViewById(R.id.shimmer_frame_item);

        }

        public void onBind(final PicturePOJO picture){
            shimmerFrameLayout.showShimmer(true);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pictureItemClickListener !=null) pictureItemClickListener.onClick(picture);

                }
            });

            Picasso.with(mContext)
                    .load(picture.getSmallUrl())
                    .placeholder(R.drawable.drawable_image_place_holder)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            shimmerFrameLayout.hideShimmer();

                        }

                        @Override
                        public void onError() {
                            shimmerFrameLayout.hideShimmer();
                        }
                    });

        }



    }