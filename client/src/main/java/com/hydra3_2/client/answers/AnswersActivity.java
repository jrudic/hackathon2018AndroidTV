package com.hydra3_2.client.answers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.hydra3_2.client.R;

public class AnswersActivity extends AppCompatActivity implements OptionsAnswersFragment.OnAnswerChoosedListener,
        NumberInputAnswersFragment.OnAnswerTypedListener, ProfileFragment.OnConnectPressedListener, ResultFragment.OnResultButtonsListener {

    public static final int PRIMARY_ANSWER_TYPE = 0;
    public static final int SECODNARY_ANSWER_TYPE = 1;
    private FragmentManager fragmentManager;
    private int answerType = 0;
    private int stepsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle("Home Pub Quiz");
        displayProfileScreen();
    }

    private void displayOptionsAnswerScreen() {
        OptionsAnswersFragment optionsAnswersFragment;

        optionsAnswersFragment = (OptionsAnswersFragment) fragmentManager
                .findFragmentByTag(OptionsAnswersFragment.TAG);
        if (optionsAnswersFragment == null) {
            optionsAnswersFragment =
                    OptionsAnswersFragment.newInstance("Zasto je Ruki nervozan?");

        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, optionsAnswersFragment,
                        OptionsAnswersFragment.TAG).commit();
    }

    private void displayNumberInputAnswerScreen() {
        NumberInputAnswersFragment numberInputAnswersFragment;

        numberInputAnswersFragment = (NumberInputAnswersFragment) fragmentManager
                .findFragmentByTag(NumberInputAnswersFragment.TAG);
        if (numberInputAnswersFragment == null) {
            numberInputAnswersFragment =
                    NumberInputAnswersFragment.newInstance("Zasto je Ruki nervozan?");

        }
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
        if (stepsCount == 0) {
            displayOptionsAnswerScreen();
        } else if (stepsCount == 1) {
            displayNumberInputAnswerScreen();
        } else if (stepsCount == 2) {
            displayNumberInputAnswerScreen();
        } else if (stepsCount == 3) {
            displayOptionsAnswerScreen();
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
