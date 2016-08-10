package com.vardanian.utils;

import javax.persistence.Query;
import java.util.List;

public class Utils {

    public static Object getSingleResult(Query query) {
        query.setMaxResults(1);
        List<Object> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
