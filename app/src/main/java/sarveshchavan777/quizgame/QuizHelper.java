package sarveshchavan777.quizgame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizHelper extends SQLiteOpenHelper {
    Context context;
    private static final int DATABASE_VERSION = 13;

    private static final String DATABASE_NAME = "mathsone";
    public static final String TABLE_QUEST = "quest";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";

    public SQLiteDatabase dbase;
    public List<Question> theListOfQuestions;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
    }

    // modified by Clinton Avery
    private void addQuestion() {
        for(int i = 0; i < 21; i++){
            Question question = new Question(i);
            this.addQuestion(question);
        }
    }

    private void addQuestion(Question question, int i) {
        this.theListOfQuestions.add(question);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        onCreate(db);
    }

    // modified by Clinton Avery
    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());

        dbase.insert(TABLE_QUEST, null, values);
    }

    // modified by Clinton Avery
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);


        while (cursor.moveToNext()) {
            Question quest = new Question(0);
            quest.setID(cursor.getInt(0));
            quest.setQUESTION(cursor.getString(1));
            quest.setANSWER(cursor.getInt(2));

            quesList.add(quest);
        }

        return quesList;
    }


    public List<Question> getAllQuestions(int i) {
        return theListOfQuestions;
    }
}
