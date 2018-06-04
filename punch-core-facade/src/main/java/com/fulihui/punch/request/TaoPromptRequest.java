package com.fulihui.punch.request;

import org.near.servicesupport.request.AbstractServiceRequest;

/**
 * @author Administrator
 */
public class TaoPromptRequest extends AbstractServiceRequest {
    /**
     * @see com.fulihui.punch.enums.TaoPromptCodeEnum
     */
    private String            code;

    private String            content;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}