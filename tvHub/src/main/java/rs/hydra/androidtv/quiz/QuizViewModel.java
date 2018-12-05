package rs.hydra.androidtv.quiz;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import rs.hydra.androidtv.quiz.model.QuizAnswer;
import rs.hydra.androidtv.quiz.model.QuizQuestion;
import rs.hydra.androidtv.quiz.user.User;
import rs.hydra.androidtv.quiz.user.UserAdapter;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private List<QuizQuestion> quizQuestions;
    private int currentQuestion = 0;

    void init(Context context) {
        quizQuestions = MockedQuestionGenerator.getMockedQuestions(context);
    }

    QuizQuestion getNextQuestion() {
        QuizQuestion question = quizQuestions.get(currentQuestion);
        currentQuestion++;
        return question;
    }

    int currentQuestionType() {
        return quizQuestions.get(currentQuestion - 1).type;
    }

    int getCorrectAnswerPosition() {
        for (int i = 0; i < quizQuestions.get(currentQuestion - 1).answers.size(); i++) {
            if (quizQuestions.get(currentQuestion - 1).answers.get(i).isCorrect) {
                return i;
            }
        }
        return 0;
    }

    public QuizAnswer getCorrectAnswer() {
        return quizQuestions.get(currentQuestion - 1).answers.get(0);
    }

    public boolean isQuizFinished() {
        return quizQuestions.size() <= currentQuestion;
    }

    public RecyclerView.Adapter getUserAdapter(Context context, List<User> users) {
        return new UserAdapter(context, users);
    }
}
