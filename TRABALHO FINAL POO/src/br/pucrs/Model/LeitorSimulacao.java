package br.pucrs.Model;

import br.pucrs.Exceptions.LeitorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Santana on 11/20/2016.
 */
public class LeitorSimulacao {
    private String nomeExperimento;
    private int numEventos;
    private ArrayList<Eventos> arrayEventos;

    public LeitorSimulacao() {
        this.arrayEventos = new ArrayList<Eventos>();
    }

    public String getNomeExperimento() {
        return nomeExperimento;
    }

    public ArrayList<Eventos> getArrayEventos() {
        return arrayEventos;
    }

    public void lerArquivo(File arquivo) throws LeitorException {
        Path path = Paths.get(arquivo.getAbsolutePath());
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha = null;
            Scanner sc;
            while ((linha = br.readLine()) != null) {
                if (linha.contains("Experimento")) {
                    this.nomeExperimento = linha.substring(linha.lastIndexOf(":") + 1, linha.length());
                } else if (linha.contains("Eventos")) {
                    this.numEventos = Integer.valueOf(linha.substring(linha.lastIndexOf(":") + 1, linha.length()).trim());
                    for (int i = 0; i < this.numEventos; i++) {
                        linha = br.readLine();
                        if(linha == null){
                            throw new LeitorException("Eventos insuficientes");
                        }
                        sc = new Scanner(linha).useDelimiter(";");
                        this.arrayEventos.add(new Eventos(sc.next().trim(), sc.next().trim()));
                    }
                }
            }
        } catch ( IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }
}
