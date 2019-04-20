### 一个简易的数据库登录程序 

## 

- ## 数据库的使用

  自定义一个MyDataBaseHelper 去继承DataHelper，重写update（）和oncreate（）方法，在构造器里初始化context，然后自己写一个数据库，用sql格式.



​	之后在activity中调用该类，为创建Database做好准备。我们使用datebase无外乎就四个功能：存，改，查，删，其实现方式大致都一样，都是使用curry光标来进行这些操作



- ### sp的使用

  sp是用来储存数据，比用类似于java io 的openfile（）方法好用。而登录系统的sp就是用来存账号和密码，所以只需要获取sp类，调用put和get方法来输入和获取

  

- ## 关于litepal

  使sqlite操作变得十分简单的工具，使用litepal只需要把javabean类和sqlite连接起来，然后对database的各种操作只需要对javabean操作就好了（我这次作业没有使用）

  




