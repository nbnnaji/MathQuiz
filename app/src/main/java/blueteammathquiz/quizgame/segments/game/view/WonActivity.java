package blueteammathquiz.quizgame.segments.game.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import blueteammathquiz.quizgame.R;

/**
 * Created by LENOVO on 1/3/2017.
 * Modified by Nkechi Nnaji on October 13, 2019
 */

public class WonActivity extends Activity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_won);
//        tv = findViewById(R.id.congo);
//        Bundle b = getIntent().getExtras();
//        int y = b.getInt("score");
//
//        //Modified by Nkechi Nnaji
//        StringBuilder builder = new StringBuilder();
//        builder.append(getString(R.string.final_score)).append(" ").append(y);
//        tv.setText(builder);
//
//        TextView textView = findViewById(R.id.title);
//        textView.setText(R.string.congratulations); //Modified by Jayesh Shah

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        tv = findViewById(R.id.congo);
        Bundle b = getIntent().getExtras();
        if(b == null) {
            return;
        }
        int score = b.getInt("score");
        int totalQuestionsCount = b.getInt("totalQuestionsCount");
        String resultText = String.format("Your score: %s/%s", score, totalQuestionsCount);
        tv.setText(resultText);
    }
}
