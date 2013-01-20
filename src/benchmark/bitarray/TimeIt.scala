package benchmark.bitarray

object TimeIt {
  def timeInNanos(n: Int, f: () => Unit) = {
    //warm up
    var i = 0;
    while (i < 100) {
      f();
      i+=1
    }
    
    
    val t0 = System.currentTimeMillis()
     i = 0;
    while (i < n) {
      f();
      i+=1
    }
    val t1 = System.currentTimeMillis()
    ( 1000000*(t1 - t0) / n)
  }
}
