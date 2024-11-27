package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Registering event listeners using OnClickListener
        findViewById(R.id.button_1).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_2).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_3).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_4).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_5).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_6).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_7).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_8).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_9).setOnClickListener(buttonClickListener);
        findViewById(R.id.button_0).setOnClickListener(buttonClickListener);

        // Operators
        findViewById(R.id.button_add).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_subtract).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_multiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_divide).setOnClickListener(operatorClickListener);

        // Equal button to calculate
        findViewById(R.id.button_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
            }
        });

        // Clear button
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                previousInput = "";
                operator = "";
                display.setText("");
            }
        });
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            currentInput += button.getText().toString();
            display.setText(currentInput);
        }
    };

    private View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            if (!currentInput.isEmpty()) {
                previousInput = currentInput;
                currentInput = "";
                operator = button.getText().toString();
                display.setText(previousInput + " " + operator);
            }
        }
    };

    private void performCalculation() {
        if (!previousInput.isEmpty() && !currentInput.isEmpty()) {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            previousInput = "";
            operator = "";
        }
    }
}
