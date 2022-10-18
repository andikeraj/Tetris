package tetris.game.pieces;

import java.util.Random;
import tetris.game.pieces.Piece.PieceType;

public class piececreate implements PieceFactory {
    boolean[][] body;
    Point pointrot;
    Random r;
    int rotate;

    public piececreate(Random r) {
        this.r = r;
    }

    @Override
    public Piece getIPiece() {
        boolean[][] body = new boolean[][] {
                { true },
                { true },
                { true },
                { true }
        };
        Point pointrot = new Point(1, 0);
        pieceinput Ipiece = new pieceinput(1, 4, body, pointrot, PieceType.I);
        rotate = 0;
        return Ipiece;
    }

    @Override
    public Piece getJPiece() {
        boolean[][] body = new boolean[][] {
                { false, true },
                { false, true },
                { true, true },
        };
        Point pointrot = new Point(1, 1);
        pieceinput jpiece = new pieceinput(2, 3, body, pointrot, PieceType.J);
        return jpiece;
    }

    @Override
    public Piece getLPiece() {
        boolean[][] body = new boolean[][] {
                { true, false },
                { true, false },
                { true, true },
        };
        Point pointrot = new Point(1, 0);
        pieceinput lpiece = new pieceinput(2, 3, body, pointrot, PieceType.L);

        return lpiece;
    }

    @Override
    public Piece getOPiece() {
        boolean[][] body = new boolean[][] {
                { true, true },
                { true, true },
        };
        Point pointrot = new Point(1, 1);
        pieceinput opiece = new pieceinput(2, 2, body, pointrot, PieceType.O);
        return opiece;
    }

    @Override
    public Piece getSPiece() {
        boolean[][] body = new boolean[][] {
                { false, true, true },
                { true, true, false },

        };
        Point pointrot = new Point(1, 1);
        pieceinput spiece = new pieceinput(3, 2, body, pointrot, PieceType.S);
        return spiece;
    }

    @Override
    public Piece getTPiece() {
        boolean[][] body = new boolean[][] {
                { true, true, true },
                { false, true, false },
        };
        Point pointrot = new Point(0, 1);
        pieceinput tpiece = new pieceinput(3, 2, body, pointrot, PieceType.T);
        return tpiece;
    }

    @Override
    public Piece getZPiece() {
        boolean[][] body = new boolean[][] {
                { true, true, false },
                { false, true, true },

        };
        Point pointrot = new Point(1, 1);
        pieceinput zpiece = new pieceinput(3, 2, body, pointrot, PieceType.Z);
        return zpiece;
    }

    @Override
    public Piece getNextRandomPiece() {
        int randint = this.r.nextInt(7);
        switch (randint) {
            case 0:
                return getIPiece();
            case 1:
                return getJPiece();
            case 2:
                return getLPiece();
            case 3:
                return getOPiece();
            case 4:
                return getSPiece();
            case 5:
                return getZPiece();
            case 6:
                return getTPiece();
            default : return null;
        }
        
    }

}
