package com.ecommerce.ecommerpizzas.view.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentAddUser;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentLogin;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMenuImp;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMyCart;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentPembayaran;

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

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, new FragmentLogin()).commit();
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
        } else if(id == R.id.nav_confirmation){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentPembayaran()).commit();
            textTitle.setText("Pembayaran");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void showHeader(String header){

    }

}
