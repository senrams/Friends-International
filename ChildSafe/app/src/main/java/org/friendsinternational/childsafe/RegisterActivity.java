package org.friendsinternational.childsafe;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class RegisterActivity extends BaseActivity {

    private View mRegisterForm;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterForm = findViewById(R.id.registration_form);
        mProgressView = findViewById(R.id.registration_progress);
        addOnClickListener(R.id.email_register_button, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true, mRegisterForm, mProgressView);
                boolean newUser = false;
                ParseUser user  = ParseUser.getCurrentUser();
                if (user == null) {
                    newUser = true;
                    user = new ParseUser();
                }
                user.setUsername(getValue(R.id.email));
                user.setPassword(getValue(R.id.password));
                user.setEmail(getValue(R.id.email));

                user.put("firstName", getValue(R.id.edit_first_name));
                user.put("lastName",getValue(R.id.edit_last_name));
                // other fields can be set just like with ParseObject
                user.put("phone", getValue(R.id.edit_phone_number));

                if (newUser) {
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            completeOperation(e);
                        }
                    });
                }
                else {
                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            completeOperation(e);
                        }
                    });
                }
            }
        });
        prepopulate();
    }

    private void completeOperation(ParseException e) {
        if (e == null) {
            Toast.makeText(getBaseContext(), "User registered sunccessfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getBaseContext(), "User registration failed." + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        showProgress(false, mRegisterForm, mProgressView);
    }

    private void prepopulate() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser!=null) {
            setText(R.id.email, currentUser.getEmail());
            setText(R.id.edit_first_name, (String) currentUser.get("firstName"));
            setText(R.id.edit_last_name, (String) currentUser.get("lastName"));
            setText(R.id.edit_phone_number, (String) currentUser.get("phone"));
        }
    }

}
