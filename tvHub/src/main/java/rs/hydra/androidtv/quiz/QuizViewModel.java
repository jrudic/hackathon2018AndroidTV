package rs.hydra.androidtv.quiz;

import android.arch.lifecycle.ViewModel;
import rs.hydra.androidtv.quiz.model.QuizQuestion;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private List<QuizQuestion> quizQuestions;
    private int currentQuestion = 0;

    void init() {
        quizQuestions = MockedQuestionGenerator.getMockedQuestions();
    }

    QuizQuestion getNextQuestion() {
        QuizQuestion question = quizQuestions.get(currentQuestion);
        currentQuestion++;
        return question;
    }

    int getCorrectAnswer() {
        for (int i = 0; i < quizQuestions.get(currentQuestion - 1).answers.size(); i++) {
            if (quizQuestions.get(currentQuestion - 1).answers.get(i).isCorrect) {
                return i;
            }
        }
        return 0;
    }
}
