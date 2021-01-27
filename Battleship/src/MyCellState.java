public enum MyCellState {
  Unknown,
  Hit,
  Miss;

  private MyCellState() {}

  public String toString() {
    switch(this) {
      case Unknown:
        return ".";
      case Hit:
        return "X";
      case Miss:
        return "o";
      default:
        return "?";
    }
  }
}
