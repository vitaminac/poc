package algs4.fundamentals.stacks;

import core.DoublyLinkedList;
import core.Queue;

import java.util.Scanner;

public class WebEx11StreamingAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        Queue<Integer> queue = new DoublyLinkedList<>();
        while (scanner.hasNext()) {
            queue.enqueue(scanner.nextInt());
            if (queue.size() > k) {
                queue.dequeue();
            }
        }

        for (int i : queue) {
            System.out.println(i);
        }
    }
}