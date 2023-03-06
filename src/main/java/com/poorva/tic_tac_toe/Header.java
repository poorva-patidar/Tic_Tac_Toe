package com.poorva.tic_tac_toe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Class for the Header part of the game
 */
public class Header {
    // First declare variables
    private StackPane pane;
    private Label text;
    private Button startButton;

    // Constructor to initialise the fields and add it to the pane
    public Header(){
        // Initialise the pane, set its size and set its x-y position
        pane = new StackPane();
        pane.setMinSize(Constants.WINDOW_WIDTH, Constants.HEADER_HEIGHT);
        pane.setTranslateX(Constants.WINDOW_WIDTH / 2);  // Middle of the screen horizontally
        pane.setTranslateY(Constants.HEADER_HEIGHT / 2); // Middle of the Header part vertically

        // Initialise the text, set its size, set its font, set its position and add it to the pane
        text = new Label("Welcome to Tic Tac Toe");
        text.setMinSize(Constants.WINDOW_WIDTH, Constants.HEADER_HEIGHT);
        text.setFont(Font.font(24));
        text.setAlignment(Pos.CENTER);
        text.setTranslateY(-20);
        pane.getChildren().add(text);

        // Initialise the button, set its size, set its position and add it to the pane
        startButton = new Button("New Game");
        startButton.setMinSize(135, 30);
        startButton.setTranslateY(20);
        pane.getChildren().add(startButton);
    }

    // Getter for the pane only
    public StackPane getPane() {
        return pane;
    }

    /**
     * This method updates the text to the given String
     * @param textMessage String which is to be set as the text
     */
    public void changeText(String textMessage){
        text.setText(textMessage);
    }

    /**
     * Makes the New game button visible to the user
     */
    public void showButton(){
        startButton.setVisible(true);
    }

    /**
     * Hides the New game button from the user
     */
    public void hideButton(){
        startButton.setVisible(false);
        // easy
    }

    /**
     * Method to set the EventHandler on the New game button
     */
    public void setButtonOnAction(EventHandler<ActionEvent> onAction){
        startButton.setOnAction(onAction);
    }
}
