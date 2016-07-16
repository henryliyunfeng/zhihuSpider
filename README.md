# zhihuSpider
## 简介
用javaAPI自带的net库，URL和正则表达式完成简单地抓取知乎explore页面上热门问题和回答的爬虫程序。
## 注意
笔者所写程序登录的是["知乎-发现"页面][1]，所以也就是说不涉及模拟登录的问题
## 需求
默认抓取的字段：

 - 问题的名字
 - 问题的URL
 - 评论者的名字
 - 所有评论
 - 评论的总数

可以明显地看出是当问题数变多，由一个地址访问页面里所有的下一个地址，抓取速度变慢。不过，从中也学习了爬虫基本原理，接下来会对其进行多线程改进，提高效率。
程序方面具体可以访问[博客][2]
## 效果
![enter description here][3]


  [1]: https://www.zhihu.com/explore
  [2]: https://henryliyunfeng.github.io/
  [3]: http://7xvohu.com1.z0.glb.clouddn.com/img/zhihu%E7%AC%AC%E5%9B%9B%E5%BC%B93.jpg