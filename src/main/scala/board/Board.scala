package board

class Board(val tiles: List[Tile]) {
  def this() = this(BoardFactory())
}
