package com.example.apinstagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DFdkNrhPTTxRNiPmPaOF8DX1lRIZQYO19oBXwM1I")
                // if defined
                .clientKey("XEkZ5LGLCM2hh8djKz7ypuLYdmcFdcwpaC3QHhjh")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
