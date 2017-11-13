package com.rocky.actor


import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created with IntelliJ IDEA.
  * User: Rocky
  * Date: 2017/11/13
  * Time: 21:28
  * To change this template use File | Settings | File Templates.
  * Description: 通过Scala的Actor并发实现word count Demo
  */

/**
  * MapTask继承Actor实现并发类
  *
  */
class MapTask extends Actor {


    override def act(): Unit = {

        loop {
            react {
                case SubmitTask(filename) => {
                    //进行map操作--最终结果为Map[String, Int]
                    //Map(rocky -> 1, tom -> 1, jery -> 1, hello -> 3)
                    val result = Source.fromFile(filename).getLines().flatMap(_.split(" ")).map((_, 1)).toList.groupBy(_._1).mapValues(_.size)
                    print(result)
                    //发送ResultTask,用它来包装result
                    sender ! ResultTask(result)
                }

                case StopTask => {

                    exit()
                }
            }
        }
    }
}

//case class 定义
case class SubmitTask(filename: String)

case class ResultTask(result: Map[String, Int])

case object StopTask

//WordCount入口
object WordCount {

    val replySet = new mutable.HashSet[Future[Any]]()
    val resultList = new ListBuffer[ResultTask]

    def main(args: Array[String]): Unit = {

        val files = Array[String]("f://words.txt", "f://words.log")

        //为每个文件启动一个线程，并开启mapTask作业，并将异步的Future放到集合
        for (f <- files) {
            val actor = new MapTask
            //启动线程并发送异步有返回值[Future]消息
            val reply = actor.start() !! SubmitTask(f)
            replySet += reply
        }


        //遍历集合中的所有Future，直到所有的异步处理都有返回值

        while (replySet.size > 0) {
            //遍历集合，将有完成的Future
            val toCompute = replySet.filter(_.isSet)
            for (f <- toCompute) {
                val result = f().asInstanceOf[ResultTask]
                resultList += result
                replySet -= f
            }
            Thread.sleep(100)
        }


        //汇总功能，相当于reduce
        //多个这样的Map(rocky -> 1, tom -> 1, jery -> 1, hello -> 3)
        val result = resultList.flatMap(_.result).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))
        println(result)

    }
}

