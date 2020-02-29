package com.xyj.treePackage;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xyj.utils.AbstartPrintTree;
import org.omg.CORBA.PolicyListHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

//二叉搜索树
//特点：  比根节点大的放右边    小的放左边
public class BinarySearchTree<E> extends AbstartPrintTree {

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

    //下面四个是实现打赢器接口的方法
    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object right(Object o) {
        Node o1 = (Node) o;
        return o1.right;
    }

    @Override
    public Object left(Object o) {
        Node o1 = (Node) o;
        return o1.left;
    }

    @Override
    public Object getNodeElement(Object o) {
        Node o1 = (Node) o;
        return o1.element;
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
        //是否是叶子节点
        public boolean isLeaf(){
            return left ==null && right == null;
        }
        //是否是度为2
        public boolean hasTwoChild(){
            return left !=null && right != null;
        }
        @Override
        public java.lang.String toString() {
            return element.toString();
        }
    }
    //内部控制器
     public static abstract class vistor<E>{
        boolean stop;
        protected abstract boolean vistor(E element);
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
            root = null;
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
                //为什么要覆盖，因为自定义类的比较方法是自定义的，可能相等，但值不相等
                eNode.element=element;
                return;
            }
        }

        //找到父节点 看看要插入那个子树
        if(c > 0){
            parent.right = new Node<E>(element,parent);
        }else{
            parent.left = new Node<E>(element,parent);
        }
        size++;
    }

    //移除树内某个元素
    public void remove(E element){
        Node<E> nodeByElement = getNodeByElement(element);
        if(nodeByElement == null){return;}
        remove(nodeByElement);
    }
    //内部移除节点
    private void remove(Node<E> node){
        //这里node不会是空的
        if (node.hasTwoChild()) {
            //找到后继节点
            Node<E> s = postNode(node);
            node.element = s.element;
            //删除后继节点
            node = s;
        }
        //来到这里必然是度为0或1的节点
        Node<E> replcement = node.left != null ? node.left : node.right;
        if(replcement != null){
            //在这里node是度为1的节点
            replcement.parent = node.parent;
            if(node.parent != null)
            {
                if(node.parent.left == node){
                    node.parent.left = replcement;
                }else{
                    node.parent.right = replcement;
                }
            }else{
                root = replcement;
            }
        }else{
            //在这里node是叶子节点
            if(node.parent == null){
                //这里node是根节点
                node = null;
            }else{
                //这里不是根节点的叶子节点
                if(node == node.parent.right){
                    node.parent.right = null;
                }else{
                    node.parent.left = null;
                }
            }
        }
        size--;
    }
    //通过element查找节点
    private Node<E> getNodeByElement(E element){
        Node<E> node = root;
        while(node != null){
            int compare = compare(element, node.element);
            if(compare == 0){return node;}
            if(compare < 0 ){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return null;
    }

    //查看树内是否包含
    public boolean contains(E element){
        return getNodeByElement(element) != null;
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
    @Override
    public String toString() {

        return "";
    }

    //前序遍历  自己  左  右
    private void preorderTraversal(Node<E> node ,vistor vis){
        if(node == null){return;}
        boolean vistor = vis.vistor(node.element);
        if(vistor == true){return;}
        preorderTraversal(node.left,vis);
        preorderTraversal(node.right,vis);
    }
    public void preorderTraversal(vistor vis){
        if(root == null||vis == null){return;}
       preorderTraversal(root,vis);
    }

    //中序遍历   左  自己 右
    public void  inorderTraversal(vistor<E> vis){
        if(vis == null || root ==null){return;}
        inorderTraversal(root,vis);
    }
    private  void  inorderTraversal(Node<E> node,vistor<E> vis){
        if(node == null){return;}
        inorderTraversal(node.left,vis);
        boolean vistor = vis.vistor(node.element);
        if(vistor == true){return;}
        inorderTraversal(node.right,vis);
    }
    //后续遍历  左  右 自己
    private void postororderTraversal(Node<E> node,vistor vis){
        if(node == null || vis.stop){return;}
        postororderTraversal(node.left,vis);
        postororderTraversal(node.right,vis);
        if(vis.stop == true){return;}
        vis.stop = vis.vistor(node.element);
    }
    public void postororderTraversal(vistor vis){
        if(root == null || vis == null){return;}
        postororderTraversal(root,vis);
    }
    //层序遍历
    public void levelOrderTranversal(vistor<E> vistor){
        if(root == null || vistor == null){return;}
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node<E> poll = queue.poll();
            /*System.out.print(poll.element+",");*/
            boolean vistor1 = vistor.vistor(poll.element);
            if(vistor1 == true){return;}
            if(poll.left!= null){
                queue.offer(poll.left);
            }
            if(poll.right != null){
                queue.offer(poll.right);
            }
        }
    }

    //获取树的高度
    /*public int height(){
       return height(root);
    }*/

    //获取某个节点的高度  用递归
    public int height(Node<E> node){
        if(node == null){return 0;}
        return 1+Math.max(height(node.left),height(node.right));
    }
    //迭代获取数的高度
    public int height(){
        Queue<Node<E>> queue = new LinkedList<>();
        int height = 0;
        int levelSize = 1;
        queue.offer(root);
        while(!queue.isEmpty()){
            Node<E> poll = queue.poll();
            levelSize--;
            if(poll.left!= null){
                queue.offer(poll.left);
            }
            if(poll.right != null){
                queue.offer(poll.right);
            }
            if(levelSize == 0){
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    public boolean isComplete(){
        if(root == null){return false;}
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while(!queue.isEmpty()){
            Node<E> poll = queue.poll();
            if(leaf && !poll.isLeaf()){
                return false;
            }
            if(poll.hasTwoChild()){
                queue.offer(poll.left);
                queue.offer(poll.right);
            }else if(poll.left == null && poll.right != null){return false;}
            else{leaf = true;
                if(poll.left != null){
                    queue.offer(poll.left);
                }
            }
        }
        return true;
    }

    public void fanzhuanTree(){
        if(root == null){return;}
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);
        while (!queue.isEmpty()){
            //每隔存在的节点都会被推出
            Node<E> poll = queue.poll();
            Node<E> left = poll.left;
            poll.left = poll.right;
            poll.right = left;
            if(poll.left != null){
                queue.offer(poll.left);
            }
            if(poll.right != null){
                queue.offer(poll.right);
            }
        }
    }
    //求前驱节点   就是中序遍历时的前一个节点
    public Node<E> perNode(Node<E> node){
        if(node == null){return null;}
        if(node.left != null){
            Node<E> left = node.left;
            while(left.right != null){
                left = left.right;
            }
            return left;
        }
        if( node.parent!= null){
            Node<E> eNode = new Node<>();
            eNode = node;
            while(eNode.parent != null){
                Node<E> p = eNode.parent;
                if(p.right == eNode){return p;}
                eNode = p;
            }
        }
        if(node.parent ==null){return null;}
        return null;
    }

    public void testpre(){
        Queue<Node<E>> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print("-"+perNode(poll)+"-");
            if(poll.left!= null){queue.offer(poll.left);}
            if(poll.right!= null){queue.offer(poll.right);}
        }
    }
    public void testpost(){
        Queue<Node<E>> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print("-"+postNode(poll)+"-");
            if(poll.left!= null){queue.offer(poll.left);}
            if(poll.right!= null){queue.offer(poll.right);}
        }
    }
    //求后驱节点   就是中序遍历时的后一个节点
    public Node<E> postNode(Node<E> node){
        if(node == null){return null;}
        if(node.right != null){
            Node<E> left = node.right;
            while(left.left!= null){
                left = left.left;
            }
            return left;
        }
        if( node.parent!= null){
            Node<E> eNode = new Node<>();
            eNode = node;
            while(eNode.parent != null){
                Node<E> p = eNode.parent;
                if(p.left == eNode){return p;}
                eNode = p;
            }
        }
        if( node.parent ==null){return null;}
        return null;
    }
}
