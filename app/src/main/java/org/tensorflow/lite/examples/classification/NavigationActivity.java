package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener{

    Button speech2sign, sign2speech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        speech2sign = findViewById(R.id.to_sign_btn);
        sign2speech = findViewById(R.id.from_sign_btn);
        speech2sign.setOnClickListener(this);
        sign2speech.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {
            case R.id.to_sign_btn:
                //redirect to text/speech to sign activity (speech library)
//                Toast.makeText(this, "Activity not included yet!", Toast.LENGTH_SHORT).show();
                intent = new Intent(NavigationActivity.this, Text2SignActivity.class);
                startActivity(intent);
                break;
            case R.id.from_sign_btn:
                //redirect to sign to text/speech activity (tensorflow)
                intent = new Intent(NavigationActivity.this, ClassifierActivity.class);
                startActivity(intent);
                break;
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        //prevent back button from cycling through inner activities
//        moveTaskToBack(true);

        //allow 2 tries if accidentally pressed back button
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
