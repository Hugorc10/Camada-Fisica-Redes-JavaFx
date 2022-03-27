package util;

import javafx.scene.control.Alert;
import view.BinaryTrasmission;
import view.DifManchesterTransmission;
import view.ManchesterTransmission;
import view.ScreenView;

import java.util.Arrays;

public class Codifications {
    
    private static BinaryTrasmission bt;
    private static ManchesterTransmission mt;
    private static DifManchesterTransmission dmt;
    
    /**
     * Metodo: transmittingApplication
     * Funcao: armazenar o texto digitado em uma string e envia-lo para o metodo 'transmittingAplicationLayer()'
     */
    public static void transmittingApplication() {
        String mensagem = ScreenView.inputTxt.getText(); // Armazena a texto digitado na String mensagem
        transmittingAplicationLayer(mensagem); // Chama a proxima camada
    } // Fim do metodo transmittingApplication
    
    
    /**
     * Metodo: transmittingAplicationLayer
     * Funcao: Armazena os codigos ASCII em um array de bytes e o envia para o metodo 'transmittingPhysicalLayer()'
     *
     * @param message
     */
    private static void transmittingAplicationLayer(String message) {
        System.out.print("\nCamada de Aplicacao Transmissora\n");
        // Insere um codigo ASCII em cada posicao do vetor
        byte[] quadroByte = message.getBytes();
        
        System.out.println("Tamanho do quadroByte: " + quadroByte.length);
        int[] quadroInteger = new int[quadroByte.length];
        
        // convertendo byteArray para intArray
        for (int i = 0; i < quadroByte.length; quadroInteger[i] = quadroByte[i++]);
        
        System.out.print("\nTamanho do quadroInteger: " + quadroInteger.length + "\n");
        System.out.print(Arrays.toString(quadroInteger));
        
        if (ScreenView.inputTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The input box is empty!");
            alert.setTitle("Alert");
            alert.showAndWait();
        } else if (ScreenView.group.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The radio buttons are not select!");
            alert.setTitle("Alert");
            alert.showAndWait();
        } else {
            transmittingPhysicalLayer(quadroInteger);
        }
    } // Fim do metodo transmittingAplicationLayer
    
    private static void transmittingPhysicalLayer(int[] frames) {
        System.out.print("Camada Fisica Transmissora");
        
        int[] fluxoBrutoDeBits = new int[0];
        
        if (ScreenView.binaryRad.isSelected()) {
            try {
                
                bt = new BinaryTrasmission(ScreenView.binaryLine1, ScreenView.speedSld);
                
                fluxoBrutoDeBits = Codifications.camadaFisicaTransmissoraCodificacaoBinaria(frames);
                
                bt.bits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    bt.bits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }
                
                if (!bt.isAlive()) {
                    bt.start();
                }
                
                for (String s : bt.bits) {
                    ScreenView.bitsTArea.appendText(s);
                }
                
                System.out.println("Imprimindo binary bits receptor: " + Arrays.toString(BinaryTrasmission.bits));
            } catch (NumberFormatException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Entrada invalida!");
                alert.setTitle("Error");
                alert.showAndWait();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Excecao desconhecida ocorreu!");
                alert.setTitle("Error");
                alert.showAndWait();
            }
        } else if (ScreenView.manchesterRad.isSelected()) {
            try {
                
                mt = new ManchesterTransmission(ScreenView.manchesterLine1, ScreenView.manchesterLine2,
                        ScreenView.manchesterLine3, ScreenView.speedSld);
                
                fluxoBrutoDeBits = Codifications.camadaFisicaTransmissoraCodificacaoManchester(frames);
                
                mt.manchesterBits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    mt.manchesterBits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }
                
                if (!mt.isAlive()) {
                    mt.start();
                }
                
                for (String s : mt.manchesterBits) {
                    ScreenView.bitsTArea.appendText(s);
                }
                
                System.out.println("Imprimindo bits manchester: " + Arrays.toString(ManchesterTransmission.manchesterBits));
            } catch (NumberFormatException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Entrada invalida!");
                alert.setTitle("Error");
                alert.showAndWait();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Excecao desconhecida ocorreu!!");
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }else if (ScreenView.difManchesterRad.isSelected()) {
            try {
                
                dmt = new DifManchesterTransmission(ScreenView.difManchesterLine1, ScreenView.difManchesterLine2,
                        ScreenView.difManchesterLine3, ScreenView.speedSld);
                
                fluxoBrutoDeBits = Codifications.camadaFisicaTransmissoraCodificacaoManchesterDiferencial(frames);
                
                dmt.difManchesterBits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    dmt.difManchesterBits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }
                
                if (!dmt.isAlive()) {
                    dmt.start();
                }
                
                for (String s : dmt.difManchesterBits) {
                    ScreenView.bitsTArea.appendText(s);
                }
                
                System.out.println("Imprimindo bits manchester diferencial: " + Arrays.toString(ManchesterTransmission.manchesterBits));
            } catch (NumberFormatException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Entrada invalida!");
                alert.setTitle("Error");
                alert.showAndWait();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Excecao desconhecida ocorreu!");
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }
        
        Codifications.meioDeComunicacao(fluxoBrutoDeBits);
    }
    
    /**
     * Metodo: camadaFisicaTransmissoraCodificacaoBinaria
     * Funcao: Codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Binaria e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    public static int[] camadaFisicaTransmissoraCodificacaoBinaria(int[] quadro) {
        System.out.print("\nCamada Fisica Transmissora Codificacao Binaria\n");
        System.out.print("Imprimindo quadro: " + Arrays.toString(quadro) + "\n");
        
        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por quatro
        int n = quadro.length / 4;
        
        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 4 != 0) {
            n++;
        }
        
        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];
        
        int index = 0; // Variavel auxiliar
        // Realiza loop ate o i ser menor que o comprimento (length) do vetor bits
        for (int i = 0; i < bits.length; i++) {
            bits[i] = quadro[index]; // bits[0] = quadro[0]
            index++;
            
            for (int y = 0; y < 3; y++) {
                if (index < quadro.length) {
                    bits[i] = bits[i] << 8; // Desloca 8 bits a esquerda
                    bits[i] = bits[i] | quadro[index]; // Concatena os bits na posicao "i" com os bits do quadro na posicao "aux"
                    index++;
                } // fim do if
            } // fim do for
        } // Fim do for
        
        return bits;
    } // Fim do metodo camadaFisicaTransmissoraCodificacaoBinaria
    
    /**
     * Metodo: camadaFisicaTransmissoraCodificacaoManchester
     * Funcao: codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Manchester e insere em um array de inteiros em forma de bits
     *
     * @param quadro
     * @return int[]
     */
    public static int[] camadaFisicaTransmissoraCodificacaoManchester(int[] quadro) {
        System.out.print("\nCamada Fisica Transmissora Codificacao Manchester\n");
    
        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por dois
        int n = quadro.length / 2;
    
        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 2 != 0) {
            n++;
        }
    
        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];
    
        int aux = 0; // Variavel auxiliar
        for (int x = 0; x < bits.length; x++) {
            for (int y = 0; y < 2; y++) {
                if (aux < quadro.length) {
                    int temp = quadro[aux];
                    for (int i = 7; i >= 0; i--) {
                        bits[x] = bits[x] << 1;
                        int mask = 1 << i; // Desloca 7 bits a esquerda ate 0
                        if (Integer.toBinaryString(temp & mask).charAt(0) == '0') {
                            bits[x] = bits[x] | 1; // Adiciona o bit 1
                        } else if (Integer.toBinaryString(temp & mask).charAt(0) == '1') {
                            bits[x] = bits[x] | 0; // Adiciona o bit 0
                        }
                        int t = Integer.parseInt(String.valueOf(Integer.toBinaryString(temp & mask).charAt(0)));
                        bits[x] = bits[x] << 1;
                        bits[x] = bits[x] | t;
                    }
                    aux++;
                }
            }
        }
    
        System.out.println(Arrays.toString(bits));
    
        return bits;
    } // Fim do metodo camadaFisicaTransmissoraCodificacaoManchester
    
    /**
     * Metodo: camadaFisicaTransmissoraCodificacaoManchesterDiferencial
     * Funcao: Codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Manchester Diferencial e insere em um array de inteiros em forma de bits
     *
     * @param quadro
     * @return int[]
     */
    public static int[] camadaFisicaTransmissoraCodificacaoManchesterDiferencial(int[] quadro) {
        System.out.println("\nCamada Fisica Transmissora Codificacao Manchester Diferencial\n");
        
        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por dois
        int n = quadro.length / 2;
        
        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 2 != 0) {
            n++;
        }
        
        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];
        
        int aux = 0; // Variavel auxiliar
        for (int x = 0; x < bits.length; x++) {
            for (int y = 0; y < 2; y++) {
                if (aux < quadro.length) {
                    int temp = quadro[aux];
                    for (int i = 7; i >= 0; i--) {
                        bits[x] = bits[x] << 1;
                        int mask = 1 << i;
                        if (Integer.toBinaryString(temp & mask).charAt(0) == '0') {
                            bits[x] = bits[x] | 1; // Adiciona 1
                        } else if (Integer.toBinaryString(temp & mask).charAt(0) == '1') {
                            bits[x] = bits[x] | 0; // Adiciona 0
                        }
                        int t = Integer.parseInt(String.valueOf(Integer.toBinaryString(temp & mask).charAt(0)));
                        bits[x] = bits[x] << 1;
                        bits[x] = bits[x] | t;
                    }
                    aux++;
                } // fim do if
            } // fim do for
        } // fim do for
        
        System.out.println(Arrays.toString(bits));
        
        return bits;
    } // Fim do metodo camadaFisicaTransmissoraCodificacaoManchesterDiferencial
    
    /**
     * Metodo: meioDeComunicacao
     * Funcao: simular a transmissao de comunicacao da informacao no meio de comunicacao
     * passando de uma variavel para outra
     *
     * @param fluxoBrutoDeBits eh o fluxo de binarios codificados
     * @return void
     */
    public static void meioDeComunicacao(int[] fluxoBrutoDeBits) {
        System.out.print("\nMeio de Comunicação\n");
        
        int[] fluxoBrutoDeBitsPontoA = new int[fluxoBrutoDeBits.length];
        
        for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
            fluxoBrutoDeBitsPontoA[i] = fluxoBrutoDeBits[i];
        }
        
        int[] fluxoBrutoDeBitsPontoB = new int[fluxoBrutoDeBits.length];
        
        for (int i = 0; i < fluxoBrutoDeBitsPontoA.length; i++) {
            // fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] and 0000 0000) or (fluxoBrutoDeBitsPontoA[i] and 1111 1111)
            fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] & ~0xff) | (fluxoBrutoDeBitsPontoA[i] & 0xff);
            // fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] & 0xfffffff0) | (fluxoBrutoDeBitsPontoA[i] & 0xf);
        }
        
        camadaFisicaReceptora(fluxoBrutoDeBitsPontoB);
    } // Fim do metodo meioDeComunicacao
    
    /**
     * Metodo: camadaFisicaReceptora
     * Funcao: recebe os bits do vetor de inteiros e decodifica de acordo
     * com o tipo de codificacao
     *
     * @param fluxoBrutoDeBits
     * @return void
     */
    private static void camadaFisicaReceptora(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora\n");
        
        int[] quadro = new int[0];
        if (ScreenView.binaryRad.isSelected()) {
            quadro = camadaFisicaReceptoraDecodificacaoBinaria(fluxoBrutoDeBits);
        } else if (ScreenView.manchesterRad.isSelected()) {
            quadro = camadaFisicaReceptoraDecodificacaoManchester(fluxoBrutoDeBits);
        } else if (ScreenView.difManchesterRad.isSelected()){
            quadro = camadaFisicaReceptoraDecodificacaoManchesterDiferencial(fluxoBrutoDeBits);
        }
        
        receivingApplicationLayer(quadro); // chama a proxima camada
    } // Fim do metodo camadaFisicaReceptora
    
    /**
     * Metodo: camadaFisicaReceptoraDecodificacaoBinaria
     * Funcao:
     *
     * @param fluxoBrutoDeBits
     * @return int[]
     */
    private static int[] camadaFisicaReceptoraDecodificacaoBinaria(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora Decodificacao Binaria\n");
        int[] quadro = new int[fluxoBrutoDeBits.length * 4];
        
        for (int x = 0; x < quadro.length; x++) {
            quadro[x] = -1;
        } // fim do for
        
        int i = 31;
        if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] <= 255) {
            i = 7;
        } else if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] <= 65535) {
            i = 15;
        } else if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] <= 16777215) {
            i = 23;
        } // fim do if
        
        int cont2 = 0;
        for (int x = 0; x < fluxoBrutoDeBits.length; x++) {
            int y = 31;
            if (x == fluxoBrutoDeBits.length - 1) {
                y = i;
            } // fim do if
            String imp = "";
            int cont1 = 1;
            
            for (; y >= 0; y--) {
                int mask = 1 << y;
                imp += "" + Integer.toBinaryString(fluxoBrutoDeBits[x] & mask).charAt(0);
                if (cont1 != 0 && cont1 % 8 == 0) {
                    quadro[cont2] = Integer.parseInt(imp, 2);
                    cont2++;
                    imp = "";
                } // fim do if
                cont1++;
            } // fim do for
        } // fim do for
        
        return quadro;
    } // Fim do metodo camadaFisicaReceptoraDecodificacaoBinaria
    
    /**
     * Metodo: camadaFisicaReceptoraDecoficacaoManchester
     * Funcao:
     *
     * @param fluxoBrutoDeBits
     * @return int[]
     */
    public static int[] camadaFisicaReceptoraDecodificacaoManchester(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora Decodificacao Manchester\n");
        
        int[] quadro = new int[fluxoBrutoDeBits.length * 2];
        
        for (int x = 0; x < quadro.length; x++)
            quadro[x] = -1;
        
        int i = 31;
        
        String n = "" + fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1];
        if (n.length() <= 6) {
            i = 15;
        }
        
        int aux2 = 0;
        for (int x = 0; x < fluxoBrutoDeBits.length; x++) {
            int y = 31;
            if (x == fluxoBrutoDeBits.length - 1) {
                y = i;
            }
            String imp = "";
            int aux1 = 1;
            
            while (y >= 0) {
                int mask = 1 << y;
                
                if ((aux1 - 1 != 0) && (aux1 - 1) % 2 != 0) {
                    imp += "" + Integer.toBinaryString(fluxoBrutoDeBits[x] & mask).charAt(0);
                }
                
                if (aux1 != 0 && aux1 % 16 == 0) {
                    quadro[aux2] = Integer.parseInt(imp, 2);
                    aux2++;
                    imp = "";
                }
                aux1++;
                y--;
            }
        }
        return quadro;
    } // Fim do metodo camadaFisicaReceptoraDecodificacaoManchester
    
    /**
     * Metodo: camadaFisicaReceptoraDecodificacaoManchesterDiferencial
     * Funcao:
     *
     * @param fluxoBrutoDeBits
     * @return int[]
     */
    private static int[] camadaFisicaReceptoraDecodificacaoManchesterDiferencial(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora Decodificacao Manchester Diferencial\n");
        
        int[] quadro = new int[fluxoBrutoDeBits.length * 2];
        
        for (int x = 0; x < quadro.length; x++)
            quadro[x] = -1;
        
        int i = 31;
        
        String n = "" + fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1];
        if (n.length() <= 6) {
            i = 15;
        }
        
        int aux2 = 0;
        for (int x = 0; x < fluxoBrutoDeBits.length; x++) {
            int y = 31;
            if (x == fluxoBrutoDeBits.length - 1) {
                y = i;
            }
            String imp = "";
            int aux1 = 1;
            
            while (y >= 0) {
                int mask = 1 << y;
                
                if ((aux1 - 1 != 0) && (aux1 - 1) % 2 != 0) {
                    imp += "" + Integer.toBinaryString(fluxoBrutoDeBits[x] & mask).charAt(0);
                }
                
                if (aux1 != 0 && aux1 % 16 == 0) {
                    quadro[aux2] = Integer.parseInt(imp, 2);
                    aux2++;
                    imp = "";
                }
                aux1++;
                y--;
            }
        }
        return quadro;
    }
    
    /**
     * Metodo: receivingApplicationLayer
     * Funcao: converte a mensagem que esta em codigo ASCII para char
     *
     * @param frames
     */
    private static void receivingApplicationLayer(int[] frames) {
        String message;
        
        StringBuilder sb = new StringBuilder(frames.length);
        for (int i = 0; i < frames.length; ++i) {
            if (frames[i] != -1)
                sb.append((char) frames[i]);
        }
        
        message = sb.toString();
        
        System.out.println("Imprimindo mensagem: " + message);
        
        aplicacaoReceptora(message);
    } // Fim do metodo receivingApplicationLayer
    
    /**
     * Metodo: aplicacaoReceptora
     * Funcao: exibir o texto decodificado na Area de Texto "mensagemReceptor"
     * <p>
     *
     * @param mensagem eh a mensagem que foi decodificada
     * @return void
     */
    private static void aplicacaoReceptora(String mensagem) {
        ScreenView.receiverTxt.setText(mensagem); // Seta a mensagem no JTextArea "mensagemReceptor"
    } // Fim do metodo aplicacaoReceptora
}
