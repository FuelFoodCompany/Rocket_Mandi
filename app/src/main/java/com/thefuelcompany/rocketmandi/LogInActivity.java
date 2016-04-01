package com.thefuelcompany.rocketmandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class LogInActivity extends AppCompatActivity {

    EditText enterPhoneNumberForLogInEditText;
    EditText enterPasswordForLogInEditText;
    Button logInButton;
    TextView signUpTextView;
    String phoneNumberForLogIn;
    String passwordForLogIn;
    EditText enterNameAtSignUpEditText;
    EditText enterPhoneNumberAtSignUpEditText;
    String fullNameAtSignUp;
    String phoneNumberAtSignUP;
    Button nextToOTPButton;
    TextView backToLogInAtSignUpTextView;
    EditText logInOTPEditText;
    Button submitOTPButton;
    TextView backTextViewAtOTP;
    TextView resendOTP;
    String otpEntered;
    String otpSent;
    String passwordAtSignUp;
    String repeatPasswordAtSignUp;

    ViewFlipper LogInViewFlipper;
    Intent intent;
    RocketMandiModel rocketMandiModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setFlipper();
        setLogInButton();
        setSignUpTextViewAtLogIn();
        startHomeActivity();
        
    }

    private void setFlipper(){
        LogInViewFlipper = (ViewFlipper) findViewById(R.id.LogIn_Acitivity_View_Flipper);
    }

    /**
     * The method will set up the Log In button on the Log In screen. If the user has already an
     * acocunt he/she will be directed to home screen of the application.
     * Else he/she can choose to sign up.
     */
    private void setLogInButton(){

        logInButton = (Button) findViewById(R.id.LogIn_LogIn_Button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditTextAtLogIn();
                setPhoneAndPasswordAtLogIn();
                Toast.makeText(LogInActivity.this, phoneNumberForLogIn + " " + passwordForLogIn, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setSignUpTextViewAtLogIn(){
        signUpTextView = (TextView) findViewById(R.id.LogIn_SignUp_Text_View);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNextToOTPButton();
                setBackToLogTextViewInAtSignUp();

                LogInActivity.this.LogInViewFlipper.showNext();
            }
        });
    }

    private void setEditTextAtLogIn(){
        enterPhoneNumberForLogInEditText = (EditText) findViewById(R.id.LogIn_Enter_Phone_For_LogIn);
        enterPasswordForLogInEditText = (EditText) findViewById(R.id.LogIn_Enter_Password_For_LogIn);
    }

    private void setPhoneAndPasswordAtLogIn(){
        phoneNumberForLogIn = enterPhoneNumberForLogInEditText.getText().toString();
        passwordForLogIn = enterPasswordForLogInEditText.getText().toString();
    }


    /**
     * The method will set up the button on Sign Up screen. On Sign Up Screen the user will
     * put his/her name and phone number.
     * An automatic OTP will be sent to user when user press the Next button.
     * The user will be conveyed to OTP screen
     */
    private void setNextToOTPButton(){
        nextToOTPButton = (Button) findViewById(R.id.LogIn_To_OTP_Next_Button);
        nextToOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditTextAtSignUp();
                setNameAndNumberAtSignUp();
                setSubmitOTPButton();
                setBackTextViewAtOTPScreen();
                setResendOTPTextView();
                sendOTP();
                Toast.makeText(LogInActivity.this,fullNameAtSignUp+ "  "+ phoneNumberAtSignUP, Toast.LENGTH_SHORT).show();
                LogInActivity.this.LogInViewFlipper.showNext();
            }
        });
    }

    private void setEditTextAtSignUp(){
        enterNameAtSignUpEditText = (EditText) findViewById(R.id.LogIn_Enter_Name_At_Sign_Up_Edit_Text);
        enterPhoneNumberAtSignUpEditText = (EditText) findViewById(R.id.Login_Enter_Phone_Number_At_Sign_Up_Edit_Text);
    }

    private void setNameAndNumberAtSignUp(){
        fullNameAtSignUp = enterNameAtSignUpEditText.getText().toString();
        phoneNumberAtSignUP = enterPhoneNumberAtSignUpEditText.getText().toString();
    }

    private void setBackToLogTextViewInAtSignUp(){
        backToLogInAtSignUpTextView = (TextView) findViewById(R.id.LogIn_Back_To_LogIn_Text_View_At_Sign_Up);
        backToLogInAtSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInActivity.this.LogInViewFlipper.showPrevious();
            }
        });
    }

    private void setSubmitOTPButton(){
        submitOTPButton = (Button) findViewById(R.id.LogIn_Submit_Button);
        submitOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOTPEditText();
                setOTPEntered();

                if (checkOTP()){
                    Toast.makeText(LogInActivity.this, otpEntered, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LogInActivity.this, "OOPS: The OTP you enetered is wrong\n" + "आपके द्वारा दर्ज किया हुआ OPp गलत है" , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setOTPEditText(){
        logInOTPEditText = (EditText) findViewById(R.id.LogIn_OTP_Edit_Text);
    }

    private void setOTPEntered(){
        otpEntered = logInOTPEditText.getText().toString();
    }

    private void setBackTextViewAtOTPScreen(){
        backTextViewAtOTP = (TextView) findViewById(R.id.LogIn_Back_Text_View);
        backTextViewAtOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInActivity.this.LogInViewFlipper.showPrevious();
            }
        });
    }

    private void setResendOTPTextView(){
        resendOTP = (TextView) findViewById(R.id.LogIn_Send_Again_Text_View);
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP();
            }
        });
    }

    /**
     * The method will send otp to the user
     */
    private void sendOTP(){
        Toast.makeText(LogInActivity.this, "OTP has been sent" , Toast.LENGTH_SHORT).show();
    }

    private boolean checkOTP(){
        return false;
    }


    private void startHomeActivity(){
        Intent homeActivityIntent = new Intent(LogInActivity.this, HomeActivity.class);
        startActivity(homeActivityIntent);
    }
}
