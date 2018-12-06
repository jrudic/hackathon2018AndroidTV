package com.hydra3_2.client.answers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.hydra3_2.client.R;

import java.util.List;

public class AnswersActivity extends AppCompatActivity implements OptionsAnswersFragment.OnAnswerChoosedListener,
        NumberInputAnswersFragment.OnAnswerTypedListener, ProfileFragment.OnConnectPressedListener, ResultFragment.OnResultButtonsListener {
    public static final String QUESTION_KEY = "question_key";
    public static final int PRIMARY_ANSWER_TYPE = 0;
    public static final int SECODNARY_ANSWER_TYPE = 1;
    private FragmentManager fragmentManager;
    private int answerType = 0;
    private int stepsCount = 0;
    List<QuizQuestion> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle("Home Pub Quiz");
        questionList = MockedQuestionGenerator.getMockedQuestions(this);
        displayProfileScreen();
    }

    private void displayOptionsAnswerScreen(QuizQuestion quizQuestion) {
        OptionsAnswersFragment optionsAnswersFragment;

        optionsAnswersFragment = (OptionsAnswersFragment) fragmentManager
                .findFragmentByTag(OptionsAnswersFragment.TAG);
//        if (optionsAnswersFragment == null) {
        optionsAnswersFragment =
                OptionsAnswersFragment.newInstance(quizQuestion.question);

        //  }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, optionsAnswersFragment,
                        OptionsAnswersFragment.TAG).commit();
    }

    private void displayNumberInputAnswerScreen(QuizQuestion quizQuestion) {
        NumberInputAnswersFragment numberInputAnswersFragment;

        numberInputAnswersFragment = (NumberInputAnswersFragment) fragmentManager
                .findFragmentByTag(NumberInputAnswersFragment.TAG);
        // if (numberInputAnswersFragment == null) {
        numberInputAnswersFragment =
                NumberInputAnswersFragment.newInstance(quizQuestion.question);

        //    }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, numberInputAnswersFragment,
                        NumberInputAnswersFragment.TAG).commit();
    }

    private void displayProfileScreen() {
        ProfileFragment profileFragment;

        profileFragment = (ProfileFragment) fragmentManager
                .findFragmentByTag(ProfileFragment.TAG);
        if (profileFragment == null) {
            profileFragment =
                    ProfileFragment.newInstance();

        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, profileFragment,
                        ProfileFragment.TAG).commit();
    }

    private void displayResultScreen() {
        ResultFragment resultFragment;

        resultFragment = (ResultFragment) fragmentManager
                .findFragmentByTag(ResultFragment.TAG);
        if (resultFragment == null) {
            resultFragment =
                    ResultFragment.newInstance();

        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, resultFragment,
                        ResultFragment.TAG).commit();
    }


    @Override
    public void onAnswerChoosed(String answer) {
        showNextFragment();
    }

    private void showNextFragment() {
        if (stepsCount < questionList.size()) {
            QuizQuestion quizQuestion  = questionList.get(stepsCount);
            switch (quizQuestion.type) {
                case 0:
                case 1:
                    displayOptionsAnswerScreen(quizQuestion);
                    break;
                case 2:
                    displayNumberInputAnswerScreen(quizQuestion);
                    break;
            }
        } else {
            displayResultScreen();
        }

        stepsCount++;
    }

    @Override
    public void onAnswerTyped(String answer) {
        showNextFragment();
    }

    @Override
    public void onConnectPressed() {
        showNextFragment();
    }

    @Override
    public void onPlayAgainPressed() {
        displayProfileScreen();
        stepsCount = 0;
    }
}
