package com.chenlb.mmseg4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Ignore;
import org.junit.Test;

public class MyTest {

	public void test100Log() {
		int freq = 1034142;
		print100Log(freq);

		freq = 847332;
		print100Log(freq);
	}

	private void print100Log(int freq) {
		int my100Log = (int) (Math.log(freq) * 100);
		System.out.println(freq+" -> "+my100Log+" | "+(Math.log(freq) * 100));
	}

	public void testDicPath() throws URISyntaxException {
		URL url = Dictionary.class.getResource("/");
		String path = "";
		path = url.toURI().getRawPath();
		System.out.println(path);
		File f = new File(path+"data");
		System.out.println(f+" -> "+f.exists());


		path = url.toExternalForm();
		System.out.println(path);

		path = url.getPath();
		System.out.println(path);

		path = System.getProperty("user.dir");
		System.out.println(path);
	}

	public void testZhNumCodeP() {
		String num = "０１２３４５６７８９";
		String n = "0123456789";
		for(int i=0; i<num.length(); i++) {
			int cp = num.codePointAt(i);
			int ncp = n.codePointAt(i);
			System.out.println((char)cp+" -> "+cp+", "+(char)ncp+" -> "+ncp);
		}
	}

	public void testCodePAndType() {
		String str = "09０９☆§┍┄○一＄￥≈∑①⑩㈠㈩⒈⒑⒒⒛⑴⑽⑾⒇！中文【ゥスぁまēūㄇㄎноνπⅠⅡⅢ";

		str = "ぁぃぅぇぉかきくけこんさしすせそたちつってとゐなにぬねのはひふへほゑまみむめもゃゅょゎを";
		str += "あいうえおがぎぐげござじずぜぞだぢづでどぱぴぷぺぽばびぶべぼらりるれろやゆよわ";

		str += "ァィゥヴェォカヵキクケヶコサシスセソタチツッテトヰンナニヌネノハヒフヘホヱマミムメモャュョヮヲ";
		str += "アイウエオガギグゲゴザジズゼゾダヂヅデドパピプペポバビブベボラリルレロヤユヨワ";

		str = "āáǎàōóǒòêēéěèīíǐìūúǔùǖǘǚǜü";

		/*str = "ㄅㄉˇˋㄓˊ˙ㄚㄞㄢㄦㄆㄊㄍㄐㄔㄗㄧㄛㄟㄣㄇㄋㄎㄑㄕㄘㄨㄜㄠㄤㄈㄌㄏㄒㄖㄙㄩㄝㄡㄥ";

		str = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
		str += "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";*/

		/*str = "αβγδεζηθικλμνξοπρστυφχψω";
		str += "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";*/

		int[] cps = new int[str.length()];
		for(int i=0; i<str.length(); i++) {
			cps[i] = str.codePointAt(i);
		}
		Arrays.sort(cps);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length(); i++) {
			sb.setLength(0);
			int cp = cps[i];//str.codePointAt(i);
			sb.appendCodePoint(cp).append(" -> ").append(cp);
			sb.append(", type=").append(Character.getType(cp));
			sb.append(", hex=").append(Integer.toHexString(cp));
			System.out.println(sb);
		}
	}

	public void testCodePAndType2() {

		int start = 12435+1;
		int end = 12449-1;

		start = 0xff21;
		end = 0xff5a;

		StringBuilder sb = new StringBuilder();
		for(int i=start; i<=end; i++) {
			sb.setLength(0);
			int cp = i;//str.codePointAt(i);
			sb.appendCodePoint(cp).append(" -> ").append(cp);
			sb.append(", type=").append(Character.getType(cp));
			sb.append(", hex=").append(Integer.toHexString(cp));
			System.out.println(sb);
		}
	}

	@Test
	@Ignore
	public void testShowUnicode() {
		int c = 0x2F81A;
		int mc = Character.toLowerCase(c);
		StringBuilder sb = new StringBuilder();
		sb.appendCodePoint(c).append(" --to low--> ").appendCodePoint(mc);
		System.out.println("c="+c+",mc="+mc+"\n"+sb);
	}

	private static long now() {
		return System.currentTimeMillis();
	}

	@Test
	@Ignore
	public void testSeeSogouDic() throws IOException {
		Dictionary dic = Dictionary.getInstance("sogou");
		Map<Character, CharNode> dict = dic.getDict();
		long start = now();
		List<Map.Entry<Character, CharNode>> es = new ArrayList<Map.Entry<Character,CharNode>>(dict.size());
		es.addAll(dict.entrySet());
		System.out.println("add use "+(now()-start)+"ms");
		start = now();
		Collections.sort(es, new Comparator<Map.Entry<Character, CharNode>>() {

			public int compare(Entry<Character, CharNode> a,
					Entry<Character, CharNode> b) {
				int r = -new Integer(a.getValue().getMaxLen()).compareTo(b.getValue().getMaxLen());
				if(r == 0) {
					r = -new Integer(a.getValue().wordNum()).compareTo(b.getValue().wordNum());
				}
				if(r == 0) {
					r = -new Integer(a.getValue().getFreq()).compareTo(b.getValue().getFreq());
				}
				return r;
			}

		});
		System.out.println("sort use "+(now()-start)+"ms");
		start = now();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("sogou/word-stat.txt")), "UTF-8"));
		writer.append("char").append('\t')
		.append("freq").append('\t')
		.append("maxLen").append('\t')
		.append("wordNum").append('\t')
		.append("lens").append("\r\n");
		for(Map.Entry<Character, CharNode> e : es) {
			CharNode cn = e.getValue();
			writer.append(e.getKey()).append('\t')
				.append(cn.getFreq()+"").append('\t')
				.append(cn.getMaxLen()+"").append('\t')
				.append(cn.wordNum()+"").append('\t')
				.append("\r\n");
		}
		writer.close();
		System.out.println("writer use "+(now()-start)+"ms");
	}
}
