package com.xyj.Test;

import com.xyj.model.Person;
import com.xyj.treePackage.BinarySearchTree;
import com.xyj.treePackage.Comparetor;

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
        System.out.println("|");
        System.out.println("|");
        
    }
    public static void test(){
        BinarySearchTree<Integer> ii = new BinarySearchTree<>();
        for(int a = 0;a <10;a++){
            ii.add((int)(Math.random()*1000));
        }
        System.out.println(ii);
    }

    public static void test2(){
        Integer[] w = {7, 4, 9, 2, 5, 8, 11, 3,12,1};
        BinarySearchTree<Person> pp = new BinarySearchTree<>();
        for(Integer a:w){
            pp.add(new Person(a,""));
        }
        pp.printlnTree();
    }

}
