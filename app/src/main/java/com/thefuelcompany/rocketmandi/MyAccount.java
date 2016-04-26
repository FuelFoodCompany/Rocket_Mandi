package com.thefuelcompany.rocketmandi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by pradumanpraduman on 20/04/16.
 */
public class MyAccount {
    private TextView editInMyAccount;
    private EditText nameTextViewInAccount;
    private EditText phoneTextViewInAccount;
    private TextView deliveryLocationTextViewInAccount;
    private Spinner deliveryLocationSpinnerInAccount;
    private String newName;
    private String newPhone;
    private RocketMandiModel modelObject;
    private Context context;
    private String[] deliveryLocationArray;
    private ArrayAdapter<String> deliveryLocationArrayAdapter;
    private int previousPositionOfDeliveryLocationSelected;

    public MyAccount(TextView editInMyAccount, EditText nameTextViewInAccount, EditText phone,
                     TextView deliveryLocationTextViewInAccount, Spinner deliveryLocationSpinnerInAccount,
                     RocketMandiModel modelObject, Context context){

        this.editInMyAccount = editInMyAccount;
        this.nameTextViewInAccount = nameTextViewInAccount;
        this.phoneTextViewInAccount = phone;
        this.deliveryLocationTextViewInAccount = deliveryLocationTextViewInAccount;
        this.deliveryLocationSpinnerInAccount = deliveryLocationSpinnerInAccount;
        this.modelObject = modelObject;
        this.context = context;
        setUpNameAndPhone();
        setUpEditInMyAccountActionListener();
        setUpDeliveryLocationSpinnerArray();
    }

    /**
     * the method will set up the edit text in my account.
     * On clicking the edit text, the EditViews of Name and Phone will become editable.
     * And the text will change to Done.
     * When done is clicked, the data in the Name and Phone edit views will be updated to database.
     */

    private void setUpEditInMyAccountActionListener() {

            editInMyAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editInMyAccount.getText().equals("EDIT")) {
                        // this code will be called when edit is clicked
                        nameTextViewInAccount.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                        // change colour
                        phoneTextViewInAccount.setInputType(InputType.TYPE_CLASS_NUMBER);
                        // changes colour
                        editInMyAccount.setText("Save Changes");
                    } else {
                        // this code will be called when save changes is clicked
                        newName = nameTextViewInAccount.getText().toString().trim();
                        String message = checkName(newName);
                        if (message.equalsIgnoreCase("true")) {
                            newPhone = phoneTextViewInAccount.getText().toString().trim();
                            String message4Phone = checkPhone(newPhone);
                            if (message4Phone.equalsIgnoreCase("true")) {
                                proceedToSaveNewNameAndPhone(newName, newPhone);
                            } else {
                                showDialogueBoxForInCorrectPhone(message4Phone);
                            }
                        } else {
                            showDialogueBoxForInCorrectName(message);
                        }
                    }
                }
            });

    }



    /**
     * the method will get the data from model class to
     * set up name and phone number of user.
     */
    private void  setUpNameAndPhone(){
        nameTextViewInAccount.setText(modelObject.getFullName());
        nameTextViewInAccount.setInputType(InputType.TYPE_NULL);
        phoneTextViewInAccount.setText(modelObject.getPhoneNumber());
        phoneTextViewInAccount.setInputType(InputType.TYPE_NULL);
    }


    /**
     * The method will check the string passed into it,
     * if it is a proper name or not. without any number or special character
     * else , pop up will be shown saying entry was incorrect.
     * @param name
     * @return
     */
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

    private void showDialogueBoxForInCorrectName(String message){
        new AlertDialog.Builder(context)
                .setTitle("गलत नाम डालना")
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

    private void showDialogueBoxForInCorrectPhone(String message){
        new AlertDialog.Builder(context)
                .setTitle("गलत नंबर डालना")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    private void proceedToSaveNewNameAndPhone(String newName , String newPhone){
        if(modelObject.matchNewPhoneWithOld(newPhone)){
            modelObject.setNewFullName(newName);
            // change colours
            nameTextViewInAccount.setInputType(InputType.TYPE_NULL);
            phoneTextViewInAccount.setInputType(InputType.TYPE_NULL);
            editInMyAccount.setText("EDIT");
        }else {
            sendOTP();
            String otpMessage = checkOTPCodeEntered();
            if(otpMessage.equalsIgnoreCase("true")){
               modelObject.setNewFullName(newName);
                modelObject.setNewPhoneNumber(newPhone);
                // change colours
                nameTextViewInAccount.setInputType(InputType.TYPE_NULL);
                phoneTextViewInAccount.setInputType(InputType.TYPE_NULL);
                  editInMyAccount.setText("EDIT");
            }else {
                showDialogueBoxForInCorrectOTP(otpMessage);
            }
        }
    }

    private void sendOTP(){
        // send it to newPhone
        // popup window to enter otp
        // get otp entered
    }

    private String checkOTPCodeEntered(){
        // match with otp entered and otp sent
        if(true){
            return "true";
        }else {
            return "आपके द्वारा दर्ज किया ओटीपी  (OTP)  गलत है" + "| कृपया पुन: प्रयास करें| "+ "\n" +
                    "कृप्या  पुनः save changes दबाएँ ";
        }

    }

    private void showDialogueBoxForInCorrectOTP(String message){
        new AlertDialog.Builder(context)
                .setTitle("गलत ओटीपी डालना")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    private void setUpDeliveryLocationSpinnerArray(){
        setDeliveryLocationArray();
        setDeliveryLocationAdapterAndPreviousPositionOfLocation();
        deliveryLocationSpinnerInAccount.setAdapter(deliveryLocationArrayAdapter);
        deliveryLocationSpinnerInAccount.setSelection(previousPositionOfDeliveryLocationSelected);
        setOnItemSelectedListenerForDeliveryLocationSpinnerInAccount();
    }

    private void setDeliveryLocationArray(){
        // get from database
        deliveryLocationArray = modelObject.getDeliveryLocationArray();
    }

    private void setDeliveryLocationAdapterAndPreviousPositionOfLocation(){
       deliveryLocationArrayAdapter  = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, deliveryLocationArray);
        previousPositionOfDeliveryLocationSelected = modelObject.getPreviousPositionOfDeliveryLocationSelected();
    }

    private void setOnItemSelectedListenerForDeliveryLocationSpinnerInAccount(){
        deliveryLocationSpinnerInAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setNewPositionOfDeliveryLocation(position);
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

}
