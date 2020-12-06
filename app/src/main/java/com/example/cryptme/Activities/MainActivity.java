package com.example.cryptme.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cryptme.R;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intentMain = new Intent(this, MainActivity.class);
        //startActivity(intentMain);

        Button encBtn = (Button) findViewById(R.id.encBtnMain);
        Button decBtn = (Button) findViewById(R.id.decBtnMain);
        encBtn.setOnClickListener(this);
        decBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.encBtnMain:
                Intent intent1 = new Intent(this, EncryptionActivity.class);
                startActivity(intent1);
                break;
            case R.id.decBtnMain:
                Intent intent2 = new Intent(this, DecryptionActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}



