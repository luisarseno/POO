package br.pucrs.Model;

import br.pucrs.Exceptions.LeitorException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Santana on 11/20/2016.
 */
public class LeitorExecucao {

    private String nomeExperimento;
    private int tempoSimulacao;
    private Central central;
    private ArrayList<Antena> arrayAntenas;
    private ArrayList<Telefone> arrayTelefone;

    public LeitorExecucao() {
        this.arrayAntenas = new ArrayList<Antena>();
        this.arrayTelefone = new ArrayList<Telefone>();
    }

    public Central getCentral() {
        return central;
    }

    public ArrayList<Antena> getArrayAntenas() {
        return arrayAntenas;
    }

    public ArrayList<Telefone> getArrayTelefone() {
        return arrayTelefone;
    }

    public void lerArquivo(File arquivo) throws LeitorException {
        Path path = Paths.get(arquivo.getAbsolutePath());
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha = null;
            int numeroLinha = 0;
            Scanner sc;
            String aux = "";
            while ((linha = br.readLine()) != null) {
                if (linha.contains("Experimento")) {
                    this.nomeExperimento = linha.substring(linha.lastIndexOf(":") + 1, linha.length());
                } else if (linha.contains("Tempo")) {
                    this.tempoSimulacao = Integer.valueOf(linha.substring(linha.lastIndexOf(":") + 1, linha.length()).trim());
                } else if (linha.contains("Central")) {
                    aux = linha.substring(linha.lastIndexOf(":") + 1, linha.length()).trim();
                    sc = new Scanner(aux).useDelimiter(";");
                    this.central = new Central(sc.next(), sc.nextInt(), new Intervalo(1, 1));
                    if(this.central == null){
                        throw new LeitorException("Erro ao pegar a central, verificar linha");
                    }
                } else if (linha.contains("Antena")) {
                    int numAntenas = Integer.valueOf(linha.substring(linha.lastIndexOf(":") + 1, linha.length()).trim());
                    for (int i = 0; i < numAntenas; i++) {
                        linha = br.readLine();
                        if(linha == null || linha.contains(":")){
                            throw new LeitorException("Antenas insuficientes");
                        }
                        sc = new Scanner(linha).useDelimiter(";");
                        this.arrayAntenas.add(new Antena(sc.next(), sc.nextInt(), new Intervalo(1, 1)));
                    }
                } else if (linha.contains("Telefone")) {
                    aux = linha.substring(linha.lastIndexOf(":") + 1, linha.length()).trim();
                    sc = new Scanner(aux).useDelimiter(";");
                    int numTelefones = sc.nextInt();
                    for (int i = 0; i < numTelefones; i++) {
                        linha = br.readLine();
                        if(linha == null){
                            throw new LeitorException("Telefones insuficientes");
                        }
                        sc = new Scanner(linha).useDelimiter(";");
                        Antena auxAntena = null;
                        String nome = sc.next();
                        String antena = sc.next().trim();
                        for (Antena a : this.arrayAntenas) {
                            if (a.getIdent().equals(antena)) {
                                auxAntena = a;
                                break;
                            }
                        }
                        if (auxAntena == null) {
                            throw new LeitorException("Antena: " + antena + " não existe, vefique o arquivo");
                        }
                        this.arrayTelefone.add(new Telefone(nome, auxAntena));
                    }
                } else {
                    throw new LeitorException("Arquivo invãlido para o sistema");
                }
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }



    public String getNomeExperimento() {
        return nomeExperimento;
    }

    public int getTempoSimulacao() {
        return tempoSimulacao;
    }
}
