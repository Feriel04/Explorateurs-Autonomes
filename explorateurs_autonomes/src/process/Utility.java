package process;

import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.lang.model.element.Element;

import config.Configuration;
import data.map.GraphicElement;
import data.map.Map;
import data.map.Treasure;
import data.map.geometry.Block;
import data.map.geometry.Position;
import data.map.mobile.Character;


/**
 * This class contains static methods for different utilities
 * 
 * @author Omar CHAKER
 * @version 1.0
 * */

public class Utility {

	/**
	 * This method reads image from their location.
	 * 
	 * @param filePath
	 * 				the image path.
	 * 
	 * @return an {@link Image}.
	 * */
	public static Image readImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
			return null;
		}
	}
	
	/**
	 * This method generates random (integer) number between "min" and "max".
	 * 
	 * @param min
	 * 				a minimum.
	 * @param max
	 * 				a maximum.
	 * 
	 * @return a random number.
	 * */
	public static int getRandomNumber(int min, int max) {
		long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int randomNumber = random.nextInt(max - min) + min;
        

		return (int) (Math.random() * (max + 1 - min)) + min;
		
		//return randomNumber;
	}
	
	/**
	 * This static method gets a block from a position in the map.
	 * 
	 * @param blocks
	 * 				an ArrayList containing all map blocks.
	 * @param searchedPosition
	 * 				an indicated position.
	 * 
	 * @return a block.
	 * */
	public static Block getBlockFromPosition(Map map, Position searchedPosition) {

		int width = map.getBlocksWidth();
		int height = map.getBlocksHeight();
		
		for(int i = 0; i< width; i++) {
			for(int j = 0; i< height; i++) {
				
				Block block = map.getBlock(i, j);
				
				int x = block.getX();
				int y = block.getY();
				
				
				if(searchedPosition.getX() < x + Block.BLOCK_WIDTH 
						&& searchedPosition.getY() < y + Block.BLOCK_WIDTH
						&& searchedPosition.getX() > x
						&& searchedPosition.getY() > y
						) {
					return block;
				}
			
			}
			
		}
		return null;
	}
	
	
	/**
	 * This static checks if two characters are adjacents.
	 * 
	 * @param char1, char2 : Two characters
	 * 
	 * @return a boolean which indicates if two characters are adjacents.
	 *
	 * */
	public static boolean isTwoCharactersAdjacents(Character char1, Character char2) {
		int dx, dy;
		
		dx = Math.abs(char1.getPosition().getX() - char2.getPosition().getX());
		dy = Math.abs(char1.getPosition().getY() - char2.getPosition().getY());
		
		if(dx == Block.BLOCK_WIDTH || dy == Block.BLOCK_WIDTH) {
			return true;
		}
		return false;
	}
	
	/**
	 * Simulated the necessary refresh time for avoiding printing issue.
	 */
	public static void windowRefreshTime() {
		try {
			Thread.sleep(Configuration.GAME_SPEED);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This method sleeps a character depending on its speed.
	 */
	public static void characterWaitingTime(int pace) {
		try {
			
			
			Thread.sleep(Configuration.GAME_SPEED * (1 - pace/100));
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This method returns a GraphicElement from a position
	 * 
	 * @param a {@link Position}.
	 * @param map blocks.
	 * 
	 * @return a {@link GraphicElement} since the position in parameter.
	 * */
	
	public static GraphicElement getGraphicElementFromPosition(Map map, Position searchedPosition) {
		
		for(GraphicElement mapElement : map.getElements()){
			int xSearchedPosition = searchedPosition.getX();
			int ySearchedPosition = searchedPosition.getY();
			
			int xMapElement = mapElement.getPosition().getX();
			int yMapElement = mapElement.getPosition().getY();
			
			
			if(searchedPosition.equals(mapElement.getPosition())) {
				return mapElement;
			}
			else if(xSearchedPosition < xMapElement + mapElement.getWidth()
					&& xSearchedPosition > xMapElement
					&& ySearchedPosition < yMapElement + mapElement.getHeight()
					&& ySearchedPosition > yMapElement) {
				return mapElement;
			}
			
		}
			
		return null;
	}
	
	
	/**
	 * This method tells if a {@link GraphicElement} is a block near a border.
	 * 
	 * @param element position.
	 * @param map.
	 * 
	 * @return if the position is not near from map sides it returns true
	 * 			 otherwise, it returns false.
	 * 
	 * */
	public static boolean isElementBlockNearBorders(Map map, Position treasurePosition) {
		int x = treasurePosition.getX();
		int y = treasurePosition.getY();
		
		return (x + Block.BLOCK_WIDTH < map.getBlocksWidth())
				&& (y + Block.BLOCK_WIDTH < map.getBlocksHeight())
				&& (x - Block.BLOCK_WIDTH > 0)
				&& (y - Block.BLOCK_WIDTH > 0);
		
	}
	
	
	
	/**
	 * This method tells if a {@link Position} is convenient to put a {@link Treasure}.
	 * 
	 * @param potential treasure position.
	 * 
	 * @return if the position is not near from map sides it returns true
	 * 			 otherwise, it returns false.
	 * 
	 * */
	public static boolean isPositionConvenientForTreasure(Map map, Position treasurePosition) {
		int x = treasurePosition.getX();
		int y = treasurePosition.getY();
		
		return isElementBlockNearBorders(map, treasurePosition)
				&& (getGraphicElementFromPosition(map, treasurePosition) != null);
		
	}
	
	/**
	 * This method returns a {@link GraphicElement} from a position
	 * 
	 * @param a position.
	 * @param a map.
	 * 
	 * @param a {@link GraphicElement}.
	 * */
	
	
	
}




