package com.rocky.mr.join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class InfoBean implements Writable
{
    private String pID;
    private String pName;
    private String catagoryId;
    private String price;
    private String oID;
    private String date;
    private String opId;
    private String amount;
    //flag = 0 order
    //flag = 1 product
    private String flag;

    public InfoBean()
    {
    }

    public void set(String pID, String pName, String catagoryId, String price, String oID, String date, String opId, String amount, String flag)
    {
        this.pID = pID;
        this.pName = pName;
        this.catagoryId = catagoryId;
        this.price = price;
        this.oID = oID;
        this.date = date;
        this.opId = opId;
        this.amount = amount;
        this.flag = flag;
    }

    public void write(DataOutput out) throws IOException
    {


        out.writeUTF(pID);
        out.writeUTF(pName);
        out.writeUTF(catagoryId);
        out.writeUTF(price);
        out.writeUTF(oID);
        out.writeUTF(date);
        out.writeUTF(opId);
        out.writeUTF(amount);
        out.writeUTF(flag);
    }

    public void readFields(DataInput in) throws IOException
    {
        this.pID = in.readUTF();
        this.pName = in.readUTF();
        this.catagoryId = in.readUTF();
        this.price = in.readUTF();
        this.oID = in.readUTF();
        this.date = in.readUTF();
        this.opId = in.readUTF();
        this.amount = in.readUTF();
        this.flag = in.readUTF();
    }

    @Override
    public String toString()
    {
        return pID + "\t" + pName + "\t"  + catagoryId + "\t"  + price + "\t"  + oID + "\t"  + date + "\t"  + opId + "\t"  + amount + "\t" + flag;
    }

    public String getpID()
    {
        return pID;
    }

    public void setpID(String pID)
    {
        this.pID = pID;
    }

    public String getpName()
    {
        return pName;
    }

    public void setpName(String pName)
    {
        this.pName = pName;
    }

    public String getCatagoryId()
    {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId)
    {
        this.catagoryId = catagoryId;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getoID()
    {
        return oID;
    }

    public void setoID(String oID)
    {
        this.oID = oID;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getOpId()
    {
        return opId;
    }

    public void setOpId(String opId)
    {
        this.opId = opId;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }
}
