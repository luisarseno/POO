package br.pucrs.Model;

import br.pucrs.Exceptions.FilaCheiaException;
import br.pucrs.Exceptions.StatusInvalidoException;

import static org.junit.Assert.*;

/**
 * Created by Santana on 11/19/2016.
 */
public class AntenaTest {
    @org.junit.Test
    public void testEnfileiraMensagem() throws Exception {
    	System.out.println("TestEnfileirarMensagem\n");
    	Antena antena = new Antena("A1", 4 ,new Intervalo(1,1));
        Mensagem msg1 = new Mensagem(1, StatusMensagem.CELULAR_ORIGEM, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        Mensagem msg2 = new Mensagem(2, StatusMensagem.CENTRAL_PARA_ANTENA, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        Mensagem msg3 = new Mensagem(3, StatusMensagem.ANTENA_PARA_CELULAR, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        Mensagem msg4 = new Mensagem(4, StatusMensagem.CELULAR_ORIGEM, new Telefone("c1",antena ),new Telefone("c2",antena ) );
        
        try{
        	//Mensagem passa
        	System.out.println("Msg1 de status: " + msg1.getStatus() + " - Enfilerada.");
        	assertEquals(true, antena.enfileiraMensagem(msg1));
            System.out.println("Status Atual: " + msg1.getStatus());
        	
            //Mensagem passa.
            System.out.println("\nMsg2 de status: " + msg2.getStatus() + " - Enfilerada.");
            assertEquals(true, antena.enfileiraMensagem(msg2));
            System.out.println("Status Atual: " + msg2.getStatus());
            
            //Mensagem passa, mas o sistema envia uma mensagem de Status Inv�lido
            System.out.println("\nMsg3 de status: " + msg3.getStatus() + " - Enfilerada.");
        	assertEquals(true, antena.enfileiraMensagem(msg3));
        	System.out.println("Status Atual: " + msg3.getStatus());
        	
        	//Mensagem falha (da false) pois a capacidade da fila chaga ao limite.
        	msg4.setStatus(StatusMensagem.CELULAR_ORIGEM);
        	assertEquals(false, antena.enfileiraMensagem(msg4));
        	
        }catch (FilaCheiaException|StatusInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    @org.junit.Test
    public void TestDesenfileirarMensagem() throws Exception {
    		System.out.println("\n\nTestDesenfileirarMensagem\n");
    		Antena antena = new Antena("A1", 4 ,new Intervalo(1,1));
    		Mensagem msg1 = new Mensagem(1, StatusMensagem.CELULAR_ORIGEM, new Telefone("c1",antena ),new Telefone("c2",antena ) );
    		Mensagem msg2 = new Mensagem(2, StatusMensagem.CENTRAL_PARA_ANTENA, new Telefone("c1",antena ),new Telefone("c2",antena ) );
    		Mensagem msg3 = new Mensagem(3, StatusMensagem.CENTRAL_PARA_ANTENA, new Telefone("c1",antena ),new Telefone("c2",antena ) );
    		Mensagem msg4 = new Mensagem(4, StatusMensagem.CELULAR_ORIGEM, new Telefone("c1",antena ),new Telefone("c2",antena ) );
           
    		try{
    			//C1-A1-CENTRAL-A1
    			System.out.println("Msg1 de status: " + msg1.getStatus() + " - Enfilerando.");
    			antena.enfileiraMensagem(msg1);
    			System.out.println("Msg1 de status: " + msg1.getStatus() + " - Enfilerada.");
    			antena.desenfileirarMensagem();
    			System.out.println("Msg1 de status: " + msg1.getStatus() + " - desinfileirado.");	

    			//CENTRAL-A1-C2
    			System.out.println("\nMsg2 de status: " + msg2.getStatus() + " - Enfilerando.");
    			antena.enfileiraMensagem(msg2);
    			System.out.println("Msg2 de status: " + msg2.getStatus() + " - Enfilerada.");
    			antena.desenfileirarMensagem();
    			System.out.println("Msg2 de status: " + msg2.getStatus() + " - desinfileirado.");
    			
    			//Testando remover da lista com mais de uma na mesma.
    			System.out.println("\nMsg3 de status: " + msg3.getStatus() + " - Enfilerando.");
    			antena.enfileiraMensagem(msg3);
    			System.out.println("Msg3 de status: " + msg3.getStatus() + " - Enfilerada.");
    			System.out.println("Msg4 de status: " + msg4.getStatus() + " - Enfilerando.");
    			antena.enfileiraMensagem(msg4);
    			System.out.println("Msg4 de status: " + msg4.getStatus() + " - Enfilerada.");
    			antena.desenfileirarMensagem();
    			System.out.println("Msg3 de status: " + msg3.getStatus() + " - desinfileirado.");
    			antena.desenfileirarMensagem();
    			System.out.println("Msg4 de status: " + msg4.getStatus() + " - desinfileirado.");
           	
    		}catch (FilaCheiaException|StatusInvalidoException e){
    			System.out.println(e.getMessage());
    		}
    	}
}