package cloud.spring.my.study.gof23.creationalPatten.prototype;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.util.BeanUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;

import java.io.*;

/**
 * 原型模式
 * <p>
 * 个人理解，就是每次创建一个新的实例，克隆也好，创建bean 也好，主要体现在与 单例模式 的区别
 * 但 clone 方法 是浅拷贝，无法对 引用类型的成员变量实现 "原型模式"
 * 此时，可以采取 重写clone方法 或 通过对象序列化 实现深拷贝，达到完整原型模式的目的
 */
public class MirrorCenter {

//    public static void main(String[] args) throws CloneNotSupportedException {
//        Date date = DateUtils.nowDate();
//        NioCar nioCar = new NioCar();
//        nioCar.setName("nio es8");
//        nioCar.setOfflineDate(date);
//        System.out.println(nioCar);≤
//
//        // 使用原型模式进行克隆，
//        // Object默认的克隆方法是浅克隆，
//        // 深克隆需要重新clone方法
//        NioCar nioCarCopy = nioCar.clone();
//        System.out.println(nioCarCopy);
//
//        System.out.println(nioCar.getOfflineDate() == nioCarCopy.getOfflineDate());
//        date.setTime(DateUtils.nowMillis() + 3600 * 1000);
//
//        System.out.println(nioCar);
//        System.out.println(nioCarCopy);
//
//    }


    public static void main(String[] args) throws CloneNotSupportedException {
        CloneExample ori = new CloneExample("Tommy", new DeepCloneTarget("Server", 5));
        System.out.println(ori.hashCode() + " ------ " + ori.getDeepCloneTarget().hashCode());

//        CloneExample clone1 = (CloneExample) ori.clone();
//        CloneExample clone2 = (CloneExample) ori.clone();
//        CloneExample clone3 = (CloneExample) ori.clone();
//        System.out.println(clone1.hashCode() + " ------ " + clone1.getDeepCloneTarget().hashCode());
//        System.out.println(clone2.hashCode() + " ------ " + clone2.getDeepCloneTarget().hashCode());
//        System.out.println(clone3.hashCode() + " ------ " + clone3.getDeepCloneTarget().hashCode());

//        CloneExample clone4 = (CloneExample) ori.deepClone();
//        CloneExample clone5 = (CloneExample) ori.deepClone();
//        CloneExample clone6 = (CloneExample) ori.deepClone();
//        System.out.println(clone4.hashCode() + " ------ " + clone4.getDeepCloneTarget().hashCode());
//        System.out.println(clone5.hashCode() + " ------ " + clone5.getDeepCloneTarget().hashCode());
//        System.out.println(clone6.hashCode() + " ------ " + clone6.getDeepCloneTarget().hashCode());

        CloneExample tgt = ObjectUtil.cloneByStream(ori);
        System.out.println(tgt.hashCode() + " ------ " + tgt.getDeepCloneTarget().hashCode());
    }

}

class DeepCloneTarget implements Cloneable, Serializable {
    private String type;
    private int age;

    public DeepCloneTarget(String type, int age) {
        this.type = type;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class CloneExample implements Cloneable, Serializable {

    private String name;

    private DeepCloneTarget deepCloneTarget;

    public DeepCloneTarget getDeepCloneTarget() {
        return deepCloneTarget;
    }

    public void setDeepCloneTarget(DeepCloneTarget deepCloneTarget) {
        this.deepCloneTarget = deepCloneTarget;
    }

    public CloneExample() {
    }

    public CloneExample(String name, DeepCloneTarget deepCloneTarget) {
        this.name = name;
        this.deepCloneTarget = deepCloneTarget;
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneExample deepObj = (CloneExample) super.clone();
        deepObj.deepCloneTarget = (DeepCloneTarget) this.deepCloneTarget.clone();
        return deepObj;
    }

    /**
     * 通过对象序列化 实现深拷贝
     *
     * @return
     */
    public Object deepClone() {
        CloneExample deepCloneExample;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            deepCloneExample = (CloneExample) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return deepCloneExample;
    }
}

