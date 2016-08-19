package com.example.android.curator;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    //private Context cxt  = this.getContext();
//    private String url1 = "http://www.thehindu.com/news/?service=rss";
//    private String url2 = "http://timesofindia.indiatimes.com/rssfeedstopstories.cms";
//    private String url3 = "http://feeds.feedburner.com/ndtvnews-top-stories?format=xml";
//    private String url4 = "http://feeds.bbci.co.uk/news/world/asia/rss.xml";
//    private List<String> urls = new ArrayList<>();
//    xmlParse obj2;
//    private Typeface tfh;
//    private Typeface tfd;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = getIntent().getIntExtra("theme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar ab = getSupportActionBar();
//        assert ab != null;
//        Spannable text = new SpannableString(ab.getTitle());
//        text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        ab.setTitle(text);
//        getSupportActionBar().setElevation(0);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setElevation(0);

        //getSupportActionBar().setDisplayOptions(too.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.atitle);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("India"));
        tabLayout.addTab(tabLayout.newTab().setText("World"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setElevation(0);


        PreferenceManager.setDefaultValues(this, R.xml.prefs, false);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final pageAdapter adapter = new pageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener((tabLayout)));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        urls.add(url1);
//        urls.add(url2);
//        urls.add(url3);
//        urls.add(url4);
//        parsing();


//        /**
//         * Implementing infinite list
//         */
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
//        {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//            {
//                if(dy > 0) //check for scroll down
//                {
//                    visibleItemCount = mLayoutManager.getChildCount();
//                    totalItemCount = mLayoutManager.getItemCount();
//                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
//
//                    if (loading)
//                    {
//                        if ( (visibleItemCount + pastVisibleItems) >= visibleThreshold)
//                        {
//                            loading = false;
//                            Log.v("...", "Last Item Wow !");
//                            //Do pagination.. i.e. fetch new data
//                            Toast toast = Toast.makeText(getApplicationContext(), "Working!", Toast.LENGTH_LONG);
//
//                            toast.show();
//                        }
//                    }
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Settings");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Settings")) {
            Intent i = new Intent(this, prefsActivity.class);
            startActivity(i);
        }
        return true;
    }


//    public List<news> getDataSet() {
//        Collections.shuffle(obj.newsList);
//        return obj.newsList;
//    }
//

}
