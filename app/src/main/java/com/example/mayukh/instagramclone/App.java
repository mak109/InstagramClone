package com.example.mayukh.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("vnS5HWURwCE5048tx12PUwYL87v8J9rFkAZWlTUI")
                // if defined
                .clientKey("zuoFxpxzodvV9Qnud9u7Z2XfL2t8J0KMZ5pVsZuq")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
