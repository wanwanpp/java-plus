1.将ClassTransform,HotAgent,ReloadTask三个类进行编译
2.使用jar cvfm hotAgent.jar manifest.mf *   将上述得到的三个class文件按照MANIFEST.MF打成jar包。
3.使用命令java –javaagent:hotAgent.jar Test运行测试类。
4.修改Bean1类，编译此类将class替换以前的class文件。
5.可以看见动态编译的效果。



参考博文：https://my.oschina.net/xianggao/blog/364068


原理是通过代理修改内存中的字节码。