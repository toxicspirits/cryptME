package com.example.cryptme.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptme.Functions.DecryptionFunction;
import com.example.cryptme.Functions.Utils;
import com.example.cryptme.R;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecryptionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ciphertext_et;
    EditText iv_et;
    EditText secretkey_et;
    TextView plaintext_tv;
    String decryptedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decryption_main);


        final Button decBtn = (Button) findViewById(R.id.decBtn);
        ciphertext_et = (EditText) findViewById(R.id.dec_ciphertext);
        secretkey_et = (EditText) findViewById(R.id.dec_secretkey);
        iv_et = (EditText) findViewById(R.id.dec_iv);
        plaintext_tv = (TextView) findViewById(R.id.dec_plaintext);

        decBtn.setOnClickListener(this);

        /*This functionality is provided in com.example.cryptme.Functions.Utils.kt.
         *In case the utils.kt class is not used, make sure to find a suitable replacement for boilerplate.
         */
        List<String> list = Utils.getData(this);
        if (list.size() > 0) {
            ciphertext_et.setText(list.get(0));
            secretkey_et.setText(list.get(1));
            iv_et.setText(list.get(2));
        }
    }

    public void onClick(View view) {
        if ((TextUtils.isEmpty(ciphertext_et.getText()) || (TextUtils.isEmpty(iv_et.getText())) || (TextUtils.isEmpty(secretkey_et.getText())))) {
            Toast t = Toast.makeText(this, "Fill empty fields.", Toast.LENGTH_SHORT);
            t.show();
        } else {
            try {
                String strEncTxt = ciphertext_et.getText().toString().trim();
                byte[] encText = decoderfunc(strEncTxt);
                String strIV = iv_et.getText().toString().trim();
                byte[] iv = decoderfunc(strIV);
                String strSecretKey = secretkey_et.getText().toString().trim();
                byte[] encodedSecretKey = decoderfunc(strSecretKey);

                SecretKey originalSecretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "AES");

                decryptedText = DecryptionFunction.decrypt(encText, originalSecretKey, iv);
                plaintext_tv.setText(decryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] decoderfunc(String enval) {
        byte[] conval = Base64.decode(enval, Base64.DEFAULT);
        return conval;
    }
}