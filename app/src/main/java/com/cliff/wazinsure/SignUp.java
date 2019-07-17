package com.cliff.wazinsure;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity {

    CircleImageView profile;
    EditText fullname,username,id_no,mobile_no,email,password;
    Button btn_signup;
    TextView login;

    Uri saveUri;
    private final int PICK_IMAGE_REQUEST =71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        profile = (CircleImageView) findViewById(R.id.profile_image);
        fullname = (EditText) findViewById(R.id.fullname);
        username = (EditText) findViewById(R.id.username);
        id_no = (EditText) findViewById(R.id.id_no);
        mobile_no = (EditText) findViewById(R.id.mobile_no);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        btn_signup = (Button) findViewById(R.id.btn_signup);
        login = (TextView) findViewById(R.id.login);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.profile_image_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    registerNewUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }

    // selecting image
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SelectPicture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() !=null){
            saveUri=data.getData();
            profile.setImageURI(saveUri);
        }
    }

    private void registerNewUser() throws IOException {
        String name = fullname.getText().toString();
        String userName = username.getText().toString();
        String idNo = id_no.getText().toString();
        String mobile = mobile_no.getText().toString();
        String eMail = email.getText().toString();
        String passw= password.getText().toString();

        PostService postService = new PostService();
        postService.register(name, idNo,mobile, eMail, saveUri, userName,passw);
    }

}
