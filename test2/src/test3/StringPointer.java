/*
 * @Author: your name
 * @Date: 2019-10-23 15:58:46
 * @LastEditTime: 2019-10-31 17:40:19
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \crawler\src\cn\intian\sensi\StringPointer.java
 */
package test3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * 没有注释的方法与{@link String}类似<br/>
 * <b>注意：</b>没有（数组越界等的）安全检查<br/>
 * 可以作为{@link HashMap}和{@link TreeMap}的key
 * 
 */
public class StringPointer implements Serializable, CharSequence, Comparable<StringPointer> {

	private static final long serialVersionUID = 1L;

	protected char[] value;

	protected final int offset;

	protected int length;

	private StopWordSet sws;
	protected int q;
	protected int p;

	private int hash = 0;

	private static class StopWordSet {
		private HashSet<String> set = new HashSet<>();
		private static StopWordSet DEFAULT;

		/*
		 * private void initHelper() { try { DEFAULT = new StopWordSet(new
		 * FileReader("stopwords.txt")); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		private StopWordSet() {

		}

		private StopWordSet(Reader is) {
			BufferedReader br = new BufferedReader(is);
			try {
				for (String str = br.readLine(); str != null; str = br.readLine()) {
					set.add(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static StopWordSet getDefaultInstance() {
			if (DEFAULT == null) {
				try {
					InputStream is =new FileInputStream("d:\\sensi_stopwords.txt"); 
					BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));			
					DEFAULT = new StopWordSet(reader);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return DEFAULT;
		}

		public boolean isContains(String str) {
			return set.contains(str);
		}
	}
	
	public StringPointer(String str){
		this.value = str.toCharArray();
		this.offset = 0;
		this.length = value.length;
		this.sws = StopWordSet.getDefaultInstance();
	}
	
	public StringPointer(char[] value, int offset, int length){
		this.value = value;
		this.offset = offset;
		this.length = length;
		this.sws = StopWordSet.getDefaultInstance();
	}

	public void nextTwoChar(int i) {
		while(i < length) {
			if(!sws.isContains(String.valueOf(value[offset + i]))) {
				int j = i + 1;
				while(j < length) {
					if(!sws.isContains(String.valueOf(value[offset + j]))) {
						p = j;
						break;
					}

					++j;
				}

				q = i;
				break;
			}

			++i;
		}
	}
	
	/**
	 * 计算该位置后（包含）2个字符的hash值
	 * 
	 * @param i 从 0 到 length - 2
	 * @return hash值
	 */
	public int nextTwoCharHash(int i){
		nextTwoChar(i);
		return 31 * value[offset + q] + value[offset + p];
	}
	
	/**
	 * 计算该位置后（包含）2个字符和为1个int型的值<br/>
	 * int值相同表示2个字符相同
	 * 
	 * @param i 从 0 到 length - 2
	 * @return int值
	 */
	public int nextTwoCharMix(int i){
		nextTwoChar(i);
		return (value[offset + q] << 16) | value[offset + p];
	}
	
	/**
	 * 该位置后（包含）的字符串，是否以某个词（word）开头
	 * 
	 * @param i 从 0 到 length - 2
	 * @param word 词
	 * @return 是否？
	 */
	public int nextStartsWith(int i, StringPointer word){
		int k = 0, j = 0;

		// 是否长度超出
		if(word.length > length - i){
			return -1;
		}

		while(k < length() - offset - i && j < word.length() - word.offset){
			if(!sws.isContains(String.valueOf(value[offset + i + k]))) {
				if(value[offset + i + k] != word.value[word.offset + j]) {
					return -1;
				}
				++j;
			}
			++k;
		}

		if(word.offset + j != word.length()) {
			return -1;
		} else {
			return k;
		}
	}
	
	/**
	 * 填充（替换）
	 * 
	 * @param begin 从此位置开始（含）
	 * @param end 到此位置结束（不含）
	 * @param fillWith 以此字符填充（替换）
	 */

	public int hightLight(int pos1, int pos2, char[] newValue, int newValPos) {
		String leftTag = "<font color='red'>";
		String rightTag = "</font>";
		//char[] newValue = new char[value.length + leftTag.length() + rightTag.length()];
		//System.out.println(newValPos);
		//System.arraycopy(value, 0, newValue, newValPos, offset + pos1);
		//newValPos = offset + pos1;
		for(char c : leftTag.toCharArray()) {
			newValue[newValPos++] = c;
		}
		
		System.arraycopy(value, offset + pos1, newValue, newValPos, pos2 - pos1);
		newValPos += pos2 - pos1;
		for(char c : rightTag.toCharArray()) {
			newValue[newValPos++] = c;
		}
		return newValPos;
		//value = newValue;
		//length += leftTag.length() + rightTag.length();

	}
	public void fill(int begin, int end, char fillWith){
		/* for(int i = begin; i < end; i ++){
			value[offset + i] = fillWith;
		} */
	}
	
	public int length(){
		return length;
	}
	
	public char charAt(int i){
		return value[offset + i];
	}

	private char[] getVal(int begin, int end) {
		char[] val = new char[end - begin];
		int j = 0;
		for(int i = offset + begin; i < end; ++i) {
			if(!sws.isContains(String.valueOf(value[i]))) {
				val[j++] = value[i];
			}
		}
		return val;
	}
	
	public StringPointer substring(int begin){
		char[] val = getVal(begin, length());
		return new StringPointer(val, 0, val.length);
	}
	
	public StringPointer substring(int begin, int end){
		char[] val = getVal(begin, end);
		return new StringPointer(val, 0, val.length);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return substring(start, end);
	}
	
	public String toString(){
		return new String(value, offset, length);
	}
	
	public int hashCode() {
		int h = hash;
		if (h == 0 && length > 0) {
			for (int i = 0; i < length; i++) {
				h = 31 * h + value[offset + i];
			}
			hash = h;
		}
		return h;
	}
	
	public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof StringPointer) {
        	StringPointer that = (StringPointer)anObject;
            if (length == that.length) {
                char v1[] = this.value;
                char v2[] = that.value;
                for(int i = 0; i < this.length; i ++){
                	if(v1[this.offset + i] != v2[that.offset + i]){
                		return false;
                	}
                }
                return true;
            }
        }
        return false;
    }

	@Override
	public int compareTo(StringPointer that) {
		int len1 = this.length;
        int len2 = that.length;
        int lim = Math.min(len1, len2);
        char v1[] = this.value;
        char v2[] = that.value;

        int k = 0;
        while (k < lim) {
            char c1 = v1[this.offset + k];
            char c2 = v2[that.offset + k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
	}

	public static void main(String... args) {
		StringPointer sp = new StringPointer("abcdefghijklmn");
		sp.fill(3, 7, '*');
		System.out.println(sp.value);
	}
}