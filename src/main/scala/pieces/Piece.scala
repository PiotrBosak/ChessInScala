package pieces

import color.Color

sealed abstract class Piece(val color: Color) {
  def isColorDifferent(another: Option[Piece]): Boolean = {
    another.isDefined && another.get.color != this.color
  }
}




case class Pawn(override val color: Color) extends Piece(color)

case class Bishop(override val color: Color) extends Piece(color)

case class Knight(override val color: Color) extends Piece(color)

case class Rook(override val color: Color) extends Piece(color)

case class Queen(override val color: Color) extends Piece(color)

case class King(override val color: Color) extends Piece(color)

