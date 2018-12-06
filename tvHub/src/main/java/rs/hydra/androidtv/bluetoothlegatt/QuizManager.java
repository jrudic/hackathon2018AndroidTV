package rs.hydra.androidtv.bluetoothlegatt;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.text.TextUtils;
import android.util.Log;
import rs.hydra.androidtv.quiz.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizManager {

    private static volatile QuizManager sInstance;
    //device,gatt
    private HashMap<String, BluetoothGatt> usersServicesHashMap = new HashMap();
    //device,user
    private HashMap<String, User> usersScoreHashMap = new HashMap();

    public String currentCorrectAnswer;

    public static QuizManager getInstance() {
        if (sInstance == null) {
            synchronized (QuizManager.class) {
                if (sInstance == null) {
                    sInstance = new QuizManager();
                }
            }
        }
        return sInstance;
    }

    private QuizManager() {
    }

    public void addConnectedGatServiceForDevice(String deviceUid, BluetoothGatt gatt) {
        if(!usersServicesHashMap.containsKey(deviceUid)){
        usersServicesHashMap.put(deviceUid, gatt);}
        Log.d("bla","Size "+usersServicesHashMap.keySet().size());
        Log.d("bla","UId "+usersServicesHashMap.get(deviceUid));
    }
    public void removeConnectedGatServiceForDevice(String deviceUid) {
//        usersServicesHashMap.remove(deviceUid);
    }
    public void addUserForDevice(String deviceUid) {
        if (usersScoreHashMap.get(deviceUid) == null) {
            usersScoreHashMap.put(deviceUid, new User());
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (String key: usersScoreHashMap.keySet()) {
            users.add(usersScoreHashMap.get(key));
        }
        return users;
    }

    public void handleChangeCharacteristicForGatt(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        String deviceAddress = gatt.getDevice().getAddress();
        if (SampleGattAttributes.ANSWER_CLIENT_CHARACTERISTIC.equals(characteristic.getUuid())) {
            final String answer = characteristic.getStringValue(0);
            Log.d("bla",answer);
            if(TextUtils.equals(answer,currentCorrectAnswer)){
                usersScoreHashMap.get(deviceAddress).increaseScore();
            }
        }
    }

    public synchronized void readAnswers() {
        Log.d("bla",""+usersServicesHashMap.keySet().size());
        for (String key: usersServicesHashMap.keySet()) {
            BluetoothGatt gatt = usersServicesHashMap.get(key);
            BluetoothGattService service= gatt.getService(SampleGattAttributes.USER_SERVICE_UUID);
            gatt.readCharacteristic(service.getCharacteristic(SampleGattAttributes.ANSWER_CLIENT_CHARACTERISTIC));
        }
    }
}
