package com.nfcx.luxinvpower.protocol.lux.command.write;

import com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION;
import com.nfcx.luxinvpower.protocol.lux.command.Command;
import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class WriteSingleCommand implements Command {
    private int register;
    private String serialNum;
    private int value;

    public WriteSingleCommand(String str, int i, int i2) {
        this.serialNum = str;
        this.register = i;
        this.value = i2;
    }

    @Override // com.nfcx.luxinvpower.protocol.lux.command.Command
    public byte[] getFrame() {
        byte[] bArr = new byte[18];
        bArr[0] = 0;
        bArr[1] = (byte) DEVICE_FUNCTION.W_SINGLE.getFunctionCode();
        try {
            System.arraycopy(this.serialNum.getBytes("ISO-8859-1"), 0, bArr, 2, this.serialNum.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProTool.convertLong2Byte2(bArr, 12, this.register, 0, true);
        ProTool.convertLong2Byte2(bArr, 14, this.value, 0, true);
        ProTool.convertLong2Byte2(bArr, 16, ProTool.modbus_Caluation_CRC16(bArr, 16), 0, true);
        return bArr;
    }
}
