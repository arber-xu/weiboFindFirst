package com.lhn;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextArea;
import javax.swing.*;



import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

public class FirstAnalysis extends JFrame {
	
	private JTextField jtfName1;
	private TextArea jtfName2;
	private ArrayList<String> test;
	
	
	public FirstAnalysis(){
		/*panel1显示用户名*/
		jtfName1 = new JTextField(20);
	
		
		JButton jtbShow=new JButton("Show the first time");
		JPanel panel1=new JPanel();
		
		
		panel1.add(jtfName1);
		
		panel1.add(jtbShow);
		
		/*panel2显示分析结果*/
		 jtfName2 = new TextArea("Result\n");
		JPanel panel2= new JPanel();
		JScrollPane scroll=new JScrollPane(jtfName2);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panel2.add(jtfName2);
		panel2.add(scroll);
	
	    
	    add(panel1, "Center");
	    add(panel2, "South");
	  
		ShowListenerClass listener1 = new ShowListenerClass();
		
		
		jtbShow.addActionListener(listener1);
		
		test = new ArrayList();
		
	}
	public static void main(String[] args) {
		JFrame frame = new FirstAnalysis();
		frame.pack();
		frame.setTitle("Your first time");
		frame.setLocation(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	//显示用户的微博
	   class ShowListenerClass implements ActionListener{
			public void actionPerformed(ActionEvent e){
				getAllMicroblog(jtfName1.getText());
				JOptionPane.showMessageDialog(null, "Show button");
			}
		}
	
		//显示用户的微博
		public void getAllMicroblog(String name){
			//初始化，
			Weibo weibo = new Weibo();
			String access_token = "2.00itKq1ClJMBkDc9b8527a28Tma5LE";
			weibo.client.setToken(access_token);
			Timeline tm = new Timeline();
			tm.client.setToken(access_token);
			int pagenum = 1;
			int i=1;
			for(;pagenum<60;pagenum++) {
			
			
			try {
				//获得用户的微博
				String a=new String("");
				String result = new String("");
				jtfName1.setText(a);
				
				StatusWapper status = tm.getUserTimelineByName(name,pagenum);
				for(Status s : status.getStatuses()){
					//获取文本信息
					a = s.getText();  
					if(IsFirst(a)) {
						
						jtfName2.append(a+'\n');
						
					    jtfName2.append(dateToString(s.getCreatedAt(),"yyyy-MM-dd")+"  第一次"+test.get(test.size()-1)+'\n');
					}
					i++;
					
				
				}
			} catch (WeiboException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			
		}
			for(String s:test) {
				System.out.println(s);	
			}
			jtfName2.append("分析微博数量："+i+'\n');
		}
		
		//获取分析结果
		public boolean IsFirst(String w){
			
			String[] terms = null;
			terms= spliter.split(w, " ").split(" ");//中文分词处理(分词后结果可能还包含有停用词）
			terms = DropStopWords(terms);//去掉停用词，以免影响分析
			
			for(int i = 0;i<terms.length;i++) {
				
				if(terms[i].equals("第一次")||terms[i].equals("初次")||terms[i].equals("首次")) {
					if(i+1<terms.length) {
					test.add(terms[i+1]);
					}
					return true;
				}
			}
			
			return false;
			
		}
		
		/**
		* 去掉停用词
		* @param text 给定的文本
		* @return 去停用词后结果
		*/
		public String[] DropStopWords(String[] oldWords)
		{
			Vector<String> v1 = new Vector<String>();
			for(int i=0;i<oldWords.length;++i)
			{
				if(StopWords.IsStopWord(oldWords[i])==false)
				{//不是停用词
					v1.add(oldWords[i]);
				}
			}
			String[] newWords = new String[v1.size()];
			v1.toArray(newWords);
			return newWords;
		}
		
		// date类型转换为String类型
	 	// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	 	// data Date类型的时间
	 	public static String dateToString(Date data, String formatType) {
	 		
	 	return new SimpleDateFormat(formatType).format(data);
	 	
	 	}
}