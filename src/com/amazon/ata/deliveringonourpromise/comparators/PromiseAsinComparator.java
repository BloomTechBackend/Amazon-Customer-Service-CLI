package com.amazon.ata.deliveringonourpromise.comparators;

import com.amazon.ata.deliveringonourpromise.dao.OrderDao;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.deliveringonourpromise.types.PromiseHistory;

import java.util.Comparator;

public class PromiseAsinComparator implements Comparator<Promise> {

    @Override
    public int compare(Promise promise1, Promise promise2) {
        String promise1Asin = promise1.getAsin();
        String promise2Asin = promise2.getAsin();
        return promise1Asin.compareTo(promise2Asin);
    }

}
