package com.chenlb.mmseg4j;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.chenlb.mmseg4j.example.MaxWord;

public class MaxWordSegTest {

	MaxWord segW;
	@Before
	public void setUp() throws Exception {
		segW = new MaxWord();
	}

	@Test
	public void testEffect() throws IOException {
		String words = segW.segWords("共和国", "|");
		Assert.assertEquals("共和|国", words);
	}

	@Test
	public void testEffect1() throws IOException {
		String words = segW.segWords("中国人民银行", "|");
		Assert.assertEquals("中国|国人|人民|银行", words);
	}

	@Test
	public void testEffect2() throws IOException {
		String words = segW.segWords("西伯利亚", "|");
		Assert.assertEquals("西|伯|利|亚", words);
	}

	@Test
	public void testEffect3() throws IOException {
		String words = segW.segWords("中华人民共和国", "|");
		Assert.assertEquals("中华|华人|人民|共和|国", words);
	}

	@Test
	public void testEffect4() throws IOException {
		String words = segW.segWords("羽毛球拍", "|");
		Assert.assertEquals("羽毛|球拍", words);
	}

	@Test
	public void testEffect5() throws IOException {
		String words = segW.segWords("化装和服装", "|");
		Assert.assertEquals("化装|和|服装", words);
	}

	@Test
	public void testEffect6() throws IOException {
		String words = segW.segWords("为什么", "|");
		Assert.assertEquals("为|什么", words);
	}

	@Test
  @Ignore
	public void testEffect7() throws IOException {
		String words = segW.segWords("很好听", "|");
    // Complex 分出 '很|好听'
    // 目前 max-word 是在 complex 之后再分词的。
		Assert.assertEquals("很好|好听", words);
	}

	@Test
	public void testEffect8() throws IOException {
		String words = segW.segWords("强冷空气", "|");
		Assert.assertEquals("强|冷|空气", words);
	}

	/**
	 * 自扩展的词库文件
	 */
	@Test
	public void testEffect9() throws IOException {
		String words = segW.segWords("白云山", "|");
		Assert.assertEquals("白云|云山", words);
	}

	@Test
	public void testEffect10() throws IOException {
		String words = segW.segWords("清华大学", "|");
		Assert.assertEquals("清华|大学", words);
	}

	@Test
	public void testEffect11() throws IOException {
		String words = segW.segWords("华南理工大学", "|");
    // '工大' 在词库中没有
    Assert.assertEquals("华南|理工|大学", words);
	}

	@Test
	public void testEffect12() throws IOException {
		String words = segW.segWords("广东工业大学", "|");
    // '业大' 在词库中有
    Assert.assertEquals("广东|工业|业大|大学", words);
	}
}
