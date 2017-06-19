package fcu.iecs.nicky.travelgo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewFriend extends AppCompatActivity {
    EditText InputAccount;
    Button add;
    InputAccount =(EditText)findViewById(R.id.input_FriendAccount);
    add=(Button)findViewById(R.id.btn_add);
    add.setOnClickListener(add_onclickListener);

    @Override
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View.OnClickListener add_onclickListener=new View.OnClickListener(){

        }


    }

}
