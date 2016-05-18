package com.yz.utils;

import org.apache.mina.core.buffer.IoBuffer;



public class DataConvertor {
	public static byte[] toByteArray (Object obj) {      
		
	             
	    IoBuffer buf = (IoBuffer)obj;
		
	   
		return buf.array();
		
       
    }
	
	public static byte[] toByteArray(int iValue, boolean bLittleEndian)
	{
		byte[] data = new byte[4];
		
		if(bLittleEndian)
		{
			data[0] = (byte)(iValue & 0x000000ff);
			data[1] = (byte)((iValue & 0x0000ff00) >> 8);
			data[2] = (byte)((iValue & 0x00ff0000) >> 16);
			data[3] = (byte)((iValue & 0xff000000) >> 24);
		}
		else{
			data[3] = (byte)(iValue & 0x000000ff);
			data[2] = (byte)((iValue & 0x0000ff00) >> 8);
			data[1] = (byte)((iValue & 0x00ff0000) >> 16);
			data[0] = (byte)((iValue & 0xff000000) >> 24);
		}
		
		return data;
	}
	
	public static long toLong(byte [] data, boolean bLittleEndian)
	{
		long lValue = 0;
		int iLen = data.length;
		if(bLittleEndian)
		{
			switch(iLen)
			{
			case 4:
				lValue = (data[0] & 0x000000ff) |
				 ((data[1] & 0x000000ff) <<8) |
				 ((data[2] & 0x000000ff) << 16)|
				 ((data[3] & 0x000000ff) << 24) ; 
				break;
			case 3:
				lValue = (data[0] & 0x000000ff) |
				 ((data[1] & 0x000000ff) <<8) |
				 ((data[2] & 0x000000ff) << 16);
				 
				break;
			case 2:
				lValue = (data[0] & 0x000000ff) |
				 ((data[1] & 0x000000ff) <<8);
				 
				break;
			default:
				break;
			}
		}
		else
		{
			switch(iLen)
			{
			case 4:
				lValue = (data[3] & 0x000000ff) |
				 ((data[2] & 0x000000ff) <<8) |
				 ((data[1] & 0x000000ff) << 16)|
				 ((data[0] & 0x000000ff) << 24) ; 
				break;
			case 3:
				lValue = (data[2] & 0x000000ff) |
				 ((data[1] & 0x000000ff) <<8) |
				 ((data[0] & 0x000000ff) << 16);
				 
				break;
			case 2:
				lValue = (data[1] & 0x000000ff) |
				 ((data[0] & 0x000000ff) <<8);
				 
				break;
			default:
				break;
			}
		}
		
		
		
		return lValue;
	}
	
	public static String toString(final byte[] data){
	
		if(null == data)
			return "";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < data.length; i++) {
			char value = (char) (data[i] & 0xFF);
			if(0 == value)
				value = '0';
			buf.append(value);
		}
		return buf.toString();
	}
	
	public static String toString_NoZeroAdded(final byte[] data){
		
		if(null == data)
			return "";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < data.length; i++) {
			char value = (char) (data[i] & 0xFF);
			if(0 == value)
				break;
			buf.append(value);
		}
		return buf.toString();
	}
	
	public static String toHexString(byte[] data) {
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < data.length; i++) {
			int value = data[i] & 0xFF;
			if (value < 16)
				buf.append("0");
			buf.append(Integer.toHexString(value));
		}
		return buf.toString();
	}
	
	 public static String bytesToHexString(byte[] src){  
	      StringBuilder stringBuilder = new StringBuilder("");  
	      if (src == null || src.length <= 0) {  
	          return null;  
	      }  
	      for (int i = 0; i < src.length; i++) {  
	          int v = src[i] & 0xFF;  
	          String hv = Integer.toHexString(v);  
	          if (hv.length() < 2) {  
	              stringBuilder.append(0);  
	          }  
	          stringBuilder.append(hv);  
	      }  
	  //    System.out.println("---"+stringBuilder.toString()+"---");
	      
	      return stringBuilder.toString();  
	  } 
}
