package com;

public class CircleSingleLinkedList<E> extends AbstracList1<E> {

    private Node first;

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public void add(int index, Object element) {
        if(index == 0){
            Node<E> newfirst = new Node<E>((E) element,first);
            Node last = (size == 0)?newfirst : node(size-1);
            last.next = newfirst;
            first = newfirst;
        }else {
            Node<E> eNode = node(index - 1);
            eNode.next = new Node<E>((E) element, eNode.next);
        }
        size++;
    }

    @Override
    public Object set(int index, Object element) {
        E element1 = node(index).element;
        node(index).element = (E) element;
        return element1;
    }

    @Override
    public E remove(int index) {
        rangChecked(index);
        E element = node(index).element;
        if(index == 0){
            if (size == 1){
                first = null;
            } else{
                Node<E> last = node(size -1);
                first = first.next;
                last.next = first;
            }
        }else {
            node(index - 1).next = node(index - 1).next.next;
        }
        size--;
        return element;
    }

    @Override
    public Object removeByelement(Object element) {
        int indexof = indexof(element);
        return remove(indexof);
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public int indexof(Object element) {
        if(element == null){
            for(int a = 0;a<size;a++){
                if(node(a).element==null){return a;}
            }
        }else {
            for (int a = 0; a < size; a++) {
                if(element.equals(node(a).element)){return a;}
            }
        }
        return ELEMENT_NOT_FIND;
    }
    //根据索引找到element
    private CircleSingleLinkedList.Node<E> node(int index){
        rangChecked(index);
        CircleSingleLinkedList.Node<E> node = first;
        for(int a = 0;a <index;a++){
            node = node.next;
        }
        return node;
    }
    //内部类
    private static class Node<E>{
        E element;
        CircleSingleLinkedList.Node<E> next;
        public Node(E element, CircleSingleLinkedList.Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        String s = "SingleLinkedList:"+"size="+size+"[";
        for(int a = 0;a<size;a++){
            s = s + node(a).element+",";
        }
        if(size ==0){
            return s+"]";
        }else{
            return s.substring(0, s.length() - 1) + "]";
        }
    }
}
