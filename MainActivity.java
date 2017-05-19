package game.alg.com.game;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity  {

    private GameLogic gameLogic;
    private Player gamePlayer;
    private boolean isGameStarted = false;

    private Button submitButton, finishButton;
    private TextView question;
    private TextView score;
    private TextView welcomeText;
    private EditText answerField;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLogic = new GameLogic();
        gamePlayer = new Player();
        initializeComponents();

        submitButton.setText("SART GAME");
        answerField.setVisibility(View.GONE);
        finishButton.setVisibility(View.GONE);
    }

    private void newQuestion() {
        String qn = gameLogic.getNextQuestion();
        gamePlayer.setQuestion(qn);
        question.setText("Question: " + qn + " ?");
        score.setText("Score: " + gamePlayer.getScore());
        answerField.setText("");
    }

    private void initializeComponents() {
        submitButton = (Button) findViewById(R.id.submit_button);
        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        answerField = (EditText) findViewById(R.id.answer);
        finishButton = (Button)findViewById(R.id.finish_button);


        layout = (RelativeLayout)findViewById(R.id.RelativeL);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameStarted) {
                    startGame();

                } else {
                    Toast.makeText(MainActivity.this, "Game started.", Toast.LENGTH_SHORT).show();
                    isGameStarted = true;
                    welcomeText.setVisibility(View.GONE);
                    submitButton.setText("SUBMIT");
                    answerField.setVisibility(VISIBLE);

                    finishButton.setVisibility(VISIBLE);
                    layout.setBackgroundResource(R.drawable.down);
                    newQuestion();

                }
            }
        });

        finishButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);
                    }
                }
        );



    }

    private void startGame() {
        char userAnswer = answerField.getText().charAt(0);
        gamePlayer.setAnswer(userAnswer);
//
//        String myName = answerField.getText().toString();
//        if (myName == "doston") {
//            Intent intent = new Intent("game.alg.com.game.Details");
//            startActivity(intent);
//        }
        boolean status = gameLogic.checkAnswer(gamePlayer);
        if (status) {
            Toast.makeText(MainActivity.this, "Correct Answer.", Toast.LENGTH_SHORT).show();
            gamePlayer.setScore(gamePlayer.getScore() + 1);
            layout.setBackgroundResource(R.drawable.two);
        } else {
            Toast.makeText(MainActivity.this, "Incorrect Answer.", Toast.LENGTH_SHORT).show();
            layout.setBackgroundResource(R.drawable.one);
        }




        newQuestion();
    }
    public void changeColor(){

    }

}
