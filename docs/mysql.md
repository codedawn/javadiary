### 1.mysql 改远程连接密码，%表示可以远程

`alter user 'root'@'%' IDENTIFIED BY '123456'`

### 2.遇到使用usessl=true连接失败问题，需要改为false

