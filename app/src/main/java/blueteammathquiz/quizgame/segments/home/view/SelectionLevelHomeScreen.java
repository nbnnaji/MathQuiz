package blueteammathquiz.quizgame.segments.home.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.FileNotFoundException;
import java.io.IOException;

import blueteammathquiz.quizgame.segments.instructions.view.InstructionsActivity;
import blueteammathquiz.quizgame.segments.practice.view.PracticeLevelActivity;
import blueteammathquiz.quizgame.segments.game.view.QuestionActivity;
import blueteammathquiz.quizgame.R;

/**
 * Created by LENOVO on 12/12/2016.
 * Modified by Nkechi Nnaji, Sept 26 2019, October 11, 2019, October 27, 2017
 * Description: Homescreen for selecting levels
 */

public class SelectionLevelHomeScreen extends AppCompatActivity {

    private static final int GET_FROM_GALLERY = 1;
    CardView cardView;
    private String[] challengeLevel = {"Easy", "Medium", "Hard"};
    ImageView image;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        //Added by Nkechi Nnaji
        overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
        Toolbar toolbar = findViewById(R.id.selection_level_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setTitle("Home");
        }


        /*Added by Nkechi Nnaji*/
        cardView = findViewById(R.id.btn_play_game);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionLevelHomeScreen.this, QuestionActivity.class);
                if(image != null) {
                    image.setDrawingCacheEnabled(true);
                    Bitmap b = image.getDrawingCache();
                    intent.putExtra("Bitmap", b);
                };
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

    //Added by Nkechi Nnaji
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selection_level_menu, menu);
        return true;
    }

    //Added by Nkechi Nnaji
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                return true;
        }
        return false;
    }


    //Added by Nkechi Nnaji for changing user profile image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK && data != null) {

            selectedImage = data.getData(); //get address of image in device
            Bitmap bitmap = null;
            try {
                image = findViewById(R.id.profile_avatar);
                //Display image
                image.setImageURI(selectedImage);
                image.setVisibility(View.VISIBLE);
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //Added/Modified by Nkechi Nnaji
    private void startMathQuiz(int levelChosen)
    {
        Intent playIntent = new Intent(this, PracticeLevelActivity.class);
        playIntent.putExtra("level", levelChosen);
        this.startActivity(playIntent);
    }

    //Added by Nkechi Nnaji
    //Event bus to register an event
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //Added by Nkechi Nnaji
    //Stop registering an event
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //Added by Nkechi Nnaji
    @Subscribe
    public void onEvent(Events event) {
        Toast.makeText(this, "Your profile image" + event.getImage(), Toast.LENGTH_SHORT).show();
    }
}
