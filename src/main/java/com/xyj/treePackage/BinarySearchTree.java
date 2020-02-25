package com.xyj.treePackage;

import javax.swing.text.AbstractDocument;
import javax.swing.tree.TreeNode;
import java.util.Comparator;

//二叉搜索树
//特点：  比根节点大的放右边    小的放左边
public class BinarySearchTree<E> {

    /*数据的规模  二叉搜索树里没有索引的概念
    * */
    private int size;

    //二叉树的根节点
    private Node<E> root;

    //比较器
    private Comparator comparator;

    public BinarySearchTree(Comparator comparetor){
            this.comparator =  comparetor;
    }

    public BinarySearchTree() {
    }

    //内部节点
    private static class Node<E>{

        //元素
        E element;

        //左子树
        Node<E> left;

        //右子树
        Node<E> right;

        //父节点
        Node<E> parent;

        public Node(E element,Node<E> parent) {
            this.parent = parent;
            this.element = element;
        }

        public Node() {
        }
    }

    //返回数据规模
    public int size(){
        return size;
    }

    //查看是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //清空树
    public void clear(){

    }

    //向树内添加元素
    public void add(E element){

        //判断元素不为空
        elementNotNull(element);

        //加入第一个元素的情况
        if(root == null){
         root = new Node<E>(element,null);
         size++;
         return;
        }

        //添加的不是第一个节点
        Node<E> eNode = root;
        int c = 0;
        Node<E> parent = new Node<E>();
        while (eNode!= null) {
            parent = eNode;
            c = compare(element, eNode.element);
            if(c >0){
                    eNode = eNode.right;
            }else if(c < 0 ){
                    eNode = eNode.left;
            }else{//相等的情况
                return;
            }
        }

        //找到父节点 看看要插入那个子树
        if(c > 0){
            parent.right = new Node<E>(element,parent);
        }else if(c < 0){
            parent.left = new Node<E>(element,parent);
        }
        size++;
    }

    //移除树内某个元素
    public void remove(E element){

    }

    //查看树内是否包含
    public boolean contains(E element){
        return true;
    }

    //判断不是空元素
    private void elementNotNull(E element){
        if(element == null){
            throw new IllegalArgumentException("element not be null");
        }
    }

    //比较元素大小的方法
    /*返回值0  代表 e1 = e2
    * 返回值>0  e1 > e2
    * 返回值<0  e1 < e2
    * */
    private int compare(E e1,E e2){
        if (comparator != null) {
            return comparator.compare(e1,e2);
        }else{
            return ((Comparable)e1).compareTo(e2);
        }
    }
    //获得树的层数
    public  static int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private static void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) {return;}
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.element);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) {return;}
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

        //element的tostring不能太长，要不打印的不能看
    public static void show(Node root) {
        if (root == null) {System.out.println("EMPTY!");}
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }
    @Override
    public String toString() {
        show(root);
        return "";
    }

    public void printlnTree(){
        show(root);
    }
}
