package io.openmessaging;

import java.io.FileInputStream;
import io.openmessaging.demo.Node;
/**
 * Created by E450C on 2017/4/21.
 */
public interface OptimisticMap {

    Node put(String key, FileInputStream fileInputStream);
    void remove(String key);
    Node get(String key);

}
