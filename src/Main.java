import Defalt.Board;
import Defalt.BoardFactory;


public class Main {
    public static void main(String[] args) {
        Board board = (new BoardFactory()).fromFEN(
                //"8/4k3/8/6B1/2Q5/4R3/8/3K4 w - - 0 1" //проверка бота(короля)
                "rnp1kbnr/pp2pppp/N6q/3p4/2P1P2B/8/PP1P1PPP/R1BQK1NR w KQkq - 0 1" //проверка бота
                //"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" // обычная расстановка
                //"1n2k1n1/p3p1pp/1pb1rp2/2p1p3/3K4/4r3/2q2b2/8 w - - 0 1" // у короля не осталось ходов
                //"4k3/p5pp/2P2n2/8/3K4/8/2n5/8 w - - 0 1" // шах

        );
        Game game = new Game(board);
        game.gameloop();
    }
}