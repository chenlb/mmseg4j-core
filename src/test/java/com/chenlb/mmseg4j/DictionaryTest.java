package com.chenlb.mmseg4j;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class DictionaryTest {

	private void printMemory() {
		Runtime rt = Runtime.getRuntime();
		long total = rt.totalMemory();
		long free = rt.freeMemory();
		long max = rt.maxMemory();
		System.out.println(String.format("total=%dk, free=%dk, max=%dk, use=%dk", total/1024, free/1024, max/1024, (total-free)/1024));
	}

	@Test
	public void testloadDicMemoryUse() {
		printMemory();
		Dictionary.getInstance();
		printMemory();
	}

	@Test
	public void testloadDic() {
		Dictionary dic = Dictionary.getInstance();
		Dictionary dic2 = Dictionary.getInstance();
		Assert.assertTrue(dic == dic2);

		dic.destroy();
		//reload
		dic2 = Dictionary.getInstance();
		Assert.assertTrue(dic != dic2);
		dic2.destroy();
	}

	@Test
	public void testloadDicByPath() {
		Dictionary dic = Dictionary.getInstance("src");
		Dictionary dic2 = Dictionary.getInstance("./src");
		Assert.assertTrue(dic == dic2);

		Assert.assertFalse(dic.match("自定义词"));

		dic.destroy();
	}

	@Test
	public void testloadMultiDic() {
		Dictionary dic = Dictionary.getInstance();

		Assert.assertTrue(dic.match("自定义词"));
	}

	@Test
	public void testMatch() {
		Dictionary dic = Dictionary.getInstance();

		Assert.assertTrue(dic.match("词典"));

		Assert.assertFalse(dic.match("人个"));
		Assert.assertFalse(dic.match("三个人"));

		Assert.assertFalse(dic.match(""));
		Assert.assertFalse(dic.match("人"));

	}

	@Test
	public void testFileHashCode() throws IOException {
		File f = new File("data");
		File f1 = new File("./data");
		Assert.assertFalse(f.equals(f1));

		f1 = f.getAbsoluteFile();
		Assert.assertFalse(f.equals(f1));

		Assert.assertTrue(f.getCanonicalFile().equals(f1.getCanonicalFile()));

		f1 = new File("data");
		Assert.assertTrue(f.equals(f1));
	}
}
