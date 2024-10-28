package com.nfcx.luxinvpower.protocol.lux.command.update;

import android.util.Base64;
import com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION;
import com.nfcx.luxinvpower.protocol.lux.command.Command;
import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class UpdateSendDataCommand implements Command {
    private int dataIndex;
    private byte[] dataList;
    private int fileType;
    private long physicalAddr;
    private String serialNum;

    public UpdateSendDataCommand(String str, int i, int i2, long j, String str2) {
        this.serialNum = str;
        this.dataIndex = i;
        this.fileType = i2;
        this.physicalAddr = j;
        this.dataList = Base64.decode(str2, 0);
    }

    private boolean hasPhysicalAddr() {
        return this.fileType == 2;
    }

    @Override // com.nfcx.luxinvpower.protocol.lux.command.Command
    public byte[] getFrame() {
        int i = hasPhysicalAddr() ? 4 : 0;
        int length = this.dataList.length + 19 + i;
        byte[] bArr = new byte[length];
        bArr[0] = 0;
        bArr[1] = (byte) DEVICE_FUNCTION.UPDATE_SEND_DATA.getFunctionCode();
        try {
            System.arraycopy(this.serialNum.getBytes("ISO-8859-1"), 0, bArr, 2, this.serialNum.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProTool.convertLong2Byte2(bArr, 12, this.dataIndex, 0, true);
        bArr[14] = (byte) this.fileType;
        ProTool.convertLong2Byte2(bArr, 15, this.dataList.length + i, 0, true);
        if (hasPhysicalAddr()) {
            ProTool.convertLong2Byte4(bArr, 17, this.physicalAddr, 0, true);
        }
        byte[] bArr2 = this.dataList;
        System.arraycopy(bArr2, 0, bArr, i + 17, bArr2.length);
        ProTool.convertLong2Byte2(bArr, length - 2, ProTool.modbus_Caluation_CRC16(bArr, r4), 0, true);
        return bArr;
    }
}
