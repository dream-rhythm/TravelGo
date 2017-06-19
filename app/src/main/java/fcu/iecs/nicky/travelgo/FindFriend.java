package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FindFriend extends AppCompatActivity {
    Button Go;
    Button FollowMe;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Go = (Button)findViewById(R.id.btn_go);
        FollowMe = (Button)findViewById(R.id.btn_follow);
        back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent();
                nextpage.setClass(FindFriend.this,FriendMap.class);
                startActivity(nextpage);
            }
        });
        FollowMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent();
                nextpage.setClass(FindFriend.this,FollowMe.class);
                startActivity(nextpage);
            }
        });
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final String[] lunch = {"a25978032", "abcde"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(FindFriend.this,
                android.R.layout.simple_spinner_dropdown_item,
                lunch);
        spinner.setAdapter(lunchList);


    }

}
