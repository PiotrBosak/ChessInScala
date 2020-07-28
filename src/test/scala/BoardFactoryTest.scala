import board.{Board, Tile}
import color.{Black, White}
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import pieces.{Bishop, Knight, Pawn, Rook}

class BoardFactoryTest extends FunSuite
  with BeforeAndAfterAll {
  val board = new Board()


  test("Board should have 64 tiles, 32 for each color") {
    assert(board.tiles.size == 64)
    assert(board.tiles.count(_.color == Black) == 32)
    assert(board.tiles.count(_.color == White) == 32)
  }
  test("Board should have 32 empty tiles at the beginning") {
    assert(board.tiles.count(_.startingPiece.isEmpty) == 32)
  }


  test("Board should have 16 pawns, 8 for each color") {
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
  test("Board should have 2 black Knights and 2 white Knights") {
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Knight] && p.get.color == White) == 2)
    assert(board.tiles
      .map(_.startingPiece)
      .count(p => p.isDefined && p.get.isInstanceOf[Knight] && p.get.color == Black) == 2)
  }

  test("At A1 there is a white rook, and at F8 there is black Bishop") {
    assert(board.tiles.find(t => t.column == 1 && t.row == 1).get.startingPiece.get == Rook(White))
    assert(board.tiles.find(t => t.column == 6 && t.row == 8).get.startingPiece.get == Bishop(Black))
  }


}
