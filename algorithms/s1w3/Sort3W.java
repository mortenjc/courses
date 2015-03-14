
public class Sort3W {
    
private static void sort(Comparable[] a, int lo, int hi) 
{
    if (hi <= lo) return;
    int lt = lo, gt = hi;
    Comparable v = a[lo];
    int i = lo;
    while (i <= gt)
    {
        //StdOut.print(lt + "  " + i + "  " + gt + "  ");
        //show(a);
        int cmp = a[i].compareTo(v);
        if      (cmp < 0) exch(a, lt++, i++);
        else if (cmp > 0) exch(a, i, gt--);
        else i++;    
    }
    
    //StdOut.print(lt + "  " + i + "  " + gt + "  ");
    //show(a);
    
    StdOut.println("");
    sort(a, lo, lt -1);
    sort(a, gt +1, hi);
}

private static void exch(Comparable[] a, int lo, int hi)
{
    Comparable tmp = a[lo];
    a[lo] = a[hi];
    a[hi] = tmp;
}

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]+ " ");
        }
        StdOut.println();
    }

     // Read strings from standard input, sort them, and print.
     public static void main(String[] args) {
        String[] a = StdIn.readStrings();
        Sort3W.sort(a, 0, a.length-1);
        show(a);
    }
}