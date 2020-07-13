package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;


public class FinanceCalculator extends Application {
    Scene compoundscene1, compoundscene2,mortgagescene,loanscene;
    Stage mainStage;
    //textfield array to implement keyboard
    TextField[] fields = new TextField[1];
    //piechart
    PieChart pchart ;
    //label to display history of compound savings one
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("Financial Calculator");

// Code related to the Compound Savings Calculator(lump sum)

        //adding an anchorpane to hold everything related to scene1
        AnchorPane compoundSavingsOne = new AnchorPane();
        //adding a Pane to hold the Labels and Textfields related to user input in Compound Savings
        Pane inputSavingsOne = new Pane();

        Label lblheading1 = new Label("Compound Savings");
        styleHeadings(lblheading1);
        lblheading1.setLayoutX(160);
        lblheading1.setLayoutY(40);

        Label lblStartPrincipal = new Label("Start Principal");
        lblStartPrincipal.setLayoutX(30);
        lblStartPrincipal.setLayoutY(110);
        styleLabels(lblStartPrincipal);


        Label lblPeriods = new Label("Periods(In Years)");
        lblPeriods.setLayoutX(30);
        lblPeriods.setLayoutY(160);
        styleLabels(lblPeriods);

        Label lblInterest = new Label("I/Y(Interest)");
        lblInterest.setLayoutX(30);
        lblInterest.setLayoutY(210);
        styleLabels(lblInterest);

        Label lblFutureVal= new Label("Future Value");
        lblFutureVal.setLayoutX(30);
        lblFutureVal.setLayoutY(260);
        styleLabels(lblFutureVal);

        TextField tfStartPrinciple = new TextField();
        tfStartPrinciple.setPrefWidth(300);
        tfStartPrinciple.setLayoutY(105);
        tfStartPrinciple.setLayoutX(170);
        tfStartPrinciple.setOnMouseClicked(e -> fields[0]=tfStartPrinciple);


        TextField tfPeriods = new TextField();
        tfPeriods.setPrefWidth(300);
        tfPeriods.setLayoutY(155);
        tfPeriods.setLayoutX(170);
        tfPeriods.setOnMouseClicked(e -> fields[0]=tfPeriods);

        TextField tfInterest = new TextField();
        tfInterest.setPrefWidth(300);
        tfInterest.setLayoutY(205);
        tfInterest.setLayoutX(170);
        tfInterest.setOnMouseClicked(e -> fields[0]=tfInterest);

        TextField tfFutureVal = new TextField();
        tfFutureVal.setPrefWidth(300);
        tfFutureVal.setLayoutY(255);
        tfFutureVal.setLayoutX(170);
        tfFutureVal.setOnMouseClicked(e -> fields[0]=tfFutureVal);

        Button calculate = new Button("Calculate");
        calculate.setPrefWidth(100);
        calculate.setPrefHeight(30);
        calculate.setLayoutX(160);
        calculate.setLayoutY(340);
        styleCalculate(calculate);


        //all calculations related to the calculate button
        calculate.setOnAction( e -> {

            try {
                if (tfFutureVal.getText().isEmpty()) {
                    Double futureVal = Double.valueOf(getsimpleFutureVal(Double.parseDouble(tfStartPrinciple.getText()),
                                       Double.parseDouble(tfPeriods.getText()), Double.parseDouble(tfInterest.getText())));
                    tfFutureVal.setText(futureVal.toString());
                } else if (tfPeriods.getText().isEmpty()) {
                    Double periods = Double.valueOf(getsimplePeriods(Double.parseDouble(tfStartPrinciple.getText()),
                            Double.parseDouble(tfInterest.getText()), Double.parseDouble(tfFutureVal.getText())));
                    tfPeriods.setText(periods.toString());
                } else if (tfInterest.getText().isEmpty()) {
                    Double interest = Double.valueOf(getsimpleInterest(Double.parseDouble(tfStartPrinciple.getText()),
                            Double.parseDouble(tfPeriods.getText()), Double.parseDouble(tfFutureVal.getText())));
                    tfInterest.setText(interest.toString());
                } else if (tfStartPrinciple.getText().isEmpty()) {
                    Double startprinciple = Double.valueOf(getsimpleStartPrinicple(Double.parseDouble(tfPeriods.getText()),
                            Double.parseDouble(tfInterest.getText()), Double.parseDouble(tfFutureVal.getText())));
                    tfStartPrinciple.setText(startprinciple.toString());
                }else {
                    showFullError();
                }
            }catch(NumberFormatException e1){
                showEmptyError();
            }
            try {//writing calculation history to a text file
                FileWriter Writer = new FileWriter("compoundSavingsWithoutPMT.txt",true);
                Writer.write(String.valueOf(java.time.LocalDateTime.now()));
                Writer.write("\nStart Principle : " +tfStartPrinciple.getText() +"\n");
                Writer.write("Periods(Years) : " +tfPeriods.getText() + "\n");
                Writer.write("Interest(I/Y) : " +tfInterest.getText() +"\n");
                Writer.write("Future Value : " + tfFutureVal.getText()+"\n");
                Writer.write("\n----------------------------------------------------------\n");
                Writer.close();

                System.out.println("Successfully wrote to the file.");
            } catch (IOException ev) {
                System.out.println("An error occurred.");
                ev.printStackTrace();
            }
            if(pchart != null){
                pchart.getData().clear();
            }
            Double interestvalue = Double.parseDouble(tfFutureVal.getText()) -
                    Double.parseDouble(tfStartPrinciple.getText());
            pchart = getChart(Double.parseDouble(tfStartPrinciple.getText()),interestvalue);
            compoundSavingsOne.getChildren().add(pchart);
        });
        //adding a button to clear textfields and piechart
        Button clearOne = new Button("Clear");
        clearOne.setPrefSize(80,30);
        clearOne.setLayoutX(370);
        clearOne.setLayoutY(348);
        styleClear(clearOne);
        clearOne.setOnAction(e ->{
            tfStartPrinciple.setText("");
            tfFutureVal.setText("");
            tfInterest.setText("");
            tfPeriods.setText("");
            compoundSavingsOne.getChildren().remove(3);
        });

        inputSavingsOne.getChildren().addAll(lblheading1,lblStartPrincipal,lblPeriods,lblInterest,lblFutureVal,
                                            clearOne,tfStartPrinciple,tfPeriods,tfInterest,tfFutureVal,calculate);
        inputSavingsOne.setLayoutX(40);
        inputSavingsOne.setLayoutY(120);
        inputSavingsOne.setPrefSize(500,410);
        inputSavingsOne.setPadding(new Insets(20,40,20,40));
        inputSavingsOne.setBackground(new Background(new BackgroundFill(Color.web("#b2cbdb"), new CornerRadii(20),
                Insets.EMPTY)));
        compoundSavingsOne.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY,
                Insets.EMPTY)));
        compoundSavingsOne.getChildren().addAll(addButtons(),inputSavingsOne,addCustKeyboard());

        compoundscene1 = new Scene(compoundSavingsOne, 925, 680);

//Code related to the Compound Savings with Payment Calculator

        //Anchorpane to hold everything related to CompoundSavings2 scene
        AnchorPane compoundSavingsTwo = new AnchorPane();
        //adding a Pane to hold the Labels and Textfields related to user input in Compound Savings with Payment
        Pane inputSavingsTwo = new Pane();

        Label lblheading2 = new Label("Compound Savings w/Payment");
        lblheading2.setLayoutX(110);
        lblheading2.setLayoutY(40);
        styleHeadings(lblheading2);

        Label lblStartPrinciple2 = new Label("Start Principal");
        lblStartPrinciple2.setLayoutX(30);
        lblStartPrinciple2.setLayoutY(110);
        styleLabels(lblStartPrinciple2);

        Label lblPeriods2 = new Label("Periods(In Years)");
        lblPeriods2.setLayoutX(30);
        lblPeriods2.setLayoutY(160);
        styleLabels(lblPeriods2);

        Label lblInterest2 = new Label("I/Y(Interest)");
        lblInterest2.setLayoutX(30);
        lblInterest2.setLayoutY(210);
        styleLabels(lblInterest2);

        Label lblFutureVal2= new Label("Future Value");
        lblFutureVal2.setLayoutX(30);
        lblFutureVal2.setLayoutY(310);
        styleLabels(lblFutureVal2);

        TextField tfStartPrinciple2 = new TextField();
        tfStartPrinciple2.setPrefWidth(300);
        tfStartPrinciple2.setLayoutY(105);
        tfStartPrinciple2.setLayoutX(170);
        tfStartPrinciple2.setOnMouseClicked(e -> fields[0]=tfStartPrinciple2);

        TextField tfPeriods2 = new TextField();
        tfPeriods2.setPrefWidth(300);
        tfPeriods2.setLayoutY(155);
        tfPeriods2.setLayoutX(170);
        tfPeriods2.setOnMouseClicked(e -> fields[0]=tfPeriods2);

        TextField tfInterest2 = new TextField();
        tfInterest2.setPrefWidth(300);
        tfInterest2.setLayoutY(205);
        tfInterest2.setLayoutX(170);
        tfInterest2.setOnMouseClicked(e -> fields[0]=tfInterest2);

        TextField tfFutureVal2 = new TextField();
        tfFutureVal2.setPrefWidth(300);
        tfFutureVal2.setLayoutY(305);
        tfFutureVal2.setLayoutX(170);
        tfFutureVal2.setOnMouseClicked(e -> fields[0]=tfFutureVal2);

        TextField tfPMT = new TextField();
        tfPMT.setPrefWidth(300);
        tfPMT.setLayoutY(255);
        tfPMT.setLayoutX(170);
        tfPMT.setOnMouseClicked(e -> fields[0]=tfPMT);


        Label lblPMT= new Label("PMT(Annuity)");
        lblPMT.setLayoutX(30);
        lblPMT.setLayoutY(260);
        styleLabels(lblPMT);

        Button calculate2 = new Button("Calculate");
        calculate2.setPrefWidth(100);
        calculate2.setPrefHeight(30);
        calculate2.setLayoutX(180);
        calculate2.setLayoutY(380);
        styleCalculate(calculate2);

        //adding a button to clear textfields and piechart
        Button clearTwo = new Button("Clear");
        clearTwo.setPrefSize(80,30);
        clearTwo.setLayoutX(370);
        clearTwo.setLayoutY(385);
        styleClear(clearTwo);
        clearTwo.setOnAction(e ->{
            tfStartPrinciple2.setText("");
            tfFutureVal2.setText("");
            tfInterest2.setText("");
            tfPeriods2.setText("");
            tfPMT.setText("");
            compoundSavingsTwo.getChildren().remove(3);
        });

        //the code related to calculations of compound savings WITH Payment
        calculate2.setOnAction( e1 -> {
            try {  if (tfStartPrinciple2.getText().isEmpty()) {
                    Double startprin2 = Double.valueOf(getcompoundStartPrinciple(Double.parseDouble(tfFutureVal2.getText()),
                                        Double.parseDouble(tfInterest2.getText()),Double.parseDouble(tfPeriods2.getText()),
                                        Double.parseDouble(tfPMT.getText())));
                        tfStartPrinciple2.setText(startprin2.toString());
                } else if (tfFutureVal2.getText().isEmpty()) {
                    Double futureval2 = Double.valueOf(getcompoundFutureVal(Double.parseDouble(tfStartPrinciple2.getText()),
                                        Double.parseDouble(tfInterest2.getText()), Double.parseDouble(tfPeriods2.getText()),
                                        Double.parseDouble(tfPMT.getText())));
                                tfFutureVal2.setText(futureval2.toString());
                } else if (tfPeriods2.getText().isEmpty()) {
                    Double periods = Double.valueOf(getcompoundPeriods(Double.parseDouble(tfStartPrinciple2.getText()),
                                     Double.parseDouble(tfInterest2.getText()), Double.parseDouble(tfPMT.getText()),
                                     Double.parseDouble(tfFutureVal2.getText())));
                                tfPeriods2.setText(periods.toString());
                } else if (tfPMT.getText().isEmpty()) {
                    Double payment = Double.valueOf(getcompoundPMT(Double.parseDouble(tfStartPrinciple2.getText()),
                            Double.parseDouble(tfInterest2.getText()), Double.parseDouble(tfPeriods2.getText()),
                            Double.parseDouble(tfFutureVal2.getText())));
                    tfPMT.setText(payment.toString());
                }else if(tfInterest2.getText().isEmpty()){
                throw new IllegalArgumentException ();
                } else {
                    showFullError();
            }
            }catch(NumberFormatException e2){
               showEmptyError();
            }
            catch (IllegalArgumentException intempty ){
                showInterestError();
            }
            try {//writing calculation history to a text file
                FileWriter Writer = new FileWriter("compoundSavingsWithPMT.txt",true);
                Writer.write(String.valueOf(java.time.LocalDateTime.now()));
                Writer.write("\nStart Principle:  " + tfStartPrinciple2.getText() +"\n");
                Writer.write("\nPeriods( In Years):  " +tfPeriods2.getText()+"\n");
                Writer.write("Interest(I/Y): " + tfInterest2.getText()+"\n");
                Writer.write("PMT:" + tfPMT.getText()+"\n");
                Writer.write("Future Value:  " + tfFutureVal2.getText() +"\n");
                Writer.write("\n----------------------------------------------------------\n");
                Writer.close();

                System.out.println("Successfully wrote to the file 02.");
            } catch (IOException ev) {
                System.out.println("An error occurred.");
                ev.printStackTrace();
            }

            if(pchart != null){
                pchart.getData().clear();
            }
            Double interestValue=Double.parseDouble(tfFutureVal2.getText())-Double.parseDouble(tfStartPrinciple2.getText());
            pchart = getChart(Double.parseDouble(tfStartPrinciple2.getText()),interestValue);
            compoundSavingsTwo.getChildren().add(pchart);
        });


        inputSavingsTwo.getChildren().addAll(lblheading2,lblStartPrinciple2,lblPeriods2,lblInterest2,lblFutureVal2,
                            tfStartPrinciple2,clearTwo,tfPeriods2,tfInterest2,tfFutureVal2,tfPMT,lblPMT,calculate2);
        inputSavingsTwo.setLayoutX(40);
        inputSavingsTwo.setLayoutY(120);
        inputSavingsTwo.setPrefSize(500,450);
        inputSavingsTwo.setPadding(new Insets(20,40,20,40));
        inputSavingsTwo.setBackground(new Background(new BackgroundFill(Color.web("#b2cbdb"), new CornerRadii(20),
                Insets.EMPTY)));

        compoundSavingsTwo.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE,CornerRadii.EMPTY,
                Insets.EMPTY)));
        compoundSavingsTwo.getChildren().addAll(addButtons(),inputSavingsTwo,addCustKeyboard());
        compoundscene2= new Scene(compoundSavingsTwo,925,680);

//Code related to Mortgage Calculator
        //AnchorPane to anchor mortgage scene
        AnchorPane mortgage = new AnchorPane();

        Pane inputMortgage = new Pane();

        Label lblheading3 = new Label("Mortgage Calculator");
        lblheading3.setLayoutX(160);
        lblheading3.setLayoutY(40);
        styleHeadings(lblheading3);

        Label lblhomePrice = new Label("House Price");
        lblhomePrice.setLayoutX(30);
        lblhomePrice.setLayoutY(110);
        styleLabels(lblhomePrice);

        Label lbldownPaymor = new Label("Down Payment");
        lbldownPaymor.setLayoutX(30);
        lbldownPaymor.setLayoutY(160);
        styleLabels(lbldownPaymor);


        Label lblMorLoanTerm = new Label("Loan Term (Years)");
        lblMorLoanTerm.setLayoutX(30);
        lblMorLoanTerm.setLayoutY(210);
        styleLabels(lblMorLoanTerm);

        Label lblInterestRateMor= new Label("Interest Rate");
        lblInterestRateMor.setLayoutX(30);
        lblInterestRateMor.setLayoutY(260);
        styleLabels(lblInterestRateMor);

        Label lblMonthPayMor = new Label("Monthly Payment");
        lblMonthPayMor.setLayoutX(30);
        lblMonthPayMor.setLayoutY(310);
        styleLabels(lblMonthPayMor);

        TextField tfhomePrice = new TextField();
        tfhomePrice.setPrefWidth(300);
        tfhomePrice.setLayoutY(105);
        tfhomePrice.setLayoutX(170);
        tfhomePrice.setOnMouseClicked(e -> fields[0]=tfhomePrice);

        TextField tfdownPaymentMor = new TextField();
        tfdownPaymentMor.setPrefWidth(300);
        tfdownPaymentMor.setLayoutY(155);
        tfdownPaymentMor.setLayoutX(170);
        tfdownPaymentMor.setOnMouseClicked(e -> fields[0]=tfdownPaymentMor);

        TextField tfMorloanTerm = new TextField();
        tfMorloanTerm.setPrefWidth(300);
        tfMorloanTerm.setLayoutY(205);
        tfMorloanTerm.setLayoutX(170);
        tfMorloanTerm.setOnMouseClicked(e -> fields[0]=tfMorloanTerm);

        TextField tfinterestMor = new TextField();
        tfinterestMor.setPrefWidth(300);
        tfinterestMor.setLayoutY(255);
        tfinterestMor.setLayoutX(170);
        tfinterestMor.setOnMouseClicked(e -> fields[0]=tfinterestMor);

        TextField tfmonthPayMor = new TextField();
        tfmonthPayMor.setPrefWidth(300);
        tfmonthPayMor.setLayoutY(305);
        tfmonthPayMor.setLayoutX(170);
        tfmonthPayMor.setOnMouseClicked(e -> fields[0]=tfmonthPayMor);

        Button calculate3 = new Button("Calculate");
        calculate3.setPrefWidth(100);
        calculate3.setPrefHeight(30);
        calculate3.setLayoutX(180);
        calculate3.setLayoutY(378);
        styleCalculate(calculate3);

        //adding a button to clear textfields and piechart
        Button clearThree = new Button("Clear");
        clearThree.setPrefSize(80,30);
        clearThree.setLayoutX(370);
        clearThree.setLayoutY(385);
        styleClear(clearThree);
        clearThree.setOnAction(e ->{
            tfhomePrice.setText("");
            tfdownPaymentMor.setText("");
            tfMorloanTerm.setText("");
            tfmonthPayMor.setText("");
            tfinterestMor.setText("");
            mortgage.getChildren().remove(3);
        });

        // calculations of the Mortgage Calculator
        calculate3.setOnAction( e2 -> {
            try {  if (tfmonthPayMor.getText().isEmpty()) {
                Double monthlyPayMor = Double.valueOf(getmonthPay(Double.parseDouble(tfhomePrice.getText()),
                        Double.parseDouble(tfdownPaymentMor.getText()),Double.parseDouble(tfMorloanTerm.getText()),
                        Double.parseDouble(tfinterestMor.getText())));
                tfmonthPayMor.setText(monthlyPayMor.toString());
            } else if (tfdownPaymentMor.getText().isEmpty()) {
                Double downpay = Double.valueOf(getDownPay(Double.parseDouble(tfhomePrice.getText()),
                        Double.parseDouble(tfMorloanTerm.getText()), Double.parseDouble(tfmonthPayMor.getText()),
                        Double.parseDouble(tfinterestMor.getText())));
                tfdownPaymentMor.setText(downpay.toString());
            } else if (tfMorloanTerm.getText().isEmpty()) {
                Double terms = Double.valueOf(getMorTerms(Double.parseDouble(tfhomePrice.getText()),
                        Double.parseDouble(tfdownPaymentMor.getText()), Double.parseDouble(tfmonthPayMor.getText()),
                        Double.parseDouble(tfinterestMor.getText())));
                tfMorloanTerm.setText(terms.toString());
            } else if (tfhomePrice.getText().isEmpty()) {
                    Double homeprice = Double.valueOf(gethousePrice(Double.parseDouble(tfdownPaymentMor.getText()),
                            Double.parseDouble(tfmonthPayMor.getText()), Double.parseDouble(tfMorloanTerm.getText()),
                            Double.parseDouble(tfinterestMor.getText())));
                    tfhomePrice.setText(homeprice.toString());
            }else if(tfinterestMor.getText().isEmpty()){
                throw new IllegalArgumentException ();
            }else {
                showFullError();
            }
            }catch(NumberFormatException e3){
               showEmptyError();
            }
            catch (IllegalArgumentException intempty ){
                showInterestError();

            }
            try {//writing calculation history to a text file
                FileWriter Writer = new FileWriter("mortgage.txt",true);
                Writer.write(String.valueOf(java.time.LocalDateTime.now()));
                Writer.write("\nHome Price :  " + tfhomePrice.getText() +"\n");
                Writer.write("Down Payment :  " +tfdownPaymentMor.getText()+"\n");
                Writer.write("Loan Term (Years): " + tfMorloanTerm.getText()+"\n");
                Writer.write("Interest Rate: " + tfinterestMor.getText()+"\n");
                Writer.write("Monthly Payment:  " + tfmonthPayMor.getText() +"\n");
                Writer.write("\n----------------------------------------------------------\n");
                Writer.close();

                System.out.println("Successfully wrote to the file 03.");
            } catch (IOException ev) {
                System.out.println("An error occurred.");
                ev.printStackTrace();
            }

            if(pchart != null){
                pchart.getData().clear();
            }
            Double interestValueMor= (Double.parseDouble(tfmonthPayMor.getText())*
                    Double.parseDouble(tfMorloanTerm.getText())*12) - Double.parseDouble(tfhomePrice.getText());
            pchart = getChart(Double.parseDouble(tfhomePrice.getText()),interestValueMor);
            mortgage.getChildren().add(pchart);

        });



        inputMortgage.getChildren().addAll(lblheading3,lblhomePrice,lbldownPaymor,lblMorLoanTerm,lblInterestRateMor,
                                lblMonthPayMor,clearThree,tfhomePrice,tfdownPaymentMor,tfinterestMor,tfMorloanTerm,
                                tfmonthPayMor,calculate3);
        inputMortgage.setLayoutX(40);
        inputMortgage.setLayoutY(120);
        inputMortgage.setPrefSize(500,450);
        inputMortgage.setPadding(new Insets(20,40,20,40));
        inputMortgage.setBackground(new Background(new BackgroundFill(Color.web("#b2cbdb"), new CornerRadii(20),
                Insets.EMPTY)));

        mortgage.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY,
                Insets.EMPTY)));
        mortgage.getChildren().addAll(addButtons(),inputMortgage,addCustKeyboard());

        mortgagescene= new Scene(mortgage,925,680);


// Code related to Loan Calculator

        //AnchorPane to anchor Loan scene
        AnchorPane loan = new AnchorPane();

        Pane inputLoan = new Pane();

        Label lblheading4 = new Label("Loan Calculator");
        lblheading4.setLayoutX(160);
        lblheading4.setLayoutY(40);
        styleHeadings(lblheading4);

        Label lblAmount = new Label("Loan Amount");
        lblAmount.setLayoutX(30);
        lblAmount.setLayoutY(110);
        styleLabels(lblAmount);

        Label lblLoanTerm = new Label("Loan Term (Mo)");
        lblLoanTerm.setLayoutX(30);
        lblLoanTerm.setLayoutY(160);
        styleLabels(lblLoanTerm);

        Label lblmonthpayloan = new Label("Monthly Payment");
        lblmonthpayloan.setLayoutX(30);
        lblmonthpayloan.setLayoutY(210);
        styleLabels(lblmonthpayloan);

        Label lblloanInterest= new Label("Interest Rate");
        lblloanInterest.setLayoutX(30);
        lblloanInterest.setLayoutY(260);
        styleLabels(lblloanInterest);

        TextField tfAmount = new TextField();
        tfAmount.setPrefWidth(300);
        tfAmount.setLayoutY(105);
        tfAmount.setLayoutX(170);
        tfAmount.setOnMouseClicked(e -> fields[0]=tfAmount);

        TextField tfloanTerm = new TextField();
        tfloanTerm.setPrefWidth(300);
        tfloanTerm.setLayoutY(155);
        tfloanTerm.setLayoutX(170);
        tfAmount.setOnMouseClicked(e -> fields[0]=tfAmount);

        TextField tfmonthpayloan = new TextField();
        tfmonthpayloan.setPrefWidth(300);
        tfmonthpayloan.setLayoutY(205);
        tfmonthpayloan.setLayoutX(170);
        tfmonthpayloan.setOnMouseClicked(e -> fields[0]=tfmonthpayloan);

        TextField tfloanInterest = new TextField();
        tfloanInterest.setPrefWidth(300);
        tfloanInterest.setLayoutY(255);
        tfloanInterest.setLayoutX(170);
        tfloanInterest.setOnMouseClicked(e -> fields[0]=tfloanInterest);

        Button calculate4 = new Button("Calculate");
        calculate4.setPrefWidth(100);
        calculate4.setPrefHeight(30);
        calculate4.setLayoutX(180);
        calculate4.setLayoutY(340);
        styleCalculate(calculate4);

        //adding a button to clear textfields and piechart
        Button clearFour = new Button("Clear");
        clearFour.setPrefSize(80,30);
        clearFour.setLayoutX(370);
        clearFour.setLayoutY(343);
        styleClear(clearFour);
        clearFour.setOnAction(e ->{
            tfAmount.setText("");
            tfloanTerm.setText("");
            tfmonthpayloan.setText("");
            tfloanInterest.setText("");
            loan.getChildren().remove(3);
        });

        //calculations of loan calculator
        calculate4.setOnAction( e3 -> {
            try {
                if (tfmonthpayloan.getText().isEmpty()) {
                    Double monthlypay = Double.valueOf(getloanMonthpay(Double.parseDouble(tfAmount.getText()),
                            Double.parseDouble(tfloanTerm.getText()), Double.parseDouble(tfloanInterest.getText())));
                    tfmonthpayloan.setText(monthlypay.toString());
                } else if (tfAmount.getText().isEmpty()) {
                    Double amount = Double.valueOf(getAmount(Double.parseDouble(tfmonthpayloan.getText()),
                            Double.parseDouble(tfloanTerm.getText()), Double.parseDouble(tfloanInterest.getText())));
                    tfAmount.setText(amount.toString());
                } else if (tfloanTerm.getText().isEmpty()) {
                    Double loanTerm = Double.valueOf(getloanTerm(Double.parseDouble(tfmonthpayloan.getText()),
                            Double.parseDouble(tfAmount.getText()), Double.parseDouble(tfloanInterest.getText())));
                    tfloanTerm.setText(loanTerm.toString());
                }else if(tfloanInterest.getText().isEmpty()){
                    throw new IllegalArgumentException ();
                } else {
                    showFullError();
                }
            }catch(NumberFormatException e1){
                showEmptyError();
            }catch (IllegalArgumentException intempty ){
                showInterestError();

            }
            try {//writing calculation history to a text file
                FileWriter Writer = new FileWriter("loan.txt",true);
                Writer.write(String.valueOf(java.time.LocalDateTime.now()));
                Writer.write("\nLoan Amount :  " + tfAmount.getText() +"\n");
                Writer.write("Loan Term (Months) : " + tfloanTerm.getText()+"\n");
                Writer.write("Interest Rate: " + tfloanInterest.getText()+"\n");
                Writer.write("Monthly Payment:  " + tfmonthpayloan.getText() +"\n");
                Writer.write("\n----------------------------------------------------------\n");
                Writer.close();

                System.out.println("Successfully wrote to the file 04.");
            } catch (IOException ev) {
                System.out.println("An error occurred.");
                ev.printStackTrace();
            }
            if(pchart != null){
                pchart.getData().clear();
            }
            Double interestValueloan= Double.parseDouble(tfmonthpayloan.getText())*
                    Double.parseDouble(tfloanTerm.getText()) - Double.parseDouble(tfAmount.getText());
            pchart = getChart(Double.parseDouble(tfAmount.getText()),interestValueloan);
            loan.getChildren().add(pchart);

        });

        inputLoan.getChildren().addAll(lblheading4,lblAmount,lblmonthpayloan,lblLoanTerm,lblloanInterest,tfAmount,
                                        clearFour,tfmonthpayloan,tfloanInterest,tfloanTerm,calculate4);
        inputLoan.setLayoutX(40);
        inputLoan.setLayoutY(120);
        inputLoan.setPrefSize(500,400);
        inputLoan.setPadding(new Insets(20,40,20,40));
        inputLoan.setBackground(new Background(new BackgroundFill(Color.web("#b2cbdb"), new CornerRadii(20),
                Insets.EMPTY)));

        loan.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY,
                Insets.EMPTY)));
        loan.getChildren().addAll(addButtons(),inputLoan,addCustKeyboard());

        loanscene = new Scene(loan,925,680);


        mainStage.setScene(compoundscene1);
        mainStage.show();
    }
    //to add an HBox for the buttons on top
    public  HBox addButtons(){
        HBox top = new HBox();
        Button b1 = new Button("Compound Savings");
        b1.setPrefSize(195,60);
        b1.setFont((Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 18)));
        b1.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(8),
                Insets.EMPTY)));
        b1.setCursor(Cursor.HAND);
        //shadow effect and color change when mouse hovers over button
        b1.setOnMouseEntered(e -> getHovereffect(b1));
        // to not show shadow effect and color change when mouse doesn't hover over button
        b1.setOnMouseExited(e -> getNormaleffect(b1));

        Button b2 = new Button("Compound Savings w/Pay");
        b2.setPrefSize(245,60);
        b2.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(8),
                Insets.EMPTY)));
        b2.setFont((Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 17)));
        b2.setCursor(Cursor.HAND);
        b2.setOnMouseEntered(e -> getHovereffect(b2));
        // to not show shadow effect and color change when mouse doesn't hover over button
        b2.setOnMouseExited(e -> getNormaleffect(b2));

        Button b3 = new Button("Mortgage  \uD83C\uDFE0");
        b3.setPrefSize(155,60);
        b3.setCursor(Cursor.HAND);
        b3.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(8),
                Insets.EMPTY)));
        b3.setFont((Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 18)));
        b3.setOnMouseEntered(e -> getHovereffect(b3));
        // to not show shadow effect and color change when mouse doesn't hover over button
        b3.setOnMouseExited(e -> getNormaleffect(b3));

        Button b4 = new Button("Loan");
        b4.setPrefSize(155,60);
        b4.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(8),
                Insets.EMPTY)));
        b4.setFont((Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 18)));
        b4.setOnMouseEntered(e -> getHovereffect(b4));
        b4.setOnMouseExited(e -> getNormaleffect(b4));
        b4.setCursor(Cursor.HAND);

        Button b5 = new Button("Help "+"❓");
        b5.setPrefSize(132,60);
        b5.setCursor(Cursor.HAND);
        b5.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(8),
                Insets.EMPTY)));
        b5.setFont((Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 19)));
        b5.setOnMouseEntered(e -> getHovereffect(b5));
        b5.setOnMouseExited(e -> getNormaleffect(b5));
        //changing scenes using the buttons on top
        b1.setOnAction(e -> mainStage.setScene(compoundscene1));
        b2.setOnAction(e -> mainStage.setScene(compoundscene2));
        b3.setOnAction(e -> mainStage.setScene(mortgagescene));
        b4.setOnAction(e -> mainStage.setScene(loanscene));
        //popup window for help view
        b5.setOnAction(event -> {
            Stage popupwindow=new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Help");
            Label label1 = new Label("          Financial Calculator Tips ");
            label1.setLayoutY(2);
            label1.setFont((Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 18)));
            Label label2 = new Label("➤ To use the calculator, leave the textfield\n  you want to calculate, Empty.");
            Label label3 = new Label("➤ Interest can be calculated only\n  in Compound Savings without payment");
            Label label4 = new Label("➤ You can use the onscreen keyboard to type \n  in the fields with ease");
            Label label5 = new Label("➤ After every Calculation, a PieChart \n  will be displayed.");
            Button close = new Button("Close");
            close.setOnAction(e-> popupwindow.close());
            close.setAlignment(Pos.BASELINE_CENTER);
            VBox layout= new VBox(15);
            layout.setLayoutX(3);
            layout.getChildren().addAll(label1,label2,label3,label4,label5,close);
            layout.setAlignment(Pos.TOP_LEFT);
            Scene scene1= new Scene(layout, 350, 350);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();

        });

        top.setAlignment(Pos.CENTER);
        top.setLayoutX(10);
        top.setSpacing(5);
        top.getChildren().addAll(b1,b2,b3,b4,b5);
    return top;
    }
    // Onscreen Keyboard
    public GridPane addCustKeyboard(){
        GridPane keyboard = new GridPane();
        //  Onscreen Keyboard Buttons
        Button btn0= new Button("0");
        styleKeys(btn0);
        btn0.setOnAction(event -> fields[0].setText(fields[0].getText()+"0"));
        Button btn1= new Button("1");
        styleKeys(btn1);
        btn1.setOnAction(event -> fields[0].setText(fields[0].getText()+"1"));
        Button btn2= new Button("2");
        styleKeys(btn2);
        btn2.setOnAction(event -> fields[0].setText(fields[0].getText()+"2"));
        Button btn3= new Button("3");
        styleKeys(btn3);
        btn3.setOnAction(event -> fields[0].setText(fields[0].getText()+"3"));
        Button btn4= new Button("4");
        styleKeys(btn4);
        btn4.setOnAction(event -> fields[0].setText(fields[0].getText()+"4"));
        Button btn5= new Button("5");
        styleKeys(btn5);
        btn5.setOnAction(event -> fields[0].setText(fields[0].getText()+"5"));
        Button btn6= new Button("6");
        styleKeys(btn6);
        btn6.setOnAction(event -> fields[0].setText(fields[0].getText()+"6"));
        Button btn7= new Button("7");
        styleKeys(btn7);
        btn7.setOnAction(event -> fields[0].setText(fields[0].getText()+"7"));
        Button btn8= new Button("8");
        styleKeys(btn8);
        btn8.setOnAction(event -> fields[0].setText(fields[0].getText()+"8"));
        Button btn9= new Button("9");
        styleKeys(btn9);
        btn9.setOnAction(event -> fields[0].setText(fields[0].getText()+"9"));
        Button btndot = new Button(".");
        styleKeys(btndot);
        btndot.setOnAction(event -> fields[0].setText(fields[0].getText()+"."));
        Button delbtn = new Button("⬅");
        styleKeys(delbtn);
        delbtn.setOnAction(event -> fields[0].setText(fields[0].getText(0, fields[0].getLength()-1)));

        // Designing the layout of the onscreen keyboard
        keyboard.setLayoutX(600);
        keyboard.setLayoutY(130);
        keyboard.setHgap(10);
        keyboard.setVgap(10);
        keyboard.setCursor(Cursor.HAND);
        keyboard.setPrefSize(200,250);
        keyboard.setBackground(new Background(new BackgroundFill(Color.web("#99a8b0"),new CornerRadii(8),
                Insets.EMPTY)));
        keyboard.setPadding(new Insets(20, 20, 20, 30));
        keyboard.add(btn7,0,0);
        keyboard.add(btn8,1,0);
        keyboard.add(btn9,2,0);
        keyboard.add(btn4,0,1);
        keyboard.add(btn5,1,1);
        keyboard.add(btn6,2,1);
        keyboard.add(btn1,0,2);
        keyboard.add(btn2,1,2);
        keyboard.add(btn3,2,2);
        keyboard.add(btn0,0,3);
        keyboard.add(btndot,1,3);
        keyboard.add(delbtn,2,3);
        return keyboard;
    }
    //method to show the error when user fills in all the fields and click the calculate button
    public void showFullError(){
        Alert fullerror = new Alert(Alert.AlertType.ERROR);
        fullerror.setTitle("Error");
        fullerror.setHeaderText("All fields full !");
        fullerror.setContentText("Please leave the field you want to calculate empty.");
        fullerror.showAndWait();
    }
    //method to show Error alert when user leaves two or more fields empty or when user enters other characters
    public void showEmptyError(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText("Please leave only 1 field empty !");
        error.setContentText(" * also make sure you enter ONLY Numbers ");
        error.showAndWait();
    }
    //method to show Error when user leaves the interest field empty
    public void showInterestError() {
        Alert intError = new Alert(Alert.AlertType.ERROR);
        intError.setTitle("Error");
        intError.setHeaderText("Sorry, Interest calculation cannot be performed.");
        intError.setContentText("Do not leave the 'Interest' field Empty.");
        intError.showAndWait();
    }

    //method to calculate future value in the compound savings without payment
    public static String getsimpleFutureVal(double startprinciple ,double periods ,double interest){
        double futurevalue = startprinciple * (Math.pow((1+((interest/100)/12)),12*periods));
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
    //method to calculate Start Principle in the Compound Savings With Payment
    public static String getcompoundStartPrinciple(double futurevalue,double inte,double periods, double PMT){
        double interest = inte/100;
        double startprinciple = (futurevalue-(PMT*((Math.pow((1+(interest/12)),12*periods)-1)/
                                    (interest/12))))/(Math.pow((1+(interest/12)),12*periods));
        return String.valueOf(Math.round(startprinciple*100.0)/100.0);
    }
    //method to calculate Future Value in the Compound Savings with Payment
    public static String getcompoundFutureVal(double startprinciple,double inte,double periods,double PMT){
        double interest = inte/100;
        double futureval = (startprinciple*Math.pow((1+(interest/12)),(12*periods)))+
                            (PMT*((Math.pow((1+(interest/12)),(12*periods))-1)/(interest/12)));
        return String.valueOf(Math.round(futureval*100.0)/100.0);
    }
    //method to calculate No.ofPeriods in the Compound Savings with Payment
    public static String getcompoundPeriods(double startprinciple,double inte,double PMT,double futurevalue){
        double interest = inte/100;
        double periods = Math.log(((((interest*futurevalue)/12)+PMT)/(((startprinciple* interest)/12)+PMT)))/
                        (12*Math.log(1+(interest/12)));
        return String.valueOf(Math.round(periods*100.0)/100.0);
    }
    //method to calculate PMT in the compound Savings with Payment
    public static String getcompoundPMT(double startprinciple,double inte,double periods,double futureval){
        double interest = inte/100;
        double payment =   (futureval-(startprinciple*Math.pow((1+(interest/12)),(12*periods))))/
                           ((Math.pow((1+(interest/12)),(12*periods))-1)/(interest/12));
        return String.valueOf(Math.round(payment*100.0)/100.0);
    }
    //method to calculate monthly payment in the Mortgage Calculator
    public static String getmonthPay(double housep,double downpay,double term ,double inte){
        double interest = inte/100;
        double payment = ((housep-downpay)*(interest/12)*Math.pow((1+(interest/12)),12*term))/
                            (Math.pow((1+(interest/12)),12*term)-1);
        return  String.valueOf(Math.round(payment*100.0)/100.0);
    }
    //method to calculate downpayment in the mortgage calculator
    public static String getDownPay(double housep,double term,double monthpay,double inte){
        double interest = inte / 100 ;
        double payment = housep - ((12*monthpay*(Math.pow((1+(interest/12)),12*term)-1))/
                        (interest*Math.pow((1+(interest/12)),12*term)));
        return String.valueOf(Math.round(payment*100.0)/100.0);
    }
    //method to calculate no of terms in the mortgage calculator
    public static String getMorTerms(double housep, double downpay,double monthpay,double inte){
        double interest = inte / 100 ;
        double terms  =  (Math.log((monthpay/(monthpay-((interest/12)*(housep-downpay))))))/
                        (12*Math.log(1+(interest/12)));
        return String.valueOf(Math.round(terms*100.0)/100.0);
    }
    //method to calculate house price in the mortgage calculator
    public static String gethousePrice(double downpay, double monthpay,double terms,double inte) {
        double interest = inte / 100;
        double houseprice = downpay+((12*monthpay*(Math.pow((1+(interest/12)),(12*terms))-1))/
                                (interest*Math.pow((1+(interest/12)),(12*terms))));
        return String.valueOf(Math.round(houseprice * 100.0) / 100.0);
    }
    //method to calculate monthly payment in the loan calculator
    public static String getloanMonthpay(double amount ,double term ,double inte){
        double interest = inte / 100;
        double monthpay =  (amount * interest) / (1 - (1 / Math.pow((1 + interest), term)));
        return String.valueOf(Math.round(monthpay *100.0)/100.0);
    }
    //method to calculate loan amount in the loan calculator
    public static String getAmount(double monthpay,double term , double inte ){
        double interest = inte / 100 ;
        double amount = (monthpay / interest) * (1 - (1 / Math.pow((1 + interest), term)));
        return String.valueOf(Math.round(amount*100.0)/100.0);
    }
    //method to calculate loan terms in the loan calculator
    public static String getloanTerm(double monthpay,double amount, double inte ){
        double interest = inte / 100 ;
        double terms = Math.log((monthpay/interest)/((monthpay/interest)-amount))/Math.log(1 + interest);
        return String.valueOf(Math.round(terms*100.0)/100.0);
    }
    //method to show shadow effect and color when mouse hovers over button
    public void getHovereffect(Button bu) {
        DropShadow shadow = new DropShadow();
        bu.setEffect(shadow);
        bu.setStyle("-fx-text-fill: white");
        bu.setBackground(new Background(new BackgroundFill(Color.web("#084369"),new CornerRadii(7),
                Insets.EMPTY)));
    }
    //method to show effect when mouse doesn't hover over button
    public void getNormaleffect(Button bu){
        bu.setEffect(null);
        bu.setStyle("-fx-text-fill: black");
        bu.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(7),
                Insets.EMPTY)));
    }
    //to style all the calculate buttons
    public void styleCalculate(Button cal){
        cal.setPrefSize(170,40);
        cal.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(7),
                Insets.EMPTY)));
        cal.setFont(Font.font("Calibri",
                FontWeight.BOLD,
                FontPosture.REGULAR,
                20));
        cal.setCursor(Cursor.HAND);
        cal.setOnMouseEntered(e ->
                cal.setBackground(new Background(new BackgroundFill(Color.web("#29a64f"),new CornerRadii(7),
                        Insets.EMPTY))));
        cal.setOnMouseExited(e1 ->
                getNormaleffect(cal));

    }
    //to style all the labels
    public void styleLabels(Label lbl){
        lbl.setFont(Font.font("Calibri",
                FontWeight.SEMI_BOLD,
                FontPosture.REGULAR,
                18));
    }
    //to style all the headings
    public void styleHeadings(Label lbl){
        lbl.setFont(Font.font("Bahnschrift",
                FontWeight.BOLD,
                FontPosture.REGULAR,
                22));
        lbl.setTextFill(Color.web("#021c52"));

    }
    //method to add a piechart
    public PieChart getChart(double principle, double interest) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Start Principal", principle),
                        new PieChart.Data("Interest", interest));

        final PieChart chart = new PieChart(pieChartData);
        changeColors(pieChartData,"#0c2e70", "#00c4de");
        chart.setLegendVisible(false);
        chart.setLayoutX(540);
        chart.setLayoutY(370);
        chart.setPrefSize(370,350);
        return chart;
    }
    public void changeColors(
            ObservableList<PieChart.Data> pieChartData,
            String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle(
                    "-fx-pie-color:" + pieColors[i % pieColors.length] + ";"
            );
            i++;
        }
    }
    //method to style keys in the keyboard
    public void styleKeys(Button btn){
        btn.setFont(Font.font("Bahnschrift",
                FontWeight.BOLD,
                FontPosture.REGULAR,
                19));
        btn.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(7), BorderWidths.DEFAULT)));
        btn.setBackground(new Background(new BackgroundFill(Color.web("#c7e5f0"),new CornerRadii(7),
                Insets.EMPTY)));
        btn.setOnMouseEntered(e-> {
            btn.setTextFill(Color.web("#b6c6e0"));
            btn.setBackground(new Background(new BackgroundFill(Color.web("#2d384a"),new CornerRadii(7),
                    Insets.EMPTY)));

        });
        btn.setOnMouseExited(e1->{
            btn.setTextFill(Color.web("#29364a"));
            btn.setBackground(new Background(new BackgroundFill(Color.web("#c7e5f0"),new CornerRadii(7),
                    Insets.EMPTY)));
        });
    }
    //method to style clear buttons
    public void styleClear(Button clear){
        clear.setFont(Font.font("Bahnschrift",
                FontWeight.BOLD,
                FontPosture.REGULAR,
                14));
        clear.setPrefSize(75,25);
        clear.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(7),
                Insets.EMPTY)));
        clear.setOnMouseEntered(e-> {
            clear.setTextFill(Color.web("#ffff"));
            clear.setBackground(new Background(new BackgroundFill(Color.web("#e04646"),new CornerRadii(7),
                    Insets.EMPTY)));

        });
        clear.setOnMouseExited(e1->{
            clear.setTextFill(Color.BLACK);
            clear.setBackground(new Background(new BackgroundFill(Color.web("#d8f2ec"),new CornerRadii(7),
                    Insets.EMPTY)));
        });

    }

    public static void main(String[] args) {
        launch(args);

    }
}

