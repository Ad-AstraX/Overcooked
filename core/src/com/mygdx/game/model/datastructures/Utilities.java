package com.mygdx.game.model.datastructures;

import com.mygdx.game.model.WorldObject;

/**
 * Methods that are / can be often reused by different classes
 */
public class Utilities {
    /**
     * Counts the amount of elements in a given stack
     * <p>
     * @param stack the stack whose elements will be counted
     * @return The amount of elements in the given stack
     * @param <ContentType> The type of the stack's content
     */
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

    /**
     * Copies a stack
     * <p>
     * @param stack the stack that is to be copied
     * @return a copy of the stack
     * @param <ContentType> The type of the stack's content
     */
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

    /**
     * Counts the amount of elements in a given queue
     * <p>
     * @param queue the queue whose elements will be counted
     * @return The amount of elements in the given queue
     * @param <ContentType> The type of the queue's content
     */
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

    /**
     * Copies a queue
     * <p>
     * @param queue the queue that is to be copied
     * @return a copy of the queue
     * @param <ContentType> The type of the queue's content
     */
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

    /**
     * Counts the amount of elements in a given list
     * <p>
     * @param list the list whose elements will be counted
     * @return The amount of elements in the given list
     * @param <ContentType> The type of the list's content
     */
    public static <ContentType> int countListElements(List<ContentType> list) {
        int count = 0;
        list.toFirst();
        while (list.hasAccess()) {
            count++;
            list.next();
        }
        return count;
    }

    /**
     * Applies quicksort on the y positions of an array filled with WorldObjects
     * Source: <a href="https://www.baeldung.com/java-quicksort"></a>
     * <p>
     * @param arr the array that is to be sorted
     * @param begin Where to start sorting
     * @param end Where to end sorting
     */
    public static void quickSort(WorldObject[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    /**
     * Helper method for <br>quicksort</br>. This is where the real sorting happens
     * Source: <a href="https://www.baeldung.com/java-quicksort"></a>
     * <p>
     * @param arr the array that is to be sorted
     * @param begin Where to start sorting
     * @param end Where to end sorting
     * @return //TODO
     */
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
