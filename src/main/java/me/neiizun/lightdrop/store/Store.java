package me.neiizun.lightdrop.store;

import java.util.HashMap;
import java.util.Map;

/**
 * Object container data, passed in {@link me.neiizun.lightdrop.command.CommandContext} for sharing data between commands.
 *
 * @since 1.2.0
 */
public class Store {
    /**
     * Map of all stored objects.
     */
    private final Map<String, Object> objectMap = new HashMap<>();

    /**
     * Write data to the store.
     * @param key Identifier of the data.
     * @param value Value of the data.
     */
    public void write(String key, Object value) {
        objectMap.put(key, value);
    }

    /**
     * Read an object from Store.
     * @param key Identifier of the data.
     * @param clazz Presumed class of the data.
     * @param <T> Presumed type of the data.
     * @return Object cast to presumed type of the data.
     */
    public <T> T read(String key, Class<T> clazz) {
        return (T) objectMap.get(key);
    }

    /**
     * Read an int from store.
     * @param key Identifier of the data.
     * @return int data.
     */
    public int readInt(String key) {
        return read(key, Integer.class);
    }

    /**
     * Read a long from store.
     * @param key Identifier of the data.
     * @return long data.
     */
    public long readLong(String key) {
        return read(key, Long.class);
    }

    /**
     * Read a double from store.
     * @param key Identifier of the data.
     * @return double data.
     */
    public double readDouble(String key) {
        return read(key, Double.class);
    }

    /**
     * Read a float from store.
     * @param key Identifier of the data.
     * @return float data.
     */
    public float readFloat(String key) {
        return read(key, Float.class);
    }

    /**
     * Read a short from store.
     * @param key Identifier of the data.
     * @return short data.
     */
    public short readShort(String key) {
        return read(key, Short.class);
    }

    /**
     * Read a byte from store.
     * @param key Identifier of the data.
     * @return byte data.
     */
    public byte readByte(String key) {
        return read(key, Byte.class);
    }


    /**
     * Read a String from store.
     * @param key Identifier of the data.
     * @return String data.
     */
    public String readString(String key) {
        return read(key, String.class);
    }

    /**
     * Read an Object from store.
     *
     * @param key Identifier of the data.
     * @return Object data.
     */
    public Object readObject(String key) {
        return objectMap.get(key);
    }

    /**
     * Check if store contains a data.
     *
     * @param key Identifier of the data.
     * @return true if store contains the data.
     */
    public boolean contains(String key) {
        return objectMap.containsKey(key);
    }
}
