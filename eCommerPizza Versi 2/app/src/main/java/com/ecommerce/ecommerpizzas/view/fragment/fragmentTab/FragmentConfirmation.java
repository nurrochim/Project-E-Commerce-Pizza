package com.ecommerce.ecommerpizzas.view.fragment.fragmentTab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.adapter.MyFragmentPagerAdapter;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentOrderSummary;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentPembayaran;

import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 10/06/2017.
 */

public class FragmentConfirmation extends BaseFragment implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    private TabHost tabHost;
    public static MyFragmentPagerAdapter myViewPagerAdapter;

    @BindView(R.id.TabScroll)
    HorizontalScrollView hScrollView;
    @BindView(R.id.viewPagerFacilitySimulation)
    ViewPager viewPager;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        ButterKnife.bind(this, view);
        this.initializeTabHost();
        this.initializeViewPager();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String s) {
        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);
        View tabView = tabHost.getCurrentTabView();

        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);
    }

    // fake content for tabhost
    class FakeContent implements TabHost.TabContentFactory {
        private final Context mContext;

        public FakeContent(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }

    private void initializeViewPager() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(new FragmentOrderSummary());
        fragments.add(new FragmentPembayaran());
        this.myViewPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), fragments);
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.setOnPageChangeListener(this);

    }

    private void initializeTabHost() {

        tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup();


        // Tab Summary
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("Summary");
        tabSpec.setIndicator("Summary");
        tabSpec.setContent(new FakeContent(getActivity()));
        tabHost.addTab(tabSpec);

        // Tab Payment
        TabHost.TabSpec tabSpecPS;
        tabSpecPS = tabHost.newTabSpec("Payment");
        tabSpecPS.setIndicator("Payment");
        tabSpecPS.setContent(new FakeContent(getActivity()));
        tabHost.addTab(tabSpecPS);

        tabHost.setOnTabChangedListener(this);
    }
}
