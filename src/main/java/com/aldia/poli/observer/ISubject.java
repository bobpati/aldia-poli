package com.aldia.poli.observer;
public interface ISubject {
    void agregarObserver(IObserver observer);
    void eliminarObserver(IObserver observer);
    void notificarObservers(String mensaje);
}
