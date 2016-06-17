package com.magnus.edutech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.magnus.edutech.BackgroundService.LoadDataInBackGround;
import com.magnus.edutech.model.User;
import com.magnus.edutech.webservices.GlobalConstants;
import com.magnus.edutech.webservices.Utilities;

import org.json.JSONException;


public class ActivityRegistrationScreen extends Activity implements OnClickListener {

    //Class
    private  MainRoutingClass mainRoutingClass;
    private LoadDataInBackGround loadDataBackgroundService;
    private Utilities utilities;
    //Variables
    private static Context context;
    private static Activity activity;
    //XML
    private TextInputLayout editTextUserNameRegistrationInput, editTextUserEmailRegistrationInput, editTextUserMobileRegistrationInput,
            editTextUserPasswordRegistrationInput, editTextUserPasswordCRegistrationInput;
    private EditText editTextUserNameRegistration, editTextUserEmailRegistration, editTextUserMobileRegistration, editTextUserPasswordRegistration,
            editTextUserCPasswordRegistration;
    private Button registrationSubmitButtonId;
    private ImageView imageViewBackRegistration;

    private Typeface typefaceContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*setStatusBarColor(null, getResources().getColor(android.R.color.black));*/
        context = this;
        activity = this;

        mainRoutingClass  =  new MainRoutingClass(context);
        utilities = new Utilities();
        typefaceContent = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
        loadDataBackgroundService = new LoadDataInBackGround();

        //initialize variables
        initVariable();
    }


    private void initVariable() {

        editTextUserNameRegistrationInput = (TextInputLayout) findViewById(R.id.editTextUserNameRegistrationInput);
        editTextUserNameRegistration = (EditText) findViewById(R.id.editTextUserNameRegistration);
        editTextUserNameRegistration.setTypeface(typefaceContent);
        editTextUserNameRegistration.addTextChangedListener(new MyTextWatcher(editTextUserNameRegistration));


        editTextUserEmailRegistrationInput = (TextInputLayout) findViewById(R.id.editTextUserEmailRegistrationInput);
        editTextUserEmailRegistration = (EditText) findViewById(R.id.editTextUserEmailRegistration);
        editTextUserEmailRegistration.setTypeface(typefaceContent);
        editTextUserEmailRegistration.addTextChangedListener(new MyTextWatcher(editTextUserEmailRegistration));

        editTextUserMobileRegistrationInput = (TextInputLayout) findViewById(R.id.editTextUserMobileRegistrationInput);
        editTextUserMobileRegistration = (EditText) findViewById(R.id.editTextUserMobileRegistration);
        editTextUserMobileRegistration.setTypeface(typefaceContent);
        editTextUserMobileRegistration.addTextChangedListener(new MyTextWatcher(editTextUserMobileRegistration));


        editTextUserPasswordRegistrationInput = (TextInputLayout) findViewById(R.id.editTextUserPasswordRegistrationInput);
        editTextUserPasswordRegistration = (EditText) findViewById(R.id.editTextUserPasswordRegistration);
        editTextUserPasswordRegistration.setTypeface(typefaceContent);
        editTextUserPasswordRegistration.addTextChangedListener(new MyTextWatcher(editTextUserPasswordRegistration));

        editTextUserPasswordCRegistrationInput = (TextInputLayout) findViewById(R.id.editTextUserPasswordCRegistrationInput);
        editTextUserCPasswordRegistration = (EditText) findViewById(R.id.editTextUserCPasswordRegistration);
        editTextUserCPasswordRegistration.setTypeface(typefaceContent);
        editTextUserCPasswordRegistration.addTextChangedListener(new MyTextWatcher(editTextUserCPasswordRegistration));
        // back imageView

        imageViewBackRegistration =  (ImageView) findViewById(R.id.imageViewBackRegistration);
        imageViewBackRegistration.setOnClickListener(this);

        registrationSubmitButtonId = (Button) findViewById(R.id.registrationSubmitButtonId);
        registrationSubmitButtonId.setTypeface(typefaceContent);
        registrationSubmitButtonId.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.registrationSubmitButtonId:
                // registration
                if (submitForm()) {

                    String fullName = editTextUserNameRegistration.getText().toString();
                    String email = editTextUserEmailRegistration.getText().toString();
                    String mobile = editTextUserMobileRegistration.getText().toString();
                    String password = editTextUserPasswordRegistration.getText().toString();
                    String confirmPassword = editTextUserCPasswordRegistration.getText().toString();

                    User user = new User(fullName,email,mobile,password,confirmPassword);

                        /* RegistrationInfo.put(GlobalConstants.MESSAGE, "Registering ....");*/

                        try {
                            loadDataBackgroundService.RegisterUserToFromServer(context, user);
                        } catch (NumberFormatException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                break;
            case R.id.imageViewBackRegistration:
                mainRoutingClass.findActivityRoute(GlobalConstants.ACTIVITY_LOGIN,null);
                finish();
                // back

                break;

        }
    }

    /**
     * Validating form
     */
    private boolean submitForm() {
        if (!validateName()) {
            return false;
        }

        if (!validateEmail()) {
            return false;
        }

        if (!validatePhone()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }
        if (!validateConfirmPassword()) {
            return false;
        }
        return true;

    }

    private boolean validateName() {
        if (!utilities.isNotEmpty(editTextUserNameRegistration.getText().toString().trim())) {
            editTextUserNameRegistrationInput.setError(getString(R.string.err_msg_name));
            requestFocus(editTextUserNameRegistration);
            return false;
        } else {
            editTextUserNameRegistrationInput.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = editTextUserEmailRegistration.getText().toString().trim();

        if (email.isEmpty() || !utilities.isValidEmail(email)) {
            editTextUserEmailRegistrationInput.setError(getString(R.string.err_msg_email));
            requestFocus(editTextUserEmailRegistration);
            return false;
        } else {
            editTextUserEmailRegistrationInput.setErrorEnabled(false);
        }

        return true;
    }
// valid password
    private boolean validatePassword() {
        String inputPassword  =editTextUserPasswordRegistration.getText().toString().trim();
        if (!utilities.isNotEmpty(inputPassword )|| inputPassword.length() < 6) {
            editTextUserPasswordRegistrationInput.setError(getString(R.string.err_msg_password));
            requestFocus(editTextUserPasswordRegistration);
            return false;
        } else {
            editTextUserPasswordRegistrationInput.setErrorEnabled(false);
        }

        return true;
    }

    // valid confirm password
    private boolean validateConfirmPassword() {
        String inputConfirmPassword  =editTextUserCPasswordRegistration.getText().toString().trim();
        String inputPassword  =editTextUserPasswordRegistration.getText().toString().trim();
        if (!utilities.isNotEmpty(inputConfirmPassword)) {
            editTextUserPasswordCRegistrationInput.setError(getString(R.string.err_msg_confirm_password));
            requestFocus(editTextUserCPasswordRegistration);
            return false;
        }
        if (!inputConfirmPassword.equalsIgnoreCase(inputPassword)) {
            editTextUserPasswordCRegistrationInput.setError(getString(R.string.err_msg_confirmation));
            requestFocus(editTextUserCPasswordRegistration);
            return false;
        }
        else {
            editTextUserPasswordCRegistrationInput.setErrorEnabled(false);
        }

        return true;
    }
    // valid phone
    private boolean validatePhone() {
        String inputPhone  =editTextUserMobileRegistration.getText().toString().trim();
        if (!utilities.isValidPhoneNumber(inputPhone)) {
            editTextUserMobileRegistrationInput.setError(getString(R.string.err_msg_phone));
            requestFocus(editTextUserMobileRegistration);
            return false;
        } else {
            editTextUserMobileRegistrationInput.setErrorEnabled(false);
        }

        return true;
    }
    // Requesting Focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.editTextUserNameRegistration:
                    validateName();
                    break;
                case R.id.editTextUserEmailRegistration:
                    validateEmail();
                    break;
                case R.id.editTextUserMobileRegistration:
                    validatePhone();
                    break;
                case R.id.editTextUserPasswordRegistration:
                    validatePassword();
                    break;
                case R.id.editTextUserCPasswordRegistration:
                      validateConfirmPassword();
                    break;
            }
        }
    }

    //Login Update from async task
    public static void updateUi() {
        Intent signUpIntent = new Intent(context, ActivityMain.class);

        if (context != null && signUpIntent != null) {
            context.startActivity(signUpIntent);
            activity.finish();
        }
    }

}
