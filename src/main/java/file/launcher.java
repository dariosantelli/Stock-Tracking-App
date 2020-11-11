package file;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.Monthly;
import org.patriques.output.timeseries.Weekly;
import org.patriques.output.timeseries.data.StockData;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class launcher extends Application {
	
	//Tile status variables (0 == tile not in use, 1 == tile is activated)
	boolean tileZeroStatus = false;
    boolean tileOneStatus = false;
    boolean tileTwoStatus = false;
    boolean tileThreeStatus = false;
    boolean tileFourStatus = false;
    boolean tileFiveStatus = false;
	
    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {

        //Initialize Tab Pane
        //--------------------------------------------
        TabPane tabPane = new TabPane();
        
        //Set actual tab height and width
        tabPane.setTabMinHeight(25);
        tabPane.setTabMinWidth(275);
        
        Tab tabone = new Tab("Price Tracker");
        Tab tabtwo = new Tab("Stock Lookup"); 
        
        tabPane.getTabs().add(tabone);
        tabPane.getTabs().add(tabtwo);
        //--------------------------------------------
        
        //Initialize scene
        //--------------------------------------------
        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox, 610, 400);
        scene.getStylesheets().add(
        	      getClass().getResource("style.css").toExternalForm()
        	    );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Stock App");
        primaryStage.setResizable(false);
        primaryStage.show();
        //--------------------------------------------
        
        //Second tab stuff
        //--------------------------------------------
      //Set up layout structure to be a centered grid
        GridPane grid = new GridPane();
        grid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        
        //Declare dynamic elements
        Text datetimeelement = new Text("");  
        Text openelement = new Text("");
        Text highelement = new Text("");
        Text lowelement = new Text("");
        Text closeelement = new Text("");
        
        //Text fields
        TextField tf = new TextField();
        
        //Combobox
        String test[] = {"1", "2", "3", "5", "14"};
        ComboBox<String> cb = new ComboBox(FXCollections.observableArrayList(test));
        
        
        //Set up start button functionality
        Button btn = new Button();
        btn.getStyleClass().add("button");
        btn.setText("Submit");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                //updater.main(null);
            	
            	String tfs = tf.getText();
            	int cbi = Integer.parseInt(cb.getValue());
            	mainone q = new mainone();
            	IntraDay sto = q.oneMinute(tfs);
            	
            	System.out.println(cbi);
            	
            	List<StockData> stockData = sto.getStockData();
            	
            	//Needed to format dateTime into a string
            	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            	
            	
            	LocalDateTime stockDatazerodt = stockData.get(0).getDateTime();
            	
            	//Assign received values to variables
            	String stockDatazeroopen = Double.toString(stockData.get(0).getOpen());
            	String stockDatazerohigh = Double.toString(stockData.get(0).getHigh());
            	String stockDatazerolow = Double.toString(stockData.get(0).getLow());
            	String stockDatazeroclose = Double.toString(stockData.get(0).getClose());
            	String formattedDateTime = stockDatazerodt.format(formatter); //Date and time of value
      	      
                //System.out.println(datetimeelement.getText());
                //t.setText(t.getText()+"f");
                datetimeelement.setText(formattedDateTime);
                openelement.setText(stockDatazeroopen);
                highelement.setText(stockDatazerohigh);
                lowelement.setText(stockDatazerolow);
                closeelement.setText(stockDatazeroclose);
                

            }
        });
        

        tabtwo.setContent(grid);
        //Set up GUI labels
        Label tickerlabel = new Label("Enter Ticker Name:");
        tickerlabel.setFont(Font.font("Arial", 16));
        
        Label quantlabel = new Label("Select Number of Results to Display:");
        quantlabel.setFont(Font.font("Arial", 16));
        
        Label datelabel = new Label("Date:");
        datelabel.setFont(Font.font("Arial", 16));
        
        Label openlabel = new Label("Open Price:");
        openlabel.setFont(Font.font("Arial", 16));
        
        Label highlabel = new Label("High Price:");
        highlabel.setFont(Font.font("Arial", 16));
        
        Label lowlabel = new Label("Low Price:");
        lowlabel.setFont(Font.font("Arial", 16));
        
        Label closelabel = new Label("Close Price:");
        closelabel.setFont(Font.font("Arial", 16));
        
        //Add elements to GUI
        grid.add(tickerlabel, 0, 0);
        grid.add(tf, 1, 0);
        grid.add(quantlabel, 2, 0);
        grid.add(cb, 3, 0);
        grid.add(btn, 4, 0);
        grid.add(datelabel , 0, 1);
        grid.add(datetimeelement, 0, 2);
        grid.add(openlabel , 1, 1);
        grid.add(openelement, 1, 2);
        grid.add(highlabel , 2, 1);
        grid.add(highelement, 2, 2);
        grid.add(lowlabel , 3, 1);
        grid.add(lowelement, 3, 2);
        grid.add(closelabel , 4, 1);
        grid.add(closeelement, 4, 2);
        

        //--------------------------------------------
        
        //STOCK PRICE TAB
        // * ----------------------------------------------------------------
        // * ----------------------------------------------------------------
        
        //ADD TICKER BUTTONS-------------------------------------------------
        Button tileZeroAdd = new Button();
        tileZeroAdd.setText("Submit");
        
        Button tileOneAdd = new Button();
        tileOneAdd.setText("Submit");
        
        Button tileTwoAdd = new Button();
        tileTwoAdd.setText("Submit");
        
        Button tileThreeAdd = new Button();
        tileThreeAdd.setText("Submit");
        
        Button tileFourAdd = new Button();
        tileFourAdd.setText("Submit");
        
        Button tileFiveAdd = new Button();
        tileFiveAdd.setText("Submit");
        //-----------------------------------------------------------------
        
        //REMOVE TICKER BUTTONS-------------------------------------------------
        Button tileZeroRemove = new Button();
        tileZeroRemove.setText("X");
        
        Button tileOneRemove = new Button();
        tileOneRemove.setText("X");
        
        Button tileTwoRemove = new Button();
        tileTwoRemove.setText("X");
        
        Button tileThreeRemove = new Button();
        tileThreeRemove.setText("X");
        
        Button tileFourRemove = new Button();
        tileFourRemove.setText("X");
        
        Button tileFiveRemove = new Button();
        tileFiveRemove.setText("X");
        //-----------------------------------------------------------------
        
        //Timeframe selection dropdown
        String timeframeOptions[] = {"1 Minute", "15 Minutes", "30 Minutes", "1 Hour", "1 Day", "1 Week", "1 Month"};
        ComboBox<String> dropdown = new ComboBox(FXCollections.observableArrayList(timeframeOptions));
        dropdown.getSelectionModel().select(3);

        
        //Initialize TextFields
        TextField tileZeroInput = new TextField();
        TextField tileOneInput = new TextField();
        TextField tileTwoInput = new TextField();
        TextField tileThreeInput = new TextField();
        TextField tileFourInput = new TextField();
        TextField tileFiveInput = new TextField();
        
        //Initialize Added Ticker Labels
        Label tickerLabelZero = new Label("");
        Label tickerLabelOne = new Label("");
        Label tickerLabelTwo = new Label("");
        Label tickerLabelThree = new Label("");
        Label tickerLabelFour = new Label("");
        Label tickerLabelFive = new Label("");
        
        //Initialize Close Price Labels
        Label tileZeroClose = new Label("");
        Label tileOneClose = new Label("");
        Label tileTwoClose = new Label("");
        Label tileThreeClose = new Label("");
        Label tileFourClose = new Label("");
        Label tileFiveClose = new Label("");
        
        //Initialize Percent Difference Labels
        Label tileZeroPercent = new Label("");
        Label tileOnePercent = new Label("");
        Label tileTwoPercent = new Label("");
        Label tileThreePercent = new Label("");
        Label tileFourPercent = new Label("");
        Label tileFivePercent = new Label("");
        
        //Initialize gridpane within tab one
        GridPane pricegrid = new GridPane();
        pricegrid.setHgap(10);
        pricegrid.setVgap(5);
        pricegrid.setPrefWidth(800);
        pricegrid.setMinWidth(800);
        pricegrid.setMaxWidth(800);

        //Set column widths
        pricegrid.getColumnConstraints().add(new ColumnConstraints(10)); // column 0
        pricegrid.getColumnConstraints().add(new ColumnConstraints(110)); // column 1
        pricegrid.getColumnConstraints().add(new ColumnConstraints(65)); // column 2
        pricegrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 3
        pricegrid.getColumnConstraints().add(new ColumnConstraints(0)); // column 4
        pricegrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 5
        pricegrid.getColumnConstraints().add(new ColumnConstraints(0)); // column 6
        pricegrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 7
        
        //Set row heights
        pricegrid.getRowConstraints().add(new RowConstraints(5)); // row 0
        pricegrid.getRowConstraints().add(new RowConstraints(25)); // row 1
        pricegrid.getRowConstraints().add(new RowConstraints(25)); // row 2
        pricegrid.getRowConstraints().add(new RowConstraints(10)); // row 3
        pricegrid.getRowConstraints().add(new RowConstraints(25)); // row 4
        pricegrid.getRowConstraints().add(new RowConstraints(20)); // row 5
        pricegrid.getRowConstraints().add(new RowConstraints(20)); // row 6
        pricegrid.getRowConstraints().add(new RowConstraints(20)); // row 7
        pricegrid.getRowConstraints().add(new RowConstraints(20)); // row 8
        pricegrid.getRowConstraints().add(new RowConstraints(25)); // row 9
        pricegrid.getRowConstraints().add(new RowConstraints(20)); // row 10
        
        //Add TextFields and Add Buttons to each Tile
        pricegrid.add(tileZeroInput, 3, 4);
        pricegrid.add(tileZeroAdd, 3, 5);
        GridPane.setHalignment(tileZeroAdd, HPos.CENTER);
        
        pricegrid.add(tileOneInput, 5, 4);
        pricegrid.add(tileOneAdd, 5, 5);
        GridPane.setHalignment(tileOneAdd, HPos.CENTER);
        
        pricegrid.add(tileTwoInput, 7, 4);
        pricegrid.add(tileTwoAdd, 7, 5);
        GridPane.setHalignment(tileTwoAdd, HPos.CENTER);
        
        pricegrid.add(tileThreeInput, 3, 9);
        pricegrid.add(tileThreeAdd, 3, 10);
        GridPane.setHalignment(tileThreeAdd, HPos.CENTER);
        
        pricegrid.add(tileFourInput, 5, 9);
        pricegrid.add(tileFourAdd, 5, 10);
        GridPane.setHalignment(tileFourAdd, HPos.CENTER);
        
        pricegrid.add(tileFiveInput, 7, 9);
        pricegrid.add(tileFiveAdd, 7, 10);
        GridPane.setHalignment(tileFiveAdd, HPos.CENTER);

        
        //Add timeframe label
        pricegrid.add(dropdown, 1, 2);
        Label drop = new Label("Change Timeframe");
        
        
        //Refresh button
        Button timeframeRefresh = new Button();
        timeframeRefresh.setText("Refresh");
        timeframeRefresh.setOnAction(new EventHandler<ActionEvent>() {
         	 
           @Override
           public void handle(ActionEvent event) {
        	   //if tile one is enabled through boolean switching, refresh. Another if statement for each tile
        	   if (tileZeroStatus == true) {
        		   tileZeroRemove.fire();
            	   tileZeroAdd.fire();
        	   		}
        	   if (tileOneStatus == true) {
        		   tileOneRemove.fire();
            	   tileOneAdd.fire();
        	   		}
        	   if (tileTwoStatus == true) {
        		   tileTwoRemove.fire();
            	   tileTwoAdd.fire();
        	   		}
           		}
       		}
        );
        
        
        pricegrid.add(drop, 1, 1);
        pricegrid.add(timeframeRefresh, 2, 2);
        mainone APIconnect = new mainone();
        tabone.setContent(pricegrid);
        
        tileZeroAdd.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	//Remove textfield and submit button and toggle status
                pricegrid.getChildren().remove(tileZeroAdd);
                pricegrid.getChildren().remove(tileZeroInput);
                tileZeroStatus = true;
                
                //Initialize stock objects
                IntraDay stockObjectIntra = null;
                Daily stockObjectDaily = null;
                Weekly stockObjectWeekly = null;
                Monthly stockObjectMonthly = null;
                List<StockData> stockData = null;
            	Map<String, String> metaData = null;
                
            	//Select data timeframe based on dropdown selection
            	if (dropdown.getValue()=="1 Minute") {
            		stockObjectIntra = APIconnect.oneMinute(tileZeroInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="15 Minutes") {
            		stockObjectIntra = APIconnect.fifteenMinutes(tileZeroInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="30 Minutes") {
            		stockObjectIntra = APIconnect.thirtyMinutes(tileZeroInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="1 Hour") {
            		stockObjectIntra = APIconnect.oneHour(tileZeroInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="1 Day") {
            		stockObjectDaily = APIconnect.oneDay(tileZeroInput.getText());
            		stockData = stockObjectDaily.getStockData();
                	metaData = stockObjectDaily.getMetaData();
            	} else if (dropdown.getValue()=="1 Week") {
            		stockObjectWeekly = APIconnect.oneWeek(tileZeroInput.getText());
            		stockData = stockObjectWeekly.getStockData();
                	metaData = stockObjectWeekly.getMetaData();
            	} else if (dropdown.getValue()=="1 Month") {
            		stockObjectMonthly = APIconnect.oneMonth(tileZeroInput.getText());
            		stockData = stockObjectMonthly.getStockData();
                	metaData = stockObjectMonthly.getMetaData();
            	}
                        	
   
            	//Assign received values to variables
            	String stockDatazeroclose = new DecimalFormat("####.##").format(stockData.get(0).getClose());
            	String priceDifference = new DecimalFormat("#.##").format(((stockData.get(0).getClose()-stockData.get(1).getClose())/stockData.get(1).getClose())*100);
            	
            	
            	//Add a '+' to the price difference if price goes up
            	if (Float.parseFloat(priceDifference) > 0) {
            		tileZeroPercent.setText("+"+priceDifference+"%");
            	} else {
            		tileZeroPercent.setText(priceDifference+"%");
            	}
           
            	
            	tickerLabelZero.setText(metaData.get("2. Symbol"));
                tileZeroClose.setText("$"+stockDatazeroclose);
                
                //Add tile zero elements
                pricegrid.add(tickerLabelZero, 3, 4);
                GridPane.setHalignment(tickerLabelZero, HPos.CENTER);
                pricegrid.add(tileZeroClose, 3, 5);
                GridPane.setHalignment(tileZeroClose, HPos.CENTER);
                pricegrid.add(tileZeroPercent, 3, 6);
                GridPane.setHalignment(tileZeroPercent, HPos.CENTER);
                pricegrid.add(tileZeroRemove, 3, 7);
                GridPane.setHalignment(tileZeroRemove, HPos.CENTER);
                }
        	}
    	);
        
        tileZeroRemove.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            	pricegrid.getChildren().remove(tickerLabelZero);
            	pricegrid.getChildren().remove(tileZeroClose);
            	pricegrid.getChildren().remove(tileZeroPercent);
                pricegrid.getChildren().remove(tileZeroRemove);
            	
            	pricegrid.add(tileZeroInput, 3, 4);
                pricegrid.add(tileZeroAdd, 3, 5);
                
                tileZeroStatus = false;
                }
        	}
    	);
        

        tileOneAdd.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	//Remove textfield and submit button and toggle status
                pricegrid.getChildren().remove(tileOneAdd);
                pricegrid.getChildren().remove(tileOneInput);
                tileOneStatus = true;
                
                //Initialize stock objects
                IntraDay stockObjectIntra = null;
                Daily stockObjectDaily = null;
                Weekly stockObjectWeekly = null;
                Monthly stockObjectMonthly = null;
                List<StockData> stockData = null;
            	Map<String, String> metaData = null;
                
            	//Select data timeframe based on dropdown selection
            	if (dropdown.getValue()=="1 Minute") {
            		stockObjectIntra = APIconnect.oneMinute(tileOneInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="15 Minutes") {
            		stockObjectIntra = APIconnect.fifteenMinutes(tileOneInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="30 Minutes") {
            		stockObjectIntra = APIconnect.thirtyMinutes(tileOneInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="1 Hour") {
            		stockObjectIntra = APIconnect.oneHour(tileOneInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="1 Day") {
            		stockObjectDaily = APIconnect.oneDay(tileOneInput.getText());
            		stockData = stockObjectDaily.getStockData();
                	metaData = stockObjectDaily.getMetaData();
            	} else if (dropdown.getValue()=="1 Week") {
            		stockObjectWeekly = APIconnect.oneWeek(tileOneInput.getText());
            		stockData = stockObjectWeekly.getStockData();
                	metaData = stockObjectWeekly.getMetaData();
            	} else if (dropdown.getValue()=="1 Month") {
            		stockObjectMonthly = APIconnect.oneMonth(tileOneInput.getText());
            		stockData = stockObjectMonthly.getStockData();
                	metaData = stockObjectMonthly.getMetaData();
            	}
                        	
   
            	//Assign received values to variables
            	String stockDataOneClose = new DecimalFormat("####.##").format(stockData.get(0).getClose());
            	String priceDifference = new DecimalFormat("#.##").format(((stockData.get(0).getClose()-stockData.get(1).getClose())/stockData.get(1).getClose())*100);
            	
            	
            	//Add a '+' to the price difference if price goes up
            	if (Float.parseFloat(priceDifference) > 0) {
            		tileOnePercent.setText("+"+priceDifference+"%");
            	} else {
            		tileOnePercent.setText(priceDifference+"%");
            	}
           
            	
            	tickerLabelOne.setText(metaData.get("2. Symbol"));
                tileOneClose.setText("$"+stockDataOneClose);
                
                //Add tile zero elements
                pricegrid.add(tickerLabelOne, 5, 4);
                GridPane.setHalignment(tickerLabelOne, HPos.CENTER);
                pricegrid.add(tileOneClose, 5, 5);
                GridPane.setHalignment(tileOneClose, HPos.CENTER);
                pricegrid.add(tileOnePercent, 5, 6);
                GridPane.setHalignment(tileOnePercent, HPos.CENTER);
                pricegrid.add(tileOneRemove, 5, 7);
                GridPane.setHalignment(tileOneRemove, HPos.CENTER);
                }
        	}
    	);
        
        tileOneRemove.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            	pricegrid.getChildren().remove(tickerLabelOne);
            	pricegrid.getChildren().remove(tileOneClose);
            	pricegrid.getChildren().remove(tileOnePercent);
                pricegrid.getChildren().remove(tileOneRemove);
            	
            	pricegrid.add(tileOneInput, 5, 4);
                pricegrid.add(tileOneAdd, 5, 5);
                
                tileOneStatus = false;
                }
        	}
    	);
        
        tileTwoAdd.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	//Remove textfield and submit button and toggle status
                pricegrid.getChildren().remove(tileTwoAdd);
                pricegrid.getChildren().remove(tileTwoInput);
                tileTwoStatus = true;
                
                //Initialize stock objects
                IntraDay stockObjectIntra = null;
                Daily stockObjectDaily = null;
                Weekly stockObjectWeekly = null;
                Monthly stockObjectMonthly = null;
                List<StockData> stockData = null;
            	Map<String, String> metaData = null;
                
            	//Select data timeframe based on dropdown selection
            	if (dropdown.getValue()=="1 Minute") {
            		stockObjectIntra = APIconnect.oneMinute(tileTwoInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="15 Minutes") {
            		stockObjectIntra = APIconnect.fifteenMinutes(tileTwoInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="30 Minutes") {
            		stockObjectIntra = APIconnect.thirtyMinutes(tileTwoInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="1 Hour") {
            		stockObjectIntra = APIconnect.oneHour(tileTwoInput.getText());
            		stockData = stockObjectIntra.getStockData();
                	metaData = stockObjectIntra.getMetaData();
            	} else if (dropdown.getValue()=="1 Day") {
            		stockObjectDaily = APIconnect.oneDay(tileTwoInput.getText());
            		stockData = stockObjectDaily.getStockData();
                	metaData = stockObjectDaily.getMetaData();
            	} else if (dropdown.getValue()=="1 Week") {
            		stockObjectWeekly = APIconnect.oneWeek(tileTwoInput.getText());
            		stockData = stockObjectWeekly.getStockData();
                	metaData = stockObjectWeekly.getMetaData();
            	} else if (dropdown.getValue()=="1 Month") {
            		stockObjectMonthly = APIconnect.oneMonth(tileTwoInput.getText());
            		stockData = stockObjectMonthly.getStockData();
                	metaData = stockObjectMonthly.getMetaData();
            	}
                        	
   
            	//Assign received values to variables
            	String stockDataTwoClose = new DecimalFormat("####.##").format(stockData.get(0).getClose());
            	String priceDifference = new DecimalFormat("#.##").format(((stockData.get(0).getClose()-stockData.get(1).getClose())/stockData.get(1).getClose())*100);
            	
            	
            	//Add a '+' to the price difference if price goes up
            	if (Float.parseFloat(priceDifference) > 0) {
            		tileTwoPercent.setText("+"+priceDifference+"%");
            	} else {
            		tileTwoPercent.setText(priceDifference+"%");
            	}
           
            	
            	tickerLabelTwo.setText(metaData.get("2. Symbol"));
                tileTwoClose.setText("$"+stockDataTwoClose);
                
                //Add tile zero elements
                pricegrid.add(tickerLabelTwo, 7, 4);
                GridPane.setHalignment(tickerLabelTwo, HPos.CENTER);
                pricegrid.add(tileTwoClose, 7, 5);
                GridPane.setHalignment(tileTwoClose, HPos.CENTER);
                pricegrid.add(tileTwoPercent, 7, 6);
                GridPane.setHalignment(tileTwoPercent, HPos.CENTER);
                pricegrid.add(tileTwoRemove, 7, 7);
                GridPane.setHalignment(tileTwoRemove, HPos.CENTER);
                }
        	}
    	);
        
        tileTwoRemove.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            	pricegrid.getChildren().remove(tickerLabelTwo);
            	pricegrid.getChildren().remove(tileTwoClose);
            	pricegrid.getChildren().remove(tileTwoPercent);
                pricegrid.getChildren().remove(tileTwoRemove);
            	
            	pricegrid.add(tileTwoInput, 7, 4);
                pricegrid.add(tileTwoAdd, 7, 5);
                
                tileTwoStatus = false;
                }
        	}
    	);
        // * ----------------------------------------------------------------
        // * ----------------------------------------------------------------

    }
}
