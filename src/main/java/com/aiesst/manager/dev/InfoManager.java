package com.aiesst.manager.dev;

import com.aiesst.manager.BaseManager;
import com.aiesst.manager.Manager;
import com.aiesst.model.sql.dev.EntityInfo;
import org.jinq.jpa.JPQL;

import java.util.List;

/**
 * @author ychost<ychost@outlook.com>
 * @version v1.0
 * @date 17-7-11
 */
@Manager
public class InfoManager extends BaseManager {

    public List<EntityInfo> queryInfoList(String info) {
        return this.jinqStreams.streamAll(this.em, EntityInfo.class)
                .where(i -> JPQL.like(i.getInfo(), info)).toList();
    }



}
