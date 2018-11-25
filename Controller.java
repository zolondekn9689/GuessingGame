package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.util.Random;

public class Controller {
    @FXML
    Button button;

    @FXML
    TextField userField;

    @FXML
    Label numTry;

    @FXML
    Label dataEntered;

    @FXML
    Label feedback;

    @FXML
    Slider slider;

    // Number input and random number.
    private int guess;

    //User input converted into only numbers.
    private String text = "";

    private GuessingGame guessingGame;
    protected int previous_difficulty = 1;
    protected int toughness;
    private int number;
    private int boundary_high;
    private int boundary_low;
    private int count_turns = 0;

    public void initialize() {
        toughness = 1;

        startNewGame();
        guessingGame.onClear();
    }

    public void onButtonClicked()
    {

        convertText();

        numTry.setText(guessingGame.setGuess(guess));
        count_turns++;


        if (guessingGame.isCorrect()) {
            userField.setEditable(false);
            feedback.setText(String.valueOf(count_turns));
        } else {
            if (number < guess) {

                System.out.println("Answer: " + number);
                if (boundary_high > guess) {


                    boundary_high = guess;
                }


            } else {

                if (boundary_low < guess) {
                    boundary_low = guess;
                    System.out.println(boundary_low + " has been assigned to " + guess);
                }


            }
        }


        //feedback.setText(Integer.toString(boundary_low) + " < " + " guess " + " < " + Integer.toString(boundary_high));

    }

    public void onDrag() {
        if (previous_difficulty != (int)Math.round(slider.getValue())) {
            startNewGame();

        }
        System.out.println("Item has been dragged: to " + (int) Math.round(slider.getValue()));
    }


    private void convertText() {
        try {

            String txt = userField.getText();
            text = "";

            System.out.println("Text: " + txt);

            for (int i = 0; i < txt.length(); i++) {
                if (Character.isDigit(txt.charAt(i))) {
                    text += (txt.charAt(i));
                }
            }

            guess = Integer.parseInt(txt);
            System.out.println("Guess: " + guess);


        } catch (Exception e) {
            numTry.setText(e.getMessage());
        }
    }



    public void onReset() {
        guess = 0;
        text = "";
        guessingGame.onClear();
        userField.setText("");
        userField.setEditable(true);
        boundary_low = 0;
        boundary_high = Integer.MAX_VALUE;
        feedback.setText(Integer.toString(boundary_low));

    }



    public void onKey(KeyEvent e) {
        if (e.getCode() == KeyCode.RIGHT) {
            toughness = (int)Math.round(slider.getValue());
            startNewGame();
        }

        if (e.getCode() == KeyCode.LEFT) {
            toughness = (int)Math.round(slider.getValue());
            startNewGame();
        }
    }


    public void startNewGame() {
        userField.setText("");
        userField.setEditable(true);
        guessingGame = new GuessingGame();
        previous_difficulty = toughness;
        count_turns = 0;
        guessingGame.createGame(toughness - 1);
        guessingGame.onClear();
        boundary_high = Integer.MAX_VALUE;
        boundary_low = 0;
        number = guessingGame.getAnswer();

    }





}
