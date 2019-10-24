package blueteammathquiz.quizgame.segments.game.model;

import android.app.Activity;

public class Question extends Activity {

    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;

    public int ANSWER;

    // modified by Clinton Avery
    public Question(int id) {
        int firstDigit;
        int secondDigit;
        ID = id;
        firstDigit = (int)Math.floor(Math.random() * 10);
        secondDigit = (int)Math.floor(Math.random() * 10);
        QUESTION = getRandomQuestion(firstDigit, secondDigit);
        ANSWER = getAnswerToRandomQuestion(firstDigit, secondDigit);
    }

    // added by Clinton Avery
    private String getRandomQuestion(int firstDigit, int secondDigit){
        String theQuestionOutput = String.format("%s X %s = ", firstDigit, secondDigit);
        return theQuestionOutput;
    }

    // added by Clinton Avery
    private int getAnswerToRandomQuestion(int firstDigit, int secondDigit){
        int theAnswerToTheRandomQuestion = firstDigit * secondDigit;
        return theAnswerToTheRandomQuestion;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getOPTB() {
        return OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public int getANSWER() {
        return ANSWER;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }

    public void setANSWER(int aNSWER) {
        ANSWER = aNSWER;
    }

}

// @Override
// public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
// getMenuInflater().inflate(R.menu.main, menu);
// return true;
// }
