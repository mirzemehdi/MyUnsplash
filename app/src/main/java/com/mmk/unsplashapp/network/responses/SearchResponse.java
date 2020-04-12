package com.mmk.unsplashapp.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {


    @SerializedName("results")
    @Expose
    private List<Result> results = null;


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Result {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("urls")
        @Expose
        private Urls urls;

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public class Urls {

            @SerializedName("regular")
            @Expose
            private String regular;
            @SerializedName("small")
            @Expose
            private String small;
            @SerializedName("thumb")
            @Expose
            private String thumb;


            public String getRegular() {
                return regular;
            }

            public void setRegular(String regular) {
                this.regular = regular;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }


}
