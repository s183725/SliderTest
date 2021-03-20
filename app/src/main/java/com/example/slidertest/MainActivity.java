package com.example.slidertest;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.sip.SipSession;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.widget.Toolbar;

//MULTITOGGLEBUTTON LIBS
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.sha.kamel.multitogglebutton.MultiToggleButton;
import com.sha.kamel.multitogglebutton.Selected;
import com.sha.kamel.multitogglebutton.ToggleButton;

import java.util.stream.Stream;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    
    final int warm = 0;
    final int cold = 1;
    
    private int[] sliderValue = new int[2];
    private Slider[] sliders = new Slider[2];
    
    private MultiToggleButton mtb1;
    private TextView slideText;
    private Selected select;

    private Calendar nowTime = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    private String date;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliders[warm] = (Slider) findViewById(R.id.warmSlider);
        sliders[cold] = (Slider) findViewById(R.id.coldSlider);
        slideText = (TextView) findViewById(R.id.slideText) ;
        initTextview();

        mtb1 = (MultiToggleButton) findViewById(R.id.mtb);
        mtb1.selectFirstItem(true);
        mtb1.setOnItemSelectedListener(mtbListen);

        sliderValue[warm] = (int) sliders[warm].getValue();
        sliderValue[cold] = (int) sliders[cold].getValue();

    }

    // WARM SLIDER CONFIGURATION
    private final Slider.OnChangeListener warmListen = new Slider.OnChangeListener() {
        @Override
        public void onValueChange(@NonNull Slider warmSlider, float value, boolean fromUser) {
            sliderValue[warm] = (int) warmSlider.getValue();
            String textString = "Warm: " + sliderValue[warm] + ", Cold: " + sliderValue[cold];
            slideText.setText(textString);
        }
    };

    // COLD SLIDER CONFIGURATION
    private final Slider.OnChangeListener coldListen = new Slider.OnChangeListener() {
        @Override
        public void onValueChange(@NonNull Slider coldSlider, float value, boolean fromUser) {
            sliderValue[cold] = (int) coldSlider.getValue();
            String textString = "Warm: " + sliderValue[warm] + ", Cold: " + sliderValue[cold];
            slideText.setText(textString);
        }
    };

    // TOGGLEBUTTON LISTENER AND CONDITIONALS
    private final MultiToggleButton.OnItemSelectedListener mtbListen = new ToggleButton.OnItemSelectedListener() {
        @Override
        public void onSelected(ToggleButton toggleButton, View item, int position, String label, boolean Selected) {
            if ( toggleButton.getSelected().getSingleItemPosition() == 1 ) {
                slidersOn(sliders, false);
                slideText.setText("" + nowTime.getTime());
            } else if ( toggleButton.getSelected().getSingleItemPosition() == 2 ) {
                initTextview();
                slidersOn(sliders, true);
                sliders[warm].addOnChangeListener(warmListen);
                sliders[cold].addOnChangeListener(coldListen);
            } else {
                slidersOn(sliders, false);
            }
        }
    };

    // GET MULTITOGGLEBUTTON SELECTED ITEM
    private int selectedItem(ToggleButton toggleButton) {
        return select.getSingleItemPosition();
    }

    // SLIDER ON/OFF SWITCH FOR state
    private void slidersOn(Slider[] sliders, boolean state) {
        if (state == true) {
            sliders[warm].setEnabled(true);
            sliders[warm].setAlpha(1.0f);
            sliders[cold].setEnabled(true);
            sliders[cold].setAlpha(1.0f);
            findViewById(R.id.imageView2).setAlpha(1.0f);
        } else {
            sliders[warm].setEnabled(false);
            sliders[warm].setAlpha(0.3f);
            sliders[cold].setEnabled(false);
            sliders[cold].setAlpha(0.3f);
            findViewById(R.id.imageView2).setAlpha(0.3f);
        }
    }

    private void initTextview() {
        String textString = "Warm: " + sliderValue[warm] + ", Cold: " + sliderValue[cold];
        slideText.setText(textString);
    }

}


