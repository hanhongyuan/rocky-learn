# Scala
## 映射与元组--map tuple
1. 映射相关操作
    ````
    //构造不可变map
    scala> val map1 = Map("tom" -> 12, "rocky" -> 28)
    map1: scala.collection.immutable.Map[String,Int] = Map(tom -> 12, rocky -> 28)
    
    //构造可变map
    scala> import collection.mutable._
    import collection.mutable._
    
    scala> val map2 = Map("lucy" -> 11, "jery" -> 12)
    map2: scala.collection.mutable.Map[String,Int] = Map(jery -> 12, lucy -> 11)
    
    scala> map2 += ("rocky" -> 28)
    res1: map2.type = Map(rocky -> 28, jery -> 12, lucy -> 11)
    
    //映射查询与修改
    scala> map2("rocky")
    res5: Int = 28
    
    scala> map2("rocky") = 30
    
    scala> map2
    res7: scala.collection.mutable.Map[String,Int] = Map(rocky -> 30, jery -> 12, lucy -> 11)
    
    scala> map2.contains("rocky")
    res8: Boolean = true
    
    scala> map2.getOrElse("tom", 0)
    res9: Int = 0
    
    scala> map2.getOrElse("rocky", 0)
    res10: Int = 30
    ````

2. 迭代映射

    **for ((k, v) <- map) k/v操作**
    ````
    scala> for  ((name, age) <- map2) println(name + "   " + age)
    rocky   30
    jery   12
    lucy   11
    ````
    
3. 获取映射的Key或者value
    ````
    scala> for (k <- map2.keys) println(k)
    rocky
    jery
    lucy
    
    scala> for (v <- map2.values) println(v)
    30
    12
    11
    ````
4. 创建排序Map
    ````
    scala> import collection.immutable.SortedMap
    import collection.immutable.SortedMap
    
    scala> val person = SortedMap(("rocky", 28), ("tom", 22))
    person: scala.collection.immutable.SortedMap[String,Int] = Map(rocky -> 28, tom -> 22)
    ````
    
