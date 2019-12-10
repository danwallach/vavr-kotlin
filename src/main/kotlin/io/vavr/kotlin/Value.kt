/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2020 Vavr, http://vavr.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("DEPRECATION")

package io.vavr.kotlin

import io.vavr.*
import io.vavr.Value
import io.vavr.collection.*
import io.vavr.collection.Array
import io.vavr.collection.LinkedHashMap
import io.vavr.collection.LinkedHashSet
import io.vavr.collection.List
import io.vavr.collection.Map
import io.vavr.collection.PriorityQueue
import io.vavr.collection.Queue
import io.vavr.collection.Set
import io.vavr.collection.SortedMap
import io.vavr.collection.SortedSet
import io.vavr.collection.TreeMap
import io.vavr.collection.TreeSet
import io.vavr.collection.Vector
import io.vavr.control.Either
import io.vavr.control.Option
import io.vavr.control.Try
import io.vavr.control.Validation
import java.io.PrintStream
import java.io.PrintWriter
import java.util.HashMap
import java.util.HashSet
import java.util.concurrent.CompletableFuture
import java.util.function.Function
import java.util.stream.Collector

/**
 * VAVR-Kotlin wrapper for VAVR's [io.vavr.Value] class. This wrapper class is a Kotlin "inline class",
 * so it has the nice property that it doesn't allocate any extra memory, yet by using it we have
 * the benefit of additional Kotlin features that Java cannot provide us. In particular, once you
 * wrap an [io.vavr.Value], perhaps using [io.vavr.Value.toKotlin], thereafter the type parameter's
 * inferred nullity will be correct (e.g., once you have `io.vavr.kotlin.Value<String>` and you operate on it,
 * you'll never end up with `Value<String!>`). Also, unlike [io.vavr.Value], when you use [io.vavr.kotlin.Value]
 * it's declared to be covariant in its type parameter, so you'll have less issues with narrowing or typecasting.
 */
inline class Value<out T>(val value: io.vavr.Value<out T>) : Iterable<T> {
    /**
     * Collects the underlying value(s) (if present) using the provided `collector`.
     *
     * @param A       the mutable accumulation type of the reduction operation
     * @param R       the result type of the reduction operation
     * @param collector Collector performing reduction
     * @return R reduction result
     */
    fun <R, A> collect(collector: Collector<in T, A, R>)
            : R = value.collect(collector)

    /**
     * Collects the underlying value(s) (if present) using the given `supplier`, `accumulator` and
     * `combiner`.
     *
     * @param R         type of the result
     * @param supplier    provide unit value for reduction
     * @param accumulator perform reduction with unit value
     * @param combiner    function for combining two values, which must be
     * compatible with the accumulator.
     * @return R reduction result
     */
    fun <R> collect(supplier: () -> R, accumulator: (R, T) -> Unit, combiner: (R, R) -> Unit)
            : R = value.collect(supplier, accumulator, combiner)

    /**
     * Tests whether every element of this iterable relates to the corresponding element of another iterable by
     * satisfying a test predicate.
     *
     * @param U       Component type of that iterable
     * @param that      the other iterable
     * @param predicate the test predicate, which relates elements from both iterables
     * @return `true` if both iterables have the same length and `predicate(x, y)`
     * is `true` for all corresponding elements `x` of this iterable and `y` of `that`,
     * otherwise `false`.
     */
    fun <U> corresponds(that: Iterable<U>, predicate: (T, U) -> Boolean)
            : Boolean = value.corresponds(that, predicate)

    /**
     * A *smoothing* replacement for `equals`. It is similar to Scala's `==` but better in the way
     * that it is not limited to collection types, e.g. `Some(1) eq List(1)`, `None eq Failure(x)` etc.
     *
     * @see io.vavr.Value.eq
     */
    fun eq(o: Any?)
            : Boolean = value.eq(o)

    /**
     * Checks, if an element exists such that the predicate holds.
     *
     * @param predicate A Predicate
     * @return true, if predicate holds for one or more elements, false otherwise
     */
    fun exists(predicate: (T) -> Boolean)
            : Boolean = value.exists(predicate)

    /**
     * Checks, if the given predicate holds for all elements.
     *
     * @param predicate A Predicate
     * @return true, if the predicate holds for all elements, false otherwise
     */
    fun forAll(predicate: (T) -> Boolean)
            : Boolean = value.forAll(predicate)

    /**
     * Performs an action on each element.
     *
     * @param action A `Consumer`
     * @throws NullPointerException if `action` is null
     */
    fun forEach(action: (T) -> Unit)
            : Unit = value.forEach(action)

    /**
     * Checks if this `Value` is asynchronously (short: async) computed.
     *
     *
     * Methods of a `Value` instance that operate on the underlying value may block the current thread
     * until the value is present and the computation can be performed.
     *
     * @return true if this `Value` is async (like [io.vavr.concurrent.Future]), false otherwise.
     */
    fun isAsync()
            : Boolean = value.isAsync

    /**
     * Checks, this `Value` is empty, i.e. if the underlying value is absent.
     *
     * @return false, if no underlying value is present, true otherwise.
     */
    fun isEmpty()
            : Boolean = value.isEmpty

    /**
     * Checks if this `Value` is lazily evaluated.
     *
     * @return true if this `Value` is lazy (like [Lazy] and [Stream]), false otherwise.
     */
    fun isLazy()
            : Boolean = value.isLazy

    /**
     * States whether this is a single-valued type.
     *
     * @return `true` if this is single-valued, `false` otherwise.
     */
    fun isSingleValued()
            : Boolean = value.isSingleValued

    /**
     * Maps the underlying value to a different component type.
     *
     * @param mapper A mapper
     * @param U    The new component type
     * @return A new value
     */
    fun <U> map(mapper: (T) -> U)
            : io.vavr.kotlin.Value<U> = value.map(mapper).toKotlin()

    /**
     * Performs the given `action` on the first element if this is an *eager* implementation.
     * Performs the given `action` on all elements (the first immediately, successive deferred),
     * if this is a *lazy* implementation.
     *
     * @param action The action that will be performed on the element(s).
     * @return this instance
     */
    fun peek(action: (T) -> Unit)
            : io.vavr.kotlin.Value<T> {
        value.peek(action)
        return this
    }

    /**
     * Returns the name of this Value type, which is used by toString().
     *
     * @return This type name.
     */
    fun stringPrefix()
            : String = value.stringPrefix()

    /**
     * Sends the string representations of this to the [PrintStream].
     * If this value consists of multiple elements, each element is displayed in a new line.
     *
     * @param out The PrintStream to write to
     * @throws IllegalStateException if `PrintStream.checkError()` is true after writing to stream.
     */
    fun out(out: PrintStream)
            : Unit = value.out(out)

    /**
     * Sends the string representations of this to the [PrintWriter].
     * If this value consists of multiple elements, each element is displayed in a new line.
     *
     * @param writer The PrintWriter to write to
     * @throws IllegalStateException if `PrintWriter.checkError()` is true after writing to writer.
     */
    fun out(writer: PrintWriter)
            : Unit = value.out(writer)

    /**
     * Sends the string representations of this to the standard error stream [System.err].
     * If this value consists of multiple elements, each element is displayed in a new line.
     *
     * @throws IllegalStateException if `PrintStream.checkError()` is true after writing to stderr.
     */
    fun stderr()
            : Unit = value.stderr()

    /**
     * Sends the string representations of this to the standard output stream [System.out].
     * If this value consists of multiple elements, each element is displayed in a new line.
     *
     * @throws IllegalStateException if `PrintStream.checkError()` is true after writing to stdout.
     */
    fun stdout()
            : Unit = value.stdout()

    override fun iterator()
            : Iterator<T> = value.iterator()

    /**
     * Creates a spliterator(), suitable for use in Java Streams.
     */
    fun spliterator()
            : java.util.Spliterator<out T> = value.spliterator()

    override fun toString()
            : String = value.toString()

    // Note: Kotlin inline classes don't let you override equals() and hashCode(), saying
    // "member with the name 'equals' is reserved for future releases", but somehow it ends
    // up calling the internal/inlined equals() and hashCode() methods, which does the
    // right thing. (Maybe?)
}

/**
 * Converts from the regular VAVR Value class to the Kotlin inline wrapper for it.
 */
fun <T> io.vavr.Value<T>.toKotlin()
        : io.vavr.kotlin.Value<T> = io.vavr.kotlin.Value(this)

/**
 * Shortcut for `exists(e -> Objects.equals(e, element))`, tests if the given `element` is contained.
 *
 * @param element An Object of type A, may be null.
 * @return true, if element is contained, false otherwise.
 */
operator fun <T> io.vavr.kotlin.Value<T>.contains(element: T): Boolean = value.contains(element)

/**
 * Converts this to a [Vector].
 *
 * @return A new [Vector].
 */
@Suppress("UNCHECKED_CAST")
fun <T> io.vavr.kotlin.Value<T>.toVector(): Vector<T> = value.toVector() as Vector<T>

/**
 * Gets the underlying value or throws if no value is present.
 *
 * @see io.vavr.Value.get
 */
fun <T> io.vavr.kotlin.Value<T>.get(): T = value.get() as T

/**
 * Gets the underlying value or throws if no value is present.
 *
 * @see io.vavr.Value.get
 */
operator fun <T> io.vavr.kotlin.Value<T>.component1(): T = get()

/**
 * Gets the underlying value or throws if no value is present.
 *
 * @see io.vavr.Value.get
 */
operator fun <T> io.vavr.kotlin.Value<T>.invoke(): T = get()

/**
 * Gets the underlying value or throws if no value is present.
 *
 * @see io.vavr.Value.get
 */
fun <T> io.vavr.kotlin.Value<T>.getValue(): T = get()

/**
 * Returns the underlying value if present, otherwise `other`.
 *
 * @param other An alternative value.
 * @return A value of type `T`
 */
fun <T> io.vavr.kotlin.Value<T>.getOrElse(other: T)
        : T  = if (isEmpty()) other else get()

/**
 * Returns the underlying value if present, otherwise `other`.
 *
 * @param supplier An alternative value supplier.
 * @return A value of type `T`
 * @throws NullPointerException if supplier is null
 */
fun <T> io.vavr.kotlin.Value<T>.getOrElse(supplier: () -> T)
        : T  = getOrElse(supplier)

/**
 * Returns the underlying value if present, otherwise throws `supplier.get()`.
 *
 * @param X      a Throwable type
 * @param supplier An exception supplier.
 * @return A value of type `T`.
 * @throws X                    if no value is present
 */
fun <X : Throwable, T> io.vavr.kotlin.Value<T>.getOrElseThrow(supplier: () -> X)
        : T = value.getOrElseThrow(supplier)

/**
 * Returns the underlying value if present, otherwise returns the result of `Try.of(supplier).get()`.
 *
 * @param supplier An alternative value supplier.
 * @return A value of type `T`.
 */
fun <T> io.vavr.kotlin.Value<T>.getOrElseTry(supplier: () -> T)
        : T = if (isEmpty()) Try.of(supplier).get() else get()

/**
 * Returns the underlying value if present, otherwise `null`.
 * (Only defined if the type `T` is not nullable, otherwise
 * it's ambiguous what a `null` return value means.)
 *
 * @return A value of type `T` or `null`.
 */
fun <T: Any> io.vavr.kotlin.Value<T>.getOrNull()
        : T? = if (isEmpty()) null else get()

/**
 * Converts this to a [Array].
 *
 * @return A new [Array].
 */
fun <T> io.vavr.kotlin.Value<T>.toArray()
        : Array<out T> = value.toArray()

/**
 * Converts this to a [CharSeq].
 *
 * @return A new [CharSeq].
 */
fun <T> io.vavr.kotlin.Value<T>.toCharSeq()
        : CharSeq = value.toCharSeq()

/**
 * Converts this to a [CompletableFuture]
 *
 * @return A new [CompletableFuture] containing the value
 */
@Suppress("UNCHECKED_CAST")
fun <T> io.vavr.kotlin.Value<T>.toCompletableFuture()
        : CompletableFuture<T> = value.toCompletableFuture() as CompletableFuture<T>

/**
 * Converts this to a [Validation].
 *
 * @param U   value type of a `Valid`
 * @param value An instance of a `Valid` value
 * @return A new [Validation.Valid] containing the given `value` if this is empty, otherwise
 * a new [Validation.Invalid] containing this value.
 */
@Deprecated("Use Value.toValidation instead.")
fun <T, U> io.vavr.kotlin.Value<T>.toInvalid(value: U)
        : Validation<T, U> = Validation.narrow(this.value.toInvalid(value))

/**
 * Converts this to a [Validation].
 *
 * @param U           value type of a `Valid`
 * @param valueSupplier A supplier of a `Valid` value
 * @return A new [Validation.Valid] containing the result of `valueSupplier` if this is empty,
 * otherwise a new [Validation.Invalid] containing this value.
 */
@Deprecated("Use Value.toValidation instead.")
fun <T, U> io.vavr.kotlin.Value<T>.toInvalid(valueSupplier: () -> U)
        : Validation<T, U> = Validation.narrow(value.toInvalid(valueSupplier))

/**
 * Converts this to a Kotlin array having an accurate component type.
 *
 * @param arrayFactory an `int` argument function that
 * creates an array of the correct component
 * type with the specified size
 * @return The array provided by the lambda filled with the values from this `Value`.
 * @see Value.toJavaArray
 */
fun <T> io.vavr.kotlin.Value<T>.toArray(arrayFactory: (Int) -> kotlin.Array<T>)
        : kotlin.Array<T> = Value.narrow(value).toJavaArray(arrayFactory)

/**
 * Converts this to a specific mutable [kotlin.collections.Collection] of type `C`.
 *
 * @param factory A lambda that returns an empty mutable `java.util.Collection` with the specified initial capacity
 * @param C     a sub-type of `java.util.Collection`
 * @return a new `kotlin.collecitons.Collection` of type `C`
 * @see Value.toJavaCollection
 */
fun <T, C : Collection<T>> io.vavr.kotlin.Value<T>.toCollection(factory: (Int) -> C)
        : C = Value.narrow(value).toJavaCollection(factory)

/**
 * Converts this to a mutable [kotlin.collections.MutableList].
 */
fun <T> io.vavr.kotlin.Value<T>.toMutableList()
        : kotlin.collections.MutableList<T> = Value.narrow(value).toJavaList()
// Kotlin's list shadows Java's list, so this is the right type signature here.


/**
 * Converts this to a specific type of mutable [kotlin.collections.List].
 *
 * @param factory A lambda that returns an empty mutable `kotlin.collections.List` with the specified initial capacity
 * @param LIST  the subtype of list
 * @return a new `kotlin.collections.MutableList` of type `LIST`
 */
fun <T, LIST : kotlin.collections.MutableList<T>> io.vavr.kotlin.Value<T>.toMutableList(factory: (Int) -> LIST)
        : LIST = Value.narrow(value).toJavaCollection(factory)

/**
 * Converts this to a mutable [kotlin.collections.MutableMap].
 *
 * @param f   A function that maps an element to a key/value pair represented by Tuple2
 * @param K The key type
 * @param V The value type
 * @return A new [kotlin.collections.HashMap]
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toMutableMap(f: (T) -> Tuple2<K, V>)
        : kotlin.collections.MutableMap<K, V> = value.toJavaMap(f)

/**
 * Converts this to a specific mutable [kotlin.collections.MutableMap].
 *
 * @param factory     A lambda that creates an empty mutable `kotlin.collections.MutableMap`
 * @param keyMapper   A function that maps an element to a key
 * @param valueMapper A function that maps an element to a value
 * @param K         The key type
 * @param V         The value type
 * @param MAP       a sub-type of `kotlin.collections.MutableMap`
 * @return a new `kotlin.collections.MutableMap` of type `MAP`
 */
fun <T, K, V, MAP : kotlin.collections.MutableMap<K, V>> io.vavr.kotlin.Value<T>.toMutableMap(factory: () -> MAP, keyMapper: (T) -> K, valueMapper: (T) -> V)
        : MAP = value.toJavaMap(factory, keyMapper, valueMapper)

/**
 * Converts this to a specific mutable [kotlin.collections.MutableMap].
 *
 * @param factory A lambda that creates an empty mutable `kotlin.collections.MutableMap`
 * @param f       A function that maps an element to a key/value pair represented by Tuple2
 * @param K     The key type
 * @param V     The value type
 * @param MAP   a sub-type of `kotlin.collections.MutableMap`
 * @return a new `kotlin.collections.MutableMap` of type `MAP`
 */
fun <T, K, V, MAP : kotlin.collections.MutableMap<K, V>> io.vavr.kotlin.Value<T>.toMutableMap(factory: () -> MAP, f: (T) -> Tuple2<out K, out V>)
        : MAP = value.toJavaMap(factory, f)

/**
 * Converts this to a [java.util.Optional].
 */
fun <T> io.vavr.kotlin.Value<T>.toJavaOptional()
        : java.util.Optional<T>  = Value.narrow(value).toJavaOptional()

/**
 * Converts this to a mutable [kotlin.collections.MutableSet].
 */
fun <T> io.vavr.kotlin.Value<T>.toMutableSet()
        : kotlin.collections.MutableSet<T> = Value.narrow(value).toJavaSet()

/**
 * Converts this to a specific [kotlin.collections.MutableSet].
 *
 * @param factory A lambda that returns an empty mutable `kotlin.collections.MutableSet` with the specified initial capacity
 * @param <SET>   a sub-type of `kotlin.collections.MutableSet`
 * @return a new `kotlin.collections.MutableSet` of type `SET`
 */
fun <T, SET : kotlin.collections.MutableSet<T>> io.vavr.kotlin.Value<T>.toJavaSet(factory: (Int) -> SET)
        : SET = Value.narrow(value).toJavaSet(factory)

/**
 * Converts this to a sequential [java.util.stream.Stream] by calling
 * `StreamSupport.stream(this.spliterator(), false)`.
 *
 * @return A new sequential [java.util.stream.Stream].
 * @see Value.spliterator
 */
fun <T> io.vavr.kotlin.Value<T>.toJavaStream()
        : java.util.stream.Stream<T> = Value.narrow(value).toJavaStream()

/**
 * Converts this to a parallel [java.util.stream.Stream] by calling
 * `StreamSupport.stream(this.spliterator(), true)`.
 *
 * @return A new parallel [java.util.stream.Stream].
 * @see Value.spliterator
 */
fun <T> io.vavr.kotlin.Value<T>.toJavaParallelStream()
        : java.util.stream.Stream<T> = Value.narrow(value).toJavaParallelStream()

/**
 * Converts this to an [Either].
 *
 * @param R   right type
 * @param right An instance of a right value
 * @return A new [Either.Right] containing the value of `right` if this is empty, otherwise
 * a new [Either.Left] containing this value.
 */
@Deprecated("Use Value.toEither instead.")
fun <T, R> io.vavr.kotlin.Value<T>.toLeft(right: R)
        : Either<T, R> = Value.narrow(value).toLeft(right)

/**
 * Converts this to a [Either].
 *
 * @param R   right type
 * @param right A supplier of a right value
 * @return A new [Either.Right] containing the result of `right` if this is empty, otherwise
 * a new [Either.Left] containing this value.
 */
@Deprecated("Use Value.toEither instead.")
fun <T, R> io.vavr.kotlin.Value<T>.toLeft(right: () -> R)
        : Either<T, R> = Value.narrow(value).toLeft(right)

/**
 * Converts this to a [Either].
 *
 * @param L left type
 * @param left An instance of a left value
 * @return A new [Either.Left] containing the value of `left` if this is empty, otherwise
 * a new [Either.Right] containing this value.
 */
@Deprecated("Use Value.toEither instead.", ReplaceWith("toEither(left)"))
fun <T, L> io.vavr.kotlin.Value<T>.toRight(left: L)
        : Either<L, T> = toRight(left)

/**
 * Converts this to a [Either].
 *
 * @param L  left type
 * @param left A supplier of a left value
 * @return A new [Either.Left] containing the result of `left` if this is empty, otherwise
 * a new [Either.Right] containing this value.
 */
@Deprecated("Use Value.toEither instead.", ReplaceWith("toEither(left)"))
fun <T, L> io.vavr.kotlin.Value<T>.toRight(left: () -> L)
        : Either<L, T> = toRight(left)

/**
 * Converts this to an [Either].
 *
 * @param left A left value for the [Either]
 * @param L  Either left component type
 * @return A new [Either].
 */
fun <T, L> io.vavr.kotlin.Value<T>.toEither(left: L)
        : Either<L, T> = Value.narrow(value).toEither(left)

/**
 * Converts this to an [Either].
 *
 * @param leftSupplier A [Supplier] for the left value for the [Either]
 * @param L          Validation error component type
 * @return A new [Either].
 */
fun <T, L> io.vavr.kotlin.Value<T>.toEither(leftSupplier: () -> L)
        : Either<L, T> = Value.narrow(value).toEither(leftSupplier)

/**
 * Converts this to a [List].
 *
 * @return A new [List].
 */
fun <T> io.vavr.kotlin.Value<T>.toList()
        : List<T> = Value.narrow(value).toList()

/**
 * Converts this to a [Map].
 *
 * @param keyMapper   A function that maps an element to a key
 * @param valueMapper A function that maps an element to a value
 * @param K         The key type
 * @param V         The value type
 * @return A new [HashMap].
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toMap(keyMapper: (T) -> K, valueMapper: (T) -> V)
        : Map<K, V> = value.toMap(keyMapper, valueMapper)

/**
 * Converts this to a [Map].
 *
 * @param f   A function that maps an element to a key/value pair represented by Tuple2
 * @param K The key type
 * @param V The value type
 * @return A new [HashMap].
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toMap(f: (T) -> Tuple2<out K, out V>)
        : Map<K, V> = value.toMap(f)

/**
 * Converts this to a [Map].
 *
 * @param keyMapper   A function that maps an element to a key
 * @param valueMapper A function that maps an element to a value
 * @param K         The key type
 * @param V         The value type
 * @return A new [LinkedHashMap].
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toLinkedMap(keyMapper: (T) -> K, valueMapper: (T) -> V)
        : Map<K, V> = value.toLinkedMap(keyMapper, valueMapper)

/**
 * Converts this to a [Map].
 *
 * @param f   A function that maps an element to a key/value pair represented by Tuple2
 * @param K The key type
 * @param V The value type
 * @return A new [LinkedHashMap].
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toLinkedMap(f: (T) -> Tuple2<out K, out V>)
        : Map<K, V> = value.toLinkedMap(f)

/**
 * Converts this to a [Map].
 *
 * @param keyMapper   A function that maps an element to a key
 * @param valueMapper A function that maps an element to a value
 * @param K         The key type
 * @param V         The value type
 * @return A new [TreeMap].
 */
fun <T, K : Comparable<K>, V> io.vavr.kotlin.Value<T>.toSortedMap(keyMapper: (T) -> K, valueMapper: (T) -> V)
        : SortedMap<K, V> = value.toSortedMap(keyMapper, valueMapper)

/**
 * Converts this to a [Map].
 *
 * @param f   A function that maps an element to a key/value pair represented by Tuple2
 * @param K The key type
 * @param V The value type
 * @return A new [TreeMap].
 */
fun <T, K : Comparable<K>, V> io.vavr.kotlin.Value<T>.toSortedMap(f: (T) -> Tuple2<out K, out V>)
        : SortedMap<K, V> = value.toSortedMap(f)

/**
 * Converts this to a [Map].
 *
 * @param comparator  A comparator that induces an order of the Map keys.
 * @param keyMapper   A function that maps an element to a key
 * @param valueMapper A function that maps an element to a value
 * @param K         The key type
 * @param V         The value type
 * @return A new [TreeMap].
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toSortedMap(comparator: Comparator<K>, keyMapper: (T) -> K, valueMapper: (T) -> V)
        : SortedMap<K, V> {
    // Kotlin can't always automatically convert from its function types to Java's.
    val javaK = Function(keyMapper)
    val javaV = Function(valueMapper)
    return value.toSortedMap(comparator, javaK, javaV)
}

/**
 * Converts this to a [Map].
 *
 * @param comparator A comparator that induces an order of the Map keys.
 * @param f          A function that maps an element to a key/value pair represented by Tuple2
 * @param K        The key type
 * @param V        The value type
 * @return A new [TreeMap].
 */
fun <T, K, V> io.vavr.kotlin.Value<T>.toSortedMap(comparator: Comparator<K>, f: (T) -> Tuple2<out K, out V>)
        : SortedMap<K, V> {
    // Kotlin can't always automatically convert from its function types to Java's.
    val javaF = Function(f)
    return value.toSortedMap(comparator, javaF)
}

/**
 * Converts this to an [Option].
 *
 * @return A new [Option].
 */
fun <T> io.vavr.kotlin.Value<T>.toOption()
        : Option<T> = Value.narrow(value).toOption()

/**
 * Converts this to an [Validation].
 *
 * @param invalid An invalid value for the [Validation]
 * @param E     Validation error component type
 * @return A new [Validation].
 */
fun <T, E> io.vavr.kotlin.Value<T>.toValidation(invalid: E)
        : Validation<E, T> = Value.narrow(value).toValidation(invalid)

/**
 * Converts this to an [Validation].
 *
 * @param invalidSupplier A [Supplier] for the invalid value for the [Validation]
 * @param E             Validation error component type
 * @return A new [Validation].
 */
fun <T, E> io.vavr.kotlin.Value<T>.toValidation(invalidSupplier: () -> E)
        : Validation<E, T> = Value.narrow(value).toValidation(invalidSupplier)

/**
 * Converts this to a [Queue].
 *
 * @return A new [Queue].
 */
fun <T> io.vavr.kotlin.Value<T>.toQueue()
        : Queue<T> = Value.narrow(value).toQueue()

/**
 * Converts this to a [PriorityQueue].
 *
 * @return A new [PriorityQueue].
 */
fun <T> io.vavr.kotlin.Value<T>.toPriorityQueue()
        : PriorityQueue<T> = Value.narrow(value).toPriorityQueue()

/**
 * Converts this to a [PriorityQueue].
 *
 * @param comparator A comparator that induces an order of the PriorityQueue elements.
 * @return A new [PriorityQueue].
 */
fun <T> io.vavr.kotlin.Value<T>.toPriorityQueue(comparator: Comparator<T>)
        : PriorityQueue<T> = Value.narrow(value).toPriorityQueue(comparator)

/**
 * Converts this to a [Set].
 *
 * @return A new [HashSet].
 */
fun <T> io.vavr.kotlin.Value<T>.toSet()
        : Set<T> = Value.narrow(value).toSet()

/**
 * Converts this to a [Set].
 *
 * @return A new [LinkedHashSet].
 */
fun <T> io.vavr.kotlin.Value<T>.toLinkedSet()
        : Set<T> = Value.narrow(value).toLinkedSet()

/**
 * Converts this to a [SortedSet].
 * Current items must be comparable
 *
 * @return A new [TreeSet].
 */
fun <T: Comparable<T>> io.vavr.kotlin.Value<T>.toSortedSet()
        : SortedSet<T> = Value.narrow(value).toSortedSet()
// The Java version throws an exception if the type isn't comparable. For Kotlin, we just declare the
// extension function to only be defined on comparable types.

/**
 * Converts this to a [SortedSet].
 *
 * @param comparator A comparator that induces an order of the SortedSet elements.
 * @return A new [TreeSet].
 */
fun <T> io.vavr.kotlin.Value<T>.toSortedSet(comparator: Comparator<T>)
        : SortedSet<T> = Value.narrow(value).toSortedSet(comparator)

/**
 * Converts this to a [Stream].
 *
 * @return A new [Stream].
 */
fun <T> io.vavr.kotlin.Value<T>.toStream()
        : Stream<T> = Value.narrow(value).toStream()

/**
 * Converts this to a [Try].
 *
 * If this value is undefined, i.e. empty, then a new `Failure(NoSuchElementException)` is returned,
 * otherwise a new `Success(value)` is returned.
 *
 * @return A new [Try].
 */
fun <T> io.vavr.kotlin.Value<T>.toTry()
        : Try<T> = Value.narrow(value).toTry()

/**
 * Converts this to a [Try].
 *
 * If this value is undefined, i.e. empty, then a new `Failure(ifEmpty.get())` is returned,
 * otherwise a new `Success(value)` is returned.
 *
 * @param ifEmpty an exception supplier
 * @return A new [Try].
 */
fun <T> io.vavr.kotlin.Value<T>.toTry(ifEmpty: () -> Throwable)
        : Try<T> = Value.narrow(value).toTry(ifEmpty)

/**
 * Converts this to a [Tree].
 *
 * @return A new [Tree].
 */
fun <T> io.vavr.kotlin.Value<T>.toTree()
        : Tree<T> = Value.narrow(value).toTree()

/**
 * Converts this to a [Tree] using a `idMapper` and `parentMapper`.
 *
 * @param ID Id type
 * @param idMapper     A mapper from source item to unique identifier of that item
 * @param parentMapper A mapper from source item to unique identifier of parent item. Need return null for root items
 * @return A new [Tree].
 * @see Tree.build
 */
fun <T, ID> io.vavr.kotlin.Value<T>.toTree(idMapper: (T) -> ID, parentMapper: (T) -> ID)
        : List<out Tree.Node<out T>> = value.toTree(idMapper, parentMapper)

/**
 * Converts this to a [Validation].
 *
 * @param E   error type of an `Invalid`
 * @param error An error
 * @return A new [Validation.Invalid] containing the given `error` if this is empty, otherwise
 * a new [Validation.Valid] containing this value.
 */
@Deprecated("Use Value.toValidation instead.", ReplaceWith("toValidation(error)"))
fun <T, E> io.vavr.kotlin.Value<T>.toValid(error: E)
        : Validation<E, T> = toValid(error)

/**
 * Converts this to a [Validation].
 *
 * @param <E>           error type of an `Invalid`
 * @param errorSupplier A supplier of an error
 * @return A new [Validation.Invalid] containing the result of `errorSupplier` if this is empty,
 * otherwise a new [Validation.Valid] containing this value.
 * @throws NullPointerException if `valueSupplier` is null
 */
@Deprecated("Use Value.toValidation instead.", ReplaceWith("toValidation(errorSupplier)"))
fun <T, E> io.vavr.kotlin.Value<T>.toValid(errorSupplier: () -> E)
        : Validation<E, T> = toValid(errorSupplier)

