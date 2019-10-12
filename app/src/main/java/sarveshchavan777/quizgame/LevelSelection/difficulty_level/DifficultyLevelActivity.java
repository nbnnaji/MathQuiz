package sarveshchavan777.quizgame.LevelSelection.difficulty_level;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import sarveshchavan777.quizgame.R;

public class DifficultyLevelActivity extends AppCompatActivity implements View.OnClickListener {

    private int level = 0, answer = 0, operator = 0, operand1 = 0, operand2 = 0;
    private final int ADD_OPERATOR = 0, SUBTRACT_OPERATOR = 1, MULTIPLY_OPERATOR = 2, DIVIDE_OPERATOR = 3;
    private String[] operators = {"+", "-", "x", "/"};
    private int[][] levelMin = {
            {1, 11, 21},
            {1, 5, 10},
            {2, 5, 10},
            {2, 3, 5}};
    private int[][] levelMax = {
            {10, 25, 50},
            {10, 20, 30},
            {5, 10, 15},
            {10, 50, 100}};
    private Random random;
    private TextView question, answerTxt, scoreTxt;
    private ImageView response;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterBtn, clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);
        question =  (TextView)findViewById(R.id.question);
        answerTxt = (TextView)findViewById(R.id.answer);
        response =  (ImageView)findViewById(R.id.response);
        scoreTxt =  (TextView)findViewById(R.id.score);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn0 = (Button)findViewById(R.id.btn0);
        enterBtn = (Button)findViewById(R.id.enter);
        clearBtn = (Button)findViewById(R.id.clear);

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

        random = new Random();
        chooseQuestion();
    }

    private void chooseQuestion(){
//get a question
        answerTxt.setText("= ?");
        operator = random.nextInt(operators.length);
        operand1 = getOperand();
        operand2 = getOperand();

        if(operator == SUBTRACT_OPERATOR){
            while(operand2>operand1){
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }

        else if(operator==DIVIDE_OPERATOR){
            while((((double)operand1/(double)operand2)%1 > 0) || (operand1==operand2))
            {
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }

        switch(operator)
        {
            case ADD_OPERATOR:
                answer = operand1+operand2;
                break;
            case SUBTRACT_OPERATOR:
                answer = operand1-operand2;
                break;
            case MULTIPLY_OPERATOR:
                answer = operand1*operand2;
                break;
            case DIVIDE_OPERATOR:
                answer = operand1/operand2;
                break;
            default:
                break;
        }

        question.setText(operand1+" "+operators[operator]+" "+operand2);
    }

    private int getOperand(){
        //return operand number
        return random.nextInt(levelMax[operator][level] - levelMin[operator][level] + 1)
                + levelMin[operator][level];
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
                }
                else{
                    //incorrect
                    scoreTxt.setText("Score: 0");
                    response.setImageResource(R.drawable.ic_wrong);
                    response.setVisibility(View.VISIBLE);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
