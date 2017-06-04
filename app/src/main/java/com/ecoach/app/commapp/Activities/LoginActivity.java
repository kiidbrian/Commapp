package com.ecoach.app.commapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.ecoach.app.commapp.Models.ResponseObjects.AccountOpeningResponse;
import com.ecoach.app.commapp.R;
import com.ecoach.app.commapp.Services.CommappService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {

    PinEntryEditText pin;
    CommappService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().hide();
        Button loginButton = (Button) findViewById(R.id.button3);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pinCode = pin.getText().toString();

            }
        });

        TextView signupButton = (TextView) findViewById(R.id.createAccountButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(SignUpActivity);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://216.45.51.132:8008/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(CommappService.class);

    }

    private void login(String pin){
        Call<AccountOpeningResponse> response = apiService.loginUser(pin);
        //Toast.makeText(CompleteSignUpActivity.this, response., Toast.LENGTH_LONG).show();
        response.enqueue(new openingCallback());
    }

    private class openingCallback implements Callback<AccountOpeningResponse> {

        @Override
        public void onResponse(Call<AccountOpeningResponse> call, Response<AccountOpeningResponse> response) {
            Intent homeActivity = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeActivity);
            //showProgress(false);
            //Toast.makeText(CompleteSignUpActivity.this, response.toString(), Toast.LENGTH_SHORT);
        }

        @Override
        public void onFailure(Call<AccountOpeningResponse> call, Throwable t) {
            //Toast.makeText(CompleteSignUpActivity.this, t.toString(), Toast.LENGTH_SHORT);
        }
    }
}
