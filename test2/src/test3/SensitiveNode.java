/*
 * @Author: your name
 * @Date: 2019-10-24 13:56:19
 * @LastEditTime: 2019-10-24 16:08:07
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \crawler\src\cn\intian\sensi\SensitiveNode.java
 */
package test3;

import java.io.Serializable;
import java.util.TreeSet;

public class SensitiveNode implements Serializable
{
    private static final long serialVersionUID = 1L;
    protected final int headTwoCharMix;
    protected final TreeSet<StringPointer> words = new TreeSet<>();
    protected SensitiveNode next;

    public SensitiveNode(int headTwoCharMix) {
        this.headTwoCharMix = headTwoCharMix;
    }

    public SensitiveNode(int headTwoCharMix, SensitiveNode parent) {
        this.headTwoCharMix = headTwoCharMix;
        parent.next = this;
    }
}