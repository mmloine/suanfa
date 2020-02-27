package com.xyj.Test;

import com.xyj.model.Person;
import com.xyj.treePackage.BinarySearchTree;
import com.xyj.treePackage.Comparetor;
import org.omg.CORBA.ARG_IN;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTreeTest{


    public static class PersonComparetor implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge()-o1.getAge();
        }
    }
    public static class PersonComparetor1 implements Comparator<Integer>{
        @Override
        public int compare(Integer e1, Integer e2) {
            return e1-e2;
        }
    }
    public static void main(String[] args) {
        test2();
        
    }
    public static void test(){
        BinarySearchTree<Integer> ii = new BinarySearchTree<>();
        for(int a = 0;a <10;a++){
            ii.add((int)(Math.random()*1000));
        }
        System.out.println(ii);
    }

    public static void test2(){
        Integer[] w = {7001,90,11,30,70,29,6,3,2134,12,1,24,123,12,354,123,23,25,20,26,353,356,400,2135,2137,7002,8000,8123,7583};
        BinarySearchTree<Integer> pp = new BinarySearchTree<>();
        for(Integer a:w){
            pp.add(a);
        }
        pp.printlnTree();

    }

    public static void testString(){
        StringBuffer aa = new StringBuffer();
        /*aa.append("  ");
        aa.append("a");*/
        System.out.println(aa);
        System.out.println(aa.length());

    }

    public static void test5(){
        System.out.println(Math.pow(2,-1));
    }
    public static void test6(){
        System.out.println(2%2);
    }
    public static void StringTest(){
        /*StringBuffer ss = new StringBuffer("asd");
        StringBuffer ss1 = new StringBuffer("zxc");
        ArrayList<StringBuffer> result = new ArrayList<>();
        result.add(ss);
        result.add(ss1);
        int length = result.get(0).length();//字符串长度
        int size = result.size();
        ArrayList<StringBuffer> result1 = new ArrayList<>();
        for(int a = 0;a<size;a++){
            StringBuffer stringBuffer = result.get(a);
        }*/
        StringBuffer asd = new StringBuffer("asd");
        StringBuffer asd1 = new StringBuffer("asd");
        System.out.println(asd.toString().equals(asd1.toString()));

    }

}
