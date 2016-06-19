package com.magnus.edutech.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.R;
import com.magnus.edutech.model.User;
import com.magnus.edutech.view.mapper.MainRoutingClass;
import com.magnus.edutech.view.utility.Utilities;
import com.magnus.edutech.view.utility.interfaces.GenericPromptDialogBoxInterface;
import com.magnus.edutech.webservices.MakeAServiceCall;
import com.magnus.edutech.webservices.RequestCallbackListener;
import com.magnus.edutech.webservices.userservices.servercall.MakeAUsersServerCall;


public class LoginScreenActivity extends Activity implements OnClickListener, RequestCallbackListener {

    //Class
    private MainRoutingClass mainRoutingClass;
    private static Utilities utilities;
    //Variables
    private static Context context;
    private static Activity activity;
    //XML
    private TextInputLayout editTextUserNameInput, editTextUserPasswordInput;
    private EditText editTextUserName, editTextUserPassword;
    private Button signInSubmitButtonId, buttonSignUp;
    private TextView textViewForgetPassword, textViewSignUp;
    private ImageView imageViewLoginOptionFacebook, imageViewLoginOptionTwitter, imageViewLoginOptionGoogle;
    private Typeface typeface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*setStatusBarColor(null, getResources().getColor(android.R.color.black));*/

        context = this;
        activity = this;

        typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
        mainRoutingClass = new MainRoutingClass(context);
        utilities = new Utilities();

        //initialize variables
        initVariable();
    }


    private void initVariable() {
        editTextUserNameInput = (TextInputLayout) findViewById(R.id.editTextUserNameInput);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextUserName.setTypeface(typeface);
        editTextUserName.addTextChangedListener(new MyTextWatcher(editTextUserName));

        editTextUserPasswordInput = (TextInputLayout) findViewById(R.id.editTextUserPasswordInput);
        editTextUserPassword = (EditText) findViewById(R.id.editTextUserPassword);
        editTextUserPassword.setTypeface(typeface);
        editTextUserPassword.addTextChangedListener(new MyTextWatcher(editTextUserPassword));

        textViewForgetPassword = (TextView) findViewById(R.id.textViewForgetPassword);
        textViewForgetPassword.setTypeface(typeface);
        textViewForgetPassword.setOnClickListener(this);

        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        textViewSignUp.setTypeface(typeface);

        imageViewLoginOptionFacebook = (ImageView) findViewById(R.id.imageViewLoginOptionFacebook);
        imageViewLoginOptionTwitter = (ImageView) findViewById(R.id.imageViewLoginOptionTwitter);
        imageViewLoginOptionGoogle = (ImageView) findViewById(R.id.imageViewLoginOptionGoogle);

        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignUp.setTypeface(typeface);
        buttonSignUp.setOnClickListener(this);

        signInSubmitButtonId = (Button) findViewById(R.id.signInSubmitButtonId);
        signInSubmitButtonId.setTypeface(typeface);
        signInSubmitButtonId.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signInSubmitButtonId:

            // Login Form Submission
                if (submitForm()) {
                    String email = editTextUserName.getText().toString();
                    String password = editTextUserPassword.getText().toString();
                    User user = new User(email, password);
                    if (utilities.isNetworkAvailable(context)) {
                        MakeAServiceCall makeAServiceCall = new MakeAServiceCall();
                        MakeAUsersServerCall makeALectureServerCall = makeAServiceCall.getUserServerCallObject(this);
                        makeALectureServerCall.serverCallUserLogin(context, user);
                    } else {
                        utilities.showInformativeDialog(context.getString(R.string.error_network_header),
                                context.getString(R.string.error_network_body), context);
                    }
                }

                break;

				/*
                varinder.magnus@gmail.com
				magnus@123*/

            case R.id.buttonSignUp:
                mainRoutingClass.findActivityRoute(GlobalConstants.ACTIVITY_REGISTRATION, null);
                finish();
                // registration
                break;
            case R.id.imageViewLoginOptionFacebook:
                // Login with facebook
                break;
            case R.id.imageViewLoginOptionTwitter:
                //Login with twiter
                break;
            case R.id.imageViewLoginOptionGoogle:
                //Login with google plus
                break;
            case R.id.textViewForgetPassword:
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);
                utilities.getGenericPromptDialog(context, promptsView, context.getString(R.string.alert_submit), context.getString(R.string.alert_cancel), false, new GenericPromptDialogBoxInterface() {
                    @Override
                    public void PositiveMethod(DialogInterface dialog, int id, String value) {
                        forgetPassword(value);
                    }

                    @Override
                    public void NegativeMethod(DialogInterface dialog, int id) {

                    }
                });
                break;
        }
    }

    /**
     * Validating form
     */
    private boolean submitForm() {
        if (!validateEmail()) {
            return false;
        }
        if (!validatePassword()) {
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = editTextUserName.getText().toString().trim();

        if (email.isEmpty() || !utilities.isValidEmail(email)) {
            editTextUserNameInput.setError(getString(R.string.err_msg_email));
            requestFocus(editTextUserName);
            return false;
        } else {
            editTextUserNameInput.setErrorEnabled(false);
        }

        return true;
    }

    // valid password
    private boolean validatePassword() {
        String inputPassword = editTextUserPassword.getText().toString().trim();
        if (!utilities.isNotEmpty(inputPassword) || inputPassword.length() < 6) {
            editTextUserPasswordInput.setError(getString(R.string.err_msg_password));
            requestFocus(editTextUserPassword);
            return false;
        } else {
            editTextUserPasswordInput.setErrorEnabled(false);
        }

        return true;
    }

    // Requesting Focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void notifyToCaller(boolean isExecuted, Object obj) {
        if(isExecuted)
        {
            User user = (User) obj;
            if(user.isIs_query_execute())
            {

            }
            else
            {

            }
        }
        else
        {
            utilities.showInformativeDialog(context.getString(R.string.err_server_header), context.getString(R.string.err_server_body), context);
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
                case R.id.editTextUserName:
                    validateEmail();
                    break;
                case R.id.editTextUserPassword:
                    validatePassword();
                    break;
            }
        }
    }

    //Login Update from async task
    public static void updateUi() {
        Intent signUpIntent = new Intent(context, HomeScreenActivity.class);

        if (context != null && signUpIntent != null) {
            context.startActivity(signUpIntent);
            activity.finish();
        }
    }

    // Forget password
    private void forgetPassword(String value) {
        if (utilities.isValidEmail(value)) {
            User user = new User();
            user.setEmail(value);
            if (utilities.isNetworkAvailable(context)) {
                MakeAServiceCall makeAServiceCall = new MakeAServiceCall();
                MakeAUsersServerCall makeALectureServerCall = makeAServiceCall.getUserServerCallObject(this);
                makeALectureServerCall.serverCallForgetPassword(context, user);
            } else {
                utilities.showInformativeDialog(context.getString(R.string.error_network_header),
                        context.getString(R.string.error_network_body), context);
            }
        } else {
            utilities.showInformativeDialog(context.getString(R.string.forget_password_prompt_header), context.getString(R.string.err_msg_email), context);
        }
    }

    //Login Update from async task
    public static void updateUiForForgetPassword() {
        utilities.showInformativeDialog(context.getString(R.string.forget_password_prompt_header), context.getString(R.string.success_forget_password), context);
    }

}
