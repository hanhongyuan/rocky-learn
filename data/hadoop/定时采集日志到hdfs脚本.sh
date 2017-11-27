#########################################################################
# File Name: 定时采集日志到hdfs脚本.sh
# Author: rocky
# Mail: wanyouxian@gmail.com
# Created Time: Mon 27 Nov 2017 04:33:22 PM CST
#########################################################################
#!/bin/bash
#set java env
export JAVA_HOME=/home/rocky/myself/src/jdk
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

#set hadoop env
export HADOOP_HOME=/home/rocky/myself/apps/hadoop-2.6.4
export PATH=${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:$PATH


#   1、先将需要上传的文件移动到待上传目录
#   2、在讲文件移动到待上传目录时，将文件按照一定的格式重名名
#       /export/software/hadoop.log1   /export/data/click_log/xxxxx_click_log_{date}


#日志文件存放的目录
log_src_dir=/home/rocky/myself/test_data/timing_put_data_hdfs

#待上传文件存放的目录
log_toupload_dir=/home/rocky/myself/test_data/timing_put_data_hdfs/toupload

#如果文件夹不存在，创建文件夹
if [ ! -d "$log_toupload_dir" ]; then
	mkdir $log_toupload_dir
fi


#日志文件上传到hdfs的根路径
hour=`date +%Y_%m_%d_%H`
hdfs_root_dir=hdfs://node200:9000/data/clickLog/$hour/
echo $hdfs_root_dir

#创建HDFS上不存在的目录
create_hdfs_not_exist_directory() {
	hadoop fs -test -e $hdfs_root_dir

	if [ $? -eq 0 ] ;then
		echo 'Directory exist'
	else
		hadoop fs -mkdir -p $hdfs_root_dir
		echo 'Directory '$hdfs_root_dir' created'  
	fi
}

create_hdfs_not_exist_directory

#打印环境变量信息
echo "envs: hadoop_home: $HADOOP_HOME"


#读取日志文件的目录，判断是否有需要上传的文件
echo "log_src_dir:"$log_src_dir
ls $log_src_dir | while read fileName
do
	if [[ "$fileName" == access.log.* ]]; then
		# if [ "access.log" = "$fileName" ];then
		date=`date +%Y_%m_%d_%H_%M_%S`
		#将文件移动到待上传目录并重命名
		#打印信息
		echo "moving $log_src_dir$fileName to $log_toupload_dir"xxxxx_click_log_$fileName"$date"
		mv $log_src_dir$fileName $log_toupload_dir"xxxxx_click_log_$fileName"$date
		#将待上传的文件path写入一个列表文件willDoing
		echo $log_toupload_dir"xxxxx_click_log_$fileName"$date >> $log_toupload_dir"willDoing."$date
	fi

done
#找到列表文件willDoing
ls $log_toupload_dir | grep will |grep -v "_COPY_" | grep -v "_DONE_" | while read line
	do
		#打印信息
		echo "toupload is in file:"$line
		#将待上传文件列表willDoing改名为willDoing_COPY_
		mv $log_toupload_dir$line $log_toupload_dir$line"_COPY_"
		#读列表文件willDoing_COPY_的内容（一个一个的待上传文件名）  ,此处的line 就是列表中的一个待上传文件的path
		cat $log_toupload_dir$line"_COPY_" |while read line
	do
		#打印信息
		echo "puting...$line to hdfs path.....$hdfs_root_dir"
		hadoop fs -put $line $hdfs_root_dir
	done    
	mv $log_toupload_dir$line"_COPY_"  $log_toupload_dir$line"_DONE_"
done
