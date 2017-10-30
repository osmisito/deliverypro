package com.oscarfernandez.deliverypro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oscarfernandez.deliverypro.R;
import com.oscarfernandez.deliverypro.controller.LoginController;
import com.oscarfernandez.deliverypro.data.SharedPreferenceHelper;
import com.oscarfernandez.deliverypro.domain.Login;

public class LoginActivity extends AppCompatActivity {

    private LoginController loginController;

    private EditText rutTxt;
    private EditText passTxt;

    private Button sendBtn;

    private String rut;
    private String pass;

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPreferenceHelper.getSharedPreference(this, "isLoged", false)) {
            goToNextScreen();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Inicia Sesión");
        loginController = new LoginController(this);
        rutTxt = (EditText) findViewById(R.id.rutTxt);
        passTxt = (EditText) findViewById(R.id.passTxt);
        sendBtn = (Button) findViewById(R.id.sendButton);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rut = rutTxt.getText().toString();
                pass = passTxt.getText().toString();
                loginController.processLogin(new Login(rut, pass));
            }
        });
    }

    public void goToNextScreen() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void showError() {
        Toast.makeText(this, "Hubo un error", Toast.LENGTH_LONG).show();
    }
}
