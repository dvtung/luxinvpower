package com.nfcx.luxinvpower.view.main.fragment.lv1;

import androidx.fragment.app.Fragment;

/* loaded from: classes2.dex */
public class AbstractItemFragment extends Fragment {
    public boolean isAdded;
    protected long itemId;

    public AbstractItemFragment(long j) {
        this.itemId = j;
    }

    public long getItemId() {
        return this.itemId;
    }
}
