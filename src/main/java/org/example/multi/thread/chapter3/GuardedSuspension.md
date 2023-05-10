# Guarded Suspension模式

Guarded Suspension模式有点像, `多线程版本的if` 。

Guarded Suspension模式存在三个特点：
- 存在循环
- 存在检查条件
- 因为某种原因等待
---
有几种实现方式：
- guarded wait
```java
// 等待端
while(!ready) {
    wait()
}

// 唤醒端
ready = true;
notifyAll()
```
- busy wait："忙等"。

有时候线程并没有使用wait()等待，
而是执行`Thread.yield()` ，也就是尽可能将优先级让给其他线程，由于等待端的线程
也是在持续运行，因为会浪费虚拟机的时间。另外，`Thread.yield()`并不会释放锁，所以这段
代码不可以写在sync中，
(因为写在sync方法中，线程会拿着锁的同时持续等待，会造成死锁)
同时， ready必须声明为`volatile`。
```java
// 等待端
while(!ready) {
    Thread.yield()
}

// 唤醒端，ready必须声明为`volatile`
ready = true;
```
- spin lock："通过旋转来锁定"
- polling : 轮询。反复检查某个事件是否发生。

> 编写代码需要注意的问题

- `检查守护条件`和`修改状态`等一连串的处理都必须是原子操作。
- 
