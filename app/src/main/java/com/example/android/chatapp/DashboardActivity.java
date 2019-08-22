package com.example.android.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity{

    ViewPager viewPager;
    TabLayout tabLayout;
    private FirebaseAuth auth;
    private TabAccessAdapter TabAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toast.makeText(this, "This is Dashboard",
                Toast.LENGTH_SHORT).show();
        auth = FirebaseAuth.getInstance();
        viewPager = (ViewPager) findViewById(R.id.main_tabs_pager);
        TabAdapter = new TabAccessAdapter(getSupportFragmentManager());
        viewPager.setAdapter(TabAdapter);

        tabLayout = (TabLayout) findViewById(R.id.mail_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         if(item.getItemId() == R.id.find) {}
        if(item.getItemId() == R.id.settings) {}
        if(item.getItemId() == R.id.Logout) {
            auth.signOut();
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
        }
        return true;
    }
}
