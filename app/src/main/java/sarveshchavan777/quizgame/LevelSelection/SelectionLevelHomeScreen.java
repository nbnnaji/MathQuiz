package sarveshchavan777.quizgame.LevelSelection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import sarveshchavan777.quizgame.LevelSelection.difficulty_level.DifficultyLevelActivity;
import sarveshchavan777.quizgame.QuestionActivity;
import sarveshchavan777.quizgame.R;

/**
 * Created by LENOVO on 12/12/2016.
 * Modified by Nkechi Nnaji, Sept 26 2019
 * Description: Homescreen for selecting levels
 */

public class SelectionLevelHomeScreen extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
        Toolbar toolbar = findViewById(R.id.selection_level_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//           getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            toolbar.setTitle("Home");
        }
        cardView = findViewById(R.id.btn_easy);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionLevelHomeScreen.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        cardView = findViewById(R.id.btn_trial);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionLevelHomeScreen.this, HowToPlayActivity.class);
                startActivity(intent);
            }
        });

        cardView = findViewById(R.id.btn_difficult);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionLevelHomeScreen.this, DifficultyLevelActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selection_level_menu, menu);
        return true;
    }
}
