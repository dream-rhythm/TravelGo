package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import static fcu.iecs.nicky.travelgo.MainActivity.MyGroupID;

public class Myfriend extends AppCompatActivity {
    Button tonewfriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myfriend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tonewfriend = (Button)findViewById(R.id.btn_newfriend);
        tonewfriend.setOnClickListener(tonewfriend_onclickListener);
    }

  View.OnClickListener tonewfriend_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent nextPage = new Intent();
            nextPage.setClass(Myfriend.this,NewFriend.class);
            startActivity(nextPage);
        }
    };
}
