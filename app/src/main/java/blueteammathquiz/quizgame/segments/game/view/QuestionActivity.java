package blueteammathquiz.quizgame.segments.game.view;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import blueteammathquiz.quizgame.segments.game.model.Question;
import blueteammathquiz.quizgame.R;
import blueteammathquiz.quizgame.segments.game.model.QuizHelper;
import blueteammathquiz.quizgame.segments.home.view.RecieverReadyEvent;

import static blueteammathquiz.quizgame.R.drawable.ic_profile;


public class QuestionActivity extends Activity {
    List<Question> quesList;
    int score = 0;
    int qid = 0;

    Question currentQ;
    TextView txtQuestion, times, scored;
    EditText whereTheAnswerGetsTyped;
    Button button1; // modified by Clinton Avery
    CharSequence submitLabel;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra("Bitmap"); //Added by Nkechi Nnaji

        QuizHelper db = new QuizHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        button1 = (Button) findViewById(R.id.button1);
        profileImage = findViewById(R.id.player_avatar);

        //Added by Nkechi Nnaji
        //Check for bitmap if added by user or use default image
        if(bitmap == null){
            profileImage.setBackgroundResource(R.drawable.ic_profile);
            profileImage.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Upload your profile picture from homescreen", Toast.LENGTH_SHORT).show();

                }
            });

        }

        profileImage.setImageBitmap(bitmap); //Added by Nkechi Nnaji

        whereTheAnswerGetsTyped = (EditText) findViewById(R.id.theUserTypesTheAnswerHere);
        showSoftKeyboard(whereTheAnswerGetsTyped);

        scored = (TextView) findViewById(R.id.score);

        times = (TextView) findViewById(R.id.timers);

        setQuestionView();
        times.setText("00:02:00");

        CounterClass timer = new CounterClass(60000, 1000);
        timer.start();

        // modified by Clinton Avery
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(whereTheAnswerGetsTyped.getText().toString());
            }
        });
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.ANSWER == Integer.parseInt(AnswerString)) {
            score++;
            scored.setText("Score : " + score);
        } else {

            Intent intent = new Intent(QuestionActivity.this,
                    ResultActivity.class);

            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
        if (qid < 21) {
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {
            Intent intent = new Intent(QuestionActivity.this,WonActivity.class);
            Bundle b = new Bundle();
            b.putInt("score",score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            times.setText("Time is up");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;

            @SuppressLint("DefaultLocale") //Added by Nkechi Nnaji
                    String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }


    }

    private void setQuestionView() {

        // the method which will put all things together
        whereTheAnswerGetsTyped.setText("");
        txtQuestion.setText(currentQ.getQUESTION());

        qid++;
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

}