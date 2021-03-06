package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import solver.CardNotReadyException;
import solver.Color;
import solver.Orientation;
import solver.TurtleCard;
import solver.TurtleCardFactory;


public class TurtleCardFactoryTest {

	private TurtleCardFactory tf;

	@Before
	public void setUp() throws Exception {
		tf = new TurtleCardFactory();
	}

	@Test(expected=CardNotReadyException.class)
	public void notReadyCardShouldThrowException() {
		tf.prepareEmptyCard("/sprites/tc1.jpg");
		tf.addHalfTurtle(Color.BLUE, Orientation.BACK);
		tf.getNewCard();
	}
	
	@Test
	public void shouldReturnCardWhenFourAdded() {
		tf.prepareEmptyCard("/sprites/tc1.jpg");
		tf.addHalfTurtle(Color.BLUE, Orientation.BACK);
		tf.addHalfTurtle(Color.BLUE, Orientation.BACK);
		tf.addHalfTurtle(Color.BLUE, Orientation.BACK);
		tf.addHalfTurtle(Color.BLUE, Orientation.BACK);
		TurtleCard tc = tf.getNewCard();
		assertEquals("[bb, bb, bb, bb]" ,tc.toString());
	}
	
	@Test
	public void shouldMakeCardOutOfString() {
		TurtleCard tc = tf.makeTurtleCard("yf;gb;rb;bf", "/sprites/tc1.jpg");
		assertEquals("[yf, gb, rb, bf]" ,tc.toString());
	}

}
