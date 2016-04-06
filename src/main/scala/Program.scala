import com.typesafe.config.{ConfigBeanFactory, ConfigFactory}
import org.apache.spark.streaming._
import org.apache.spark.{SparkConf, SparkContext}
/**
  * Created by yuvali on 04/02/2016.
  */
object Program {

  def main(args: Array[String]): Unit = {

    val sc = new SparkConf().setAppName("mapWithState bug reproduce").setMaster(args(1))
    val sparkContext = new SparkContext(sc)

    val ssc = new StreamingContext(sparkContext, Seconds(4))

    // Create a stream that generates 1000 lines per second
    val stream = ssc.receiverStream(new DummySource(10))

    // Split the lines into words, and create a paired (key-value) dstream
    val wordStream = stream.flatMap {
      _.split(" ")
    }.map(word => {
      val factory = ConfigFactory.load()
      val config: YuvalConfig = ConfigBeanFactory.create(factory.getConfig("yuval"), classOf[YuvalConfig])
      println("------------------------------------------------")
      println(s"Got Key: ${config.dummy}")
      println("------------------------------------------------")
      (word, 1)
    }).print()

    ssc.remember(Minutes(1)) // To make sure data is not deleted by the time we query it interactively

    // Don't forget to set checkpoint directory
    ssc.start()
    ssc.awaitTermination()
  }
}
