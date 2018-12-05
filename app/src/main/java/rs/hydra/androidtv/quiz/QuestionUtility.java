package rs.hydra.androidtv.quiz;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class QuestionUtility {

    @IntDef({QuestionType.MULTI_ANSWERS, QuestionType.PICTURE, QuestionType.NUMBER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface QuestionType {
        int MULTI_ANSWERS = 1;
        int PICTURE = 2;
        int NUMBER = 3;
    }
}
