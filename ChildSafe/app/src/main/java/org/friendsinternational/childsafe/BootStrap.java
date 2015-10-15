package org.friendsinternational.childsafe;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

/**
 * Created by CoolBuddy on 13/10/2015.
 */
public class BootStrap extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "xpV5UgBrdVPchdAajTLuUgZACj1WrlqTzG2hF9t9", "KHzUlgka1NXy76bjsR9zdulnMfdccqIeZ82ixbR4");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("FIMessages");
    }
}
