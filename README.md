mmseg4j core 使用 Chih-Hao Tsai 的 MMSeg 算法(http://technology.chtsai.org/mmseg/ )实现的中文分词器。

MMSeg 算法有两种分词方法：Simple和Complex，都是基于正向最大匹配。Complex 加了四个规则过虑。官方说：词语的正确识别率达到了 98.41%。mmseg4j 已经实现了这两种分词算法。

```xml
<dependency>
    <groupId>com.chenlb.mmseg4j</groupId>
    <artifactId>mmseg4j-core</artifactId>
    <version>1.10.0</version>
</dependency>
```

## example

```
git clone https://github.com/chenlb/mmseg4j-core mmseg4j-core
cd mmseg4j-core
mvn compile

#运行
#Complex 分词模式
java -cp .:target/classes com.chenlb.mmseg4j.example.Complex

#Simple 分词模式
java -cp .:target/classes com.chenlb.mmseg4j.example.Simple

#MaxWord 分词模式
java -cp .:target/classes com.chenlb.mmseg4j.example.MaxWord

#或编译打包
mvn package

java -cp .:target/mmseg4j-core-1.10.1-SNAPSHOT.jar com.chenlb.mmseg4j.example.Complex
```

## 其它

* [早期的介绍](https://github.com/chenlb/mmseg4j-from-googlecode)