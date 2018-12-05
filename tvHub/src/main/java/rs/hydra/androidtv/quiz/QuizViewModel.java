package rs.hydra.androidtv.quiz;

import android.arch.lifecycle.ViewModel;
import rs.hydra.androidtv.quiz.model.QuizQuestion;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private List<QuizQuestion> quizQuestions;

    public void init() {
        quizQuestions = MockedQuestionGenerator.getMockedQuestions();
    }
}
