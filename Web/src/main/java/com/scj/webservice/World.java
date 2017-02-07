package com.scj.webservice;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
@XmlRootElement
@XmlType(name="world")
public class World {
    private String governer;
    private int century;

    public World() {
    }

    public World(String governer, int centuty) {
        this.governer = governer;
        this.century = centuty;
    }

    public String getGoverner() {
        return governer;
    }

    public void setGoverner(String governer) {
        this.governer = governer;
    }

    public int getCentury() {
        return century;
    }

    public void setCentury(int century) {
        this.century = century;
    }
}
