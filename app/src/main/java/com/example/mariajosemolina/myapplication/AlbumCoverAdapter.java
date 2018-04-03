package com.example.mariajosemolina.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.ImageView;


public class AlbumCoverAdapter extends ArrayAdapter<String>  {
    private Context context;
    ArrayList<String> albumNames;
    ArrayList<String> coverNames;
    String artist;

    public AlbumCoverAdapter(Context context, int textViewResourceId, ArrayList<String> albumNames, ArrayList<String> coverNames, String artist) {
        super(context, textViewResourceId, albumNames);
        this.context = context;
        this.albumNames = albumNames;
        this.coverNames = coverNames;
        this.artist = artist;
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
            albumName.setText(albumNames.get(position));
            TextView artistName = (TextView) gridView.findViewById(R.id.grid_album_artist);
            artistName.setText(artist);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_album_cover);


            String cover_path = coverNames.get(position);
            imageView.setImageResource(context.getResources().getIdentifier("drawable/" + cover_path, null, context.getPackageName()));

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return albumNames.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
