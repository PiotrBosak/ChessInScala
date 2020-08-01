package rules

import board.{Board, Tile}

trait Rule {
  def calculatePossibleMoves(row: Int, column: Int, board: Board): List[Tile]

  def calculatePossibleAttacks(row: Int, column: Int, board: Board): List[Tile]


}
