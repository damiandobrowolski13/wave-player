
/**********************************************************************************
 *
 *  Compilation:  javac -cp .:stdplayer.jar MP3Player.java
 *  Execution:    java  -cp .:stdplayer.jar MP3Player file.mp3
 *  Dependencies: Wave.java
 *
 *  Plays an mp3 file specified as a command line input.
 *
 *  Note:  under Windows, replace the : with a ; when specifying the classpath.
 *  Note:  stdplayer.jar must be located in the current directory.
 *
 **********************************************************************************/


import javazoom.jl.player.StdPlayer;

public class MP3Player {
    public static void main(String[] args) {
        String filename = args[0];
        StdPlayer.open(filename);
        while (!StdPlayer.isEmpty()) {
	        double[] left  = StdPlayer.getLeftChannel();
            double[] right = StdPlayer.getRightChannel();  
            Wave w = new Wave(left, right);
            EchoFilter echosong = new EchoFilter(300, w);
            echosong.get(w).play();
        }
        StdPlayer.close();
        System.exit(0);
    }
}
