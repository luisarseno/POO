package br.pucrs.View;/**
 * Created by Santana on 11/19/2016.
 */

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;

public class Principal extends Application {

    private TextField nomeArquivoSimulacaoTextField;
    private TextField nomeArquivoEventosTextField;
    private FileChooser fileChooser;
    private JButton exeBut;
    private JTextField textArqEx;
    private JButton simBut;
    private JTextField textArqSim;
    private JLabel trabalhoPOOLabel;
    private JButton confirmarButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        JPanel painel = new JPanel();
        painel.add(exeBut);
        painel.add(textArqEx);
        painel.add(simBut);
        painel.add(textArqSim);
        painel.add(trabalhoPOOLabel);
        painel.add(confirmarButton);
        painel.add(confirmarButton);
        JFrame janela = new JFrame("Argentum");
        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);
    }


    // Leitura do arquivo


}