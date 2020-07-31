package board

import color.Black
import exceptions.InvalidTileInitializationException
import org.scalatest.flatspec.AnyFlatSpec

class TileTest extends AnyFlatSpec {
  "Black A2 tile" should "throw exception" in {
    assertThrows[InvalidTileInitializationException]{
      Tile(Black,1,2,None)
    }
  }

}
