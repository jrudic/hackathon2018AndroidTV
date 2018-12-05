package rs.hydra.androidtv.quiz;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import rs.hydra.androidtv.R;
import rs.hydra.androidtv.quiz.model.QuestionUtility;
import rs.hydra.androidtv.quiz.model.QuizQuestion;

public class QuizActivity extends FragmentActivity {

    private static final long QUESTION_TIME = 3000;

    private LinearLayout numericAnswersLayout, multiAnswerLayout;

    private TextView timer;
    private TextView questionTitle;
    private Button answer1, answer2, answer3, answer4;
    private QuizViewModel viewModel;
    private ImageView questionImage;
    private Button nextQuestionButton;

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
        multiAnswerLayout = findViewById(R.id.multi_answer_layout);
        questionImage = findViewById(R.id.questionImage);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        setupListeners();
    }

    private void setupListeners() {
        nextQuestionButton.setOnClickListener(v -> nextQuestion());
    }

    private void startCounter() {
        new CountDownTimer(QUESTION_TIME, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                showSolution();
            }
        }.start();
    }

    private void showSolution() {
        nextQuestionButton.setVisibility(View.VISIBLE);
        markCorrectAnswer();
    }

    private void markCorrectAnswer() {
        int correct = viewModel.getCorrectAnswer();
        switch (correct) {
            case QuestionUtility.ANSWER_ONE:
                answer1.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case QuestionUtility.ANSWER_TWO:
                answer2.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case QuestionUtility.ANSWER_THREE:
                answer3.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case QuestionUtility.ANSWER_FOUR:
                answer4.setBackgroundColor(getResources().getColor(R.color.green));
                break;
        }
    }

    private void nextQuestion() {
        nextQuestionButton.setVisibility(View.GONE);
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
        questionImage.setVisibility(View.VISIBLE);
        multiAnswerLayout.setVisibility(View.VISIBLE);

        questionTitle.setText(question.question);
        answer1.setText(question.answers.get(QuestionUtility.ANSWER_ONE).answer);
        answer2.setText(question.answers.get(QuestionUtility.ANSWER_TWO).answer);
        answer3.setText(question.answers.get(QuestionUtility.ANSWER_THREE).answer);
        answer4.setText(question.answers.get(QuestionUtility.ANSWER_FOUR).answer);

        Glide.with(this).load(question.imageURL).into(questionImage);
    }

    private void showNumberQuestion(QuizQuestion question) {
        numericAnswersLayout.setVisibility(View.VISIBLE);
        questionImage.setVisibility(View.GONE);
        multiAnswerLayout.setVisibility(View.GONE);

        questionTitle.setText(question.question);
    }

    private void showMultiAnswerQuestion(QuizQuestion question) {
        questionImage.setVisibility(View.GONE);
        numericAnswersLayout.setVisibility(View.GONE);
        multiAnswerLayout.setVisibility(View.VISIBLE);

        questionTitle.setText(question.question);
        answer1.setText(question.answers.get(QuestionUtility.ANSWER_ONE).answer);
        answer2.setText(question.answers.get(QuestionUtility.ANSWER_TWO).answer);
        answer3.setText(question.answers.get(QuestionUtility.ANSWER_THREE).answer);
        answer4.setText(question.answers.get(QuestionUtility.ANSWER_FOUR).answer);
    }
}
