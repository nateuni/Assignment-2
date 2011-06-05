package ass02;
/** A sliding block puzzle, with walls between positions. */
public interface SlidingBlock
{
  /** Solve a puzzle with a minimal number of moves, no more than some specified number. */
  int[] solve (int[] start, int[] goal, int maxMoves);

  /** Find a shortest path from a given position to the first row of the puzzle. */
  int[] shortestPath (int startPosition);

  /** Block direct moves between two adjacent positions. */
  void addWall (int positionI, int positionJ);
}
