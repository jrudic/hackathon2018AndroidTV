package rs.hydra.androidtv.quiz.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class QuestionUtility {

    public static final int ANSWER_ONE = 0;
    public static final int ANSWER_TWO = 1;
    public static final int ANSWER_THREE = 2;
    public static final int ANSWER_FOUR = 3;

    @IntDef({QuestionType.MULTI_ANSWERS, QuestionType.PICTURE, QuestionType.NUMBER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface QuestionType {
        int MULTI_ANSWERS = 1;
        int PICTURE = 2;
        int NUMBER = 3;
    }
}
