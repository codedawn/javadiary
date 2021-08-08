如何在jupyter notebook上面上传文件夹

Anneaisun1995 2019-10-30 10:21:31  9339  收藏 21
分类专栏： 数据分析 python
版权

数据分析
同时被 2 个专栏收录
6 篇文章0 订阅
订阅专栏

python
15 篇文章0 订阅
订阅专栏
用jupyter做数据分析时，要读取很多csv文件，需要先将这些文件上传到jupyter工作目录，upload只能上传单个文件，怎样一次性上传所有的文件，节约时间呢？可以将这些要上传的文件打包，压缩上传之后，再解压

import zipfile
import os
files = zipfile.ZipFile('需要解压的文件路径', 'r')
files.extractall('解压到的目录')
files.close() 
1
2
3
4
5
如果需要上传的数据本身就是在文件夹里，此方法同样适用。
————————————————
版权声明：本文为CSDN博主「Anneaisun1995」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/Anneaisun1995/article/details/102813720