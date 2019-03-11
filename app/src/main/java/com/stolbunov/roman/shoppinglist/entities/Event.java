package com.stolbunov.roman.shoppinglist.entities;

public class Event<T> {
    private T value;
    private boolean isHandled;

    public Event(T value) {
        this.value = value;
        isHandled = false;
    }

    public boolean isHandled(){
        return isHandled;
    }

    public synchronized T getValue() {
        if (isHandled) {
            return null;
        } else {
            isHandled = true;
            return value;
        }
    }
}
