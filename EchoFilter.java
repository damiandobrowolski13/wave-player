 /**
  * This class creates an echo filter by adding a delay of specified time to the wave
  * array and uses a method get() to take an incoming wave and return a new, modified wave.
  * 
  * instance variables delay and arrays delayleft, delayright, echoleft, echoright
  * 
  * @author Damian Dobrowolski
  * @partner Vlad
  * @date 09.22.2023
  * */

public class EchoFilter{
	private int delay;
	private double[] delayleft;
	private double[] delayright;
	private double[] echoleft;
	private double[] echoright;

	/**
	 * @param int del (delay)
	 * @param Wave w
	 * Constructor takes delay value and wave w and intializes arrays:
	 * 
	 * delayleft/delayright are last *value of delay* values of left/right channels
	 * 
	 * echoleft/echoright are left/right channels of w, but with *delay length* more values, 
	 * 	for the purpose of...
	 * 		appending delayleft/delayright correspondingly to echoleft/echoright,
	 * 		so that they will be filled with the normal channels of w plus the amount of echo
	 * 		(delay) added at the end, in the form of the last *value of delay* elements of
	 * 		w channels (these will repeat when the initial end is reached
	 * 		to create an echo effect)
	 * */

	public EchoFilter(int del, Wave w){
		delay = del;
		delayleft = new double[delay];
		delayright = new double[delay];
		for (int i = 0; i < delay; i++){
			delayleft[i] = w.getLeft()[w.getLeft().length - delay + i];
			delayright[i] = w.getRight()[w.getRight().length - delay + i];
			System.out.println(i);
		}
		echoleft = new double[w.getLeft().length + delay];
		echoright = new double[w.getRight().length + delay];
		for (int i = 0; i < w.getLeft().length; i++){
			echoleft[i] = w.getLeft()[i];
			echoright[i] = w.getRight()[i];
		}
		for (int i = 1; i <= delay; i++){
			echoleft[echoleft.length - i] = delayleft[delay - i];
			echoright[echoright.length - i] = delayright[delay - i];
		}
	}

	/**
	 * @param Wave w
	 * @return Wave echowave
	 * method that returns new wave echowave that is both channels of echo put together
	 * */

	public Wave get(Wave w){
		Wave echowave = new Wave(echoleft, echoright);
		return echowave;
	}
}