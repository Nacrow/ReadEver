package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.BookFragment;
import com.example.myapplication.ui.Item;
import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.PwdActivity;
import com.example.myapplication.ui.SearchFragment;
import com.example.myapplication.ui.SessionHandler;
import com.example.myapplication.ui.User;
import com.example.myapplication.ui.creditActivity;
import com.example.myapplication.ui.fragment_order;
import com.example.myapplication.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    com.google.android.material.internal.NavigationMenuItemView modify_pwd;
    com.google.android.material.internal.NavigationMenuItemView credit;
    com.google.android.material.internal.NavigationMenuItemView logout;
    private AppBarConfiguration mAppBarConfiguration;
    private com.google.android.material.navigation.NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav=findViewById(R.id.nav_view);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_pwd, R.id.nav_credit, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        BottomNavigationView bottomNavigation= findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
       NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        final SessionHandler session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView fullname=findViewById(R.id.header_fullname);
        TextView Username=findViewById(R.id.header_username);
        TextView balanc=findViewById(R.id.header_price);
        fullname.setText(user.getFullName());
        Username.setText(user.getUsername());
        DatabaseHelperUser db= new DatabaseHelperUser(getApplicationContext());
        int d;
        Cursor res=db.inquery(user.getUsername());
        res.moveToFirst();
        d=res.getInt(4);
        balanc.setText("Blance: "+String.valueOf(d));
        modify_pwd=findViewById(R.id.nav_pwd);
        modify_pwd.setOnClickListener(new com.google.android.material.internal.NavigationMenuItemView.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PwdActivity.class));
            }
        });
        logout=findViewById(R.id.nav_logout);
        logout.setOnClickListener(new com.google.android.material.internal.NavigationMenuItemView.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        credit=findViewById(R.id.nav_credit);
        credit.setOnClickListener(new com.google.android.material.internal.NavigationMenuItemView.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, creditActivity.class));
            }
        });

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    item.setChecked(true);
                    break;
                case R.id.nav_search:
                    selectedFragment = new SearchFragment();
                    item.setChecked(true);
                    break;
                case R.id.nav_book:
                    selectedFragment = new BookFragment();
                    item.setChecked(true);
                    break;
                case R.id.nav_shopping:
                    selectedFragment = new fragment_order();
                    item.setChecked(true);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
            return false;
        }
    };
}