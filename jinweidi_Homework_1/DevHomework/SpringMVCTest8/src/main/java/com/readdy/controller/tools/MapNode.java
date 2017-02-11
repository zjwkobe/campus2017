package com.readdy.controller.tools;

class MapNode implements Comparable<MapNode> {
    private Character c;
    private Integer times;

    public MapNode(Character aChar, Integer aInt) {
        c = aChar;
        times = aInt;
    }

    public Character getChar() {
        return c;
    }

    public Integer getTimes() {
        return times;
    }

    /**
     * 按字符出现次数由大到小排序，
     * 若次数相同，按 UNICODE 编码由小到大排序。
     * @param mn
     * @return
     */
    public int compareTo(MapNode mn) {
        if (this.times == mn.getTimes()) {
            return (((int)this.c)-((int)mn.getChar()));
        }
        return (mn.getTimes()-this.times);
    }
}
