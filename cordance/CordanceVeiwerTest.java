package cordance;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class CordanceVeiwerTest {
//
//	private ArrayList<String> wordIndex;
//	private HashMap<String, ArrayList<Integer>> wordCataloge;
//	private ArrayList<String> positionName;
//
//	private String searchWord = "hello";
//	private CordanceVeiwer tester;
//
//	@Test
//	public void test() {
//




//		positionName = new ArrayList<String>();
//		wordIndex = new ArrayList<>();//goodevening i am trying desperatly to
//		//test something in java by maticulously adding words to an arraylist
//		//so that i can test a cordance program i'm making
//		wordIndex.add("goodevening");//0
//		wordIndex.add("i");//1
//		wordIndex.add("am");//2
//		wordIndex.add("trying");//3
//		wordIndex.add("desperatly");//4
//		wordIndex.add("to");//5
//		wordIndex.add("test");//6
//		wordIndex.add("something");//7
//		wordIndex.add("in");//8
//		wordIndex.add("java");//9
//		wordIndex.add("by");//10
//		wordIndex.add("maticulously");//11
//		wordIndex.add("adding");//12
//		wordIndex.add("words");//13
//		wordIndex.add("to");//14
//		wordIndex.add("an");//15
//		wordIndex.add("arraylist");//16
//		wordIndex.add("so");//17
//		wordIndex.add("that");//18
//		wordIndex.add("i");//19
//		wordIndex.add("can");//20
//		wordIndex.add("test");//21
//		wordIndex.add("a");//22
//		wordIndex.add("cordance");//23
//		wordIndex.add("program");//24
//		wordIndex.add("i'm");//25
//		wordIndex.add("making.");//26
//
//		wordCataloge = new HashMap<>();
//		ArrayList<Integer> tmp;
//		tmp = new ArrayList<>();
//		tmp.add(new Integer(0));
//		tmp.add(new Integer(0));
//		wordCataloge.put("goodevening", tmp);
//		ArrayList<Integer> tmp1 = new ArrayList<Integer>();
//		tmp1.add(new Integer(0));
//		tmp1.add(new Integer(1));
//		tmp1.add(new Integer(19));
//		wordCataloge.put("i", tmp1);
//		ArrayList<Integer> tmp2 = new ArrayList<Integer>();
//		tmp2.add(new Integer(0));
//		tmp2.add(new Integer(2));
//		wordCataloge.put("am", tmp2);
//		ArrayList<Integer> tmp3 = new ArrayList<Integer>();
//		tmp3.add(new Integer(0));
//		tmp3.add(new Integer(3));
//		wordCataloge.put("trying", tmp3);
//		ArrayList<Integer> tmp4 = new ArrayList<Integer>();
//		tmp4.add(new Integer(0));
//		tmp4.add(new Integer(4));
//		wordCataloge.put("desperatly", tmp4);
//		ArrayList<Integer> tmp5 = new ArrayList<Integer>();
//		tmp5.add(new Integer(0));
//		tmp5.add(new Integer(5));
//		tmp5.add(new Integer(14));
//		wordCataloge.put("to", tmp5);
//		ArrayList<Integer> tmp6 = new ArrayList<Integer>();
//		tmp6.add(new Integer(0));
//		tmp6.add(new Integer(6));
//		tmp6.add(new Integer(21));
//		wordCataloge.put("test", tmp6);
//		ArrayList<Integer> tmp7 = new ArrayList<Integer>();
//		tmp7.add(new Integer(0));
//		tmp7.add(new Integer(7));
//		wordCataloge.put("something", tmp7);
//		ArrayList<Integer> tmp8 = new ArrayList<Integer>();
//		tmp8.add(new Integer(0));
//		tmp8.add(new Integer(8));
//		wordCataloge.put("in", tmp8);
//		ArrayList<Integer> tmp9 = new ArrayList<Integer>();
//		tmp9.add(new Integer(0));
//		tmp9.add(new Integer(9));
//		wordCataloge.put("java", tmp9);
//		ArrayList<Integer> tmp10 = new ArrayList<Integer>();
//		tmp10.add(new Integer(0));
//		tmp10.add(new Integer(10));
//		wordCataloge.put("by", tmp10);
//		ArrayList<Integer> tmp11 = new ArrayList<Integer>();
//		tmp11.add(new Integer(0));
//		tmp11.add(new Integer(11));
//		wordCataloge.put("maticulously", tmp11);
//		ArrayList<Integer> tmp12 = new ArrayList<Integer>();
//		tmp12.add(new Integer(0));
//		tmp12.add(new Integer(12));
//		wordCataloge.put("adding", tmp12);
//		ArrayList<Integer> tmp13 = new ArrayList<Integer>();
//		tmp13.add(new Integer(0));
//		tmp13.add(new Integer(13));
//		wordCataloge.put("word", tmp13);
//		ArrayList<Integer> tmp14 = new ArrayList<Integer>();
//		tmp14.add(new Integer(0));
//		tmp14.add(new Integer(15));
//		wordCataloge.put("an", tmp14);
//		ArrayList<Integer> tmp15 = new ArrayList<Integer>();
//		tmp15.add(new Integer(0));
//		tmp15.add(new Integer(16));
//		wordCataloge.put("arraylist", tmp15);
//		ArrayList<Integer> tmp16 = new ArrayList<Integer>();
//		tmp16.add(new Integer(0));
//		tmp16.add(new Integer(17));
//		wordCataloge.put("so", tmp16);
//		ArrayList<Integer> tmp17 = new ArrayList<Integer>();
//		tmp17.add(new Integer(0));
//		tmp17.add(new Integer(18));
//		wordCataloge.put("that", tmp17);
//		ArrayList<Integer> tmp18 = new ArrayList<Integer>();
//		tmp18.add(new Integer(0));
//		tmp18.add(new Integer(20));
//		wordCataloge.put("can", tmp18);
//		System.out.println(tmp18);
//		ArrayList<Integer> tmp19 = new ArrayList<Integer>();
//		tmp19.add(new Integer(0));
//		tmp19.add(new Integer(22));
//		wordCataloge.put("a", tmp19);
//		ArrayList<Integer> tmp20 = new ArrayList<Integer>();
//		tmp20.add(new Integer(0));
//		tmp20.add(new Integer(23));
//		wordCataloge.put("cordance", tmp20);
//		ArrayList<Integer> tmp21 = new ArrayList<Integer>();
//		tmp21.add(new Integer(0));
//		tmp21.add(new Integer(24));
//		wordCataloge.put("program", tmp21);
//		ArrayList<Integer> tmp22 = new ArrayList<Integer>();
//		tmp22.add(new Integer(0));
//		tmp22.add(new Integer(25));
//		wordCataloge.put("i'm", tmp22);
//		ArrayList<Integer> tmp23 = new ArrayList<Integer>();
//		tmp23.add(new Integer(0));
//		tmp23.add(new Integer(26));
//		wordCataloge.put("making", tmp23);
//
//
//
//
//		System.out.println(wordCataloge.get("program"));
//
//
//
////		//tester = new CordanceVeiwer();
////		searchWord = "can";
////
////
////		System.out.println(tester.getCordance(searchWord, 10));
////
////		assertEquals("Test for x", tester.getCordance(searchWord, 10), "by maticulously adding words to an arraylist so that i can test a cordance program i'm making. ");
//
//
				
//	}

}
