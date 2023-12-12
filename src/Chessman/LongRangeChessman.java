package Chessman;

import Defalt.Board;
import Defalt.Color;
import Defalt.Coordinates;

import java.util.List;

//класс для ладьи слона и королевы, где описываются методы,
// чтобы фигуры не перепрыгивали другие на одной диагонали, вертикали или горизонтали
public abstract class LongRangeChessman extends Chessman {


    public LongRangeChessman(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public boolean isSquaredAvailableForMove(Coordinates newCoordinates, Board board) {
        boolean result = super.isSquaredAvailableForMove(newCoordinates, board);

        if (result) {
            return isSquareAvailableForAttack(newCoordinates, board);
        } else {
            return false;
        }
    }

    @Override
    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        List<Coordinates> coordinatesBetween;
        if (this.coordinates.file == coordinates.file) {
            coordinatesBetween = Board.getVerticalCoordinatesBetween(this.coordinates, coordinates);
        } else if (this.coordinates.rank.equals(coordinates.rank)) {
            coordinatesBetween = Board.getHorizontalCoordinatesBetween(this.coordinates, coordinates);
        } else {
            coordinatesBetween = Board.getDiagonalCoordinatesBetween(this.coordinates, coordinates);
        }

        for (Coordinates c : coordinatesBetween) {
            if (!board.isSquareEmpty(c)) {
                return false;
            }
        }

        return true;
    }
}
