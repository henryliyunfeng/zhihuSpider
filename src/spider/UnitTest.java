package spider;

import java.io.IOException;
import java.util.ArrayList;

/*
 * 负责单元测试，某个模块的测试验证
 */
public class UnitTest {
	public static void main(String[] args){
		//知乎的发现页面
		String url="https://www.zhihu.com/explore";
	    // 访问链接并获取页面内容  
	    String content = Spider.ReadPage(url); 
	    // 打印结果  
	    System.out.println(content);
	    //单元测试:zhihu.getRealUrl(String test)方法
	    String test="https://www.zhihu.com/question/46727998/answer/108099200";
	    Zhihu zhihu=new Zhihu(test);
	    zhihu.getRealUrl(test);
	    System.out.println("全部回答页面地址="+zhihu.zhihuUrl);  
	    
	    //测试：Filewriter.writerIntoFile(String filename)
	    //Filewriter.writeIntoFile("output.txt");
	    //测试：Filewriter.writerIntoFile(String filename,ArrayList<Zhihu> myZhihu)
	    ArrayList<Zhihu> myZhihu=Spider.GetZhihu(content);
	    Filewriter.writeIntoFile("output.tXt",myZhihu);
	    
	}

}
