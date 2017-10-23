package com.linalgs.lgspractica4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fm;
    FragmentTransaction ft;
    private String correoR, contraseñaR, urlf;
    GoogleApiClient mGoogleApiClient;
    //1 registro, 2 es google, 3 facebook
    private int optlog;
    //SharedPreferences prefs;
    //SharedPreferences.Editor editor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            correoR = extras.getString("correo");
            contraseñaR = extras.getString("contraseña");
            urlf = extras.getString("urlf");
            //textView.setText(String.valueOf(extras.getString("correo")));
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        //final int optLog = prefs.getInt("optlog",0);

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            MSemillaFragment fragment = new MSemillaFragment();
            ft.replace(R.id.frame,fragment).commit();

        } else if (id == R.id.nav_gallery) {
            SwipeFragment fragment = new SwipeFragment();
            ft.replace(R.id.frame,fragment).commit();

        } else if (id == R.id.nav_slideshow) {
            Swipe2Fragment fragment = new Swipe2Fragment();
            ft.replace(R.id.frame,fragment).commit();

        } else if (id == R.id.nav_manage) {
            BottFragment fragment = new BottFragment();
            ft.replace(R.id.frame,fragment).commit();

        } else if (id == R.id.nav_share) {
            UPerfilFragment fragment = new UPerfilFragment();
            ft.replace(R.id.frame,fragment).commit();

        } else if (id == R.id.nav_send) {
            Intent intent;
            //prefs = getSharedPreferences("MisPreferencias" , Context.MODE_PRIVATE);
            //editor = prefs.edit();
            //if(optLog == 1){
                intent = new Intent(MainActivity.this,LoginActivity.class);
                //almacenamos el valor de optLog
                //editor.putInt("optlog",1);
                //editor.commit();
                //intent.putExtra("correo",correoR);
                //intent.putExtra("password",contraseñaR);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();
            //}
            //else if (optLog == 2 ){logOutG();

                //almacenamos el valor de optLog
              //  editor.putInt("optlog",0);
                //editor.commit();
            //}
            //else if (optLog == 3){logOutF();

                //almacenamos el valor de optLog
//                editor.putInt("optlog",0);
 //               editor.commit();
           // }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void logOutF(){

        //LoginManager.getInstance().logOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.putExtra("correo",correoR);
        intent.putExtra("password",contraseñaR);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    public void logOutG (){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    intent.putExtra("correo",correoR);
                    intent.putExtra("password",contraseñaR);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(),"No se pudo cerrar session",Toast.LENGTH_SHORT).show();}

            }
        });

    }

}
