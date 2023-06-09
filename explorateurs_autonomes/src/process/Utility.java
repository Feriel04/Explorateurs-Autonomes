package process;

import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.lang.model.element.Element;

import config.Configuration;
import data.map.GraphicElement;
import data.map.Map;
import data.map.Treasure;
import data.map.geometry.Block;
import data.map.geometry.Position;
import data.map.mobile.Character;
import data.map.obstacles.Rock;
import data.map.obstacles.Tree;


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

		int xPosition = searchedPosition.getX();
		int yPosition = searchedPosition.getY();
		
		return map.getBlock(xPosition/Block.BLOCK_WIDTH, yPosition/Block.BLOCK_WIDTH);
	}
	
	/**
	 * This static method returns a blockManager from a position in the map.
	 * 
	 * @param blockManagers
	 * 				an ArrayList containing all block Managers.
	 * @param searchedPosition
	 * 				an indicated position.
	 * 
	 * @return a block.
	 * */
	public static BlockManager getBlockManagerFromBlock(ArrayList<BlockManager> blockManagers, Block block) {

		int x = block.getX();
		int y = block.getY();
		
		for(BlockManager blockManager : blockManagers) {
			if(blockManager.getBlock().getX() == x 
					&& blockManager.getBlock().getY() == y) {
				return blockManager;
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
	 * This method returns a GraphicElement from a position
	 * 
	 * @param x and y.
	 * @param map blocks.
	 * 
	 * @return a {@link GraphicElement} since the position in parameter.
	 * */
	
	public static GraphicElement getGraphicElementFromPosition(Map map, int xSearchedPosition, int ySearchedPosition) {
		
		for(GraphicElement mapElement : map.getElements()){
			
			int xMapElement = mapElement.getPosition().getX();
			int yMapElement = mapElement.getPosition().getY();
			
			
			if(xSearchedPosition < xMapElement + mapElement.getWidth()
					&& xSearchedPosition >= xMapElement
					&& ySearchedPosition < yMapElement + mapElement.getHeight()
					&& ySearchedPosition >= yMapElement) {
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
	 * This method tells if a {@link GraphicElement} is n block near an Element.
	 * 
	 * @param element position.
	 * @param map.
	 * 
	 * @return if the position is not near from map sides it returns true
	 * 			 otherwise, it returns false.
	 * 
	 * */
	public static boolean isElementNBlockNearElement(Map map, Position elementPosition, int nbBlocks) {
		int i, j, line, column;
		Block block;
		int xPosition = elementPosition.getX();
		int yPosition = elementPosition.getY();
		if(xPosition/Block.BLOCK_WIDTH + nbBlocks < map.getBlocksWidth()
			&& xPosition/Block.BLOCK_WIDTH - nbBlocks > 0
			&& yPosition/Block.BLOCK_WIDTH + nbBlocks < map.getBlocksHeight()
			&& yPosition/Block.BLOCK_WIDTH - nbBlocks > 0) {
				for(GraphicElement mapElement : map.getElements()) {
					Position mapElementPosition = mapElement.getPosition();
					int x = mapElementPosition.getX();
					int y = mapElementPosition.getY();
					
					if(x <= xPosition + nbBlocks * Block.BLOCK_WIDTH
							&& x >= xPosition - nbBlocks * Block.BLOCK_WIDTH
							&& y <= yPosition + nbBlocks * Block.BLOCK_WIDTH
							&& y >= yPosition - nbBlocks * Block.BLOCK_WIDTH) {
						return true;
					}
					
				}
			return false;
		}
		else {
			return true;
		}
		
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
	 * This method returns an {@link ArrayList} of {@link BlockManager}s which are occupied
	 * 
	 * @param an {@link ArrayList} of {@link BlockManager}s.
	 * 
	 * @param an {@link ArrayList} of occupied {@link BlockManager}s.
	 * */
	
	public static ArrayList<BlockManager> getOccupiedBlockManagers(ArrayList<BlockManager> blockManagers){
		ArrayList<BlockManager> occupiedManagers = new ArrayList<BlockManager>();
		for(BlockManager blockManager : blockManagers) {
			if(!blockManager.isFree()) {
				occupiedManagers.add(blockManager);
			}
		}
		return occupiedManagers;
	}
	
	
	/**
	 * This method return a {@link HashMap} with all adjacent elements of an explorator associated
	 * with the needed orientation to go in.
	 * 
	 * @param characterPosition
	 * @param mapElements
	 * 
	 * @return a {@link HashMap}
	 * 
	 * */
	public static HashMap<Integer,GraphicElement> getAllAdjacentElementsWithOrientation(Position characterPosition, Map map){
			HashMap<Integer,GraphicElement> elementsWithOrientation = new HashMap<Integer,GraphicElement>();
			GraphicElement element;
			int x = characterPosition.getX();
			int y = characterPosition.getX();
			int orientation = 0; 
			ArrayList<Position> positions = new ArrayList<Position>();
			
			positions.add(new Position(x, y - Block.BLOCK_WIDTH));
			positions.add(new Position(x, y + Block.BLOCK_WIDTH));
			positions.add(new Position(x + Block.BLOCK_WIDTH, y));
			positions.add(new Position(x - Block.BLOCK_WIDTH, y));
			
			for(Position position : positions) {
				element = getGraphicElementFromPosition(map, position);
				elementsWithOrientation.put(orientation, element);
				orientation++;
			}
			
			return elementsWithOrientation;
	}
	
	
	/**
	 * This method lists all adjacent elements near the explorator with a
	 * perimeter of nbBlocks
	 * 
	 * @param nbBlocks
	 * 				a perimeter of exploration
	 * @param map
	 * 				a map.
	 * @param charPosition
	 * 				a character position.
	 *
	 * @return the orientation
	 * 
	 **/
	
	public static ArrayList<GraphicElement> getNBlocksAdjacents(int nbBlocks, Map map, Position charPosition) {
		int i, j, line, column;
		ArrayList<GraphicElement> adjacents = new ArrayList<GraphicElement>();
		Block block;
		int xPosition = charPosition.getX();
		int yPosition = charPosition.getY();
		if(xPosition/Block.BLOCK_WIDTH + nbBlocks < map.getBlocksWidth()
			&& xPosition/Block.BLOCK_WIDTH - nbBlocks > 0
			&& yPosition/Block.BLOCK_WIDTH + nbBlocks < map.getBlocksHeight()
			&& yPosition/Block.BLOCK_WIDTH - nbBlocks > 0) {
			
			for(GraphicElement mapElement : map.getElements()) {
				Position mapElementPosition = mapElement.getPosition();
				int x = mapElementPosition.getX();
				int y = mapElementPosition.getY();
				
				if(x <= xPosition + nbBlocks * Block.BLOCK_WIDTH
						&& x >= xPosition - nbBlocks * Block.BLOCK_WIDTH
						&& y <= yPosition + nbBlocks * Block.BLOCK_WIDTH
						&& y >= yPosition - nbBlocks * Block.BLOCK_WIDTH) {
					adjacents.add(mapElement);
				}
				
			}
		}
		return adjacents;
	}
	
	/**
	 * This method generates a path to a position
	 * 
	 * @param charPosition
	 * 			The position of a character
	 * @param searchedPosition
	 * 			The position of a treasure
	 * 
	 * @returns orientations
	 * 			an orientation {@link ArrayList}
	 * 
	 * */
	public static ArrayList<Integer> generatePathToTreasure(Position charPosition, Position searchedPosition) {
	    ArrayList<Integer> orientations = new ArrayList<Integer>();
	    
	    
	    // horizontal and vertical distance between the explorator and the treasure
	    int xDistance = searchedPosition.getX() - charPosition.getX();
	    int yDistance = searchedPosition.getY() - charPosition.getY();
	    
	    // Find the orientations
	    if (xDistance > 0) {
	      for (int i = 0; i < xDistance; i++) {
	        orientations.add(Exploration.EAST_ORIENTATION);
	      }
	    } else {
	      for (int i = 0; i < -xDistance; i++) {
	        orientations.add(Exploration.WEST_ORIENTATION);
	      }
	    }
	    
	    if (yDistance > 0) {
	      for (int i = 0; i < yDistance; i++) {
	        orientations.add(Exploration.SOUTH_ORIENTATION);
	      }
	    } else {
	      for (int i = 0; i < -yDistance; i++) {
	        orientations.add(Exploration.NORTH_ORIENTATION);
	      }
	    }
	    
	    return orientations;
	  }
	
	
	/**
	 * This method generates a path to a treasure in a forest
	 * 
	 * */
	
	public static ArrayList<Integer> generatePathToTreasure(Position charPosition){
		ArrayList<Integer> orientations = new ArrayList<Integer>();
		return orientations;
	}
	
	
	
}




