import Chessman.Chessman;
import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.List;
import java.util.Set;

public class StalemateGameStateChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color) {
        List<Chessman> chessmen = board.getChessmanByColor(color);

        for (Chessman chessman : chessmen) {
            Set<Coordinates> availableMoveSquares = chessman.getAvailableMoves(board);

            if (availableMoveSquares.size() > 0) {
                return GameState.ONGOING;
            }
        }

        return GameState.У_КОРОЛЯ_НЕ_ОСТАЛОСЬ_ХОДОВ;
    }
}
