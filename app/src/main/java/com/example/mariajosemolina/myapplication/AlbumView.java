package com.example.mariajosemolina.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AlbumView extends AppCompatActivity  {

    ImageView albumCoverImage;
    TextView albumNameText, artistNameText;
    LinearLayout songsLayout;

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
        setContentView(R.layout.album_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Album View");
        actionBar.setDisplayHomeAsUpEnabled(true);


        songsLayout = (LinearLayout) findViewById(R.id.songsLayout);
        albumCoverImage = (ImageView) findViewById(R.id.albumCoverImage);
        albumNameText = (TextView) findViewById(R.id.albumName);
        artistNameText = (TextView) findViewById(R.id.artistName);

        Bundle params = getIntent().getExtras();
        String albumName = params.getString("albumName");

        ArrayList<String> album = new ArrayList<>();
        for (int i = 0; i < Start.db.size(); i++) {
            album = Start.db.get(i);
            if (albumName.equals(album.get(2))) {
                break;
            }
        }

        final int idAlbum = Integer.parseInt(album.get(0));
        String imageName = album.get(3);
        String artistName = album.get(1);

        Context context = getApplicationContext();
        int id = context.getResources().getIdentifier("drawable/"+imageName,
                null, context.getPackageName());
        albumCoverImage.setImageResource(id);
        albumNameText.setText(albumName);
        artistNameText.setText(artistName);

        for (int i = 4; i < album.size();i++) {
            final TextView item = new TextView(AlbumView.this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llp.setMargins(50, 40, 0, 40);
            final String songName = album.get(i);
            item.setText((i-3)+". "+songName);
            item.setId(i);
            item.setTextSize(20);
            item.setClickable(true);
            item.setLayoutParams(llp);
            item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AlbumView.this, SimulationPlay.class);
                    intent.putExtra("songName", songName);
                    intent.putExtra("idAlbum", idAlbum);
                    startActivity(intent);

                }
            });

            View horizontalLine = new View(this);
            horizontalLine.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    5
            ));
            horizontalLine.setBackgroundColor(Color.parseColor("#B3B3B3"));

            songsLayout.addView(item);
            songsLayout.addView(horizontalLine);
        }

    }
}
