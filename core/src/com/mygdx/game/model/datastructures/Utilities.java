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
}
