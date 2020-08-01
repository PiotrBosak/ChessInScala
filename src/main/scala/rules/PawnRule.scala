package rules

import board.{Board, Tile}
import color.White

case object PawnRule extends Rule {
  override def calculatePossibleMoves(column: Int, row: Int, board: Board): List[Tile] =
    List(oneTileMove(column, row, board), twoTileMove(column, row, board)).flatten

  override def calculatePossibleAttacks(column: Int, row: Int, board: Board): List[Tile] = {
    val lp = lePassant(column, row, board)
    if (lp.isDefined)
      lp.get :: regularAttacks(column, row, board)
    else regularAttacks(column, row, board)
  }

  private def twoTileMove(column: Int, row: Int, board: Board): Option[Tile] = {
    val currentTile = board.getTile(column, row)
    for {
      tile <- currentTile if nextTwoTilesEmpty(tile, board)
    } yield tile
  }

  private def nextTwoTilesEmpty(tile: Tile, board: Board): Boolean = {
    val piece = tile.currentPiece.getOrElse(return false)
    val nextTwoRowDifference = if (piece.color == White) 2 else -2
    board.getTile(tile.column, tile.row + nextTwoRowDifference)
      .getOrElse(return false)
      .currentPiece.isEmpty && nextTileEmpty(tile, board)
  }

  private def nextTileEmpty(tile: Tile, board: Board): Boolean = {
    val piece = tile.currentPiece.getOrElse(return false)
    val nextRowDifference = if (piece.color == White) 1 else -1
    board.getTile(tile.column, tile.row + nextRowDifference)
      .getOrElse(return false)
      .currentPiece.isEmpty
  }


  private def oneTileMove(column: Int, row: Int, board: Board): Option[Tile] = {
    val currentTile = board.getTile(column, row)
    for {
      tile <- currentTile if nextTileEmpty(tile, board)
    } yield tile
  }

  //todo remember about lePassant at some point in time
  private def lePassant(column: Int, row: Int, board: Board): Option[Tile] = None


  private def regularAttacks(column: Int, row: Int, board: Board): List[Tile] = {
    val currentTile = board.getTile(column, row)
    val left = for {
      tile <- currentTile
      tileOnLeft <- getLeftAttackedTile(tile, board)
    } yield tileOnLeft

    val right = for {
      tile <- currentTile
      tileOnRight <- getRightAttackedTile(tile, board)
    } yield tileOnRight
    List(left, right).flatten


  }

  private def getLeftAttackedTile(tile: Tile, board: Board): Option[Tile] =
    getAttackedTile(tile, board, -1)

  private def getRightAttackedTile(tile: Tile, board: Board): Option[Tile] =
    getAttackedTile(tile, board, 1)

  private def getAttackedTile(tile: Tile, board: Board, columnDifference: Int): Option[Tile] = {
    val rowDifference = if (tile.currentPiece.getOrElse(return None).color == White) 1 else -1
    val attackedTile = board.getTile(tile.column + columnDifference, tile.row + rowDifference)

    val piece = attackedTile.flatMap(_.currentPiece).filter(_.isColorDifferent(tile.currentPiece))
    for {
      tile <- attackedTile if piece.isDefined
    } yield tile
  }
}
