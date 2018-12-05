package rs.hydra.androidtv.quiz;

import android.content.Context;
import rs.hydra.androidtv.R;
import rs.hydra.androidtv.quiz.model.QuestionUtility;
import rs.hydra.androidtv.quiz.model.QuizAnswer;
import rs.hydra.androidtv.quiz.model.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class MockedQuestionGenerator {

    public static List<QuizQuestion> getMockedQuestions(Context context) {
        List<QuizQuestion> questions = new ArrayList<>();

        QuizQuestion question1 = new QuizQuestion();
        question1.background = context.getResources().getDrawable(R.drawable.angkor_wat);
        question1.question = "U kojoj se državi nalazi Angkor Wat, najveći hramski / religijski kompleks na svijetu?";
        question1.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers1 = new ArrayList<>();
        answers1.add(new QuizAnswer("Peru", false));
        answers1.add(new QuizAnswer("Vjetnam", false));
        answers1.add(new QuizAnswer("Tajland", false));
        answers1.add(new QuizAnswer("Kambodza", true));
        question1.answers = answers1;
        questions.add(question1);

        QuizQuestion question2 = new QuizQuestion();
        question2.background = context.getResources().getDrawable(R.drawable.big_work);
        question2.question = "Koji latinski izraz znači veliki rad, i odnosi se na najbolje, najpopularnije, ili najpriznatije postignuće autora, umjetnika ili kompozitora?";
        question2.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers2 = new ArrayList<>();
        answers2.add(new QuizAnswer("Fama volat", false));
        answers2.add(new QuizAnswer("Magnum opus", true));
        answers2.add(new QuizAnswer("Labor omnia vincit", false));
        answers2.add(new QuizAnswer("Tempori parce", false));
        question2.answers = answers2;
        questions.add(question2);

        QuizQuestion question3 = new QuizQuestion();
        question3.background = context.getResources().getDrawable(R.drawable.first_world_war);
        question3.question = "Koje je godine započeo Prvi svjetski rat?";
        question3.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers3 = new ArrayList<>();
        answers3.add(new QuizAnswer("1914", true));
        question3.answers = answers3;
        questions.add(question3);

        QuizQuestion question6 = new QuizQuestion();
        question6.background = context.getResources().getDrawable(R.drawable.moder);
        question6.question = "Ko se nalazi na slici?";
        question6.type = QuestionUtility.QuestionType.PICTURE;
        question6.imageURL = "https://www.biography.com/.image/t_share/MTE4MDAzNDEwNTEzMDA0MDQ2/thomas-edison-9284349-1-402.jpg";
        List<QuizAnswer> answers6 = new ArrayList<>();
        answers6.add(new QuizAnswer("Thomas Jefferson", false));
        answers6.add(new QuizAnswer("Thomas Edison", true));
        answers6.add(new QuizAnswer("Thomas Shelby", false));
        answers6.add(new QuizAnswer("Thomas Muller", false));
        question6.answers = answers6;
        questions.add(question6);

        QuizQuestion question7 = new QuizQuestion();
        question7.background = context.getResources().getDrawable(R.drawable.juzna_koreja);
        question7.question = "U Južnoj Koreji, ljudi veruju da ovaj kućni aparat može da vas ubije:";
        question7.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers7 = new ArrayList<>();
        answers7.add(new QuizAnswer("Mikrotalasna", false));
        answers7.add(new QuizAnswer("Blender", false));
        answers7.add(new QuizAnswer("CD plejer", false));
        answers7.add(new QuizAnswer("Ventilator", true));
        question7.answers = answers7;
        questions.add(question7);

        QuizQuestion question8 = new QuizQuestion();
        question8.background = context.getResources().getDrawable(R.drawable.prvi_srpski_ustanak);
        question8.question = "Koje je godine započeo Prvi srpski ustanak?";
        question8.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers8 = new ArrayList<>();
        answers8.add(new QuizAnswer("1804", true));
        question8.answers = answers8;
        questions.add(question8);

        QuizQuestion question9 = new QuizQuestion();
        question9.background = context.getResources().getDrawable(R.drawable.modern3);
        question9.question = "Ko se nalazi na slici?";
        question9.type = QuestionUtility.QuestionType.PICTURE;
        question9.imageURL = "https://srpskoblago.rs/wp-content/uploads/2012/07/17.jpg";
        List<QuizAnswer> answers9 = new ArrayList<>();
        answers9.add(new QuizAnswer("Sava Sumanovic", true));
        answers9.add(new QuizAnswer("Ivan Radovic", false));
        answers9.add(new QuizAnswer("Ilija Bosilj", false));
        answers9.add(new QuizAnswer("Sava Milosevic", false));
        question9.answers = answers9;
        questions.add(question9);

        QuizQuestion question10 = new QuizQuestion();
        question10.background = context.getResources().getDrawable(R.drawable.kit_kat);
        question10.question = "   \"Kit Ket\" čokoladice u Japanu dolaze u čak 300 različitih ukusa i oblika. Ovaj ukus ne postoji:";
        question10.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers10 = new ArrayList<>();
        answers10.add(new QuizAnswer("Sirova riba", true));
        answers10.add(new QuizAnswer("Vasabi sos", false));
        answers10.add(new QuizAnswer("Sake", false));
        answers10.add(new QuizAnswer("Slatki ljubičasti krompir", false));
        question10.answers = answers10;
        questions.add(question10);

        QuizQuestion question12 = new QuizQuestion();
        question12.background = context.getResources().getDrawable(R.drawable.moon);
        question12.question = "Koje godine je Neil Armstrong sleteo na Mesec?";
        question12.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers12 = new ArrayList<>();
        answers12.add(new QuizAnswer("1969", true));
        question12.answers = answers12;
        questions.add(question12);

        QuizQuestion question13 = new QuizQuestion();
        question13.background = context.getResources().getDrawable(R.drawable.human);
        question13.question = "Koji je najveci organ u ljudskom telu?";
        question13.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers13 = new ArrayList<>();
        answers13.add(new QuizAnswer("Koza", true));
        answers13.add(new QuizAnswer("Srce", false));
        answers13.add(new QuizAnswer("Mozak", false));
        answers13.add(new QuizAnswer("Creva", false));
        question13.answers = answers13;
        questions.add(question13);

        QuizQuestion question14 = new QuizQuestion();
        question14.background = context.getResources().getDrawable(R.drawable.modern4);
        question14.question = "Ko se nalazi na slici?";
        question14.type = QuestionUtility.QuestionType.PICTURE;
        question14.imageURL = "https://upload.wikimedia.org/wikipedia/commons/8/89/Boris_Yeltsin_30_November_2001.jpg";
        List<QuizAnswer> answers14 = new ArrayList<>();
        answers14.add(new QuizAnswer("Sergej Kirijenko", false));
        answers14.add(new QuizAnswer("Oleg Lobov", false));
        answers14.add(new QuizAnswer("Boris Jeljcin", true));
        answers14.add(new QuizAnswer("Jevgenij Primakov", false));
        question14.answers = answers14;
        questions.add(question14);

        return questions;
    }
}
