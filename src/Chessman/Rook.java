package Chessman;

import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//ладья
public class Rook extends LongRangeChessman {
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getChessmanMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        // сверху вниз и наоборот
        for (int i = -9; i < 9; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(i, 0));
        }

        // снизу вверх и наоброт
        for (int i = -9; i < 9; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(0, i));
        }
        return result;
    }




}
