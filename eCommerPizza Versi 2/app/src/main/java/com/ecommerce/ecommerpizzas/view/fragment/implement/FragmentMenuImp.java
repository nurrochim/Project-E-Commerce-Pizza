package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.adapter.ListAdapter;
import com.ecommerce.ecommerpizzas.holder.MenuListHolder;
import com.ecommerce.ecommerpizzas.models.menu.MenuListData;
import com.ecommerce.ecommerpizzas.models.menu.MenuRespon;
import com.ecommerce.ecommerpizzas.presenter.menu.MenuPresenter;
import com.ecommerce.ecommerpizzas.utils.GridSpacingItemDecoration;
import com.ecommerce.ecommerpizzas.utils.Service;
import com.ecommerce.ecommerpizzas.utils.URLs;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.ecommerce.ecommerpizzas.view.fragment.MenuFragment;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 21/05/2017.
 */

public class FragmentMenuImp extends BaseFragment implements MenuFragment {
    @BindView(R.id.view_menu)
    RecyclerView listview;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindDrawable(R.drawable.loading)
    Drawable loading;

    @BindDrawable(R.drawable.error)
    Drawable error;

    @Inject
    public Service service;

    private ListAdapter adapter;
    private MenuPresenter menuPresenter;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.menu_pizza, container, false);
        ButterKnife.bind(this, view);
        getDeps().inject(this);
        menuPresenter = new MenuPresenter(this, service);
        menuPresenter.getMenuList();
    }


    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        createToast("Sory... Somethng Wrong... \n Try again later");
        //Log.e("Network Error ", appErrorMessage);
    }

    @Override
    public void getMenuListSuccess(MenuRespon menuRespon) {
        adapter = new ListAdapter<MenuListData, MenuListHolder>(
                R.layout.recycle_view_menu,
                MenuListHolder.class,
                MenuListData.class,
                menuRespon.getData()) {
            @Override
            protected void bindView(MenuListHolder holder, MenuListData model, int position) {
                holder.bind(model.getMenuName());
                Glide.with(getContext())
                        .load(URLs.getStorageUrl()+model.getImage1())
                        .placeholder(loading)
                        .error(error)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .animate(R.anim.fade_in)
                        //.fitCenter()
                        //.skipMemoryCache(true)
                        .into(holder.getImageView());

                holder.getMenuLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentMenuDetail fragment = new FragmentMenuDetail();
                        fragment.setModel(model);
                        FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();
                        fragmentTrans.addToBackStack(null);
                        fragmentTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTrans.replace(R.id.content_main, fragment);
                        fragmentTrans.commit();
                    }
                });
            }
        };

        listViewDecor();
    }

    public void listViewDecor() {
        listview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        int spanCount = 2; // 3 columns
        int spacing = 10; // 20px
        boolean includeEdge = true;
        listview.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        listview.setAdapter(adapter);
    }
}
