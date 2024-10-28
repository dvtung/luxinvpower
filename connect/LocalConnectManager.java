package com.nfcx.luxinvpower.connect;

import com.nfcx.luxinvpower.connect.ble.BluetoothLocalConnect;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.tcp.TcpManager;

/* loaded from: classes2.dex */
public class LocalConnectManager {
    private static BluetoothLocalConnect bluetoothLocalConnect;

    public static LocalConnect getLocalConnect(String str) {
        if (Constants.LOCAL_CONNECT_TYPE_BLUETOOTH.equals(str)) {
            return bluetoothLocalConnect;
        }
        return TcpManager.getInstance();
    }

    public static LocalConnect getBluetoothLocalConnect() {
        return bluetoothLocalConnect;
    }

    public static void updateBluetoothLocalConnect(BluetoothLocalConnect bluetoothLocalConnect2) {
        BluetoothLocalConnect bluetoothLocalConnect3 = bluetoothLocalConnect;
        if (bluetoothLocalConnect3 != null) {
            bluetoothLocalConnect3.close();
        }
        bluetoothLocalConnect = bluetoothLocalConnect2;
    }
}
