package main.java.day09;

import java.util.ArrayList;
import java.util.List;

// Class for Block object
public class Block {
	List<Integer> numbers = new ArrayList<>();
	Integer levelResult;
	// Default constructor
	public Block() {
		super();
	}
	// Constructor with parameters
	public Block(List<Integer> numbers, Integer levelResult) {
		super();
		this.numbers = numbers;
		this.levelResult = levelResult;
	}
	// Getters and Setters
	public List<Integer> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
	public Integer getLevelResult() {
		return levelResult;
	}
	public void setLevelResult(Integer levelResult) {
		this.levelResult = levelResult;
	}
	@Override
	public String toString() {
		return "Block [numbers=" + numbers + ", levelResult=" + levelResult + "]";
	}
}