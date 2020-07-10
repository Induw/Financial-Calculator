package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static java.lang.String.valueOf;


public class FinanceCalculator extends Application {
    Scene compoundscene1, compoundscene2,mortgagescene,loanscene;
    Stage mainStage;
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("Financial Calculator");

        //adding an anchorpane to hold everything related to scene1
        AnchorPane compoundSavingsOne = new AnchorPane();
        //adding a Pane to hold the Labels and Textfields related to user input in Compound Savings
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

        Label lblFutureVal= new Label("Future Value");
        lblFutureVal.setLayoutX(30);
        lblFutureVal.setLayoutY(260);

        TextField tfStartPrinciple = new TextField();
        tfStartPrinciple.setPrefWidth(300);
        tfStartPrinciple.setLayoutY(105);
        tfStartPrinciple.setLayoutX(170);

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

        //the code related to calculations of compound savings without payment

        calculate.setOnAction( e -> {
            try {
                if (tfFutureVal.getText().isEmpty()) {
                    Double futureVal = Double.valueOf(getsimpleFutureVal(Double.parseDouble(tfStartPrinciple.getText()), Double.parseDouble(tfPeriods.getText()), Double.parseDouble(tfInterest.getText())));
                    tfFutureVal.setText(futureVal.toString());
                } else if (tfPeriods.getText().isEmpty()) {
                    Double periods = Double.valueOf(getsimplePeriods(Double.parseDouble(tfStartPrinciple.getText()), Double.parseDouble(tfInterest.getText()), Double.parseDouble(tfFutureVal.getText())));
                    tfPeriods.setText(periods.toString());
                } else if (tfInterest.getText().isEmpty()) {
                    Double interest = Double.valueOf(getsimpleInterest(Double.parseDouble(tfStartPrinciple.getText()), Double.parseDouble(tfPeriods.getText()), Double.parseDouble(tfFutureVal.getText())));
                    tfInterest.setText(interest.toString());
                } else if (tfStartPrinciple.getText().isEmpty()) {
                    Double startprinciple = Double.valueOf(getsimpleStartPrinicple(Double.parseDouble(tfPeriods.getText()), Double.parseDouble(tfInterest.getText()), Double.parseDouble(tfFutureVal.getText())));
                    tfStartPrinciple.setText(startprinciple.toString());
                }
            }catch(NumberFormatException e1){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Please leave only 1 field empty !");
                error.setContentText(" * also make sure you enter ONLY Numbers ");
                error.showAndWait();
            }

        });
        inputSavingsOne.getChildren().addAll(lblheading1,lblStartPrincipal,lblPeriods,lblInterest,lblFutureVal,tfStartPrinciple,tfPeriods,tfInterest,tfFutureVal,calculate);
        inputSavingsOne.setLayoutX(40);
        inputSavingsOne.setLayoutY(120);
        inputSavingsOne.setPrefSize(500,400);
        inputSavingsOne.setPadding(new Insets(20,40,20,40));
        inputSavingsOne.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));

        compoundSavingsOne.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        compoundSavingsOne.getChildren().addAll(addButtons(),inputSavingsOne,addCustKeyboard());

        compoundscene1= new Scene(compoundSavingsOne, 925, 660);


        //Anchorpane to hold everything related to scene2

        AnchorPane compoundSavingsTwo = new AnchorPane();
        //adding a Pane to hold the Labels and Textfields related to user input in Compound Savings with Payment
        Pane inputSavingsTwo = new Pane();

        Label lblheading2 = new Label("Compound Savings w/Payment");
        lblheading2.setLayoutX(160);
        lblheading2.setLayoutY(40);

        Label lblStartPrinciple2 = new Label("Start Principal");
        lblStartPrinciple2.setLayoutX(30);
        lblStartPrinciple2.setLayoutY(110);

        Label lblPeriods2 = new Label("Periods(In Years)");
        lblPeriods2.setLayoutX(30);
        lblPeriods2.setLayoutY(160);

        Label lblInterest2 = new Label("I/Y(Interest)");
        lblInterest2.setLayoutX(30);
        lblInterest2.setLayoutY(210);

        Label lblFutureVal2= new Label("Future Value");
        lblFutureVal2.setLayoutX(30);
        lblFutureVal2.setLayoutY(260);

        TextField tfStartPrinciple2 = new TextField();
        tfStartPrinciple2.setPrefWidth(300);
        tfStartPrinciple2.setLayoutY(105);
        tfStartPrinciple2.setLayoutX(170);

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

        inputSavingsTwo.getChildren().addAll(lblheading2,lblStartPrinciple2,lblPeriods2,lblInterest2,lblFutureVal2,tfStartPrinciple2,tfPeriods2,tfInterest2,tfFutureVal2,tfPMT,lblPMT,lblradio,calculate2,rb1,rb2);
        inputSavingsTwo.setLayoutX(40);
        inputSavingsTwo.setLayoutY(120);
        inputSavingsTwo.setPrefSize(500,500);
        inputSavingsTwo.setPadding(new Insets(20,40,20,40));
        inputSavingsTwo.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));

        compoundSavingsTwo.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        compoundSavingsTwo.getChildren().addAll(addButtons(),inputSavingsTwo,addCustKeyboard());

        compoundscene2= new Scene(compoundSavingsTwo,925,660);

        //AnchorPane to anchor scene 3
        AnchorPane mortgage = new AnchorPane();

        Pane inputMortgage = new Pane();

        Label lblheading3 = new Label("Mortgage Calculator");
        lblheading3.setLayoutX(160);
        lblheading3.setLayoutY(40);

        Label lblhomePrice = new Label("Home Price");
        lblhomePrice.setLayoutX(30);
        lblhomePrice.setLayoutY(110);

        Label lbldownPaymor = new Label("Down Payment");
        lbldownPaymor.setLayoutX(30);
        lbldownPaymor.setLayoutY(160);

        Label lblMorLoanTerm = new Label("Loan Term");
        lblMorLoanTerm.setLayoutX(30);
        lblMorLoanTerm.setLayoutY(210);

        Label lblInterestRateMor= new Label("Interest Rate");
        lblInterestRateMor.setLayoutX(30);
        lblInterestRateMor.setLayoutY(260);

        TextField tfhomePrice = new TextField();
        tfhomePrice.setPrefWidth(300);
        tfhomePrice.setLayoutY(105);
        tfhomePrice.setLayoutX(170);

        TextField tfdownPaymentMor = new TextField();
        tfdownPaymentMor.setPrefWidth(300);
        tfdownPaymentMor.setLayoutY(155);
        tfdownPaymentMor.setLayoutX(170);

        TextField tfMorloanTerm = new TextField();
        tfMorloanTerm.setPrefWidth(300);
        tfMorloanTerm.setLayoutY(205);
        tfMorloanTerm.setLayoutX(170);

        TextField tfinterestRate = new TextField();
        tfinterestRate.setPrefWidth(300);
        tfinterestRate.setLayoutY(255);
        tfinterestRate.setLayoutX(170);

        Button calculate3 = new Button("Calculate");
        calculate3.setPrefWidth(100);
        calculate3.setPrefHeight(30);
        calculate3.setLayoutX(180);
        calculate3.setLayoutY(340);

        inputMortgage.getChildren().addAll(lblheading3,lblhomePrice,lbldownPaymor,lblMorLoanTerm,lblInterestRateMor,tfhomePrice,tfdownPaymentMor,tfinterestRate,tfMorloanTerm,calculate3);
        inputMortgage.setLayoutX(40);
        inputMortgage.setLayoutY(120);
        inputMortgage.setPrefSize(500,400);
        inputMortgage.setPadding(new Insets(20,40,20,40));
        inputMortgage.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));

        mortgage.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        mortgage.getChildren().addAll(addButtons(),inputMortgage,addCustKeyboard());

        mortgagescene= new Scene(mortgage,925,660);

        //AnchorPane to anchor scene 4
        AnchorPane loan = new AnchorPane();

        Pane inputLoan = new Pane();

        Label lblheading4 = new Label("Loan Calculator");
        lblheading4.setLayoutX(160);
        lblheading4.setLayoutY(40);

        Label lblPrice = new Label("Price");
        lblPrice.setLayoutX(30);
        lblPrice.setLayoutY(110);

        Label lblLoanTerm = new Label("Loan Term");
        lblLoanTerm.setLayoutX(30);
        lblLoanTerm.setLayoutY(160);

        Label lbldownpayloan = new Label("Down Payment");
        lbldownpayloan.setLayoutX(30);
        lbldownpayloan.setLayoutY(210);

        Label lblloanInterest= new Label("Interest Rate");
        lblloanInterest.setLayoutX(30);
        lblloanInterest.setLayoutY(260);

        TextField tfPrice = new TextField();
        tfPrice.setPrefWidth(300);
        tfPrice.setLayoutY(105);
        tfPrice.setLayoutX(170);

        TextField tfloanTerm = new TextField();
        tfloanTerm.setPrefWidth(300);
        tfloanTerm.setLayoutY(155);
        tfloanTerm.setLayoutX(170);

        TextField tfdownpayloan = new TextField();
        tfdownpayloan.setPrefWidth(300);
        tfdownpayloan.setLayoutY(205);
        tfdownpayloan.setLayoutX(170);

        TextField tfloanInterest = new TextField();
        tfloanInterest.setPrefWidth(300);
        tfloanInterest.setLayoutY(255);
        tfloanInterest.setLayoutX(170);

        Button calculate4 = new Button("Calculate");
        calculate4.setPrefWidth(100);
        calculate4.setPrefHeight(30);
        calculate4.setLayoutX(180);
        calculate4.setLayoutY(340);

        inputLoan.getChildren().addAll(lblheading4,lblPrice,lbldownpayloan,lblLoanTerm,lblloanInterest,tfPrice,tfdownpayloan,tfloanInterest,tfloanTerm,calculate4);
        inputLoan.setLayoutX(40);
        inputLoan.setLayoutY(120);
        inputLoan.setPrefSize(500,400);
        inputLoan.setPadding(new Insets(20,40,20,40));
        inputLoan.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(20),Insets.EMPTY)));

        loan.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        loan.getChildren().addAll(addButtons(),inputLoan,addCustKeyboard());

        loanscene = new Scene(loan,925,660);


        mainStage.setScene(compoundscene1);
        mainStage.show();
    }
    public  HBox addButtons(){
        //to add an HBox for the buttons on top
        HBox top = new HBox();
        Button b1 = new Button("Compound Savings");
        Button b2 = new Button("Compound Savings w/Payment");
        Button b3 = new Button("Mortgage");
        Button b4 = new Button("Loan");
        Button b5 = new Button("Help");
        b1.setOnAction(e -> { mainStage.setScene(compoundscene1);
            DropShadow shadow = new DropShadow();
            b1.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"), CornerRadii.EMPTY, Insets.EMPTY)));
            b1.setEffect(shadow);
        });
        b2.setOnAction(e -> mainStage.setScene(compoundscene2));
        b3.setOnAction(e -> mainStage.setScene(mortgagescene));
        b4.setOnAction(e -> mainStage.setScene(loanscene));
        top.setSpacing(60);
        top.setAlignment(Pos.CENTER);
        top.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY,Insets.EMPTY)));
        top.getChildren().addAll(b1,b2,b3,b4,b5);
        top.setPadding(new Insets(20,40,20,40));
    return top;
    }
    // Onscreen Keyboard
    public GridPane addCustKeyboard(){
        GridPane keyboard=new GridPane();
        //  Onscreen Keyboard Buttons
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
        Button dot = new Button(".");
        Button del = new Button("Delete");
        // Designing the layout of the keyboard
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

    //method to calculate future value in the compound savings without payment
    public static String getsimpleFutureVal(double startprincipal ,double periods ,double interest){
        double futurevalue = startprincipal * (Math.pow((1+((interest/100)/12)),12*periods));
        return String.valueOf(Math.round(futurevalue *100.0)/100.0);
    }
    //method to calculate periods in the compound savings without payment
    public static String getsimplePeriods(double startprinciple,double interest , double futurevalue){
        double periods = (Math.log(futurevalue/startprinciple))/(12*(Math.log(1+((interest/100)/12))));
        return String.valueOf(Math.round(periods * 100.0)/100.0);
    }
    //method to calculate interest in the compound savings without payment
    public static String getsimpleInterest(double startpriciple,double periods,double futurevalue){
        double interest =(12*(Math.pow((futurevalue/startpriciple),(1/(12*periods)))-1))*100;
        return String.valueOf(Math.round(interest * 100.0)/100.0);
    }
    //method to calculate start principle in the compound savings without payment
    public static String getsimpleStartPrinicple(double periods,double interest,double futurevalue){
        double startprinciple = futurevalue/Math.pow((1+(interest/100)/12),12*periods);
        return String.valueOf(Math.round(startprinciple * 100.0)/100.0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
