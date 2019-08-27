package com.example.homework721;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etGeo;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etGeo = findViewById(R.id.et_geo);
        btnResult = findViewById(R.id.btn_result);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etGeo.getText().toString();
                String UriGeo = "";
                if (isLetterValue(input)) {
                    //в строе есть буква
                    UriGeo = "?q=" + input;
                } else {
                    //в строе букв нет
                    UriGeo = input;
                }
                Uri uri = Uri.parse("geo:" + UriGeo);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);

                // Проверяeм, может ли система обработать наш Intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.msg_error_no_app), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //проверка на тип строки
    private Boolean isLetterValue(String input) {
        int count = 0;
        Boolean isletter = false;
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                count++;
            }
        }
        if (count > 0) {
            isletter = true;
        }
        return isletter;
    }
}
