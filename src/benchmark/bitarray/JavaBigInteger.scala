package benchmark.bitarray

import scala.util.Random
import java.math.BigInteger

object JavaBigInteger extends TestSize{

  var rnd = new Random()
  var l = for (i <- 0 to m) yield {
    var bi = BigInteger.ZERO
    for (j <- 0 until nbits) {
      bi = bi.setBit(rnd.nextInt(maxBit))
    }
    bi
  }

  val it = new Iterator[BigInteger] {
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
      it.next.bitCount
    }) - tnext_1))
    println("isEmpty\t" + (TimeIt.timeInNanos(10000000, () => {
      it.next == BigInteger.ZERO
    }) - tnext_1))
    println("rev\t" + (TimeIt.timeInNanos(10000000, () => {
      val bi=BigInteger.ZERO
      it.next.not()
    }) - tnext_1))

    println("shift\t" + (TimeIt.timeInNanos(10000000, () => {
      it.next.shiftRight(1)
    }) - tnext_1))
    println("cshift\t" + (TimeIt.timeInNanos(10000000, () => {
      val bi = it.next
      val bit0 = bi.testBit(0)
      val bi2 = it.next.shiftRight(1)
      if (bit0) bi2.setBit(maxBit - 1) else bi2
    }) - tnext_1))
    
    

    val tnext_2 = TimeIt.timeInNanos(10000000, () => { it.next; it.next })
    println("2*next\t" + tnext_2)
    println("&\t" + (TimeIt.timeInNanos(10000000, () => { it.next.and(it.next) }) - tnext_2))
    println("|\t" + (TimeIt.timeInNanos(10000000, () => { it.next.or(it.next)}) - tnext_2))
    println("xor\t" + (TimeIt.timeInNanos(10000000, () => { it.next.xor(it.next)}) - tnext_2))
  }
}
