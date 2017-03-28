package com.nehvin.s05e72appbraintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button hideGoBtn = null;
    Button btnChoice1 = null;
    Button btnChoice2 = null;
    Button btnChoice3 = null;
    Button btnChoice4 = null;
    CountDownTimer cd = null;
    TextView countDownTimerView = null;
    TextView mathSumView = null;
    TextView scoreCountView = null;
    TextView answerTextView = null;
    Button startAgainbtn = null;
    Random rand = null;
    int firstNumber;
    int secondNumber;
    List<Integer> answers = null;
    int locationOfCorrectAnswer;
    int totalAnswered=0;
    int totalCorrectAnswers=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        hideGoBtn = (Button) findViewById(R.id.goButton);
        hideGoBtn.setVisibility(View.VISIBLE);

        btnChoice1 = (Button) findViewById(R.id.btnChoice1);
        btnChoice1.setVisibility(View.INVISIBLE);

        btnChoice2 = (Button) findViewById(R.id.btnChoice2);
        btnChoice2.setVisibility(View.INVISIBLE);

        btnChoice3= (Button) findViewById(R.id.btnChoice3);
        btnChoice3.setVisibility(View.INVISIBLE);

        btnChoice4 = (Button) findViewById(R.id.btnChoice4);
        btnChoice4.setVisibility(View.INVISIBLE);

        countDownTimerView = (TextView) findViewById(R.id.countDownTimerView);
        countDownTimerView.setVisibility(View.INVISIBLE);

        mathSumView = (TextView) findViewById(R.id.mathSum);
        mathSumView.setVisibility(View.INVISIBLE);

        scoreCountView = (TextView) findViewById(R.id.scoreCount);
        scoreCountView.setVisibility(View.INVISIBLE);

        answerTextView = (TextView)findViewById(R.id.answerTextView);
        answerTextView.setVisibility(View.INVISIBLE);

        startAgainbtn = (Button) findViewById(R.id.startAgainBtn);
        startAgainbtn.setVisibility(View.INVISIBLE);

        rand = new Random();
        answers = new ArrayList<Integer>();
        totalAnswered=0;
        totalCorrectAnswers=0;
    }

    public void hideGoBtn(View view)
    {
        hideGoBtn.setVisibility(View.GONE);
        btnChoice1.setVisibility(View.VISIBLE);
        btnChoice2.setVisibility(View.VISIBLE);
        btnChoice3.setVisibility(View.VISIBLE);
        btnChoice4.setVisibility(View.VISIBLE);
        countDownTimerView.setVisibility(View.VISIBLE);
        scoreCountView.setVisibility(View.VISIBLE);
        mathSumView.setVisibility(View.VISIBLE);
        answerTextView.setVisibility(View.VISIBLE);
        startCounter();
    }

    private void startCounter() {
        generateRandomEquation();
        cd = new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000 < 10)
                {
                    countDownTimerView.setText("0"+Long.toString(millisUntilFinished / 1000) + "s");
                }
                else {
                    countDownTimerView.setText(Long.toString(millisUntilFinished / 1000) + "s");
                }
            }

            @Override
            public void onFinish() {
                countDownTimerView.setText("00s");
                btnChoice1.setClickable(false);
                btnChoice2.setClickable(false);
                btnChoice3.setClickable(false);
                btnChoice4.setClickable(false);
                startAgainbtn.setVisibility(View.VISIBLE);
                //display final score
            }
        }.start();
    }

    public void reinitialize()
    {
        totalAnswered=0;
        totalCorrectAnswers=0;
        scoreCountView.setText(totalCorrectAnswers+"/"+totalAnswered);
        countDownTimerView.setText("30s");
        answerTextView.setText("");
        startAgainbtn.setVisibility(View.INVISIBLE);
        btnChoice1.setClickable(true);
        btnChoice2.setClickable(true);
        btnChoice3.setClickable(true);
        btnChoice4.setClickable(true);
        startCounter();
    }

    public void startAgain(View view)
    {
        reinitialize();
    }

    private void generateRandomEquation()
    {
        firstNumber = (int) (rand.nextInt(20) + 1);
        secondNumber = (int) (rand.nextInt(20) + 1);
        int correctAnswer = firstNumber+secondNumber;
        mathSumView.setText(firstNumber + " + "+secondNumber);
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;

        for (int i=0;i<4;i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(i,correctAnswer);
            }
            else
            {
                incorrectAnswer = rand.nextInt(40)+1;
                while(incorrectAnswer == correctAnswer)
                {
                    incorrectAnswer = rand.nextInt(40)+1;
                }
                answers.add(i,incorrectAnswer);
            }
        }
        btnChoice1.setText(Integer.toString(answers.get(0)));
        btnChoice2.setText(Integer.toString(answers.get(1)));
        btnChoice3.setText(Integer.toString(answers.get(2)));
        btnChoice4.setText(Integer.toString(answers.get(3)));
    }

    public void btnCLick(View view)
    {
        if(Integer.parseInt(view.getTag().toString()) == locationOfCorrectAnswer)
        {
            answerTextView.setText("Correct Answer!!");
            totalCorrectAnswers++;
        }
        else
        {
            answerTextView.setText("Wrong Answer!!!");
        }
        totalAnswered++;
        scoreCountView.setText(totalCorrectAnswers+"/"+totalAnswered);
        generateRandomEquation();
    }
}