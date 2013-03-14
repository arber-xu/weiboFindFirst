package com.lhn;

public class StopWords {
	private static String stopWordsList[] ={"的", "我们","要","自己","之","将","“","”","，","（","）","后","应","到","某","后","个","是","位","新","两","在","中","或","有","更","好","","啊","哦","吧"};//常用停用词
	public static boolean IsStopWord(String word)
	{
		for(int i=0;i<stopWordsList.length;++i)
		{
			if(word.equalsIgnoreCase(stopWordsList[i]))
				return true;
		}
		return false;
	}
}
