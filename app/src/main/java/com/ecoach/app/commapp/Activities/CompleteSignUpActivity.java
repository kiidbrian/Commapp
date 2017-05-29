package com.ecoach.app.commapp.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.ecoach.app.commapp.Models.Account;
import com.ecoach.app.commapp.Models.ResponseObjects.AccountOpeningResponse;
import com.ecoach.app.commapp.R;
import com.ecoach.app.commapp.Services.CommappService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class CompleteSignUpActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private EditText idNumber;
    private EditText instiCode;
    private Spinner idType;
    private Spinner accountType;
    private String firstname, secondname, email, phone, dob, address;
    CommappService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_sign_up);
        setupActionBar();
        idNumber = (EditText) findViewById(R.id.idNumber);
        instiCode = (EditText) findViewById(R.id.institutionCode);
        idType = (Spinner) findViewById(R.id.idtypeSpinner);
        ArrayAdapter<CharSequence> oAdapter = ArrayAdapter.createFromResource(this,
                R.array.idType, android.R.layout.simple_spinner_item);
        oAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idType.setAdapter(oAdapter);

        accountType = (Spinner) findViewById(R.id.accountTypeSpinner);
        ArrayAdapter<CharSequence> lAdapter = ArrayAdapter.createFromResource(this,
                R.array.accountType, android.R.layout.simple_spinner_item);
        lAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(lAdapter);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        // Set up the login form. getIntent().getStringExtra("pin");
    /*    mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        ;*/
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://216.45.51.132:8008/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(CommappService.class);
    }



    /**
     * Callback received when a permissions request has been completed.
     */
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        //mEmailView.setError(null);
        //mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(idNumber.getText())) {
            idNumber.setError("Required");
            focusView = idNumber;
            cancel = true;
        }
        else if (TextUtils.isEmpty(instiCode.getText())) {
            instiCode.setError("Required");
            focusView = instiCode;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Account openAccount = new Account();
            openAccount.first_name = getIntent().getStringExtra("firstname");
            openAccount.last_name = getIntent().getStringExtra("secondname");
            openAccount.email = getIntent().getStringExtra("email");
            openAccount.address = getIntent().getStringExtra("address");
            openAccount.phone_number = getIntent().getStringExtra("phone");
            openAccount.dob = getIntent().getStringExtra("dob");

            if(OpenAccountRequest(openAccount)){

            }
        }
    }

    private boolean OpenAccountRequest(Account account){
        Call<AccountOpeningResponse> response = apiService.requestOpening(account.first_name, account.last_name, account.email, account.phone_number, account.dob, account.account_type, account.gender, account.address, account.institution_code);
        //Toast.makeText(CompleteSignUpActivity.this, response., Toast.LENGTH_LONG).show();
        response.enqueue(new openingCallback());
        return false;
    }

    private class openingCallback implements Callback<AccountOpeningResponse>{

        @Override
        public void onResponse(Call<AccountOpeningResponse> call, Response<AccountOpeningResponse> response) {
            showProgress(false);
            Toast.makeText(CompleteSignUpActivity.this, "here", Toast.LENGTH_SHORT);
        }

        @Override
        public void onFailure(Call<AccountOpeningResponse> call, Throwable t) {
            Toast.makeText(CompleteSignUpActivity.this, "nope", Toast.LENGTH_SHORT);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

