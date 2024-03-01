package com.math.findingmememo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class problem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        final EditText inputNumber = findViewById(R.id.num_entry);
        final TextView numResult = findViewById(R.id.number_result);
        Button MeanButton = findViewById(R.id.button_mean);
        Button MedianButton = findViewById(R.id.button_median);
        Button ModeButton = findViewById(R.id.button_mode);
        TextView changeText = findViewById(R.id.changeText);


        inputNumber.setInputType(InputType.TYPE_CLASS_TEXT);
        inputNumber.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        for (int i = start; i < end; i++) {
                            if (!Character.isDigit(source.charAt(i)) && source.charAt(i) != ' ') {
                                return "";
                            }
                        }
                        return null;
                    }
                }
        });

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
                    if (c == ' ') {
                        formattedText.append(',');
                    }
                }
                numResult.setText(formattedText.toString());
            }
        });
        MeanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = numResult.getText().toString().trim();

                if (currentText.isEmpty()) {
                    changeText.setText("Please provide input");
                    return; //
                }

                String[] numbers = currentText.split(",");
                double sum = 0;
                for (String number : numbers) {
                    sum += Double.parseDouble(number.trim());
                }

                double mean = sum / numbers.length;
                changeText.setText("Mean: " + mean);
            }
        });

        MedianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = numResult.getText().toString().trim();

                if (currentText.isEmpty()) {
                    changeText.setText("Please provide input");
                    return; //
                }

                String[] numbers = currentText.split(",");
                double[] doubleNumbers = new double[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    doubleNumbers[i] = Double.parseDouble(numbers[i].trim());
                }

                Arrays.sort(doubleNumbers);

                double median;
                int middle = doubleNumbers.length / 2;
                if (doubleNumbers.length % 2 == 0) {
                    median = (doubleNumbers[middle - 1] + doubleNumbers[middle]) / 2;
                } else {
                    median = doubleNumbers[middle];
                }

                changeText.setText("Median: " + median);
            }
        });

        ModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = numResult.getText().toString().trim();

                if (currentText.isEmpty()) {
                    changeText.setText("Please provide input");
                    return; //
                }

                String[] numbers = currentText.split(",");
                double[] doubleNumbers = new double[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    doubleNumbers[i] = Double.parseDouble(numbers[i].trim());
                }

                Map<Double, Integer> frequencyMap = new HashMap<>();
                for (double num : doubleNumbers) {
                    if (frequencyMap.containsKey(num)) {
                        frequencyMap.put(num, frequencyMap.get(num) + 1);
                    } else {
                        frequencyMap.put(num, 1);
                    }
                }

                double mode = 0;
                int maxFrequency = 0;
                boolean isUnique = true;
                for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
                    int frequency = entry.getValue();
                    if (frequency > maxFrequency) {
                        maxFrequency = frequency;
                        mode = entry.getKey();
                        isUnique = true;
                    } else if (frequency == maxFrequency) {
                        isUnique = false;
                    }
                }

                if (isUnique) {
                    changeText.setText("Mode: " + mode);
                } else {
                    changeText.setText("Multiple modes with frequency " + maxFrequency);
                }
            }
        });


    }

}
