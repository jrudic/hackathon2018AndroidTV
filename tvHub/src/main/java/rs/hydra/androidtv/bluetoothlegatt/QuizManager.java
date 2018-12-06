package rs.hydra.androidtv.bluetoothlegatt;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.text.TextUtils;
import android.util.Log;
import rs.hydra.androidtv.quiz.model.QuestionUtility;
import rs.hydra.androidtv.quiz.model.QuizAnswer;
import rs.hydra.androidtv.quiz.model.QuizQuestion;
import rs.hydra.androidtv.quiz.user.User;

import java.math.BigInteger;
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
    }
    public void removeConnectedGatServiceForDevice(String deviceUid) {
        usersServicesHashMap.remove(deviceUid);
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
//        Log.d("bla",""+usersServicesHashMap.keySet().size());
//        for (String key: usersServicesHashMap.keySet()) {
//            BluetoothGatt gatt = usersServicesHashMap.get(key);
//            BluetoothGattService service= gatt.getService(SampleGattAttributes.USER_SERVICE_UUID);
//            gatt.readCharacteristic(service.getCharacteristic(SampleGattAttributes.ANSWER_CLIENT_CHARACTERISTIC));
//        }
    }

    public void setQuestionAndAnswer(QuizQuestion question, QuizAnswer correctAnswer) {
        for (String key: usersServicesHashMap.keySet()) {
            BluetoothGatt gatt = usersServicesHashMap.get(key);
            BluetoothGattService service= gatt.getService(SampleGattAttributes.USER_SERVICE_UUID);

            Log.d("bla",question.question+"characteristic for service"  +service.getCharacteristics().size());
            BluetoothGattCharacteristic questionCharacteristic = service.getCharacteristic(SampleGattAttributes.QUESTION_CHARACTERISTIC);
            questionCharacteristic.setValue(question.question);
            questionCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
            gatt.setCharacteristicNotification(questionCharacteristic,true);
            gatt.writeCharacteristic(questionCharacteristic);
//            BluetoothGattCharacteristic questionTypeCharacteristic = service.getCharacteristic(SampleGattAttributes.QUESTION_TYPE_CHARACTERISTIC);
//            questionTypeCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
//            questionTypeCharacteristic.setValue(""+ question.type);
//            gatt.setCharacteristicNotification(questionTypeCharacteristic,true);
//            gatt.writeCharacteristic(questionTypeCharacteristic);

            if(question.type!= QuestionUtility.QuestionType.NUMBER){
                currentCorrectAnswer = String.valueOf(question.answers.indexOf(correctAnswer));
            }else{
                currentCorrectAnswer=correctAnswer.answer;
            }

        }
    }
}
