package br.pucrs.View;

import br.pucrs.Exceptions.LeitorException;
import br.pucrs.Model.*;

import java.io.File;

/**
 * Created by Santana on 11/20/2016.
 */
public class Experimento {
    public static void main(String[] args){
        File fileExecucao = new File("C:\\Users\\Santana\\Documents\\INSTANCIACAO1.txt");
        File fileSimulacao = new File("C:\\Users\\Santana\\Documents\\EVENTOS1.txt");
        LeitorExecucao leitorExecucao= new LeitorExecucao();
        LeitorSimulacao leitorSimulacao = new LeitorSimulacao();

        try {
            leitorExecucao.lerArquivo(fileExecucao);
            System.out.println("Nome:"+leitorExecucao.getNomeExperimento()+"\n"
                    + "Tempo: " +leitorExecucao.getTempoSimulacao()+"\n"
                    + "Central: "+leitorExecucao.getCentral()
            );

            for (Antena a: leitorExecucao.getArrayAntenas()) {
                System.out.println(a);
            }

            for (Telefone t : leitorExecucao.getArrayTelefone()) {
                System.out.println(t);
            }
        } catch (LeitorException e){
            System.out.println(e.getMessage());
        }

        try {
            leitorSimulacao.lerArquivo(fileSimulacao);
            System.out.println("Nome: "+leitorSimulacao.getNomeExperimento());

            for (Eventos e : leitorSimulacao.getArrayEventos()) {
                System.out.println(e);
            }
        } catch (LeitorException e){
            System.out.println(e.getMessage());
        }




    }
}
