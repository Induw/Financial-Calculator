package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

        //adding an anchorpane to hold everything related to scene1
        AnchorPane compoundSavingsOne = new AnchorPane();
        //adding a Pane to hold the Labels and Textfields related to user input in Compound Savings
        Pane inputSavingsOne = new Pane();

        //to add an HBox for the buttons on top
        HBox top = new HBox();
        Button b1 = new Button("Compound Savings");
        Button b2 = new Button("Compound Savings w/Payment");
        Button b3 = new Button("Mortgage");
        Button b4 = new Button("Loan");
        Button b5 = new Button("Help");
        b1.setOnAction(e -> mainStage.setScene(scene1));
        b2.setOnAction(e -> mainStage.setScene(scene2));
        top.setSpacing(60);
        top.setAlignment(Pos.CENTER);
        top.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY,Insets.EMPTY)));
        top.getChildren().addAll(b1,b2,b3,b4,b5);
        top.setPadding(new Insets(20,40,20,40));


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

        Label lblFutureVal= new Label("Future Value");
        lblFutureVal.setLayoutX(30);
        lblFutureVal.setLayoutY(260);

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

        TextField tfFutureVal = new TextField();
        tfFutureVal.setPrefWidth(300);
        tfFutureVal.setLayoutY(255);
        tfFutureVal.setLayoutX(170);

        Button calculate = new Button("Calculate");
        calculate.setPrefWidth(100);
        calculate.setPrefHeight(30);
        calculate.setLayoutX(180);
        calculate.setLayoutY(340);

        inputSavingsOne.getChildren().addAll(lblheading1,lblStartPrincipal,lblPeriods,lblInterest,lblFutureVal,tfStartPrincipal,tfPeriods,tfInterest,tfFutureVal,calculate);
        inputSavingsOne.setLayoutX(40);
        inputSavingsOne.setLayoutY(120);
        inputSavingsOne.setPrefSize(500,400);
        inputSavingsOne.setPadding(new Insets(20,40,20,40));
        inputSavingsOne.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));


        compoundSavingsOne.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        compoundSavingsOne.getChildren().addAll(top,inputSavingsOne,addCustKeyboard());
        scene1= new Scene(compoundSavingsOne, 925, 660);

        //anchorpane to hold everything related to scene2
        AnchorPane compoundSavingsTwo = new AnchorPane();
        //adding a Pane to hold the Labels and Textfields related to user input in Compound Savings with Payment
        Pane inputSavingsTwo = new Pane();

        //to add an HBox for the buttons on top
        HBox topTwo = new HBox();
        Button b12 = new Button("Compound Savings");
        Button b22 = new Button("Compound Savings w/Payment");
        Button b32 = new Button("Mortgage");
        Button b42 = new Button("Loan");
        Button b52 = new Button("Help");
        b12.setOnAction(e -> mainStage.setScene(scene1));
        b22.setOnAction(e -> mainStage.setScene(scene2));
        topTwo.setSpacing(60);
        topTwo.setAlignment(Pos.CENTER);
        topTwo.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY,Insets.EMPTY)));
        topTwo.getChildren().addAll(b12,b22,b32,b42,b52);
        topTwo.setPadding(new Insets(20,40,20,40));

        Label lblheading2 = new Label("Compound Savings w/Payment");
        lblheading2.setLayoutX(160);
        lblheading2.setLayoutY(40);

        Label lblStartPrincipal2 = new Label("Start Principal");
        lblStartPrincipal2.setLayoutX(30);
        lblStartPrincipal2.setLayoutY(110);

        Label lblPeriods2 = new Label("Periods(In Years)");
        lblPeriods2.setLayoutX(30);
        lblPeriods2.setLayoutY(160);

        Label lblInterest2 = new Label("I/Y(Interest)");
        lblInterest2.setLayoutX(30);
        lblInterest2.setLayoutY(210);

        Label lblFutureVal2= new Label("Future Value");
        lblFutureVal2.setLayoutX(30);
        lblFutureVal2.setLayoutY(260);

        TextField tfStartPrincipal2 = new TextField();
        tfStartPrincipal2.setPrefWidth(300);
        tfStartPrincipal2.setLayoutY(105);
        tfStartPrincipal2.setLayoutX(170);

        TextField tfPeriods2 = new TextField();
        tfPeriods2.setPrefWidth(300);
        tfPeriods2.setLayoutY(155);
        tfPeriods2.setLayoutX(170);

        TextField tfInterest2 = new TextField();
        tfInterest2.setPrefWidth(300);
        tfInterest2.setLayoutY(205);
        tfInterest2.setLayoutX(170);

        TextField tfFutureVal2 = new TextField();
        tfFutureVal2.setPrefWidth(300);
        tfFutureVal2.setLayoutY(255);
        tfFutureVal2.setLayoutX(170);

        TextField tfPMT = new TextField();
        tfPMT.setPrefWidth(300);
        tfPMT.setLayoutY(305);
        tfPMT.setLayoutX(170);

        Label lblPMT= new Label("PMT(Annuity)");
        lblPMT.setLayoutX(30);
        lblPMT.setLayoutY(310);

        Label lblradio = new Label("PMT made at");
        lblradio.setLayoutX(30);
        lblradio.setLayoutY(360);

        RadioButton rb1 = new RadioButton("Beginning");
        rb1.setLayoutX(170);
        rb1.setLayoutY(360);
        RadioButton rb2 = new RadioButton("End");
        rb2.setLayoutX(280);
        rb2.setLayoutY(360);

        Button calculate2 = new Button("Calculate");
        calculate2.setPrefWidth(100);
        calculate2.setPrefHeight(30);
        calculate2.setLayoutX(180);
        calculate2.setLayoutY(420);

        inputSavingsTwo.getChildren().addAll(lblheading2,lblStartPrincipal2,lblPeriods2,lblInterest2,lblFutureVal2,tfStartPrincipal2,tfPeriods2,tfInterest2,tfFutureVal2,tfPMT,lblPMT,lblradio,calculate2,rb1,rb2);
        inputSavingsTwo.setLayoutX(40);
        inputSavingsTwo.setLayoutY(120);
        inputSavingsTwo.setPrefSize(500,500);
        inputSavingsTwo.setPadding(new Insets(20,40,20,40));
        inputSavingsTwo.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));
        compoundSavingsTwo.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        compoundSavingsTwo.getChildren().addAll(topTwo,inputSavingsTwo,addCustKeyboard());

        scene2= new Scene(compoundSavingsTwo,925,660);


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
