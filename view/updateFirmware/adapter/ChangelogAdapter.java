package com.nfcx.luxinvpower.view.updateFirmware.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.view.updateFirmware.item.ChangelogItem;
import java.util.List;

/* loaded from: classes2.dex */
public class ChangelogAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<ChangelogItem> changelogItems;

    public ChangelogAdapter(List<ChangelogItem> list) {
        this.changelogItems = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_firmware_item_changelog, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.changelogItems.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.changelogItems.size();
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView createTimeTextView;
        TextView descriptionTextView;
        TextView fwCodeTextView;

        public ViewHolder(View view) {
            super(view);
            this.fwCodeTextView = (TextView) view.findViewById(R.id.fwCodeTextView);
            this.createTimeTextView = (TextView) view.findViewById(R.id.createTimeTextView);
            this.descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
        }

        public void bind(ChangelogItem changelogItem) {
            this.fwCodeTextView.setText(changelogItem.getFwCode());
            this.createTimeTextView.setText(changelogItem.getCreateTime());
            this.descriptionTextView.setText(changelogItem.getDescription());
        }
    }
}
