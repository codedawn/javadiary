写操作会加锁

1.读写分离，读操作在原数组上进行

2.写操作复制一个数组，然后在该数组进行操作，再把原数组指向新数组







内存占用：使内存多了一倍，使用了额外空间

数据不一致：读的时候可能在写，但是不在同一个数组上，可能数据不一致



适合在读多写少的场景使用，对数据实时性不太高的，还有内存不敏感