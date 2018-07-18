package com.conduit.plastic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by android on 2016/12/1.
 */

public class FileBeanEntity implements Serializable {

    private List<String> accessUrls;
    private List<String> resourcePaths;
    private List<String> sourceUrls;

    public List<String> getAccessUrls() {
        return accessUrls;
    }

    public void setAccessUrls(List<String> accessUrls) {
        this.accessUrls = accessUrls;
    }

    public List<String> getResourcePaths() {
        return resourcePaths;
    }

    public void setResourcePaths(List<String> resourcePaths) {
        this.resourcePaths = resourcePaths;
    }

    public List<String> getSourceUrls() {
        return sourceUrls;
    }

    public void setSourceUrls(List<String> sourceUrls) {
        this.sourceUrls = sourceUrls;
    }
}
