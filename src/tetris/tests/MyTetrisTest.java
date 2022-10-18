package tetris.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tetris.game.MyTetrisFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.PieceExercise;
import prog2.tests.tetris.pub.TestUtil;
import tetris.game.MyTetrisFactory;
import tetris.game.pieces.Piece;
import tetris.game.pieces.Piece.PieceType;
import tetris.game.pieces.PieceFactory;


/**
 * Within this class and/or package you can implement your own tests.
 *
 * Note that no classes or interfaces will be available, except those initially
 * provided. Make use of {@link MyTetrisFactory} to get other factory instances.
 */

public class MyTetrisTest {
	@Rule
	public TestRule timeout = TestUtil.timeoutSeconds(5);

	private final static long SEED = 343681;

	PieceFactory pf = MyTetrisFactory.createPieceFactory(new Random(SEED));

	public boolean checkPieceEquals(Piece p0, Piece p1) {
		if (p0.getPieceType() != p1.getPieceType())
			return false;
		if (p0.getWidth() != p1.getWidth())
			return false;
		if (p0.getHeight() != p1.getHeight())
			return false;
		for (int i = 0; i < p0.getHeight(); i++)
			for (int j = 0; j < p0.getWidth(); j++)
				if (p0.getBody()[i][j] != p1.getBody()[i][j])
					return false;
		return true;
	}

	public void assertRotationsEqual(Piece start, int rotations) throws Exception {
		Piece rotation = start;
		for (int i = 0; i < rotations; i++) {
			rotation = rotation.getClockwiseRotation();
		}
		assertTrue(start.getPieceType().toString() + " piece is not equal to the piece got after " + rotations
				+ " rotations ", checkPieceEquals(start, rotation));
		assertEquals(
				start.getPieceType().toString() + " piece type is not equal to the piece type got after rotations ",
				start.getPieceType(), rotation.getPieceType());
	}
	@Test
	public void test() {
		assertNotNull(MyTetrisFactory.createBoard(MyTetrisFactory.DEFAULT_ROWS, MyTetrisFactory.DEFAULT_COLUMNS));
	}
	@Test
	public void testOPieceRotations() throws Exception {
		assertRotationsEqual(pf.getOPiece(), 4);
	}
	
		
		
	
	

}
