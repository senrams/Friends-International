package org.friendsinternational.childsafe;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
    protected void startActivity(Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }

    protected void addOnClickListener(int btnId, View.OnClickListener listener) {
        Button mEmailSignInButton = (Button) findViewById(btnId);
        mEmailSignInButton.setOnClickListener(listener);
    }

    protected String getValue(int controlId) {
        View control = findViewById(controlId);
        if (control instanceof EditText)
            return ((EditText)control).getText().toString();
        else if (control instanceof TextView)
            ((TextView)control).getText().toString();
        return null;
    }

    protected void setText(int controlId, String text) {
        View control = findViewById(controlId);
        if (control instanceof EditText)
            ((EditText)control).setText(text);
        else if (control instanceof TextView)
            ((TextView)control).setText(text);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show, final View mainView, final View progressView) {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mainView.setVisibility(show ? View.GONE : View.VISIBLE);
    }



}
