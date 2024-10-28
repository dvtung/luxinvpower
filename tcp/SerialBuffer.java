package com.nfcx.luxinvpower.tcp;

import com.google.firebase.messaging.ServiceStarter;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class SerialBuffer {
    public static int NO_DATA_WAIT_MILLS = 2000;
    private boolean hasChar;
    private long lastTimeReceiveData;
    private Calendar sendCalendar;
    private String Content = "";
    private boolean available = false;
    private int LengthNeeded = 1;
    private int count = 0;
    private int waitMillis = 10;
    private int CHAR_COUNT_MAX = ServiceStarter.ERROR_UNKNOWN;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void clearContent() {
        this.Content = "";
        this.LengthNeeded = 1;
        this.hasChar = false;
        this.count = 0;
        this.sendCalendar = null;
        this.lastTimeReceiveData = 0L;
        this.sendCalendar = Calendar.getInstance();
        notifyAll();
    }

    public synchronized String getMsg(int i) {
        String str;
        this.LengthNeeded = i;
        notifyAll();
        if (this.LengthNeeded > this.Content.length()) {
            this.available = false;
            while (!this.available && this.count < this.CHAR_COUNT_MAX) {
                try {
                    wait(this.waitMillis);
                    this.count++;
                } catch (InterruptedException unused) {
                }
                if (!this.available) {
                    if (!this.hasChar) {
                        if ((this.sendCalendar != null && System.currentTimeMillis() - this.sendCalendar.getTimeInMillis() > NO_DATA_WAIT_MILLS) || this.count > this.CHAR_COUNT_MAX) {
                            break;
                        }
                    } else if (System.currentTimeMillis() - this.lastTimeReceiveData > 1000) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (this.available) {
            str = this.Content.substring(0, this.LengthNeeded);
        } else {
            str = this.Content;
        }
        clearContent();
        return str;
    }

    public synchronized void putChar(int i) {
        Character ch = new Character((char) i);
        if (i == 255 && this.Content.length() == 0) {
            return;
        }
        String concat = this.Content.concat(ch.toString());
        this.Content = concat;
        if (this.LengthNeeded <= concat.length()) {
            this.available = true;
        }
        if (!this.hasChar) {
            this.hasChar = true;
            this.count = 0;
        }
        this.lastTimeReceiveData = System.currentTimeMillis();
        notifyAll();
    }
}
