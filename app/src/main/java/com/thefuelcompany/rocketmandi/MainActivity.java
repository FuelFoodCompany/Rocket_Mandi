package com.thefuelcompany.rocketmandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  //  private RocketMandiModel rocketMandiModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   rocketMandiModel = new RocketMandiModel();

        if(checkLoggedIn()){

            Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeActivityIntent);
        }else {
            Intent logInActivityIntent= new Intent(MainActivity.this,LogInActivity.class);
            startActivity(logInActivityIntent);
        }
        this.finish();
    }

    private boolean checkLoggedIn(){
        return true;
    }

}
