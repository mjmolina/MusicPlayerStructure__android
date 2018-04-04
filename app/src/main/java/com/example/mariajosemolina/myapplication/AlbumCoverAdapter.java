package com.example.mariajosemolina.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.ImageView;


public class AlbumCoverAdapter extends ArrayAdapter<Album>  {
    private Context context;
    ArrayList<Album> albums;

    public AlbumCoverAdapter(Context context, int textViewResourceId, ArrayList<Album> albums) {
        super(context, textViewResourceId, albums);
        this.context = context;
        this.albums = albums;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cover, parent, false);
        View gridView;

        if (convertView == null) {

            gridView = new View(context);
            gridView = inflater.inflate(R.layout.cover, null);

            // set value into textview
            TextView albumName = (TextView) gridView.findViewById(R.id.grid_album_label);
            albumName.setText(albums.get(position).albumName);
            TextView artistName = (TextView) gridView.findViewById(R.id.grid_album_artist);
            artistName.setText(albums.get(position).artistName);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_album_cover);


            String cover_path = albums.get(position).albumCover;
            imageView.setImageResource(context.getResources().getIdentifier("drawable/" + cover_path, null, context.getPackageName()));

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
