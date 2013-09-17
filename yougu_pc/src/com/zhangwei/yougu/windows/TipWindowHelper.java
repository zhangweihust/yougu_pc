package com.zhangwei.yougu.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.zhangwei.yougu.pojo.Response.RespFindActionListByTimeVip_item;

/**
 * @author Tom
 */
public class TipWindowHelper {

	private ArrayList<TipWindow> pops;
	public static int cur_add_size_y = 0;
	
	private static TipWindowHelper ins = null;
	
	public static TipWindowHelper getInstance(){
		if(ins==null){
			ins = new TipWindowHelper();
		}
		
		return ins;
	}
	
	public void show(String people_zyl, ArrayList<RespFindActionListByTimeVip_item> newPeopleActions) {
		// TODO Auto-generated method stub
		if(newPeopleActions!=null && newPeopleActions.size()>0){
			String str_title = null;
			String str_action = "打开网页"; 
			StringBuilder str_content = new StringBuilder();
 			long action_time  = 0L;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH:mm");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar calendar=java.util.Calendar.getInstance();
			calendar.roll(java.util.Calendar.DAY_OF_YEAR, 0);
			long today_long = calendar.getTime().getTime();
			
			ArrayList<String> urls = new ArrayList<String>();
			
			for(RespFindActionListByTimeVip_item  item: newPeopleActions){
				str_title = item.nickname;
				action_time = item.time;
				String[] text = item.text.split(",");
				str_content.append(sdf2.format(new Date(action_time)));
				str_content.append(" ");
				str_content.append(text[0]);
				str_content.append(" ");
				str_content.append(text[1]);
				str_content.append(" ");
				str_content.append(text[4]);
				str_content.append(" ");
				double money = Double.valueOf(text[5]) / 10000;
				str_content.append(money + "万元");
				str_content.append("\n");
				
				if(action_time>today_long){
					String url = "http://stockhtm.finance.qq.com/sstock/ggcx/" + text[2] + ".shtml";
					boolean found = false;
					if(urls.size()>0){
						for(String i : urls){
							if(url.equals(i)){
								found = true;
								break;
							}
						}
						
					}
					
					if(!found){
						urls.add(url);
					}

				}


			
				
			}
			
			show(str_title, str_action, people_zyl, str_content.toString(), urls, sdf.format(new Date(action_time)));
		}
	}
	
	public void show(String str_title, String str_action, String str_content_title, String str_content, final ArrayList<String> urls, String date){

		// 新建300x250的消息提示框
		// Tom, 设置整个提示框大小
		TipWindow tw = new TipWindow(300, 250);
		pops.add(tw);
		//handle(urls);
		tw.init(tw, str_title, str_action, str_content_title, str_content, urls, date);
		
		tw.run();
		if(cur_add_size_y<500){
			cur_add_size_y+=5;
		}

	}
	
	public void clear(){
		if(pops.size()>0){
			for(TipWindow  tw: pops){
				tw.close();
			}
		}
		cur_add_size_y = 0;
	}

	private TipWindowHelper() {
		pops = new ArrayList<TipWindow>();
	}
	





/*	public static void main(String args[]) {
		new TipWindowHelper();
	}*/

}
