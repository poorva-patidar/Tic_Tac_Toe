package com.poorva.tic_tac_toe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Board {

    private StackPane pane;
    private Header header;
    private Box[][] grid = new Box[3][3];
    private Line line;

    // To know players turn
    private char turnStatus = '1';

    // To indicate the end of the game
    private boolean isEnd = false;

    // Constructor
    public Board(Header header){
        this.header = header;
        // Initialise the pane, set its size and set its x-y position
        pane = new StackPane();
        pane.setMinSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        pane.setTranslateX(Constants.WINDOW_WIDTH / 2);
        pane.setTranslateY((Constants.BOARD_HEIGHT / 2) + Constants.HEADER_HEIGHT);

        // Method for adding all the boxes
        addBoxes();

        // Initializing a line and adding it to the pane
        line = new Line();
        pane.getChildren().add(line);

    }

    public void changeTurnStatus(){
        if(turnStatus == '1'){
            turnStatus = '2';
        } else {
            turnStatus = '1';
        }

        // Update in the header
        header.changeText("Player " + turnStatus + "'s Turn");
    }

    // Getter for current turnStatus
    public String getTurnStatus(){
        if(turnStatus == '1'){
            return "X";
        } else{
            return "O";
        }
    }

    // For starting a new fresh game
    public void newGame(){
        isEnd = false;
        turnStatus = '1';
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                 grid[r][c].setValue("");
            }
        }

        line.setVisible(false);
    }

    //  This method creates each box in the grid
    private void addBoxes() {
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                // Creating new boxes and adding them to the pane with correct measurements
                Box box = new Box();
                box.getPane().setTranslateX((c * 100) - 100);
                box.getPane().setTranslateY((r * 100) - 100);
                pane.getChildren().add(box.getPane());

                //Adding boxes to the grid array
                grid[r][c] = box;
            }
        }
    }

    // Getter for the pane only
    public StackPane getPane() {
        return pane;
    }

    // method for checking if anyone wins the game or not
    public void checkForVictory(){
          checkRows();
          checkColumns();
          checkDiagonalLeftToRight();
          checkDiagonalRightToLeft();
          checkForTie();
    }

    // checking for each row
    private void checkRows() {
        for(int r = 0; r < 3; r++){
            if(grid[r][0].getValue().equals(grid[r][1].getValue()) &&
                    grid[r][0].getValue().equals(grid[r][2].getValue()) &&
                    !grid[r][0].getValue().isEmpty()){
                String winner = grid[r][0].getValue();
                gameEnd(winner, new WinningBoxes(grid[r][0], grid[r][1], grid[r][2]));
                return;
            }
        }
    }

    // Checking for each column
    private void checkColumns() {
        if(!isEnd){
            for(int c = 0; c < 3; c++){
                if(grid[0][c].getValue().equals(grid[1][c].getValue()) &&
                        grid[0][c].getValue().equals(grid[2][c].getValue()) &&
                        !grid[0][c].getValue().isEmpty()){
                    String winner = grid[0][c].getValue();
                    gameEnd(winner, new WinningBoxes(grid[0][c], grid[1][c], grid[2][c]));
                    return;
                }
            }
        }
    }

    // Checking for the diagonal from left to the right
    private void checkDiagonalRightToLeft() {
        if (!isEnd) {
            if (grid[0][0].getValue().equals(grid[1][1].getValue()) &&
                    grid[0][0].getValue().equals(grid[2][2].getValue()) &&
                    !grid[0][0].getValue().isEmpty()) {
                String winner = grid[0][0].getValue();
                gameEnd(winner, new WinningBoxes(grid[0][0], grid[1][1], grid[2][2]));
                return;
            }
        }
    }

    // Checking for the diagonal from the right to the left
    private void checkDiagonalLeftToRight() {
        if(!isEnd){
            if(grid[0][2].getValue().equals(grid[1][1].getValue()) &&
                    grid[0][2].getValue().equals(grid[2][0].getValue()) &&
                    !grid[0][2].getValue().isEmpty()){
                String winner = grid[0][2].getValue();
                gameEnd(winner, new WinningBoxes(grid[0][2], grid[1][1], grid[2][0]));
                return;
            }
        }
    }

    // Checking if the game is completed and no one won
    private void checkForTie() {
        if(!isEnd){
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                   if(grid[r][c].getValue().isEmpty()){
                       return;
                   }
                }
            }

            isEnd = true;
            header.changeText("Game Tied!!!");
            header.showButton();
        }
    }


    // Method for ending the game
    private void gameEnd(String winner, WinningBoxes winningBoxes){
        isEnd = true;
        drawLine(winningBoxes);
        if(winner.equals("X")){
            header.changeText("Player " + 1 + " Wins!");
        } else{
            header.changeText("Player " + 2 + " Wins!");
        }
        header.showButton();
    }

    // Method to draw the line
    private void drawLine(WinningBoxes winningBoxes){
        line.setStartX(winningBoxes.start.getPane().getTranslateX());
        line.setStartY(winningBoxes.start.getPane().getTranslateY());
        line.setEndX(winningBoxes.end.getPane().getTranslateX());
        line.setEndY(winningBoxes.end.getPane().getTranslateY());
        line.setTranslateX(winningBoxes.middle.getPane().getTranslateX());
        line.setTranslateY(winningBoxes.middle.getPane().getTranslateY());
        line.setVisible(true);
    }

    // Classes for winning boxes
    private class WinningBoxes{
        Box start;
        Box middle;
        Box end;

        public WinningBoxes(Box start, Box middle, Box end){
            this.start = start;
            this.middle = middle;
            this.end = end;
        }
    }

    // Class for an instance of each box present on the board
    private class Box {

        private StackPane pane;
        private Label label;

        public Box(){
            pane = new StackPane();
            pane.setMinSize(100, 100);

            // For creating borderLines for each boxes
            Rectangle borderLine = new Rectangle();
            borderLine.setHeight(100);
            borderLine.setWidth(100);
            borderLine.setFill(Color.TRANSPARENT);
            borderLine.setStroke(Color.BLACK);
            pane.getChildren().add(borderLine);

            // A label for 'X' or 'O' or Blank for each Box
            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            // EventHandler for the label to change its text when user clicks a box
            pane.setOnMouseClicked(e -> {
               if(label.getText().isEmpty() && !isEnd){
                   label.setText(getTurnStatus());
                   changeTurnStatus();
                   checkForVictory();
               }
            });
        }

        // Getter for the pane
        public StackPane getPane() {
            return pane;
        }

        // Getter for the label to know its current state
        public String getValue(){ return label.getText(); }

        // Setter for the label to set a particular value for a box
        public void setValue(String value){
            label.setText(value);
        }

    }

}
