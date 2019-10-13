package sarveshchavan777.quizgame.LevelSelection;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;

import sarveshchavan777.quizgame.R;

public class InstructionsActivity extends AppCompatActivity {

    private String[] challengeLevel = {"Easy", "Medium", "Hard"};
    Button trial_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //Trial button

        trial_btn = findViewById(R.id.trial_btn);

        trial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play button
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper((InstructionsActivity.this), R.style.BlueTeam_Dialog_Theme));
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

        Button homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructionsActivity.this, SelectionLevelHomeScreen.class);
                startActivity(intent);
            }
        });
    }

    private void startMathQuiz(int levelChosen)
    {
        Intent playIntent = new Intent(this, PracticeLevelActivity.class);
        playIntent.putExtra("level", levelChosen);
        this.startActivity(playIntent);
    }
}
