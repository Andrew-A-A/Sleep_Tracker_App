package com.example.st;


import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private Fragment fragmentintro= new IntroFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        ImageView startApp= findViewById(R.id.introButton);

        startApp.setOnClickListener(view ->
        {

                    findViewById(R.id.introButton).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout));
                    findViewById(R.id.textView).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout));
                    findViewById(R.id.lottieAnimationView).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout));
                    handler.postDelayed(startNewFragment,1000);
                    startApp.setOnClickListener(null);
                                                                         });


            }
    public Runnable startNewFragment = ()->
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.constraintee, fragmentintro )
                    .commit();

}




