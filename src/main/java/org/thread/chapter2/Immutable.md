> Immutable Class

Immutable ：不需要使用sync就能确保线程安全<br>
- String <br>
- BigInteger <br>
- BigDecimal <br>
- Pattern <br>
- 基本类型的包装类，Integer, Short <br>
- awt.Color <br>

> Mutable :
- awt.Point <br>

Immutable 模式中，实例的状态不会发生改变，所以多个线程可以共享一个实例。
