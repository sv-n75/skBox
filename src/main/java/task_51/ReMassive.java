package task_51;

import java.util.Arrays;

public class ReMassive {

    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, .где сидит фазан.";
        String[] word = text.replaceAll("\\p{Punct}", "").split("\\s+");
        System.out.println(Arrays.toString(word));

        int n = word.length;
        String temp;
        for (int i = 0; i < n / 2; i++) {
            temp = word[n - i - 1];
            word[n - i - 1] = word[i];
            word[i] = temp;
        }
        System.out.println(Arrays.toString(word));
    }
}
