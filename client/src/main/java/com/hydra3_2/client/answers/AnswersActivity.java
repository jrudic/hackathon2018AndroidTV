package com.hydra3_2.client.answers;

import android.bluetooth.*;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import com.hydra3_2.client.R;
import com.hydra3_2.client.gattserver.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.List;

public class AnswersActivity extends AppCompatActivity implements OptionsAnswersFragment.OnAnswerChoosedListener,
        NumberInputAnswersFragment.OnAnswerTypedListener, ProfileFragment.OnConnectPressedListener, ResultFragment.OnResultButtonsListener {
    public static final String QUESTION_KEY = "question_key";
    public static final String TAG =AnswersActivity.class.getSimpleName();
    public static final int PRIMARY_ANSWER_TYPE = 0;
    public static final int SECODNARY_ANSWER_TYPE = 1;
    private FragmentManager fragmentManager;
    private int answerType = 0;
    private int stepsCount = 0;
    List<QuizQuestion> questionList;
    private static final long SCAN_PERIOD = 10000;
    private static final long QUESTION_TIME = 17000;

    /* Bluetooth API */
    private BluetoothManager mBluetoothManager;
    private BluetoothGattServer mBluetoothGattServer;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    /* Collection of notification subscribers */
    private Set<BluetoothDevice> mRegisteredDevices = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle("Home Pub Quiz");
        questionList = MockedQuestionGenerator.getMockedQuestions(this);
        displayProfileScreen();
        // Devices with a display should not go to sleep
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mBluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = mBluetoothManager.getAdapter();
        // We can't continue without proper Bluetooth support
        if (!checkBluetoothSupport(bluetoothAdapter)) {
            finish();
        }

        // Register for system Bluetooth events
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBluetoothReceiver, filter);
        if (!bluetoothAdapter.isEnabled()) {
            Log.d(TAG, "Bluetooth is currently disabled...enabling");
            bluetoothAdapter.enable();
        } else {
            Log.d(TAG, "Bluetooth enabled...starting services");
            startAdvertising();
            startServer();
        }
    }

    private void displayOptionsAnswerScreen(QuizQuestion quizQuestion) {
        OptionsAnswersFragment optionsAnswersFragment;

        optionsAnswersFragment = (OptionsAnswersFragment) fragmentManager
                .findFragmentByTag(OptionsAnswersFragment.TAG);
//        if (optionsAnswersFragment == null) {
        optionsAnswersFragment =
                OptionsAnswersFragment.newInstance(quizQuestion.question);

        //  }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, optionsAnswersFragment,
                        OptionsAnswersFragment.TAG).commit();
    }

    private void displayNumberInputAnswerScreen(QuizQuestion quizQuestion) {
        NumberInputAnswersFragment numberInputAnswersFragment;

        numberInputAnswersFragment = (NumberInputAnswersFragment) fragmentManager
                .findFragmentByTag(NumberInputAnswersFragment.TAG);
        // if (numberInputAnswersFragment == null) {
        numberInputAnswersFragment =
                NumberInputAnswersFragment.newInstance(quizQuestion.question);

        //    }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, numberInputAnswersFragment,
                        NumberInputAnswersFragment.TAG).commit();
    }

    private void displayProfileScreen() {
        ProfileFragment profileFragment;

        profileFragment = (ProfileFragment) fragmentManager
                .findFragmentByTag(ProfileFragment.TAG);
        if (profileFragment == null) {
            profileFragment =
                    ProfileFragment.newInstance();

        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, profileFragment,
                        ProfileFragment.TAG).commit();
    }

    private void displayResultScreen() {
        ResultFragment resultFragment;

        resultFragment = (ResultFragment) fragmentManager
                .findFragmentByTag(ResultFragment.TAG);
        if (resultFragment == null) {
            resultFragment =
                    ResultFragment.newInstance();

        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, resultFragment,
                        ResultFragment.TAG).commit();
    }


    @Override
    public void onAnswerChoosed(String answer) {
        if (stepsCount != 0) {
            showNext();
        }
    }

    private void showNextFragment() {
        if (stepsCount < questionList.size()) {
            QuizQuestion quizQuestion = questionList.get(stepsCount);
            switch (quizQuestion.type) {
                case 0:
                case 1:
                    displayOptionsAnswerScreen(quizQuestion);
                    break;
                case 2:
                    displayNumberInputAnswerScreen(quizQuestion);
                    break;
            }
        } else {
            displayResultScreen();
        }

        stepsCount++;
    }

    @Override
    public void onAnswerTyped(String answer) {
        if (stepsCount != 0) {
            showNext();
        }
    }

    @Override
    public void onConnectPressed() {
        showNextFragment();
    }

    @Override
    public void onPlayAgainPressed() {
        displayProfileScreen();
        stepsCount = 0;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        BluetoothAdapter bluetoothAdapter = mBluetoothManager.getAdapter();
        if (bluetoothAdapter.isEnabled()) {
            stopServer();
            stopAdvertising();
        }

        unregisterReceiver(mBluetoothReceiver);
    }

    /**
     * Verify the level of Bluetooth support provided by the hardware.
     * @param bluetoothAdapter System {@link BluetoothAdapter}.
     * @return true if Bluetooth is properly supported, false otherwise.
     */
    private boolean checkBluetoothSupport(BluetoothAdapter bluetoothAdapter) {

        if (bluetoothAdapter == null) {
            Log.w(TAG, "Bluetooth is not supported");
            return false;
        }

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.w(TAG, "Bluetooth LE is not supported");
            return false;
        }

        return true;
    }

    /**
     * Listens for Bluetooth adapter events to enable/disable
     * advertising and server functionality.
     */
    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);

            switch (state) {
                case BluetoothAdapter.STATE_ON:
                    startAdvertising();
                    startServer();
                    break;
                case BluetoothAdapter.STATE_OFF:
                    stopServer();
                    stopAdvertising();
                    break;
                default:
                    // Do nothing
            }
        }
    };

    /**
     * Begin advertising over Bluetooth that this device is connectable
     * and supports the Current Time Service.
     */
    private void startAdvertising() {
        BluetoothAdapter bluetoothAdapter = mBluetoothManager.getAdapter();
        mBluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        if (mBluetoothLeAdvertiser == null) {
            Log.w(TAG, "Failed to create advertiser");
            return;
        }

        AdvertiseSettings settings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
                .setConnectable(true)
                .setTimeout(0)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
                .build();

        AdvertiseData data = new AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .setIncludeTxPowerLevel(false)
                .addServiceUuid(new ParcelUuid(UserService.USER_SERVICE_UUID))
                .build();

        mBluetoothLeAdvertiser
                .startAdvertising(settings, data, mAdvertiseCallback);
    }

    /**
     * Stop Bluetooth advertisements.
     */
    private void stopAdvertising() {
        if (mBluetoothLeAdvertiser == null) return;

        mBluetoothLeAdvertiser.stopAdvertising(mAdvertiseCallback);
    }

    /**
     * Initialize the GATT server instance with the services/characteristics
     * from the Time Profile.
     */
    private void startServer() {
        mBluetoothGattServer = mBluetoothManager.openGattServer(this, mGattServerCallback);
        if (mBluetoothGattServer == null) {
            Log.w(TAG, "Unable to create GATT server");
            return;
        }

        mBluetoothGattServer.addService(UserService.createService());
    }

    /**
     * Shut down the GATT server.
     */
    private void stopServer() {
        if (mBluetoothGattServer == null) return;

        mBluetoothGattServer.close();
    }

    /**
     * Callback to receive information about the advertisement process.
     */
    private AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            Log.i(TAG, "LE Advertise Started.");
        }

        @Override
        public void onStartFailure(int errorCode) {
            Log.w(TAG, "LE Advertise Failed: "+errorCode);
        }
    };
    /**
     * Callback to handle incoming requests to the GATT server.
     * All read/write requests for characteristics and descriptors are handled here.
     */
    private BluetoothGattServerCallback mGattServerCallback = new BluetoothGattServerCallback() {

        @Override
        public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.i(TAG, "BluetoothDevice CONNECTED: " + device);
                notifyRegisteredDevices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.i(TAG, "BluetoothDevice DISCONNECTED: " + device);
                //Remove device from any active subscriptions
                mRegisteredDevices.remove(device);
            }
        }

        @Override
        public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset,
                                                BluetoothGattCharacteristic characteristic) {
            long now = System.currentTimeMillis();
            if (UserService.ANSWER_CLIENT_CHARACTERISTIC.equals(characteristic.getUuid())) {

                Log.i(TAG, "Read Answer");
                mBluetoothGattServer.sendResponse(device,
                        requestId,
                        BluetoothGatt.GATT_SUCCESS,
                        0, "Answer".getBytes());
//                        TimeProfile.getExactTime(now, TimeProfile.ADJUST_NONE));
            } else {
                // Invalid characteristic
                Log.w("bla", "Invalid Characteristic Read: " + characteristic.getUuid());
                mBluetoothGattServer.sendResponse(device,
                        requestId,
                        BluetoothGatt.GATT_FAILURE,
                        0,
                        null);
            }
        }

        @Override
        public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {
            if (UserService.QUESTION_CHARACTERISTIC.equals(characteristic.getUuid())) {
                Log.d("Bla"," question "+new String(value));
                showNextFragment();

            }else if(UserService.QUESTION_TYPE_CHARACTERISTIC.equals(characteristic.getUuid())){
                Log.d("Bla","type "+characteristic.getStringValue(0));
            }
        }


        @Override
        public void onExecuteWrite(BluetoothDevice device, int requestId, boolean execute) {
            super.onExecuteWrite(device, requestId, execute);
        }
    };
    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


        /**
         * Send a time service notification to any devices that are subscribed
         * to the characteristic.
         */
        private void notifyRegisteredDevices() {
            if (mRegisteredDevices.isEmpty()) {
                Log.i(TAG, "No subscribers registered");
                return;
            }
            byte[] exactTime = "asdf".getBytes();

            Log.i(TAG, "Sending update to " + mRegisteredDevices.size() + " subscribers");
            for (BluetoothDevice device : mRegisteredDevices) {
                BluetoothGattCharacteristic answer = mBluetoothGattServer
                        .getService(UserService.USER_SERVICE_UUID)
                        .getCharacteristic(UserService.ANSWER_CLIENT_CHARACTERISTIC);
                answer.setValue(exactTime);
                mBluetoothGattServer.notifyCharacteristicChanged(device, answer, false);
            }
        }


    }

    private void showNext() {
        new CountDownTimer(QUESTION_TIME, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                showNextFragment();
            }
        }.start();
    }

}
