mmseg4j core 使用 Chih-Hao Tsai 的 MMSeg 算法(http://technology.chtsai.org/mmseg/ )实现的中文分词器。

MMSeg 算法有两种分词方法：Simple和Complex，都是基于正向最大匹配。Complex 加了四个规则过虑。官方说：词语的正确识别率达到了 98.41%。mmseg4j 已经实现了这两种分词算法。

```xml
<dependency>
    <groupId>com.chenlb.mmseg4j</groupId>
    <artifactId>mmseg4j-core</artifactId>
    <version>1.10.0</version>
</dependency>
```