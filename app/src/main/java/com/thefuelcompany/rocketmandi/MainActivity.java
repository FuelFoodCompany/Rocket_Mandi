package com.thefuelcompany.rocketmandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private Firebase myFirebaseRef;
    private String emailEncoded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * The Firebase library must be initialized once with an Android context.
         * This must happen before any Firebase app reference is created or used.
         */
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://rocket-mandi.firebaseio.com/");

        if(checkLoggedIn()){

            Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
            String email = (String) myFirebaseRef.getAuth().getProviderData().get("email");
            homeActivityIntent.putExtra("email" , email);
            homeActivityIntent.putExtra("emailEncoded" , getEncodedEmail(email));
            startActivity(homeActivityIntent);
        }else {
            Intent logInActivityIntent= new Intent(MainActivity.this,LogInActivity.class);
            startActivity(logInActivityIntent);
        }
        this.finish();
    }

    private boolean checkLoggedIn(){
       AuthData author = myFirebaseRef.getAuth();
        if(author == null){
            return false;
        }
        return true;
    }

    private String getEncodedEmail(String email){
        emailEncoded = email;
        emailEncoded = email.replace(".", "DOT");
        emailEncoded = email.replace("#", "HASH");
        emailEncoded = email.replace("$" , "DOLLAR");
        return emailEncoded;
    }

}
