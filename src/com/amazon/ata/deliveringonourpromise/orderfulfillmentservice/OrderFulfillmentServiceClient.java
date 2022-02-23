package com.amazon.ata.deliveringonourpromise.orderfulfillmentservice;

import com.amazon.ata.deliveringonourpromise.PromiseClient;
import com.amazon.ata.deliveringonourpromise.dao.PromiseDao;
import com.amazon.ata.deliveringonourpromise.promisehistoryservice.PromiseHistoryClient;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.deliverypromiseservice.service.DeliveryPromise;
import com.amazon.ata.orderfulfillmentservice.OrderFulfillmentService;
import com.amazon.ata.orderfulfillmentservice.OrderPromise;

/**
 * Client for accessing OrderFulfillmentService to retrieve Promises.
 */

public class OrderFulfillmentServiceClient implements PromiseClient {
        private OrderFulfillmentService OFS;


    /**
     * Creates new clients that calls OFS with the given service object.
     * @param OFS The OrderFulfillmentService that this client will call.
     */
    public OrderFulfillmentServiceClient(OrderFulfillmentService OFS) {
        this.OFS = OFS;
    }

    @Override
    public Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId) {
        OrderPromise deliveryPromise = OFS.getOrderPromise(customerOrderItemId);

        if (null == deliveryPromise) {
            return null;
        }

        return Promise.builder()
                .withPromiseLatestArrivalDate(deliveryPromise.getPromiseLatestArrivalDate())
                .withCustomerOrderItemId(deliveryPromise.getCustomerOrderItemId())
                .withPromiseLatestShipDate(deliveryPromise.getPromiseLatestShipDate())
                .withPromiseEffectiveDate(deliveryPromise.getPromiseEffectiveDate())
                .withIsActive(deliveryPromise.isActive())
                .withPromiseProvidedBy(deliveryPromise.getPromiseProvidedBy())
                .withAsin(deliveryPromise.getAsin())
                .build();
    }


}
