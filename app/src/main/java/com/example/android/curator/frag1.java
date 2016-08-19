package com.example.android.curator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Danish on 7/23/2016.
 */
public class frag1 extends Fragment {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public RecyclerView.Adapter myAdapter;
    xmlParse obj;
    private List<String> urls = new ArrayList<>();
    private Typeface tfh;
    private Typeface tfd;
    private String url1 = "http://www.thehindu.com/news/?service=rss";
    private String url2 = "http://timesofindia.indiatimes.com/rssfeedstopstories.cms";
    private String url3 = "http://feeds.feedburner.com/ndtvnews-top-stories?format=xml";
    private String url4 = "http://feeds.bbci.co.uk/news/world/asia/rss.xml";
//
//    private String url1 = "http://feeds.bbci.co.uk/news/world/rss.xml";
//    private String url2 = "http://feeds.feedburner.com/ndtvnews-world-news";
//    private String url3 = "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";
//    private String url4 = "http://www.thehindu.com/news/international/?service=rss";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_frag1);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        final SwipeRefreshLayout mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_view);
        if (mSwipeRefresh != null)
            mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshItems();
                    mSwipeRefresh.setRefreshing(false);
                }
            });


        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        urls.add(url4);
        parsing();


        tfh = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Play-B.ttf");
        tfd = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Roboto-R.ttf");

        recyclerView.setLayoutManager(mLayoutManager);
        final MyAdapter myAdapter = new MyAdapter(getDataSet(), tfh, tfd, mLayoutManager, recyclerView);
        myAdapter.setContext(getActivity().getApplicationContext());

        recyclerView.setAdapter(myAdapter);
        registerForContextMenu(recyclerView);
//        RecyclerView.ItemDecoration itemDecoration =
//                new DividerItemDecoration(getActivity().getApplicationContext());
//        recyclerView.addItemDecoration(itemDecoration);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration());

        return view;
    }


    public void refreshItems() {
        parsing();
        myAdapter = new MyAdapter(getDataSet(), tfh, tfd, (LinearLayoutManager) mLayoutManager, recyclerView);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }


    public void parsing() {

        obj = new xmlParse();

        for (int i = 0; i < urls.size(); i++) {
            obj.fetchXML(urls.get(i));
        }
    }

    public List<news> getDataSet() {
        Collections.shuffle(obj.newsList);
        return obj.newsList;
    }
}
