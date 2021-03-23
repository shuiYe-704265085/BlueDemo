package com.example.carl.bluedemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private Button openBlue, closeBlue;
    private LinearLayout linearLayout_list ,linearLayout_warn;
    private BluetoothAdapter mBluetoothAdapter;
    public static final int REQUEST_ENABLE_BT = 1;

    private List<Blue> blueList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //蓝牙状态发生改变 arg1:0 关闭 arg1:1 打开
                    if (msg.arg1 == 0) {
                        linearLayout_list.setVisibility(View.GONE);
                        linearLayout_warn.setVisibility(View.VISIBLE);
                    } else {
                        linearLayout_list.setVisibility(View.VISIBLE);
                        linearLayout_warn.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindBroadcast();
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(blueStatusBroadcastReceiver);
        unregisterReceiver(blueSearchBroadcastReceiver);
    }

    private void initView() {
        openBlue = (Button) findViewById(R.id.open_blue);
        closeBlue = (Button) findViewById(R.id.close_blue);
        linearLayout_list = (LinearLayout) findViewById(R.id.blue_list);
        linearLayout_warn = (LinearLayout) findViewById(R.id.warn_text);
        // 蓝牙搜索页面初始化
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            linearLayout_list.setVisibility(View.VISIBLE);
            linearLayout_warn.setVisibility(View.GONE);
        } else if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
            linearLayout_list.setVisibility(View.GONE);
            linearLayout_warn.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    private void initEvent() {
        openBlue.setOnClickListener(this);
        closeBlue.setOnClickListener(this);
    }

    private void bindBroadcast() {
        IntentFilter blueStatusIntentFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(blueStatusBroadcastReceiver, blueStatusIntentFilter);
//
        IntentFilter blueSearchIntentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(blueSearchBroadcastReceiver, blueSearchIntentFilter);

    }

    private void openBlue() {
        Log.d(TAG, "openBlue: carlxxx open blue");
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "此设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        }

        //确认蓝牙是否启用,若未启用则打开
        if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
//            mBluetoothAdapter.enable();
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void closeBlue() {
        //关闭蓝牙
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_blue:
                Log.d(TAG, "onClick: carlxxx open blue");
                openBlue();
                break;
            case R.id.close_blue:
                Log.d(TAG, "onClick: carlxxx close blue");
                closeBlue();
                break;
            default:
                break;
        }
    }

    //监听手机蓝牙变化广播
    private BroadcastReceiver blueStatusBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Message msg = new Message();

            Log.d(TAG, "onReceive: carlxxx " + intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1));
            switch (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
                case BluetoothAdapter.STATE_ON:
                    msg.what = 1;
                    msg.arg1 = 1;
                    mHandler.sendMessage(msg);
                    break;
                case BluetoothAdapter.STATE_OFF:
                    msg.what = 1;
                    msg.arg1 = 0;
                    mHandler.sendMessage(msg);
                    break;
                default:
                    break;
            }
        }
    };

    private BroadcastReceiver blueSearchBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
}
