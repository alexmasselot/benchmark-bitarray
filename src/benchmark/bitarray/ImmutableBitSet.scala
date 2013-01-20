package benchmark.bitarray

import scala.util.Random
import scala.collection.immutable.BitSet

object ImmutableBitSet extends TestSize {

  var rnd = new Random()
  var l = for (i <- 0 to m) yield {
    var bi = BitSet()
    for (j <- 0 until nbits) {
      bi += (rnd.nextInt(maxBit))
    }
    bi
  }

  val it = new Iterator[BitSet] {
    var i = 0
    def hasNext = true
    def next = {
      i = (i + 1) % m
      l(i)
    }
  }

  def main(args: Array[String]) {
    println("######     " + this.getClass())
    println("######     " + sizeStr)
    val tnext_1 = TimeIt.timeInNanos(10000000, () => { it.next })
    println("1*next\t" + tnext_1)
    println("count\t" + (TimeIt.timeInNanos(10000000, () => {
      it.next.size
    }) - tnext_1))
    println("isEmpty\t" + (TimeIt.timeInNanos(10000000, () => {
      it.next.isEmpty
    }) - tnext_1))

    val tnext_2 = TimeIt.timeInNanos(10000000, () => { it.next; it.next })
    println("2*next\t" + tnext_2)
    println("&\t" + (TimeIt.timeInNanos(10000000, () => { it.next & it.next }) - tnext_2))
    println("|\t" + (TimeIt.timeInNanos(10000000, () => { it.next | it.next }) - tnext_2))
    println("xor\t" + (TimeIt.timeInNanos(10000000, () => { it.next ^ it.next }) - tnext_2))
  }
}
