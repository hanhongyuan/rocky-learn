# mapreduce 简介
    Mapreduce是一个分布式运算程序的编程框架，是用户开发"基于hadoop的数据分析应用"的核心框架。
    Mapreduce核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的分布式运算程序，并发运行在一个hadoop集群上。

# mapreduce 运行时框架结构和执行流程
1. 结构

    一个完整的mapreduce程序在分布式运行时有三类实例进程：
    1. MRAppMaster：负责整个程序的过程调度及状态协调
    2. mapTask：负责map阶段的整个数据处理流程
    3. ReduceTask：负责reduce阶段的整个数据处理流程
2. 流程解析
    1. 一个mr程序启动的时候，最先启动的是MRAppMaster，MRAppMaster启动后根据本次job的描述信息,计算出需要的maptask实例数量，然后向集群申请机器启动相应数量的maptask进程
	2. maptask进程启动之后，根据给定的数据切片范围进行数据处理，主体流程为：
        - 利用客户指定的inputformat来获取RecordReader读取数据，形成输入KV对
        - 将输入KV对传递给客户定义的map()方法，做逻辑运算,并将map()方法输出的KV对收集到缓存
        - 将缓存中的KV对按照K分区排序后不断溢写到磁盘文件
    3. MRAppMaster监控到所有maptask进程任务完成之后，会根据客户指定的参数启动相应数量的reducetask进程，并告知reducetask进程要处理的数据范围(数据分区)
    4. Reducetask进程启动之后，根据MRAppMaster告知的待处理数据所在位置，从若干台maptask运行所在机器上获取到若干个maptask输出结果文件，并在本地进行重新归并排序，然后按照相同key的KV为一个组，调用客户定义的reduce()方法进行逻辑运算，并收集运算输出的结果KV，然后调用客户指定的outputformat将结果数据输出到外部存储

            

