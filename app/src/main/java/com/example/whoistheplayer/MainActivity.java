package com.example.whoistheplayer;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
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
    Question[] questions = new Question[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize java variables with it's corresponding layout object
        imgCelebrity= findViewById(R.id.ivCelebrity);
        option1 = (Button)findViewById(R.id.option1);
        option2 = (Button)findViewById(R.id.option2);
        option3 = (Button)findViewById(R.id.option3);
        option4 = (Button)findViewById(R.id.option4);
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
        //
        // add questions to array
        questions[0] = q1;
        questions[1] = q2;
        questions[2] = q3;
        questions[3] = q4;
        questions[4] = q5;
        // finish creating questions

        // Start game with first question
        fillQA(0);
    }

    public void clickOption(View view) {
        String answer = ((Button)view).getText().toString();
        if(isCorrectAnswer(question, answer)){
            score ++;
            tvScore.setText(String.valueOf(score));
        }
        question++;
        if (question > questions.length -1) {
            finishGame();
        } else {
            fillQA(question);
        }
    }

    private void fillQA(int question){
        tvQuestion.setText("Question number " + String.valueOf(question+1));
        setImage(questions[question].imageName);
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
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Game finished")
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
        tvQuestion.setText("Question number " + String.valueOf(question+1));
        fillQA(question);
        tvScore.setText(String.valueOf(score));
    }
}