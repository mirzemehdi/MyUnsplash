package com.mmk.unsplashapp.ui.fragments.picturelist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.ui.activities.main.MainActivity;
import com.mmk.unsplashapp.ui.fragments.picturedownload.PictureDownloadFragment;
import com.mmk.unsplashapp.utils.ILoadMore;
import com.mmk.unsplashapp.pojo.PicturePOJO;
import com.mmk.unsplashapp.utils.InfiniteScrollListener;


import java.util.List;

public class PictureListFragment extends Fragment implements PictureListContractor.View{

    public static final String ARGUMENT_PICTURE="ARGUMENT_PICTURE";
    public static final String BUNDLE_SAVED_PICTURES="BUNDLE_SAVED_PICTURES";

    private SearchView searchView;
    private RecyclerView picturesRecyclerView;
    private InfiniteScrollListener infiniteScrollListener;
    private PicturesAdapter picturesAdapter;
    private String currentSearch="";
    private PictureListContractor.Presenter mPresenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if(getView()==null)
             view=inflater.inflate(R.layout.fragment_picture_list,container,false);
        else view=getView();
        init(view);
        setClicks();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PictureListPresenter(this);
        picturesAdapter=new PicturesAdapter();
        mPresenter.loadPictures();
    }

    private void init(View view) {

        searchView=view.findViewById(R.id.search_view_picture_list);
        picturesRecyclerView=view.findViewById(R.id.recycler_view_picture_list);
        picturesRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(
                2, LinearLayoutManager.VERTICAL);
        picturesRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        infiniteScrollListener=new InfiniteScrollListener(staggeredGridLayoutManager, new ILoadMore() {
            @Override
            public void onLoad() {
                if (TextUtils.isEmpty(currentSearch))
                    mPresenter.loadPictures();
                else
                     mPresenter.searchPictures(currentSearch);
            }
        });

        picturesRecyclerView.addOnScrollListener(infiniteScrollListener);
        picturesRecyclerView.setAdapter(picturesAdapter);




    }

    private void setClicks() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                reset();
                currentSearch = query;
                mPresenter.searchPictures(currentSearch);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        picturesAdapter.setPictureItemClickListener(new PictureItemClickListener() {
            @Override
            public void onClick(PicturePOJO picture) {
                Bundle args=new Bundle();
                args.putParcelable(ARGUMENT_PICTURE,picture);
                PictureDownloadFragment downloadFragment=new PictureDownloadFragment();
                downloadFragment.setArguments(args);
                ((MainActivity)getActivityOfActivity()).changeFragment(downloadFragment);

            }
        });
    }


    public void reset() {
        currentSearch = "";
        mPresenter.reset();
        picturesAdapter.clearList();
        infiniteScrollListener.reset();
    }

    @Override
    public void addPictures(List<PicturePOJO> pictureList) {
        picturesAdapter.addPictureList(pictureList);
    }


    @Override
    public void showItemLoading(boolean isLoading) {
        if (isLoading) picturesAdapter.setLoading(true);
        else picturesAdapter.setLoading(false);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContextOfActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PictureListPresenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public Activity getActivityOfActivity() {
        return getActivity();
    }

    @Override
    public Context getContextOfActivity() {
        return getContext();
    }
}
