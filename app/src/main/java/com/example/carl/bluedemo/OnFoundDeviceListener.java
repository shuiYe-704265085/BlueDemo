package com.example.carl.bluedemo;

import android.bluetooth.BluetoothDevice;

public interface OnFoundDeviceListener {
        // 扫描到设备
        void foundDevice(BluetoothDevice device);

        // 扫描完成
        void discoveryFinished();
}
