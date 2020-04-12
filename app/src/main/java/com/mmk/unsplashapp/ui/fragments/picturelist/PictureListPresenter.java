package com.mmk.unsplashapp.ui.fragments.picturelist;

import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.intractor.PhotosIntractor;
import com.mmk.unsplashapp.model.Picture;
import com.mmk.unsplashapp.network.responses.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureListPresenter implements PictureListContractor.Presenter {
    private PictureListContractor.View mView;
    private PhotosIntractor photosIntractor;
    private int currentPageNumber;

    public PictureListPresenter(PictureListContractor.View mView) {
        this.mView = mView;
        currentPageNumber = 0;
        photosIntractor = new PhotosIntractor();
        mView.setPresenter(this);
    }

    @Override
    public void loadPictures() {
        if (mView == null) return;
        mView.showItemLoading(true);
        photosIntractor.loadPhotos(String.valueOf(currentPageNumber)
                , new Callback<List<SearchResponse.Result>>() {
                    @Override
                    public void onResponse(Call<List<SearchResponse.Result>> call, Response<List<SearchResponse.Result>> response) {
                        List<Picture> pictureList = new ArrayList<>();

                        if (response.isSuccessful()) {
                            for (SearchResponse.Result result : response.body()) {
                                Picture picture = new Picture(result.getId(),
                                        result.getUrls().getSmall(),
                                        result.getUrls().getRegular());
                                pictureList.add(picture);

                            }
                            mView.addPictures(pictureList);
                            currentPageNumber++;
                        } else {
                            error();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SearchResponse.Result>> call, Throwable t) {
                        error();
                    }
                });

    }

    @Override
    public void searchPictures(String query) {
        if (mView == null) return;

        mView.showItemLoading(true);
        photosIntractor.searchPhotos(query, String.valueOf(currentPageNumber)
                , new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        List<Picture> pictureList = new ArrayList<>();

                        if (response.isSuccessful()) {
                            for (SearchResponse.Result result : response.body().getResults()) {
                                Picture picture = new Picture(result.getId(),
                                        result.getUrls().getSmall(),
                                        result.getUrls().getRegular());
                                pictureList.add(picture);

                            }
                            mView.addPictures(pictureList);
                            currentPageNumber++;
                        } else {
                            error();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        error();
                    }
                });

    }

    @Override
    public void reset() {
        currentPageNumber = 1;
    }

    private void error() {

        mView.showErrorMessage(mView.getContextOfActivity()
                .getResources()
                .getString(R.string.toast_fail_message));
        mView.showItemLoading(false);
    }
}
