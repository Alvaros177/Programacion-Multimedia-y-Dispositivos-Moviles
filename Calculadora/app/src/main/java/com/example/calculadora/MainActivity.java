package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView display;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] buttons = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_mul, R.id.btn_div, R.id.btn_dot,
                R.id.btn_ac, R.id.btn_c, R.id.btn_equals
        };

        for (int id : buttons) {
            findViewById(id).setOnClickListener(this);
        }

        display.setText("0");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_ac) {
            input = "";
            display.setText("0");
            return;
        }

        if (id == R.id.btn_c) {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                display.setText(input.isEmpty() ? "0" : input);
            }
            return;
        }

        if (id == R.id.btn_equals) {
            calculateResult();
            return;
        }

        Button btn = (Button) v;
        String text = btn.getText().toString();

        if (isOperator(text)) {
            if (input.isEmpty()) return;
            if (isOperator(input.charAt(input.length() - 1) + "")) {
                input = input.substring(0, input.length() - 1);
            }
            input += text;
            display.setText(input);
            return;
        }

        if (text.equals(".")) {
            String lastNumber = getLastNumber(input);
            if (lastNumber.contains(".")) return;
            if (lastNumber.isEmpty()) text = "0.";
        }

        if (text.equals("0")) {
            String lastNumber = getLastNumber(input);
            if (lastNumber.equals("0")) return;
        }

        if (input.isEmpty() && text.equals("0")) {
            display.setText("0");
            return;
        }

        input += text;
        display.setText(input);
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private String getLastNumber(String expr) {
        String[] parts = expr.split("[+\\-*/]");
        return parts.length > 0 ? parts[parts.length - 1] : "";
    }

    private void calculateResult() {
        if (input.isEmpty()) {
            display.setText("0");
            return;
        }

        if (isOperator(input.charAt(input.length() - 1) + "")) {
            input = input.substring(0, input.length() - 1);
        }

        try {
            double result = evaluate(input);
            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
                input = String.valueOf((long) result);
            } else {
                display.setText(String.valueOf(result));
                input = String.valueOf(result);
            }
        } catch (Exception e) {
            display.setText("Error");
            input = "";
        }
    }

    private double evaluate(String expr) throws Exception {
        // Simplified evaluation using two stacks for operators and numbers (basic calculator)
        java.util.Stack<Double> numbers = new java.util.Stack<>();
        java.util.Stack<Character> ops = new java.util.Stack<>();

        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (isOperator(c + "")) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = ops.pop();
                    numbers.push(applyOp(a, b, op));
                }
                ops.push(c);
                i++;
            } else {
                throw new Exception("Invalid character");
            }
        }

        while (!ops.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = ops.pop();
            numbers.push(applyOp(a, b, op));
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private double applyOp(double a, double b, char op) throws ArithmeticException {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: return 0;
        }
    }
}
