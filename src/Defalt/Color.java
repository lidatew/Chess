package Defalt;

public enum Color {
    BLACK,
    WHITE;
    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
    }

}
