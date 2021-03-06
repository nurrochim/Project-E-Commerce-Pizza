package com.ecommerce.ecommercepizza;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecommerce.ecommercepizza.fragment.AddUser;
import com.ecommerce.ecommercepizza.fragment.FragmentLogin;
import com.ecommerce.ecommercepizza.fragment.FragmentMenu;
import com.ecommerce.ecommercepizza.fragment.FragmentMyCart;
import com.ecommerce.ecommercepizza.fragment.FragmentPembayaran;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static NavigationView navigationView;
    ImageView btnMenu;
    public static DrawerLayout drawer;
    public static TextView textTitle;
    List<Fragment> listFragments;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTitle = (TextView) findViewById(R.id.text_title);
        btnMenu = (ImageView)  findViewById(R.id.image_menu);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
                textTitle.setText("Coba");
            }
        });

        listFragments = new ArrayList<Fragment>();
        listFragments.add(new FragmentMenu());
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, listFragments.get(0)).commit();

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentLogin()).commit();
            textTitle.setText("Pizza E-Commerce");
//            Intent intent = new Intent(getApplicationContext(), AddUser.class);
//            startActivityForResult(intent, 0);
        } else if(id == R.id.nav_add_user){
            fragmentManager.beginTransaction().replace(R.id.content_main,new AddUser()).commit();
            textTitle.setText("Add User");
        } else if(id == R.id.nav_menu_utama){
            fragmentManager.beginTransaction().replace(R.id.content_main,new FragmentMenu()).commit();
            textTitle.setText("Menu");
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
}
