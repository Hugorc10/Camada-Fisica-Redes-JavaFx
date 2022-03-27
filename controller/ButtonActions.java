package controller;

import util.Codifications;
import view.BinaryTrasmission;
import view.DifManchesterTransmission;
import view.ManchesterTransmission;
import view.ScreenView;

public class ButtonActions {
    
    private static BinaryTrasmission bt;
    private static ManchesterTransmission mt;
    private static DifManchesterTransmission dmt;
    
    /**
     * Metodo: onClickStart.
     * Funcao: armazena o texto digitado em uma string e o envia para o metodo 'codificarCaracteres()'.
     */
    public static void onClickStart() {
    
        bt = new BinaryTrasmission(ScreenView.binaryLine1, ScreenView.speedSld);
        mt = new ManchesterTransmission(ScreenView.manchesterLine1,  ScreenView.manchesterLine2,
                ScreenView.manchesterLine3, ScreenView.speedSld);
        dmt = new DifManchesterTransmission(ScreenView.difManchesterLine1, ScreenView.difManchesterLine2,
                ScreenView.difManchesterLine3, ScreenView.speedSld);
    
        for (int i = 0; i < ScreenView.binaryLine1.length; i++) {
            ScreenView.binaryLine1[i].setStartX(0);
            ScreenView.binaryLine1[i].setEndX(0);
            ScreenView.binaryLine1[i].setStartY(0);
            ScreenView.binaryLine1[i].setEndY(0);
        }
    
        for (int i = 0; i < ScreenView.manchesterLine1.length; i++) {
            ScreenView.manchesterLine1[i].setStartX(0);
            ScreenView.manchesterLine1[i].setStartY(0);
            ScreenView.manchesterLine1[i].setEndX(0);
            ScreenView.manchesterLine1[i].setEndY(0);
            ScreenView.manchesterLine2[i].setStartX(0);
            ScreenView.manchesterLine2[i].setStartY(0);
            ScreenView.manchesterLine2[i].setEndX(0);
            ScreenView.manchesterLine2[i].setEndY(0);
            ScreenView.manchesterLine3[i].setStartX(0);
            ScreenView.manchesterLine3[i].setStartY(0);
            ScreenView.manchesterLine3[i].setEndX(0);
            ScreenView.manchesterLine3[i].setEndY(0);
        }
    
        for (int i = 0; i < ScreenView.difManchesterLine1.length; i++) {
            ScreenView.difManchesterLine1[i].setStartX(0);
            ScreenView.difManchesterLine1[i].setStartY(0);
            ScreenView.difManchesterLine1[i].setEndX(0);
            ScreenView.difManchesterLine1[i].setEndY(0);
            ScreenView.difManchesterLine2[i].setStartX(0);
            ScreenView.difManchesterLine2[i].setStartY(0);
            ScreenView.difManchesterLine2[i].setEndX(0);
            ScreenView.difManchesterLine2[i].setEndY(0);
            ScreenView.difManchesterLine3[i].setStartX(0);
            ScreenView.difManchesterLine3[i].setStartY(0);
            ScreenView.difManchesterLine3[i].setEndX(0);
            ScreenView.difManchesterLine3[i].setEndY(0);
        }
        
        ScreenView.bitsTArea.setText("");
        ScreenView.receiverTxt.setText("");
        Codifications.transmittingApplication(); // Chama a proxima camada
    } // Fim do metodo onClickStart
    
    
    /**
     * Metodo: onClickClear
     * Funcao: limpa o painel e os campos de texto exibidos na aplicacao
     */
    public static void onClickClear() {
        
        for (int i = 0; i < ScreenView.binaryLine1.length; i++) {
            ScreenView.binaryLine1[i].setStartX(0);
            ScreenView.binaryLine1[i].setEndX(0);
            ScreenView.binaryLine1[i].setStartY(0);
            ScreenView.binaryLine1[i].setEndY(0);
        }
        
        for (int i = 0; i < ScreenView.manchesterLine1.length; i++) {
            ScreenView.manchesterLine1[i].setStartX(0);
            ScreenView.manchesterLine1[i].setStartY(0);
            ScreenView.manchesterLine1[i].setEndX(0);
            ScreenView.manchesterLine1[i].setEndY(0);
            ScreenView.manchesterLine2[i].setStartX(0);
            ScreenView.manchesterLine2[i].setStartY(0);
            ScreenView.manchesterLine2[i].setEndX(0);
            ScreenView.manchesterLine2[i].setEndY(0);
            ScreenView.manchesterLine3[i].setStartX(0);
            ScreenView.manchesterLine3[i].setStartY(0);
            ScreenView.manchesterLine3[i].setEndX(0);
            ScreenView.manchesterLine3[i].setEndY(0);
        }
    
        for (int i = 0; i < ScreenView.difManchesterLine1.length; i++) {
            ScreenView.difManchesterLine1[i].setStartX(0);
            ScreenView.difManchesterLine1[i].setStartY(0);
            ScreenView.difManchesterLine1[i].setEndX(0);
            ScreenView.difManchesterLine1[i].setEndY(0);
            ScreenView.difManchesterLine2[i].setStartX(0);
            ScreenView.difManchesterLine2[i].setStartY(0);
            ScreenView.difManchesterLine2[i].setEndX(0);
            ScreenView.difManchesterLine2[i].setEndY(0);
            ScreenView.difManchesterLine3[i].setStartX(0);
            ScreenView.difManchesterLine3[i].setStartY(0);
            ScreenView.difManchesterLine3[i].setEndX(0);
            ScreenView.difManchesterLine3[i].setEndY(0);
        }
    } // Fim do metodo onClickClear
    
    public static void onClickStop() throws InterruptedException {
        if (bt.isAlive()) {
            bt.interrupt();
        } else if (mt.isAlive()) {
            mt.interrupt();
        } else {
            dmt.interrupt();
        }
    }
}
