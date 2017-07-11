package com.aiesst.model.sql.dev;

import com.aiesst.model.sql.EntityBase;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ychost<ychost@outlook.com>
 * @version v1.0
 * @date 17-7-11
 */
@Entity
@Table(name = "info")
public class EntityInfo {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private int id;

    @Column(name = "remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "info", length = 4000)
    private String info;

    @Column(name = "upload_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime = new Date();

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
