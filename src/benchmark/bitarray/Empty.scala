package benchmark.bitarray

import scala.util.Random

object Empty {
  def main(args: Array[String]) {
	  println("nothing\t"+TimeIt.timeInNanos(1000000000, ()=>{}))
	  println("sleep(2)\t"+TimeIt.timeInNanos(1000, ()=>{Thread.sleep(2)}))
	  
	  val rnd=new Random()
	  println("200 random int\t"+TimeIt.timeInNanos(100000, ()=>{for(i <- 0 to 200){var i = rnd.nextInt(2048)}}))
  }
}
