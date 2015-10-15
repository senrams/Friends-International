package org.friendsinternational.childsafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParsePush;

public class PublishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        final Button button = (Button) findViewById(R.id.btnSend);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                publishMessage();
            }
        });
    }

    public void publishMessage() {
        EditText et = (EditText) findViewById(R.id.txtMessage);
        String str = et.getText().toString();
        ParsePush push = new ParsePush();
        push.setChannel("FIMessages");
        push.setMessage(str);
        push.sendInBackground();
    }
}
