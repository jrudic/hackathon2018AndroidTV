package rs.hydra.androidtv.quiz;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import rs.hydra.androidtv.R;
import rs.hydra.androidtv.quiz.model.QuestionUtility;
import rs.hydra.androidtv.quiz.model.QuizQuestion;

public class QuizActivity extends AppCompatActivity {

    private static final long QUESTION_TIME = 30000;

    private LinearLayout numericAnswersLayout, multiAnsverLayout;

    private TextView timer;
    private TextView questionTitle;
    private Button answer1, answer2, answer3, answer4;
    private QuizViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        viewModel.init();

        initViews();
        nextQuestion();
    }

    private void initViews() {
        timer = findViewById(R.id.timerTextView);
        questionTitle = findViewById(R.id.questionTitle);
        numericAnswersLayout = findViewById(R.id.numeric_answer_layout);
        multiAnsverLayout = findViewById(R.id.multi_answer_layout);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
    }


    private void startCounter() {
        new CountDownTimer(QUESTION_TIME, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                nextQuestion();
            }
        }.start();
    }

    private void nextQuestion() {
        startCounter();
        showQuestion(viewModel.getNextQuestion());
    }

    private void showQuestion(QuizQuestion question) {
        switch (question.type) {
            case QuestionUtility.QuestionType.MULTI_ANSWERS:
                showMultiAnswerQuestion(question);
                break;
            case QuestionUtility.QuestionType.NUMBER:
                showNumberQuestion(question);
                break;
            case QuestionUtility.QuestionType.PICTURE:
                showPictureQuestion(question);
                break;
        }
    }

    private void showPictureQuestion(QuizQuestion question) {
        numericAnswersLayout.setVisibility(View.GONE);
        multiAnsverLayout.setVisibility(View.VISIBLE);

    }

    private void showNumberQuestion(QuizQuestion question) {
        numericAnswersLayout.setVisibility(View.GONE);
        multiAnsverLayout.setVisibility(View.VISIBLE);

    }

    private void showMultiAnswerQuestion(QuizQuestion question) {
        numericAnswersLayout.setVisibility(View.GONE);
        multiAnsverLayout.setVisibility(View.VISIBLE);

        questionTitle.setText(question.question);
        answer1.setText(question.answers.get(QuestionUtility.ANSWER_ONE).answer);
        answer2.setText(question.answers.get(QuestionUtility.ANSWER_TWO).answer);
        answer3.setText(question.answers.get(QuestionUtility.ANSWER_THREE).answer);
        answer4.setText(question.answers.get(QuestionUtility.ANSWER_FOUR).answer);
    }
}
