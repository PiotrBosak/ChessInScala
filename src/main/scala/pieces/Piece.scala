package pieces

import color.Color

sealed abstract class Piece(color: Color)

case class Pawn(color: Color) extends Piece(color)

case class Bishop(color: Color) extends Piece(color)

case class Knight(color: Color) extends Piece(color)

case class Rook(color: Color) extends Piece(color)

case class Queen(color: Color) extends Piece(color)

case class King(color: Color) extends Piece(color)

