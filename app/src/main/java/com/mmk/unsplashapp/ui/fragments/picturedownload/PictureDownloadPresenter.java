package com.mmk.unsplashapp.ui.fragments.picturedownload;

import android.os.Environment;

import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.intractor.PhotosIntractor;
import com.mmk.unsplashapp.model.Picture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureDownloadPresenter implements PictureDownloadContractor.Presenter {

    private PictureDownloadContractor.View mView;
    private PhotosIntractor photosIntractor;

    public PictureDownloadPresenter(PictureDownloadContractor.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
        photosIntractor=new PhotosIntractor();
    }



    @Override
    public void downloadImage(final Picture picture) {

        mView.showDownloadProgress(true);
        photosIntractor.downloadPhoto(picture.getRegularUrl(), new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()&&response.body()!=null) {

                    boolean isDownloaded=writeResponseBodyToDisk(response.body(),
                            "Image_"+picture.getId());
                    if (isDownloaded){
                        mView.showMessage(mView.getContextOfActivity()
                                .getResources()
                                .getString(R.string.toast_success_message_download));
                        mView.showDownloadProgress(false);
                    }
                    else{
                        mView.showMessage(mView.getContextOfActivity()
                                .getResources()
                                .getString(R.string.toast_fail_message_download));
                        mView.showDownloadProgress(false);
                    }
                }
                else {
                    mView.showMessage(mView.getContextOfActivity()
                            .getResources()
                            .getString(R.string.toast_fail_message_download));
                    mView.showDownloadProgress(false);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                mView.showMessage(mView.getContextOfActivity()
                        .getResources()
                        .getString(R.string.toast_fail_message));
                mView.showDownloadProgress(false);
            }
        });


    }



    private  boolean writeResponseBodyToDisk(ResponseBody body,String imageName) {
        try {

            File rootPath=new File(Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_PICTURES)+File.separator
                    +mView.getActivityOfActivity().getResources().getString(R.string.app_name));
            if (!rootPath.exists()) rootPath.mkdir();

            File imagePath = new File( rootPath
                    +File.separator+  imageName+".jpg");
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imagePath);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
