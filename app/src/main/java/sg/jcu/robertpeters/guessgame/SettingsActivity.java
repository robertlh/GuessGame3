package sg.jcu.robertpeters.guessgame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("GuessGame", MODE_PRIVATE);
        setContentView(R.layout.activity_settings);
        final TextView minText = findViewById(R.id.textMinValue);
        minText.setText(Integer.toString(preferences.getInt("minValue",10)));
        SeekBar minSeek = findViewById(R.id.minValue);
        minSeek.setProgress(preferences.getInt("minValue", 10));
        minSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                preferences.edit()
                        .putInt("minValue", progress)
                        .apply();
                minText.setText(Integer.toString(progress));
            }
        });

        final TextView maxText = findViewById(R.id.textMaxValue);
        SeekBar maxSeek = findViewById(R.id.maxValue);
        maxText.setText(Integer.toString(preferences.getInt("maxValue",20)));
        maxSeek.setProgress(preferences.getInt("maxValue", 20));
        maxSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                preferences.edit()
                        .putInt("maxValue", progress)
                        .apply();
                maxText.setText(Integer.toString(progress));
            }
        });
    }





}
