package com.mmk.unsplashapp.ui.fragments.picturedownload;

import com.mmk.unsplashapp.model.Picture;
import com.mmk.unsplashapp.ui.BaseView;

public interface PictureDownloadContractor {

    interface View extends BaseView<PictureDownloadPresenter>{
        void showDownloadProgress(boolean isDownloading);
        void showMessage(String message);
    }

    interface Presenter {

        void downloadImage(Picture picture);
    }
}
