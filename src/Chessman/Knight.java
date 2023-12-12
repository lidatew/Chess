package Chessman;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//конь
public class Knight extends Chessman {
    public Knight(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    //не переопределяем метод isSquaredAvailableForMove потому что конь может перепрыгивать другие фигуры
    @Override
    protected Set<CoordinatesShift> getChessmanMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1,2),
                new CoordinatesShift(2,1),

                new CoordinatesShift(2,-1),
                new CoordinatesShift(1,-2),

                new CoordinatesShift(-2,-1),
                new CoordinatesShift(-1,-2),

                new CoordinatesShift(-2,1),
                new CoordinatesShift(-1,2)
        ));
    }
}
