# Experimental Spark Project Setup
* See [Spark, Uber Jars and Shading with sbt-assembly](http://asyncified.io/2016/04/07/spark-uber-jars-and-shading-with-sbt-assembly/)

Run via

```
./spark-submit --master spark://master:7777  \
	--driver-class-path=config-1.3.0.jar \
	--class Program SimpleBugReproduce-assembly-2.0.jar
```