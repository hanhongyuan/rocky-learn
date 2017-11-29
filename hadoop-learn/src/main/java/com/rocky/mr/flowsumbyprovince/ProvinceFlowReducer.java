package com.rocky.mr.flowsumbyprovince;

import com.rocky.mr.flowsum.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/28/17
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ProvinceFlowReducer extends Reducer<Text, FlowBean, Text, FlowBean>
{
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException
    {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for (FlowBean bean : values)
        {
            sumUpFlow += bean.getUpFlow();
            sumDownFlow += bean.getDownFlow();
        }
        context.write(key, new FlowBean(sumUpFlow, sumDownFlow));

    }
}
