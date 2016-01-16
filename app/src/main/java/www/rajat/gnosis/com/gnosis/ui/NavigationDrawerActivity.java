package www.rajat.gnosis.com.gnosis.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import www.rajat.gnosis.com.gnosis.R;

public class NavigationDrawerActivity extends AppCompatActivity {

    private ListView categoryList;
    private ListView navList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        String[] categories = {
                "Sports",
                "Music",
                "Arts",
                "Culture",
                "College Tutors",
                "Academics"
        };

        String[] navItems = {
                "Profile",
                "Contact Us",
                "About Us"
        };

        categoryList = (ListView) findViewById(R.id.category_list);
        ArrayAdapter<String> mCategoryAdapter = new ArrayAdapter<String>(this, R.layout.category_item, R.id.category_item_textview, categories);
        categoryList.setAdapter(mCategoryAdapter);

        navList = (ListView) findViewById(R.id.navList);
        ArrayAdapter<String> mNavAdapter = new ArrayAdapter<String>(this, R.layout.nav_item, R.id.nav_item_textview, navItems);
        navList.setAdapter(mNavAdapter);

    }
}
