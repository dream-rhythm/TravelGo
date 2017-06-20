package fcu.iecs.nicky.travelgo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static fcu.iecs.nicky.travelgo.MainActivity.MyGroupID;

public class Myfriend extends AppCompatActivity {
    Button tonewfriend;
 //   TextView ShowName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfriend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tonewfriend = (Button)findViewById(R.id.btn_newfriend);
        tonewfriend.setOnClickListener(tonewfriend_onclickListener);
     //   ShowName =(EditText)findViewById(R.id.ShowName);
    }
    private static final int ACTIVITY_SET_ACCOUNT=1;
  View.OnClickListener tonewfriend_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Myfriend.this,NewFriend.class);
            startActivityForResult(intent,ACTIVITY_SET_ACCOUNT);
        }
    };

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        String inAccount;
        TextView showaccount;
        TextView count;

        showaccount =(TextView)findViewById(R.id.Account);
        count = (TextView)findViewById(R.id.Count);

        if(intent == null) return;

        super.onActivityResult(requestCode,resultCode,intent);
        switch(requestCode){
            case ACTIVITY_SET_ACCOUNT:
                inAccount = intent.getStringExtra("Account");
                showaccount.setText(inAccount);
                count.setText("好友數量 1/10");
        }
    }
}
