package com.mmk.unsplashapp.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


//This Infinite Scroll Listener is For StaggerLayoutManager

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private StaggeredGridLayoutManager layoutManager;
    private boolean isLoading = true;
    private int totalItemCount = 0;
    private int lastVisibleItem = 0;
    private int visibleThreshold = 5;
    private int previousTotalCount = 0;
    private ILoadMore loadMore;


    public InfiniteScrollListener(StaggeredGridLayoutManager layoutManager, ILoadMore loadMore) {
        this.layoutManager = layoutManager;
        this.loadMore = loadMore;
    }

    public void reset() {
        isLoading = true;
        totalItemCount = 0;
        lastVisibleItem = 0;
        visibleThreshold = 5;
        previousTotalCount = 0;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {

            totalItemCount = layoutManager.getItemCount();
            int[] lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null);
            lastVisibleItem=getLastVisibleItem(lastVisibleItemPositions);


            if (isLoading && totalItemCount > previousTotalCount) {
                previousTotalCount = totalItemCount + 1; //+1 for progress item
                isLoading = false;
            }

            if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                if (loadMore != null) loadMore.onLoad();
                isLoading = true;
            }


        }

    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }
}
