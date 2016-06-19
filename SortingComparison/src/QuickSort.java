import java.util.*;
public class QuickSort {
	public static void main(String[] args) {
		int[] x = { 9, 2, 4, 7, 3, 7, 10 };
		System.out.println(Arrays.toString(x));
 
		int low = 0;
		int high = x.length - 1;
		int k=3;
//		quickSort(x, low, high);
//		quickSort2(x, low, high,k);
		quickSort_Iterative1(x);

		//insertionSort(x);
		System.out.println(Arrays.toString(x));
	}
	
    // partition a[left] to a[right], assumes left < right
    private static int partition(int[] a, int left, int right) {
        // DK:  added check if (left == right):
        if (left == right) return left;
        int i = left - 1;
        int j = right;
        while (true) {
            while (a[++i] < a[right])       // find item on left to swap
                ;                           // a[right] acts as sentinel
            while (a[right] < a[--j])       // find item on right to swap
                if (j == left) break;       // don't go out-of-bounds
            if (i >= j) break;              // check if pointers cross
            swap(a, i, j);                  // swap two elements into place
        }
        swap(a, i, right);                  // swap with partition element
        return i;
    }

	 
	   public static void quickSort_Iterative1(int[] a) {
	      boolean[] sorted = new boolean[a.length];
	      int i, j, sortedCount = 0;
	      while (sortedCount < a.length) {
	        for (i=0; i<a.length; i++)
	          if (!sorted[i]) {
	            for (j=i; (j<a.length-1) && (!sorted[j+1]); j++) ;
	            for ( ; i<=j; i++) {
	              sorted[i = partition(a,i,j)] = true;
	              sortedCount++;
	            }
	          }
	      }
	   }
	 
	   // version 2 with insertionSort for ranges <= k
	   public static void quickSort_Iterative2(int[] a, int k) {
	      int[] range = new int[a.length+1]; // if (range[i]<0) then skip[i] = |range[i]|
	      range[0] = a.length-1;
	      int i, j, sortedCount = 0;
	      while (sortedCount < a.length) {
	        for (i=0; i<a.length; i++)
	          if (range[i] >= i) {
	            j = range[i];
	            if (j-i < k) {
	              // inserion sort the elements from a[i] to a[j] inclusive
	              // and set all their ranges to -((j+1)-k)
	              for (int m=i; m<=j; m++) {
	                for (int n=m; n>i && a[n-1]>a[n]; n--)
	                  swap(a, n, n-1);
	                range[m] = -((j+1)-m);
	                sortedCount++;
	              }
	              i = j;
	            }
	            else {
	              for ( ; i<=j; i++) {
	                int p = partition(a,i,j);
	                sortedCount++;
	                if (p > i) range[i] = p-1;
	                if (p < j) range[p+1] = j;
	                range[i = p] = -1; // sorted
	              }
	            }
	          }
	          else {
	            // skip[i] += skip[i + skip[i]];
	            while ((j = range[i-range[i]]) < 0) range[i] += j;
	            i += -range[i]-1;
	          }
	      }
	   }

	 
	    public static void quickSort_Iterative3(int[] a) {
	        sort1(a, 0, a.length);
	      }

	      private static void sort1(int x[], int off, int len) {
	         // Choose a partition element, v
	        int m = off + (len >> 1);       // Small arrays, middle element
	        if (len > 7) {
	            int l = off;
	            int n = off + len - 1;
	            if (len > 40) {        // Big arrays, pseudomedian of 9
	          int s = len/8;
	          l = med3(x, l,     l+s, l+2*s);
	          m = med3(x, m-s,   m,   m+s);
	          n = med3(x, n-2*s, n-s, n);
	            }
	            m = med3(x, l, m, n); // Mid-size, med of 3
	        }
	        int v = x[m];

	        // Establish Invariant: v* (<v)* (>v)* v*
	        int a = off, b = a, c = off + len - 1, d = c;
	        while(true) {
	          while (b <= c && x[b] <= v) {
	            if (x[b] == v)
	                swap(x, a++, b);
	            b++;
	          }
	          while (c >= b && x[c] >= v) {
	            if (x[c] == v)
	                swap(x, c, d--);
	            c--;
	          }
	          if (b > c)
	            break;
	          swap(x, b++, c--);
	        }

	        // Swap partition elements back to middle
	        int s, n = off + len;
	        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	        if ((s = b-a) > 1)
	            sort1(x, off, s);
	        if ((s = d-c) > 1)
	            sort1(x, n-s, s);
	      }

	      private static void vecswap(int x[], int a, int b, int n) {
	        for (int i=0; i<n; i++, a++, b++)
	          swap(x, a, b);
	      }

	      private static int med3(int x[], int a, int b, int c) {
	        return (x[a] < x[b] ?
	          (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
	          (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
	      }



	  public static void swap(int[] a, int i, int j) {
	      int temp = a[i];
	      a[i] = a[j];
	      a[j] = temp;
	    }
	
	 public static void insertionSort(int array[]) {
//		 System.out.println("insert");
	        int n = array.length;
	        for (int j = 1; j < n; j++) {
	            int key = array[j];
	            int i = j-1;
	            while ( (i > -1) && ( array [i] > key ) ) {
	                array [i+1] = array [i];
	                i--;
	            }
	            array[i+1] = key;
	
	        }
	    }
	
}