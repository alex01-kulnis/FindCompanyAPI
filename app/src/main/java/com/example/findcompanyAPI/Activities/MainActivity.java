package com.example.findcompanyAPI.Activities;

import static com.example.findcompanyAPI.Config.appPreferencesName;
import static com.example.findcompanyAPI.Config.baseRetrofitUrl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findcompanyAPI.Api.api.ApiServices;
import com.example.findcompanyAPI.Database.DBHelper;
import com.example.findcompanyAPI.Models.Event;
import com.example.findcompanyAPI.Models.User;
import com.example.findcompanyAPI.R;
import com.example.findcompanyAPI.Utils.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText  firstname, secondname, login, password;
    Button auth, registration;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureRetrofif();

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        firstname = (EditText) findViewById(R.id.firstname);
        secondname = (EditText) findViewById(R.id.secondname);

        auth = (Button) findViewById(R.id.auth);
        registration = (Button) findViewById(R.id.registration);

        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Utils.hasConnection(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "No active networks... ", Toast.LENGTH_LONG).show();
                    return;
                }
                String uniquelogin = login.getText().toString();
                String pass = password.getText().toString();
                String first = firstname.getText().toString();
                String second = secondname.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseRetrofitUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiServices apiService = retrofit.create(ApiServices.class);

                User user = new User(first,second,uniquelogin,pass);

                Call<User> call = apiService.registration(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            Log.d("Code", String.valueOf(response.code()));
                            return;
                        }

                        Toast.makeText(MainActivity.this,"Регистрация прошла успешно",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                            Log.d("gg","11");
                        }
                });
            }
        });

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
//    if(Utils.hasConnection(MainActivity.this)) {}
//    else
//    {
//        Toast.makeText(MainActivity.this, "No active networks... ", Toast.LENGTH_LONG).show();
//    }

    private void configureRetrofif(){
        /*SharedPreferences settings = getSharedPreferences(appPreferencesName, Context.MODE_PRIVATE);
        String finalresult = "Bearer " + settings.getString("token","");


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .header("Authorization", finalresult)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseRetrofitUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(okHttpClient)
                .build();

        ApiServices apiService = retrofit.create(ApiServices.class);

        Call<List<Event>> call = apiService.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (!response.isSuccessful()){
                    Log.d("Code", String.valueOf(response.code()));
                    return;
                }

                List<Event> events = response.body();

                for (Event event :events){
                    Log.d("id",event.getName_event());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.d("gg","11");
            }
        });
    }
}