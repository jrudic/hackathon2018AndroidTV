package com.hydra3_2.client.answers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.hydra3_2.client.R;
import com.hydra3_2.client.utils.PreferenceUtility;


public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";

    EditText profileNameInput;
    Button connectButton;
    OnConnectPressedListener listener;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public interface OnConnectPressedListener {
        void onConnectPressed();
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    public void onButtonPressed() {
        if (listener != null) {
            listener.onConnectPressed();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConnectPressedListener) {
            listener = (OnConnectPressedListener) context;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileNameInput = view.findViewById(R.id.nameInput);
        connectButton = view.findViewById(R.id.connectButton);
        String profileName = PreferenceUtility.getUsername(getContext());
        if (!TextUtils.isEmpty(profileName)) {
            profileNameInput.setText(profileName);
        }
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = profileNameInput.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    PreferenceUtility.setUsername(getContext(), name);
                    onButtonPressed();
                } else {
                    profileNameInput.setError(getString(R.string.profile_name_error_msg));
                }
            }
        });
    }

}
