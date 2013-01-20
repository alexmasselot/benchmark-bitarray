package benchmark.bitarray

trait TestSize {
  val m: Int = 1000
  val nbits:Int = 20
  val maxBit:Int = 2048
  
  val sizeStr = "nbits="+nbits+",max="+maxBit
}
