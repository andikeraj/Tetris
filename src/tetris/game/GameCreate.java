package tetris.game;

import java.util.ArrayList;

import tetris.game.pieces.Piece;
import tetris.game.pieces.PieceFactory;

public class GameCreate implements TetrisGame {

    Board board;
    Piece currentPiece;
    Piece nextPiece;
    boolean gamestate = false;
    PieceFactory piece_factory;
    private ArrayList<GameObserver> MyObserver;
    long point = 0;
    int pieceColumn;
    int pieceRow;
    private int comletedRows;

    public GameCreate(Board board, PieceFactory piece_factory) {

        this.board = board;

        this.nextPiece = piece_factory.getNextRandomPiece();
        this.piece_factory = piece_factory;
        this.MyObserver = new ArrayList<>();
        this.comletedRows = 0;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Piece getCurrentPiece() {

        return this.currentPiece;
    }

    @Override
    public Piece getNextPiece() {
        return nextPiece;
    }

    @Override
    public int getNumberOfCompletedRows() {
        return this.comletedRows;
    }

    @Override
    public int getPieceColumn() {
        pieceColumn = currentPiece.getWidth();
        return pieceColumn;
    }

    @Override
    public int getPieceRow() {
        pieceRow = currentPiece.getHeight();
        return pieceRow;
    }

    @Override
    public long getPoints() {
        return point;
    }

    @Override
    public boolean isGameOver() {
        return gamestate;

    }

    @Override
    public boolean moveDown() {
        if (board.canRemovePiece(currentPiece, pieceRow, pieceColumn) && pieceRow != board.getNumberOfRows() - 1) {
            board.removePiece(currentPiece, pieceRow, pieceColumn);
            if (board.canAddPiece(currentPiece, pieceRow + 1, pieceColumn)) {
                board.addPiece(currentPiece, pieceRow + 1, pieceColumn);
                pieceRow++;
                for (GameObserver wentdown : MyObserver) {
                    wentdown.piecePositionChanged();
                }
                return true;
            }
            board.addPiece(currentPiece, pieceRow, pieceColumn);
            return false;
        }
        return false;

    }

    @Override
    public boolean moveLeft() {
        if (board.canRemovePiece(currentPiece, pieceRow, pieceColumn)) {
            board.removePiece(currentPiece, pieceRow, pieceColumn);
            if (board.canAddPiece(currentPiece, pieceRow, pieceColumn - 1)) {
                board.addPiece(currentPiece, pieceRow, pieceColumn - 1);
                pieceColumn--;
                for (GameObserver wentleft : MyObserver) {
                    wentleft.piecePositionChanged();
                }
                return true;
            }
            board.addPiece(currentPiece, pieceRow, pieceColumn);
            return false;
        }
        return false;

    }

    @Override
    public boolean moveRight() {
        if (board.canRemovePiece(currentPiece, pieceRow, pieceColumn)) {
            board.removePiece(currentPiece, pieceRow, pieceColumn);
            if (board.canAddPiece(currentPiece, pieceRow, pieceColumn + 1)) {
                board.addPiece(currentPiece, pieceRow, pieceColumn + 1);
                pieceColumn++;
                for (GameObserver wentright : MyObserver) {
                    wentright.piecePositionChanged();
                }
                return true;
            }
            board.addPiece(currentPiece, pieceRow, pieceColumn);
            return false;
        }
        return false;

    }

    @Override
    public boolean newPiece() {
        int count = board.deleteCompleteRows();
        this.comletedRows += count;

        if (count == 0) {
            point += 0;
        }
        if (count == 1) {
            point += 100;
        }
        if (count == 2) {
            point += 300;
        }
        if (count == 3) {
            point += 500;
        }
        if (count == 4) {
            point += 1000;
        }
        for (GameObserver numberofrows : MyObserver) {
            numberofrows.rowsCompleted();
        }
        this.currentPiece = this.nextPiece;
        if (board.canAddPiece(this.currentPiece, 2, board.getNumberOfColumns() / 2)) {
            board.addPiece(this.currentPiece, 2, board.getNumberOfColumns() / 2);
            this.pieceRow = 2;
            this.pieceColumn = board.getNumberOfColumns() / 2;
            this.nextPiece = piece_factory.getNextRandomPiece();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean rotatePieceClockwise() {
        if (board.canRemovePiece(currentPiece, pieceRow, pieceColumn)) {
            board.removePiece(currentPiece, pieceRow, pieceColumn);
            if (board.canAddPiece(currentPiece.getClockwiseRotation(), pieceRow, pieceColumn)) {
                currentPiece = currentPiece.getClockwiseRotation();
                board.addPiece(currentPiece, pieceRow, pieceColumn);
                for (GameObserver rotation : MyObserver) {
                    rotation.piecePositionChanged();
                }
                return true;
            }
            board.addPiece(currentPiece, pieceRow, pieceColumn);
        }
        return false;
    }

    @Override
    public boolean rotatePieceCounterClockwise() {
        if (board.canRemovePiece(currentPiece, pieceRow, pieceColumn)) {
            board.removePiece(currentPiece, pieceRow, pieceColumn);
            if (board.canAddPiece(currentPiece.getCounterClockwiseRotation(), pieceRow, pieceColumn)) {
                currentPiece = currentPiece.getCounterClockwiseRotation();
                board.addPiece(currentPiece, pieceRow, pieceColumn);
                for (GameObserver rotation : MyObserver) {
                    rotation.piecePositionChanged();
                }
                return true;
            }
            board.addPiece(currentPiece, pieceRow, pieceColumn);
        }
        return false;
    }

    @Override
    public void setGameOver() {
        for (GameObserver MyObserver : MyObserver) {
            MyObserver.gameOver();

        }
        gamestate = true;
    }

    @Override
    public void step() {
        if (currentPiece == null) {
            newPiece();
        } else {
            if (moveDown()) {
                System.out.println();
            }
            if (moveDown() == false) {

                for (GameObserver piecelanded : MyObserver) {
                    piecelanded.pieceLanded();
                }

                if (newPiece() == false) {
                    setGameOver();
                }

            }
        }

    }

    @Override
    public void addObserver(GameObserver observer) {
        MyObserver.add(observer);

    }

    @Override
    public void removeObserver(GameObserver observer) {
        MyObserver.remove(observer);

    }

}
