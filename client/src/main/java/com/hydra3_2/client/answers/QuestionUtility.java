package com.hydra3_2.client.answers;

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
        int MULTI_ANSWERS = 0;
        int PICTURE = 1;
        int NUMBER = 2;
        int FINISHED = 3;
    }

    public static int getClientQuestionType(int type) {
        switch (type) {
            case QuestionType.MULTI_ANSWERS:
            case QuestionType.PICTURE:
                return 0;
            case QuestionType.NUMBER:
                return 1;
            case QuestionType.FINISHED:
                return 2;
            default:
                return 0;
        }
    }
}
