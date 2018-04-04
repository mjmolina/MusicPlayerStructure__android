## Music Structure App

This is the Music Structure App for Udacity Android basics challenge,
which is my fourth project.

The application, as the name states, is just the structure
of a Music Player since we have not learn how to play a real
music file.

### About the application

This app has different screens and views, because I tried to use
all of my previous knowledge of the past projects.

The *activities* structure is the following:

* The cover page contains three buttons that will provide the user
for different functionalities:
 * Songs: It will display all the songs that the system currently have.
 * Artists: It will display all the artists of all the songs.
 * Albums: It will show the list of all the albums that are available.

 * The **Songs** activity it only a list of TextView elements that
 contains the information related to the song. Each item is clickable
 and will open a new activity, the Player with the basic options
 that one can expect.

 * The **Artists** activity will display as a list all the artists,
 and also the items are clickable. With the selected artist, a new
 activity will appear that  include images of all the albums on
 the upper half of the screen, and at the bottom a list of the selected
 album.

 * The **Albums** activity will list the albums and following the same
 idea, the selected item will open a new activity that will contain
 the information of the album on the top, and the songs at the bottom
 of the screen.

In total the application has 5 activities/views:
1. The cover
2. The content list (for albums, artists and songs)
3. The artist view
4. The album view
5. The player (which is like a "song view")

All the views, but the cover, have a menu with buttons to
go directly to the views: Home, Artists, Albums and Songs.

### Specification

* Each album consist of an object of the custom class **Album** that has:
 * Album ID (int)
 * Artist name (String)
 * Album name (String)
 * Album cover (String)
 * Song list (ArrayList<String>)

* The information of all the music is saved in a ArrayList<Album>,
this is because I store the information of the whole album in an instance
of the class Album.

I called this structure **db** because in the future I hope to be able
to update the app to use a real database.

* Most of the items that I show on the screen are generated from
Java, because an application like this one needs to be dynamical,
and building a screen by hand with all the information of albums
and artists, will be too hard.

* Besides the usual Layouts and Views, I decided to use GridView
for displaying the albums covers, which was a little bit difficult
to implement, because one need to define an XML and a Java class
with information to properly display the elements inside the GridView.

* Most of the views are valid vertical and horizontally, but some
of them could use a little bit more of work, but since the amount
of things that I tried to implements was really difficult,
I decided that just "a functional horizontal view" was more than
enough for this project.

### Extra things that I learned

* GridView: It was really confusing and difficult to implement,
but after following many tutorials and videos, I managed to have mine
properly working.

* How activities work: In the past I only had Parent -> Child activities
applications, so it was easy to come back to the previous view,
but now, since I had many levels in the activity stack, I found
how to be able to "finish" the current activity, so the previous
one that was displayed will be shown.

* Starting activities from each view is not too complicated, and
I do it finishing the current activity and explicitly starting the
new desire activity.

* Variables in classes: I did not know that I could access the
database from different java classes, but since the variable is
public, all the java files in my package could access the information
of this variable.
