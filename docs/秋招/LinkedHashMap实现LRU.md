```java
public class LRUCache<K, V> {
    private Map<K, V> map;
    private final int cacheSize;

    public LRUCache(int initialCapacity) {
        map = new LinkedHashMap<K, V>(initialCapacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
        this.cacheSize = initialCapacity;
    }
}

```

linkedmap继承自hashmap，和hashmap一样可以快速查找，

内部还维护了一个双向链表，维护插入顺序或者Lru顺序

afternodeaccess，如果accessorder为true。会将被访问的节点移动到链表尾部，也就是保证了尾部的节点是最近访问的节点，然后头部节点是最久没有访问的节点

afternodeinsertion

在调用put方法时，会调用removeeldestentry方法，返回true，会移除最久未使用的节点，也就是头部的节点，这样就实现了lru
