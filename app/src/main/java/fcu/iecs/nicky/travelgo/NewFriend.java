package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewFriend extends AppCompatActivity {
    EditText InputAccount;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add=(Button)findViewById(R.id.btn_add);
        add.setOnClickListener(add_onclickListener);
        InputAccount =(EditText)findViewById(R.id.input_FriendAccount);

    }

    View.OnClickListener add_onclickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseReference UserChecker = FirebaseDatabase.getInstance().getReference("UserTable");
            UserChecker.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean isOk=false;
                    String getAccount = InputAccount.getText().toString();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("name").getValue().toString().equals(getAccount)){
                            isOk=true;
                            break;
                        }
                    }
                    if(isOk==true){
                        Intent nextPage = new Intent();
                        nextPage.setClass(NewFriend.this,Myfriend.class);
                        startActivity(nextPage);
                        Toast.makeText(NewFriend.this,"好友加入成功",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(NewFriend.this,"該好友不存在,請重新輸入",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        }
    };
}
