# Work Thread 

工作没来就一直等，工作来了就工作，这种模式又叫线程池模式。

# 类图
![img.png](../../../../resources/WorkThread.png)

# 时序图
![img.png](../../../../resources/WorkThreadTimeSequence.png)

Work Thread的调用方法和执行方法是分开的，方法的调用被成为invoke，方法放入执行被称为execute。这样做的好处是

- 提高响应速度
- 控制执行的顺序
- 可以取消或反复执行
- 分布式架构常用的架构
