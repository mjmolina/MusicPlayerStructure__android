package com.example.mariajosemolina.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.MenuItem;
import android.graphics.Typeface;

import java.util.ArrayList;

public class ArtistView extends AppCompatActivity {

    GridView gridView;
    LinearLayout linearLayout;

    ArrayList<String> albumNames = new ArrayList<>();
    ArrayList<String> coverNames = new ArrayList<>();
    String artistName;
    ArrayList<ArrayList<String>> albums = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Artist View");
        actionBar.setDisplayHomeAsUpEnabled(true);

        gridView = (GridView) findViewById(R.id.gridView);
        linearLayout = (LinearLayout) findViewById(R.id.songsListLayout);

        Bundle params = getIntent().getExtras();
        String artistName = params.getString("artistName");

        for (int i = 0; i < Start.db.size(); i++) {
            ArrayList<String> album = Start.db.get(i);
            if (artistName.equals(album.get(1))) {
                albums.add(album);
                albumNames.add(album.get(2));
                coverNames.add(album.get(3));
            }
        }

        final AlbumCoverAdapter adapter = new AlbumCoverAdapter(this,
                android.R.layout.simple_gallery_item, albumNames, coverNames, artistName);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String clickedAlbum = ((TextView) v.findViewById(R.id.grid_album_label)).getText().toString();
                displayAlbumSongs(albums, clickedAlbum);
            }
        });

        final TextView item = new TextView(ArtistView.this);
        item.setText("Select an Album");
        item.setGravity(Gravity.CENTER);
        item.setId(0);
        item.setTypeface(null, Typeface.ITALIC);
        item.setTextSize(20);
        linearLayout.addView(item);

    }

    private void displayAlbumSongs(ArrayList<ArrayList<String>> albums, String clickedAlbum) {
        for (int i = 0; i < albums.size();i++) {
            ArrayList<String> album = albums.get(i);

            if (clickedAlbum.equals(album.get(2))) {
                linearLayout.removeAllViews();
                for (int j = 4; j < album.size(); j++) {
                    final int albumId = Integer.parseInt(album.get(0));

                    final TextView item = new TextView(ArtistView.this);
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    llp.setMargins(50, 40, 0, 40);
                    final String songName = album.get(j);
                    item.setText((j-3)+". "+songName);
                    item.setId(i);
                    item.setTextSize(20);
                    item.setClickable(true);
                    item.setLayoutParams(llp);
                    item.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ArtistView.this, SimulationPlay.class);
                            intent.putExtra("songName", songName);
                            intent.putExtra("idAlbum", albumId);
                            startActivity(intent);

                        }
                    });

                    View horizontalLine = new View(this);
                    horizontalLine.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            5
                    ));
                    horizontalLine.setBackgroundColor(Color.parseColor("#B3B3B3"));

                    linearLayout.addView(item);
                    linearLayout.addView(horizontalLine);
                }
            }
        }
    }
}
