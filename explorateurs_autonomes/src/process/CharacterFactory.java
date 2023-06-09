package process;

import java.awt.Dimension;


import config.Configuration;
import data.map.GraphicElement;
import data.map.Treasure;
import data.map.geometry.Block;
import data.map.geometry.Position;
import data.map.obstacles.Forest;
import data.map.obstacles.Mud;
import data.map.obstacles.River;
import data.map.obstacles.Rock;
import data.map.obstacles.Tree;
import data.map.mobile.Character;
import exceptions.ValueException;

/**
 * this class represents a factory for creating different characters with specified attributes and positions on a game map.
 * @version 2.0
 * @author Feriel MALEK
 * */

public class CharacterFactory {
	public final static int FAST_STRONG_INTELLIGENT_TOUGH = 0;
	public final static int STRONG = 1;
	public final static int INTELLIGENT = 2;
	public final static int TOUGH = 3;
	public final static int ALL = 4;
	
	public final static int Vickie = 5;
	public final static int Sam = 6;
	public final static int Charlie = 7;
	public final static int Lexi = 8;
	public final static int Kevin = 9;
	public final static int Andy = 10;
	
	
	public static Character createCharacter(int characterName, Position position) throws ValueException {
		
		Dimension charDim = new Dimension(Block.BLOCK_WIDTH, Block.BLOCK_WIDTH);
		if(characterName == Vickie) {
			return new Character(position, charDim, 60, "Vickie", 60, 80, 80, 0);
		}
		else if (characterName == Sam) {
			return new Character(position, charDim, 80, "Sam", 80, 70, 60, 0);
		}
		else if (characterName == Charlie) {
			return new Character(position, charDim, 90, "Charlie", 50, 90, 60, 0);
		}
		else if (characterName == Lexi) {
			return new Character(position, charDim, 80, "Lexi", 60, 80, 80, 0);
		}
		else if (characterName == Kevin) {
			return new Character(position, charDim, 70, "Kevin", 90, 60, 90, 0);
		}
		else if (characterName == Andy) {
			return new Character(position, charDim, 60, "Andy", 80, 70, 80, 0);
		}
		
		else
			throw new ValueException("Cette valeur n'existe pas veuillez choisir un nombre entre"
					+ "5 et 10 ou utiliser directement les constantes de la classe.");
	}


}
