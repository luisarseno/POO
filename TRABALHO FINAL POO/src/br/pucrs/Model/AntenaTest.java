package br.pucrs.Model;

import br.pucrs.Exceptions.FilaCheiaException;
import br.pucrs.Exceptions.StatusInvalidoException;

import static org.junit.Assert.*;

/**
 * Created by Santana on 11/19/2016.
 */
public class AntenaTest {
    @org.junit.Test
    public void enfileiraMensagem() throws Exception {
        Antena antena = new Antena("A1", 2 ,new Intervalo(1,1));
        Mensagem msg = new Mensagem("teste", StatusMensagem.CELULAR, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        try{
            antena.enfileiraMensagem(msg);
            antena.enfileiraMensagem(msg);
            antena.enfileiraMensagem(msg);

        }catch (FilaCheiaException|StatusInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    @org.junit.Test
    public void desenfileirarMensagem() throws Exception {

    }

}