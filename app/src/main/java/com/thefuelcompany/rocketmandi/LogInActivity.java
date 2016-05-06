package com.thefuelcompany.rocketmandi;

/**
 * author
 * Create by Praduman on 30/04/2016
 * for Rocket Mandi
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInActivity extends AppCompatActivity {

    // Fields and Strings at Log In Page
    private EditText enterEmailForLogInEditText;
    private EditText enterPasswordForLogInEditText;
    private Button logInButton;
    private TextView signUpTextView;
    private String emailForLogIn;
    private String passwordForLogIn;
    private TextView forgotPasswordTextView;
    private String emailAtForgotPassword;

    //Fields and Strings at Sign Up Page One (Name and Number)
    private EditText enterNameAtSignUpEditText;
    private EditText enterPhoneNumberAtSignUpEditText;
    private String fullNameAtSignUp;
    private String phoneNumberAtSignUP;
    private Button nextToOTPButton;
    private TextView backToLogInAtSignUpTextView;

    // Fields and Strings at Sign Up Page Two (Submit OTP)
    private EditText logInOTPEditText;
    private Button submitOTPButton;
    private TextView backTextViewAtOTP;
    private TextView resendOTP;
    private String otpEntered;
    private String otpSent;

    // Fields and Strings at Sign Up Page Three (Email and Password)
    private String createPasswordAtSignUp;
    private String repeatPasswordAtSignUp;
    private String enterEmailAtSignUp;
    private EditText enterEmailAtSignUpEditText;
    private EditText createPasswordAtSignUpEditText;
    private EditText repeatPasswordAtSignUpEditText;
    private Button createAccountButtonAtSignUp;
    private String emailEncoded;

    // Others
    private ViewFlipper LogInViewFlipper;
    private Firebase myFirebaseRef;
    private String enterEmptyFieldsMessage;
    private String noInternetConnectionTitle;
    private String noInternetConnectionMessage;
    private RelativeLayout loadingPanelLayoutAtLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setErrorMessage();
        setFlipper();
        setLogInButton();
        setSignUpTextViewAtLogIn();
        setForgotPasswordTextView();
    }

    private void setErrorMessage(){
        myFirebaseRef = new Firebase("https://rocket-mandi.firebaseio.com/");
        enterEmptyFieldsMessage = "Please fill empty field(s) and try again";
        noInternetConnectionTitle = "No Internet Connection";
        noInternetConnectionMessage  ="Your internet is not working properly. \n Please try again";
        loadingPanelLayoutAtLogIn = (RelativeLayout) findViewById(R.id.loadingPanel);
        loadingPanelLayoutAtLogIn.setVisibility(View.GONE);
    }

    /**
     * The flipper include Log In Page and all three Sign Up Page.
     */
    private void setFlipper(){
        LogInViewFlipper = (ViewFlipper) findViewById(R.id.logIn_activity_view_flipper);
    }

    /**
     **********************************************************************************
     * From here all the elements of Log In Page and Action Listeners will set up
     * ********************************************************************************
     */

    private void setLogInButton(){

        logInButton = (Button) findViewById(R.id.LogIn_LogIn_Button);
        setEditTextAtLogIn();
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmailAndPasswordAtLogIn();
                String message4Email = checkEmail(emailForLogIn);
                if (message4Email.equalsIgnoreCase("true")) {

                    if (checkInternetConnection()) {
                        loadingPanelLayoutAtLogIn.setVisibility(View.VISIBLE);
                        logInUser(emailForLogIn, passwordForLogIn);
                    } else {
                        showErrorDialogue(noInternetConnectionTitle, noInternetConnectionMessage);
                    }

                } else {
                    showErrorDialogue("Incorrect entries", message4Email);
                }
            }
        });
    }


    private void setEditTextAtLogIn(){
        enterEmailForLogInEditText = (EditText) findViewById(R.id.logIn_enter_email_for_logIn);
        enterPasswordForLogInEditText = (EditText) findViewById(R.id.LogIn_Enter_Password_For_LogIn);
    }


    private void setEmailAndPasswordAtLogIn(){
        emailForLogIn = enterEmailForLogInEditText.getText().toString();
        passwordForLogIn = enterPasswordForLogInEditText.getText().toString().toLowerCase();
    }


    private void logInUser(String email , String password){

        myFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                emailEncoded = getEncodedEmail(emailForLogIn);
                startHomeActivityFromLogIn();
                LogInActivity.this.finish();
                loadingPanelLayoutAtLogIn.setVisibility(View.GONE);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                loadingPanelLayoutAtLogIn.setVisibility(View.GONE);
                showErrorDialogue("Incorrect Combination !!!", firebaseError.getMessage());
            }
        });

    }

    private void setForgotPasswordTextView(){
        forgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_text_view);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogueBox();
            }
        });

    }

    private void showDialogueBox(){
        final Dialog dialog = new Dialog(LogInActivity.this);

        dialog.setContentView(R.layout.forgot_password_dialogue);
        dialog.setTitle("Forgot Password");

        final EditText forgotPasswordEditText = (EditText) dialog.findViewById(R.id.enter_email_edit_text_at_forgot_password);

        Button btnSave          = (Button) dialog.findViewById(R.id.submit_button_at_forgot_password);
        Button btnCancel        = (Button) dialog.findViewById(R.id.cancel_button_at_forgot_password);
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAtForgotPassword = forgotPasswordEditText.getText().toString();
                String message4Email = checkEmail(emailAtForgotPassword);
                if (message4Email.equalsIgnoreCase("true")) {
                    dialog.setContentView(R.layout.waiting_circle_for_dialog);
                    resetPassword(emailAtForgotPassword, dialog);
                } else {
                    dialog.dismiss();
                    showErrorDialogue("Invalid Email", message4Email);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    /*****************************************************************
     * From here we will set the methods to let the user sign up
     * on the application and create a new account.
     * ****************************************************************
     */

    private void setSignUpTextViewAtLogIn(){
        signUpTextView = (TextView) findViewById(R.id.logIn_signUp_text_view);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNextToOTPButton();
                setBackToLogTextViewInAtSignUp();

                LogInActivity.this.LogInViewFlipper.showNext();
            }
        });
    }


    private void setNextToOTPButton(){
        nextToOTPButton = (Button) findViewById(R.id.login_to_OTP_next_button);
        nextToOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditTextAtSignUp();
                setNameAndNumberAtSignUp();
                String message4Name = checkName(fullNameAtSignUp);
                if(message4Name.equalsIgnoreCase("true")){
                    String message4Phone = checkPhone(phoneNumberAtSignUP);
                    if(message4Phone.equalsIgnoreCase("true")){
                        setSubmitOTPButton();
                        setBackTextViewAtOTPScreen();
                        setResendOTPTextView();
                        sendOTP();
                        LogInActivity.this.LogInViewFlipper.showNext();
                    }else {
                        showErrorDialogue("Invalid Phone Number", message4Phone);
                    }
                }else {
                    showErrorDialogue("Invalid Name", message4Name);
                }
            }
        });
    }

    private void setEditTextAtSignUp(){
        enterNameAtSignUpEditText = (EditText) findViewById(R.id.logIn_enter_name_at_sign_up_edit_text);
        enterPhoneNumberAtSignUpEditText = (EditText) findViewById(R.id.login_enter_phone_number_at_sign_up_edit_text);
    }

    private void setNameAndNumberAtSignUp(){
        fullNameAtSignUp = enterNameAtSignUpEditText.getText().toString();
        phoneNumberAtSignUP = enterPhoneNumberAtSignUpEditText.getText().toString();
    }


    private String checkName(String name) {

        if (name.isEmpty()) {
            return enterEmptyFieldsMessage;
        } else {
            if(name.length() <4 ){
                return "You name should be between 4 to 20 characters";
            }else {
                char[] chars = name.toCharArray();

                for (int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isLetter(c) || Character.isWhitespace(c)){
                    }else {
                        return "You can use only alphabets in your name";
                    }
                }
            }

            return "true";
        }
    }

    private String checkPhone(String phone){
        if(phone.isEmpty()){
            return enterEmptyFieldsMessage;
        }else {
            if(!(phone.length() ==10)){
                return "Please enter 10 digit phone number";
            } else {
                char[] chars = phone.toCharArray();
                for(int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isDigit(c)){
                    }else {
                        return "Phone number can only have digits" ;
                    }
                }
                return "true";
            }

        }
    }


    private void setBackToLogTextViewInAtSignUp(){
        backToLogInAtSignUpTextView = (TextView) findViewById(R.id.logIn_back_to_login_text_view_at_sign_up);
        backToLogInAtSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInActivity.this.LogInViewFlipper.showPrevious();
            }
        });
    }


    private void setSubmitOTPButton(){
        submitOTPButton = (Button) findViewById(R.id.login_submit_button);
        setOTPEditText();
        submitOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOTPEntered();
                String message4OTP = checkOTP();
                if (message4OTP.equalsIgnoreCase("true")) {
                    setEnterAndRepeatPasswordForSignUpEditText();
                    setCreateAccountTextView();
                    LogInActivity.this.LogInViewFlipper.showNext();
                } else {
                    showErrorDialogue("Wrong OTP", message4OTP);
                }
            }
        });
    }

    private void setOTPEditText(){
        logInOTPEditText = (EditText) findViewById(R.id.login_OTP_edit_text);
        logInOTPEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void setOTPEntered(){
        otpEntered = logInOTPEditText.getText().toString();
    }

    private void setBackTextViewAtOTPScreen(){
        backTextViewAtOTP = (TextView) findViewById(R.id.login_back_text_view);
        backTextViewAtOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInActivity.this.LogInViewFlipper.showPrevious();
            }
        });
    }

    private void setResendOTPTextView(){
        resendOTP = (TextView) findViewById(R.id.logIn_send_again_text_view);
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP();
            }
        });
    }



    private void setEnterAndRepeatPasswordForSignUpEditText(){
        enterEmailAtSignUpEditText = (EditText) findViewById(R.id.login_sign_up_enter_email_edit_text);
        createPasswordAtSignUpEditText = (EditText) findViewById(R.id.login_signup_create_password_edit_text);
        repeatPasswordAtSignUpEditText = (EditText) findViewById(R.id.login_signup_repeat_password_edit_text);

    }

    private void setCreateAccountTextView(){
        createAccountButtonAtSignUp = (Button) findViewById(R.id.login_sign_up_create_account_button);
        createAccountButtonAtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnterAndRepeatPassword();
                String message4Password = checkPassword();
                if(message4Password.equalsIgnoreCase("true")){
                    String message4MatchPassword = checkPasswordMatch();
                    if(message4MatchPassword.equalsIgnoreCase("true")){
                        String check4Email = checkEmail(enterEmailAtSignUp);
                        if(check4Email.equalsIgnoreCase("true")){
                            if(checkInternetConnection()){
                                loadingPanelLayoutAtLogIn.setVisibility(View.VISIBLE);
                                emailEncoded = getEncodedEmail(enterEmailAtSignUp);
                                createNewAccount();
                            }else {
                                showErrorDialogue(noInternetConnectionTitle ,noInternetConnectionMessage );
                            }
                        }else {
                            showErrorDialogue("Invalid Email" , check4Email);
                        }

                    }else {
                        showErrorDialogue("Password do not match" , message4MatchPassword);
                    }

                }else {
                    showErrorDialogue("Invalid Password" , message4Password);
                }
            }
        });
    }

    private void setEnterAndRepeatPassword(){
        enterEmailAtSignUp = enterEmailAtSignUpEditText.getText().toString();
        createPasswordAtSignUp = createPasswordAtSignUpEditText.getText().toString().toLowerCase();
        repeatPasswordAtSignUp = repeatPasswordAtSignUpEditText.getText().toString().toLowerCase();
    }

    private String checkEmail(String email){

        if(email.length() == 0){
            return enterEmptyFieldsMessage;
        }else {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            CharSequence inputStr = email;

            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                return "true";
            }
            return "Please provide a valid email address. \n It cab be used to reset password and other communication";
        }

    }

    private String checkPassword(){
        if(createPasswordAtSignUp.length()>3){
            return "true";
        }else {
            return "Password should have at least 4 characters";
        }
    }

    private String checkPasswordMatch(){
        if(createPasswordAtSignUp.equalsIgnoreCase(repeatPasswordAtSignUp)){
            return "true";
        }else {
            return "Passwords do not match. \n Please try again.";
        }
    }

    private void createNewAccount(){

        createNewAccountInFirebase(enterEmailAtSignUp, createPasswordAtSignUp);
    }

    private void startHomeActivityFromSignUp(){
        Intent homeActivityIntent = new Intent(LogInActivity.this, HomeActivity.class);
        homeActivityIntent.putExtra("email" , enterEmailAtSignUp);
        homeActivityIntent.putExtra("emailEncoded" , emailEncoded);
        startActivity(homeActivityIntent);
    }

    private void startHomeActivityFromLogIn(){
        Intent homeActivityIntent = new Intent(LogInActivity.this, HomeActivity.class);
        homeActivityIntent.putExtra("email" , emailForLogIn);
        homeActivityIntent.putExtra("emailEncoded" , emailEncoded);
        startActivity(homeActivityIntent);
    }

    /**
     * ********************************************************************************
     * Firebase methods to authenticate user and create user
     * ********************************************************************************
     */

    private void createNewAccountInFirebase(final String email , String password){
        myFirebaseRef.createUser(email
                , password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                myFirebaseRef.child("users").child(emailEncoded).child("fullName").setValue(fullNameAtSignUp);
                myFirebaseRef.child("users").child(emailEncoded).child("email").setValue(enterEmailAtSignUp);
                myFirebaseRef.child("users").child(emailEncoded).child("phone").setValue(phoneNumberAtSignUP);
                myFirebaseRef.child("users").child(emailEncoded).child("positionOfPickUpArea").setValue(0);
                myFirebaseRef.child("users").child(emailEncoded).child("positionOfPickUpLocation").setValue(0);
                startHomeActivityFromSignUp();
                LogInActivity.this.finish();
                loadingPanelLayoutAtLogIn.setVisibility(View.GONE);
                Toast.makeText(LogInActivity.this, "Welcome to Rocket Mandi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                loadingPanelLayoutAtLogIn.setVisibility(View.GONE);
                showErrorDialogue("Error", firebaseError.getMessage());
            }
        });
    }

    private void resetPassword(String email, final Dialog dialog){
        myFirebaseRef.resetPassword(email, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                dialog.dismiss();
                showErrorDialogue("Email Sent", "A new password has been sent to your email id. \n Please check your email.");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                dialog.dismiss();
                showErrorDialogue("OOPS..!!!", firebaseError.getMessage().toString());
            }
        });
    }

    /**
     * **********************************************************************************
     * Common methods used by Log In and Sign Up elements
     * **********************************************************************************
     */

    private boolean checkInternetConnection(){

        return true;

    }

    private void showErrorDialogue(String title, String message){

        new AlertDialog.Builder(LogInActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    private String getEncodedEmail(String email){
        emailEncoded = email;
        emailEncoded = emailEncoded.replace(".", "DOT");
        emailEncoded = emailEncoded.replace("#", "HASH");
        emailEncoded = emailEncoded.replace("$" , "DOLLAR");
        return emailEncoded;
    }


    /**
     * **********************************************************************************
     * All the methods to send otp will be kept below here of the sendOTP.msg91 API.
     * **********************************************************************************
     */

    private void sendOTP(){

    }

    private String checkOTP(){
        // compate otp entered and sent
        if(true){
            return "true";
        }else {
            return "OTP entered by you is incorrect. \n  Please try again";
        }
    }


    
}
