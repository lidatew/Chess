package Defalt;

import Chessman.CoordinatesShift;

import java.util.Objects;

public class Coordinates {
    //вертикаль
    public final File file;
    //горизонталь
    public final Integer rank;

    public Coordinates(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return file == that.file && Objects.equals(rank, that.rank);
    }

    //метод расчитывающий сдвиг
    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(File.values()[this.file.ordinal() + shift.fileShift], this.rank + shift.rankShift);
    }

    //метод котрый проверяет можно ли вообще сдвинуть фигуру
    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;
        if (f < 0 || f > 7)
            return false;
        if (r < 1 || r > 8)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                 file + rank +
                '}';
    }
}
