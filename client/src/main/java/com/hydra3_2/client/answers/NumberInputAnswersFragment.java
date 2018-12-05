package com.hydra3_2.client.answers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.hydra3_2.client.R;


public class NumberInputAnswersFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "NumberInputAnswersFragment";
    private static final String QUESTION_KEY = "question_key";

    private String question;
    private TextView questionTextView;
    private OnAnswerTypedListener mListener;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, butn8, butn9, btnClear;

    private String inputText = "";
    private EditText inputTextView;

    public NumberInputAnswersFragment() {
        // Required empty public constructor
    }


    public static NumberInputAnswersFragment newInstance(String question) {
        NumberInputAnswersFragment fragment = new NumberInputAnswersFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_KEY, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_numeric_answers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionTextView = view.findViewById(R.id.questionText);
        inputTextView = view.findViewById(R.id.answerInputView);
        questionTextView.setText(question);

        btn0 = view.findViewById(R.id.numberZero);
        btn1 = view.findViewById(R.id.numberOne);
        btn2 = view.findViewById(R.id.numberTwo);
        btn3 = view.findViewById(R.id.numberThree);
        btn4 = view.findViewById(R.id.numberFour);
        btn5 = view.findViewById(R.id.numberFive);
        btn6 = view.findViewById(R.id.numberSix);
        btn7 = view.findViewById(R.id.numberSeven);
        butn8 = view.findViewById(R.id.numberEight);
        butn9 = view.findViewById(R.id.numberNine);
        btnClear = view.findViewById(R.id.buttonClear);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        butn8.setOnClickListener(this);
        butn9.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    public void onButtonPressed(String answer) {
        if (mListener != null) {
            mListener.onAnswerTyped(answer);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerTypedListener) {
            mListener = (OnAnswerTypedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAnswerChoosedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.numberZero:
                setText("0");
                break;
            case R.id.numberOne:
                setText("1");
                break;
            case R.id.numberTwo:
                setText("2");
                break;
            case R.id.numberThree:
                setText("3");
                break;
            case R.id.numberFour:
                setText("4");
                break;
            case R.id.numberFive:
                setText("5");
                break;
            case R.id.numberSix:
                setText("6");
                break;
            case R.id.numberSeven:
                setText("7");
                break;
            case R.id.numberEight:
                setText("8");
                break;
            case R.id.numberNine:
                setText("9");
                break;
            case R.id.buttonClear:
                clearText();
                break;
            default:
                break;
        }
    }

    private void setText(String text) {
        inputText = inputText.concat(text);
        inputTextView.setText(inputText);
    }

    private void clearText() {
        inputText = "";
        inputTextView.setText(inputText);
    }


    public interface OnAnswerTypedListener {
        // TODO: Update argument type and name
        void onAnswerTyped(String answer);
    }
}
