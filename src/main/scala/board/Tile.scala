package board

import color.{Black, Color, White}
import exceptions.InvalidTileInitializationException
import pieces.Piece

case class Tile private(color: Color, column: Int, row: Int, startingPiece: Option[Piece], currentPiece: Option[Piece]) {


  def isEmpty: Boolean = currentPiece.isEmpty

  def hasPiece: Boolean = currentPiece.isDefined

  def hasMoved: Boolean = currentPiece eq startingPiece

}

object Tile {
  def apply(color: Color, column: Int, row: Int, startingPiece: Option[Piece], currentPiece: Option[Piece]): Tile = {
    if (column < 1 || column > 8) throw InvalidTileInitializationException()
    if (row < 1 || column > 8) throw InvalidTileInitializationException()
    if ((row + column) % 2 == 0) {
      if (color == White) throw InvalidTileInitializationException()
    }
    else if ((row + column) % 2 == 1) {
      if (color == Black) throw InvalidTileInitializationException()
    }
    new Tile(color, column, row, startingPiece, currentPiece)
  }


  def apply(color: Color, column: Int, row: Int, startingPiece: Option[Piece]): Tile =
    apply(color, column, row, startingPiece, startingPiece)
}


