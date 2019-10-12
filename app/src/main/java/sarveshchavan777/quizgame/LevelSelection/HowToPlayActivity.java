package sarveshchavan777.quizgame.LevelSelection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sarveshchavan777.quizgame.QuestionActivity;
import sarveshchavan777.quizgame.R;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        Button homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToPlayActivity.this, SelectionLevelHomeScreen.class);
                startActivity(intent);
            }
        });
    }
}
