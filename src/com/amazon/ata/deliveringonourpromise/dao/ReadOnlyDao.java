package com.amazon.ata.deliveringonourpromise.dao;

/**
 * DAO interface to abstract calls.
 */
public interface ReadOnlyDao<I, O> {

    /**
     * Get object method to be implemented.
     * @param orderId Order Id
<<<<<<< HEAD
     * @param I expected class of the value
     * @param O expected class of the value
=======
     * @param I a param
     * @param O a param
>>>>>>> sprint_3
     * @return Object abstracted object
     */
    O get(I orderId);
}
