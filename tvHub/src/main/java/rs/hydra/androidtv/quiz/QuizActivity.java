package rs.hydra.androidtv.quiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.*;
import rs.hydra.androidtv.R;
import rs.hydra.androidtv.bluetoothlegatt.BluetoothLeService;
import rs.hydra.androidtv.bluetoothlegatt.QuizManager;
import rs.hydra.androidtv.bluetoothlegatt.SampleGattAttributes;
import rs.hydra.androidtv.quiz.model.QuestionUtility;
import rs.hydra.androidtv.quiz.model.QuizAnswer;
import rs.hydra.androidtv.quiz.model.QuizQuestion;
import rs.hydra.androidtv.quiz.score.ScoreActivity;
import rs.hydra.androidtv.quiz.user.User;

import java.util.ArrayList;

public class QuizActivity extends FragmentActivity {

    private static final long QUESTION_TIME = 15000;

    private LinearLayout numericAnswersLayout, multiAnswerLayout;
    private RelativeLayout questionLayout, startLayout;
    private LinearLayout backgroundRelativeLayout;

    private TextView timer;
    private TextView numberSolution;
    private TextView questionTitle;
    private Button answer1, answer2, answer3, answer4;
    private QuizViewModel viewModel;
    private ImageView questionImage;
    private Button nextQuestionButton;
    private Button startQuiz;
    private RecyclerView userList;

    private ArrayList<User> discoveredUsers = new ArrayList<>();

    //Bloototh stuff
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeService mBluetoothLeService;
    private boolean mScanning;
    private Handler mHandler;
    private static final int REQUEST_ENABLE_BT = 1;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mHandler = new Handler();

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        viewModel.init(this);

        initViews();

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
        }

        questionLayout.setVisibility(View.GONE);
        startLayout.setVisibility(View.VISIBLE);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private void initViews() {
        userList = findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(this));

        timer = findViewById(R.id.timerTextView);
        startQuiz = findViewById(R.id.startQuiz);
        questionLayout = findViewById(R.id.question_layout);
        startLayout = findViewById(R.id.start_layout);
        questionTitle = findViewById(R.id.questionTitle);
        numericAnswersLayout = findViewById(R.id.numeric_answer_layout);
        multiAnswerLayout = findViewById(R.id.multi_answer_layout);
        questionImage = findViewById(R.id.questionImage);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        numberSolution = findViewById(R.id.numberSolution);
        backgroundRelativeLayout = findViewById(R.id.backgroundRelativeLayout);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        setupListeners();
    }

    private void setupListeners() {
        nextQuestionButton.setOnClickListener(v -> nextQuestion());
        startQuiz.setOnClickListener(v -> startQuiz());
    }

    private void startQuiz() {
        startLayout.setVisibility(View.GONE);
        questionLayout.setVisibility(View.VISIBLE);
        nextQuestion();
    }

    private void startCounter() {
        new CountDownTimer(QUESTION_TIME, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                QuizManager.getInstance().readAnswers();
                showSolution();
            }
        }.start();
    }

    private void showSolution() {
        nextQuestionButton.setVisibility(View.VISIBLE);
        markCorrectAnswer();
    }

    private void markCorrectAnswer() {
        int type = viewModel.currentQuestionType();
        switch (type) {
            case QuestionUtility.QuestionType.MULTI_ANSWERS:
            case QuestionUtility.QuestionType.PICTURE:
                int correct = viewModel.getCorrectAnswerPosition();
                markCorrectAnswer(correct);
                break;
            case QuestionUtility.QuestionType.NUMBER:
                QuizAnswer answer = viewModel.getCorrectAnswer();
                showNumberAnswer(answer);
                break;
        }
    }

    private void showNumberAnswer(QuizAnswer answer) {
        numberSolution.setVisibility(View.VISIBLE);
        numberSolution.setText(answer.answer);
    }

    private void markCorrectAnswer(int correct) {
        switch (correct) {
            case QuestionUtility.ANSWER_ONE:
                answer1.setBackgroundColor(getResources().getColor(R.color.green));
                startBlinking(answer1);
                break;
            case QuestionUtility.ANSWER_TWO:
                answer2.setBackgroundColor(getResources().getColor(R.color.green));
                startBlinking(answer2);
                break;
            case QuestionUtility.ANSWER_THREE:
                answer3.setBackgroundColor(getResources().getColor(R.color.green));
                startBlinking(answer3);
                break;
            case QuestionUtility.ANSWER_FOUR:
                answer4.setBackgroundColor(getResources().getColor(R.color.green));
                startBlinking(answer4);
                break;
        }
    }

    private void startBlinking(Button answer) {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        answer.startAnimation(anim);

        //cancel blink after some time
        new CountDownTimer(2480, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                stopBlinking();
            }
        }.start();
    }

    private void stopBlinking() {
        answer1.clearAnimation();
        answer2.clearAnimation();
        answer3.clearAnimation();
        answer4.clearAnimation();
    }

    private void nextQuestion() {
        clearData();
        if (viewModel.isQuizFinished()) {
            showResults();
        } else {
            startCounter();
            showQuestion(viewModel.getNextQuestion());
            QuizManager.getInstance().currentCorrectAnswer = viewModel.getCorrectAnswer().answer;
        }
    }

    private void showResults() {
        Intent intent = new Intent(this, ScoreActivity.class);
        Bundle bnd = new Bundle();
        bnd.putParcelableArrayList(ScoreActivity.USERS, QuizManager.getInstance().getAllUsers());
        intent.putExtras(bnd);
        startActivity(intent);
    }

    private void clearData() {
        clearAnswers();
        nextQuestionButton.setVisibility(View.GONE);
        numberSolution.setVisibility(View.INVISIBLE);
    }

    private void clearAnswers() {
        stopBlinking();
        answer1.setBackgroundColor(getResources().getColor(R.color.button_color));
        answer2.setBackgroundColor(getResources().getColor(R.color.button_color));
        answer3.setBackgroundColor(getResources().getColor(R.color.button_color));
        answer4.setBackgroundColor(getResources().getColor(R.color.button_color));
    }

    private void showQuestion(QuizQuestion question) {
        backgroundRelativeLayout.setBackground(question.background);
        switch (question.type) {
            case QuestionUtility.QuestionType.MULTI_ANSWERS:
                showMultiAnswerQuestion(question);
                break;
            case QuestionUtility.QuestionType.NUMBER:
                showNumberQuestion(question);
                break;
            case QuestionUtility.QuestionType.PICTURE:
                showPictureQuestion(question);
                break;
        }
    }

    private void showPictureQuestion(QuizQuestion question) {
        numericAnswersLayout.setVisibility(View.GONE);
        questionImage.setVisibility(View.VISIBLE);
        multiAnswerLayout.setVisibility(View.VISIBLE);

        questionTitle.setText(question.question);
        answer1.setText(question.answers.get(QuestionUtility.ANSWER_ONE).answer);
        answer2.setText(question.answers.get(QuestionUtility.ANSWER_TWO).answer);
        answer3.setText(question.answers.get(QuestionUtility.ANSWER_THREE).answer);
        answer4.setText(question.answers.get(QuestionUtility.ANSWER_FOUR).answer);

        questionImage.setImageDrawable(question.imageURL);
    }

    private void showNumberQuestion(QuizQuestion question) {
        numericAnswersLayout.setVisibility(View.VISIBLE);
        questionImage.setVisibility(View.GONE);
        multiAnswerLayout.setVisibility(View.GONE);

        questionTitle.setText(question.question);
    }

    private void showMultiAnswerQuestion(QuizQuestion question) {
        questionImage.setVisibility(View.GONE);
        numericAnswersLayout.setVisibility(View.GONE);
        multiAnswerLayout.setVisibility(View.VISIBLE);

        questionTitle.setText(question.question);
        answer1.setText(question.answers.get(QuestionUtility.ANSWER_ONE).answer);
        answer2.setText(question.answers.get(QuestionUtility.ANSWER_TWO).answer);
        answer3.setText(question.answers.get(QuestionUtility.ANSWER_THREE).answer);
        answer4.setText(question.answers.get(QuestionUtility.ANSWER_FOUR).answer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Ensures Bluetooth is enabled on the device.  If Bluetooth is not currently enabled,
        // fire an intent to display a dialog asking the user to grant permission to enable it.
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanLeDevice(false);
    }

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(() -> {
                mScanning = false;
                mBluetoothAdapter.getBluetoothLeScanner().stopScan(onScanCallback);
                invalidateOptionsMenu();
            }, SCAN_PERIOD);

            mScanning = true;
            ScanFilter userServicesFillter = new ScanFilter.Builder().setServiceUuid(new ParcelUuid(SampleGattAttributes.USER_SERVICE_UUID)).build();
            ArrayList<ScanFilter> filters = new ArrayList<>();
            filters.add(userServicesFillter);
            mBluetoothAdapter.getBluetoothLeScanner().startScan(filters, new ScanSettings.Builder().build(), onScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.getBluetoothLeScanner().stopScan(onScanCallback);
        }
        invalidateOptionsMenu();
    }

    private ScanCallback onScanCallback =
            new ScanCallback() {
                public void onScanResult(int callbackType, ScanResult result) {
                    final BluetoothDevice device = result.getDevice();
                    runOnUiThread(() -> addUser(device));
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addUser(BluetoothDevice device) {
        User newUser = new User(device.getName());
        if (!this.discoveredUsers.contains(newUser)) {
            mBluetoothLeService.connect(device.getAddress());
            QuizManager.getInstance().addUserForDevice(device.getAddress());
            this.discoveredUsers.add(newUser);
            userList.setAdapter(viewModel.getUserAdapter(this, this.discoveredUsers));
        }
    }
    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            scanLeDevice(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
}
