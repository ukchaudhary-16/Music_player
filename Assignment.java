import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
class Greetings{
    LocalTime currenTime= LocalTime.now();
    int hour =currenTime.getHour();
    String greeting;
    {if(hour>=1 && hour<=12){
        greeting="Good Morning ";
    }else if(hour>12 && hour<=24){
        greeting=" Good Evening ";
    }
}
}

class Playlist{
    // public Playlist(){}//default constructor
    private punjSongs punjSongs; // Store the punjSongs instance
    private HindiSongs hindiSongs;

    // Constructor that accepts punjSongs
    public Playlist(punjSongs punjSongs, HindiSongs hindiSongs) {
        this.punjSongs = punjSongs;
        this.hindiSongs=hindiSongs;
    }

    public void displayPlaylists() {
       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the playlist name:");
        String playlistName = scanner.nextLine();
        if (playlistName.equalsIgnoreCase("Punjabi")) {
            System.out.println("Displaying Punjabi Songs:");
            for (Song song : punjSongs.getPunjabiSongs()) {
                System.out.println(song.toString());
            }
        } 

        else if(playlistName.equalsIgnoreCase("Hindi")){
            System.out.println("Displaying Hindi Songs:");
            for(Song song: hindiSongs.getHindiSongs()){
                System.out.println(song.toString());
            }
        }
        else {
            System.out.println("No playlist found for the given name.");
        }
    }
    public void playSong(Song song) {
        try {
            File file = new File(song.getFilepath());
            if (!file.exists()) {
                System.out.println("File not found: " + song.getFilepath());
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println("Playing " + song.getTitle() + " by " + song.getArtist());

            // Wait for the clip to finish playing
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing song: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
     



class Song{
    private String title; // Abstraction used 
    private String artist;
    private String filepath;
    public Song(String title, String artist, String filepath){
        this.title=title;
        this.filepath=filepath;
        this.artist=artist;
    }

    public String getTitle(){   // Encapsulation used as it helps to acces the private field
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public String getFilepath(){
        return filepath;
    }
    @Override // used to provide more info like title, artitst. In java it is used to return the string in certain matter which does not involve hashcode
    public String toString(){
        return title+" by "+artist;
    }
}

class punjSongs extends Song{
    private Song[] punjSongs;
    public punjSongs(String title, String artist, String filepath) {
        super(title, artist, filepath);
         this.punjSongs = new Song[]{
            new Song("Soflty", "Karan Aujla", "D:\\Song\\Punjabi1.wav"),
            new Song("Apa Fer Milange", "Savi Kehlon","D:\\Song\\Punjabi2.wav"),
        };
       
}
public Song[] getPunjabiSongs() {
    return punjSongs;           
}
}

class HindiSongs extends Song{
    private Song[] hindiSongs;
    public HindiSongs(String title, String artist, String filepath) {
        super(title, artist, filepath);
        this.hindiSongs = new Song[]{
            new Song("Khariyat", "Arijit Singh", "D:\\Song\\Hindi"),
            new Song("Dheere Dheere", "HoneySingh","D:\\Song\\Hindi"),
        };
    }
        public Song[] getHindiSongs(){
            return hindiSongs;
        }
       
}

class Assignment {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your name");
        String name= sc.nextLine();
        Greetings gre=new Greetings();
        System.out.println(gre.greeting+name);
        System.out.println("What's your mood today");
        System.out.println("Available Playlist : Punjabi , Hindi , English");
        punjSongs punj = new punjSongs("Soflty", "Karan Aujla", "D:\\Class_Java_Programs");
        HindiSongs hind =new HindiSongs("Khariyat", "Arijit Singh", "D:\\\\Song\\\\Hindi");
        // Pass punjSongs to Playlist
        Playlist playlist = new Playlist(punj,hind);
        playlist.displayPlaylists();
        System.out.println("Which song do you want to listen?");
        String songChoice =sc.nextLine();

        boolean songFound = false;
        for(Song song: punj.getPunjabiSongs()){
            if(song.getTitle().equalsIgnoreCase(songChoice)){
        }
    }
    for (Song song : punj.getPunjabiSongs()) {
        if (song.getTitle().equalsIgnoreCase(songChoice)) {
            playlist.playSong(song);
            break;
        }
    }
    for (Song song : hind.getHindiSongs()) {
        if (song.getTitle().equalsIgnoreCase(songChoice)) {
            playlist.playSong(song);
            break;
        }
    }
        
}
}
