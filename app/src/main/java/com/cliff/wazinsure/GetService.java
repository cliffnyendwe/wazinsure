package com.cliff.wazinsure;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetService {

    public static final String TAG = GetService.class.getSimpleName();


    public static void allCustomers(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constatnts.BASE_URL+ "/customers").newBuilder();

        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Customers> processResults(Response response) {
        ArrayList<Customers> allCustomers = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject customersJSON = new JSONObject(jsonData);




            Log.v(TAG, "Response " + customersJSON.toString());
            if (response.isSuccessful()) {
                for (int i = 0; i < customersJSON.length(); i++) {
                    JSONObject resultJSON = customersJSON;
                    String customer_id = resultJSON.getString("customer_id");
                    String id_no = resultJSON.getString("id_no");
                    String first_name = resultJSON.getString("first_name");
                    String last_name = resultJSON.getString("last_name");
                    String dob = resultJSON.getString("dob");
                    String kra_pin = resultJSON.getString("kra_pin");
                    String occupation = resultJSON.getString("occupation");
                    String mobile_no = resultJSON.getString("mobile_no");
                    String email = resultJSON.getString("email");
                    String location = resultJSON.getString("location");
                    String postal_address = resultJSON.getString("postal_address");
                    String postal_code = resultJSON.getString("postal_code");
                    String town = resultJSON.getString("town");
                    String country = resultJSON.getString("country");
                    String photo_url = resultJSON.getString("photo_url");
                    String nok_fullname = resultJSON.getString("nok_fullname");
                    String nok_mobileno = resultJSON.getString("nok_mobileno");
                    String nok_relation = resultJSON.getString("nok_relation");
                    String agent_code = resultJSON.getString("agent_code");
                    String agent_usercode = resultJSON.getString("agent_usercode");
                    String sales_channel = resultJSON.getString("sales_channel");


                    Customers customers =  new Customers(customer_id, id_no, first_name, last_name, dob, kra_pin, occupation, mobile_no, email,
                            location, postal_address, postal_code, town, country, photo_url, nok_fullname, nok_mobileno, nok_relation, agent_code, agent_usercode, sales_channel);
                    allCustomers.add(customers);

                    System.out.println(customers);


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

}
