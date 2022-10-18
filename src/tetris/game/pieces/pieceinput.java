package tetris.game.pieces;

import org.hamcrest.core.IsInstanceOf;

public class pieceinput implements Piece {
    int width;
    int height;
    boolean[][] body;

    Point pointrot;
    PieceType piecetype;
    Piece clone;
    Piece otherpiece;

    public pieceinput(int width, int height, boolean[][] body, Point pointrot, PieceType piecetype) {
        this.width = width;
        this.height = height;
        this.body = body;
        this.pointrot = pointrot;
        this.piecetype = piecetype;
    }

    @Override
    public Piece clone() {

        return clone;
    }

    @Override
    public boolean[][] getBody() {
        return body;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Piece)) {
            return false;
        }
        Piece otherpiece = (Piece) object;
        if (this.getPieceType() != otherpiece.getPieceType()) {
            return false;
        }
        if (this.getWidth() != otherpiece.getWidth()) {
            return false;
        }
        if (this.getHeight() != otherpiece.getHeight()) {
            return false;
        }
        if (!(this.getRotationPoint().equals(otherpiece.getRotationPoint()))){
            return false;
        }
        for (int i = 0; i < this.getHeight(); i++)
			for (int j = 0; j < this.getWidth(); j++)
				if (this.getBody()[i][j] != otherpiece.getBody()[i][j])
					return false;
        
        return true;
    }

    @Override
    public Piece getClockwiseRotation() {
        int newheight = getWidth();
        int newwidth = getHeight();
        Point pointrot = getRotationPoint();
        boolean[][] body = getBody();
        boolean[][] rot = new boolean[newheight][newwidth];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {

                rot[j][getHeight() - i - 1] = body[i][j];

            }

        }

        Point newrotpos = new Point(pointrot.getColumn(), getHeight() - pointrot.getRow() - 1);
        pieceinput otherpiece = new pieceinput(newwidth, newheight, rot, newrotpos, piecetype);
        if(this.equals(otherpiece)){
            return this;
        }
        return otherpiece;
    }

    @Override
    public Piece getCounterClockwiseRotation() {
        int newcounthight = getWidth();
        int newcountwidth = getHeight();
        Point pointrot = getRotationPoint();
        boolean[][] body = getBody();
        boolean[][] countrot = new boolean[newcounthight][newcountwidth];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {

                countrot[getWidth() - j - 1][i] = body[i][j];

            }
        }
        Point newcountrotpos = new Point(getWidth() - pointrot.getColumn() - 1, pointrot.getRow());
        pieceinput otherpiece = new pieceinput(newcountwidth, newcounthight, countrot, newcountrotpos, piecetype);
        return otherpiece;
    }

    @Override
    public int getHeight() {

        return height;
    }

    @Override
    public PieceType getPieceType() {

        return piecetype;
    }

    @Override
    public Point getRotationPoint() {

        return pointrot;
    }

    @Override
    public int getWidth() {

        return width;
    }

}
