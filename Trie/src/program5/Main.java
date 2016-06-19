package program5;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] srg) throws IOException{
		File f = new File("lowercase.txt");
		Trie trie = new Trie();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s= br.readLine();
		while(s!=null){
//			System.out.println(s);
			s =s.replaceAll("-", "");
			trie.insert(s);
			s = br.readLine();
		}
//		System.out.println(trie.search("abhorrent"));
		
		// check peter.txt
		long t1 = System.currentTimeMillis();
		Scanner sc = new Scanner(new FileReader("peter.txt"));
		s= sc.next();
		int c=0;
		try{
		while(s!=null){
//			System.out.println("s= "+s);
			String s2 = "";
			for(char ch:s.toCharArray()){
				if(ch>='a' && ch<='z'){
					s2+=ch;
				}else if(ch>='a' && ch<='z'){
					s2+=Character.toLowerCase(ch);
				}
			}
//			System.out.println("s= "+s2);
			
			if(s2.length()>0 && !trie.search(s2)){
				//System.out.println(s2);
				c++;
			}
			s = sc.next();
		}}catch(Exception e){
//			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		long t = t2-t1;
		System.out.println("Using Trie total misspelt words in peter.txt is "+c+" time taken = "+t);
		c=0;
		
		t1 = System.currentTimeMillis();
		sc = new Scanner(new FileReader("huck.txt"));
		s= sc.next();
		try{
		while(s!=null){
//			System.out.println("s= "+s);
			String s2 = "";
			for(char ch:s.toCharArray()){
				if(ch>='a' && ch<='z'){
					s2+=ch;
				}else if(ch>='a' && ch<='z'){
					s2+=Character.toLowerCase(ch);
				}
			}
//			System.out.println("s= "+s2);
			
			if(s2.length()>0 && !trie.search(s2)){
				//System.out.println(s2);
				c++;
			}
			s = sc.next();
		}}catch(Exception e){
//			e.printStackTrace();
		}
		t2 = System.currentTimeMillis();
		t = t2-t1;
		System.out.println("Using Trie total misspelt words in huck.txt is "+c+" time taken = "+t);
		c=0;
		System.out.println();
		t1 = System.currentTimeMillis();
		testArrayList("peter.txt");
		t2 = System.currentTimeMillis();
		t = t2-t1;
		System.out.println("time taken by ArrayList for peter.txt = "+t);
		System.out.println();
		t1 = System.currentTimeMillis();
		testArrayList("huck.txt");
		t2 = System.currentTimeMillis();
		t = t2-t1;
		System.out.println("time taken by ArrayList for huck.txt = "+t);
		System.out.println();
		
		t1 = System.currentTimeMillis();
		testSet("peter.txt");
		t2 = System.currentTimeMillis();
		t = t2-t1;
		System.out.println("time taken by Set for peter.txt = "+t);
		System.out.println();
		
		t1 = System.currentTimeMillis();
		testSet("huck.txt");
		t2 = System.currentTimeMillis();
		t = t2-t1;
		System.out.println("time taken by Set for huck.txt = "+t);
		
	}
	public static  void testArrayList(String file) throws IOException{
		File f = new File("lowercase.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s= br.readLine();
		ArrayList<String> list = new ArrayList<>();
		while(s!=null){
//			System.out.println(s);
			s =s.replaceAll("-", "");
			list.add(s);
			s = br.readLine();
		}
//		System.out.println(trie.search("abhorrent"));
		
		// check peter.txt
		Scanner sc = new Scanner(new FileReader(file));
		s= sc.next();
		int c=0;
		try{
		while(s!=null){
//			System.out.println("s= "+s);
			String s2 = "";
			for(char ch:s.toCharArray()){
				if(ch>='a' && ch<='z'){
					s2+=ch;
				}else if(ch>='a' && ch<='z'){
					s2+=Character.toLowerCase(ch);
				}
			}
//			System.out.println("s= "+s2);
			
			if(s2.length()>0 && !list.contains(s2)){
				//System.out.println(s2);
				c++;
			}
			s = sc.next();
		}}catch(Exception e){
//			e.printStackTrace();
		}
		System.out.println("Using ArrayList total misspelt words in "+file+" is "+c);
		c=0;
		
	}
	
	public static void testSet(String file) throws IOException{
		File f = new File("lowercase.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s= br.readLine();
		Set<String> set = new HashSet();
		while(s!=null){
//			System.out.println(s);
			s =s.replaceAll("-", "");
			set.add(s);
			s = br.readLine();
		}
//		System.out.println(trie.search("abhorrent"));
		
		// check peter.txt
		Scanner sc = new Scanner(new FileReader(file));
		s= sc.next();
		int c=0;
		try{
		while(s!=null){
//			System.out.println("s= "+s);
			String s2 = "";
			for(char ch:s.toCharArray()){
				if(ch>='a' && ch<='z'){
					s2+=ch;
				}else if(ch>='a' && ch<='z'){
					s2+=Character.toLowerCase(ch);
				}
			}
//			System.out.println("s= "+s2);
			
			if(s2.length()>0 && !set.contains(s2)){
				//System.out.println(s2);
				c++;
			}
			s = sc.next();
		}}catch(Exception e){
//			e.printStackTrace();
		}
		System.out.println("Using Set total misspelt words in "+file+" is "+c);
		c=0;
	}
}
