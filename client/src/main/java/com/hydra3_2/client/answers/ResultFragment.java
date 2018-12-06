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
import com.hydra3_2.client.R;


public class ResultFragment extends Fragment {
    public static final String TAG = "ResultFragment";

    OnResultButtonsListener listener;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.result_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button playAgain = view.findViewById(R.id.playAgain);
        Button finishButton = view.findViewById(R.id.finishButton);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });
    }

    public void onButtonPressed() {
        if (listener != null) {
            listener.onPlayAgainPressed();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProfileFragment.OnConnectPressedListener) {
            listener = (OnResultButtonsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAnswerChoosedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface OnResultButtonsListener {
        void onPlayAgainPressed();

    }

}
