package com.amazon.ata.deliveringonourpromise.activity;

import com.amazon.ata.deliveringonourpromise.dao.ReadOnlyDao;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.deliveringonourpromise.types.OrderItem;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.deliveringonourpromise.types.PromiseHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity class, handling the GetPromiseHistoryByOrderId API.
 */
public class GetPromiseHistoryByOrderIdActivity {
    private ReadOnlyDao<String, Order> orderDao;
    private ReadOnlyDao<String, List<Promise>> promiseDao;

    /**
     * Instantiates an activity for handling the API, accepting the relevant DAOs to
     * perform its work.
     *
     * @param orderDao   data access object fo retrieving Orders by order ID
     * @param promiseDao data access object for retrieving Promises by order item ID
     */
    public GetPromiseHistoryByOrderIdActivity(ReadOnlyDao<String, Order> orderDao,
                                              ReadOnlyDao<String, List<Promise>> promiseDao) {
        this.orderDao = orderDao;
        this.promiseDao = promiseDao;
    }

    /**
     * Returns the PromiseHistory for the given order ID, if the order exists. If the order does
     * not exist a PromiseHistory with a null order and no promises will be returned.
     *
     * @param orderId The order ID to fetch PromiseHistory for
     * @return PromiseHistory containing the order and promise history for that order
     */

    //TODO MT5 the below is displaying one history only. Needs to display multiple histories if > 1 item exists per order.
    public PromiseHistory getPromiseHistoryByOrderId(String orderId) {
        if (null == orderId) {
            throw new IllegalArgumentException("order ID cannot be null");
        }

        Order order = orderDao.get(orderId);
        OrderItem customerOrderItem = null;
        List<OrderItem> customerOrderItemListForPromises = order.getCustomerOrderItemList(); //TODO MT5

        if (null != order) {
            List<OrderItem> customerOrderItems = order.getCustomerOrderItemList();
            if (customerOrderItems != null && !customerOrderItems.isEmpty()) {
                customerOrderItem = customerOrderItems.get(0); //Pre MT5
            }
        }


        PromiseHistory history = new PromiseHistory(order);

        //Below: List<Promise> is adding orderItems and returning Promise History. It is using the above list created for MT5
        //Time:9:29am lets see if it works. Next step: promise history needs to print it out to the user.
        if (customerOrderItemListForPromises.get(0) != null) {
            for (int i = 0; i < order.getCustomerOrderItemList().size(); i++) {
                OrderItem orderItem = order.getCustomerOrderItemList().get(i);
                List<Promise> promises = promiseDao.get(orderItem.getCustomerOrderItemId());
                for (Promise promise : promises) {
                    promise.setConfidence(orderItem.isConfidenceTracked(), orderItem.getConfidence());
                    history.addPromise(promise);
                }
            }

//
//            List<Promise> promises = promiseDao.get(customerOrderItem.getCustomerOrderItemId());// returns promise for ONE ITEM. Make return for ALL ITEMS in one ID
//            for (Promise promise : promises) {
//                promise.setConfidence(customerOrderItem.isConfidenceTracked(), customerOrderItem.getConfidence());
//                history.addPromise(promise);
//            }

        }
        return history;
    }
}
