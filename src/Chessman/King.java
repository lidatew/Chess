package Chessman;
import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.HashSet;
import java.util.Set;

//король
public class King extends Chessman {
    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getChessmanMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int fileShift = -1; fileShift <= 1; fileShift++) {
            for (int rankShift = -1; rankShift <= 1; rankShift++) {
                if ((fileShift == 0) && (rankShift == 0)) {
                    continue;
                }

                result.add(new CoordinatesShift(fileShift, rankShift));
            }
        }

        return result;
    }

    @Override
    public boolean isSquaredAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquaredAvailableForMove(coordinates, board);

        if (result) {
            return !board.isSquareAttackedByColor(coordinates, color.opposite());
        }

        return false;
    }
}
