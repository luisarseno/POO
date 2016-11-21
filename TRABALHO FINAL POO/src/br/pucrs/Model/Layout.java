package br.pucrs.Model;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Layout extends Application{
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX - Leitura de Arquivos Trabalho final");
        final FileChooser fileChooser = new FileChooser();
        GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(60, 30, 60, 30));
        //grid.setGridLinesVisible(true);
        
        Text scenetitle = new Text("Lendo arquivos");
        scenetitle.setId("Lendo arquivos-text");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 55));
        grid.add(scenetitle, 2, 0, 2, 1);
        
        Label ArqCodigos = new Label("Arquivo de execução:");
        grid.add(ArqCodigos, 2, 1);
        
        TextField textFileExecucao = new TextField();
        grid.add(textFileExecucao, 2, 2, 2, 1);
        
        Button btn = new Button("Procurar");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 4, 2);

        
        Label ArqSimulacao = new Label("Arquivo de simulaçao:");
        grid.add(ArqSimulacao, 2, 3);
        
        TextField textFileSimulacao = new TextField();
        grid.add(textFileSimulacao, 2, 4, 2, 1);
        
        Button btn2 = new Button("Procurar");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 4, 4);
        
        Button confirmar = new Button("Confirmar");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn3.getChildren().add(confirmar);
        grid.add(hbBtn3, 4, 6);
        
        Label mensagem = new Label("Resultados:");
        grid.add(mensagem, 0, 8);
        
        TextArea txtA = new TextArea();
        grid.add(txtA, 0, 9, 14 ,55);

        btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(primaryStage);
                        if(file.exists()){
                            textFileExecucao.setText(file.getAbsolutePath());

                        }
                    }
                });
        btn2.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file  = fileChooser.showOpenDialog(primaryStage);
                        if(file.exists()){
                            textFileSimulacao.setText(file.getAbsolutePath());

                        }
                    }
                });

        confirmar.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File fileExecucao = new File(textFileExecucao.getText());
                        File fileSimulacao = new File(textFileSimulacao.getText());
                        Experimento experimento = new Experimento(fileExecucao, fileSimulacao);
                        txtA.clear();
                        txtA.appendText(experimento.lerArquivoExecucao());
                        txtA.appendText("\n\n\n"+experimento.lerArquivoSimulacao());
                    }
                });
        
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

