package com.thefuelcompany.rocketmandi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.msg91.sendotp.library.Config;
import com.msg91.sendotp.library.SendOtpVerification;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by pradumanpraduman on 20/04/16.
 */
public class MyAccount implements  ActivityCompat.OnRequestPermissionsResultCallback, VerificationListener {

    // Required by Constructor
    private TextView nameTextViewInAccount;
    private TextView phoneTextViewInAccount;
    private ImageView editNameImageViewInAccount;
    private ImageView editNumberImageViewInAccount;
    private TextView emailTextViewInAccount;
    private TextView passwordTextViewInAccount;
    private ImageView editEmailImageViewInAccount;
    private String email;
    private String emailDecoded;
    private Spinner pickUpAreaSpinner;
    private Spinner pickUpLocationSpinner;

    private RocketMandiModel modelObject;
    private Context context;

    // Others
    private String newName;
    private String newPhone;
    private String newEmail;
    private EditText editTextOldPasswordAtChangePassword;
    private EditText editTextNewPasswordAtChangePassword;
    private EditText editTextRepeatPasswordAtChangePassword;
    private String[] pickUpAreaArray;
    private String[] pickUpLocationArray;
    private ArrayAdapter<String> pickUpAreaArrayAdapter;
    private ArrayAdapter<String> pickUpLocationArrayAdapter;
    private int previousPositionOfAreaSelected;
    private int previousPositionOfPickUpLocationSelected;
    private String pickUpAreaSelected;
    private String pickUpLocationSelected;
    private Firebase myFirebaseRef;
    private String fillEmptyFieldsMessage;

    private EditText editTextForNewNamePopUp;
    private EditText editTextForNewNumberPopUp;
    private EditText editTextOldPassword;
    private EditText editTextNewEmail;

    private static final String TAG = Verification.class.getSimpleName();
    private String otpReturnMessage;
    private Verification otpVerification;
    private String countryCode;
    private String otpEntered;


    public MyAccount(TextView nameTextViewInAccount, TextView phoneTextViewInAccount,ImageView editNameImageViewInAccount,
                     ImageView editNumberImageViewInAccount, TextView emailTextViewInAccount, TextView passwordTextViewInAccount,
                     ImageView editEmailImageViewInAccount, Spinner pickUpAreaSpinner , Spinner pickUpLocationSpinner,
                     RocketMandiModel modelObject, Context context, String email){

        this.nameTextViewInAccount = nameTextViewInAccount;
        this.phoneTextViewInAccount = phoneTextViewInAccount;
        this.editNameImageViewInAccount = editNameImageViewInAccount;
        this.editNumberImageViewInAccount = editNumberImageViewInAccount;
        this.emailTextViewInAccount = emailTextViewInAccount;
        this.passwordTextViewInAccount = passwordTextViewInAccount;
        this.editEmailImageViewInAccount = editEmailImageViewInAccount;
        this.pickUpAreaSpinner = pickUpAreaSpinner;
        this.pickUpLocationSpinner = pickUpLocationSpinner;
        this.modelObject = modelObject;
        this.context = context;
        this.email = email;
        myFirebaseRef = new Firebase("https://rocket-mandi.firebaseio.com/");
        fillEmptyFieldsMessage = "Please fill empty fields \n and try again";
        countryCode = "91";
        setUpNameAndPhoneTextView();
        setUpEmailAndPasswordTextView();
        setUpSpinners();
    }


    /**
     * ********************************************************************************
     * Setting up Name and Number Text Views and OnClick listeners for Image Views
     * of the same.
     * ********************************************************************************
     */
    private void  setUpNameAndPhoneTextView(){
        nameTextViewInAccount.setText(modelObject.getFullName());
        phoneTextViewInAccount.setText(modelObject.getPhoneNumber());

        editNameImageViewInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNameImageOnClick();
            }
        });

        editNumberImageViewInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNumberOnClick();
            }
        });
    }

    private void editNameImageOnClick(){
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.change_customer_name);
        dialog.setTitle("Change Name");

        editTextForNewNamePopUp = (EditText) dialog.findViewById(R.id.enter_new_name_edit_text_at_change_customer_name);
        Button btnSave          = (Button) dialog.findViewById(R.id.save_new_name_button_at_change_customer_name);
        Button btnCancel        = (Button) dialog.findViewById(R.id.cancel_button_at_change_customer_name);
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSaveButtonForEditNameOnClick(dialog);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void setSaveButtonForEditNameOnClick(Dialog dialog){
        newName = editTextForNewNamePopUp.getText().toString();
        String message4Name = checkName(newName);
        if(message4Name.equalsIgnoreCase("true")){
           dialog.setContentView(R.layout.waiting_circle_for_dialog);
            modelObject.setNewFullName(newName);
            nameTextViewInAccount.setText(newName);
            dialog.dismiss();
        }else {
            dialog.dismiss();
            showErrorDialogue("OOPS!!!", message4Name);
        }
    }

    private void editNumberOnClick(){
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.change_customer_number_dialogue);
        dialog.setTitle("Change Number");

        editTextForNewNumberPopUp = (EditText) dialog.findViewById(R.id.enter_new_number_edit_text_at_change_customer_number);
        Button btnSave          = (Button) dialog.findViewById(R.id.save_new_number_button_in_change_customer_number);
        Button btnCancel        = (Button) dialog.findViewById(R.id.cancel_button_at_change_customer_number_dialogue);
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSaveButtonForEditNumberOnClick(dialog);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void setSaveButtonForEditNumberOnClick(Dialog dialog){
        newPhone = editTextForNewNumberPopUp.getText().toString();
        String message4Phone = checkPhone(newPhone);
        if (message4Phone.equalsIgnoreCase("true")){
            dialog.setContentView(R.layout.waiting_circle_for_dialog);
             sendOTP();
             dialog.dismiss();
        }else {
            dialog.dismiss();
            showErrorDialogue("Invalid Number" , message4Phone);
        }
    }


    /**
     * ********************************************************************************
     * Setting up Email and Passwords Text Views and OnClick listeners for Image Views
     * of the same.
     * ********************************************************************************
     */

    private void setUpEmailAndPasswordTextView(){
        emailTextViewInAccount.setText(modelObject.getEmail());

        editEmailImageViewInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmailOnClick();
            }
        });

        passwordTextViewInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordOnClick();

            }
        });
    }

    private void editEmailOnClick(){
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.change_customer_email_dialogue);
        dialog.setTitle("Change Email");

        editTextOldPassword = (EditText) dialog.findViewById(R.id.enter_old_password_edit_text_at_change_customer_email);
        editTextNewEmail = (EditText) dialog.findViewById(R.id.enter_new_email_edit_text_at_change_customer_email);

        Button btnSave          = (Button) dialog.findViewById(R.id.save_new_email_button_in_change_customer_email);
        Button btnCancel        = (Button) dialog.findViewById(R.id.cancel_button_at_change_email_dialogue);
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSaveButtonForEditEmail(dialog);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void setSaveButtonForEditEmail(Dialog dialog){
        newEmail = editTextNewEmail.getText().toString();
        String message4Email = checkEmail(newEmail);
        String password = editTextOldPassword.getText().toString();

        if(message4Email.equalsIgnoreCase("true")){
            if(!(password.isEmpty())){
                dialog.setContentView(R.layout.waiting_circle_for_dialog);
                modelObject.setNewEmail(email, newEmail, password, emailDecoded);
                emailTextViewInAccount.setText(newEmail);
                dialog.dismiss();
            }else {
                dialog.dismiss();
                showErrorDialogue("Invalid Password", fillEmptyFieldsMessage);
            }

        }else {
            dialog.dismiss();
            showErrorDialogue("Invalid Email", message4Email);
        }
    }


    private void changePasswordOnClick(){
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.change_customer_password_dialogue);
        dialog.setTitle("Change Password");

         editTextOldPasswordAtChangePassword = (EditText) dialog.findViewById(R.id.enter_old_password_edit_text_at_change_customer_password);
         editTextNewPasswordAtChangePassword = (EditText) dialog.findViewById(R.id.enter_new_password_edit_text_at_change_customer_password);
         editTextRepeatPasswordAtChangePassword = (EditText) dialog.findViewById(R.id.enter_repeat_password_edit_text_at_change_customer_password);

        Button btnSave          = (Button) dialog.findViewById(R.id.save_new_password_button_in_change_customer_password);
        Button btnCancel        = (Button) dialog.findViewById(R.id.cancel_button_at_change_password_dialogue);
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSaveButtonForEditPassword(dialog);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void setSaveButtonForEditPassword(Dialog dialog){
        String newPassword = editTextNewPasswordAtChangePassword.getText().toString();
        String repeatPassword = editTextRepeatPasswordAtChangePassword.getText().toString();
        String oldPassword = editTextOldPasswordAtChangePassword.getText().toString();

        if(!(newPassword.isEmpty() || repeatPassword.isEmpty())){
            if(newPassword.equalsIgnoreCase(repeatPassword)){
                dialog.setContentView(R.layout.waiting_circle_for_dialog);
                modelObject.setNewPassword(email, oldPassword, newPassword);
                dialog.dismiss();
            }else {
                dialog.dismiss();
                showErrorDialogue("Passwords do not match" , "New password and repeat password do not match. \n" +
                        "Please try again");
            }
        }else {
            dialog.dismiss();
            showErrorDialogue("Invalid Password", fillEmptyFieldsMessage);
        }
    }



    /**
     * ********************************************************************************
     * Setting up spinners for the area and location of delivery.
     * ********************************************************************************
     */

    private void setUpSpinners(){
        setPickUpAreaSpinnerArray();
        setPickUpLocationSpinnerArray();
    }

    private void setPickUpAreaSpinnerArray(){
        setPickUpAreaArray();
        setPickUpAreaAdapter();
        pickUpAreaSpinner.setAdapter(pickUpAreaArrayAdapter);
        pickUpAreaSpinner.setSelection(previousPositionOfAreaSelected);
        setOnItemSelectedListenerForPickUpArea();
    }

    private void setPickUpAreaArray(){
        pickUpAreaArray = modelObject.getPickUpAreaArray();
    }

    private void setPickUpAreaAdapter(){
        pickUpAreaArrayAdapter  = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, pickUpAreaArray);
        previousPositionOfAreaSelected = modelObject.getPreviousPositionOfPickUpAreaSelected();
    }

    private void setOnItemSelectedListenerForPickUpArea(){
        pickUpAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.waiting_circle_for_dialog);
                dialog.show();
                setNewPositionOfPickUpArea(position);
                setNewPickUpArea(position);
                setNewPositionOfDeliveryLocation(0);
                setPickUpLocationSpinnerArray();
                dialog.dismiss();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }

    private void setNewPositionOfPickUpArea(int position){
        modelObject.setNewPositionOfPickUpAreaSelected(position);
    }

    private void setNewPickUpArea(int position){
        pickUpAreaSelected = pickUpAreaSpinner.getItemAtPosition(position).toString();
        modelObject.setPickUpAreaSelected(pickUpAreaSelected);
        modelObject.setDeliveryLocationArray();
    }




   private void setPickUpLocationSpinnerArray(){
        setPickUpLocationArray();
        setDeliveryLocationAdapterAndPreviousPositionOfLocation();
        pickUpLocationSpinner.setAdapter(pickUpLocationArrayAdapter);
        pickUpLocationSpinner.setSelection(previousPositionOfPickUpLocationSelected);
        setOnItemSelectedListenerForDeliveryLocationSpinnerInAccount();
    }

    private void setPickUpLocationArray(){
        pickUpLocationArray = modelObject.getDeliveryLocationArray();
    }

    private void setDeliveryLocationAdapterAndPreviousPositionOfLocation(){
       pickUpLocationArrayAdapter  = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, pickUpLocationArray);
        previousPositionOfPickUpLocationSelected = modelObject.getPreviousPositionOfDeliveryLocationSelected();
    }

    private void setOnItemSelectedListenerForDeliveryLocationSpinnerInAccount(){
        pickUpLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.waiting_circle_for_dialog);
                dialog.show();
                setNewPositionOfDeliveryLocation(position);
                setNewPickUpLocation(position);
                dialog.dismiss();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }

    private void setNewPositionOfDeliveryLocation(int position){
        modelObject.setNewPositionOfDeliveryLocation(position);
    }

    private void setNewPickUpLocation(int position){
        pickUpLocationSelected = pickUpLocationSpinner.getItemAtPosition(position).toString();
        modelObject.setNewPickUpLocation(pickUpLocationSelected);
    }


    /**
     * ********************************************************************************
     *  All methods to check name , number etc. and dialogue box to show error.
     *  OTP sent and checked form here.
     *  ********************************************************************************
     */


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


    private String checkPhone(String phone){
        if(phone.isEmpty()){
            return fillEmptyFieldsMessage;
        }else {
            if(!(phone.length() ==10)){
                return "Please enter 10 digit phone number";
            } else {
                char[] chars = phone.toCharArray();
                for(int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isDigit(c)){
                        // do nothing
                    }else {
                        return "Please enter a valid number";
                    }
                }
                return "true";
            }

        }

    }

    private String checkName(String name) {

        if (name.isEmpty()) {
            return fillEmptyFieldsMessage;
        } else {
            if((name.length() <4)){
                return "Please enter name of minimum 4 words";
            }else {
                char[] chars = name.toCharArray();

                for (int i=0; i<chars.length; i++){
                    char c = chars[i];
                    if(Character.isLetter(c) || Character.isWhitespace(c)){
                        // do nothing
                    }else {
                        return "You can only use english alphabets in you name";
                    }
                }
            }

            return "true";
        }
    }

    private String checkEmail(String email){
        if(email.length() == 0){
            return fillEmptyFieldsMessage;
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

    private void sendOTP(){
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.forgot_password_dialogue);
        dialog.setTitle("Sending OTP");
        dialog.show();
        Config config = SendOtpVerification.config().context(context)
                .build();
        otpVerification = SendOtpVerification.createSmsVerification(config, newPhone, this, countryCode);
        otpVerification.initiate();
        dialog.dismiss();

        showDialogToEnterOTP();

    }

    private void showDialogToEnterOTP(){

        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.submit_otp_dialogue);
        dialog.setTitle("Enter OTP");

        final EditText otpEditText = (EditText) dialog.findViewById(R.id.enter_OTP_edit_text_at_enter_OTP_dialogue);

        Button btnSave          = (Button) dialog.findViewById(R.id.submit_OTP_button_at_submit_OTP_dialogue);
        Button btnCancel        = (Button) dialog.findViewById(R.id.cancel_OTP_button_at_submit_OTP_dialogue);
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpEntered = otpEditText.getText().toString();
                if (!(otpEntered.isEmpty())) {
                    dialog.dismiss();
                    String message4OTP = checkOTPCodeEntered();
                    Toast.makeText(context , message4OTP+ "  :::::"  , Toast.LENGTH_SHORT).show();
                 /**   if (message4OTP.equalsIgnoreCase("true")) {
                        dialog.setContentView(R.layout.waiting_circle_for_dialog);
                        modelObject.setNewPhoneNumber(newPhone);
                        phoneTextViewInAccount.setText(newPhone);
                        dialog.dismiss();
                    } else {
                        showErrorDialogue("Wrong OTP", message4OTP);
                    }*/

                } else {
                    showErrorDialogue("Invalid OTP", "Please enter OTP sent to you");
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


    private String checkOTPCodeEntered(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.waiting_circle_for_dialog);
        dialog.setTitle("Checking OTP");
        otpVerification.verify(otpEntered);
        dialog.dismiss();
        return otpReturnMessage;
    }

    @Override
    public void onInitiated(String response) {
        Log.d(TAG, "Initialized!");
    }

    @Override
    public void onInitiationFailed(Exception paramException) {
        Log.e(TAG, "Verification initialization failed: " + paramException.getMessage());

    }

    @Override
    public void onVerified(String response) {
        Log.d(TAG, "Verified!\n" + response);
        otpReturnMessage = "true";
        Toast.makeText(context , "Thank you :-) You Phone number is verified now" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVerificationFailed(Exception paramException) {
        Log.e(TAG, "Verification failed: " + paramException.getMessage());
        otpReturnMessage = "Please, enter otp again OR. \n Check your internet connection.";
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(Activity,
     * String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(
     *Activity, String[], int)}
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(Activity, String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        otpReturnMessage = "SHIT";
        Toast.makeText(context, "Something happened here" , Toast.LENGTH_SHORT).show();
    }
}
