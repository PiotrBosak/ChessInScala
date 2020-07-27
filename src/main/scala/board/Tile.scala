package board

import color.Color
import pieces.Piece

case class Tile(color: Color, column: Int, row: Int, startingPiece: Option[Piece], currentPiece: Option[Piece]) {
  def this(color: Color, row: Int, column: Int, piece: Option[Piece]) = this(color, row, column, piece, piece)
}
