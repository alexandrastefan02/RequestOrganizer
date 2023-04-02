package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class RequestQueue {
    private HashMap<String, Cerere> requestMap;
    private PriorityQueue<Cerere> requestQueue;

    public RequestQueue(HashMap<String, Cerere> requestMap) {
        this.requestMap = requestMap;
        requestQueue = new PriorityQueue<>(new Comparator<Cerere>() {
            public int compare(Cerere o1, Cerere o2) {
                if (o1.date.getTime() < o2.date.getTime())
                    return -1;
                else if (o1.date.getTime() == o2.date.getTime())
                    return 0;
                else
                    return 1;
            }
        });
    }

    public void putElementsWithKeyInQueue(String key) {
        Cerere request = requestMap.get(key);
        if (request != null) {
            requestQueue.add(request);
        }
    }

    public PriorityQueue<Cerere> getRequestQueue() {
        return requestQueue;
    }
}
