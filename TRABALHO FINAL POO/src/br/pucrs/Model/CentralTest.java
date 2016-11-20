package br.pucrs.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.pucrs.Exceptions.FilaCheiaException;
import br.pucrs.Exceptions.StatusInvalidoException;

public class CentralTest {

	@org.junit.Test
    public void TestDesempilhaProcesso() throws Exception {
    	System.out.println("TestDesempilhaProcesso\n");
    	Central central = new Central("Central", 10, new Intervalo(1,1));
    	Antena antena = new Antena("A1", 4 ,new Intervalo(1,1));
        Mensagem msg1 = new Mensagem("teste", StatusMensagem.CENTRAL_PARA_ANTENA, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        Mensagem msg2 = new Mensagem("teste", StatusMensagem.ANTENA_PARA_CENTRAL, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        Mensagem msg3 = new Mensagem("teste", StatusMensagem.CELULAR, new Telefone("c1",antena ),new Telefone("c2",antena ) );
       
        
        /**
        Ocorreu um erro, o método desempilharProcesso deveria alterar o status da mensagem
        para "CENTRAL_PARA_ANTENA", entretanto ele esta alterando para "ANTENA_PARA_CELULAR".
        **/
        
        try{
        	System.out.println("Msg1 de status: " + msg1.getStatus() + " - Empilhando.");
        	central.empilhaProcesso(msg1);
        	System.out.println("Msg1 de status: " + msg1.getStatus() + " - Empilhada.");
        	assertEquals(true, central.desempilhaProcesso());
        	System.out.println("Msg1 de status: " + msg1.getStatus() + " - Desimpilhando.");
        		
        	System.out.println("\nMsg2 de status: " + msg2.getStatus() + " - Empilhando.");
        	central.empilhaProcesso(msg2);
        	System.out.println("Msg2 de status: " + msg2.getStatus() + " - Empilhada.");
        	assertEquals(true, central.desempilhaProcesso());
        	System.out.println("Msg2 de status: " + msg2.getStatus() + " - Desimpilhando.");
        	
        	System.out.println("\nMsg3 de status: " + msg3.getStatus() + " - Empilhando.");
        	central.empilhaProcesso(msg3);
        	System.out.println("Msg3 de status: " + msg3.getStatus() + " - Empilhada.");
        	assertEquals(true, central.desempilhaProcesso());
        	System.out.println("Msg3 de status: " + msg3.getStatus() + " - Desimpilhando.");
        }catch (FilaCheiaException|StatusInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

}
