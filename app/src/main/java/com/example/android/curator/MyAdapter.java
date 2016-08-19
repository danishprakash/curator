package com.example.android.curator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Danish on 6/15/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    private static List<news> newsListA;// = new ArrayList<>;
    private static List<news> newsListB;
    private static Typeface tfh;
    private static Typeface tfd;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }


    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView headline;
        TextView description;
        private Context context;

        public myViewHolder(final View view, Context context) {
            super(view);
            headline = (TextView) view.findViewById(R.id.headline_view);
            description = (TextView) view.findViewById(R.id.description_view);

            /**
             * Opens the link in a browser for further reading
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(newsListA.get(getAdapterPosition()).getLink()));
                    view.getContext().startActivity(i);
                }
            });
            view.setOnCreateContextMenuListener(this);
//            view.setOnLongClickListener(new View.OnLongClickListener(){
//
//
//                /**
//                 * Long hold to share the article link.
//                 * @param v view param
//                 * @return def true
//                 */
//                @Override
//                public boolean onLongClick(View v) {
//                    Intent i = new Intent(Intent.ACTION_SEND);
//                    i.setType("text/plain");
//                    i.putExtra(Intent.EXTRA_SUBJECT, newsListA.get(getAdapterPosition()).getHeadline());
//                    i.putExtra(Intent.EXTRA_TEXT, newsListA.get(getAdapterPosition()).getLink());
//                    view.getContext().startActivity(Intent.createChooser(i, "Share article to..."));
//                    return true;
//                }
//            });
            this.context = context;

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select");
            MenuItem menuItem_share = menu.add(0, v.getId(), 0, "Share");
            MenuItem menuItem_browser = menu.add(0, v.getId(), 0, "Open in Browser");
            menuItem_share.setOnMenuItemClickListener(this);
            menuItem_browser.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getTitle().equals("Share")) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, newsListA.get(getAdapterPosition()).getHeadline());
                i.putExtra(Intent.EXTRA_TEXT, newsListA.get(getAdapterPosition()).getLink());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("tag", String.valueOf(context));
                context.startActivity(i);
            } else if (item.getTitle().equals("Open in Browser")) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(newsListA.get(getAdapterPosition()).getLink()));
                context.startActivity(i);
            }
            return true;
        }
    }


    public MyAdapter(List<news> myList, Typeface tfh, Typeface tfd, LinearLayoutManager layoutManager, RecyclerView rView) {
        newsListA = myList;
        this.tfh = tfh;
        this.tfd = tfd;


    }

    public MyAdapter() {

    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new myViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        if (position > 0) {
            holder.headline.setText(newsListA.get(position).getHeadline());
            holder.description.setText(newsListA.get(position).getDescription());
            holder.description.setTypeface(tfd);
            holder.headline.setTypeface(tfh);
        }

    }


    @Override
    public int getItemCount() {
        return newsListA.size();
    }

}
