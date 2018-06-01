
import controlador.ClienteControlador;
import controlador.MembresiaControlador;
import modelo.Cliente;
import java.sql.Date;
import modelo.Membresia;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodrigo
 */
public class Prueba {
    private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private static final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try{
                if(ino.isMessageAvailable())
                {
//                    ino.sendData( String.valueOf(MembresiaControlador.obtenerMembresia(ino.printMessage()) != null) );
                    String codigo = ino.printMessage();
                    Membresia m = MembresiaControlador.obtenerMembresia(codigo);
                    ino.sendData((m != null)?"1":"0");
                    if(m != null)
                    {
                        System.out.println(m.getCliente().getNombre() + "PUEDE INGRESAR");
                    }
                    else
                    {
                        System.out.println("No es miembro");
                    } 
                }
            }
            catch(SerialPortException | ArduinoException ex)
            {
                
            }
        }
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
            ino.arduinoRXTX("COM6", 115200, listener);
        }
        catch( ArduinoException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
