package com.ecommerce.ecommerpizzas.view.main;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.fragmentTab.FragmentConfirmation;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentAddUser;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentLogin;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMenuImp;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMyCart;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentOrderSummary;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentPembayaran;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentStatusOrder;
import com.j256.ormlite.stmt.query.In;

import android.app.NotificationManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.image_menu)
    ImageView btnMenu;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
                //textTitle.setText("Coba");
            }
        });

        Bundle bundle = getIntent().getExtras();
        Integer statusOrder = 0;
        boolean openStatusOrderFragment = false;
        if (bundle != null) {
            if(bundle.getString("status_order") != null && !bundle.getString("status_order").isEmpty()){
                statusOrder = Integer.parseInt(bundle.getString("status_order"));
                openStatusOrderFragment = true;
                Log.i("Status Order", statusOrder.toString());
            }
// else{
//                Long statusOrderLong = bundle.getLong("status_order");
//                statusOrder = Integer.parseInt(statusOrderLong.toString());
//                openStatusOrderFragment = true;
//                Log.i("Status Order Background", statusOrder.toString());
//            }

//            CharSequence textToast = values;
//            Toast toast = Toast.makeText(this,textToast, Toast.LENGTH_SHORT);
//            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
//            if( textView != null) textView.setGravity(Gravity.CENTER);
//            toast.show();
        }

        if(openStatusOrderFragment){
            fragmentManager = getSupportFragmentManager();
            FragmentStatusOrder fragmentStatusOrder = new FragmentStatusOrder();
            fragmentStatusOrder.setStatusOrder(statusOrder);
            fragmentManager.beginTransaction().replace(R.id.content_main, fragmentStatusOrder).commit();
        }else{
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, new FragmentLogin()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentLogin()).commit();
            textTitle.setText("Pizza E-Commerce");
        } else if(id == R.id.nav_add_user){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentAddUser()).commit();
            textTitle.setText("Add User");
        } else if(id == R.id.nav_menu_utama){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentMenuImp()).commit();
            textTitle.setText("Pizza E-Commerce");
        } else if(id == R.id.nav_my_cart){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentMyCart()).commit();
            textTitle.setText("My Cart");
        }
//        else if(id == R.id.nav_confirmation){
//            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentPembayaran()).commit();
//            textTitle.setText("Pembayaran");
//        }
        else if(id == R.id.nav_summary){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentConfirmation()).commit();
            textTitle.setText("Order Summary");
        }else if(id == R.id.nav_order_status){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentStatusOrder()).commit();
            textTitle.setText("Status Order");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void showHeader(String header){

    }

}
