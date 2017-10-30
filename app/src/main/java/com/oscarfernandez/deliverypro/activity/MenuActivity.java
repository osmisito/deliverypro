package com.oscarfernandez.deliverypro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oscarfernandez.deliverypro.R;
import com.oscarfernandez.deliverypro.data.SharedPreferenceHelper;

public class MenuActivity extends AppCompatActivity {

    private Button rutasBtn;
    private Button logOutBtn;
    private Button ordenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menú");
        rutasBtn = (Button) findViewById(R.id.btnRutas);
        logOutBtn = (Button) findViewById(R.id.btnLogout);
        ordenBtn = (Button) findViewById(R.id.btnRendicion);
        rutasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRutasScreen();
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceHelper.setSharedPreference(MenuActivity.this, "isLoged", false);
                SharedPreferenceHelper.setSharedPreference(MenuActivity.this, "idUser", "0");
                goToLoginScreen();
            }
        });
        ordenBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gotToRendicionScreen();
            }
        });
    }

    private void goToRutasScreen() {
        Intent intent = new Intent(this, RutasActivity.class);
        startActivity(intent);
    }

    private void goToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void gotToRendicionScreen() {
        Intent intent = new Intent(this, RendicionOrdenActivity.class);
        startActivity(intent);
    }
}
