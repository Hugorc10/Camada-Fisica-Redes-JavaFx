package model;

public enum LinePositions {
    // Define a largura da linha do clock
    CLOCK_LINE_WIDTH(60),
    // Posicao do eixo vertical que ira representar a transicao do sinal positivo
    POSITIVE_SIGNAL_Y(250),
    // Posicao do eixo vertical que ira representar a transicao do sinal negativo
    NEGATIVE_SIGNAL_Y(350),
    // Posicao inicial da animacoes das linhas no eixo vertical
    STREAM_X(250);
    
    private int value;
    
    LinePositions(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
