package com.mmk.unsplashapp.ui;

import android.app.Activity;
import android.content.Context;

public interface BaseView<T> {

    void setPresenter(T presenter);
    Activity getActivityOfActivity();
    Context getContextOfActivity();

}
