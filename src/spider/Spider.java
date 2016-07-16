package spider;


/*
 * Spider类来存放一些爬虫常用的函数。
 * 这个爬虫是用来抓去知乎发现页面上的所有的问题标题，回答的链接，回答（包括编辑推荐、今日最热、本月最热）
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
        // 定义一个字符串用来存储网页内容  
        String result = "";  
        // 定义一个缓冲字符输入流  
        BufferedReader in = null;  
  
        try {  
            // 将string转成url对象  
            URL realUrl = new URL(url);  
            // 初始化一个链接到那个url的连接  
            URLConnection connection = realUrl.openConnection();  
            // 开始实际的连接  
            connection.connect();  
            // 初始化 BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream(), "UTF-8"));//查看了知乎使用UTF-8的编码格式  
            // 用来临时存储抓取到的每一行的数据  
            String line;  
            while ((line = in.readLine()) != null) {  
                // 遍历抓取到的每一行并将其存储到result里面  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally来关闭输入流  
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
  
    //测试
//    static ArrayList<String> getQuestion(String content){
//    	ArrayList<String> res=new ArrayList<String>();
//    	Pattern p=Pattern.compile("question_link.+?href=\"(.+?)\"");//修改成匹配链接地址
//    	Matcher m=p.matcher(content);
//    	
//    	while(m.find()){//这里讲解一下java正则高阶用法，分组和捕获
//    		res.add(m.group(1)+'\n');//group(1)表示被第一个括号括起来的组——引自《java编程思想》
//    	}
//    	return res;
//    }
    
    /*
     * 抓取知乎发现页面上所有问题的链接地址,
     * 并返回该问题链接下的Zhihu类信息，
     * 
     */
    static ArrayList<Zhihu> GetZhihu(String content) {  
       ArrayList<Zhihu> res=new ArrayList<Zhihu>();
       //埋好陷阱，等着掉下去
       //形如<h2><a class="question_link" target="_blank" href="/question/46727998/answer/108099200">《守望先锋》有可能出手游吗？</a></h2>
       //抓取 这部分，/question/46727998/answer/108099200      
       Pattern urlPattern=Pattern.compile("question_link.+?href=\"(.+?)\">");
       Matcher questionMatcher=urlPattern.matcher(content);
       //是否掉到陷阱里面
       Boolean isFind = questionMatcher.find();  
       while(isFind){
    	   //存储刚才定义的知乎对象，数组里面都是Zhihu对象
    	   Zhihu zhihuTmp=new Zhihu("https://www.zhihu.com"+questionMatcher.group(1));//参数是问题的地址的完整链接
    	   res.add(zhihuTmp);
    	   //继续查找下一个匹配对象
    	   isFind=questionMatcher.find();
       }
       return res;
    }  
  
}  