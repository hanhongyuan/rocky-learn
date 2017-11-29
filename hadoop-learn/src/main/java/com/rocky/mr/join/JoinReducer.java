package com.rocky.mr.join;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JoinReducer extends Reducer<Text, InfoBean, InfoBean, NullWritable>
{
    @Override
    protected void reduce(Text key, Iterable<InfoBean> values, Context context) throws IOException, InterruptedException
    {
        InfoBean pdBean = new InfoBean();
        ArrayList<InfoBean> orderBeans = new ArrayList<InfoBean>();
        for (InfoBean bean : values)
        {
            if ("1".equals(bean.getFlag())){
                try
                {
                    BeanUtils.copyProperties(pdBean, bean);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }else {
                InfoBean obean = new InfoBean();
                try
                {
                    BeanUtils.copyProperties(obean, bean);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                orderBeans.add(obean);
            }
        }
        for (InfoBean bean : orderBeans)
        {
           bean.setpID(pdBean.getpID());
           bean.setpName(pdBean.getpName());
           bean.setCatagoryId(pdBean.getCatagoryId());
           bean.setPrice(pdBean.getPrice());
            context.write(bean, NullWritable.get());
        }
    }
}
