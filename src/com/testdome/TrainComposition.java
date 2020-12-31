package com.testdome;

import java.util.ArrayList;
import java.util.List;

public class TrainComposition {
    private List<Integer> queue;

    TrainComposition() {
        queue = new ArrayList();
    }

    public void attachWagonFromLeft(int wagonId) {
        List<Integer> newQueue = new ArrayList<>();
        newQueue.add(wagonId);
        if(queue.size() > 0) newQueue.addAll(queue);
        queue = newQueue;
    }

    public void attachWagonFromRight(int wagonId) {
        queue.add(wagonId);
    }

    public int detachWagonFromLeft() {
        if(queue.isEmpty()) return 0;
        List<Integer> newQueue = new ArrayList<>();
        for (int i = 1; i < queue.size(); i++) {
            newQueue.add(queue.get(i));
        }
        int detached = queue.get(0);
        queue = newQueue;
        return detached;
    }

    public int detachWagonFromRight() {
        if(queue.isEmpty()) return 0;
        int detached = queue.get(queue.size()-1);
        queue = queue.subList(0, queue.size() - 1);
        return detached;
    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(1);
        train.attachWagonFromLeft(2);

        train.attachWagonFromRight(7);
        train.attachWagonFromRight(8);
    // 2, 1, 7, 8

        System.out.println(train.detachWagonFromRight()); // 8
        System.out.println(train.detachWagonFromLeft()); // 2
        System.out.println(train.detachWagonFromLeft()); // 1
        System.out.println(train.detachWagonFromRight()); // 7


    }
}
