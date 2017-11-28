package com.rocky.mr.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/28
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean>
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
        FlowBean resultBean = new FlowBean(sumUpFlow, sumDownFlow);
        context.write(key, resultBean);

    }
}
