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

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        // Opcional: poner iconos en tabs
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab2);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab3);

        fab.setOnClickListener(v ->
                Snackbar.make(v, "Has pulsado el FAB", Snackbar.LENGTH_LONG)
                        .setAction("OK", click -> {})
                        .show()
        );
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "Formulario");
        adapter.addFragment(new SecondFragment(), "Segunda");
        adapter.addFragment(new ThirdFragment(), "Tercera");
        viewPager.setAdapter(adapter);
    }
}