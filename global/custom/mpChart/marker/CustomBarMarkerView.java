package com.nfcx.luxinvpower.global.custom.mpChart.marker;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CustomBarMarkerView extends MarkerView {
    private static final int ENERGY_TYPE_MONTH = 0;
    private static final int ENERGY_TYPE_TOTAL = 2;
    private static final int ENERGY_TYPE_YEAR = 1;
    private BarChart barChart;
    private TextView batteryTextView;
    private TextView consumptionTextView;
    private int currentEnergyType;
    private TextView gridTextView;
    private TextView solarPvTextView;
    private TextView xValueTextView;

    public CustomBarMarkerView(Context context, BarChart barChart, int i, int i2) {
        super(context, i);
        this.currentEnergyType = i2;
        this.barChart = barChart;
        UserData userData = GlobalInfo.getInstance().getUserData();
        Inverter currentInverter = userData.getCurrentInverter();
        try {
            JSONObject energyChartColors = userData.getChartColor().getEnergyChartColors();
            this.xValueTextView = (TextView) findViewById(R.id.xValueTextView);
            TextView textView = (TextView) findViewById(R.id.solarPvTextView);
            this.solarPvTextView = textView;
            textView.setTextColor(Color.parseColor(energyChartColors.getString("Solar Production")));
            TextView textView2 = (TextView) findViewById(R.id.batteryTextView);
            this.batteryTextView = textView2;
            textView2.setTextColor(Color.parseColor(energyChartColors.getString("Battery")));
            this.gridTextView = (TextView) findViewById(R.id.gridTextView);
            if (currentInverter.isSnaSeries()) {
                this.gridTextView.setTextColor(Color.parseColor(energyChartColors.getString("Import to User")));
            } else {
                this.gridTextView.setTextColor(Color.parseColor(energyChartColors.getString("Export to Grid")));
            }
            TextView textView3 = (TextView) findViewById(R.id.consumptionTextView);
            this.consumptionTextView = textView3;
            textView3.setTextColor(Color.parseColor(energyChartColors.getString("Consumption")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry entry, Highlight highlight) {
        Integer num;
        int i = this.currentEnergyType;
        if (i == 0) {
            this.xValueTextView.setText("Day: " + Math.round(entry.getX()));
        } else if (i == 1) {
            this.xValueTextView.setText("Month: " + Math.round(entry.getX()));
        } else if (i == 2) {
            this.xValueTextView.setText("Year: " + Math.round(entry.getX()));
        } else {
            this.xValueTextView.setText("Date: " + Math.round(entry.getX()));
        }
        BarDataSet barDataSet = (BarDataSet) ((BarData) this.barChart.getData()).getDataSets().get(highlight.getDataSetIndex());
        int i2 = 0;
        while (true) {
            if (i2 >= barDataSet.getValues().size()) {
                num = null;
                break;
            } else {
                if (((Entry) barDataSet.getValues().get(i2)).getX() == entry.getX()) {
                    num = Integer.valueOf(i2);
                    break;
                }
                i2++;
            }
        }
        if (num != null) {
            for (int i3 = 0; i3 < ((BarData) this.barChart.getData()).getDataSets().size(); i3++) {
                List<T> values = ((BarDataSet) ((IDataSet) ((BarData) this.barChart.getData()).getDataSets().get(i3))).getValues();
                if (!values.isEmpty() && values.size() > num.intValue()) {
                    Entry entry2 = (Entry) values.get(num.intValue());
                    if (i3 == 0) {
                        this.solarPvTextView.setText("" + entry2.getY());
                    } else if (i3 == 1) {
                        this.batteryTextView.setText("" + entry2.getY());
                    } else if (i3 == 2) {
                        this.gridTextView.setText("" + entry2.getY());
                    } else if (i3 == 3) {
                        this.consumptionTextView.setText("" + entry2.getY());
                    }
                } else if (i3 == 0) {
                    this.solarPvTextView.setText("");
                } else if (i3 == 1) {
                    this.batteryTextView.setText("");
                } else if (i3 == 2) {
                    this.gridTextView.setText("");
                } else if (i3 == 3) {
                    this.consumptionTextView.setText("");
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
