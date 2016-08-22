package com.example.android.curator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;

/**
 * Created by Danish on 8/22/2016.
 */
public class readerView extends AppCompatActivity {

    private String headline, article, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_view);

        Intent i = getIntent();
        url = i.getStringExtra("articleURL");
        getData(url);
    }

    readerView(String url) {
        this.url = url;
    }


    public void getData(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            headline = document.title();
            Elements description = document.select("meta[name=description]");
            Elements desc1 = document.select("p");
            article = desc1.text();
//            Elements title1 = document.select("meta[name=title]");
//            title = title1.attr("content");

            TextView headlineView = (TextView) findViewById(R.id.reader_headline);
            TextView articleView = (TextView) findViewById(R.id.reader_article);

            headlineView.setText(headline);
            articleView.setText(article);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
