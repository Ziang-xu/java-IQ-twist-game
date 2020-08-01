package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BigGridTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    private HashMap<Character, Integer> verPosition = new HashMap<Character, Integer>() {
        {
            put('A', 0);
            put('B', 1);
            put('C', 2);
            put('D', 3);
        }
    };

    private void testPutPeg(Peg pegIn, String positionIn, String expectedColor, int expectedStatus) {
        assert positionIn.length() == 2;
        BigGrid bg = new BigGrid();
        int horiPosition = Integer.parseInt(positionIn.substring(0, 1));
        int vertPosition = verPosition.get(positionIn.charAt(1));
        bg.putPeg(pegIn, positionIn);
        int outStatus = bg.getGridStatus()[horiPosition-1][vertPosition];
        String outColor = bg.getGridColor()[horiPosition-1][vertPosition];
        assertTrue("Peg was: " + pegIn.toString() + ", position was: " + positionIn + ", expected: " + Integer.toString(outStatus) + expectedColor + ", but got: " + Integer.toString(outStatus) + outColor, expectedStatus == outStatus && expectedColor.equals(outColor));
    }

    private void testPutPiece(PieceType pieceIn, String positionIn, String expected) {
        assert positionIn.length() == 2;
        BigGrid bg = new BigGrid();
        int horiPosition = Integer.parseInt(positionIn.substring(0, 1));
        int vertPosition = verPosition.get(positionIn.charAt(1));
        bg.putPiece(pieceIn, positionIn);
        String out = bg.getGridColor()[horiPosition][vertPosition];
        assertTrue("Peg was: " + pieceIn.toString() + ", position was: " + positionIn + ", expected: " + expected + ", but got: " + out, expected.equals(out));
    }

    @Test
    public void putPegTest() {
        testPutPeg(Peg.i, "2B", "red", 2);
        testPutPeg(Peg.j, "1C", "blue", 2);
        testPutPeg(Peg.k, "3A", "green", 2);
        testPutPeg(Peg.l, "4D", "yellow", 2);
    }
}