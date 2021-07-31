package me.neiizun.lightdrop.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Holder of all stores, permit creating stores and removes stores.
 *
 * @since 1.2.0
 */
public class StoreCluster {
    /**
     * Map of all stores by name.
     */
    private final Map<String, Store> storeMap = new HashMap<>();

    /**
     * Create a new store.
     *
     * @param name Name of the store.
     * @return new Store instance.
     */
    public Store newStore(String name) {
        Store store = new Store();
        storeMap.put(name, store);
        return store;
    }

    /**
     * Remove a store from cluster.
     *
     * @param name Name of the store.
     */
    public void removeStore(String name) {
        storeMap.remove(name);
    }

    /**
     * Get a store from cluster by name.
     *
     * @param name name of the store.
     * @return Store instance (null if not present).
     */
    public Store getStore(String name) {
        return storeMap.get(name);
    }

    /**
     * Find a store by name.
     *
     * @param name Name of the store.
     * @return Optional (empty if not present).
     */
    public Optional<Store> findStore(String name) {
        return Optional.ofNullable(getStore(name));
    }
}
