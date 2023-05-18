# Balking模式

与Guarded Suspension模式一样，也存在守护条件。
在Balking模式中，如果守护条件不成立，则立即中断处理，
但是在Guarded Suspension中，如果守护条件不成立，会进入该对象的等待队列中。