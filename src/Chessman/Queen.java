package Chessman;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.HashSet;
import java.util.Set;

//ферзь
public class Queen extends LongRangeChessman {
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getChessmanMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        //ходы слона
        //снизу слева > сверху справа и наоборот
        for(int i = -7;i<=7;i++){
            if(i==0)continue;
            result.add(new CoordinatesShift(i,i));
        }
        //сверху слева > снизу справа и наоборот
        for(int i = -7;i<=7;i++){
            if(i==0)continue;
            result.add(new CoordinatesShift(i,-i));
        }

        //ходы ладьи
        // сверху вниз и наоборот
        for (int i = -9; i < 9; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(i, 0));
        }

        // снизу вверх и наоборот
        for (int i = -9; i < 9; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(0, i));
        }
        return result;
    }
}
