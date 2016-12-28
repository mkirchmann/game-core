package de.neuenberger.game.core;

import java.util.ArrayList;
import java.util.List;

public class Vector2D {
	private final int x;
	private final int y;

	/**
	 * @param x
	 * @param y
	 */
	public Vector2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 * @return
	 */
	public Vector2D setX(int x) {
		return new Vector2D(x, y);
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public Vector2D setY(int y) {
		return new Vector2D(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public Vector2D rotate(int direction) {
		int oldx = x;
		int oldy = y;
		int newX = oldy * direction;
		int newY = -oldx * direction;
		return new Vector2D(newX, newY);
	}

	public static Integer hitTest(List<Vector2D> list, Vector2D point, int offset) {
		return hitTest(list, point, offset, Integer.MAX_VALUE);
	}

	public static Integer hitTest(List<Vector2D> list, Vector2D point, int offset, int maxTest) {
		Integer result = null;
		if (list != null) {
			for (int i = offset; i < list.size() && i < maxTest; i++) {
				Vector2D vektor = list.get(i);

				if (vektor.equals(point)) {
					result = i;
					break;
				}
			}
		}
		return result;
	}

	public Vector2D cloneMove(Vector2D move) {
		return new Vector2D(x + move.x, y + move.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vektor [x=" + x + ", y=" + y + "]";
	}

	public static List<Vector2D> move(List<Vector2D> list, Vector2D move) {
		List<Vector2D> listOfVectors = new ArrayList<Vector2D>(list.size());
		for (Vector2D vector2d : list) {
			listOfVectors.add(vector2d.cloneMove(move));
		}
		return listOfVectors;
	}

	public Vector2D moveX(int i) {
		return new Vector2D(x + i, y);
	}

	public Vector2D moveY(int i) {
		return new Vector2D(x, y + i);
	}

	public static List<Vector2D> fromString(String str) {
		String[] split = str.split("\\\n");
		List<Vector2D> result = new ArrayList<Vector2D>();
		for (int y = 0; y < split.length; y++) {
			String currentString = split[y];
			for (int x = 0; x < currentString.length(); x++) {
				if (currentString.charAt(x) == '*') {
					result.add(new Vector2D(x, y));
				}
			}
		}
		return result;
	}

	public boolean hasSameX(Vector2D obstacle2d) {
		return x == obstacle2d.x;
	}

}
