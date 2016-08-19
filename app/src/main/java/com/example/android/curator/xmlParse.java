package com.example.android.curator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danish on 6/6/2016.
 */

public class xmlParse {

    public String title = null;
    public String content = null;
    private String link = null;
    public volatile boolean parsingComplete = true;
    public List<String> titles = new ArrayList<>();
    public List<String> contents = new ArrayList<>();
    public List<news> newsList = new ArrayList<>();
    private String baseUrl1;

    List<news> getList() {
        return newsList;
    }

    xmlParse() {

    }

    public void setUrl(String url) {
        baseUrl1 = url;
    }


    public void parseXML(XmlPullParser myParser) {
        int event;
        String text = null;

        try {
            myParser.next();
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if (name.equals("title")) {
                            title = text;//(null, "value");
                            //titles.add(title);
                        } else if (name.equals("description")) {
                            content = text;//(null, "value");
                            //contents.add(content);
                        } else if (name.equals("link")) {
                            link = text;
                        }
                        if (title != null && content != null && link != null && title.length() > 15 && content.length() > 80) {
                            newsList.add(new news(title, content, link));
                            title = content = link = null;
                        }
                        break;
                }

                event = myParser.next();

            }
            parsingComplete = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fetchXML(final String baseUrl) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //for(int i=0; i<urlList.size(); i++) {
                    URL url = new URL(baseUrl);

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser parser = factory.newPullParser();

                    // We will get the XML from an input stream
                    parser.setInput(getInputStream(url), "UTF_8");

                    parseXML(parser);
                    //stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

}






















