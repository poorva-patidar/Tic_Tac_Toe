package com.poorva.tic_tac_toe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private Header header;
    private Board board;

    @Override
    public void start(Stage stage) {
        try{
            BorderPane root = new BorderPane();
            Scene main = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            fillLayout(root);
            stage.setScene(main);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method creates the initial layout for the game
     * @param root is the --BorderPane-- in which all the --elements-- are to inserted
     */
    private void fillLayout(BorderPane root) {
        // Fill the Header part of the game
        fillHeader(root);

        // Fill the Board area of the game
        fillBoard(root);
    }

    /**
     * Method to create and fill the text and button to the header part
     * @param root Root pane to add the header part in
     */
    private void fillHeader(BorderPane root) {
        header = new Header();

        // Add the eventHandler or the start Button
        header.setButtonOnAction(startGame());

        // Add it to the root Pane
        root.getChildren().add(header.getPane());
    }

    /**
     * This method returns the EventHandler which handles the New game button pressed event
     * @return EventHandler<ActionEvent> to set on the New game button
     */
    private EventHandler<ActionEvent> startGame(){
        return actionEvent -> {
            // Hide the New game button
            header.hideButton();

            // Change the text to show the player's turn
            header.changeText("Player 1's Turn");

            // Starting a new game
            board.newGame();
        };
    }

    private void fillBoard(BorderPane root) {
         board = new Board(header);
         root.getChildren().add(board.getPane());
    }

    public static void main(String[] args) {
        launch();
    }
}