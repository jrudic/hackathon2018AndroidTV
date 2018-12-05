package rs.hydra.androidtv.quiz;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import rs.hydra.androidtv.R;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
    }
}
