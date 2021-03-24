package com.example.cryptme.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptme.R;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.io.Reader;

public class AttachFile extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attach_file);
        final Button attachBtn = (Button)findViewById(R.id.attachBtn);
        attachBtn.setOnClickListener(this);
        EditText editText = (EditText)findViewById(R.id.enc_plaintext);
    }
    public static final int PERMISSION_REQUEST_STORAGE = 1;
    private static final int FILE_PICKER = 2;

    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }
    public void openFile(Uri pickerInitialUri)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, FILE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK)
        {
            Uri uri;
            if(data != null)
            {
                uri = data.getData();
                try {
                    System.out.println(readTextFromUri(uri));
                    String plaindata = readTextFromUri(uri);
                    Intent intent = new Intent(this, EncryptionActivity.class);
                    EditText plaintext = (EditText)findViewById(R.id.enc_plaintext);
                    plaintext.setText(readTextFromUri(uri));
                    intent.putExtra()



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            System.out.println("Permission Granted");
            openFile(null);
        }
        else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

            System.out.println("Showing Rationale");
        }
        else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_STORAGE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Permission not Granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}

