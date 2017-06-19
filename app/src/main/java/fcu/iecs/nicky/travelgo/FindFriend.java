package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FindFriend extends AppCompatActivity {
    Button Go;
    Button FollowMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Go = (Button)findViewById(R.id.btn_go);
        FollowMe = (Button)findViewById(R.id.btn_follow);

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


    }

}
