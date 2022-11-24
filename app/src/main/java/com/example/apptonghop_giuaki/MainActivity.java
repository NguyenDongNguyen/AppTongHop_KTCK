package com.example.apptonghop_giuaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apptonghop_giuaki.fragment.HomeFragment;
import com.example.apptonghop_giuaki.fragment.ProfileFragment;
import com.example.apptonghop_giuaki.fragment.ShowRecycler;
import com.example.apptonghop_giuaki.fragment.ShowRecycler;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_PROFILE = 0;
    private static final int FRAGMENT_LISTVIEW = 1;
    private static final int FRAGMENT_HOME = 2;

    private int mCurrentFragment = FRAGMENT_LISTVIEW;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    RecyclerView lvXeCon;
    ImageView imgAvatar;
    TextView tvName, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvXeCon = (RecyclerView) findViewById(R.id.rv);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnhXa();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new ShowRecycler());
        mNavigationView.getMenu().findItem(R.id.nav_listview_car).setChecked(true);

        showUserInformation();

    }

    private void AnhXa() {
        mNavigationView = findViewById(R.id.navigation_view);
        imgAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);
        tvName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.nav_my_profile) {
            if (mCurrentFragment != FRAGMENT_PROFILE) {
                replaceFragment(new ProfileFragment());
                mCurrentFragment = FRAGMENT_PROFILE;
            }
        }else if (id==R.id.nav_listview_car) {
            if (mCurrentFragment != FRAGMENT_LISTVIEW) {
                replaceFragment(new ShowRecycler());
                mCurrentFragment = FRAGMENT_LISTVIEW;
            }
        }else if (id==R.id.nav_home) {
            if (mCurrentFragment != FRAGMENT_HOME) {
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }else if (id==R.id.nav_sign_out) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
            finish();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    private void showUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if (name == null) {
            tvName.setVisibility(View.GONE);
        } else {
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(name);
        }
        tvEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.userdefault).into(imgAvatar);
    }

}