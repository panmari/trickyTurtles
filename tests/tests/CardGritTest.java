package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import solver.CardGrid;
import solver.CardNotReadyException;
import solver.DataSetParser;
import solver.TurtleCard;

import ch.aplu.jgamegrid.Location;


public class CardGritTest {

	CardGrid gg;
	List<TurtleCard> allCards;
	
	@Before
	public void setUp() throws Exception {
		gg = new CardGrid("cardset.data", false);
		allCards = gg.getCards();
	}

	@Test
	public void testIsThereConflict() {
		TurtleCard[][] grid = gg.getGrid();
		assertNull(grid[0][0]);
		
		Location p = gg.putDownCard(allCards.get(0), false);
		assertNotNull(grid[p.x][p.y]);
		assertFalse(gg.isThereConflict(p));
		
		p = gg.putDownCard(allCards.get(4), false);
		assertNotNull(grid[p.x][p.y]);
		assertFalse(gg.isThereConflict(p));
		
		p = gg.putDownCard(allCards.get(3), false);
		assertFalse(gg.isThereConflict(p));
		
		p = gg.putDownCard(allCards.get(2), false);
		assertNotNull(grid[p.x][p.y]);
		assertEquals(new Location(0,1), p);
		assertTrue(gg.isThereConflict(p));
	}
	
	@Test(expected=CardNotReadyException.class)
	public void shouldNotLaySameCardTwice() {
		gg.putDownCard(allCards.get(0), false);
		gg.putDownCard(allCards.get(0), false);
	}
}
