package cloud.spring.my.common.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

/**
 *  hash 相关算法
 */
public class HashUtils {

    public static String getMurmur3_128(String val){
        HashCode hashCode = Hashing.murmur3_128().newHasher().putString(val, Charsets.UTF_8).hash();
        return hashCode.toString().toUpperCase();
    }

    public static String getSpooky(String val){
        return null;
    }
}
