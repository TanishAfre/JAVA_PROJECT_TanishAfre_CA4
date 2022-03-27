package org.example;

public class Counter {
    int counterId;
    String counterName;

    public Counter(int counterId, String counterName) {
        this.counterId = counterId;
        this.counterName = counterName;
    }

    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(int counterId) {
        this.counterId = counterId;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counterId='" + counterId + '\'' +
                ", counterName='" + counterName + '\'' +
                '}';
    }
}

