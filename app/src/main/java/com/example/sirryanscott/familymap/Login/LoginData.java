package com.example.sirryanscott.familymap.Login;

import android.view.View;
import android.widget.EditText;

import com.example.sirryanscott.familymap.Model.User;
import com.example.sirryanscott.familymap.R;

/**
 * Created by sirryanscott on 7/27/16.
 */
public class LoginData {
    private static LoginData ourInstance = new LoginData();

    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_Host;
    private EditText editText_Port;
    private String serverHost;
    private String serverPort;

    private View myView;

    private User currentUser;

    public static LoginData getInstance() {
        return ourInstance;
    }

    private LoginData() {
        currentUser = new User();
    }

    public void setLoginData(){
        editText_username = (EditText)myView.findViewById(R.id.userNameField);
        editText_password = (EditText)myView.findViewById(R.id.passwordField);
        editText_Host = (EditText)myView.findViewById(R.id.serverHost);
        editText_Port = (EditText)myView.findViewById(R.id.serverPort);

        currentUser.setUserName(editText_username.getText().toString());
        currentUser.setPassword(editText_password.getText().toString());
        serverHost = editText_Host.getText().toString();
        serverPort = editText_Port.getText().toString();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getUrl(){
        return serverHost + ":" + serverPort;
    }

    public void setMyView(View myView) {
        this.myView = myView;
    }


}
