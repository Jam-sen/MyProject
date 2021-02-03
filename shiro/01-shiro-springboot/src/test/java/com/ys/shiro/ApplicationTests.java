package com.ys.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = "helloworld".getBytes();
		byte[] result = md5.digest(bytes);
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b :
				result) {
			System.out.println(b);
			int number = b & 0xff;
			String s = Integer.toHexString(number);
			if(s.length()==1){
				stringBuilder.append("0"+s);
			}else{
				stringBuilder.append(s);
			}
		}
		System.out.println(stringBuilder);
	}

}
