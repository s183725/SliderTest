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

//MULTITOGGLEBUTTON LIBS
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.sha.kamel.multitogglebutton.MultiToggleButton;
import com.sha.kamel.multitogglebutton.Selected;
import com.sha.kamel.multitogglebutton.ToggleButton;



public class MainActivity extends AppCompatActivity {
    
    final int warm = 0;
    final int cold = 1;
    
    private int[] sliderValue = new int[2];
    private Slider[] sliders = new Slider[2];
    
    private MultiToggleButton mtb1;
    private TextView slideText;

    private Calendar nowTime;
    private SimpleDateFormat dateFormat;
    private String date;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtb1 = (MultiToggleButton) findViewById(R.id.mtb);
        mtb1.selectFirstItem(true);
        nowTime = Calendar.getInstance();

        sliders[warm] = (Slider) findViewById(R.id.warmSlider);
        sliders[cold] = (Slider) findViewById(R.id.coldSlider);
        sliders[warm].addOnChangeListener(warmListen);
        sliders[cold].addOnChangeListener(coldListen);

        slideText = (TextView) findViewById(R.id.slideText) ;
        sliderValue[warm] = (int) sliders[warm].getValue();
        sliderValue[cold] = (int) sliders[cold].getValue();
        
        mtb1.setOnItemSelectedListener(mtbListen);
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
        public void onSelected(ToggleButton toggleButton, View item, int position, String label, boolean selected) {
            if ( selectedItem(mtb1) == 2) {
                slidersOn(sliders, false);
            } else if ( selectedItem(mtb1) == 3) {
                slidersOn(sliders, true);
            } else {
                slidersOn(sliders, false);
                slideText.setAlpha(0.5f);
            }
        }
    };

    // GET MULTITOGGLEBUTTON SELECTED ITEM
    private int selectedItem(ToggleButton toggleButton) {
        Selected select = toggleButton.getSelected();
        return select.getSingleItemPosition();
    }

    // SLIDER ON/OFF SWITCH FOR state
    private void slidersOn(Slider[] sliders, boolean state) {
        if (state = true) {
            sliders[warm].setEnabled(true);
            sliders[warm].setAlpha(1.0f);
            sliders[cold].setEnabled(true);
            sliders[cold].setAlpha(1.0f);
            findViewById(R.id.imageView2).setAlpha(1.0f);
        } else {
            sliders[warm].setEnabled(false);
            sliders[warm].setAlpha(0.5f);
            sliders[cold].setEnabled(false);
            sliders[cold].setAlpha(0.5f);
            findViewById(R.id.imageView2).setAlpha(0.5f);
        }
    }

}

