package com.amazon.ata.deliveringonourpromise.types;

import com.amazon.ata.ordermanipulationauthority.OrderCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    @Test
    public void order_changingOrderFields_returnsSameOriginalFields(){
        //GIVEN
        OrderItem someItem;
        someItem = OrderItem.builder()
                .withQuantity(5)
                .withTitle("Potatoes")
                .build();

        Order someOrder;
        someOrder = Order.builder()
                .withOrderId("111-7497023-2960775")
                .withCustomerOrderItemList(new ArrayList<OrderItem>())
                .build();

        //WHEN
//        int sizeOfOriginalList = someOrder.getCustomerOrderItemList().size();
//        System.out.println("sizeOfOriginalList = " + sizeOfOriginalList);
//        someOrder.getCustomerOrderItemList().add(someItem);
//        int sizeofModifiedList = someOrder.getCustomerOrderItemList().size();
//        System.out.println("sizeofModifiedList = " + sizeofModifiedList);
//
//        boolean isEqual =sizeofModifiedList == sizeOfOriginalList;
//        System.out.println(isEqual);

         //THEN
        Assertions.assertThrows(UnsupportedOperationException.class, () -> someOrder.getCustomerOrderItemList().add(someItem), "You cannot change the order's variables.");

    }




}
