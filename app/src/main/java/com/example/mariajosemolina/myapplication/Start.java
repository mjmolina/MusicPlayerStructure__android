package com.example.mariajosemolina.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;


public class Start extends AppCompatActivity {

    public static ArrayList<ArrayList<String>> db;
    Button buttonArtists, buttonSongs, buttonAlbums, buttonExit;
    public static int ID_MULTIPLIER = 100000;


    // Initializing the data base of our artists/albums/songs
    private void setupDataBase() {
        db = new ArrayList<ArrayList<String>>();
        ArrayList<String> album;

        // Structure of each Album as a cover
        // Artist, Album Title, Album Cover, Song 1, Song 2, ... Song N

        // Starting populating the data structure with each Album

        // Txarango
        album = new ArrayList<>(Arrays.asList("1","Txarango", "Benvinguts al llarg viatge", "txarango_benvingutsalllargviatge",
                "Benvinguts","En caravana", "Amagada primavera", "Quan tot s'enlaira", "Volveremos",
                "Pren el carrer", "No deixis de caminar", "Un pam de nas", "Sueña como un niño",
                "Per art de màgia", "Sempre balla", "Arriba la nit", "Vola", "La dansa del vestit"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("2", "Txarango", "Som Riu", "txarango_somriu",
                "Esperança", "Músic de carrer", "La vuelta al mundo", "Compta amb mi", "El meu poble",
                "Alegre i encantada", "Som un riu", "Governant", "Batega", "Corazón viajero",
                "Camp de batalla", "Com dues gotes d'aigua", "Tant de bo",  "No t'adormis"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("3", "Txarango", "El Cor de la Terra", "txarango_elcordelaterra",
                "Obriu les portes", "Una lluna a l'aigua", "El tren del temps",
                "Som foc (feat. The Cat Empire)", "Mil ocells (feat. Jarabe de Palo)",
                "Meravellós regal (feat. Safari Children's Choir)", "Resiste y grita (feat. EKO Camp)",
                "Terra endins (feat. Manu Chao)", "Ulls de corall", "Et recordo", "Que tot et vagi bé",
                "T'espero",  "Somriurem",  "Quan calla la ciutat"));
        db.add(album);
        // Itaca Band
        album = new ArrayList<>(Arrays.asList("4","Itaca Band", "Temerario", "itacaband_temerario",
                "Temerario", "Camaleón", "180", "Torna'm", "Cerca del cielo", "La locura",
                "Siempre que tu quieras", "Cerca del suelo", "Trapos sucios", "Finestres",
                "Ojo por ojo", "Rebélate", "La llave"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("5", "Itaca Band", "Rema", "itacaband_rema",
                "Lineas al azar", "Cuando tu te vas", "Rema", "Para todos los niños", "Oye mama",
                "Ja fa temps", "Imagina", "Deixa de pensar", "Dime bonita", "Al rico coco",
                "Petjades", "De puntillas"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("6","Itaca Band", "Explosiva", "itacaband_explosiva",
                "Ahora y aquí", "A vivirlo", "La indomable", "L'endema", "Radicalmente optimista",
                "Rompe tus promesas", "La vida explosiva", "Los de abajo", "Un nou element",
                "Faltará", "Deja que se vayan", "Las leyes"));
        db.add(album);
        // Aspencat
        album = new ArrayList<>(Arrays.asList("7","Aspencat", "Tot és ara", "aspencat_totesara",
                "Música naix de la ràbia", "Trenca els miralls", "Vull brindar", "Sense por",
                "Mantindre el foc", "Fent equilibris", "Som moviment", "Trinxeres en la foscor",
                "Interludi", "Seguim en peu", "Alçar el vol", "Escriurem mil batalles",
                "S'atura el temps", "La història és nostra"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("8", "Aspencat", "Essència", "aspencat_essencia",
                "En els teus ulls", "Nit d'esperança", "La distància", "Serem un cicló",
                "Quan caminàvem", "Antimatèria", "Fam de Justícia", "Queden matinades",
                "Batega la ciutat", "El teu crit", "Outro"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("9", "Aspencat", "Naixen primaveres", "aspencat_naixenprimaveres",
                "Intro", "Naixen primaveres", "Llums, Càmera i acció", "L'herència", "Mirades",
                "Futur nuvolat", "Revolucionari", "L'últim segon", "Somni", "La lluna té dos cares",
                "SOS"));
        db.add(album);
        // Lu Rois
        album = new ArrayList<>(Arrays.asList("10", "Lu Rois", "Clarobscur", "lurois_clarobscur",
                "Doppelgänger", "Bosc endins", "La dona de la nit alta", "Paradisos artificials",
                "Silvestre", "L'hora de l'Atzur", "Ruda", "A contrallum",
                "Que plogui tant com vulgui", "Posidònia", "És per tu"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("11", "Lu Rois", "Cau de Lluna", "lurois_caudelluna",
                "Crepitar nocturn", "Deixa't dur", "Emergir del silenci", "Adéu reina meva"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("12", "Lu Rois", "Camí del Far", "lurois_camidelfar",
                "Fluïx", "La dansarina", "Amor innocent", "Viure d'imaginar",
                "Passejades nocturnes", "El somiador", "Solamente", "Vestida de pena",
                "Camí del far"));
        db.add(album);
        // Pascuala Ilabaca
        album = new ArrayList<>(Arrays.asList("13", "Pascuala Ilabaca", "Diablo rojo, diablo verde", "pascualailabaca_diablorojodiabloverde",
                "Diablo rojo, Diablo verde", "Cueca triste", "Ay mamita, mamita", "Mal día",
                "Señas para llegar a Mapu", "Cueca la sincera", "La escala", "Violeta y Frida",
                "Machi", "Los hielos", "Lamenta la canela", "Casi me voy!",
                "Diablo rojo, Diablo verde (carnaval)"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("14", "Pascuala Ilabaca", "Busco Paraiso", "pascualailabaca_buscoparaiso",
                "Busco paraíso", "Rezos en montegrande", "Es difícil", "En el tren a Kanyakumari",
                "Pecado original", "No es nuez", "Sueño de pelícano", "Carnaval de San Lorenzo",
                "Isla", "Canción de noche", "Pájaro-Niño", "La luna llena"));
        db.add(album);
        album = new ArrayList<>(Arrays.asList("15", "Pascuala Ilabaca", "Rey Loj", "pascualailabaca_reyloj",
                "Ya no esperes más", "La muerte en Quillagua", "El perdon", "Rey Loj",
                "Preguntas al rey", "Cueca la mariposa", "Santa Caravana", "Caminito Viejo",
                "Nubes para los niños", "Sueño con un rayo", "Yo nací en chuqui",
                "Hombre sube al árbol", "Extintos", "Sabatanasa"));
        db.add(album);

        // Check ID albums
        for (int i = 0; i < db.size();i++) {
            if (Integer.parseInt(db.get(i).get(0)) != i+1)
                System.out.println("ERROR: IDs are not incremental");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        setupDataBase();

        buttonArtists = (Button) findViewById(R.id.buttonArtists);
        buttonSongs = (Button) findViewById(R.id.buttonSongs);
        buttonAlbums = (Button) findViewById(R.id.buttonAlbums);
        buttonExit = (Button) findViewById(R.id.buttonExit);

        buttonArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start.this, ContentList.class);
                intent.putExtra("isArtist", true);
                intent.putExtra("isSong", false);
                intent.putExtra("isAlbum", false);
                startActivity(intent);

            }
        });

        buttonSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start.this, ContentList.class);
                intent.putExtra("isArtist", false);
                intent.putExtra("isSong", true);
                intent.putExtra("isAlbum", false);
                startActivity(intent);

            }
        });

        buttonAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start.this, ContentList.class);
                intent.putExtra("isArtist", false);
                intent.putExtra("isSong", false);
                intent.putExtra("isAlbum", true);
                startActivity(intent);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });
    }
}
