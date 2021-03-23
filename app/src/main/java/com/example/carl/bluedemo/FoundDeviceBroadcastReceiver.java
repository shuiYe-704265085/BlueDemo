package com.example.carl.bluedemo;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FoundDeviceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "FoundDeviceBroadcast";
    private static OnFoundDeviceListener mOnFoundDeviceListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 获取设备
        BluetoothDevice btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // 扫描发现的设备
            if (null != mOnFoundDeviceListener) {
                mOnFoundDeviceListener.foundDevice(btDevice);
            }
        }
    }

    public void setOnFoundDeviceListener(OnFoundDeviceListener listener) {
        mOnFoundDeviceListener = listener;
    }
}
