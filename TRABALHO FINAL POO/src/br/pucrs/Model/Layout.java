package br.pucrs.Model;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Layout extends Application{
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX - Leitura de Arquivos Trabalho final");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(80, 80, 80, 80));
        //grid.setGridLinesVisible(true);
        
        Text scenetitle = new Text("Lendo arquivos");
        scenetitle.setId("Lendo arquivos-text");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 55));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        Label ArqCodigos = new Label("Arquivo de codigos.zip:");
        grid.add(ArqCodigos, 0, 1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField, 0, 2, 2, 1);
        
        Button btn = new Button("Procurar");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 2);

        
        Label ArqSimulacao = new Label("Arquivo de simulaçao:");
        grid.add(ArqSimulacao, 0, 3);
        
        TextField userTextField1 = new TextField();
        grid.add(userTextField1, 0, 4, 2, 1);
        
        Button btn2 = new Button("Procurar");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 2, 4);
        
        Button btn3 = new Button("Confirmar");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 2, 6);
        
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

