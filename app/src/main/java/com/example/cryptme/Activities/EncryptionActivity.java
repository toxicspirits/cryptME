package com.example.cryptme.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptme.Functions.EncryptionFunction;
import com.example.cryptme.Functions.Utils;
import com.example.cryptme.R;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Base64;

import org.w3c.dom.Text;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class EncryptionActivity extends AppCompatActivity implements View.OnClickListener {

    KeyGenerator keyGenerator;
    SecretKey secretKey;
    byte[] secretKeyen;
    String strSecretKey;
    byte[] IV = new byte[16];
    byte[] cipherText;
    SecureRandom random;


    /*Do not place Android context classes in static field. It causes some major memory leaks*/
    EditText plaintext_et;
    TextView secretkey_tv;
    TextView iv_tv;
    TextView ciphertext_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encryption_main);

        plaintext_et = (EditText) findViewById(R.id.enc_plaintext);
        secretkey_tv = (TextView) findViewById(R.id.enc_secretkey);
        iv_tv = (TextView) findViewById(R.id.enc_iv);
        ciphertext_tv = (TextView) findViewById(R.id.enc_ciphertext);

        final Button encryptBtn = (Button) findViewById(R.id.encBtn);
        encryptBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        System.out.println("DEBUG: WORKS");
        if (TextUtils.isEmpty(plaintext_et.getText())) {
            Toast t = Toast.makeText(this, "Fill empty field.", Toast.LENGTH_SHORT);
            t.show();
        } else {
            try {
                keyGenerator = KeyGenerator.getInstance("AES");
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
            secretKeyen = secretKey.getEncoded();
            strSecretKey = encoderfunc(secretKeyen);
            secretkey_tv.setText(strSecretKey);

            random = new SecureRandom();
            random.nextBytes(IV);
            try {
                cipherText = EncryptionFunction.encrypt(plaintext_et.getText().toString().trim().getBytes(), secretKey, IV);

                String sonuc = encoderfunc(cipherText);
                ciphertext_tv.setText(sonuc);

                String tvIV = encoderfunc(IV);
                iv_tv.setText(tvIV);

                Utils.saveData(this, sonuc, strSecretKey, tvIV);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String encoderfunc(byte[] decval) {
        String conVal = Base64.encodeToString(decval, Base64.DEFAULT);
        //String conVal = new String(Base64.getEncoder(byte));
        return conVal;
    }
}

