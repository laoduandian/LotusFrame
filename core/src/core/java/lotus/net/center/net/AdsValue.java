package lotus.net.center.net;

public class AdsValue {
    static public final int admob = 1 << 0;//1
    static public final int uad = 1 << 1;//2
    static public final int gdt = 1 << 2;//4
    static public final int baidu = 1 << 3;//8
    static public final int csj = 1<<4;//16

    static public final int admobUad = admob|uad;//3
    static public final int gdtBaidu = gdt|baidu;//12
    static public final int gdtCsj = gdt|csj;//20
    static public final int csjBaidu = csj|baidu;//24
    static public final int gdtCsjBaidu = gdt|csj|baidu;//28
}
