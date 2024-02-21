package com.math.findingmememo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class problem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        final EditText inputNumber = findViewById(R.id.num_entry);
        final TextView numResult = findViewById(R.id.number_result);

        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = inputNumber.getText().toString().trim();
                StringBuilder formattedText = new StringBuilder();
                for (int i = 0; i < inputText.length(); i++) {
                    char c = inputText.charAt(i);
                    formattedText.append(c);
                    if (c == ' ') { // Add comma after each space
                        formattedText.append(',');
                    }
                }
                numResult.setText(formattedText.toString());
            }
        });
    }

}
