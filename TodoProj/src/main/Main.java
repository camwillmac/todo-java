package main;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	public TextArea text = new TextArea();
	public ArrayList<Todo> tl = new ArrayList<>();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage){
		text.setEditable(false);
		BorderPane bp = new BorderPane();
		bp.setPrefSize(400, 300);
		HBox menu = new HBox(10);
		bp.setTop(menu);
		menu.setPadding(new Insets(20, 20, 20, 20));
		menu.setAlignment(Pos.CENTER);
		Button mAdd = new Button("Adding");
		Button mView = new Button("View");
		Button mRemove = new Button("Remove");
		Button mData = new Button("Data");
		menu.getChildren().addAll(mAdd, mView, mRemove, mData);
		
		VBox addV = new VBox(10);
		
		TextField nf = new TextField();
		HBox nb = new HBox(5);
		nb.getChildren().addAll(new Label("Name: "), nf);
		addV.getChildren().add(nb);
		
		TextField df = new TextField();
		HBox db= new HBox(5);
		db.getChildren().addAll(new Label("Decription: "), df);
		addV.getChildren().add(db);
		
		Button addB = new Button("Add");
		addV.getChildren().add(addB);
		Label aStat = new Label("Status: Pending");
		addV.getChildren().add(aStat);
		
		
		VBox remv = new VBox(10);
		TextField toR = new TextField();
		remv.getChildren().add(toR);
		
		Button byNum = new Button("Rmv num");
		
		Label rStat = new Label("Status: pending");
		remv.getChildren().addAll(byNum, rStat);
		
		HBox filing = new HBox(20);
		Button save = new Button("Save");
		Button load = new Button("Load");
		Button clear = new Button("Clear");
		filing.setAlignment(Pos.CENTER);
		filing.getChildren().addAll(save, load, clear);
		
		mData.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				bp.setCenter(filing);
			}
		});
		mAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				bp.setCenter(addV);
			}
		});
		mView.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				updateText();
				bp.setCenter(text);
			}
		});
		mRemove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				bp.setCenter(remv);
			}
		});
		
		addB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tl.add(new Todo(nf.getText(), df.getText()));
				aStat.setText("'"+nf.getText() +"'  added!");
				nf.clear();
				df.clear();
			}
		});
		
		byNum.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int num=Integer.parseInt(toR.getText());
				rStat.setText("Status: removed "+num+": "+tl.get(num).name);
				tl.remove(num);
				toR.clear();
			}
		});

		save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					PrintWriter writer = new PrintWriter("data.txt");
					updateText();
					writer.print(text.getText());
					writer.close();
				} catch (Exception err) {
					System.out.println("Error: "+err);
				}
			}
		});
		load.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					Scanner scn = new Scanner(Paths.get("data.txt"));
					while (scn.hasNextLine()) {
						String[] pts = scn.nextLine().split(", ");
						tl.add(new Todo(pts[1], pts[2]));
					}
					scn.close();
				}
				catch (Exception e2) {
					System.out.println("Error: "+e2);
				}
			}
		});
		clear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tl.clear();
			}
		});
		
		stage.setScene(new Scene(bp));
		stage.setTitle("Todo program");
		stage.show();
	}
	
	public void updateText() {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<tl.size();i++) {
			builder.append(i+", "+tl.get(i).name+", "+tl.get(i).desc);
			if (i<tl.size()-1) {
				builder.append("\n");
			}
		}
		text.setText(builder.toString());
	}

}
