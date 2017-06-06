package Ques;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Subset {
	
	public static void main (String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> input = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            input.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(input.dequeue());
        }
        

}}
