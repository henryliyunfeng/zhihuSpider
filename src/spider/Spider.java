package spider;


/*
 * Spider�������һЩ���泣�õĺ�����
 * �������������ץȥ֪������ҳ���ϵ����е�������⣬�ش�����ӣ��ش𣨰����༭�Ƽ����������ȡ��������ȣ�
 */
import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class Spider {  
    static String ReadPage(String url) {  
        // ����һ���ַ��������洢��ҳ����  
        String result = "";  
        // ����һ�������ַ�������  
        BufferedReader in = null;  
  
        try {  
            // ��stringת��url����  
            URL realUrl = new URL(url);  
            // ��ʼ��һ�����ӵ��Ǹ�url������  
            URLConnection connection = realUrl.openConnection();  
            // ��ʼʵ�ʵ�����  
            connection.connect();  
            // ��ʼ�� BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream(), "UTF-8"));//�鿴��֪��ʹ��UTF-8�ı����ʽ  
            // ������ʱ�洢ץȡ����ÿһ�е�����  
            String line;  
            while ((line = in.readLine()) != null) {  
                // ����ץȡ����ÿһ�в�����洢��result����  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("����GET��������쳣��" + e);  
            e.printStackTrace();  
        }  
        // ʹ��finally���ر�������  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
        return result;  
  
    }  
  
    //����
//    static ArrayList<String> getQuestion(String content){
//    	ArrayList<String> res=new ArrayList<String>();
//    	Pattern p=Pattern.compile("question_link.+?href=\"(.+?)\"");//�޸ĳ�ƥ�����ӵ�ַ
//    	Matcher m=p.matcher(content);
//    	
//    	while(m.find()){//���ｲ��һ��java����߽��÷�������Ͳ���
//    		res.add(m.group(1)+'\n');//group(1)��ʾ����һ���������������顪�����ԡ�java���˼�롷
//    	}
//    	return res;
//    }
    
    /*
     * ץȡ֪������ҳ����������������ӵ�ַ,
     * �����ظ����������µ�Zhihu����Ϣ��
     * 
     */
    static ArrayList<Zhihu> GetZhihu(String content) {  
       ArrayList<Zhihu> res=new ArrayList<Zhihu>();
       //������壬���ŵ���ȥ
       //����<h2><a class="question_link" target="_blank" href="/question/46727998/answer/108099200">�������ȷ桷�п��ܳ�������</a></h2>
       //ץȡ �ⲿ�֣�/question/46727998/answer/108099200      
       Pattern urlPattern=Pattern.compile("question_link.+?href=\"(.+?)\">");
       Matcher questionMatcher=urlPattern.matcher(content);
       //�Ƿ������������
       Boolean isFind = questionMatcher.find();  
       while(isFind){
    	   //�洢�ղŶ����֪�������������涼��Zhihu����
    	   Zhihu zhihuTmp=new Zhihu("https://www.zhihu.com"+questionMatcher.group(1));//����������ĵ�ַ����������
    	   res.add(zhihuTmp);
    	   //����������һ��ƥ�����
    	   isFind=questionMatcher.find();
       }
       return res;
    }  
  
}  