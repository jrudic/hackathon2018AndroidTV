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
import android.widget.TextView;
import com.hydra3_2.client.R;

import static com.hydra3_2.client.answers.AnswersActivity.QUESTION_KEY;


public class OptionsAnswersFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "OptionsAnswersFragment";
    // TODO: Rename parameter arguments, choose names that match

    private String question;
    private TextView questionTextView;
    private Button buttonA, buttonB, buttonC, buttonD;
    private OnAnswerChoosedListener mListener;

    public OptionsAnswersFragment() {
        // Required empty public constructor
    }


    public static OptionsAnswersFragment newInstance(String question) {
        OptionsAnswersFragment fragment = new OptionsAnswersFragment();
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
        return inflater.inflate(R.layout.fragment_options_answers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionTextView = view.findViewById(R.id.questionText);
        questionTextView.setText(question);
        buttonA = view.findViewById(R.id.buttonA);
        buttonB = view.findViewById(R.id.buttonB);
        buttonC = view.findViewById(R.id.buttonC);
        buttonD = view.findViewById(R.id.buttonD);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
    }

    public void onButtonPressed(String answer) {
        if (mListener != null) {
            mListener.onAnswerChoosed(answer);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerChoosedListener) {
            mListener = (OnAnswerChoosedListener) context;
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
            case R.id.buttonA:
                invalidateButtons();
                setSelection(buttonA);
                onButtonPressed("A");
                break;
            case R.id.buttonB:
                invalidateButtons();
                setSelection(buttonB);
                onButtonPressed("A");
                break;
            case R.id.buttonC:
                invalidateButtons();
                setSelection(buttonC);
                onButtonPressed("C");
                break;
            case R.id.buttonD:
                invalidateButtons();
                setSelection(buttonD);
                onButtonPressed("D");
                break;
            default:
                break;
        }
    }

    private void invalidateButtons() {
        desecelt(buttonA);
        desecelt(buttonB);
        desecelt(buttonC);
        desecelt(buttonD);
    }

    private void setSelection(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        button.setTextColor(getResources().getColor(R.color.white));
    }

    private void desecelt(Button button) {
        button.setBackgroundResource(android.R.drawable.btn_default);
        button.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public interface OnAnswerChoosedListener {
        void onAnswerChoosed(String answer);
    }

}
