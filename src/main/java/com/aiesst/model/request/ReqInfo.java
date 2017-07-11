package com.aiesst.model.request;

import com.aiesst.model.entity.dev.EntityInfo;

/**
 * @author ychost<ychost@outlook.com>
 * @version v1.0
 * @date 17-7-11
 */
public class ReqInfo {
    private String name;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static EntityInfo convertToEntity(ReqInfo reqInfo) {
        if (reqInfo == null) {
            return null;
        }
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setInfo(reqInfo.getName() + ":" + reqInfo.getVersion());
        return entityInfo;
    }
}
