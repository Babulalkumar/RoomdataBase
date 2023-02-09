package com.example.mvvmpattern;

import android.os.Bundle;

import com.example.mvvmpattern.adpter.ViewPagerAdpter;
import com.example.mvvmpattern.database.BooksDataBase;
import com.example.mvvmpattern.ui.dashboard.DashboardFragment;
import com.example.mvvmpattern.ui.home.HomeFragment;
import com.example.mvvmpattern.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mvvmpattern.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        binding = ActivityMainBinding.inflate (getLayoutInflater ());
        setContentView (binding.getRoot ());
        //   BottomNavigationView navView = findViewById (R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder (
                R.id.navigation_home, R.id.navigation_dashboard)
                .build ();
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController (this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController (binding.navView, navController);*/
        Bundle bundle = new Bundle();
        bundle.putString("amount", "amount");
      //  Navigation.findNavController(navController).navigate(R.id.navigation_notification, bundle);

        // setting up the adapter
        ViewPagerAdpter viewPagerAdapter = new ViewPagerAdpter (getSupportFragmentManager ());

        // add the fragments
        viewPagerAdapter.Add (new HomeFragment (), "Home");
        viewPagerAdapter.Add (new DashboardFragment (), "DashBoard");
        viewPagerAdapter.Add (new NotificationsFragment (), "Notification");

        // Set the adapter
        binding.viewpager.setAdapter (viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        binding.tabLayout.setupWithViewPager (binding.viewpager);
    }

}