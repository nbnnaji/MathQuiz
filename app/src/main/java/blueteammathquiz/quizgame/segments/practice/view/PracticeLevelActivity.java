package blueteammathquiz.quizgame.segments.practice.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import blueteammathquiz.quizgame.segments.home.view.SelectionLevelHomeScreen;
import blueteammathquiz.quizgame.R;

public class PracticeLevelActivity extends AppCompatActivity implements View.OnClickListener {

    /*
        Variables
     */
    private int level , answer, operator, operand1, operand2  = 0;

    /*
        Operators
     */
    private final int ADD = 0, SUBTRACT = 1, MULTIPLY = 2, DIVIDE = 3;

    /*
    Array of operators
     */
    private String[] operators = {"+", "-", "x", "/"};

    /*
    Array of:
    Levels (vertical): Easy, medium & Hard
    Operators (horizontal) : Addition, subtraction, multiplication & division
     */
    private int[][] minimumLevel = {
            {1, 11, 21},
            {1, 5, 10},
            {2, 5, 10},
            {2, 3, 5}};
    private int[][] maximumLevel = {
            {10, 25, 50},
            {10, 20, 30},
            {5, 10, 15},
            {10, 50, 100}};

    /*
       Instance of Random class
     */
    private Random random;

    /*
    Question, answer text & score text
     */
    private TextView question, answerTxt, scoreTxt;

    /*
        Response
     */
    private ImageView response;

    /*
        Keyboard buttons
     */
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterBtn, clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_level);

        Toolbar toolbar = findViewById(R.id.practice_level_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setTitle("Practice");
        }

        question =  findViewById(R.id.question);
        answerTxt = findViewById(R.id.answer);
        response =  findViewById(R.id.response);
        scoreTxt =  findViewById(R.id.score);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        enterBtn = findViewById(R.id.enter);
        clearBtn = findViewById(R.id.clear);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        enterBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            int passedLevel = extras.getInt("level", -1);
            if(passedLevel>=0) level = passedLevel;
        }


        random = new Random();
        chooseQuestion();
    }

    private void chooseQuestion(){
//get a question
        answerTxt.setText("= ?");
        operator = random.nextInt(operators.length);
        operand1 = getOperand();
        operand2 = getOperand();

        if(operator == SUBTRACT){
            while(operand2>operand1){
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }

        else if(operator==DIVIDE){
            while((((double)operand1/(double)operand2)%1 > 0) || (operand1==operand2))
            {
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }

        switch(operator)
        {
            case ADD:
                answer = operand1+operand2;
                break;
            case SUBTRACT:
                answer = operand1-operand2;
                break;
            case MULTIPLY:
                answer = operand1*operand2;
                break;
            case DIVIDE:
                answer = operand1/operand2;
                break;
            default:
                break;
        }

        question.setText(operand1+" "+operators[operator]+" "+operand2);
    }

    private int getOperand(){
        //return operand number
        return random.nextInt(maximumLevel[operator][level] - minimumLevel[operator][level] + 1)
                + minimumLevel[operator][level];
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.enter){
            //enter button
            String answerContent = answerTxt.getText().toString();
            if(!answerContent.endsWith("?"))
            {
                //we have an answer
                int enteredAnswer = Integer.parseInt(answerContent.substring(2));
                int exScore = getScore();
                if(enteredAnswer==answer){
                    //correct
                    scoreTxt.setText("Score: "+(exScore+1));
                    response.setImageResource(R.drawable.ic_right);
                    response.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Well Done!", Toast.LENGTH_SHORT).show();

                }
                else{
                    //incorrect
                    scoreTxt.setText("Score: 0");
                    response.setImageResource(R.drawable.ic_wrong);
                    response.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Wrong answer!", Toast.LENGTH_LONG).show();

                }
                chooseQuestion();
            }

        }
        else if(view.getId()==R.id.clear){
            //clear button
            answerTxt.setText("= ?");
        }
        else {
            //number button
            response.setVisibility(View.INVISIBLE);
            int enteredNum = Integer.parseInt(view.getTag().toString());
            if(answerTxt.getText().toString().endsWith("?"))
                answerTxt.setText("= "+enteredNum);
            else
                answerTxt.append(""+enteredNum);
        }
    }

    private int getScore(){
        String scoreStr = scoreTxt.getText().toString();
        return Integer.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ")+1));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(PracticeLevelActivity.this, SelectionLevelHomeScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
