import java.util.Comparator;

public class MergeSort
{
    private static Comparable [] aux;

    private static void merge(Comparable[] a, Comparable[] aux, int lo, 
                              int mid, int hi)
    {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
        
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        
        int i = lo, j = mid+1;
        for (int  k = lo; k <= hi; k++)
        {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
        StdOut.print("merge: ");
        show(a);
    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort (a, aux, lo, mid);
        sort (a, aux, mid+1, hi);
        if (!less(a[mid+1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }
    
    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }
    
    private static boolean less(Comparable v, Comparable w)
    {
        return (v.compareTo(w) < 0);
    }
    
    private static boolean isSorted(Comparable[] a, int l, int r) 
    {
        for (int i = l; i < r; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
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
        MergeSort.sort(a);
        show(a);
    }
}