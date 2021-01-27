package com.tzieba;

import java.util.ArrayList;

public class BookCharacter implements Positions {
  private final String name;
  private int appearanceCount;
  private int closenessCount;
  private double closenessFactor;
  private ArrayList<Integer> positions = new ArrayList<>();

  public BookCharacter(String name) {
    this.name = name;
    this.appearanceCount = 0;
    this.closenessCount = 0;
  }

  public String getName() {
    return this.name;
  }

  public void incrementClosenessCount() {
    closenessCount++;
    closenessFactor = (double) closenessCount / appearanceCount;
  }

  @Override
  public String toString() {
    return String.format("[%-10s, %d] is close to the Ring %d times with a closeness factor of %5.4f",
        name, appearanceCount, closenessCount, closenessFactor);
  }

  @Override
  public void addPosition(int position) {
    positions.add(position);
    appearanceCount++;
  }

  @Override
  public ArrayList<Integer> getPositions() {
    return positions;
  }
}
