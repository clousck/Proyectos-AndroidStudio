package com.example.myapplicationutn009;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etFirstNumber, etSecondNumber;
    Button btnSum, btnSubtract, btnMultiply, btnDivide;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        InputFilter binaryInputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.length() == 1 && (source.charAt(0) == '0' || source.charAt(0) == '1')) {
                    return source;
                }
                return "";
            }
        };

        etFirstNumber = findViewById(R.id.etFirstNumber);
        etSecondNumber = findViewById(R.id.etSecondNumber);
        btnSum = findViewById(R.id.btnSum);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        tvResult = findViewById(R.id.tvResult);

        etFirstNumber.setFilters(new InputFilter[]{binaryInputFilter});
        etSecondNumber.setFilters(new InputFilter[]{binaryInputFilter});
    }

    public int binarioADecimal(String binario) {
        int resultant = 0;
        int longitude = binario.length();

        for (int i = 0; i < longitude; i++) {
            char digito = binario.charAt(i);

            if (digito == '1') {
                int potencia = longitude - 1 - i;
                resultant += (int) Math.pow(2, potencia);
            } else if (digito != '0') {
                throw new IllegalArgumentException("El string contiene caracteres no binarios");
            }
        }

        return resultant;
    }

    public String decimalABinario(int decimal) {
        StringBuilder binario = new StringBuilder();

        if (decimal == 0) {
            return "0";
        }

        while (decimal > 0) {
            binario.append(decimal % 2);
            decimal /= 2;
        }

        return binario.reverse().toString();
    }


    public void Multiply(View view) {
        String firstNumber = etFirstNumber.getText().toString();
        String secondNumber = etSecondNumber.getText().toString();

        if (!firstNumber.isEmpty() && !secondNumber.isEmpty()) {
            int num1 = binarioADecimal(firstNumber);
            int num2 = binarioADecimal(secondNumber);
            int result = num1 * num2;
            tvResult.setText("Resultado: " + decimalABinario(result));
        }
    }

    public void Divide(View view) {
        String firstNumber = etFirstNumber.getText().toString();
        String secondNumber = etSecondNumber.getText().toString();

        if (!firstNumber.isEmpty() && !secondNumber.isEmpty()) {
            int num1 = binarioADecimal(firstNumber);
            int num2 = binarioADecimal(secondNumber);
            if (num2 == 0) {
                tvResult.setText("Error: División por cero");
                return;
            }
            int result = num1 / num2;
            tvResult.setText("Resultado: " + decimalABinario(result));
        }
    }

    public void Add(View view) {
        String firstNumber = etFirstNumber.getText().toString();
        String secondNumber = etSecondNumber.getText().toString();

        if (!firstNumber.isEmpty() && !secondNumber.isEmpty()) {
            int num1 = binarioADecimal(firstNumber);
            int num2 = binarioADecimal(secondNumber);
            int result = num1 + num2;
            tvResult.setText("Resultado: " + decimalABinario(result));
        }
    }

    public void Substarct(View view) {
        String firstNumber = etFirstNumber.getText().toString();
        String secondNumber = etSecondNumber.getText().toString();

        if (!firstNumber.isEmpty() && !secondNumber.isEmpty()) {
            int num1 = binarioADecimal(firstNumber);
            int num2 = binarioADecimal(secondNumber);
            int result = num1 - num2;
            tvResult.setText("Resultado: " + decimalABinario(result));
        }
    }

    public void Clear(View view) {
        etFirstNumber.setText("");
        etSecondNumber.setText("");
        tvResult.setText("Resultado: ");
    }
}