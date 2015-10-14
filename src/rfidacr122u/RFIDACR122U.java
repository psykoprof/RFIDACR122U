/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfidacr122u;
import java.util.Arrays;
import java.util.List;
import javax.smartcardio.*;


/**
 *
 * @author Brice
 */
public class RFIDACR122U {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws CardException {
        // TODO code application logic here
        byte tab[] = {(byte)0xFF,(byte)0xCA,(byte)0x00,(byte)0x00,(byte)0x00};
        // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        CardTerminal terminal = terminals.get(0);
        // establish a connection with the card
        Card card = terminal.connect("*");
        System.out.println("card: " + card);
        CardChannel channel = card.getBasicChannel();
        ResponseAPDU r = channel.transmit(new CommandAPDU(tab));
        //System.out.println("response: " + Arrays.toString(r.getBytes()));
        System.out.println("response: " + bytesToHexString(r.getBytes()));

        // disconnect
        card.disconnect(false);
    }
    
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b: bytes) {
            sb.append(String.format("%02x",b&0xff));
        }
        return sb.toString();
    }
    
}
