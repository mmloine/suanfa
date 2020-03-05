package com.xyj.treePackage;

import com.xyj.utils.AbstartPrintTree;
import com.xyj.utils.PrintlnTree;
import javafx.scene.Parent;

import javax.swing.tree.FixedHeightLayoutCache;
import java.util.Comparator;

public class AvlTree<E> extends BinarySearchTree<E>{
    public AvlTree(Comparator comparetor) {
        super(comparetor);
    }

    public AvlTree() {
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while((node = node.parent) != null){
            if(isBalance()){
                updateHeight(node);
            }else{
                rebalance(node);
                break;
            }
        }
    }

    private void updateHeight(Node<E> node){
        AvlNode<E> node1 = (AvlNode<E>) node;
        node1.updateHeight();
    }



    private void rebalance(Node<E> parent){

        Node<E> grand = parent.parent;
        Node<E> left = parent.left;
        Node<E> right = parent.right;
        Node<E> child;
        if(left == null){
             child = right;
        }else{
            child = left;
        }
        if(parent.isRightTree()&& left == null){
            rotaLeft(grand);
            return;
        }
        if(parent.isRightTree()&& left != null){
            rotaright(parent);
            rotaLeft(grand);
            return;
        }
        if(parent.isLeftTree()&& left == null){
            rotaLeft(parent);
            rotaright(grand);
            return;
        }
        if(parent.isLeftTree()&& left != null){
            rotaright(grand);
            return;
        }
    }
    //左旋转 ll
    private void rotaLeft(Node<E> node){
        AvlNode<E> right = (AvlNode<E>) node.right;
        AvlNode<E> parent = (AvlNode<E>) node.parent;
        int a;
        if(node.isLeftTree()){
            a = 1;
        }else{
            a = 0;
        }
        node.right = null;
        node.parent = right;
        right.left = node;
        if(parent == null){
            right.parent = null;
            root = right;
        }else{
            if(a == 1){
                right.parent = parent;
                parent.left = right;
            }else{
                right.parent=parent;
                parent.right = right;
            }
        }
    }
    //右旋转 rr
    private void rotaright(Node<E> node){
        AvlNode<E> left = (AvlNode<E>) node.left;
        AvlNode<E> parent = (AvlNode<E>) node.parent;
        int a;
        if(node.isLeftTree()){
            a = 1;
        }else{
            a = 0;
        }
        node.left = null;
        node.parent = left;
        left.right = node;
        if(parent == null){
            left.parent = null;
            root = left;
        }else{
            if(a == 1){
                left.parent = parent;
                parent.left = left;
            }else{
                left.parent=parent;
                parent.right = left;
            }
        }
    }
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AvlNode<E>(element, (AvlNode<E>) parent);
    }

    @Override
    protected Node<E> createNode() {
        return new AvlNode<E>();
    }

    private static class AvlNode<E> extends Node<E> {
        //节点的高度
        int height =1;
        public AvlNode(){}
        public AvlNode(E eleMent,AvlNode<E> node){
            this.element = eleMent;
            this.parent = node;
        }
        //获得平衡因子
        public int balanceFactor(){
            int leftHeight = left==null ? 0 :((AvlNode)left).height;
            int rightHeight = right==null ? 0 :((AvlNode)right).height;
            return leftHeight-rightHeight;
        }
        public void updateHeight(){
            int leftHeight = left==null ? 0 :((AvlNode)left).height;
            int rightHeight = right==null ? 0 :((AvlNode)right).height;
            height = Math.max(leftHeight,rightHeight);
        }
        public Node<E> tallerChild(){
            int leftHeight = left==null ? 0 :((AvlNode)left).height;
            int rightHeight = right==null ? 0 :((AvlNode)right).height;
            if(leftHeight>rightHeight){return left;}
            if(leftHeight<rightHeight){return right;}
            return isLeftTree()?left:right;
        }
    }

    public boolean isBalance(Node<E> node){
        int left;
        int right;
        Node<E> left1 = node.left;
        Node<E> right1 = node.right;
        if(left1 == null){
            left = 0;
        }else{
            left = height(left1);
        }
        if(right1 == null){
            right = 0;
        }else{
            right = height(root.right);
        }

        int result = Math.abs(left-right);
        if(result > 1){return false;}else{return true;}
    }

    public boolean isBalance(){
        int left;
        int right;
        Node<E> left1 = root.left;
        Node<E> right1 = root.right;
        if(left1 == null){
            left = 0;
        }else{
            left = height(left1);
        }
        if(right1 == null){
            right = 0;
        }else{
            right = height(root.right);
        }

        int result = Math.abs(left-right);
        if(result > 1){return false;}else{return true;}
    }
}
