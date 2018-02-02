package sg.jcu.robertpeters.guessgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences preferences;
    private int max, min, number;
    private TextView tips;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("GuessGame", MODE_PRIVATE);
        input = findViewById(R.id.guessedNumber);
        tips = findViewById(R.id.tips);
        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    int input = Integer.parseInt(s.toString());
                    if (input == number) {
                        tips.setText(R.string.textCorrect);
                    } else if (number > input) {
                        tips.setText(R.string.textLow);
                    } else {
                        tips.setText(R.string.textHigh);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        min = preferences.getInt("minValue", 10);
        max = preferences.getInt("maxValue", 20);
    }

    public void Settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void NewRandomNumber(View view) {
        newRandomNumber();
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        min = preferences.getInt("minValue", 10);
        max = preferences.getInt("maxValue", 20);
        newRandomNumber();
    }

    private void newRandomNumber() {
        number = min + (int) (Math.random() * (max - min));
        input.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("number", number);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        number = savedInstanceState.getInt("number", number);
        tips.setText(Integer.toString(number));
    }
}
