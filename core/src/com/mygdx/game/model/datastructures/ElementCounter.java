package com.mygdx.game.model.datastructures;

public class ElementCounter {

    /**
     * A method which counts the amount of elements in a stack
     * <p>
     * @param stack The stack which elements are to be counted
     * @return The number of elements in the given stack
     * @param <ContentType> A parameter needed as stack is a generic class
     */
    public static <ContentType> int countStackElements(Stack<ContentType> stack) {
        int count = 0;
        Stack<ContentType> helpStack = new Stack<>();
        while (!stack.isEmpty()) {
            helpStack.push(stack.top());
            stack.pop();
            count++;
        }
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.top());
            helpStack.pop();
        }
        return count;
    }

    /**
     * A method which counts the amount of elements in a queue
     * <p>
     * @param queue The queue which elements are to be counted
     * @return The number of elements in the given queue
     * @param <ContentType> A parameter needed as queue is a generic class
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
     * A method which counts the amount of elements in a list
     * <p>
     * @param list The list which elements are to be counted
     * @return The number of elements in the given list
     * @param <ContentType> A parameter needed as list is a generic class
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
}
