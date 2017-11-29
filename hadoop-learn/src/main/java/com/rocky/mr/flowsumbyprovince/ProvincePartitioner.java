package com.rocky.mr.flowsumbyprovince;

import com.rocky.mr.flowsum.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/28/17
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean>
{
    public static HashMap<String, Integer> provinceDict = new HashMap<String, Integer>();
    static {
        provinceDict.put("138", 0);
        provinceDict.put("136", 1);
        provinceDict.put("137", 2);
        provinceDict.put("135", 3);

    }
    public int getPartition(Text text, FlowBean flowBean, int numPartitions)
    {
        String prefix = text.toString().substring(0, 3);
        Integer provinceId = provinceDict.get(prefix);


        return provinceId == null ? 4 : provinceId;
    }
}
