package rs.hydra.androidtv.quiz;

import java.util.ArrayList;
import java.util.List;

public class MockedQuestionGenerator {

    public static List<QuizQuestion> getMockedQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();

        QuizQuestion question1 = new QuizQuestion();
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
        question3.question = "Koje je godine započeo Prvi svjetski rat?";
        question3.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers3 = new ArrayList<>();
        answers3.add(new QuizAnswer("1914", true));
        question3.answers = answers3;
        questions.add(question3);

        QuizQuestion question4 = new QuizQuestion();
        question4.question = "Koji latinski izraz znači veliki rad, i odnosi se na najbolje, najpopularnije, ili najpriznatije postignuće autora, umjetnika ili kompozitora?";
        question4.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers4 = new ArrayList<>();
        answers4.add(new QuizAnswer("Operska pjevačica", false));
        answers4.add(new QuizAnswer("Engleska kraljica", false));
        answers4.add(new QuizAnswer("Francuska kraljica", true));
        answers4.add(new QuizAnswer("Poznati poslastičar", false));
        question4.answers = answers4;
        questions.add(question4);

        QuizQuestion question5 = new QuizQuestion();
        question5.question = "U kojoj se državi nalazi Angkor Wat, najveći hramski / religijski kompleks na svijetu?";
        question5.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers5 = new ArrayList<>();
        answers5.add(new QuizAnswer("Madrid", false));
        answers5.add(new QuizAnswer("Veneciju", true));
        answers5.add(new QuizAnswer("Barselonu", false));
        answers5.add(new QuizAnswer("Rim", false));
        question5.answers = answers5;
        questions.add(question5);

        QuizQuestion question6 = new QuizQuestion();
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
        question8.question = "Koje je godine započeo Prvi srpski ustanak?";
        question8.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers8 = new ArrayList<>();
        answers8.add(new QuizAnswer("1804", true));
        question8.answers = answers8;
        questions.add(question8);

        QuizQuestion question9 = new QuizQuestion();
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
        question10.question = "\"Kit Ket\" čokoladice u Japanu dolaze u čak 300 različitih ukusa i oblika. Ovaj ukus ne postoji:";
        question10.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers10 = new ArrayList<>();
        answers10.add(new QuizAnswer("Sirova riba", true));
        answers10.add(new QuizAnswer("Vasabi sos", false));
        answers10.add(new QuizAnswer("Sake", false));
        answers10.add(new QuizAnswer("Slatki ljubičasti krompir", false));
        question10.answers = answers10;
        questions.add(question10);

        QuizQuestion question11 = new QuizQuestion();
        question11.question = "Koje je godine započeo je bilo boj na Kosovu?";
        question11.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers11 = new ArrayList<>();
        answers11.add(new QuizAnswer("1389", true));
        question11.answers = answers11;
        questions.add(question11);

        QuizQuestion question12 = new QuizQuestion();
        question12.question = "Koje godine je Neil Armstrong sleteo na Mesec?";
        question12.type = QuestionUtility.QuestionType.NUMBER;
        List<QuizAnswer> answers12 = new ArrayList<>();
        answers12.add(new QuizAnswer("1969", true));
        question12.answers = answers12;
        questions.add(question12);

        QuizQuestion question13 = new QuizQuestion();
        question13.question = "Koji je najveci organ u ljudskom telu?";
        question13.type = QuestionUtility.QuestionType.MULTI_ANSWERS;
        List<QuizAnswer> answers13 = new ArrayList<>();
        answers13.add(new QuizAnswer("Koza", true));
        answers13.add(new QuizAnswer("Srce", false));
        answers13.add(new QuizAnswer("Mozak", false));
        answers13.add(new QuizAnswer("Creva", false));
        question10.answers = answers13;
        questions.add(question13);

        QuizQuestion question14 = new QuizQuestion();
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

        QuizQuestion question15 = new QuizQuestion();
        question15.question = "Ko se nalazi na slici?";
        question15.type = QuestionUtility.QuestionType.PICTURE;
        question15.imageURL = "http://www.gstatic.com/tv/thumb/persons/609784/609784_v9_bb.jpg";
        List<QuizAnswer> answers15 = new ArrayList<>();
        answers15.add(new QuizAnswer("Eminem", false));
        answers15.add(new QuizAnswer("50 Cent", false));
        answers15.add(new QuizAnswer("Travis Scott", false));
        answers15.add(new QuizAnswer("Wiz Khalifa", true));
        question15.answers = answers15;
        questions.add(question15);

        return questions;
    }
}
