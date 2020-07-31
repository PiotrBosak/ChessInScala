package board

import color.{Black, White}
import org.scalatest.flatspec.AnyFlatSpec
import pieces._

class BoardFactoryTest extends AnyFlatSpec {
  val board = new Board()

  "Board" should " have 64 tiles, 32 for each color" in {
    assert(board.tiles.size == 64)
    assert(board.tiles.count(_.color == Black) == 32)
    assert(board.tiles.count(_.color == White) == 32)
  }
  "Board" should "have 32 empty tiles at the beginning" in {
    assert(board.tiles.count(_.startingPiece.isEmpty) == 32)
  }


  "Board" should "have 16 pawns, 8 for each color" in {
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Pawn]) == 16)
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Pawn] && p.get.color == Black) == 8)
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Pawn] && p.get.color == White) == 8)
  }
  "Board" should "have 2 black Knights and 2 white Knights" in {
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Knight] && p.get.color == White) == 2)
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Knight] && p.get.color == Black) == 2)
  }

  "Board" should "have a white rook at A1 and a black bishop at F8" in {
    assert(board.tiles.find(t => t.column == 1 && t.row == 1).get.startingPiece.get == Rook(White))
    assert(board.tiles.find(t => t.column == 6 && t.row == 8).get.startingPiece.get == Bishop(Black))
  }


}
