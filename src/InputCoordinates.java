import Chessman.Chessman;
import Chessman.King;
import Defalt.*;


import java.util.*;

//ввод координат и их проверка
public class InputCoordinates {
    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input() {
        while (true) {
            System.out.println("Напишите координату (например а1)");

            String line = scanner.nextLine();

            if (line.length() != 2) {
                System.out.println("Неправильный формат");
                continue;
            }

            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);

            if (!Character.isLetter(fileChar)) {
                System.out.println("Неправильный формат");
                continue;
            }

            if (!Character.isDigit(rankChar)) {
                System.out.println("Неправильный формат");
                continue;

            }

            int rank = Character.getNumericValue(rankChar);
            if (rank < 1 || rank > 8) {
                System.out.println("Неправильный формат");
                continue;

            }

            File file = File.fromChar(fileChar);
            if (file == null) {
                System.out.println("Неправильный формат");
                continue;
            }

            return new Coordinates(file, rank);
        }
    }


    //вводятся координаты, но по цветам
    public static Coordinates inputChessmanCoordinatesForColor(Color color, Board board) {
        while (true) {
            System.out.println("Напишите координаты фигуры, которую хотите сдвинуть");
            Coordinates coordinates = input();

            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Здесь нет фигуры");
                continue;
            }
            Chessman chessman = board.getChessman(coordinates);
            //проверка цвета, если не совпадает с текущим ходом нельзя двигать
            if (chessman.color != color) {
                System.out.println("Не тот цвет");
                continue;
            }

            Set<Coordinates> availableMoveSquares = chessman.getAvailableMoves(board);
            if (availableMoveSquares.size() == 0) {
                System.out.println("Некуда ходить");
                continue;
            }
            return coordinates;
        }

    }


    //ввести координаты из набора
    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
        while (true) {
            System.out.println("Введите ход для выбранной фигуры");
            Coordinates input = input();

            if (!coordinates.contains(input)) {
                System.out.println("Некуда ходить");
                continue;
            }
            return input;
        }
    }

    public static Move inputMove(Board board, Color color, BoardConsole renderer) {
        while (true) {
            // input
            Coordinates sourceCoordinates = InputCoordinates.inputChessmanCoordinatesForColor(
                    color, board
            );

            Chessman chessman = board.getChessman(sourceCoordinates);
            Set<Coordinates> availableMoveSquares = chessman.getAvailableMoves(board);

            renderer.render(board, chessman);
            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);

            Move move = new Move(sourceCoordinates, targetCoordinates);

            if (validateIfKingInCheckAfterMove(board, color, move)) {
                System.out.println("Шах твоему королю!");
                continue;
            }

            return move;
        }
    }



    public static Move chooseMove(Board board, Color color, BoardConsole renderer) {
        List<Chessman> chessmen = board.getChessmanByColor(color);
        Random random = new Random();

        for (Chessman chessman1 : chessmen) {
            Set<Coordinates> availableMoveSquares = chessman1.getAvailableMoves(board);

            for (Coordinates targetCoordinates : availableMoveSquares) {
                Move move = new Move(chessman1.coordinates, targetCoordinates);

                if (board.getChessman(targetCoordinates) != null && board.getChessman(targetCoordinates).color != color) {
                    if (validateIfKingInCheckAfterMove(board, color, move)) {
                        return getKingMove(board,color,renderer);
                    }
                    renderer.render(board, chessman1);
                    return move;
                }
            }
        }

        Chessman randomChessman = chessmen.get(random.nextInt(chessmen.size()));
        Set<Coordinates> randomMoveSquares = randomChessman.getAvailableMoves(board);
        Coordinates randomTargetCoordinates = randomMoveSquares.stream().findAny().orElse(null);

        Move move = new Move(randomChessman.coordinates, randomTargetCoordinates);

        if (validateIfKingInCheckAfterMove(board, color, move)) {
            return getKingMove(board,color,renderer);
        }

        renderer.render(board, randomChessman);
        return move;
    }

    private static Move getKingMove (Board board, Color color, BoardConsole renderer) {
        Chessman king = board.getChessmanByColor(color).stream().filter(chessman -> chessman instanceof King).findFirst().get();
        Set<Coordinates> kingMoves = king.getAvailableMoves(board);
        Coordinates randomTargetCoordinatesKing = kingMoves.stream().findAny().orElse(null);
        renderer.render(board, king);
        return new Move(king.coordinates, randomTargetCoordinatesKing);
    }

    private static boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
        Board copy = (new BoardFactory()).copy(board);
        copy.moveChessman(move);

        // we trust that there is king on the board
        Chessman king = copy.getChessmanByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();
        return copy.isSquareAttackedByColor(king.coordinates, color.opposite());
    }


}
