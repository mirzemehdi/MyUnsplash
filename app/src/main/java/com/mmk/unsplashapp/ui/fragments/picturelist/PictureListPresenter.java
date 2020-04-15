package com.mmk.unsplashapp.ui.fragments.picturelist;

import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.intractor.PhotosIntractor;
import com.mmk.unsplashapp.pojo.PicturePOJO;
import com.mmk.unsplashapp.pojo.ResponseSearchPicturesPOJO;

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
                , new LoadPicturesCallBack());

    }

    @Override
    public void searchPictures(String query) {
        if (mView == null) return;

        mView.showItemLoading(true);
        photosIntractor.searchPhotos(query, String.valueOf(currentPageNumber)
                , new SearchPicturesCallBack());

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

    public class SearchPicturesCallBack implements Callback<ResponseSearchPicturesPOJO>{

        @Override
        public void onResponse(Call<ResponseSearchPicturesPOJO> call, Response<ResponseSearchPicturesPOJO> response) {
            List<PicturePOJO> pictureList = new ArrayList<>();

            if (response.isSuccessful()) {
                for (ResponseSearchPicturesPOJO.Result result : response.body().getResults()) {
                    PicturePOJO picture = new PicturePOJO(result.getId(),
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
        public void onFailure(Call<ResponseSearchPicturesPOJO> call, Throwable t) {
            error();
        }
    }


    public class LoadPicturesCallBack implements Callback<List<ResponseSearchPicturesPOJO.Result>>{

        @Override
        public void onResponse(Call<List<ResponseSearchPicturesPOJO.Result>> call, Response<List<ResponseSearchPicturesPOJO.Result>> response) {
            List<PicturePOJO> pictureList = new ArrayList<>();

            if (response.isSuccessful()) {
                for (ResponseSearchPicturesPOJO.Result result : response.body()) {
                    PicturePOJO picture = new PicturePOJO(result.getId(),
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
        public void onFailure(Call<List<ResponseSearchPicturesPOJO.Result>> call, Throwable t) {
            error();
        }
    }
}
