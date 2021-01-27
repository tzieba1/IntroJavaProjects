package com.tzieba;

import java.util.ArrayList;

public class TheRing implements Positions {
  private final ArrayList<Integer> positions = new ArrayList<>();

  public TheRing() {
  }

  @Override
  public void addPosition(int position) {
    positions.add(position);
  }

  @Override
  public ArrayList<Integer> getPositions() {
    return positions;
  }
}
