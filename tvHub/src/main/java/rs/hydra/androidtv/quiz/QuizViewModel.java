package rs.hydra.androidtv.quiz;

import android.arch.lifecycle.ViewModel;
import rs.hydra.androidtv.quiz.model.QuizQuestion;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private List<QuizQuestion> quizQuestions;
    private int currentQuestion = 0;

    public void init() {
        quizQuestions = MockedQuestionGenerator.getMockedQuestions();
    }

    public QuizQuestion getNextQuestion() {
        QuizQuestion question = quizQuestions.get(currentQuestion);
        currentQuestion++;
        return question;
    }
}
