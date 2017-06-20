package com.example.eryk.e_vote;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * A register screen that offers registration services.
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    // Information Edit Texts
    private EditText mEmailEt, mReEmailEt, mFirstNameEt, mSecondNameEt, mSurnameEt, mAddrFirstEt, mAddrSecondEt, mAddrThirdEt, mAddrFourthEt, mPhoneEt;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner mContrySpinner;
    private Spinner mCitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mCitySpinner = (Spinner) findViewById(R.id.reg_citySpinner);
        mContrySpinner = (Spinner) findViewById(R.id.reg_countrySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countryArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mContrySpinner.setAdapter(adapter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.cityArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mCitySpinner.setAdapter(adapter2);

        // Set up the reg form.
        // Personal info
        mFirstNameEt = (EditText) findViewById(R.id.reg_firstName);
        mSecondNameEt = (EditText) findViewById(R.id.reg_middleName);
        mSurnameEt = (EditText) findViewById(R.id.reg_surname);
        // Address Info
        mAddrFirstEt = (EditText) findViewById(R.id.reg_addrFirstLine);
        mAddrSecondEt = (EditText) findViewById(R.id.reg_addrSecondLine);
        mAddrThirdEt = (EditText) findViewById(R.id.reg_addrThirdLine);
        mAddrFourthEt = (EditText) findViewById(R.id.reg_addrFourthLine);

        // Contact Info
        mEmailEt = (EditText) findViewById(R.id.reg_email);
        mReEmailEt = (EditText) findViewById(R.id.reg_reEmail);
        mPhoneEt = (EditText) findViewById(R.id.reg_phoneNo);

        mLoginFormView = findViewById(R.id.reg_form);
        mProgressView = findViewById(R.id.reg_progress);
        TextView mLoginView = (TextView) findViewById(R.id.reg_loginButton);
        Button mEmailSignInButton = (Button) findViewById(R.id.reg_email_sign_in_button);


        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        // Go back to login activity ( button at the very bottom of the app ).
        mLoginView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
    /**
     * Attempts to register the account specified by the reg form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual reg attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailEt.setError(null);
        mEmailEt.setError(null);
        mReEmailEt.setError(null);
        mFirstNameEt.setError(null);
        mSecondNameEt.setError(null);
        mSurnameEt.setError(null);
        mAddrFirstEt.setError(null);
        mAddrSecondEt.setError(null);
        mAddrThirdEt.setError(null);
        mAddrFourthEt.setError(null);
        mPhoneEt.setError(null);

        // Store values at the time of the login attempt.
        String firstName,secondName,surName,email,reEmail,addrFirst,addrSecond,addrThirt,postalCode,phoneS;
        firstName = mFirstNameEt.getText().toString();
        secondName = mSecondNameEt.getText().toString();
        surName = mSurnameEt.getText().toString();
        email = mEmailEt.getText().toString();
        reEmail = mReEmailEt.getText().toString();
        addrFirst = mAddrFirstEt.getText().toString();
        addrSecond = mAddrSecondEt.getText().toString();
        addrThirt = mAddrThirdEt.getText().toString();
        postalCode = mAddrFourthEt.getText().toString();
        phoneS = mPhoneEt.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for the valid data.
        if(TextUtils.isEmpty(firstName)){
            mFirstNameEt.setError(getString(R.string.error_field_required));
            focusView = mFirstNameEt;
            cancel = true;
        } else if(TextUtils.isEmpty(surName)){
            mSurnameEt.setError(getString(R.string.error_field_required));
            focusView = mSurnameEt;
            cancel = true;
        } else if(TextUtils.isEmpty(addrFirst)){
            mAddrFirstEt.setError(getString(R.string.error_field_required));
            focusView = mAddrFirstEt;
            cancel = true;
        } else if(TextUtils.isEmpty(addrSecond)){
            mAddrSecondEt.setError(getString(R.string.error_field_required));
            focusView = mAddrSecondEt;
            cancel = true;
        } else if(TextUtils.isEmpty(addrThirt)){
            mAddrThirdEt.setError((getString(R.string.error_field_required)));
            focusView = mAddrThirdEt;
            cancel = true;
        } else if(TextUtils.isEmpty(postalCode)){
            mAddrFourthEt.setError(getString(R.string.error_field_required));
            focusView = mAddrFourthEt;
            cancel = true;
        } else if (TextUtils.isEmpty(email)) {
            mEmailEt.setError(getString(R.string.error_field_required));
            focusView = mEmailEt;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailEt.setError(getString(R.string.error_invalid_email));
            focusView = mEmailEt;
            cancel = true;
        } else if(TextUtils.isEmpty(reEmail)){
            mReEmailEt.setError(getString(R.string.error_field_required));
            focusView = mReEmailEt;
            cancel = true;
        } else if(!isEmailValid(reEmail)){
            mReEmailEt.setError(getString(R.string.error_invalid_email));
            focusView = mReEmailEt;
            cancel = true;
        } else if(!isReEmailCorrect(email,reEmail)){
            mReEmailEt.setError(getString(R.string.error_email_match));
            focusView = mReEmailEt;
            cancel = true;
        } else if(TextUtils.isEmpty(phoneS)){
            mPhoneEt.setError(getString(R.string.error_field_required));
            focusView = mPhoneEt;
            cancel = true;
        } else if(!isPhoneValid(phoneS)){
            mPhoneEt.setError(getString(R.string.error_invalid_phone));
            focusView = mPhoneEt;
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
            registerUser();

            //mAuthTask = new UserRegTask(email);
            //mAuthTask.execute((Void) null);
        }
    }

    private boolean isReEmailCorrect(String email, String reEmail){
        if(email.compareTo(reEmail) != 0){
            return false;
        }
        return true;
    }

    private boolean isPhoneValid(String phone){
        if(!TextUtils.isDigitsOnly(phone)){
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with more complex logic
        return email.contains("@");
    }

    // Method to attempt user registration.
    private void registerUser(){
        final int PASS_SIZE = getResources().getInteger(R.integer.db_pass_size);
        // Create a new parse user
        ParseUser user = new ParseUser();
        user.setUsername(mEmailEt.getText().toString()); // set the username
        user.setEmail(mEmailEt.getText().toString());    // set the email
        user.setPassword(getRandomString(PASS_SIZE));   // generate the password
        user.put(getString(R.string.db_firstName),mFirstNameEt.getText().toString());  // put first name
        if(mSecondNameEt.getText() != null){ // Check if the field is not empty
            if(!TextUtils.isEmpty(mSecondNameEt.getText().toString())){
                user.put(getString(R.string.db_secondName),mSecondNameEt.getText().toString()); // put second name
            }
        }
        user.put(getString(R.string.db_surname),mSurnameEt.getText().toString());         // put surname
        user.put(getString(R.string.db_addrOne),mAddrFirstEt.getText().toString());       // put addr one
        user.put(getString(R.string.db_addrTwo),mAddrSecondEt.getText().toString());      // put addr two
        if(mAddrThirdEt.getText() != null){
            if(!TextUtils.isEmpty(mAddrThirdEt.getText().toString())){
                user.put(getString(R.string.db_addrThree),mAddrThirdEt.getText().toString());  // put addr three
            }
        }
        user.put(getString(R.string.db_postalCode), mAddrFourthEt.getText().toString());
        user.put(getString(R.string.db_phoneNo),mPhoneEt.getText().toString());
        // TODO: Insert more data into database
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                showProgress(false);
                if(e == null){
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    moveOn();
                }else{
                    Toast.makeText(RegisterActivity.this, getString(R.string.error_reg_failed), Toast.LENGTH_SHORT).show();
                    Log.i("RA0001",String.valueOf(e.getCode()));
                    Log.i("RA0002",e.getLocalizedMessage());
                    Log.i("RA0003",e.getMessage());
                }
            }
        });



    }
    private void moveOn(){
        // TODO: Start another activity to scan the ID or to Enter Password + display generated password
    }

    private String getRandomString(final int sizeOfRandomString)
    {
        final String ALLOWED_CHARACTERS = getString(R.string.allowedPassword);
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
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

