package com.sgtslade.sites.monitoring;

import com.sgtslade.sites.base.Site;

import java.util.Objects;

public class ObservedItem {
    private String name;
    private Site site;

    public ObservedItem(String name, Site site) {
        this.name = name;
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public boolean getAvailability(){
        return site.productAvailable(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservedItem that = (ObservedItem) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(site, that.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, site);
    }
}
