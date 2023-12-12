package Chessman;

import Defalt.Color;
import Defalt.Coordinates;
import Defalt.Board;


import java.util.HashSet;
import java.util.Set;

abstract public class Chessman {
    //можно смотреть какой цвет, но не изменять
    public final Color color;
    public Coordinates coordinates;

    public Chessman(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    //метод, который возвращает свободные клетки куда можно переместиться
    public Set<Coordinates> getAvailableMoves(Board board) {
        //набор клеток куда мы можем сходить
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift : getChessmanMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isSquaredAvailableForMove(newCoordinates, board)) {
                    result.add(newCoordinates);
                }
            }

        }
        return result;
    }

    //метод проверяющий есть ли фигура противника на месте куда хотим переместиться
    public boolean isSquaredAvailableForMove(Coordinates newCoordinates, Board board) {
        return board.isSquareEmpty(newCoordinates) || board.getChessman(newCoordinates).color != color;
    }

    protected abstract Set<CoordinatesShift> getChessmanMoves();

    protected Set<CoordinatesShift> getChessmanAttacks() {
        return getChessmanMoves();
    }

    public Set<Coordinates> getAttackedSquares(Board board) {
        Set<CoordinatesShift> pieceAttacks = getChessmanAttacks();
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift pieceAttack : pieceAttacks) {
            if (coordinates.canShift(pieceAttack)) {
                Coordinates shiftedCoordinates = coordinates.shift(pieceAttack);

                if (isSquareAvailableForAttack(shiftedCoordinates, board)) {
                    result.add(shiftedCoordinates);
                }
            }
        }

        return result;
    }



    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        return true;
    }





}
