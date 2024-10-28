package com.nfcx.luxinvpower.protocol.lux.command.update;

import com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION;
import com.nfcx.luxinvpower.protocol.lux.command.Command;
import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class UpdateResetCommand implements Command {
    private long crc32;
    private int dataCount;
    private int fileType;
    private String serialNum;

    public UpdateResetCommand(String str, int i, int i2, long j) {
        this.serialNum = str;
        this.fileType = i;
        this.dataCount = i2;
        this.crc32 = j;
    }

    @Override // com.nfcx.luxinvpower.protocol.lux.command.Command
    public byte[] getFrame() {
        byte[] bArr = new byte[21];
        bArr[0] = 0;
        bArr[1] = (byte) DEVICE_FUNCTION.UPDATE_RESET.getFunctionCode();
        try {
            System.arraycopy(this.serialNum.getBytes("ISO-8859-1"), 0, bArr, 2, this.serialNum.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bArr[12] = (byte) this.fileType;
        ProTool.convertLong2Byte2(bArr, 13, this.dataCount, 0, true);
        ProTool.convertLong2Byte4(bArr, 15, this.crc32, 0, true);
        ProTool.convertLong2Byte2(bArr, 19, ProTool.modbus_Caluation_CRC16(bArr, 19), 0, true);
        return bArr;
    }
}
