package com.cliff.wazinsure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Login extends AppCompatActivity {

    EditText username,user_password;
    Button login;
    TextView sign_up;
    String status = "ggg";
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        user_password = (EditText)findViewById(R.id.user_password);
        login = (Button)findViewById(R.id.btn_login);
        sign_up = (TextView)findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    try {
                        userLogin();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void userLogin() throws IOException, InterruptedException {
        String user_name = username.getText().toString();
        String password = user_password.getText().toString();

        PostService postService = new PostService();
        postService.login(user_name, password);

    }

}
