package com.mmk.unsplashapp.ui.fragments.picturedownload;

import com.mmk.unsplashapp.pojo.PicturePOJO;
import com.mmk.unsplashapp.ui.BaseView;

public interface PictureDownloadContractor {

    interface View extends BaseView<PictureDownloadPresenter>{
        void showDownloadProgress(boolean isDownloading);
        void showMessage(String message);
    }

    interface Presenter {

        void downloadImage(PicturePOJO picture);
    }
}
