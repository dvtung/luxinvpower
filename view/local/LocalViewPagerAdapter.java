package com.nfcx.luxinvpower.view.local;

import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment;
import com.nfcx.luxinvpower.view.local.fragment.LocalOffGridSetFragment;
import com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class LocalViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList;
    private Set<Long> itemIds;
    private Local12KSetFragment local12KSetFragment;
    private LocalOffGridSetFragment localOffGridSetFragment;
    private LocalSetFragment localSetFragment;
    private RecyclerView recyclerView;

    public LocalViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, List<Fragment> list, LocalSetFragment localSetFragment, LocalOffGridSetFragment localOffGridSetFragment, Local12KSetFragment local12KSetFragment) {
        super(fragmentManager, lifecycle);
        this.itemIds = new HashSet();
        this.fragmentList = list;
        this.localSetFragment = localSetFragment;
        this.localOffGridSetFragment = localOffGridSetFragment;
        this.local12KSetFragment = local12KSetFragment;
    }

    public void switchLocalSetFragment(int i) {
        if (this.fragmentList.size() > 1) {
            this.fragmentList.remove(1);
        }
        if (i == 6) {
            this.fragmentList.add(this.local12KSetFragment);
        } else if (i == 3) {
            this.fragmentList.add(this.localOffGridSetFragment);
        } else {
            this.fragmentList.add(this.localSetFragment);
        }
        HashSet hashSet = new HashSet();
        Iterator<Fragment> it = this.fragmentList.iterator();
        while (it.hasNext()) {
            hashSet.add(Long.valueOf(it.next().hashCode()));
        }
        this.itemIds = hashSet;
        new Handler().post(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.LocalViewPagerAdapter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LocalViewPagerAdapter.this.m321x7ddf55f0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$switchLocalSetFragment$0$com-nfcx-luxinvpower-view-local-LocalViewPagerAdapter, reason: not valid java name */
    public /* synthetic */ void m321x7ddf55f0() {
        for (int i = 0; i < 100; i++) {
            Tool.sleep(10L);
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView != null && !recyclerView.isComputingLayout()) {
                notifyDataSetChanged();
                return;
            }
        }
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        return this.fragmentList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.fragmentList.size();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.fragmentList.get(i).hashCode();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public boolean containsItem(long j) {
        return this.itemIds.contains(Long.valueOf(j));
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
}
