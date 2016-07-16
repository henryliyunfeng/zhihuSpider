package spider;

/*
 * 设计一个Zhihu封装类，来存储所有抓取到的对象
 * 5个字段：问题标题、问题的描述、问题对应的链接、评论者的名字、所有的评论信息
 *getRealUrl(String url)方法
 */
import java.util.ArrayList;
import java.util.regex.Pattern;  
import java.util.regex.Matcher;  

public class Zhihu {  
    public String question;// 问题的标题 
    public String questionDescription;//对问题的描述
    public String zhihuUrl;// 问题对应的链接  
    public ArrayList<String> answersName;//所有评论者的名字
    public ArrayList<String> answers;// 存储所有回答的数组  
  
    // 带参数<String url>的构造器
    public Zhihu(String url) {  
        question = "";  
        questionDescription="";
        zhihuUrl = "";  
        answersName=new ArrayList<String>();
        answers = new ArrayList<String>();  
        
        if(getRealUrl(url)){
        	System.out.println("该问题全部回答的页面地址="+zhihuUrl);
        	//根据该问题所在的页面地址，爬虫子页面所有内容
        	String allContent=Spider.ReadPage(zhihuUrl);
        	//抓取问题
        	Pattern questionPattern=Pattern.compile("zh-question-title.+?><h2.+?>.+?>(.+?)<");
        	Matcher questionMatcher=questionPattern.matcher(allContent);
        	if(questionMatcher.find())
        		question=questionMatcher.group(1);
        	//抓去问题描述
        	Pattern descriptionPattern=Pattern.compile("zh-question-detail.+?><div.+?>(.+?)</div>");
        	Matcher descriptionMatcher=descriptionPattern.matcher(allContent);
        	if(descriptionMatcher.find())
        		questionDescription=descriptionMatcher.group(1);
        	//抓取评论者的姓名
        	Pattern namePattern=Pattern.compile("data-author-name=\"(.+?)\"");
        	Matcher nameMatcher=namePattern.matcher(allContent);
        	Boolean nameFind=nameMatcher.find();
        	//不停地抓取问题页面上所有评论者的姓名
        	while(nameFind){
        		answersName.add(nameMatcher.group(1));
        		nameFind=nameMatcher.find();
        	}
        	//抓取所有回答
        	Pattern answersPattern=Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
        	Matcher answerMatcher=answersPattern.matcher(allContent);
        	Boolean answerFind=answerMatcher.find();
        	while(answerFind){
        		answers.add(answerMatcher.group(1));
        		answerFind=answerMatcher.find();
        	}
        }
    } 
    /*
     * 把链接：https://www.zhihu.com/question/46727998/answer/108099200
     * 转变成：https://www.zhihu.com/question/46727998
     * 后者才是所有回答的网页真实地址
     */
    public boolean getRealUrl(String url){
    	Pattern linkPattern=Pattern.compile("question/(.+?)/");
    	Matcher linkMatcher=linkPattern.matcher(url);
    	if(linkMatcher.find()){
    		zhihuUrl="https://www.zhihu.com/question/"+linkMatcher.group(1);
    		return true;
    	}
    	return false;    		
    }
  
    @Override  
    public String toString() {  
        return "问题标题：" + question + "\n问题的描述："+questionDescription+"\n链接：" + zhihuUrl +"\n评论者："+answersName+ "\n回答：" + answers.size() + "\n";  
    } 
    
    //格式化内容，用于Filewiter.java写入本地时的排版
    public String realToString(){
    	String content= "问题标题："+question+"\r\n";
    	content+="问题的描述："+questionDescription+"\r\n";
    	content+="链接："+zhihuUrl+"\r\n";
    	content+="评论者："+answersName+"\r\n";
    	content+="回答：";
    	for(int i=0;i<answers.size();i++){
    		int num=i+1;
    		content+="No."+num+"="+answers.get(i)+"\r\n";
    	}
    	//去除替换部分不需要的html标签
        content = content.replaceAll("<br>", "\r\n");  
        content = content.replaceAll("<.*?>", ""); 
    	return content;
    }
}  
