package com.example.practica;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab = findViewById(R.id.fab);

        configurarViewPager();
        configurarTabs();
        configurarFAB();
    }

    private void configurarViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FirstFragment(), "Formulario");
        adapter.addFragment(new SecondFragment(), "Pestaña 2");
        adapter.addFragment(new ThirdFragment(), "Pestaña 3");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void configurarTabs() {
        if (tabLayout.getTabAt(0) != null)
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab1);

        if (tabLayout.getTabAt(1) != null)
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab2);

        if (tabLayout.getTabAt(2) != null)
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab3);
    }

    private void configurarFAB() {
        fab.setOnClickListener(view ->
                Snackbar.make(view,
                                "Acción principal ejecutada",
                                Snackbar.LENGTH_LONG)
                        .setAction("OK", v -> {})
                        .show()
        );
    }
}
