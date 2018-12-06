/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hydra3_2.client.gattserver;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import java.util.UUID;

/**
 * User service
 */
public class UserService {
    private static final String TAG = UserService.class.getSimpleName();

    public static UUID USER_SERVICE_UUID = UUID.fromString("00001805-0000-1000-8000-00805f9b34fb");

    public static UUID ANSWER_CLIENT_CHARACTERISTIC = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public static UUID QUESTION_TYPE_CHARACTERISTIC = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static UUID QUESTION_CHARACTERISTIC = UUID.fromString("00002907-0000-1000-8000-00805f9b34fb");
    public static UUID USER_NAME_CHARACTERISTIC = UUID.fromString("00002a39-0000-1000-8000-00805f9b34fb");

    /**
     * Return a configured {@link BluetoothGattService} instance for the
     * Current Time Service.
     */
    public static BluetoothGattService createService() {
        BluetoothGattService service = new BluetoothGattService(USER_SERVICE_UUID,
                BluetoothGattService.SERVICE_TYPE_PRIMARY);

        // Answer characteristic
        BluetoothGattCharacteristic answer = new BluetoothGattCharacteristic(ANSWER_CLIENT_CHARACTERISTIC,
                //Read-only characteristic, supports notifications
                BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                BluetoothGattCharacteristic.PERMISSION_READ);
        BluetoothGattCharacteristic question = new BluetoothGattCharacteristic(QUESTION_CHARACTERISTIC,
                //Read-only characteristic, supports notifications
                BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                BluetoothGattCharacteristic.PERMISSION_READ);
//        BluetoothGattDescriptor configDescriptor = new BluetoothGattDescriptor(ANSWER_CLIENT_DESCRIPTOR,
//                //Read/write descriptor
//                BluetoothGattDescriptor.PERMISSION_READ | BluetoothGattDescriptor.PERMISSION_WRITE);
//        answer.addDescriptor(configDescriptor);

        BluetoothGattCharacteristic questionType = new BluetoothGattCharacteristic(QUESTION_TYPE_CHARACTERISTIC,
                //Read-only characteristic, supports notifications
                BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                BluetoothGattCharacteristic.PERMISSION_READ);
        BluetoothGattCharacteristic userName = new BluetoothGattCharacteristic(USER_NAME_CHARACTERISTIC,
                //Read-only characteristic, supports notifications
                BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                BluetoothGattCharacteristic.PERMISSION_READ);

        service.addCharacteristic(answer);
        service.addCharacteristic(question);
        service.addCharacteristic(questionType);
        service.addCharacteristic(userName);

        return service;
    }
}
