package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button toLogin,toFindPlace,toFindFriend,toMyFriend,toMyGroup;
    TextView View_UserName;
    public static final int LoginActivityID=1000;
    public static final int FindplaceID = 2000;
    public static final String MyGroupID = "My group";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toLogin = (Button)findViewById(R.id.btn_ToLogin);
        toFindFriend = (Button)findViewById(R.id.btn_findFriend);
        toFindPlace =(Button)findViewById(R.id.btn_findPlace);
        toMyFriend =(Button)findViewById(R.id.btn_myFriend);
        toMyGroup = (Button)findViewById(R.id.btn_myGroup);
        View_UserName =(TextView)findViewById(R.id.textView_UserName);

        toLogin.setOnClickListener(toLogin_onclickListener);
        toFindFriend.setOnClickListener(toFindFriend_onclickListener);
        toFindPlace.setOnClickListener(toFindPlace_onclickListener);
        toMyFriend.setOnClickListener(toMyFriend_onclickListener);
        toMyGroup.setOnClickListener(toMyGroup_onclickListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    View.OnClickListener toLogin_onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(toLogin.getText().toString()=="登入"){
                Intent toNextPage = new Intent();
                toNextPage.setClass(MainActivity.this,LoginActivity.class);
                startActivityForResult(toNextPage,LoginActivityID);
            }
            else {
                View_UserName.setText("尚未登入");
                toLogin.setText("登入");
            }
        }
    };

    View.OnClickListener toFindPlace_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toFindPlacePage = new Intent();
            toFindPlacePage.setClass(MainActivity.this,FindPlace.class);
            startActivityForResult(toFindPlacePage,FindplaceID);
        }
    };

    View.OnClickListener toFindFriend_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener toMyFriend_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(View_UserName.getText().toString().equals("尚未登入")){
                Intent toNextPage = new Intent();
                toNextPage.setClass(MainActivity.this,LoginActivity.class);
                startActivityForResult(toNextPage,LoginActivityID);
            }
            else{
                Intent toMyfriendPage = new Intent();
                toMyfriendPage.setClass(MainActivity.this,Myfriend.class);
                startActivity(toMyfriendPage);
            }
        }
    };

    View.OnClickListener toMyGroup_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(View_UserName.getText().toString().equals("尚未登入")){
                Intent toNextPage = new Intent();
                toNextPage.setClass(MainActivity.this,LoginActivity.class);
                startActivityForResult(toNextPage,LoginActivityID);
            }
            else{
                Intent toMyGroupPage = new Intent();
                String userName = View_UserName.getText().toString();
                toMyGroupPage.setClass(MainActivity.this,MyGroup.class);
                toMyGroupPage.putExtra(userName,MyGroupID);
                startActivity(toMyGroupPage);
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case LoginActivityID:
                Boolean isOk = data.getBooleanExtra("isOk",false);
                if(isOk==true){
                    String UserName = data.getStringExtra("UserName");
                    View_UserName.setText(UserName);
                    toLogin.setText("登出");
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
