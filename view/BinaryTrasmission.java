package view;

import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.LinePositions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryTrasmission extends Thread {
    
    // Bits que irao ser representados como String
    public static String[] bits;
    
    public static boolean stop = false;
    
    Line[] binaryLine1;
    Slider speedSld;
    
    
    public BinaryTrasmission(Line[] binaryLine1, Slider speedSld) {
        this.binaryLine1 = binaryLine1;
        this.speedSld = speedSld;
    } // Fim do construtor
    
    public void run() {
        
        int z = LinePositions.STREAM_X.getValue();
        
        binaryLine1[0].setStroke(Color.RED);
        
        StringBuilder sb = new StringBuilder();
        String s = "";
        
        for (int i = 0; i < bits.length; i++) {
            s = sb.append(bits[i]).toString();
        }
        
        for (int i = 0; i < s.length(); i++) {
            
            for (int k = binaryLine1.length - 1; k >= 1; k--) {
                binaryLine1[k].setStartX(binaryLine1[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                binaryLine1[k].setEndX(binaryLine1[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                binaryLine1[k].setStartY(binaryLine1[k - 1].getStartY());
                binaryLine1[k].setEndY(binaryLine1[k - 1].getEndY());
                binaryLine1[k].setStroke(binaryLine1[k - 1].getStroke());
            } // Fim do for
            
            if (s.charAt(i) == '0') {
                binaryLine1[0].setStartX(z);
                binaryLine1[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                binaryLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                binaryLine1[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
            } else if (s.charAt(i) == '1') {
                binaryLine1[0].setStartX(z);
                binaryLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                binaryLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                binaryLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
            } // Fim do if/else
            
            try {
                Thread.sleep((long) (1000 - speedSld.getValue()));
            } catch (InterruptedException ex) {
                Logger.getLogger(BinaryTrasmission.class.getName()).log(Level.SEVERE, null, ex);
            } // Fim do try/catch
        }
    }
}
