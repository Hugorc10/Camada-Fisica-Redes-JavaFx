package view;

import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.LinePositions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ManchesterTransmission extends Thread {
    
    // Bits que irao ser representados como String
    public static String[] manchesterBits;
    
    Line manchesterLine1[];
    Line manchesterLine2[];
    Line manchesterLine3[];
    Slider speedSld;
    
    public ManchesterTransmission(Line[] manchesterLine1, Line[] manchesterLine2, Line[] manchesterLine3, Slider speedSld) {
        this.manchesterLine1 = manchesterLine1;
        this.manchesterLine2 = manchesterLine2;
        this.manchesterLine3 = manchesterLine3;
        this.speedSld = speedSld;
    } // Fim do construtor ManchesterTransmission
    
    public void run() {
        
        int z = LinePositions.STREAM_X.getValue();
        
        manchesterLine1[0].setStroke(Color.RED);
        manchesterLine2[0].setStroke(Color.RED);
        manchesterLine3[0].setStroke(Color.RED);
        
        StringBuilder sb = new StringBuilder();
        String s = "";
        
        for (int i = 0; i < manchesterBits.length; i++) {
            s = sb.append(manchesterBits[i]).toString();
        }
        
        for (int i = 0; i < s.length(); i++) {
            
            for (int k = manchesterLine1.length - 1; k >= 1; k--) {
                manchesterLine1[k].setStartX(manchesterLine1[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine1[k].setEndX(manchesterLine1[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine1[k].setStartY(manchesterLine1[k - 1].getStartY());
                manchesterLine1[k].setEndY(manchesterLine1[k - 1].getEndY());
                manchesterLine1[k].setStroke(manchesterLine1[k - 1].getStroke());
                
                manchesterLine2[k].setStartX(manchesterLine2[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine2[k].setEndX(manchesterLine2[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine2[k].setStartY(manchesterLine2[k - 1].getStartY());
                manchesterLine2[k].setEndY(manchesterLine2[k - 1].getEndY());
                manchesterLine2[k].setStroke(manchesterLine2[k - 1].getStroke());
                
                manchesterLine3[k].setStartX(manchesterLine3[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine3[k].setEndX(manchesterLine3[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine3[k].setStartY(manchesterLine3[k - 1].getStartY());
                manchesterLine3[k].setEndY(manchesterLine3[k - 1].getEndY());
                manchesterLine3[k].setStroke(manchesterLine3[k - 1].getStroke());
            }  // Fim do for
            
            if (s.charAt(i) == '0') {
                manchesterLine1[0].setStartX(z);
                manchesterLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                manchesterLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());

                manchesterLine2[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine2[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                manchesterLine2[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine2[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());

                manchesterLine3[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine3[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                manchesterLine3[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine3[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
            } else if (s.charAt(i) == '1') {
                manchesterLine1[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                manchesterLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                manchesterLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
    
                manchesterLine2[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine2[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                manchesterLine2[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine2[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
    
                manchesterLine3[0].setStartX(z);
                manchesterLine3[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                manchesterLine3[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                manchesterLine3[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
            } // Fim do if/else
            
            try {
                Thread.sleep(1000 - (int) speedSld.getValue());
            } catch (InterruptedException ex) {
                Logger.getLogger(ManchesterTransmission.class.getName()).log(Level.SEVERE, null, ex);
            } // Fim do try/catch
        } // Fim do for
    } // Fim do metodo run
} // Fim da classe
