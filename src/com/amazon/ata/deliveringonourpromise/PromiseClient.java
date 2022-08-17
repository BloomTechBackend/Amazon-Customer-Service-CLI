package com.amazon.ata.deliveringonourpromise;

import com.amazon.ata.deliveringonourpromise.types.Promise;

public interface PromiseClient {

    /**
     * getter method.
     * @param customerOrderItemId the itemId
     * @return a Promise object.
     */
    Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId);
}
