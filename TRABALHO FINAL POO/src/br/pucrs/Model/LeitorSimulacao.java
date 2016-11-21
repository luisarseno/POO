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
    private ArrayList<Mensagem> arrayMensagem;
    private ArrayList<Telefone> arrayTelefone;

    public LeitorSimulacao(ArrayList telefones) {
        this.arrayMensagem = new ArrayList<Mensagem>();
        this.arrayTelefone = telefones;
    }

    public String getNomeExperimento() {
        return nomeExperimento;
    }

    public ArrayList<Mensagem> getArrayMensagem() {
        return arrayMensagem;
    }

    public void lerArquivo(File arquivo) throws LeitorException {
        Path path = Paths.get(arquivo.getAbsolutePath());
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha = null;
            Scanner sc;
            String identOrigem = null;
            String identDestino = null;
            Telefone telOrigem = null;
            Telefone telDestino = null;
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
                        telOrigem = null;
                        telDestino = null;
                        sc = new Scanner(linha).useDelimiter(";");
                        identOrigem = sc.next().trim();
                        identDestino = sc.next().trim();
                        for (Telefone t: this.arrayTelefone ) {
                            if(t.getIdent().equals(identOrigem)){
                                telOrigem = t;
                            }
                            if(t.getIdent().equals(identDestino)){
                                telDestino = t;
                            }
                        }
                        if(telDestino == null || telOrigem == null){
                            throw new LeitorException("Telefones inexistentes no sistema!\n");
                        }
                        if(telDestino == telOrigem){
                            throw new LeitorException("Telefones são iguais!\n");
                        }
                        this.arrayMensagem.add(new Mensagem(i+1, StatusMensagem.CELULAR_ORIGEM, telOrigem, telDestino));
                    }
                }
            }
        } catch ( IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }
}
