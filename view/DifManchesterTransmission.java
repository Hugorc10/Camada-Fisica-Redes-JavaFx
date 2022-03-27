package view;

import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.LinePositions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DifManchesterTransmission extends Thread {
    
    // Bits que irao ser representados como String
    public static String[] difManchesterBits;
    
    Line[] difManchesterLine1;
    Line[] difManchesterLine2;
    Line[] difManchesterLine3;
    Slider speedSld;
    
    public DifManchesterTransmission(Line[] difManchesterLine1, Line[] difManchesterLine2, Line[] difManchesterLine3, Slider speedSld) {
        this.difManchesterLine1 = difManchesterLine1;
        this.difManchesterLine2 = difManchesterLine2;
        this.difManchesterLine3 = difManchesterLine3;
        this.speedSld = speedSld;
    }
    
    public void run() {
        
        int z = LinePositions.STREAM_X.getValue();
        
        difManchesterLine1[0].setStroke(Color.RED);
        difManchesterLine2[0].setStroke(Color.RED);
        difManchesterLine3[0].setStroke(Color.RED);
        
        StringBuilder sb = new StringBuilder();
        String s = "";
        
        for (int i = 0; i < difManchesterBits.length; i++) {
            s = sb.append(difManchesterBits[i]).toString();
        }
        
        for (int i = 0; i < s.length(); i++) {
            
            for (int k = difManchesterLine1.length - 1; k >= 1; k--) {
                difManchesterLine1[k].setStartX(difManchesterLine1[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                difManchesterLine1[k].setEndX(difManchesterLine1[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                difManchesterLine1[k].setStartY(difManchesterLine1[k - 1].getStartY());
                difManchesterLine1[k].setEndY(difManchesterLine1[k - 1].getEndY());
                difManchesterLine1[k].setStroke(difManchesterLine1[k - 1].getStroke());
                
                difManchesterLine2[k].setStartX(difManchesterLine2[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                difManchesterLine2[k].setEndX(difManchesterLine2[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                difManchesterLine2[k].setStartY(difManchesterLine2[k - 1].getStartY());
                difManchesterLine2[k].setEndY(difManchesterLine2[k - 1].getEndY());
                difManchesterLine2[k].setStroke(difManchesterLine2[k - 1].getStroke());
                
                difManchesterLine3[k].setStartX(difManchesterLine3[k - 1].getStartX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                difManchesterLine3[k].setEndX(difManchesterLine3[k - 1].getEndX() + LinePositions.CLOCK_LINE_WIDTH.getValue());
                difManchesterLine3[k].setStartY(difManchesterLine3[k - 1].getStartY());
                difManchesterLine3[k].setEndY(difManchesterLine3[k - 1].getEndY());
                difManchesterLine3[k].setStroke(difManchesterLine3[k - 1].getStroke());
            }  // Fim do for
            
            if (s.charAt(i) == '0') {
                
                if (difManchesterLine3[1].getStartX() == z + (LinePositions.CLOCK_LINE_WIDTH.getValue() * 2)) {
                    difManchesterLine1[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                    difManchesterLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
    
                    difManchesterLine2[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine2[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
    
                    difManchesterLine3[0].setStartX(z);
                    difManchesterLine3[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                    difManchesterLine3[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine3[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                } else {
                    difManchesterLine1[0].setStartX(z);
                    difManchesterLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
    
                    difManchesterLine2[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine2[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
    
                    difManchesterLine3[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine3[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                    difManchesterLine3[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                    difManchesterLine3[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                }
            } else if (s.charAt(i) == '1') {
                
                if (difManchesterLine3[1].getStartX() == z + (LinePositions.CLOCK_LINE_WIDTH.getValue() * 2)) {
                    difManchesterLine1[0].setStartX(z);
                    difManchesterLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
        
                    difManchesterLine2[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine2[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
        
                    difManchesterLine3[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine3[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                    difManchesterLine3[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                    difManchesterLine3[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                } else {
                    difManchesterLine1[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine1[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine1[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue());
                    difManchesterLine1[0].setEndY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
        
                    difManchesterLine2[0].setStartX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setStartY(LinePositions.POSITIVE_SIGNAL_Y.getValue());
                    difManchesterLine2[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine2[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
        
                    difManchesterLine3[0].setStartX(z);
                    difManchesterLine3[0].setStartY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                    difManchesterLine3[0].setEndX(z - LinePositions.CLOCK_LINE_WIDTH.getValue() / 2);
                    difManchesterLine3[0].setEndY(LinePositions.NEGATIVE_SIGNAL_Y.getValue());
                }
            } // Fim do if/else
            
            try {
                Thread.sleep(1000 - (int) speedSld.getValue());
            } catch (InterruptedException ex) {
                Logger.getLogger(ManchesterTransmission.class.getName()).log(Level.SEVERE, null, ex);
            } // Fim do try/catch
        } // Fim do for
    } // Fim do metodo run
}
