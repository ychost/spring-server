package com.aiesst.model.response.dev;

import com.aiesst.model.entity.dev.EntityInfo;

/**
 * @author ychost<ychost@outlook.com>
 * @version v1.0
 * @date 17-7-11
 */
public class RepInfo {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static RepInfo convertByEntity(EntityInfo entityInfo) {
        if (entityInfo == null) {
            return null;
        }
        RepInfo repInfo = new RepInfo();
        repInfo.setInfo("converted: " + entityInfo);
        return repInfo;
    }
}
