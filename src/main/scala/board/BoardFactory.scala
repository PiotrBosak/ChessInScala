package board

import color.{Black, White}
import pieces._

import scala.annotation.tailrec

case object BoardFactory {
  def apply(): List[Tile] = {
    createTileWithWhiteQueen() ::
      createTileWithBlackQueen() ::
      createTileWithWhiteKing() ::
      createTileWithBlackKing() ::
      createTilesWithWhitePawns() ++
        createTilesWithBlackPawns() ++
        createTilesWithWhiteRooks() ++
        createTilesWithBlackRooks() ++
        createTilesWithWhiteKnights() ++
        createTilesWithBlackKnights() ++
        createTilesWithWhiteBishops() ++
        createTilesWithBlackBishops() ++
        createTilesWithNoPieces()
  }

  private def createTilesWithBlackBishops(): List[Tile] = List(
     Tile(White, 3, 8, Some(Bishop(Black))),
     Tile(Black, 6, 8, Some(Bishop(Black)))
  )


  private def createTilesWithBlackKnights(): List[Tile] = List(
     Tile(Black, 2, 8, Some(Knight(Black))),
     Tile(White, 7, 8, Some(Knight(Black)))
  )

  private def createTilesWithBlackRooks(): List[Tile] = List(
     Tile(White, 1, 8, Some(Rook(Black))),
     Tile(Black, 8, 8, Some(Rook(Black)))
  )

  private def createTilesWithWhiteBishops(): List[Tile] = List(
     Tile(Black, 3, 1, Some(Bishop(White))),
     Tile(White, 6, 1, Some(Bishop(White)))
  )

  private def createTilesWithWhiteKnights(): List[Tile] = List(
     Tile(White, 2, 1, Some(Knight(White))),
     Tile(Black, 7, 1, Some(Knight(White)))
  )

  private def createTilesWithWhiteRooks(): List[Tile] = List(
     Tile(Black, 1, 1, Some(Rook(White))),
     Tile(White, 8, 1, Some(Rook(White)))
  )

  private def createTileWithBlackKing(): Tile =
     Tile(White, 5, 8, Some(King(Black)))

  private def createTileWithBlackQueen(): Tile =
     Tile(Black, 4, 8, Some(Queen(Black)))

  private def createTileWithWhiteKing(): Tile =
     Tile(Black, 5, 1, Some(King(White)))

  private def createTileWithWhiteQueen(): Tile =
     Tile(White, 4, 1, Some(Queen(White)))


  private def createTilesWithNoPieces(): List[Tile] = {
    @tailrec
    def aux(row: Int, list: List[Tile]): List[Tile] =
      if (row > 6) list
      else aux(row + 1, list ++ createTiles(row, None))

    aux(3, List())

  }


  private def createTilesWithBlackPawns(): List[Tile] =
    createTiles(7, Some(Pawn(Black)))

  private def createTilesWithWhitePawns(): List[Tile] =
    createTiles(2, Some(Pawn(White)))


  private def createTiles(row: Int, piece: Option[Piece]) = {
    @tailrec
    def createTilesInColumn(column: Int, list: List[Tile]): List[Tile] = {
      if (column > 8) list
      else if ((row + column) % 2 == 0)
        createTilesInColumn(column + 1,  Tile(Black, column, row, piece) :: list)
      else
        createTilesInColumn(column + 1,  Tile(White, column, row, piece) :: list)
    }

    createTilesInColumn(1, List())
  }
}
