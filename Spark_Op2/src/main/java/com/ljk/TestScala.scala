package com.ljk

import java.io.FileInputStream

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.math.BigInt.probablePrime
import scala.util.parsing.json.JSON

object TestScala {
  def main(args: Array[String]): Unit = {
//    val array = new Array[String](3)
//    array(0) = "a"
//    array(1) = "b"
//    array(2) = "c"
//
//    val result = array.filter(x => x.equalsIgnoreCase("d"))
//    print(result.length)
//    print(!"a".equalsIgnoreCase("a"))
//    testMap
//    val kv = testJson
//    kv.keySet.map(key => println("key:"+key+" value:"+kv.get(key).get))
    testString("a","b","c")
  }
  def testMap = {
    val map = new mutable.HashMap[String,String]()
    map.put("a","1")
    map.put("b","2")
    map.put("c","3")
    val key = map.keySet
//    key.map(x => println(x))

    val result = key.filter(x => x.equalsIgnoreCase("g"))
    println(result.size)

    val ab = new ArrayBuffer[String]()
    ab+="a"
    ab+="b"
    println(ab.size)
    println(ab.apply(0))
  }
  def test = {
    val map = new mutable.HashMap[String,ArrayBuffer[String]]()

    val ab = new ArrayBuffer[String]()
    ab+="a"
    ab+="b"
    val ab2 = new ArrayBuffer[String]()
    ab2+="c"
    ab2+="d"

    map.put("1",ab)
    map.put("2",ab2)

    println(map.keySet.map(x =>{
      map.get(x).size
    }))


  }
  def testJson = {
    val in = new FileInputStream("C:\\A-MyWork\\GitR\\git\\noteWork\\Fast_Json\\src\\main\\java\\com\\ljk\\test1.json")
    val rb = new Array[Byte](2048)
    val content = in.read(rb)
    in.close()
    val jsonString = new String(rb)
    val kv = new mutable.HashMap[String,List[String]]()
    val json = JSON.parseFull(jsonString)
    println(json)
      json match {
        case Some(lists:List[Map[String,String]]) =>{
          lists.map(list => list.keySet.map(key => {
            if ( kv.contains(key)){
              kv.put(key,kv.get(key).get:::List(list.get(key).get))
            }
            else if (!kv.contains(key)){
              kv.put(key,List(list.get(key).get))
            }
            else println("kv error")
          }))
        }
        case Some(map:Map[String,String]) => {
          map.keySet.map(key => kv.put(key,List(map.get(key).get)))
        }
        case None => println("error")
        case _ => println("unkonw data")
      }

//    println(json)
    kv
  }
  def testString(s : String*) = {
    s.size match {
      case 1 => println("1")
      case 2 => println("2")
      case 3 => println("3a")
    }

  }
}
