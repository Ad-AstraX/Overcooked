package com.mygdx.game.model.datastructures;

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
}
