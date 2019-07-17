package com.cliff.wazinsure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class PostService {

    Login login = new Login();

    //    Signup new user
    public void register(String fullname, String id_no, String mobile_no, String email, Uri profileurl, String username, String password) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = Constatnts.BASE_URL + "/register";

        OkHttpClient client = new OkHttpClient();


        JSONObject postdata = new JSONObject();
        try {
            postdata.put("fullname", fullname);
            postdata.put("id_no", id_no);
            postdata.put("mobile_no", mobile_no);
            postdata.put("email", email);
            postdata.put("profileurl", profileurl);
            postdata.put("username", username);
            postdata.put("password", password);

        } catch(JSONException e){
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        System.out.println(url);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage();
                Log.w("failure Response", mMessage);
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e(TAG, mMessage);
                JSONObject responseJSON = null;
                try {
                    responseJSON = new JSONObject(mMessage);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    User login
    public void login(String username, String password) throws IOException {

    MediaType MEDIA_TYPE = MediaType.parse("application/json");
    String url = Constatnts.BASE_URL + "/login";

    OkHttpClient client = new OkHttpClient();


    JSONObject postdata = new JSONObject();
    try {
        postdata.put("username", username);
        postdata.put("password", password);

    } catch(JSONException e){
        e.printStackTrace();
    }

    RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .build();

    System.out.println(url);

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            String mMessage = e.getMessage();
            Log.w("failure Response", mMessage);
            call.cancel();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String mMessage = response.body().string();
//            Log.e(TAG, mMessage);
            JSONObject responseJSON = null;
            try {
                responseJSON = new JSONObject(mMessage);
                System.out.println(responseJSON);
                if (responseJSON.getString("status").equals("success")){
                    login.status = responseJSON.getString("status");
//                    login.vvv();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });
}

    public void startActivity(Intent intent) {
        startActivity(intent);
    }


}

