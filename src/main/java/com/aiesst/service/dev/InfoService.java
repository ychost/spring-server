package com.aiesst.service.dev;

import com.aiesst.manager.dev.InfoManager;
import com.aiesst.model.request.ReqInfo;
import com.aiesst.model.response.RestCode;
import com.aiesst.model.response.RestModel;
import com.aiesst.model.response.dev.RepInfo;
import com.aiesst.model.entity.dev.EntityInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ychost<ychost@outlook.com>
 * @version v1.0
 * @date 17-7-11
 */
@Service
public class InfoService {
    private final InfoManager infoManager;

    InfoService(InfoManager infoManager) {
        this.infoManager = infoManager;
    }

    public RestModel saveInfo(ReqInfo info) {
        RestModel restModel = new RestModel();
        try {
            EntityInfo entityInfo = ReqInfo.convertToEntity(info);
            this.infoManager.persist(entityInfo);
        } catch (Exception e) {
            restModel.setCode(RestCode.SqlError).setMessage("服务器出错");
        }
        return restModel;
    }

    /**
     * 用like模糊查询信息
     *
     * @param infoLike
     * @return
     */
    public RestModel queryInfo(String infoLike) {
        RestModel restModel = new RestModel();
        try {
            List<EntityInfo> entityInfoList = this.infoManager.queryInfoList(infoLike);
            List<RepInfo> repInfoList = new ArrayList<>();
            entityInfoList.forEach(i -> {
                repInfoList.add(RepInfo.convertByEntity(i));
            });
            restModel.setData(repInfoList);
        } catch (Exception e) {
            restModel.setCode(RestCode.NullError).setMessage("服务器出错");
        }
        return restModel;
    }
}
