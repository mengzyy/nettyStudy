package com.mzy.bio;


/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-14 09:07
 **/
class LoopQueue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;


    public LoopQueue(int capacity) {
        /*
         * 这里的capacity加1的原因是，在循环队列中， 我们会浪费一个空间，这样能存元素的数量会少一个，
         * 所以这里我们基于传进来的容量+1，这样就可以储存期望的元素数量了
         */
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }


    public LoopQueue() {
        this(10);
    }

    //入队
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }


    //出队
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }


    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;

    }

    public int getCapacity() {
        // 减一是因为我们初始化的时候进行了+1操作
        return data.length - 1;
    }


    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is emtpy.");
        return data[front];
    }


    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("[");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}

public class test {

    public static void main(String[] args) {

        LoopQueue<String> loopQueue = new LoopQueue<>(1000);
        loopQueue.enqueue("hahaha");
        loopQueue.enqueue("xixixi");
        loopQueue.dequeue();


    }


}