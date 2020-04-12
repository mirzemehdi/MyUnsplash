package com.mmk.unsplashapp.ui.fragments.picturedownload;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.github.abdularis.buttonprogress.DownloadButtonProgress;
import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.model.Picture;
import com.mmk.unsplashapp.ui.fragments.picturelist.PictureListFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PictureDownloadFragment extends Fragment implements PictureDownloadContractor.View {

    private static final int REQUEST_CODE_WRITE_STORAGE = 1;
    private PictureDownloadContractor.Presenter mPresenter;

    private ImageView imageView;
    private Picture picture;
    private ProgressBar progressBar;
    private DownloadButtonProgress btnDownload;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_download, container, false);
        new PictureDownloadPresenter(this);
        init(view);
        setClicks();
        return view;
    }


    private void init(View view) {
        if (getArguments() != null) {
            picture = (Picture) getArguments().getParcelable(PictureListFragment.ARGUMENT_PICTURE);
        }


        progressBar = view.findViewById(R.id.progressBar_loading);
        imageView = view.findViewById(R.id.imageView_activity_download);
        btnDownload = view.findViewById(R.id.btn_download);
        showLoading(true);
        if (picture != null) {
            Picasso.with(getContextOfActivity())
                    .load(picture.getRegularUrl())
                    .placeholder(R.drawable.item_place_holder)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            showLoading(false);
                        }

                        @Override
                        public void onError() {
                            showLoading(false);
                        }
                    });
        }

    }

    private void setClicks() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (askPermission()) {
                    if (picture != null)
                        mPresenter.downloadImage(picture);
                } else
                    Toast.makeText(getContextOfActivity(),
                            getResources().getString(R.string.toast_permission_required)
                            , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean askPermission() {
        if (ActivityCompat.checkSelfPermission(getContextOfActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_STORAGE);
            return false;
        }
        else return true;


    }


    public void showLoading(boolean isLoading) {
        if (isLoading)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showDownloadProgress(boolean isDownloading) {
        if (isDownloading) {
            Toast.makeText(getActivityOfActivity(), "Image is downloading ", Toast.LENGTH_SHORT).show();
            btnDownload.setIndeterminate();
            btnDownload.setClickable(false);
        } else {
            btnDownload.setIdle();
            btnDownload.setClickable(true);
        }


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivityOfActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PictureDownloadPresenter presenter) {
        mPresenter = presenter;
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
