## 笔记

JDK1.8 中 GC默认走的是：UseParallelGC（并行GC）

- 初始化时整个堆的大小和young区是一样的，因为一开始老年代没有占用。
- youngGC young区的回收率会大于整个堆的回收，因为部分数据会提升到old区，有一部分可能进行了复制
- 堆内存变大最终会触发Full GC
- [Times: user=0.18（用户线程使用的时间） sys=0.00（系统线程使用的时间）, real=0.02 secs（真正暂停的时间 20 ms）] 
- 默认的堆内存是物理内存的1/4
- 随着配置堆内存的增加，可以让GC的次数减少，降低OOM的概率



串行 GC

- [GC (Allocation Failure) ] : GC产生原因，分配失败
- [DefNew: 139776K->17472K(157248K), 0.0253666 secs]：young占用大小
- [Tenured: 87362K->87150K(87424K), 0.0102716 secs] 126595K->104329K(126720K), [Metaspace: 2689K->2689K(1056768K)], 0.0103485 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] ：full GC old区发生回收
- 回收耗费时间较长



并行 GC

- [PSYoungGen: 21640K->0K(116736K)]：young区
-  [ParOldGen: 312925K->295488K(349696K)] ：old区
- [Full GC (Ergonomics)]
- Young 区回收的干掉的数据很多，Old区相对来说就没那么多了
- Full GC实际上会 发生年轻代GC 和 老年代GC



CMS

 阶段1: Initial Mark（初始化标记）[GC (CMS Initial Mark) [1 CMS-initial-mark: 197236K(349568K)]

 阶段2: Concurrent Mark（并发标记）

 阶段3: Concurrent Preclean（并发预处理）

 阶段4: Final Remark（最终标记）[GC (CMS Final Remark) [YG occupancy: 26818 K (157248 K)]

 阶段5: Concurrent Sweep（并发清除）

 阶段6: Concurrent Reset（并发重置）



G1 

Evacuation Pause: young(纯年轻代模式转移暂停) Concurrent Marking(并发标记)
 阶段 1: Initial Mark(初始标记)
 阶段 2: Root Region Scan(Root区扫描)

阶段 3: Concurrent Mark(并发标记)
 阶段 4: Remark(再次标记)
 阶段 5: Cleanup(清理)
 Evacuation Pause (mixed)(转移暂停: 混合模式) Full GC (Allocation Failure)



内存是4g时，效果都差不多，内存不够用，相对来说效果和效率都不高



## 作业

本机配置：

处理器：2.6 GHz 六核Intel Core i7

内存：16 GB 2667 MHz DDR4

java version： "1.8.0_131"

### 一、运行程序GCLogAnalysis.java，分析结果

程序：GCLogAnalysis.java

```java
Java HotSpot(TM) 64-Bit Server VM (25.131-b11) for bsd-amd64 JRE (1.8.0_131-b11), built on Mar 15 2017 01:32:22 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 16777216k(1719808k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=4294967296 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
2020-10-27T22:18:43.484-0800: 0.101: [GC (Allocation Failure) [PSYoungGen: 65536K->10751K(76288K)] 65536K->23264K(251392K), 0.0092068 secs] [Times: user=0.02 sys=0.06, real=0.01 secs] 
2020-10-27T22:18:43.505-0800: 0.121: [GC (Allocation Failure) [PSYoungGen: 76287K->10748K(141824K)] 88800K->45658K(316928K), 0.0122699 secs] [Times: user=0.03 sys=0.07, real=0.01 secs] 
2020-10-27T22:18:43.553-0800: 0.170: [GC (Allocation Failure) [PSYoungGen: 141802K->10750K(141824K)] 176712K->91602K(316928K), 0.0196057 secs] [Times: user=0.03 sys=0.13, real=0.02 secs] 
2020-10-27T22:18:43.589-0800: 0.206: [GC (Allocation Failure) [PSYoungGen: 141451K->10748K(272896K)] 222303K->131681K(448000K), 0.0164188 secs] [Times: user=0.02 sys=0.10, real=0.02 secs] 
2020-10-27T22:18:43.605-0800: 0.222: [Full GC (Ergonomics) [PSYoungGen: 10748K->0K(272896K)] [ParOldGen: 120932K->118900K(250368K)] 131681K->118900K(523264K), [Metaspace: 2689K->2689K(1056768K)], 0.0148921 secs] [Times: user=0.12 sys=0.00, real=0.02 secs] 
2020-10-27T22:18:43.697-0800: 0.314: [GC (Allocation Failure) [PSYoungGen: 262144K->10741K(272896K)] 381044K->207230K(523264K), 0.0334672 secs] [Times: user=0.04 sys=0.21, real=0.04 secs] 
2020-10-27T22:18:43.731-0800: 0.347: [Full GC (Ergonomics) [PSYoungGen: 10741K->0K(272896K)] [ParOldGen: 196489K->183433K(360960K)] 207230K->183433K(633856K), [Metaspace: 2689K->2689K(1056768K)], 0.0207113 secs] [Times: user=0.17 sys=0.00, real=0.02 secs] 
2020-10-27T22:18:43.788-0800: 0.405: [GC (Allocation Failure) [PSYoungGen: 262144K->88351K(555520K)] 445577K->271785K(916480K), 0.0357272 secs] [Times: user=0.04 sys=0.24, real=0.04 secs] 
2020-10-27T22:18:43.946-0800: 0.563: [GC (Allocation Failure) [PSYoungGen: 550175K->108022K(569856K)] 733609K->379843K(930816K), 0.0735398 secs] [Times: user=0.09 sys=0.47, real=0.07 secs] 
2020-10-27T22:18:44.020-0800: 0.636: [Full GC (Ergonomics) [PSYoungGen: 108022K->0K(569856K)] [ParOldGen: 271820K->280483K(487424K)] 379843K->280483K(1057280K), [Metaspace: 2689K->2689K(1056768K)], 0.0347641 secs] [Times: user=0.27 sys=0.02, real=0.03 secs] 
2020-10-27T22:18:44.123-0800: 0.740: [GC (Allocation Failure) [PSYoungGen: 461824K->134383K(971264K)] 742307K->414867K(1458688K), 0.0534544 secs] [Times: user=0.06 sys=0.34, real=0.05 secs] 
2020-10-27T22:18:44.325-0800: 0.942: [GC (Allocation Failure) [PSYoungGen: 934639K->185335K(985600K)] 1215123K->542514K(1473024K), 0.0944571 secs] [Times: user=0.10 sys=0.62, real=0.09 secs] 
Heap
 PSYoungGen      total 985600K, used 496804K [0x000000076ab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 800256K, 38% used [0x000000076ab00000,0x000000077db2b3c0,0x000000079b880000)
  from space 185344K, 99% used [0x000000079b880000,0x00000007a6d7dcb0,0x00000007a6d80000)
  to   space 246272K, 0% used [0x00000007b0f80000,0x00000007b0f80000,0x00000007c0000000)
 ParOldGen       total 487424K, used 357179K [0x00000006c0000000, 0x00000006ddc00000, 0x000000076ab00000)
  object space 487424K, 73% used [0x00000006c0000000,0x00000006d5ccec18,0x00000006ddc00000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K


```



```
java -XX:+PrintGCDetails -Xloggc:gc.demo.log -XX:+PrintGCDateStamps GCLogAnalysis 
```

- 执行该命令，jdk1.8 默认使用：UseParallelGC 垃圾回收器

  ```
  -XX:+UseParallelGC 
  ```

- 原先以为年轻代+老年代+元空间所占用的内存空间进行相加，发现小于本机内存的1/4，后面才发现顶部的已经有说明。初始化堆内存会分配给256M，最大堆内存会设置为4g（本机内存的1/4）,也验证成功。

  ```
  Heap
   PSYoungGen      total 1081856K, used 227544K [0x000000076ab00000, 0x00000007c0000000, 0x00000007c0000000)
    eden space 778240K, 3% used [0x000000076ab00000,0x000000076c94dcb0,0x000000079a300000)
    from space 303616K, 64% used [0x00000007ad780000,0x00000007b9768640,0x00000007c0000000)
    to   space 309760K, 0% used [0x000000079a300000,0x000000079a300000,0x00000007ad180000)
   ParOldGen       total 600064K, used 450454K [0x00000006c0000000, 0x00000006e4a00000, 0x000000076ab00000)
    object space 600064K, 75% used [0x00000006c0000000,0x00000006db7e5840,0x00000006e4a00000)
   Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
    class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
  ```

  ```
  -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=4294967296
  ```

- 手动调大设置Xms和Xmx值为 8g

  ```
  java -Xms8g -Xmx8g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis 
  ```

  ```
  Java HotSpot(TM) 64-Bit Server VM (25.131-b11) for bsd-amd64 JRE (1.8.0_131-b11), built on Mar 15 2017 01:32:22 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
  Memory: 4k page, physical 16777216k(2013508k free)
  
  /proc/meminfo:
  
  CommandLine flags: -XX:InitialHeapSize=8589934592 -XX:MaxHeapSize=8589934592 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
  Heap
   PSYoungGen      total 2446848K, used 2097664K [0x0000000715580000, 0x00000007c0000000, 0x00000007c0000000)
    eden space 2097664K, 100% used [0x0000000715580000,0x0000000795600000,0x0000000795600000)
    from space 349184K, 0% used [0x00000007aab00000,0x00000007aab00000,0x00000007c0000000)
    to   space 349184K, 0% used [0x0000000795600000,0x0000000795600000,0x00000007aab00000)
   ParOldGen       total 5592576K, used 0K [0x00000005c0000000, 0x0000000715580000, 0x0000000715580000)
    object space 5592576K, 0% used [0x00000005c0000000,0x00000005c0000000,0x0000000715580000)
   Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
    class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
  
  ```

- 手动调大设置Xms和Xmx值为 128g，会触发OOM

  ```
  java -Xms128m -Xmx128m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis 
  ```

  ```
  Java HotSpot(TM) 64-Bit Server VM (25.131-b11) for bsd-amd64 JRE (1.8.0_131-b11), built on Mar 15 2017 01:32:22 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
  Memory: 4k page, physical 16777216k(2009520k free)
  
  /proc/meminfo:
  
  CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
  2020-10-27T22:26:50.306-0800: 0.087: [GC (Allocation Failure) [PSYoungGen: 33235K->5114K(38400K)] 33235K->12779K(125952K), 0.0052343 secs] [Times: user=0.01 sys=0.03, real=0.01 secs] 
  2020-10-27T22:26:50.319-0800: 0.100: [GC (Allocation Failure) [PSYoungGen: 38071K->5115K(38400K)] 45735K->27364K(125952K), 0.0072910 secs] [Times: user=0.01 sys=0.05, real=0.01 secs] 
  2020-10-27T22:26:50.332-0800: 0.112: [GC (Allocation Failure) [PSYoungGen: 38108K->5115K(38400K)] 60358K->38989K(125952K), 0.0061167 secs] [Times: user=0.02 sys=0.03, real=0.00 secs] 
  2020-10-27T22:26:50.342-0800: 0.123: [GC (Allocation Failure) [PSYoungGen: 38308K->5102K(38400K)] 72182K->50294K(125952K), 0.0057568 secs] [Times: user=0.02 sys=0.03, real=0.00 secs] 
  2020-10-27T22:26:50.353-0800: 0.134: [GC (Allocation Failure) [PSYoungGen: 38134K->5110K(38400K)] 83326K->64031K(125952K), 0.0064776 secs] [Times: user=0.02 sys=0.04, real=0.01 secs] 
  2020-10-27T22:26:50.364-0800: 0.145: [GC (Allocation Failure) [PSYoungGen: 37672K->5113K(19968K)] 96594K->73063K(107520K), 0.0047687 secs] [Times: user=0.01 sys=0.02, real=0.00 secs] 
  2020-10-27T22:26:50.372-0800: 0.152: [GC (Allocation Failure) [PSYoungGen: 19763K->9102K(29184K)] 87713K->78431K(116736K), 0.0017887 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.376-0800: 0.157: [GC (Allocation Failure) [PSYoungGen: 23510K->12000K(29184K)] 92838K->82680K(116736K), 0.0019412 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
  2020-10-27T22:26:50.378-0800: 0.159: [Full GC (Ergonomics) [PSYoungGen: 12000K->0K(29184K)] [ParOldGen: 70680K->76704K(87552K)] 82680K->76704K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0107475 secs] [Times: user=0.07 sys=0.01, real=0.01 secs] 
  2020-10-27T22:26:50.392-0800: 0.173: [Full GC (Ergonomics) [PSYoungGen: 14550K->0K(29184K)] [ParOldGen: 76704K->79129K(87552K)] 91255K->79129K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0091695 secs] [Times: user=0.07 sys=0.01, real=0.01 secs] 
  2020-10-27T22:26:50.405-0800: 0.186: [Full GC (Ergonomics) [PSYoungGen: 14817K->0K(29184K)] [ParOldGen: 79129K->83341K(87552K)] 93946K->83341K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0103106 secs] [Times: user=0.07 sys=0.01, real=0.01 secs] 
  2020-10-27T22:26:50.418-0800: 0.199: [Full GC (Ergonomics) [PSYoungGen: 14832K->698K(29184K)] [ParOldGen: 83341K->87441K(87552K)] 98174K->88139K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0089868 secs] [Times: user=0.07 sys=0.01, real=0.01 secs] 
  2020-10-27T22:26:50.430-0800: 0.211: [Full GC (Ergonomics) [PSYoungGen: 14848K->5625K(29184K)] [ParOldGen: 87441K->87388K(87552K)] 102289K->93013K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0076853 secs] [Times: user=0.07 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.439-0800: 0.220: [Full GC (Ergonomics) [PSYoungGen: 14434K->8811K(29184K)] [ParOldGen: 87388K->87310K(87552K)] 101823K->96122K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0090032 secs] [Times: user=0.08 sys=0.00, real=0.01 secs] 
  2020-10-27T22:26:50.450-0800: 0.231: [Full GC (Ergonomics) [PSYoungGen: 14727K->8866K(29184K)] [ParOldGen: 87310K->87392K(87552K)] 102038K->96259K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0077177 secs] [Times: user=0.07 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.459-0800: 0.240: [Full GC (Ergonomics) [PSYoungGen: 14197K->9653K(29184K)] [ParOldGen: 87392K->87470K(87552K)] 101589K->97124K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0108580 secs] [Times: user=0.11 sys=0.00, real=0.02 secs] 
  2020-10-27T22:26:50.471-0800: 0.251: [Full GC (Ergonomics) [PSYoungGen: 14697K->10149K(29184K)] [ParOldGen: 87470K->87470K(87552K)] 102168K->97620K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0017400 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.473-0800: 0.254: [Full GC (Ergonomics) [PSYoungGen: 14434K->11981K(29184K)] [ParOldGen: 87470K->87470K(87552K)] 101905K->99452K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0018156 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.475-0800: 0.256: [Full GC (Ergonomics) [PSYoungGen: 14799K->12604K(29184K)] [ParOldGen: 87470K->87493K(87552K)] 102270K->100098K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0106551 secs] [Times: user=0.10 sys=0.01, real=0.01 secs] 
  2020-10-27T22:26:50.487-0800: 0.267: [Full GC (Ergonomics) [PSYoungGen: 14847K->13134K(29184K)] [ParOldGen: 87493K->87208K(87552K)] 102340K->100343K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0033355 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
  2020-10-27T22:26:50.490-0800: 0.271: [Full GC (Ergonomics) [PSYoungGen: 14721K->13670K(29184K)] [ParOldGen: 87208K->87208K(87552K)] 101930K->100878K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0015997 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.492-0800: 0.273: [Full GC (Ergonomics) [PSYoungGen: 14285K->13382K(29184K)] [ParOldGen: 87208K->87208K(87552K)] 101494K->100590K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0013938 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.494-0800: 0.275: [Full GC (Ergonomics) [PSYoungGen: 14751K->14459K(29184K)] [ParOldGen: 87208K->87143K(87552K)] 101959K->101603K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0048572 secs] [Times: user=0.04 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.499-0800: 0.280: [Full GC (Ergonomics) [PSYoungGen: 14603K->14531K(29184K)] [ParOldGen: 87143K->87143K(87552K)] 101747K->101675K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0015216 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
  2020-10-27T22:26:50.501-0800: 0.281: [Full GC (Ergonomics) [PSYoungGen: 14848K->14675K(29184K)] [ParOldGen: 87143K->87143K(87552K)] 101991K->101819K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0015018 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.502-0800: 0.283: [Full GC (Ergonomics) [PSYoungGen: 14830K->14387K(29184K)] [ParOldGen: 87143K->87143K(87552K)] 101973K->101531K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0014549 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.504-0800: 0.285: [Full GC (Ergonomics) [PSYoungGen: 14755K->14755K(29184K)] [ParOldGen: 87143K->87143K(87552K)] 101899K->101899K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0015486 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.506-0800: 0.286: [Full GC (Ergonomics) [PSYoungGen: 14838K->14755K(29184K)] [ParOldGen: 87548K->87215K(87552K)] 102387K->101971K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0013770 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.507-0800: 0.288: [Full GC (Ergonomics) [PSYoungGen: 14777K->14755K(29184K)] [ParOldGen: 87474K->87143K(87552K)] 102252K->101899K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0018811 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.509-0800: 0.290: [Full GC (Ergonomics) [PSYoungGen: 14847K->14755K(29184K)] [ParOldGen: 87518K->87143K(87552K)] 102366K->101899K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0020183 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
  2020-10-27T22:26:50.511-0800: 0.292: [Full GC (Ergonomics) [PSYoungGen: 14755K->14755K(29184K)] [ParOldGen: 87404K->87143K(87552K)] 102160K->101899K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0019444 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.513-0800: 0.294: [Full GC (Ergonomics) [PSYoungGen: 14755K->14755K(29184K)] [ParOldGen: 87515K->87143K(87552K)] 102271K->101899K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0014112 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.515-0800: 0.296: [Full GC (Ergonomics) [PSYoungGen: 14825K->14755K(29184K)] [ParOldGen: 87530K->87474K(87552K)] 102356K->102230K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0080528 secs] [Times: user=0.07 sys=0.00, real=0.01 secs] 
  2020-10-27T22:26:50.523-0800: 0.304: [Full GC (Ergonomics) [PSYoungGen: 14821K->14821K(29184K)] [ParOldGen: 87474K->87474K(87552K)] 102296K->102296K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0015951 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
  2020-10-27T22:26:50.525-0800: 0.305: [Full GC (Allocation Failure) [PSYoungGen: 14821K->14821K(29184K)] [ParOldGen: 87474K->87455K(87552K)] 102296K->102276K(116736K), [Metaspace: 2689K->2689K(1056768K)], 0.0080853 secs] [Times: user=0.06 sys=0.00, real=0.01 secs] 
  Heap
   PSYoungGen      total 29184K, used 14848K [0x00000007bd580000, 0x00000007c0000000, 0x00000007c0000000)
    eden space 14848K, 100% used [0x00000007bd580000,0x00000007be400000,0x00000007be400000)
    from space 14336K, 0% used [0x00000007bf200000,0x00000007bf200000,0x00000007c0000000)
    to   space 14336K, 0% used [0x00000007be400000,0x00000007be400000,0x00000007bf200000)
   ParOldGen       total 87552K, used 87455K [0x00000007b8000000, 0x00000007bd580000, 0x00000007bd580000)
    object space 87552K, 99% used [0x00000007b8000000,0x00000007bd567e00,0x00000007bd580000)
   Metaspace       used 2719K, capacity 4486K, committed 4864K, reserved 1056768K
    class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
  ```

  | GC状态\Xms/Xmx | 128m | 4g   | 8g   |
  | -------------- | ---- | ---- | ---- |
  | 是否发生GC     | 是   | 是   | 否   |
  | 是否发生FullGC | 是   | 否   | 否   |
  | GC次数         | 最多 | 次之 | 无   |

  - 总结：以并行GC为示例，随着内存的增大，GC的次数会减少，而内存的减少会导致GC次数的增多，也比较容易触发老年代的回收，当堆内存变大最终会触发Full GC，当Eden区和Old区的内存，无法回收，导致新对象无法被分配，就会触发OOM了。

### 二、压测`gateway-server-0.0.1-SNAPSHOT.jar`

```
wrk -c20 -d60s http://localhost:8088/api/hello
```

并行 GC

```
java -jar -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    40.55ms  145.84ms   1.18s    92.62%
    Req/Sec    36.63k    10.73k   59.28k    82.22%
  2997697 requests in 1.00m, 357.90MB read
Requests/sec:  49881.58
Transfer/sec:      5.96MB

java -jar -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    34.61ms  120.41ms 813.05ms   92.35%
    Req/Sec    38.99k     8.68k   48.95k    91.71%
  3184099 requests in 1.00m, 380.15MB read
Requests/sec:  53038.44
Transfer/sec:      6.33MB


java -jar -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    30.10ms  110.96ms 935.83ms   92.80%
    Req/Sec    34.11k    10.95k   47.04k    83.61%
  2935876 requests in 1.00m, 350.52MB read
Requests/sec:  48896.19
Transfer/sec:      5.84MB

```

串行

```
java -jar -XX:+UseSerialGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    29.06ms  128.95ms   1.15s    94.67%
    Req/Sec    35.56k     9.78k   46.01k    82.93%
  2822198 requests in 1.00m, 336.94MB read
  Socket errors: connect 0, read 0, write 0, timeout 20
Requests/sec:  47008.18
Transfer/sec:      5.61MB

java -jar -XX:+UseSerialGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    37.44ms  156.11ms   1.56s    93.66%
    Req/Sec    34.83k     9.59k   47.49k    85.10%
  2918746 requests in 1.00m, 348.47MB read
Requests/sec:  48567.44
Transfer/sec:      5.80MB

java -jar -XX:+UseSerialGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    32.48ms  119.03ms 895.91ms   92.88%
    Req/Sec    33.22k    10.73k   53.77k    82.82%
  2981567 requests in 1.00m, 355.97MB read
Requests/sec:  49622.65
Transfer/sec:      5.92MB
```

CMS

```

java -jar -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.21ms   97.50ms 725.01ms   93.06%
    Req/Sec    32.50k    10.55k   52.59k    84.69%
  2953945 requests in 1.00m, 352.67MB read
Requests/sec:  49149.28
Transfer/sec:      5.87MB

java -jar -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.10ms   97.29ms 769.32ms   93.07%
    Req/Sec    33.96k     9.59k   49.77k    85.28%
  2838491 requests in 1.00m, 338.89MB read
Requests/sec:  47240.80
Transfer/sec:      5.64MB

java -jar -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    39.88ms  134.87ms 951.43ms   91.90%
    Req/Sec    28.67k     9.42k   45.58k    82.99%
  2672810 requests in 1.00m, 319.11MB read
Requests/sec:  44524.35
Transfer/sec:      5.32MB

```

G1

```
java -jar -XX:+UseG1GC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    66.89ms  199.84ms   1.75s    90.46%
    Req/Sec    36.17k    10.41k   53.40k    89.14%
  3132307 requests in 1.00m, 373.97MB read
Requests/sec:  52175.62
Transfer/sec:      6.23MB

java -jar -XX:+UseG1GC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    37.22ms  139.83ms   1.19s    92.92%
    Req/Sec    34.64k    11.39k   52.25k    81.17%
  3111552 requests in 1.00m, 371.49MB read
Requests/sec:  51781.96
Transfer/sec:      6.18MB


java -jar -XX:+UseG1GC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar
wrk -c20 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  2 threads and 20 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    83.09ms  221.43ms   1.90s    89.07%
    Req/Sec    33.34k    11.57k   46.19k    85.05%
  2952162 requests in 1.00m, 352.46MB read
Requests/sec:  49132.42
Transfer/sec:      5.87MB
```

- 总结：在本机上，G1的压测效果会好些，但是实验的结果有点疑问，使用并行GC分配4g内存时，运行效果反而比分配1g内存的请求量少，也有多尝试多压几次，数据始终不是很理想，可能和我本机在运行其他软件有关系。

