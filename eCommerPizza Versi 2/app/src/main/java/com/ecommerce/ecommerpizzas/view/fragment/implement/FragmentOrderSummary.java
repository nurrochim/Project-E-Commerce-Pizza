package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.Space;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;

import com.ecommerce.ecommerpizzas.models.entity.MyCart;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 03/06/2017.
 */

public class FragmentOrderSummary extends BaseFragment {
    @BindView(R.id.tableLayoutSum)
    TableLayout tableSum;
    @BindView(R.id.divider_layout)
    LinearLayout dividerLayout;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.order_summary, container, false);
        ButterKnife.bind(this, view);
        loadData();
    }

    public void loadData(){
        try {
            openDatabaseHelper();
            List<MyCart> myCartList = myCartsDao.queryForAll();

            // remove exist row
            int size = tableSum.getChildCount();
            for (int i = 2; i < size; i++) {
                View child = tableSum.getChildAt(i);
                if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
            }

            // write row
            for (int i = 0; i < myCartList.size(); i++) {
                MyCart myCart = myCartList.get(i);
                TableRow tr = new TableRow(view.getContext());
                tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

                TextView textName = new TextView(view.getContext());
                textName.setText(myCart.getMenuName());
                textName.setPadding(5, 0, 5, 0);
                tr.addView(textName);

                TextView textQTY = new TextView(view.getContext());
                textQTY.setText(myCart.getQty());
                textQTY.setPadding(5, 0, 5, 0);
                textQTY.setGravity(Gravity.CENTER);
                textQTY.setBackgroundResource(R.drawable.shape_radius_layout);
                tr.addView(textQTY);

                TextView textPrice = new TextView(view.getContext());
                textPrice.setText(myCart.getHarga());
                textPrice.setPadding(5, 0, 5, 0);
                textPrice.setGravity(Gravity.RIGHT);
                tr.addView(textPrice);



                int[] attrs = { android.R.attr.listDivider };
                TypedArray ta = getContext().obtainStyledAttributes(attrs);
                //Get Drawable and use as needed
                Drawable divider = ta.getDrawable(0);
                LinearLayout ly  = new LinearLayout(getContext());
                ly.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
                ly.setBackground(divider);

                tr.setPadding(0,3,0,3);
                tableSum.addView(tr);
                tableSum.addView(ly);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbh.close();
        }
    }
}
