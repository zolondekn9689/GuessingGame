package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessingGame {

    private List<Integer> list;
    private List<String> feedback;
    private int guess;
    private int random_number = 0;
    private int max_size;
    private Random rd;
    private int[] levels = {10, 1000, 10000, 9999999, Integer.MAX_VALUE};
    GuessingGame()
    { }

    public void createGame(int level) {
        list = new ArrayList<>();
        feedback = new ArrayList<>();
        setLevel(level);
        System.out.println(random_number);

    }

    public int getNumber(int index) {
        return list.get(index);
    }

    public String getFeedBack(int index) {
        return feedback.get(index);
    }

    public String setGuess(int gues) {
        list.add(gues);
        guess = gues;
        feedback.add(getFeedBack());
        return getFeedBack();
    }

    public void onClear() {
        createGame(1);
        list = new ArrayList<>();
        feedback = new ArrayList<>();

    }

    public boolean isCorrect() {
        return guess == random_number;
    }

    private String getFeedBack() {
        if (guess == random_number) {
            return "You are correct!!!\n";
        }

        if (guess < random_number) {


            return "Your guess too small. \n";
        }



        if (guess > random_number) {
            return "Your guess is too big. \n";
        }
        return "";
    }

    public int getAnswer() {
        return random_number;
    }

    private void setLevel(int difficulty) {
        max_size = levels[difficulty];
        if (difficulty <= 5 && difficulty >= 1) {
            generateNum();
        }
        System.out.println("Answer: " + random_number);
    }

    private void generateNum() {
        rd = new Random();
        random_number = rd.nextInt(max_size);
    }

    public int getGuess() {
        return guess;
    }


}
