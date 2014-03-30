package com.chenlb.mmseg4j;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.chenlb.mmseg4j.example.Complex;

public class ComplexSegTest {

	Complex segW;
	@Before
	public void setUp() throws Exception {
		segW = new Complex();
		//ComplexSeg.setShowChunk(true);
	}

	/*public void testSeg() {
		String txt = "";
		txt = "各人发表关于受一股来自中西伯利亚的强冷空气影响";
		ComplexSeg.setShowChunk(true);
		ComplexSeg seg = new ComplexSeg(new Dictionary("dic"));	//sogou
		Sentence sen = new Sentence(txt.toCharArray(), 0);
		System.out.println();
		while(!sen.isFinish()) {
			Chunk chunk = seg.seg(sen);
			System.out.println(chunk+" -> "+chunk.getStartOffset());
		}
	}*/

	@Test
	public void testEffect() throws IOException {
		String words = segW.segWords("研究生命起源", "|");
		Assert.assertEquals("研究|生命|起源", words);
	}

	@Test
	public void testEffect1() throws IOException {
		String words = segW.segWords("为首要考虑", "|");
		Assert.assertEquals("为首|要|考虑", words);
	}

	@Test
	public void testEffect2() throws IOException {
		String words = segW.segWords("眼看就要来了", "|");
		Assert.assertEquals("眼看|就要|来|了", words);
	}

	@Test
	public void testEffect3() throws IOException {
		String words = segW.segWords("中西伯利亚", "|");
		Assert.assertEquals("中|西伯利亚", words);
	}

	@Test
	public void testEffect4() throws IOException {
		String words = segW.segWords("国际化", "|");
		Assert.assertEquals("国际化", words);
	}

	@Test
	public void testEffect5() throws IOException {
		String words = segW.segWords("化装和服装", "|");
		Assert.assertEquals("化装|和|服装", words);
	}

	@Test
	public void testEffect6() throws IOException {
		String words = segW.segWords("中国人民银行", "|");
		Assert.assertEquals("中国人民银行", words);
	}

	/**
	 * 自扩展的词库文件
	 */
	@Test
	public void testEffect7() throws IOException {
		String words = segW.segWords("白云山", "|");
		Assert.assertEquals("白云山", words);
	}

	@Test
	public void testEffect10() throws IOException {
		String words = segW.segWords("清华大学", "|");
		Assert.assertEquals("清华大学", words);
	}

	@Test
	public void testEffect11() throws IOException {
		String words = segW.segWords("华南理工大学", "|");
		Assert.assertEquals("华南理工大学", words);
	}

	@Test
	public void testEffect12() throws IOException {
		String words = segW.segWords("广东工业大学", "|");
		Assert.assertEquals("广东工业大学", words);
	}

	@Test
	public void testUnitEffect() throws IOException {
		String words = segW.segWords("2008年底发了资金吗", "|");
		Assert.assertEquals("2008|年|底|发|了|资金|吗", words);
	}

	@Test
	public void testUnitEffect1() throws IOException {
		String words = segW.segWords("20分钟能完成", "|");
		Assert.assertEquals("20|分钟|能|完成", words);
	}
}
