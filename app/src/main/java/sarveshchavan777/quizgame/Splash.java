package sarveshchavan777.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import sarveshchavan777.quizgame.LevelSelection.SelectionLevelHomeScreen;

/**
 * Created by LENOVO on 12/12/2016.
 * Modified by Nkechi Nnaji on October 6, 2019
 */

public class Splash extends Activity {

    Button btn;
    ImageView imageView;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        imageView = findViewById(R.id.splash_iv);

        if(imageView == null) throw new AssertionError();

        imageView.setBackgroundResource(R.drawable.animation_loading);
        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

       btn = findViewById(R.id.start_btn);
       final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadeout);
       btn.setOnClickListener(new View.OnClickListener() {

                                   @Override
                                   public void onClick(View view) {
                                       btn.startAnimation(animation);

                                       Intent intent = new Intent(Splash.this, SelectionLevelHomeScreen.class);
                                       startActivity(intent);
                                       overridePendingTransition(R.anim.slide_down,  R.anim.fadeout);
                                       finish();
                                   }
        });


    }
}
