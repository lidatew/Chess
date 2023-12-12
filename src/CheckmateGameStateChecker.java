import Chessman.Chessman;
import Chessman.King;
import Defalt.*;

import java.util.List;
import java.util.Set;

public class CheckmateGameStateChecker extends GameStateChecker{

    @Override
    public GameState check(Board board, Color color) {
        //проверяет поставлен ли королю шах
        //проверяет что нет ходов для короля

        //обуславливаемся что король на доске

        Chessman king = board.getChessmanByColor(color).stream().filter(chessman -> chessman instanceof King).findFirst().get();

        if (!board.isSquareAttackedByColor(king.coordinates, color.opposite())) {
            return GameState.ONGOING;
        }

        List<Chessman> chessmen = board.getChessmanByColor(color);
        for (Chessman chessman : chessmen) {
            Set<Coordinates> availableMoveSquares = chessman.getAvailableMoves(board);

            for (Coordinates coordinates : availableMoveSquares) {
                Board clone = new BoardFactory().copy(board);
                clone.moveChessman(new Move(chessman.coordinates, coordinates));

                Chessman clonedKing = clone.getChessmanByColor(color).stream().filter(c -> c instanceof King).findFirst().get();

                if (!clone.isSquareAttackedByColor(clonedKing.coordinates, color.opposite())) {
                    return GameState.ONGOING;
                }
            }
        }

        if (color == Color.WHITE) {
            return GameState.ШАХ_И_МАТ_БЕЛОМУ_КОРОЛЮ;
        } else {
            return GameState.ШАХ_И_МАТ_ЧЕРНОМУ_КОРОЛЮ;
        }
    }
}
