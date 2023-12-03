 /**
  * This class creates a Wave that has a left and right channel 
  * as arrays of soundwaves that have frequency, time, and amplitude –
  * to be used with a player to play sounds.
  * 
  * @author Damian Dobrowolski
  * @date 09.21.2023
  * */

 import javazoom.jl.player.StdPlayer;

 /**
  * Wave class with constant SAMPLERATE
  * and instance variables left and right channel arrays
  * */

 public class Wave {
   public final int SAMPLERATE = 44100;
   private double[] leftchan; // wave channels
   private double[] rightchan;

   /**
  * Constructor that creates and initializes left and right channel arrays
  * with formula: f(t) = amp * sin(2 * pi * freq * t / sr)
  * @param double Hz
  * @param double seconds
  * @param double amplitude
  * */

   public Wave (double Hz, double seconds, double amplitude){
    leftchan = new double[(int)(SAMPLERATE * seconds)];
    rightchan = new double[(int)(SAMPLERATE * seconds)];
    for (int i = 0; i < leftchan.length; i++){
      double t = i / (double)SAMPLERATE;
      leftchan[i] = amplitude * Math.sin(2 * Math.PI * Hz * t);
      rightchan[i] = amplitude * Math.sin(2 * Math.PI * Hz * t);
    }
  }

/**
  * Constructor that initializes arrays sent as parameters
  * @param double[] left
  * @param double[] right
  * */

  public Wave (double[] left, double[] right){
    leftchan = left;
    rightchan = right;
  }

  public void play(){
    StdPlayer.playWave(leftchan, rightchan);
  }

/**
 *@param Wave b 
 *@return new Wave that is the sum of the current object wave and Wave b
 * */


  public Wave plus(Wave b){
     double[] newrightchan = new double[rightchan.length];
     double[] newleftchan = new double[leftchan.length];
     for (int i = 0; i < leftchan.length; i++){
      newrightchan[i] = rightchan[i] + b.rightchan[i];
      newleftchan[i] = leftchan[i] + b.leftchan[i];
     }
     return new Wave(newleftchan, newrightchan);
  }

  public double[] getLeft(){
    return leftchan;
  }

  public double[] getRight(){
    return rightchan;
  }
}