package blueteammathquiz.quizgame.segments.home.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import blueteammathquiz.quizgame.segments.instructions.view.InstructionsActivity;
import blueteammathquiz.quizgame.segments.practice.view.PracticeLevelActivity;
import blueteammathquiz.quizgame.segments.game.view.QuestionActivity;
import blueteammathquiz.quizgame.R;

/**
 * Created by LENOVO on 12/12/2016.
 * Modified by Nkechi Nnaji, Sept 26 2019, October 11, 2019
 * Description: Homescreen for selecting levels
 */

public class SelectionLevelHomeScreen extends AppCompatActivity {

    CardView cardView;
    private String[] challengeLevel = {"Easy", "Medium", "Hard"};

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

        /*Added by Nkechi Nnaji*/

        cardView = findViewById(R.id.btn_easy);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionLevelHomeScreen.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        /*Added by Nkechi Nnaji*/
        cardView = findViewById(R.id.instructions_btn);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionLevelHomeScreen.this, InstructionsActivity.class);
                startActivity(intent);
            }
        });

        /*Added by Nkechi Nnaji*/
        cardView = findViewById(R.id.level_selection_btn);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play button
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper((SelectionLevelHomeScreen.this), R.style.BlueTeam_Dialog_Theme));
                builder.setTitle("Choose practice level")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(challengeLevel, 0, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int levelChosen) {
                                dialog.dismiss();

                                startMathQuiz(levelChosen);

                            }


                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selection_level_menu, menu);
        return true;
    }


    private void startMathQuiz(int levelChosen)
    {
        Intent playIntent = new Intent(this, PracticeLevelActivity.class);
        playIntent.putExtra("level", levelChosen);
        this.startActivity(playIntent);
    }
}
