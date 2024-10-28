package com.nfcx.luxinvpower.tcp;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.protocol.tcp.data.response.HeartBeatData;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.response.ResponseHeartBeatDataFrame;
import com.nfcx.luxinvpower.tool.ProTool;
import java.util.Date;

/* loaded from: classes2.dex */
public class TcpAnalyzer extends SerialBuffer {
    private static final int PREFIX_CHECK_LENGTH = 2;
    private static final int PREFIX_LENGTH = 6;
    private static final int[] RECEIVE_PREFIX;
    private static String prefixText = "";
    private CommandSender commandSender;
    private int dataLength = 0;
    private StringBuffer dataBuffer = new StringBuffer();
    private boolean prefixValid = false;

    static {
        int[] iArr = {161, 26};
        RECEIVE_PREFIX = iArr;
        for (int i : iArr) {
            prefixText += ProTool.getStringFromHex(i);
        }
    }

    public TcpAnalyzer(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override // com.nfcx.luxinvpower.tcp.SerialBuffer
    public synchronized void putChar(int i) {
        this.dataBuffer.append((char) i);
        checkData(i);
    }

    private void checkData(int i) {
        int length = this.dataBuffer.length();
        if (length == 2) {
            if (this.dataBuffer.toString().startsWith(prefixText)) {
                this.prefixValid = true;
                return;
            }
            return;
        }
        if (length > 2 && !this.prefixValid && i == 26) {
            int indexOf = this.dataBuffer.toString().indexOf(prefixText);
            if (indexOf > 0) {
                this.dataBuffer.delete(0, indexOf);
                this.prefixValid = true;
                return;
            }
            return;
        }
        if (length == 6 && this.prefixValid) {
            this.dataLength = (i * 256) + this.dataBuffer.charAt(length - 2);
        } else if (length == this.dataLength + 6 && this.prefixValid) {
            handleValidData();
        }
    }

    private void handleValidData() {
        System.out.println("LuxPower - " + new Date() + " - Valid: " + ProTool.showData(this.dataBuffer.toString()));
        char charAt = this.dataBuffer.charAt(7);
        if (charAt > 0) {
            switch (charAt) {
                case Opcodes.INSTANCEOF /* 193 */:
                    handleHeartBeatData();
                    break;
                case Opcodes.MONITORENTER /* 194 */:
                    handleTranslate();
                    break;
                case Opcodes.MONITOREXIT /* 195 */:
                    String stringBuffer = this.dataBuffer.toString();
                    this.commandSender.putCommandWaitMap("read_datalog_" + ProTool.count(stringBuffer.charAt(19), stringBuffer.charAt(18)), stringBuffer);
                    break;
                case 196:
                    String stringBuffer2 = this.dataBuffer.toString();
                    this.commandSender.putCommandWaitMap("write_datalog_" + ProTool.count(stringBuffer2.charAt(19), stringBuffer2.charAt(18)), stringBuffer2);
                    break;
            }
        }
        StringBuffer stringBuffer3 = this.dataBuffer;
        stringBuffer3.delete(0, stringBuffer3.length());
        this.dataBuffer.trimToSize();
        this.prefixValid = false;
    }

    private void handleTranslate() {
        char charAt = this.dataBuffer.charAt(21);
        switch (charAt) {
            case '!':
                if (this.dataBuffer.charAt(32) == 1) {
                    this.commandSender.putCommandWaitMap("tcpUpdate_Prepare", this.dataBuffer.toString());
                    return;
                }
                return;
            case '\"':
                if (this.dataBuffer.charAt(32) == 1) {
                    this.commandSender.putCommandWaitMap("tcpUpdate_Send_" + ProTool.count(this.dataBuffer.charAt(34), this.dataBuffer.charAt(33)), this.dataBuffer.toString());
                    return;
                }
                return;
            case '#':
                if (this.dataBuffer.charAt(32) == 1) {
                    this.commandSender.putCommandWaitMap("tcpUpdate_Reset", this.dataBuffer.toString());
                    return;
                }
                return;
            default:
                int count = ProTool.count(this.dataBuffer.charAt(33), this.dataBuffer.charAt(32));
                char charAt2 = this.dataBuffer.charAt(34);
                if (charAt == 3 && charAt2 == 'P') {
                    if (count == 0) {
                        this.commandSender.putCommandWaitMap("read_03_1", this.dataBuffer.toString());
                    } else if (count == 40) {
                        this.commandSender.putCommandWaitMap("read_03_2", this.dataBuffer.toString());
                    } else if (count == 80) {
                        this.commandSender.putCommandWaitMap("read_03_3", this.dataBuffer.toString());
                    }
                    this.commandSender.putCommandWaitMap("read_multi_03_" + count + "_" + ((int) charAt2), this.dataBuffer.toString());
                    return;
                }
                if (charAt == 3 && charAt2 == 2) {
                    this.commandSender.putCommandWaitMap("read_single_03_" + count, this.dataBuffer.toString());
                    return;
                }
                if (charAt == 3) {
                    this.commandSender.putCommandWaitMap("read_multi_03_" + count + "_" + ((int) charAt2), this.dataBuffer.toString());
                    return;
                }
                if (charAt != 4 || charAt2 != 'P') {
                    if (charAt == 6) {
                        this.commandSender.putCommandWaitMap("write_single_06", this.dataBuffer.toString());
                        return;
                    } else {
                        if (charAt == 16) {
                            this.commandSender.putCommandWaitMap("write_multi_06_" + count + "_" + ((int) charAt2), this.dataBuffer.toString());
                            return;
                        }
                        return;
                    }
                }
                if (count == 0) {
                    this.commandSender.putCommandWaitMap("read_04_1", this.dataBuffer.toString());
                    return;
                } else if (count == 40) {
                    this.commandSender.putCommandWaitMap("read_04_2", this.dataBuffer.toString());
                    return;
                } else {
                    if (count == 80) {
                        this.commandSender.putCommandWaitMap("read_04_3", this.dataBuffer.toString());
                        return;
                    }
                    return;
                }
        }
    }

    private void handleHeartBeatData() {
        this.commandSender.putCommandWaitMap("heart_beat", this.dataBuffer.toString());
        char charAt = this.dataBuffer.charAt(18);
        ResponseHeartBeatDataFrame responseHeartBeatDataFrame = new ResponseHeartBeatDataFrame(this.dataBuffer.charAt(2) == 2 ? TCP_PROTOCOL._02 : TCP_PROTOCOL._01);
        responseHeartBeatDataFrame.setData(new HeartBeatData(this.dataBuffer.substring(8, 18), charAt));
        this.commandSender.sendPureCommand(responseHeartBeatDataFrame);
    }
}
