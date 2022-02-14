package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.data.OrderDatastore;
import com.amazon.ata.deliveringonourpromise.ordermanipulationauthority.OrderManipulationAuthorityClient;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.ordermanipulationauthority.OrderManipulationAuthority;
import com.amazon.ata.ordermanipulationauthority.OrderResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

public class OrderDaoTest {

    private OrderDao dao;

    @Test
    public void get_validOrderID_returnOrderObject(){
        // GIVEN
        OrderDao orderDao = new OrderDao(new OrderManipulationAuthorityClient(new OrderManipulationAuthority(OrderDatastore.getDatastore())));
        String validOrderID = "111-7497023-2960775";

        //WHEN
        Order result = orderDao.get(validOrderID);

        //THEN
        Assertions.assertEquals("111-7497023-2960775", result.getOrderId(), "Expected valid order object to return a valid order ID.");
    }

    @Test
    public void get_invalidOrderID_throwsException(){
        //GIVEN
        OrderDao orderDao = new OrderDao(new OrderManipulationAuthorityClient(new OrderManipulationAuthority(OrderDatastore.getDatastore())));
        String invalidOrderID = "11-7023-296077"; // invalid ID

        //WHEN+THEN
        Assertions.assertThrows(NullPointerException.class,
                () -> orderDao.get(invalidOrderID).getOrderId(), "Invalid ID should result in NullPointerException.");
    }

    @Test
    public void get_NullOrderID_throwsException(){
        //GIVEN
        OrderDao orderDao = new OrderDao(new OrderManipulationAuthorityClient(new OrderManipulationAuthority(OrderDatastore.getDatastore())));
        String nullID= null;

        //WHEN + THEN
        Assertions.assertThrows(NullPointerException.class,
                () -> orderDao.get(nullID).getOrderId(), "Null ID should return a NullPointerException.");

    }


}
