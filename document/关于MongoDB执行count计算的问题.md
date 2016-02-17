# 关于MongoDB执行count计算的问题

### 无查询条件的count
当执行无查询条件的count时，响应速度是非常快的。
&gt; db.test.count()

### 带查询条件的count
当执行带查询条件的count时，随着数据量的增加，即使查询条件能够命中索引，响应速度会逐渐变慢。
如果集合中文档数量达到千万级、亿级，count计算的响应速度可能无法满足需求。
&gt; db.test.count({age:{$gt:20}})
或者
&gt; db.test.find({age:{$gt:20}}).count()

### 在对集合中文档总数精度要求不高的情况下，可以进行如下优化查询

1. 如果文档数量非常大，而展示数据精度的要求并不到，可以采用类似微信图文阅读数的显示方法，在阅读人数超过100000人，显示100000+。
    MongoDB的count计算可以修改如下
    &gt; db.test.find({age:{$gt:20}}).limit(100000).count()
    
    当集合中符合条件的文档数量小于100000时，会返回真是的文档数量。
    当集合中符合条件的文档数量大于100000时，会返回100000，可以展示为100000+。

2. 使用MongoDB的Java Driver，查询方法如下：
```Java
    long count = collection.find(new BasicDBObject("age", new BasicDBObject("$gt", 20))).limit(1000000).size();
```



