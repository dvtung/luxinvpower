package com.nfcx.luxinvpower.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class ImageUtil {
    private static Map<String, Bitmap> mMemoryCache = new ConcurrentHashMap();
    private static int cacheSize = 0;

    public static String bitmapToBase64(Bitmap bitmap) {
        String str = "";
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                if (bitmap != null) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                            byteArrayOutputStream2.flush();
                            byteArrayOutputStream2.close();
                            str = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 0);
                            byteArrayOutputStream = byteArrayOutputStream2;
                        } catch (IOException e) {
                            e = e;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            e.printStackTrace();
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.flush();
                                byteArrayOutputStream.close();
                            }
                            return str;
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.flush();
                                    byteArrayOutputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return str;
    }

    public static Bitmap base64ToBitmap(String str, int i, int i2) {
        if (cacheSize == 0) {
            cacheSize = (int) ((Runtime.getRuntime().maxMemory() / 1024) / 8);
        }
        Bitmap bitmap = null;
        try {
            try {
                Bitmap bitmap2 = mMemoryCache.get(str);
                if (bitmap2 == null) {
                    try {
                        String replaceAll = str.replaceAll("data:image/jpeg;base64,", "");
                        byte[] decode = Base64.decode(replaceAll, 0);
                        bitmap = Bitmap.createBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length), i, i2, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
                        mMemoryCache.put(replaceAll, bitmap);
                        bitmap2 = bitmap;
                    } catch (Exception e) {
                        e = e;
                        bitmap = bitmap2;
                        e.printStackTrace();
                        try {
                            System.gc();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return bitmap;
                    }
                }
                try {
                    System.gc();
                    return bitmap2;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return bitmap2;
                }
            } catch (Throwable th) {
                try {
                    System.gc();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }
}
