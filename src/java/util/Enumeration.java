/*
 * Copyright (c) 1994, 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.util;

/**
 * An object that implements the Enumeration interface generates a
 * series of elements, one at a time. Successive calls to the
 * {@code nextElement} method return successive elements of the
 * series.
 *  实现Enumeration接口的对象产生一系列的元素，一次一个。
 *  成功调用nextElement方法返回系列的元素
 *
 * <p>
 * For example, to print all elements of a {@code Vector<E>} <i>v</i>:
 * <pre>
 *   for (Enumeration&lt;E&gt; e = v.elements(); e.hasMoreElements();)
 *       System.out.println(e.nextElement());</pre>
 * <p>
 * Methods are provided to enumerate through the elements of a
 * vector, the keys of a hashtable, and the values in a hashtable.
 * Enumerations are also used to specify the input streams to a
 * {@code SequenceInputStream}.
 *  方法提供枚举vector，hashtable的键，hashtable的值。
 *  Enumeration也用在指定SequenceInputStream的输入流，
 *
 * @apiNote
 * The functionality of this interface is duplicated by the {@link Iterator}
 * interface.  In addition, {@code Iterator} adds an optional remove operation,
 * and has shorter method names.  New implementations should consider using
 * {@code Iterator} in preference to {@code Enumeration}. It is possible to
 * adapt an {@code Enumeration} to an {@code Iterator} by using the
 * {@link #asIterator} method.
 *  此接口（Enumeration）的功能由Iterator接口重写。此外，Iterator接口添加了一个可选的删除方法，拥有更短的名字。
 *  新的实现需要优先考虑Iterator。通过使用 asIterator方法将Enumeration转换为Iterator。
 *
 * @see     Iterator
 * @see     java.io.SequenceInputStream
 * @see     Enumeration#nextElement()
 * @see     Hashtable
 * @see     Hashtable#elements()
 * @see     Hashtable#keys()
 * @see     Vector
 * @see     Vector#elements()
 *
 * @author  Lee Boynton
 * @since   1.0
 */
public interface Enumeration<E> {
    /**
     * Tests if this enumeration contains more elements.
     *  测试enumeration是否包含更多元素
     *
     * @return  {@code true} if and only if this enumeration object
     *           contains at least one more element to provide;
     *          {@code false} otherwise.
     */
    boolean hasMoreElements();

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return     the next element of this enumeration.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    E nextElement();

    /**
     * Returns an {@link Iterator} that traverses the remaining elements
     * covered by this enumeration. Traversal is undefined if any methods
     * are called on this enumeration after the call to {@code asIterator}.
     *
     * @apiNote
     * This method is intended to help adapt code that produces
     * {@code Enumeration} instances to code that consumes {@code Iterator}
     * instances. For example, the {@link java.util.jar.JarFile#entries
     * JarFile.entries()} method returns an {@code Enumeration<JarEntry>}.
     * This can be turned into an {@code Iterator}, and then the
     * {@code forEachRemaining()} method can be used:
     *
     * <pre>{@code
     *     JarFile jarFile = ... ;
     *     jarFile.entries().asIterator().forEachRemaining(entry -> { ... });
     * }</pre>
     *
     * (Note that there is also a {@link java.util.jar.JarFile#stream
     * JarFile.stream()} method that returns a {@code Stream} of entries,
     * which may be more convenient in some cases.)
     *
     * @implSpec
     * The default implementation returns an {@code Iterator} whose
     * {@link Iterator#hasNext hasNext} method calls this Enumeration's
     * {@code hasMoreElements} method, whose {@link Iterator#next next}
     * method calls this Enumeration's {@code nextElement} method, and
     * whose {@link Iterator#remove remove} method throws
     * {@code UnsupportedOperationException}.
     *
     * @return an Iterator representing the remaining elements of this Enumeration
     *
     * @since 9
     */
    default Iterator<E> asIterator() {
        return new Iterator<>() {
            @Override public boolean hasNext() {
                return hasMoreElements();
            }
            @Override public E next() {
                return nextElement();
            }
        };
    }
}
