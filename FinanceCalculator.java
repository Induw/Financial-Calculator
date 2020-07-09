package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class FinanceCalculator extends Application {
    Scene scene1, scene2,scene3,scene4;
    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("Financial Calculator");

        //to add an HBox for the buttons on top
        HBox top = new HBox();
        Button b1 = new Button("Compound Savings");
        Button b2 = new Button("Compound Savings w/Payment");
        Button b3 = new Button("Mortgage");
        Button b4 = new Button("Loan");
        Button b5 = new Button("How To Use");
        top.setSpacing(60);
        top.setAlignment(Pos.CENTER);
        top.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY,Insets.EMPTY)));
        top.getChildren().addAll(b1,b2,b3,b4,b5);
        top.setPadding(new Insets(20,40,20,40));



        //adding a Pane to hold the Labels and Textfields related to user input in Simple Savings
        Pane inputSavingsOne = new Pane();

        Label lblheading1 = new Label("Compound Savings");
        lblheading1.setLayoutX(180);
        lblheading1.setLayoutY(40);

        Label lblStartPrincipal = new Label("Start Principal");
        lblStartPrincipal.setLayoutX(30);
        lblStartPrincipal.setLayoutY(110);

        Label lblPeriods = new Label("Periods(In Years)");
        lblPeriods.setLayoutX(30);
        lblPeriods.setLayoutY(160);

        Label lblInterest = new Label("I/Y(Interest)");
        lblInterest.setLayoutX(30);
        lblInterest.setLayoutY(210);

        Label lblFirstVal= new Label("Future Value");
        lblFirstVal.setLayoutX(30);
        lblFirstVal.setLayoutY(260);

        TextField tfStartPrincipal = new TextField();
        tfStartPrincipal.setPrefWidth(300);
        tfStartPrincipal.setLayoutY(105);
        tfStartPrincipal.setLayoutX(170);

        TextField tfPeriods = new TextField();
        tfPeriods.setPrefWidth(300);
        tfPeriods.setLayoutY(155);
        tfPeriods.setLayoutX(170);

        TextField tfInterest = new TextField();
        tfInterest.setPrefWidth(300);
        tfInterest.setLayoutY(205);
        tfInterest.setLayoutX(170);

        TextField tfFirstVal = new TextField();
        tfFirstVal.setPrefWidth(300);
        tfFirstVal.setLayoutY(255);
        tfFirstVal.setLayoutX(170);




        inputSavingsOne.getChildren().addAll(lblheading1,lblStartPrincipal,lblPeriods,lblInterest,lblFirstVal,tfStartPrincipal,tfPeriods,tfInterest,tfFirstVal);
        inputSavingsOne.setLayoutX(40);
        inputSavingsOne.setLayoutY(130);
        inputSavingsOne.setPrefSize(500,400);
        inputSavingsOne.setPadding(new Insets(20,40,20,40));
        inputSavingsOne.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));

        AnchorPane simplesavings = new AnchorPane();
        simplesavings.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        simplesavings.getChildren().addAll(top,inputSavingsOne,addCustKeyboard());
        scene1= new Scene(simplesavings, 925, 660);


        mainStage.setScene(scene1);
        mainStage.show();
    }
    //  Custom Keyboard
    public GridPane addCustKeyboard(){
        GridPane keyboard=new GridPane();
        //  Custom Keyboard Buttons
        Button num0= new Button("0");
        Button num1= new Button("1");
        Button num2= new Button("2");
        Button num3= new Button("3");
        Button num4= new Button("4");
        Button num5= new Button("5");
        Button num6= new Button("6");
        Button num7= new Button("7");
        Button num8= new Button("8");
        Button num9= new Button("9");
        Button dot= new Button(".");
        Button del= new Button("Delete");

        //  Setting the Calculator -- Column,Row (Embedding)
        keyboard.setLayoutX(600);
        keyboard.setLayoutY(150);
        keyboard.setHgap(10);
        keyboard.setVgap(10);
        keyboard.setPadding(new Insets(0, 10, 0, 10));
        keyboard.add(num7,0,0);
        keyboard.add(num8,1,0);
        keyboard.add(num9,2,0);
        keyboard.add(num4,0,1);
        keyboard.add(num5,1,1);
        keyboard.add(num6,2,1);
        keyboard.add(num1,0,2);
        keyboard.add(num2,1,2);
        keyboard.add(num3,2,2);
        keyboard.add(num0,0,3);
        keyboard.add(dot,1,3);
        keyboard.add(del,2,3);
        return keyboard;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
