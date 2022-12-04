package com.example.snakeladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class gamePageController {
    @FXML
    Text number;
    @FXML
    Text turnname;
    @FXML
    ImageView player1;
    @FXML
    ImageView player2;

    int turn = 1;

    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeLadderCoordinateChanges;

    @FXML
    public void rolldice(MouseEvent event) throws IOException{
        getsnakeLadderCoordinateChanges();
        Random random = new Random();
        int randomnumber = random.nextInt(6) + 1;
        number.setText("" + randomnumber);
        if(turn == 1){
            double moveX = player1.getTranslateX(); //gets relate position that's why 500 not 800 in below if condition
            double moveY = player1.getTranslateY(); //aforesaid thing is about getTranslate function

           Pair<Double,Double> movementCoordinates = movement(moveX,moveY,randomnumber);

            player1.setTranslateX(movementCoordinates.getKey());
            player1.setTranslateY(movementCoordinates.getValue());

           if(snakeLadderCoordinateChanges.containsKey(movementCoordinates)){
               player1.setTranslateX(snakeLadderCoordinateChanges.get(movementCoordinates).getKey());
               player1.setTranslateY(snakeLadderCoordinateChanges.get(movementCoordinates).getValue());
           }
           checkWin(player1.getTranslateX(),player1.getTranslateY());
        }

        else{
            double moveX = player2.getTranslateX(); //gets relate position that's why 500 not 800 in below if condition
            double moveY = player2.getTranslateY(); //aforesaid thing is about getTranslate function

            Pair<Double,Double> movementCoordinates = movement(moveX,moveY,randomnumber);

            player2.setTranslateX(movementCoordinates.getKey());
            player2.setTranslateY(movementCoordinates.getValue());

            if(snakeLadderCoordinateChanges.containsKey(movementCoordinates)){
                player2.setTranslateX(snakeLadderCoordinateChanges.get(movementCoordinates).getKey());
                player2.setTranslateY(snakeLadderCoordinateChanges.get(movementCoordinates).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }

        if(randomnumber !=6) {

            if (turn == 1) {
                turnname.setText("Player 2 Turn");
                turn = 2;
            } else {
                turnname.setText("Player 1 Turn");
                turn = 1;
            }
        }
    }
    Pair<Double,Double> movement(double moveX, double moveY,int randomnumber){

        double X = moveX;
        double Y = moveY;
        if(moveY%100==0)
        {
            moveX+= randomnumber*50;
            if(moveX>500)
            {
                moveX = 500*2 - moveX +50;
                moveY-=50;
            }
        }
        else{
            moveX-= randomnumber*50;
            if(moveX<50)
            {
                if(moveY == -450){
                    return new Pair<>(X,Y);
                }
                moveX = -1*(moveX - 50);
                moveY-=50;
            }
        }
        return new Pair<>(moveX,moveY);
    }
    void getsnakeLadderCoordinateChanges(){
        snakeLadderCoordinateChanges = new HashMap<>();
        this.snakeLadderCoordinateChanges.put(new Pair(50.0, 0.0), new Pair(150.0, -150.0));
        this.snakeLadderCoordinateChanges.put(new Pair(50.0, 0.0), new Pair(150.0, -150.0));
        this.snakeLadderCoordinateChanges.put(new Pair(200.0, 0.0), new Pair(350.0, -50.0));
        this.snakeLadderCoordinateChanges.put(new Pair(200.0, -50.0), new Pair(350.0, 0.0));
        this.snakeLadderCoordinateChanges.put(new Pair(450.0, 0.0), new Pair(500.0, -150.0));
        this.snakeLadderCoordinateChanges.put(new Pair(50.0, -100.0), new Pair(100.0, -200.0));
        this.snakeLadderCoordinateChanges.put(new Pair(400.0, -100.0), new Pair(200.0, -400.0));
        this.snakeLadderCoordinateChanges.put(new Pair(100.0, -300.0), new Pair(100.0, -50.0));
        this.snakeLadderCoordinateChanges.put(new Pair(50.0, 0.0), new Pair(150.0, -150.0));
        this.snakeLadderCoordinateChanges.put(new Pair(500.0, -250.0), new Pair(350.0, -300.0));
        this.snakeLadderCoordinateChanges.put(new Pair(500.0, -350.0), new Pair(500.0, -450.0));
        this.snakeLadderCoordinateChanges.put(new Pair(50.0, -350.0), new Pair(50.0, -450.0));
        this.snakeLadderCoordinateChanges.put(new Pair(200.0, -300.0), new Pair(50.0, -250.0));
        this.snakeLadderCoordinateChanges.put(new Pair(350.0, -250.0), new Pair(350.0, -150.0));
        this.snakeLadderCoordinateChanges.put(new Pair(150.0, -450.0), new Pair(100.0, -350.0));
        this.snakeLadderCoordinateChanges.put(new Pair(300.0, -450.0), new Pair(300.0, -350.0));
        this.snakeLadderCoordinateChanges.put(new Pair(400.0, -450.0), new Pair(400.0, -350.0));
        this.snakeLadderCoordinateChanges.put(new Pair(350.0, -400.0), new Pair(200.0, -100.0));

    }
    void checkWin(Double X , Double Y) throws IOException{
        if(X == 50.0 && Y == -450){
            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setHeaderText("Result");
            if(turn == 1){
                resultAlert.setContentText("Player 1 is winner");
                resultAlert.showAndWait();

            }
            else{
                resultAlert.setContentText("Player 2 is winner");
                resultAlert.showAndWait();
            }
            startGame start = new startGame();
            HelloApplication.root.getChildren().setAll(start.root);
        }
    }
}
