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
import android.widget.ImageButton;
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
    ImageButton menuHome, menuArtist, menuAlbum, menuSong;

    ArrayList<Album> albums = new ArrayList<>();

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
        menuHome = (ImageButton) findViewById(R.id.menuHome);
        menuArtist = (ImageButton) findViewById(R.id.menuArtist);
        menuAlbum = (ImageButton) findViewById(R.id.menuAlbum);
        menuSong = (ImageButton) findViewById(R.id.menuSong);

        Bundle params = getIntent().getExtras();
        String artistName = params.getString("artistName");

        for (int i = 0; i < Start.db.size(); i++) {
            Album album = Start.db.get(i);
            if (artistName.equals(album.artistName)) {
                albums.add(album);
            }
        }

        final AlbumCoverAdapter adapter = new AlbumCoverAdapter(this,
                android.R.layout.simple_gallery_item, albums);

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

        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ArtistView.this, Start.class);
                startActivity(intent);
            }
        });
        menuArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ArtistView.this, ContentList.class);
                intent.putExtra("isArtist", true);
                intent.putExtra("isSong", false);
                intent.putExtra("isAlbum", false);
                startActivity(intent);
            }
        });
        menuAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ArtistView.this, ContentList.class);
                intent.putExtra("isArtist", false);
                intent.putExtra("isSong", false);
                intent.putExtra("isAlbum", true);
                startActivity(intent);
            }
        });
        menuSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ArtistView.this, ContentList.class);
                intent.putExtra("isArtist", false);
                intent.putExtra("isSong", true);
                intent.putExtra("isAlbum", false);
                startActivity(intent);
            }
        });

    }

    private void displayAlbumSongs(ArrayList<Album> albums, String clickedAlbum) {
        for (int i = 0; i < albums.size();i++) {
            Album album = albums.get(i);

            if (clickedAlbum.equals(album.albumName)) {
                linearLayout.removeAllViews();
                for (int j = 0; j < album.songList.size(); j++) {
                    final int albumId = album.id;

                    final TextView item = new TextView(ArtistView.this);
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    llp.setMargins(50, 40, 0, 40);
                    final String songName = album.songList.get(j);
                    item.setText((j+1)+". "+songName);
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
