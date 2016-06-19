import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SortingDriver {
	public static void main(String[] arg) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		while(true){
		System.out.println("Do you want to continue?(y/n)");
		char ch = sc.nextLine().charAt(0);
		if(ch=='n' || ch=='N')
			break;
		System.out.println("Enter name of file:");
		String s = sc.nextLine();
		File f = new File(s);
		Scanner br = new Scanner(new FileReader(f));
		int n=0;
		if(s.contains("500")){
			n=500;
		}else if(s.contains("50")){
			n= 50;
		}else if(s.contains("20K")){
			n=20000;
		}else if(s.contains("2K")){
			n=2000;
		}else if(s.contains("1K")){
			n=1000;
		}else if(s.contains("10K")){
			n=10000;
		}else if(s.contains("5K")){
			n=5000;
		}else{
			n=200000;
		}
		int[] arr = new int[n];
		for(int i=0;i<n;i++){
			arr[i] = br.nextInt();
		}
		QuickSort qSort = new QuickSort();
		HeapSort hSort = new HeapSort();
		long start =0,end=0;
		double t1=0,t2=0,t3=0,t4=0,t5=0;
		
		for(int i=0;i<10;i++){
			int[] arr2 = Arrays.copyOf(arr, arr.length) ;
			start = System.currentTimeMillis();
			qSort.quickSort_Iterative1(arr2);
			end = System.currentTimeMillis();
			t1+= end - start;
			System.out.println(Arrays.toString(arr2));
			arr2 = Arrays.copyOf(arr, arr.length) ;
			
			start = end;
			start = System.currentTimeMillis();
			qSort.quickSort_Iterative2(arr2,50);
			end = System.currentTimeMillis();
			t5 += end - start;
			System.out.println(Arrays.toString(arr2));
			arr2 = Arrays.copyOf(arr, arr.length) ;
			
			start = end;
			start = System.currentTimeMillis();
			qSort.quickSort_Iterative2(arr2,100);
			end = System.currentTimeMillis();
			t2 += end - start;
			System.out.println(Arrays.toString(arr2));
			arr2 = Arrays.copyOf(arr, arr.length) ;

			start = end;
			start = System.currentTimeMillis();
			qSort.quickSort_Iterative3(arr2);
			end = System.currentTimeMillis();
			t3 += end - start;
			System.out.println(Arrays.toString(arr2));
			arr2 = Arrays.copyOf(arr, arr.length) ;
			
			start = System.currentTimeMillis();
			hSort.heapSort(arr2);
			end = System.currentTimeMillis();
			t4 += end - start;
			start = end;
			System.out.println(Arrays.toString(arr2));
			
		}
		t1/=10;t2/=10;t3/=10;t4/=10;t5/=10;
		System.out.println("Time takes by all algos:");
		System.out.println("Quick sort version 1(1st element as pivot): "+t1);
		System.out.println("Quick sort version 2(1st element as pivot, and use Insertion sort for k<100): "+t2);
		System.out.println("Quick sort version 2(1st element as pivot, and use Insertion sort for k<50): "+t5);
		System.out.println("Quick sort version 3(median of 3 as pivot): "+t3);
		System.out.println("Heap sort version 1: "+t4);
	}
	}
}
