package spider;

import java.io.IOException;
import java.util.ArrayList;

/*
 * ����Ԫ���ԣ�ĳ��ģ��Ĳ�����֤
 */
public class UnitTest {
	public static void main(String[] args){
		//֪���ķ���ҳ��
		String url="https://www.zhihu.com/explore";
	    // �������Ӳ���ȡҳ������  
	    String content = Spider.ReadPage(url); 
	    // ��ӡ���  
	    System.out.println(content);
	    //��Ԫ����:zhihu.getRealUrl(String test)����
	    String test="https://www.zhihu.com/question/46727998/answer/108099200";
	    Zhihu zhihu=new Zhihu(test);
	    zhihu.getRealUrl(test);
	    System.out.println("ȫ���ش�ҳ���ַ="+zhihu.zhihuUrl);  
	    
	    //���ԣ�Filewriter.writerIntoFile(String filename)
	    //Filewriter.writeIntoFile("output.txt");
	    //���ԣ�Filewriter.writerIntoFile(String filename,ArrayList<Zhihu> myZhihu)
	    ArrayList<Zhihu> myZhihu=Spider.GetZhihu(content);
	    Filewriter.writeIntoFile("output.tXt",myZhihu);
	    
	}

}
