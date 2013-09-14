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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
/*	private Map<String, String> feaMap = null;
	private Point oldP; // 上一次坐标,拖动窗口时用
	//private TipWindow tw = null; // 提示框
	private ImageIcon img = null;// 图像组件
	private JLabel imgLabel = null; // 背景图片标签
	private JPanel headPan = null;
	private JPanel feaPan = null;
	private JPanel btnPan = null;
	private JLabel title = null;
	private JLabel head = null;
	private JLabel close = null;// 关闭按钮
	private JTextArea feature = null;
	private JScrollPane jfeaPan = null;
	private JLabel releaseLabel = null;
	private JLabel update = null;
	private SimpleDateFormat sdf = null;*/

	{
		/*		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		feaMap = new HashMap<String, String>();
		feaMap.put("release", sdf.format(new Date()));*/
		
	}
	
	private static TipWindowHelper ins = null;
	
	public static TipWindowHelper getInstance(){
		if(ins==null){
			ins = new TipWindowHelper();
		}
		
		return ins;
	}
	
	public void show(ArrayList<RespFindActionListByTimeVip_item> newPeopleActions) {
		// TODO Auto-generated method stub
		if(newPeopleActions!=null && newPeopleActions.size()>0){
			String str_title = null;
			String str_action = "打开网页"; 
			StringBuilder str_content = new StringBuilder();
 			long action_time  = 0L;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH:mm");
			
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
				urls.add("http://stockhtm.finance.qq.com/sstock/ggcx/" + text[2] + ".shtml");
			}
			
			show(str_title, str_action, "str_content_title", str_content.toString(), urls, sdf.format(new Date(action_time)));
		}
	}
	
	public void show(String str_title, String str_action, String str_content_title, String str_content, final ArrayList<String> urls, String date){

		// 新建300x250的消息提示框
		// Tom, 设置整个提示框大小
		TipWindow tw = new TipWindow(300, 250);
		//handle(urls);
		tw.init(tw, str_title, str_action, str_content_title, str_content, urls, date);
		
		tw.run();
	}

	private TipWindowHelper() {

	}
	





/*	public static void main(String args[]) {
		new TipWindowHelper();
	}*/

}
