public class Target implements Comparable {
  public int x;
  public int y;
  public int placement;
  public int suspicion;
  public int corners;
  public int direction;

  public Target(int x, int y, int placement, int suspicion, int corners, int direction) {
    this.x = x;
    this.y = y;
    this.placement = placement;
    this.suspicion = suspicion;
    this.corners = corners;
    this.direction = direction;
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other.getClass() == this.getClass()) {
      if (this.x == ((Target) other).x && this.y == ((Target) other).y ) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int compareTo(Object o) {
    if (((Target) o).placement == 0) {
      //Both zero (Hits)
      if (this.placement == 0) {
        if (suspicion + corners > ((Target) o).suspicion + ((Target) o).corners) {
          return -1;
        } else if (suspicion + corners < ((Target) o).suspicion + ((Target) o).corners) {
          return 1;
        } else {
          return 0;
        }
      } else {
        return -1;
      }
    } else {
      // Both non-zero placement (Unknown)
      if (this.placement != 0) {
        if (placement + suspicion + corners > ((Target) o).placement + ((Target) o).suspicion + ((Target) o).corners) {
          return -1;
        } else if (placement + suspicion + corners < ((Target) o).placement + ((Target) o).suspicion + ((Target) o).corners) {
          return 1;
        } else {
          return 0;
        }
      } else {
        return 1;
      }
    }
  }
}
