package Chessman;
import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Defalt.Color.*;

//пешка
public class Pawn extends Chessman {
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }



    @Override
    protected Set<CoordinatesShift> getChessmanMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        if (color == WHITE) {
            result.add(new CoordinatesShift(0, 1));

            if (coordinates.rank == 2) {
                result.add(new CoordinatesShift(0, 2));
            }

            result.add(new CoordinatesShift(-1, 1));
            result.add(new CoordinatesShift(1, 1));
        } else {
            result.add(new CoordinatesShift(0, -1));

            if (coordinates.rank == 7) {
                result.add(new CoordinatesShift(0, -2));
            }

            result.add(new CoordinatesShift(-1, -1));
            result.add(new CoordinatesShift(1, -1));
        }

        return result;
    }



    @Override
    protected Set<CoordinatesShift> getChessmanAttacks() {
        Set<CoordinatesShift> result = new HashSet<>();

        if (color == WHITE) {
            result.add(new CoordinatesShift(-1, 1));
            result.add(new CoordinatesShift(1, 1));
        } else {
            result.add(new CoordinatesShift(-1, -1));
            result.add(new CoordinatesShift(1, -1));
        }

        return result;
    }

    @Override
    public boolean isSquaredAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.file == coordinates.file) {
            int rankShift = Math.abs(this.coordinates.rank - coordinates.rank);

            if (rankShift == 2) {
                List<Coordinates> between = Board.getVerticalCoordinatesBetween(this.coordinates, coordinates);

                return (board.isSquareEmpty(between.get(0))) && board.isSquareEmpty(coordinates);
            } else {
                return board.isSquareEmpty(coordinates);
            }
        } else {
            if (board.isSquareEmpty(coordinates)) {
                return false;
            } else {
                return board.getChessman(coordinates).color != color;
            }
        }
    }
}
