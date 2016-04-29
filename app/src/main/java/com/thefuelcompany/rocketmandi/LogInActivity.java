package com.thefuelcompany.rocketmandi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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
    String createPasswordAtSignUp;
    String repeatPasswordAtSignUp;
    EditText createPasswordAtSignUpEditText;
    EditText repeatPasswordAtSignUpEditText;
    Button createAccountButtonAtSignUp;

    ViewFlipper LogInViewFlipper;
    Intent intent;
    RocketMandiModel rocketMandiModel;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        context = LogInActivity.this;
        setFlipper();
        setLogInButton();
        setSignUpTextViewAtLogIn();
    }

    private void setFlipper(){
        LogInViewFlipper = (ViewFlipper) findViewById(R.id.logIn_activity_view_flipper);
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

                String message4Phone = checkPhoneNumberAtLogIn(phoneNumberForLogIn);
                if(message4Phone.equalsIgnoreCase("true")){

                    if(checkInternetConnection()){
                        if(checkRegistrationOfPhoneInDatabase()){
                            if(checkPhoneAndPasswordCombination()){
                                logInUser(phoneNumberForLogIn , passwordForLogIn);
                            }else {
                                showErrorDialogue("गलत पासवर्ड" , "पासवर्ड गलत है। \n कृपया पुन: प्रयास करें");
                            }
                        }else {
                            showErrorDialogue("फोन नंबर पंजीकृत (registered) नहीं किया गया है" ,
                                    "फोन नंबर पंजीकृत नहीं है। \n कृपया साइन अप करो");
                        }
                    }else {
                        showErrorDialogue("कोई इंटरनेट कनेक्शन नहीं" ,"मोबाइल इंटरनेट कनेक्शन ठीक से काम नहीं कर रहा है।\n" +
                                "इंटरनेट कनेक्शन की जाँच करें और फिर से कोशिश करें");
                    }

                }else {
                    showErrorDialogue("गलत नंबर डालना" , message4Phone);
                }
                Toast.makeText(LogInActivity.this, phoneNumberForLogIn + " " + passwordForLogIn, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setEditTextAtLogIn(){
        enterPhoneNumberForLogInEditText = (EditText) findViewById(R.id.logIn_enter_phone_for_logIn);
        enterPasswordForLogInEditText = (EditText) findViewById(R.id.LogIn_Enter_Password_For_LogIn);
    }


    private void setPhoneAndPasswordAtLogIn(){
        phoneNumberForLogIn = enterPhoneNumberForLogInEditText.getText().toString();
        passwordForLogIn = enterPasswordForLogInEditText.getText().toString();
    }

    private String checkPhoneNumberAtLogIn(String phone){
        if(phone.isEmpty()){
            return "रिक्त स्थानों की पूर्ति करें";
        }else {
            if(!(phone.length() ==10)){
                return "कृप्या 10 अंकों का फ़ोन नंबर डालें";
            } else {
                char[] chars = phone.toCharArray();
                for(int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isDigit(c)){
                        // do nothing, its good
                    }else {
                        return "कृपया एक सही संख्या डालिये" ;
                    }
                }
                return "true";
            }

        }
    }

    private boolean checkRegistrationOfPhoneInDatabase(){
        // check from database
        return true;
    }

    private boolean checkInternetConnection(){
        // check internet connection
        return true;
    }

    private void showErrorDialogue(String title, String message){

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    private void logInUser(String phone , String password){
        // log in user
    }

    private boolean checkPhoneAndPasswordCombination(){
        return true;
    }




    /*****************************************************************
     * From here we will se the methods to let the user sign up
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

    /**
     * The method will set up the button on Sign Up screen. On Sign Up Screen the user will
     * put his/her name and phone number.
     * An automatic OTP will be sent to user when user press the Next button.
     * The user will be conveyed to OTP screen
     */
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
                        showErrorDialogue("गलत नंबर डालना" , message4Phone);
                    }
                }else {
                    showErrorDialogue("गलत नाम डालना", message4Name);
                }
            }
        });
    }
    // method will set up the fields at sign up page to enter name and number.
    private void setEditTextAtSignUp(){
        enterNameAtSignUpEditText = (EditText) findViewById(R.id.logIn_enter_name_at_sign_up_edit_text);
        enterPhoneNumberAtSignUpEditText = (EditText) findViewById(R.id.login_enter_phone_number_at_sign_up_edit_text);
    }

    // method will get the value of edit texts at sign up page after user click next button.
    private void setNameAndNumberAtSignUp(){
        fullNameAtSignUp = enterNameAtSignUpEditText.getText().toString();
        phoneNumberAtSignUP = enterPhoneNumberAtSignUpEditText.getText().toString();
    }


    private String checkName(String name) {

        if (name.isEmpty()) {
            return "रिक्त स्थानों की पूर्ति करें";
        } else {
            if((name.length() <4 || name.length() > 30)){
                return "कम से कम 4 शब्दों का नाम दर्ज करें और अधिक से अधिक 30 शब्दों में";
            }else {
                char[] chars = name.toCharArray();

                for (int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isLetter(c) || Character.isWhitespace(c)){
                        // do nothing
                    }else {
                        return "आप आपके नाम में केवल अंग्रेजी शब्दों का प्रयोग कर सकते हैं";
                    }
                }
            }

            return "true";
        }
    }

    private String checkPhone(String phone){
        if(phone.isEmpty()){
            return "रिक्त स्थानों की पूर्ति करें";
        }else {
            if(!(phone.length() ==10)){
                return "कृप्या 10 अंकों का फ़ोन नंबर डालें";
            } else {
                char[] chars = phone.toCharArray();
                for(int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isDigit(c)){
                        // do nothing
                    }else {
                        return "कृपया एक सही संख्या डालिये" ;
                    }
                }
                return "true";
            }

        }
    }


    // method will set up the back to log in text at sign up screen.
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
                    showErrorDialogue("गलत ओटीपी", message4OTP);
                }
            }
        });
    }

    // set otp at submit otp view
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

    /**
     * The method will send otp to the user
     */
    private void sendOTP(){
        // send otp
        Toast.makeText(LogInActivity.this, "OTP has been sent" , Toast.LENGTH_SHORT).show();
    }

    private String checkOTP(){
        // match otp sent and entered
        if(true){
            return "true";
        }else {
         return "आपके द्वारा दर्ज " +
                 "ओटीपी गलत है \n कृपया पुन: प्रयास करें।";
        }
    }

    private void setEnterAndRepeatPasswordForSignUpEditText(){
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
                       if(checkInternetConnection()){
                           createNewAccount();
                           startHomeActivity();
                       }else {
                           showErrorDialogue("कोई इंटरनेट कनेक्शन नहीं" ,"मोबाइल इंटरनेट कनेक्शन ठीक से काम नहीं कर रहा है।\n" +
                                   "इंटरनेट कनेक्शन की जाँच करें और फिर से कोशिश करें" );
                       }
                    }else {
                        showErrorDialogue("पासवर्ड नही मिल रहा" , message4MatchPassword);
                    }

                }else {
                    showErrorDialogue("अवैध पासवर्ड" , message4Password);
                }
            }
        });
    }

    private void setEnterAndRepeatPassword(){
        createPasswordAtSignUp = createPasswordAtSignUpEditText.getText().toString();
        repeatPasswordAtSignUp = repeatPasswordAtSignUpEditText.getText().toString();
    }

    private String checkPassword(){
        if(createPasswordAtSignUp.length()>4 && createPasswordAtSignUp.length()<12){
            return "true";
        }else {
            return "पासवर्ड कम से कम 4 शब्दों का होना चाहिए";
        }
    }

    private String checkPasswordMatch(){
        if(createPasswordAtSignUp.equalsIgnoreCase(repeatPasswordAtSignUp)){
            return "true";
        }else {
            return "पासवर्ड नही मिल रहा।\n" +
                    "कृपया पुन: प्रयास करें।";
        }
    }

    private void createNewAccount(){
        // update from here directly.
        Toast.makeText(context , "new account created" , Toast.LENGTH_SHORT).show();
    }

    private void startHomeActivity(){
        Intent homeActivityIntent = new Intent(LogInActivity.this, HomeActivity.class);
        startActivity(homeActivityIntent);
    }
}
