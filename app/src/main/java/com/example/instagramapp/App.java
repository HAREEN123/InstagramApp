package com.example.instagramapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("yMli3ql6lyZ283ecMqGZ6LWfMzvOdMhuycxmh9ZT")
                // if defined
                .clientKey("V8OBtpAJ5NZqTfJWG84TlUiyJVHWuEhylh8QQc8B")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
