package Chessman;
import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//слон
public class Bishop extends LongRangeChessman {
    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getChessmanMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

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
        return result;
    }
}
