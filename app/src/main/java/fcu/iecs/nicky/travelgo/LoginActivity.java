package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button cancel;
    Button login;
    Button newUser;
    EditText InputName,InputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cancel = (Button)findViewById(R.id.btn_cancel);
        login = (Button)findViewById(R.id.btn_Login);
        newUser = (Button)findViewById(R.id.btn_newUser);
        cancel.setOnClickListener(cancel_onclickListener);
        login.setOnClickListener(login_onclickListener);
        newUser.setOnClickListener(newUser_onclickListener);
        InputName = (EditText)findViewById(R.id.input_UserName);
        InputPassword=(EditText)findViewById(R.id.input_Password);


    }

    View.OnClickListener login_onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseReference UserChecker = FirebaseDatabase.getInstance().getReference("UserTable");
            UserChecker.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean isOk=false;
                    String userInput_name = InputName.getText().toString();
                    String userInput_password = InputPassword.getText().toString();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("name").getValue().toString().equals(userInput_name)&&
                                ds.child("password").getValue().toString().equals(userInput_password)){
                                    isOk=true;
                                    break;
                        }
                    }
                    if(isOk==true){
                        Toast.makeText(LoginActivity.this,"登入成功",Toast.LENGTH_LONG).show();
                        Intent back =new Intent();
                        back.putExtra("isOk",isOk);
                        back.putExtra("UserName",userInput_name);
                        setResult(RESULT_OK, back);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"登入失敗",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    };
    View.OnClickListener cancel_onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent back =new Intent();
            back.putExtra("isOk",false);
            back.putExtra("UserName","尚未登入");
            setResult(RESULT_OK, back);
            finish();
        }
    };
    View.OnClickListener newUser_onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
