package com.changgou.goods.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/7 15:34
 */
public class Goods implements Serializable {
    //spu信息
    private  Spu spu;
    //Sku集合信息
    private List<Sku> skuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "spu=" + spu +
                ", skuList=" + skuList +
                '}';
    }
}
