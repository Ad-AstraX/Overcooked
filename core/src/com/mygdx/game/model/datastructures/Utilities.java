package com.mygdx.game.model.datastructures;

import com.mygdx.game.model.WorldObject;

public class Utilities {
    public static <ContentType> int countStackElements(Stack<ContentType> stack) {
        int count = 0;
        Stack<ContentType> help = new Stack<>();

        while (!stack.isEmpty()) {
            help.push(stack.top());
            stack.pop();
            count++;
        }

        while (!help.isEmpty()) {
            stack.push(help.top());
            help.pop();
        }

        return count;
    }

    public static <ContentType> Stack<ContentType> copyStack(Stack<ContentType> stack) {
        Stack<ContentType> help = new Stack<>();
        Stack<ContentType> copy = new Stack<>();

        while (!stack.isEmpty()) {
            help.push(stack.top());
            stack.pop();
        }

        while (!help.isEmpty()) {
            stack.push(help.top());
            copy.push(help.top());
            help.pop();
        }

        return copy;
    }
    public static <ContentType> int countQueueElements(Queue<ContentType> queue) {
        int count = 0;
        Queue<ContentType> helpQueue = new Queue<>();
        while (!queue.isEmpty()) {
            helpQueue.enqueue(queue.front());
            queue.dequeue();
            count++;
        }
        while (!helpQueue.isEmpty()) {
            queue.enqueue(helpQueue.front());
            helpQueue.dequeue();
        }
        return count;
    }

    public static <ContentType> Queue<ContentType> copyQueue(Queue<ContentType> queue) {
        Queue<ContentType> help = new Queue<>();
        Queue<ContentType> copy = new Queue<>();

        while (!queue.isEmpty()) {
            copy.enqueue(queue.front());
            help.enqueue(queue.front());
            queue.dequeue();
        }
        // TODO POSSIBLE ISSUE HERE MUST TEST
        queue = help;

        return copy;
    }

    public static <ContentType> int countListElements(List<ContentType> list) {
        int count = 0;
        list.toFirst();
        while (list.hasAccess()) {
            count++;
            list.next();
        }
        return count;
    }


    // Sorting algorithm for WorldObjects. I need it, don't you dare delete
    public static void quickSort(WorldObject[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    private static int partition(WorldObject[] arr, int begin, int end) {
        float pivot = arr[end].getPosition().y;
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j].getPosition().y >= pivot) {
                i++;

                WorldObject swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        WorldObject swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}
