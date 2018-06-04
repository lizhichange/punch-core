package com.fulihui.punch.result;

import lombok.Getter;
import lombok.Setter;
import org.near.toolkit.model.ToString;

import java.util.Date;

@Setter
@Getter
public class CarouselResult extends ToString {

    private static final long serialVersionUID = 7580420392574071832L;

    private Integer           id;

    /**
     * 创建时间
     */
    private Date              gmtCreate;
    /**
     * 修改时间
     */
    private Date              gmtModified;

    /**
     * 用户id
     *
     */
    private String            userId;

}
