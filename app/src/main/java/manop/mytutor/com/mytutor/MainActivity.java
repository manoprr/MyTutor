package manop.mytutor.com.mytutor;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import manop.mytutor.com.mytutor.fragment.MainFragment;
import manop.mytutor.com.mytutor.tablayout.TablayoutMycourseFragment;
import manop.mytutor.com.mytutor.tablayout.TablayoutRecentFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.FootBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);



//        Add Fragment to Activity
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentMainFragment, new MainFragment())
                    .commit();
                           }



    }   // Main Method

    private BottomNavigationView.OnNavigationItemSelectedListener navListener   =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.btnHome:
                            selectedFragment = new MainFragment();
                            break;
                        case R.id.btnChat:
                            selectedFragment = new MainFragment();
                            break;

                        case R.id.btnRecent:
                            selectedFragment = new TablayoutRecentFragment();
                            break;
                        case R.id.btnAccount:
                            selectedFragment = new MainFragment();
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentMainFragment,
                            selectedFragment).commit();
                    return true;


                }
            };

}   // Main Class
