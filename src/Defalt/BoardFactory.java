package Defalt;

//класс создающий доску ссылками
public class BoardFactory {
    private final ChessmanFactory chessmanFactory = new ChessmanFactory();



    //считывание ссылки и разбивка ее для расставления фигур
    public Board fromFEN (String fen){
        Board board = new Board(fen);
        String[] parts = fen.split(" ");
        String chessmanPositions = parts[0];

        String[] fenRows = chessmanPositions.split("/");
        for (int i = 0; i < fenRows.length; i++) {
            String row = fenRows[i];
            int rank = 8 - i;
            int fileIndex = 0;
            for (int j = 0; j < row.length(); j++) {
                char fenChar = row.charAt(j);

                if (Character.isDigit(fenChar)) {
                    fileIndex += Character.getNumericValue(fenChar);
                } else {
                    File file = File.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(file, rank);

                    board.setChessman(coordinates, chessmanFactory.fromFenChar(fenChar, coordinates));
                    fileIndex++;
                }
            }
        }
        return board;
    }

    public Board copy(Board source) {
        Board clone = fromFEN(source.startingFen);

        for (Move move : source.moves) {
            clone.moveChessman(move);
        }

        return clone;
    }

}
