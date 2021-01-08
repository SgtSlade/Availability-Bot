package com.sgtslade.sites.monitoring;

import com.sgtslade.sites.base.Site;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Monitor {
    private final Set<ObservedItem> observedItems = new HashSet<>();

    public Set<ObservedItem> getItem(String name){
        return observedItems.stream().filter(observedItem -> observedItem.getName().equals(name)).collect(Collectors.toSet());
    }

    public Set<ObservedItem> getSiteStock(Site site){
        return observedItems.stream().filter(observedItem -> observedItem.getSite().equals(site)).collect(Collectors.toSet());
    }

    public Set<ObservedItem> getObservedItems() {
        return observedItems;
    }

    public Set<Site> getObservedSites(){
        return observedItems.stream().map(ObservedItem::getSite).collect(Collectors.toSet());
    }

    public void observeItem(String item, Site site){
        observedItems.add(new ObservedItem(item,site));
    }
}
