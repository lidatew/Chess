import Chessman.Chessman;
import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;
import Defalt.Move;

import java.util.List;
import java.util.Set;

//описывает цикл ходов (белые>черные)
public class Game {
    private final Board board;

    private final BoardConsole renderer = new BoardConsole();

    private final List<GameStateChecker> checkers = List.of(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );

    public Game(Board board) {
        this.board = board;
    }

    public void gameloop() {
        Color colorToMove = Color.WHITE;

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            renderer.render(board);

            if (colorToMove == Color.WHITE) {
                System.out.println("Белые ходят");


                Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

                // make move
                board.moveChessman(move);

                colorToMove = colorToMove.opposite();
            }
            if (colorToMove == Color.BLACK) {
                Move move2 = InputCoordinates.chooseMove(board, colorToMove, renderer);

                board.moveChessman(move2);

                colorToMove = colorToMove.opposite();
            }

            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);
        System.out.println("Игра закончилась " + state);


    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }

        return GameState.ONGOING;
    }
}
