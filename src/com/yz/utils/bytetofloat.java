package com.yz.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class bytetofloat {

	public static float bytes2float(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(
				new ByteArrayInputStream(data));
		float f = dis.readFloat();
		dis.close();
		return f;
	}

	public static float bytes2float(byte[] data, int offset, int length)
			throws IOException {

		byte[] tmp = new byte[length];

		for (int i = 0; i < length; i++)
			tmp[i] = data[i + offset];

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(tmp));
		float f = dis.readFloat();
		dis.close();
		return f;
	}

	public static void main(String[] args) throws IOException {
		byte[] b = { 0x41, (byte) 0xc8, 0, 0 };
		System.out.println(bytes2float(b));

	}
}