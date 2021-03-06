package solver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Parses a file into a set of TurtleCards.
 * Usually, this file is created using the TrickyTurtles Generator,
 * available at www.java-online.ch
 * The format used is:
 * <ul>
 * <li> One line per card </li>
 * <li> A card consists of 4 HalfTurtles, separated by ";"</li>
 * <li> A HalfTurtle is represented by two letters: [color][orientation]</li>
 * For valid colors or orientations see their respective classes.
 * @author panmari
 * @see Color
 * @see Orientation
 * @see HalfTurtle
 */
public class DataSetParser {

	Scanner parseScanner;
	
	public DataSetParser(String filePath) throws FileNotFoundException {
		InputStream input = getClass().getResourceAsStream(filePath); //for internal card set
		if (input == null)
			input = new FileInputStream(filePath);
		parseScanner = new Scanner(input);
		parseScanner.useDelimiter("\\r?\\n");
	}

	/**
	 * TODO: Do not allow more than 9 cards (or do we want to allow that?)
	 * @return
	 */
	public List<TurtleCard> parse() {
		LinkedList<TurtleCard> tcList = new LinkedList<TurtleCard>();
		TurtleCardFactory tf = new TurtleCardFactory();
		while (parseScanner.hasNextLine()) {
			String line = parseScanner.nextLine();
			int firstSpace = line.indexOf(" ");
			if (firstSpace != -1) {
				String tcString = line.substring(0, firstSpace);
				String sprite = line.substring(firstSpace + 1);
				tcList.add(tf.makeTurtleCard(tcString, sprite));
			}
		}
		return tcList;
	}
}
