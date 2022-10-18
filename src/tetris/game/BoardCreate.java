package tetris.game;

import tetris.game.pieces.Piece;
import tetris.game.pieces.Point;
import tetris.game.pieces.Piece.PieceType;

public class BoardCreate implements Board {
    int myrow;
    int mycolumn;
    PieceType[][] board;

    public BoardCreate(int myrow, int mycolumn) {
        this.myrow = myrow;
        this.mycolumn = mycolumn;
        this.board = new PieceType[myrow][mycolumn];
        for (int i = 0; i < myrow; i++) {
            for (int j = 0; j < mycolumn; j++) {
                board[i][j] = null;
            }
        }
    }

    @Override
    public Board clone() {
        BoardCreate copiedboard = new BoardCreate(myrow, mycolumn);
        for (int i = 0; i < myrow; i++) {
            for (int j = 0; j < mycolumn; j++) {
                board[i][j] = this.board[i][j];
            }
        }
        return copiedboard;
    }

    @Override
    public void addPiece(Piece piece, int row, int column) {
        if (canAddPiece(piece, row, column) == false) {
            throw new IllegalArgumentException();
        } else {
            boolean[][] body = piece.getBody();
            for (int i = 0; i < piece.getHeight(); i++) {
                for (int j = 0; j < piece.getWidth(); j++) {
                    if (body[i][j] == true) {
                        int rindex = i - piece.getRotationPoint().getRow();
                        int cindex = j - piece.getRotationPoint().getColumn();
                        board[row + rindex][column + cindex] = piece.getPieceType();

                    }
                }
            }
        }

    }

    @Override
    public boolean canAddPiece(Piece piece, int row, int column) {
        if (piece == null) {
            throw new IllegalArgumentException();
        }
        if (row < 0 || column < 0) {
            return false;
        }
        boolean[][] body = piece.getBody();
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(); j++) {
                if (body[i][j] == true) {
                    int rindex = i - piece.getRotationPoint().getRow();
                    int cindex = j - piece.getRotationPoint().getColumn();
                    if (row + rindex >= myrow || row + rindex < 0 || column + cindex >= mycolumn ||
                            column + cindex < 0) {
                        return false;
                    } else if (board[row + rindex][column + cindex] != null) {
                        return false;

                    }

                }
            }
        }

        return true;
    }

    @Override
    public boolean canRemovePiece(Piece piece, int row, int column) {

        if (piece == null) {
            throw new IllegalArgumentException();
        }
        if (row < 0 || column < 0) {
            return false;
        }
        if (row > myrow || column > mycolumn) {
            return false;
        }
        if (board[row][column] == null && board[row][column] != piece.getPieceType()) {
            return false;
        }
        boolean[][] body = piece.getBody();
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(); j++) {
                if (body[i][j] == true) {
                    int rindex = i - piece.getRotationPoint().getRow();
                    int cindex = j - piece.getRotationPoint().getColumn();
                    if (row + rindex < 0 || column + cindex < 0 || row + rindex >= myrow
                            || column + cindex >= mycolumn) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int deleteCompleteRows() {
        int completedrows = 0;
        boolean delete = true;
        for (int i = 0; i < myrow; i++) {
            for (int j = 0; j < mycolumn; j++) {
                if (board[i][j] == null) {
                    delete=false;
                }
            }
            if(delete){
                completedrows++;
                for(int k = i; k > 0; k--) {
                    for(int j = 0; j < mycolumn; j++) {
                        board[k][j] = board[k-1][j];
                    }
                }
            }
            delete = true;
        }
        return completedrows;
    }

    @Override
    public PieceType[][] getBoard() {
        return board;
    }

    @Override
    public int getNumberOfColumns() {
        return mycolumn;
    }

    @Override
    public int getNumberOfRows() {
        return myrow;
    }

    @Override
    public void removePiece(Piece piece, int row, int column) {
        if (canRemovePiece(piece, row, column) && piece != null) {
            boolean[][] body = piece.getBody();
            for (int i = 0; i < piece.getHeight(); i++) {
                for (int j = 0; j < piece.getWidth(); j++) {
                    if (body[i][j] == true) {
                        int rindex = i - piece.getRotationPoint().getRow();
                        int cindex = j - piece.getRotationPoint().getColumn();
                        board[row + rindex][column + cindex] = null;
                    }
                }
            }
        } else
            throw new IllegalArgumentException();
    }

}
