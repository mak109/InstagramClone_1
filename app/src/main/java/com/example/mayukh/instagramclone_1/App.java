package com.example.mayukh.instagramclone_1;
import com.parse.Parse;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("pY8bjuAkHP4WYwXVQvuPvtq1fG2LkP5EAgFuequ4")
                // if defined
                .clientKey("fy6eMuoksbTOxB2qCtHfCyq8izMyotJghOMO8N80")
                .server("https://parseapi.back4app.com/")
                .build()

        
        );
    }
}
