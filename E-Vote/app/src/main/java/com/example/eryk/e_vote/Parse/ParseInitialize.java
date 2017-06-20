package com.example.eryk.e_vote.Parse;

import android.app.Application;
import android.util.Log;

import com.example.eryk.e_vote.R;
import com.parse.Parse;

/**
 * Created by Eryk on 16/03/2017.
 */

public class ParseInitialize extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.parseID))
                .clientKey(null)
                .server(getString(R.string.parseServer))
                .build()
        );

        Log.i("Parse"," Initialized");
    }
}
