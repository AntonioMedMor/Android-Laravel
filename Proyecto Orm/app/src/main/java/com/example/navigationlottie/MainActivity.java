package com.example.navigationlottie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationlottie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private int selectedTab=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final LinearLayout homeLayout=findViewById(R.id.homeLayout);
        final LinearLayout galleryLayout=findViewById(R.id.galleryLayout);
        final LinearLayout slideshowLayout=findViewById(R.id.slideshowLayout);

        final LottieAnimationView homeView = findViewById(R.id.homeView);
        final LottieAnimationView galleryView = findViewById(R.id.galleryView);
        final LottieAnimationView slideshowView = findViewById(R.id.slideshowView);

        final TextView homeTxt = findViewById(R.id.homeTxt);
        final TextView galleryTxt = findViewById(R.id.galleryTxt);
        final TextView slideshowTxt = findViewById(R.id.slideshowTxt);


        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
                MenuItem item = binding.navView.getMenu().findItem(R.id.nav_home);
                NavigationUI.onNavDestinationSelected(item, navController);
                DrawerLayout drawer = binding.drawerLayout;
                drawer.closeDrawer(GravityCompat.START);

                if (selectedTab != 1) {
                    galleryTxt.setVisibility(View.GONE);
                    slideshowTxt.setVisibility(View.GONE);
                    galleryView.cancelAnimation();
                    slideshowView.cancelAnimation();

                    galleryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    slideshowLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeLayout.setBackgroundResource(R.drawable.menu_item);
                    homeTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);
                    homeView.playAnimation();
                    selectedTab = 1;
                }
            }
        });

        galleryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
                MenuItem item = binding.navView.getMenu().findItem(R.id.nav_gallery);
                NavigationUI.onNavDestinationSelected(item, navController);
                DrawerLayout drawer = binding.drawerLayout;
                drawer.closeDrawer(GravityCompat.START);

                if (selectedTab != 2) {
                    homeTxt.setVisibility(View.GONE);
                    slideshowTxt.setVisibility(View.GONE);
                    homeView.cancelAnimation();
                    slideshowView.cancelAnimation();

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    slideshowLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    galleryLayout.setBackgroundResource(R.drawable.menu_item);
                    galleryTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    galleryLayout.startAnimation(scaleAnimation);
                    galleryView.playAnimation();
                    selectedTab = 2;
                }
            }
        });

        slideshowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
                MenuItem item = binding.navView.getMenu().findItem(R.id.nav_slideshow);
                NavigationUI.onNavDestinationSelected(item, navController);
                DrawerLayout drawer = binding.drawerLayout;
                drawer.closeDrawer(GravityCompat.START);

                if (selectedTab != 3) {
                    homeTxt.setVisibility(View.GONE);
                    galleryTxt.setVisibility(View.GONE);
                    homeView.cancelAnimation();
                    galleryView.cancelAnimation();

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    galleryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    slideshowLayout.setBackgroundResource(R.drawable.menu_item);
                    slideshowTxt.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    slideshowLayout.startAnimation(scaleAnimation);
                    slideshowView.playAnimation();
                    selectedTab = 3;
                }
            }
        });

        /*setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}