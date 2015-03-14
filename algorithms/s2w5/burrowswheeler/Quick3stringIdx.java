/***********************************************************************************
 *  WARNING: This program assumes that the <tt>substring()</tt> method takes
 *  constant time and space. Beginning with Oracle / OpenJDK Java 7, Update 6,
 *  the substring method takes linear time and space in the size of the
 *  extracted substring. Do NOT use this code with such versions.
 *
 *  To fix: replace less() with a version that uses charAt().
 *
 ***********************************************************************************/

public class Quick3stringIdx {
    private static final int CUTOFF =  15;   // cutoff to insertion sort
    private static int N;
    private static String s;

    // sort the array a[] of strings -done 
    public static void sort(int [] ia, String str) {
        // StdRandom.shuffle(a);
        N = str.length()/2;
        s = str;
        sort(ia, 0, ia.length-1, 0);
        assert isSorted(ia);
    }

    // return the dth character of s, -1 if d = length of s - done
    private static int charAt(String str, int d) { 
        assert d >= 0 && d <= str.length();
        if (d == str.length()) return -1;
        return str.charAt(d);
    }


    // 3-way string quicksort a[lo..hi] starting at dth character
    private static void sort(int [] ia, int lo, int hi, int d) { 

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(ia, lo, hi, d);
            return;
        }
        
        int lt = lo, gt = hi;
        int v = charAt(s.substring(ia[lo], ia[lo]+N), d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(s.substring(ia[i], ia[i]+N), d);
            if      (t < v) exch(ia, lt++, i++);
            else if (t > v) exch(ia, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(ia, lo, lt-1, d);
        if (v >= 0) sort(ia, lt, gt, d+1);
        sort(ia, gt+1, hi, d);
    }

    
    // sort from a[lo] to a[hi], starting at the dth character - done
    private static void insertion(int [] ia, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(s.substring(ia[j], ia[j]+N), s.substring(ia[j-1], ia[j-1]+N), d); j--)
                exch(ia, j, j-1);
    }

    // exchange a[i] and a[j] - done
    private static void exch(int [] ia, int i, int j) {
        int temp = ia[i];
        ia[i] = ia[j];
        ia[j] = temp;
    }

    // is v less than w, starting at character d - done
    private static boolean less(String v, String w, int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        return v.substring(d).compareTo(w.substring(d)) < 0; 
    }


    // is the array sorted - done
    private static boolean isSorted(int [] ia) {
        for (int i = 1; i < ia.length; i++)
            if (s.substring(ia[i], ia[i]+N).compareTo(s.substring(ia[i-1], ia[i-1]+N)) < 0) return false;
        return true;
    }


    public static void main(String[] args) {

        // read in the strings from standard input
        String tmp = "ABRACADABRA!";
        int M = tmp.length();
        String a = tmp + tmp;
        int [] ia = new int [M];
        for (int i = 0; i < M; i++)
          ia[i] = i;
        for (int i = 0; i < M; i++)
          System.out.println(a.substring(ia[i], ia[i]+M));

        // sort the strings
        sort(ia, a);

        System.out.println("-----------------------");
        // print the results
        for (int i = 0; i < M; i++)
            StdOut.println(a.substring(ia[i], ia[i]+M));
    }
}
