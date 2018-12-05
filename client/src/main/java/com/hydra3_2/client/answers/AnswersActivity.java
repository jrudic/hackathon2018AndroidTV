package com.hydra3_2.client.answers;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hydra3_2.client.R;

public class AnswersActivity extends AppCompatActivity implements OptionsAnswersFragment.OnAnswerChoosedListener,
        NumberInputAnswersFragment.OnAnswerTypedListener {

    public static final int PRIMARY_ANSWER_TYPE = 0;
    public static final int SECODNARY_ANSWER_TYPE = 1;
    private FragmentManager fragmentManager;
    private int answerType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle("Home Pub Quiz");

        if (answerType == 0) {
            displayOptionsAnswerScreen();
        } else {
            displayNumberInputAnswerScreen();
        }

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

    @Override
    public void onAnswerChoosed(String answer) {
        displayNumberInputAnswerScreen();
    }

    @Override
    public void onAnswerTyped(String answer) {
        displayOptionsAnswerScreen();
    }
}
