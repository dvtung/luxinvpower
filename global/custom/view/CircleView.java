package com.nfcx.luxinvpower.global.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes2.dex */
public class CircleView extends View {
    private Paint paint;
    private int size;

    public CircleView(Context context) {
        super(context);
        this.paint = new Paint();
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
    }

    public CircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
        this.size = min;
        setMeasuredDimension(min, min);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(-1);
        this.paint.setColor(-7829368);
        this.paint.setStyle(Paint.Style.STROKE);
        int i = this.size;
        float f = i * 0.1f;
        float f2 = i - f;
        canvas.drawOval(new RectF(f, f, f2, f2), this.paint);
    }
}
