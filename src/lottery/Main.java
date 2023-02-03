package lottery;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static int[] getLotteryNumbers(int numCount, int minNum, int maxNum) {
        int[] lottery = new int[numCount];
        for (int i = 0; i < numCount; i++) {
            int n = minNum + ThreadLocalRandom.current().nextInt(maxNum);
            boolean duplicate = false;
            for (int j = i - 1; j >= 0; j--) {
                if (lottery[j] == n) {
                    duplicate = true;
                    break;
                }
            }
            if (! duplicate) {
                lottery[i] = n;
            } else {
                i--;
            }
        }
        return lottery;
    }

    public static int[] getUserNumbers(int numCount, int minNum, int maxNum) {
        int[] userNumbers = new int[numCount];
        Scanner input = new Scanner(System.in);
        System.out.printf("Введіть %d номерів між %d та %d через пробіл%n", numCount, minNum, maxNum);
        for (int i = 0; i < numCount; i++) {
            userNumbers[i] = input.nextInt();
            if (userNumbers[i] < minNum || userNumbers[i] > maxNum) {
                System.out.println("Некоректний номер");
                i--;
            }
        }
        return userNumbers;
    }

    public static int getWin(int[] lottery, int[] user) {
        int win = 0;
        for (int i = 0; i < lottery.length; i++) {
            if (lottery[i] == user[i]) {
                win++;
            }
        }
        return win;
    }

    public static void main(String[] args) {
        final int numbersCount = 7;
        final int lotteryCount = 9;

        int[] userNumbers = getUserNumbers(numbersCount, 0, lotteryCount);
        int[] lottery = getLotteryNumbers(numbersCount, 0, lotteryCount);

        System.out.print("Виграли номери: ");
        System.out.println(Arrays.toString(lottery));

        int win = getWin(lottery, userNumbers);

        System.out.printf("%d з %d номерів вгадано.%n", win, numbersCount);

        if (win == numbersCount) {
            System.out.println("Перемога!");
        } else {
            System.out.println("Хай щастить!");
        }
    }
}