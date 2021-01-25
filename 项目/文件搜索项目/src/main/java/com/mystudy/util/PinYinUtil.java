package com.mystudy.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {
    public static String getPinYin(String name) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        StringBuilder sb = new StringBuilder();
        for(char ch : name.toLowerCase().toCharArray()) {
            try {
                String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                if (pinyinArr == null || pinyinArr.length == 0) {
                    sb.append(ch);
                    continue;
                }
                sb.append(pinyinArr[0]);
            }catch (BadHanyuPinyinOutputFormatCombination e) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String getPinYinFirst(String name) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        StringBuilder sb = new StringBuilder();
        for(char ch : name.toLowerCase().toCharArray()) {
            try {
                String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                if (pinyinArr == null || pinyinArr.length == 0) {
                    sb.append(ch);
                    continue;
                }
                sb.append(pinyinArr[0].charAt(0));
            }catch (BadHanyuPinyinOutputFormatCombination e) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "我是一个中国人";
        System.out.println(getPinYin(s));
        System.out.println(getPinYinFirst(s));
    }
}
