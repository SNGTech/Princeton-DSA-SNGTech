/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String survivor = "";
        while (!StdIn.isEmpty()) {
            String in = StdIn.readString();
            boolean isChamp = StdRandom.bernoulli((double) 1 / i);
            if (isChamp) survivor = in;
            i++;
        }
        StdOut.println(survivor);
    }
}
