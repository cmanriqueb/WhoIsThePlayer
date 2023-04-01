package com.example.whoistheplayer;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static class Answer{
        String answerText;
        Boolean isCorrect;

        public Answer(String answerText, Boolean isCorrect){
            this.answerText = answerText;
            this.isCorrect = isCorrect;
        }
    }
    static class Question{
        String imageName;
        Answer[] answers;

        public Question(String imageName, Answer[] answers){
            this.imageName = imageName;
            this.answers = answers;
        }
    }

    ImageView imgCelebrity;
    int question = 0;
    Button option1, option2, option3, option4;
    TextView tvScore, tvQuestion;
    int score = 0;
    int consecutiveGuessedToWin = 3;
    int consecutiveGuessed = 0;
    Question[] questions = new Question[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize java variables with it's corresponding layout object
        imgCelebrity= findViewById(R.id.ivCelebrity);
        option1 = (Button)findViewById(R.id.option1);
        option1.setBackgroundResource(android.R.drawable.btn_default);
        option2 = (Button)findViewById(R.id.option2);
        option2.setBackgroundResource(android.R.drawable.btn_default);
        option3 = (Button)findViewById(R.id.option3);
        option3.setBackgroundResource(android.R.drawable.btn_default);
        option4 = (Button)findViewById(R.id.option4);
        option4.setBackgroundResource(android.R.drawable.btn_default);
        // initialize text views
        tvScore = findViewById(R.id.txtScore);
        tvScore.setText(String.valueOf(score));
        tvQuestion = findViewById(R.id.txtQuestion);
        tvQuestion.setText("Question number " + String.valueOf(question+1));

        // Create Questions
        //
        // Question #1
        Answer q1a1 = new Answer("Messi", false);
        Answer q1a2 = new Answer("Cristiano Ronaldo", true);
        Answer q1a3 = new Answer("Ronaldinho", false);
        Answer q1a4 = new Answer("Xavi", false);
        Answer[] q1answers = new Answer[]{q1a1,q1a2,q1a3,q1a4};
        Question q1 = new Question("cr7",q1answers);
        //
        // Question #2
        Answer q2a1 = new Answer("Bad Bunny", false);
        Answer q2a2 = new Answer("John Secada", false);
        Answer q2a3 = new Answer("Juan Luis Guerra", true);
        Answer q2a4 = new Answer("Wilfrido Vargas", false);
        Answer[] q2answers = new Answer[]{q2a1,q2a2,q2a3,q2a4};
        Question q2 = new Question("juanluisguerra",q2answers);
        //
        // Question #3
        Answer q3a1 = new Answer("Mila Kunis", true);
        Answer q3a2 = new Answer("Ariana Grande ", false);
        Answer q3a3 = new Answer("Selena Gomez", false);
        Answer q3a4 = new Answer("Emma Watson", false);
        Answer[] q3answers = new Answer[]{q3a1,q3a2,q3a3,q3a4};
        Question q3 = new Question("milakunis",q3answers);
        //
        // Question #4
        Answer q4a1 = new Answer("Paolo Sorrentino", false);
        Answer q4a2 = new Answer("Will Smith", false);
        Answer q4a3 = new Answer("Antonio Banderas", false);
        Answer q4a4 = new Answer("Pedro Almodóvar", true);
        Answer[] q4answers = new Answer[]{q4a1,q4a2,q4a3,q4a4};
        Question q4 = new Question("pedroalmodovar",q4answers);
        //
        // Question #5
        Answer q5a1 = new Answer("Quentin Tarantino", false);
        Answer q5a2 = new Answer("Bong Joon-ho", true);
        Answer q5a3 = new Answer("Ryusuke Hamaguchi", false);
        Answer q5a4 = new Answer("Noah Baumbach", false);
        Answer[] q5answers = new Answer[]{q5a1,q5a2,q5a3,q5a4};
        Question q5 = new Question("bongjoonho",q5answers);
        //
        // Question #6
        Answer q6a1 = new Answer("Pedro Pascal", true);
        Answer q6a2 = new Answer("David Coperfield ", false);
        Answer q6a3 = new Answer("Luke Skywalker", false);
        Answer q6a4 = new Answer("Luis Enrique", false);
        Answer[] q6answers = new Answer[]{q6a1,q6a2,q6a3,q6a4};
        Question q6 = new Question("pedropascal",q6answers);
        //
        // Question #7
        Answer q7a1 = new Answer("Adele", false);
        Answer q7a2 = new Answer("Madona", false);
        Answer q7a3 = new Answer("Avril Lavigne", false);
        Answer q7a4 = new Answer("Rihana", true);
        Answer[] q7answers = new Answer[]{q7a1,q7a2,q7a3,q7a4};
        Question q7 = new Question("rihana",q7answers);
        //
        // Question #8
        Answer q8a1 = new Answer("Alfred", false);
        Answer q8a2 = new Answer("Michael Caine", true);
        Answer q8a3 = new Answer("Bruce Willis", false);
        Answer q8a4 = new Answer("Michael Douglas", false);
        Answer[] q8answers = new Answer[]{q8a1,q8a2,q8a3,q8a4};
        Question q8 = new Question("michaelcaine",q8answers);
        //
        //
        // add questions to array
        questions[0] = q1;
        questions[1] = q2;
        questions[2] = q3;
        questions[3] = q4;
        questions[4] = q5;
        questions[5] = q6;
        questions[6] = q7;
        questions[7] = q8;
        // finish creating questions

        // Start game with first question
        fillQA(0);
    }

    public void clickOption(View view) {
        String answer = ((Button)view).getText().toString();
        if(isCorrectAnswer(question, answer)){
            Toast toast = Toast.makeText(getApplicationContext(),"CORRECT!!",Toast.LENGTH_SHORT);
            toast.getView().setBackgroundColor(Color.parseColor("#00FF00"));
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
            score ++;
            consecutiveGuessed++;
            tvScore.setText(String.valueOf(score));
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"INCORRECT!!",Toast.LENGTH_SHORT);
            toast.getView().setBackgroundColor(Color.parseColor("#FF0000"));
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
            consecutiveGuessed=0;
        }
        question++;
        if (question > questions.length -1 || consecutiveGuessed == consecutiveGuessedToWin) {
            finishGame();
        } else {
            fillQA(question);
        }
    }

    private void fillQA(int question){
        tvQuestion.setText("Question number " + String.valueOf(question+1));
        setImage(questions[question].imageName);
        //option1.setBackground(defaultButtonBackground);
        option1.setText(questions[question].answers[0].answerText);
        option2.setText(questions[question].answers[1].answerText);
        option3.setText(questions[question].answers[2].answerText);
        option4.setText(questions[question].answers[3].answerText);
    }


    private boolean isCorrectAnswer(int questionNumber, String answerText) {
        Boolean answer = false;
        Question tempQuestion = questions[questionNumber];
        Log.d("questionNumber",String.valueOf(questionNumber));
        Log.d("answerText",answerText);
        for(int i=0; i <=3; i++){
            if (tempQuestion.answers[i].answerText == answerText){
                //Log.d("tempQuestion.answers[i].isCorrect",String.valueOf(tempQuestion.answers[i].isCorrect));
                answer = (Boolean)tempQuestion.answers[i].isCorrect;
            }
        }
        return answer;
    }

    private void setImage(String imageName) {
        Log.d("imageName",imageName);
        Resources res = getResources();
        int resID = res.getIdentifier(imageName , "drawable", getPackageName());
        imgCelebrity.setImageResource(resID);
    }

    private void finishGame() {
        String alertMessage;
        if (score == consecutiveGuessedToWin)
        {
            alertMessage = "CONGRATULATIONS!!";
        }else{
            alertMessage = "No luck this time... ";
        }
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle(alertMessage)
                //set message
                .setMessage("Do you want to play again?")
                //set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        restartGame();
                        Toast.makeText(getApplicationContext(),"Let's go!",Toast.LENGTH_LONG).show();
                    }
                })
                //set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        finish();
                    }
                })
                .show();
    }

    private void restartGame() {
        score = 0;
        question = 0;
        consecutiveGuessed = 0;
        tvQuestion.setText("Question number " + String.valueOf(question+1));
        fillQA(question);
        tvScore.setText(String.valueOf(score));
    }

    public void goToHowToPlay(View view){
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
}