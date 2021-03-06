package cn.zucc.edu.jxm.analysis;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.jxm.mmseg4j.ComplexSeg;
import com.jxm.mmseg4j.Dictionary;
import com.jxm.mmseg4j.MMSeg;
import com.jxm.mmseg4j.Seg;
import com.jxm.mmseg4j.Word;


public class MessageSeg {
	protected Dictionary dic;

	public MessageSeg() {
		dic = Dictionary.getInstance();
	}

	protected Seg getSeg() {
		return new ComplexSeg(dic);
	}

	public String segWords(Reader input, String wordSpilt) throws IOException {
		StringBuilder sb = new StringBuilder();
		Seg seg = getSeg(); // 取得不同的分词具体算法
		MMSeg mmSeg = new MMSeg(input, seg);
		Word word = null;
		boolean first = true;
		while ((word = mmSeg.next()) != null) {
			if (!first) {
				sb.append(wordSpilt);
			}
			String w = word.getString();
			sb.append(w);
			first = false;

		}
		return sb.toString();
	}

	public String segWords(String txt, String wordSpilt) throws IOException {
		return segWords(new StringReader(txt), wordSpilt);
	}
}
