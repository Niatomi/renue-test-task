package ru.niatomi.util.cache.Impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author niatomi
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Node<T, U> {

    private Node<T, U> previous;
    private Node<T, U> next;
    private T key;
    private U value;

}
