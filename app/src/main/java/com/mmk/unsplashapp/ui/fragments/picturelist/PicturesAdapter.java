package com.mmk.unsplashapp.ui.fragments.picturelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.pojo.PicturePOJO;
import com.mmk.unsplashapp.utils.viewholder.LoadingViewHolder;


import java.util.ArrayList;
import java.util.List;

public class PicturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<PicturePOJO> pictureList;
    private PictureItemClickListener pictureItemClickListener;

    private final static int VIEW_TYPE_ITEM=0;
    private final static int VIEW_TYPE_LOADING=1;

    public PicturesAdapter() {
        pictureList =new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ITEM){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_picture,parent,false);
            return new PictureItemViewHolder(view,parent.getContext(), pictureItemClickListener);
        }
        else if (viewType==VIEW_TYPE_LOADING){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder=(LoadingViewHolder)holder;
            StaggeredGridLayoutManager.LayoutParams params=(StaggeredGridLayoutManager.LayoutParams) loadingViewHolder.itemView.getLayoutParams();
            params.setFullSpan(true);
            loadingViewHolder.onBind();

        }
        else if (holder instanceof PictureItemViewHolder){
            PictureItemViewHolder pictureItemViewHolder =(PictureItemViewHolder)holder;

            PicturePOJO picture= pictureList.get(position);
            pictureItemViewHolder.onBind(picture);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return pictureList.get(position)==null? VIEW_TYPE_LOADING: VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public void addPictureList(List<PicturePOJO> pictureList) {
        setLoading(false);
        int startPos=this.pictureList.size();
        this.pictureList.addAll(pictureList);
        notifyItemRangeChanged(startPos, pictureList.size());
    }

    public List<PicturePOJO> getPictureList() {
        return pictureList;
    }

    public void setPictureItemClickListener(PictureItemClickListener pictureItemClickListener){
        this.pictureItemClickListener = pictureItemClickListener;
    }

    public void setLoading(boolean isLoading){
        if (isLoading) {
            pictureList.add(null);
            notifyItemInserted(pictureList.size() -1);
        }
        else{
            int lastPos= pictureList.size()-1;
            pictureList.remove(lastPos);
            notifyItemRemoved(lastPos);
        }
    }

    public void clearList(){
        pictureList.clear();
        notifyDataSetChanged();
    }





}
