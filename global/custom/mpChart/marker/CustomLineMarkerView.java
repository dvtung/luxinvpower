package com.nfcx.luxinvpower.global.custom.mpChart.marker;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.InvTool;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CustomLineMarkerView extends MarkerView {
    private TextView batteryTextView;
    private TextView consumptionTextView;
    private TextView gridTextView;
    private LineChart lineChart;
    private TextView socTextView;
    private TextView solarPvTextView;
    private TextView xValueTextView;

    public CustomLineMarkerView(Context context, LineChart lineChart, int i) {
        super(context, i);
        this.lineChart = lineChart;
        try {
            JSONObject chartColors = GlobalInfo.getInstance().getUserData().getChartColor().getChartColors();
            this.xValueTextView = (TextView) findViewById(R.id.xValueTextView);
            TextView textView = (TextView) findViewById(R.id.solarPvTextView);
            this.solarPvTextView = textView;
            textView.setTextColor(Color.parseColor(chartColors.getString("Solar PV")));
            TextView textView2 = (TextView) findViewById(R.id.batteryTextView);
            this.batteryTextView = textView2;
            textView2.setTextColor(Color.parseColor(chartColors.getString("Battery")));
            TextView textView3 = (TextView) findViewById(R.id.gridTextView);
            this.gridTextView = textView3;
            textView3.setTextColor(Color.parseColor(chartColors.getString("Grid")));
            TextView textView4 = (TextView) findViewById(R.id.consumptionTextView);
            this.consumptionTextView = textView4;
            textView4.setTextColor(Color.parseColor(chartColors.getString("Consumption")));
            TextView textView5 = (TextView) findViewById(R.id.socTextView);
            this.socTextView = textView5;
            textView5.setVisibility(0);
            this.socTextView.setTextColor(Color.parseColor(chartColors.getString("SOC")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry entry, Highlight highlight) {
        Integer num;
        int round = Math.round(entry.getX());
        this.xValueTextView.setText(InvTool.fillZero(round / 3600) + ":" + InvTool.fillZero((round % 3600) / 60) + ":" + InvTool.fillZero(round % 60));
        LineDataSet lineDataSet = (LineDataSet) ((LineData) this.lineChart.getData()).getDataSets().get(highlight.getDataSetIndex());
        int i = 0;
        while (true) {
            if (i >= lineDataSet.getValues().size()) {
                num = null;
                break;
            } else {
                if (((Entry) lineDataSet.getValues().get(i)).getX() == entry.getX()) {
                    num = Integer.valueOf(i);
                    break;
                }
                i++;
            }
        }
        if (num != null) {
            for (int i2 = 0; i2 < ((LineData) this.lineChart.getData()).getDataSets().size(); i2++) {
                Entry entry2 = (Entry) ((LineDataSet) ((IDataSet) ((LineData) this.lineChart.getData()).getDataSets().get(i2))).getValues().get(num.intValue());
                if (i2 == 0) {
                    this.solarPvTextView.setText("" + entry2.getY());
                } else if (i2 == 1) {
                    this.batteryTextView.setText("" + entry2.getY());
                } else if (i2 == 2) {
                    this.gridTextView.setText("" + entry2.getY());
                } else if (i2 == 3) {
                    this.consumptionTextView.setText("" + entry2.getY());
                } else if (i2 == 4) {
                    this.socTextView.setText("" + Math.round(entry2.getY()) + "%");
                }
            }
        }
        super.refreshContent(entry, highlight);
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
