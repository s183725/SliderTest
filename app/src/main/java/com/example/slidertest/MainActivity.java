package com.example.slidertest;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ToggleButton;

import com.sha.kamel.multitogglebutton.MultiToggleButton;


public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MultiToggleButton mtb1 = (MultiToggleButton) findViewById(R.id.mtb);
        mtb1.selectFirstItem(true);



    }



}
