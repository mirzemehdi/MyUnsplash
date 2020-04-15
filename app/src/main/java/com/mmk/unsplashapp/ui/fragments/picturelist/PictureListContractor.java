package com.mmk.unsplashapp.ui.fragments.picturelist;

import com.mmk.unsplashapp.pojo.PicturePOJO;
import com.mmk.unsplashapp.ui.BaseView;

import java.util.List;

 interface PictureListContractor {
     interface View extends BaseView<PictureListPresenter>{

         void addPictures(List<PicturePOJO> pictureList);
         void showItemLoading(boolean isLoading);
         void showErrorMessage(String errorMessage);
     }
     interface Presenter{
         void loadPictures();
         void searchPictures(String query);
         void reset();
     }
}
