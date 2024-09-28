package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javafx.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javafx.scene.layout.HBox;

public class UI extends Application{
	@Override
	public void start(Stage stage) { // sets stage
		stage.setTitle("UI Menu");
		HBox hbox = new HBox();
		
		Scene scene = new Scene(hbox, 500, 400); //important: adds hbox to scene
		Insets insets = null;
		TextField textfield = new TextField();		
		
		// Menu options
		MenuItem menu1 = new MenuItem("Date & Time");
		MenuItem menu2 = new MenuItem("Export Text");		
		MenuItem menu3 = new MenuItem("Change Background Color");		
		MenuItem menu4 = new MenuItem("Exit");
		
		
		// Event Handlers
		menu1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				textfield.setText(formatter.format(now));
			};
		});
		menu2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// write to a file
				String fileName = "log.txt";
				try { 
					// get text from textfield
					String content = textfield.getText(); // getText() is key here
					
					PrintWriter outputStream = new PrintWriter(fileName);
					outputStream.println("Here's your info: " + content); // stores in RAM first, so you must close it
					outputStream.close(); // flushes data to file
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		menu3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				// generate random green hue - this took a very long time to figure out
				Random rand = new Random();
				int green = rand.nextInt(256);
				hbox.setStyle("-fx-background-color: rgb(0, " + green + ", 0);");
			}
		});
		menu4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			};
		});
		
		MenuButton menuButton = new MenuButton("Options", null, menu1, menu2, menu3, menu4);
		
		// important step - add components to HBox layout
		hbox.getChildren().addAll(menuButton, textfield);
		
		// padding & margins
		insets = new Insets(20, 20, 20, 20);
		HBox.setMargin(menuButton, insets);
		HBox.setMargin(textfield, insets);
		
		// Add scene to stage
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args); // launch application
	}
}
