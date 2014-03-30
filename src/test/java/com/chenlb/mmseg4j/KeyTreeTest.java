package com.chenlb.mmseg4j;

import com.chenlb.mmseg4j.CharNode.KeyTree;

import junit.framework.TestCase;

public class KeyTreeTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testMatch() {
		char[] w = "为什么".toCharArray();
		KeyTree kt = new KeyTree();
		kt.add(w);
		assertTrue(kt.match(w, 0, w.length));
		assertFalse(kt.match(w, 0, 2));
		assertFalse(kt.match("怎么样".toCharArray(), 0, 3));
		
		w = "国人民银行".toCharArray();
		kt.add(w);
		int tailLen = kt.maxMatch("中国人民银行".toCharArray(), 1);
		assertEquals(tailLen, w.length);
	}
	
	public void testMatch2() {
		Dictionary dic = Dictionary.getInstance();
		int tailLen = dic.maxMatch("中国人民银行".toCharArray(), 0);
		assertEquals(tailLen, 5);
	}
}
