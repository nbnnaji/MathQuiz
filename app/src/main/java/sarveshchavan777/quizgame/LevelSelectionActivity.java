package sarveshchavan777.quizgame;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.support.v7.widget.Toolbar;

/**
 * Created by LENOVO on 12/12/2016.
 * Modified by Nkechi Nnaji, Sept 26 2019
 * Description: Homescreen for selecting levels
 */

public class LevelSelectionActivity extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.selection_level_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            toolbar.setTitle("Home");
        }
        cardView = (CardView) findViewById(R.id.btn_easy);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelSelectionActivity.this, QuestionActivity.class);
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
