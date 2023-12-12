import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;
import Defalt.File;
import Chessman.Chessman;

import java.util.Set;

import static java.util.Collections.emptySet;

//визуализация доски
public class BoardConsole {
    //константы для раскрашивания
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";
    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

    public void render(Board b, Chessman chessmanToMove) {
        Set<Coordinates> availableMoveSquares = emptySet();

        if (chessmanToMove != null) {
            availableMoveSquares = chessmanToMove.getAvailableMoves(b);
        }
        int a = 8;
        String lineABC ="  "+ "A" + "\u2009" + "\u200a" + " " + " "+ "B" + "\u2009" + "\u200a" + " " + " "+"C" + "\u2009" + "\u200a" + " " + " "+"D" + "\u2009" + "\u200a" + " " + " "+
                "E" + "\u2009" + "\u200a" + " " + " "+"F" + "\u2009" + "\u200a" + " " + " "+"G" + "\u2009" + "\u200a" + " " + " "+"H" + "\u2009" + "\u200a" + " " + " ";
        for (int rank = 8; rank >= 1; rank--) {
            String line = ""+a;
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighlight = availableMoveSquares.contains(coordinates);


                if (b.isSquareEmpty(coordinates)) {
                    line += (getSpriteForEmptySquare(coordinates, isHighlight));
                } else {
                    line += getChessmanSprite(b.getChessman(coordinates), isHighlight);
                }
            }
            line += (ANSI_RESET);
            a--;
            System.out.println(line);
        }
        System.out.println(lineABC);
    }

    public void render(Board b) {
        render(b,null);
    }


        private String getChessmanSprite(Chessman chessman, boolean isHighlight) {
        return colorizeSprite(" " + selectUnicodeSpriteForChessman(chessman) + " ", chessman.color,
                Board.isSquareColor(chessman.coordinates), isHighlight);
    }


    //у каждой фигуры в юникоде есть свой спрайт
    private String selectUnicodeSpriteForChessman(Chessman chessman) {
        switch (chessman.getClass().getSimpleName()) {
            case "Pawn":
                return "♙";
            case "Knight":
                return "♞";

            case "Bishop":
                return "♝";

            case "Rook":
                return "♜";

            case "Queen":
                return "♛";

            case "King":
                return "♚";
        }
        return "";
    }


    //метод который красить спарйт
    private String colorizeSprite(String sprite, Color chessmanColor, boolean isSquareColor, boolean isHighlight) {
        String result = sprite;

        if (chessmanColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isHighlight) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareColor) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }

        return result;
    }

    //поля клеток
    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighlight) {
        return colorizeSprite("\u2009" + "\u200a" + " " + " " + " ", Color.WHITE,
                Board.isSquareColor(coordinates), isHighlight);
    }
}
