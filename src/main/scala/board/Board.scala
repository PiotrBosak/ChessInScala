package board

import pieces.Piece

class Board(val tiles: List[Tile]) {
  def this() = this(BoardFactory())




  def getTile(column: Int, row: Int): Option[Tile] = tiles.find(t => t.column == column && t.row == row)

}
