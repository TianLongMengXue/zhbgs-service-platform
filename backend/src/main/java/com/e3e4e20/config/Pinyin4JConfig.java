package com.e3e4e20.config;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/*
Filename: Pinyin4JConfig
Created: 2023年04月11日 15时29分27秒 星期二
Author: 天龙梦雪
*/

// https://www.jianshu.com/p/57a6600d2862
public class Pinyin4JConfig {
    /**
     * 获得一个中文汉字拼音首字母,且该字母为大写
     * @param chinese 一个中文汉字
     * @return 该汉字的首字母且该字母大写
     */
    public String getUpperAlphabet(String chinese) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : nameChar) {
            if (c > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
                            c, defaultFormat)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(c);
            }
        }
        return pinyinName.toString();
    }

    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     * @param chineseString 汉字
     * @return 一个纯小写字母的字符串
     */
    public String getLowerPingYin(String chineseString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder output = new StringBuilder();
        if (chineseString != null && chineseString.length() > 0
                && !"null".equals(chineseString)) {
            char[] input = chineseString.trim().toCharArray();
            try {
                for (char c : input) {
                    if (Character.toString(c).matches(
                            "[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                                c, format);
                        output.append(temp[0]);
                    } else
                        output.append(Character.toString(c));
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            return "*";
        }
        return output.toString();
    }
}
/*
 * HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
 *
 * 控制大小写
 * UPPERCASE：大写  (ZHONG)
 * LOWERCASE：小写  (zhong)
 * defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
 *
 * WITHOUT_TONE：无音标  (zhong)
 * WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)
 * WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
 * defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
 *
 * WITH_V：用 "v" 表示 "ü"  (nv)
 * WITH_U_AND_COLON：用"u:"表示 "ü"  (nu:)
 * WITH_U_UNICODE：直接用 "ü" (nü)
 * defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
 *
 * toHanyuPinyinStringArray如果传入的字符不是汉字不能转换成拼音，那么会直接返回null。
 *
 * String[] pinyin = PinyinHelper.toHanyuPinyinStringArray('重', defaultFormat);
 * for(String str: pinyin){
 *    System.out.println(str);
 * }
 */