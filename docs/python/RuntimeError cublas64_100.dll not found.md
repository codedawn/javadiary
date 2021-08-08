

通过设置环境变量解决



RuntimeError: cublas64_100.dll not found

Ryan Huang 2020-08-09 11:35:15  792  收藏 1
分类专栏： paddlepaddle历险记
版权

paddlepaddle历险记
专栏收录该内容
25 篇文章1 订阅
订阅专栏
在安装Paddlepaddle、tensorflow或者Pytorch的GPU版本时都可能遇到这个问题

RuntimeError: cublas64_100.dll not found
1
1.第一种可能是没有安装CUDA
可以参考这篇文章手把手教你安装CUDA部分，要安装与AI框架相对应的版本的CUDA：
https://blog.csdn.net/HaoZiHuang/article/details/107868005
比如，当前时间下，Paddlepaddle不支持 CUDA 9.1/9.2/10.1

2.第二种安装了CUDA没有设置环境变量
推荐先重启一下电脑，windows设置环境变量大多靠重启
还不行的话，检查一下环境变量是否正确


3.第三种移动一下dll库的位置

在C:\Users\Administrator\Anaconda3\envs\paddle\Library\bin目录下可以找到缺失的cublas64_100.dll文件 将其复制到C:\Windows\System32目录下即可 重新输入paddle.fluid.install_check.run_check()显示Your Paddle is installed successfully! Let's start deep Learning with Paddle now，安装成功

参考自：
https://ai.baidu.com/forum/topic/show/954113

4.玄学

https://blog.csdn.net/Audrey_Hepburn_1/article/details/101642694
————————————————
版权声明：本文为CSDN博主「Ryan Huang」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/HaoZiHuang/article/details/107891428