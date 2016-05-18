package com.yz.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SafeConvertor {
	private static final Logger log = LoggerFactory
			.getLogger(SafeConvertor.class);
	public static String toString(Object obj){
		if(null == obj){
			return "";
		}
		else{
			return obj.toString();
		}
	}
	
	public static boolean ParseBoolean(Object obj){
		boolean bRet = false;
		if(null == obj){
			return bRet;
		}else{
			int iResult = 0;
			if(obj instanceof Boolean){
				bRet = (Boolean)obj;
			}
			else{
				try{
					iResult =Integer.parseInt(obj.toString());
					if(iResult != 0){
						bRet = true;
					}
					else{
						bRet = false;
					}
				}
				catch (NumberFormatException e){
					log.warn(e.getMessage());
				}
			}
			
			return bRet;
		}
	}
	
	public static int ParseInt(Object obj){
		if(null == obj){
			return 0;
		}else{
			int iResult = 0;
			try{
				iResult =Integer.parseInt(obj.toString());
			}
			catch (NumberFormatException e){
				log.warn(e.getMessage());
			}
			return iResult;
		}
	}
	
	public static int ByteArray2Int(byte[] buf){
		int i = 0x000000ff & buf[3];
		i = i | (0x0000ff00 & buf[2]);
		i = i | (0x00ff0000 & buf[1]);
		i = i | (0xff000000 & buf[0]);
		return i;
	}
	
	public static byte[] Int2ByteArray(int i) throws Exception {
		  ByteArrayOutputStream buf = new ByteArrayOutputStream();   
		  DataOutputStream out = new DataOutputStream(buf);   
		  out.writeInt(i);   
		  byte[] b = buf.toByteArray();
		  out.close();
		  buf.close();
		  return b;
		 }
}
