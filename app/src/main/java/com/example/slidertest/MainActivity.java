package com.example.slidertest;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.sha.kamel.multitogglebutton.MultiToggleButton;
import com.sha.kamel.multitogglebutton.Selected;
import com.sha.kamel.multitogglebutton.ToggleButton;


public class MainActivity extends Activity {

    public int warmVal;
    public int coldVal;

    private Slider warmSlider;
    private Slider coldSlider;
    private MultiToggleButton mtb1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtb1 = (MultiToggleButton) findViewById(R.id.mtb);
        mtb1.selectFirstItem(true);

        warmSlider = (Slider) findViewById(R.id.warmSlider);
        coldSlider = (Slider) findViewById(R.id.coldSlider);

        warmVal = (int) warmSlider.getValue();
        coldVal = (int) coldSlider.getValue();

        mtb1.setOnItemSelectedListener(mtbSelect);

    }

    // WARM SLIDER CONFIGURATION
    private final Slider.OnChangeListener warmListen = new Slider.OnChangeListener() {
        @Override
        public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
            warmVal = (int) warmSlider.getValue();
        }
    };

    // COLD SLIDER CONFIGURATION
    private final Slider.OnChangeListener coldListen = new Slider.OnChangeListener() {
        @Override
        public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
            coldVal = (int) coldSlider.getValue();
        }
    };

    // TOGGLEBUTTON CONFIGURATION
    private final MultiToggleButton.OnItemSelectedListener mtbSelect = new ToggleButton.OnItemSelectedListener() {
        @Override
        public void onSelected(ToggleButton toggleButton, View item, int position, String label, boolean selected) {

            if ( position == 2) {

            } else if ( position == 3) {

            } else {
                
            }

        }
    };

    private static void showSnackbar(View view, @StringRes int messageRes) {
        Snackbar.make(view, messageRes, Snackbar.LENGTH_SHORT).show();
    }


}

