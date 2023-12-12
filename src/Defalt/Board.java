package Defalt;

import Chessman.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {

     public final String startingFen;
     HashMap<Coordinates, Chessman> chessmen = new HashMap<>();

     public List<Move> moves = new ArrayList<>();

     public Board(String startingFen) {
          this.startingFen = startingFen;
     }

     //cтавим фигуру
     public  void  setChessman( Coordinates coordinates, Chessman chessman){
          //обновляем координаты
          chessman.coordinates = coordinates;
          //ставим
          chessmen.put(coordinates,chessman);
     }

     //метод который удаляет фигуру
     public void removeChessman(Coordinates coordinates){
          chessmen.remove(coordinates);
     }

     //перенос фигуры
     public void moveChessman(Move move){
          Chessman chessman = getChessman(move.from);

          removeChessman(move.from);
          setChessman(move.to, chessman);

          moves.add(move);
     }

     //проверка стоит ли фигура
     public boolean isSquareEmpty(Coordinates coordinates){
          return !chessmen.containsKey(coordinates);
     }


     //какая фигура стоит
     public Chessman getChessman(Coordinates coordinates){
          return chessmen.get(coordinates);
     }


     public static boolean isSquareColor(Coordinates coordinates){
          //ordinal номер в списке
          //+1 потому что не с 0 а с 1 на доске
          return ((coordinates.file.ordinal()+1)+ coordinates.rank)%2 == 0;
     }

     //промежуточные ячейки для слона и ферзя (чтобы убрать перепрыгивание фигуры)
     public static List<Coordinates> getDiagonalCoordinatesBetween(Coordinates source, Coordinates target) {
          // допущение - клетки лежат на одной диагонали (две другие фигуры)

          List<Coordinates> result = new ArrayList<>();

          int fileShift = source.file.ordinal() < target.file.ordinal() ? 1 : -1;
          int rankShift = source.rank < target.rank ? 1 : -1;

          for (
                  int fileIndex = source.file.ordinal() + fileShift,
                  rank = source.rank + rankShift;

                  fileIndex != target.file.ordinal() && rank != target.rank;

                  fileIndex += fileShift, rank += rankShift
          ) {
               result.add(new Coordinates(File.values()[fileIndex], rank));
          }

          return result;
     }

     //промежуточные ячейки(вертикаль) для ладьи и ферзя (чтобы убрать перепрыгивание фигуры)
     public static List<Coordinates> getVerticalCoordinatesBetween(Coordinates source, Coordinates target) {
          // допущение - клетки лежат на одной вертикали (две другие фигуры)

          List<Coordinates> result = new ArrayList<>();

          int rankShift = source.rank < target.rank ? 1 : -1;

          for (int rank = source.rank + rankShift; rank != target.rank; rank += rankShift) {
               result.add(new Coordinates(source.file, rank));
          }

          return result;
     }

     //промежуточные ячейки(горизонталь) для ладьи и ферзя (чтобы убрать перепрыгивание фигуры)
     public static List<Coordinates> getHorizontalCoordinatesBetween(Coordinates source, Coordinates target) {
          // допущение - клетки лежат на одной горизонтали (две другие фигуры)

          List<Coordinates> result = new ArrayList<>();

          int fileShift = source.file.ordinal() < target.file.ordinal() ? 1 : -1;

          for (
                  int fileIndex = source.file.ordinal() + fileShift; fileIndex != target.file.ordinal(); fileIndex += fileShift) {
               result.add(new Coordinates(File.values()[fileIndex], source.rank));
          }

          return result;
     }


     public List<Chessman> getChessmanByColor(Color color) {
          List<Chessman> result = new ArrayList<>();

          for (Chessman chessman : chessmen.values()) {
               if (chessman.color == color) {
                    result.add(chessman);
               }
          }

          return result;
     }

     public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
          List<Chessman> chessmen = getChessmanByColor(color);

          for (Chessman chessman : chessmen) {
               Set<Coordinates> attackedSquares = chessman.getAttackedSquares(this);

               if (attackedSquares.contains(coordinates)) {
                    return true;
               }
          }

          return false;
     }





}
