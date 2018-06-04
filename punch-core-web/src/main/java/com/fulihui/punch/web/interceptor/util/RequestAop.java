package com.fulihui.punch.web.interceptor.util;

import org.near.toolkit.model.ToString;

/**
 * The type Request aop.
 *
 * @author LZ
 */
public class RequestAop extends ToString {

    private static final long serialVersionUID = -379699933194414339L;
    private String            tempContextUrl;
    private String            url;
    private String            method;
    private String            uri;
    private String            queryString;
    private String            localName;
    private String            serverName;
    private String            contextPath;

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets server name.
     *
     * @return the server name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets server name.
     *
     * @param serverName the server name
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Gets temp context url.
     *
     * @return the temp context url
     */
    public String getTempContextUrl() {
        return tempContextUrl;
    }

    /**
     * Sets temp context url.
     *
     * @param tempContextUrl the temp context url
     */
    public void setTempContextUrl(String tempContextUrl) {
        this.tempContextUrl = tempContextUrl;
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets uri.
     *
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets uri.
     *
     * @param uri the uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets query string.
     *
     * @return the query string
     */
    public String getQueryString() {
        return queryString;
    }

    /**
     * Sets query string.
     *
     * @param queryString the query string
     */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
     * Gets local name.
     *
     * @return the local name
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Sets local name.
     *
     * @param localName the local name
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
