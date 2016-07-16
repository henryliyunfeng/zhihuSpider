package spider;

/*
 * ���һ��Zhihu��װ�࣬���洢����ץȡ���Ķ���
 * 5���ֶΣ�������⡢����������������Ӧ�����ӡ������ߵ����֡����е�������Ϣ
 *getRealUrl(String url)����
 */
import java.util.ArrayList;
import java.util.regex.Pattern;  
import java.util.regex.Matcher;  

public class Zhihu {  
    public String question;// ����ı��� 
    public String questionDescription;//�����������
    public String zhihuUrl;// �����Ӧ������  
    public ArrayList<String> answersName;//���������ߵ�����
    public ArrayList<String> answers;// �洢���лش������  
  
    // ������<String url>�Ĺ�����
    public Zhihu(String url) {  
        question = "";  
        questionDescription="";
        zhihuUrl = "";  
        answersName=new ArrayList<String>();
        answers = new ArrayList<String>();  
        
        if(getRealUrl(url)){
        	System.out.println("������ȫ���ش��ҳ���ַ="+zhihuUrl);
        	//���ݸ��������ڵ�ҳ���ַ��������ҳ����������
        	String allContent=Spider.ReadPage(zhihuUrl);
        	//ץȡ����
        	Pattern questionPattern=Pattern.compile("zh-question-title.+?><h2.+?>.+?>(.+?)<");
        	Matcher questionMatcher=questionPattern.matcher(allContent);
        	if(questionMatcher.find())
        		question=questionMatcher.group(1);
        	//ץȥ��������
        	Pattern descriptionPattern=Pattern.compile("zh-question-detail.+?><div.+?>(.+?)</div>");
        	Matcher descriptionMatcher=descriptionPattern.matcher(allContent);
        	if(descriptionMatcher.find())
        		questionDescription=descriptionMatcher.group(1);
        	//ץȡ�����ߵ�����
        	Pattern namePattern=Pattern.compile("data-author-name=\"(.+?)\"");
        	Matcher nameMatcher=namePattern.matcher(allContent);
        	Boolean nameFind=nameMatcher.find();
        	//��ͣ��ץȡ����ҳ�������������ߵ�����
        	while(nameFind){
        		answersName.add(nameMatcher.group(1));
        		nameFind=nameMatcher.find();
        	}
        	//ץȡ���лش�
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
     * �����ӣ�https://www.zhihu.com/question/46727998/answer/108099200
     * ת��ɣ�https://www.zhihu.com/question/46727998
     * ���߲������лش����ҳ��ʵ��ַ
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
        return "������⣺" + question + "\n�����������"+questionDescription+"\n���ӣ�" + zhihuUrl +"\n�����ߣ�"+answersName+ "\n�ش�" + answers.size() + "\n";  
    } 
    
    //��ʽ�����ݣ�����Filewiter.javaд�뱾��ʱ���Ű�
    public String realToString(){
    	String content= "������⣺"+question+"\r\n";
    	content+="�����������"+questionDescription+"\r\n";
    	content+="���ӣ�"+zhihuUrl+"\r\n";
    	content+="�����ߣ�"+answersName+"\r\n";
    	content+="�ش�";
    	for(int i=0;i<answers.size();i++){
    		int num=i+1;
    		content+="No."+num+"="+answers.get(i)+"\r\n";
    	}
    	//ȥ���滻���ֲ���Ҫ��html��ǩ
        content = content.replaceAll("<br>", "\r\n");  
        content = content.replaceAll("<.*?>", ""); 
    	return content;
    }
}  
