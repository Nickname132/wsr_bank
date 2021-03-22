package com.example.worldskilsbank;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class account extends AppCompatActivity implements View.OnClickListener{
    ImageView imgMap;
    ImageView imgDial;
    ImageView imgMail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);



        imgDial = (ImageView) findViewById(R.id.img_dial);
        imgMail = (ImageView) findViewById(R.id.img_mail);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                imgMap.setVisibility(View.VISIBLE);
                                imgDial.setVisibility(View.GONE);
                                imgMail.setVisibility(View.GONE);
                                break;
                            case R.id.action_dial:
                                imgMap.setVisibility(View.GONE);
                                imgDial.setVisibility(View.VISIBLE);
                                imgMail.setVisibility(View.GONE);
                                break;
                            case R.id.action_mail:
                                imgMap.setVisibility(View.GONE);
                                imgDial.setVisibility(View.GONE);
                                imgMail.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }



}
