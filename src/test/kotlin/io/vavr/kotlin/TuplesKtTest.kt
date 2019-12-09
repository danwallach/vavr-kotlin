package io.vavr.kotlin

import io.vavr.*
import io.vavr.kotlin.control.some
import org.junit.Test

class TuplesKtTest {
    @Test
    fun tuple() {
    }

    @Test
    fun tupleOf1() {
        val t: Tuple1<Int> = tuple(1)
        assert(t._1 == 1)
    }

    @Test
    fun tupleOf2() {
        val t: Tuple2<Int, Int> = tuple(1, 2)
        assert(t._1 == 1)
        assert(t._2 == 2)
    }

    @Test
    fun tupleOf3() {
        val t: Tuple3<Int, Int, Int> = tuple(1, 2, 3)
        assert(t._1 == 1)
        assert(t._2 == 2)
        assert(t._3 == 3)
    }

    @Test
    fun tupleOf4() {
        val t: Tuple4<Int, Int, Int, Int> = tuple(1, 2, 3, 4)
        assert(t._1 == 1)
        assert(t._2 == 2)
        assert(t._3 == 3)
        assert(t._4 == 4)
    }

    @Test
    fun tupleOf5() {
        val t: Tuple5<Int, Int, Int, Int, Int> = tuple(1, 2, 3, 4, 5)
        assert(t._1 == 1)
        assert(t._2 == 2)
        assert(t._3 == 3)
        assert(t._4 == 4)
        assert(t._5 == 5)
    }

    @Test
    fun tupleOf6() {
        val t: Tuple6<Int, Int, Int, Int, Int, Int> = tuple(1, 2, 3, 4, 5, 6)
        assert(t._1 == 1)
        assert(t._2 == 2)
        assert(t._3 == 3)
        assert(t._4 == 4)
        assert(t._5 == 5)
        assert(t._6 == 6)
    }

    @Test
    fun tupleOf7() {
        val t: Tuple7<Int, Int, Int, Int, Int, Int, Int> = tuple(1, 2, 3, 4, 5, 6, 7)
        assert(t._1 == 1)
        assert(t._2 == 2)
        assert(t._3 == 3)
        assert(t._4 == 4)
        assert(t._5 == 5)
        assert(t._6 == 6)
        assert(t._7 == 7)
    }

    @Test
    fun tupleOf8() {
        val t: Tuple8<Int, Int, Int, Int, Int, Int, Int, Int> = tuple(1, 2, 3, 4, 5, 6, 7, 8)
        assert(t._1 == 1)
        assert(t._2 == 2)
        assert(t._3 == 3)
        assert(t._4 == 4)
        assert(t._5 == 5)
        assert(t._6 == 6)
        assert(t._7 == 7)
        assert(t._8 == 8)
    }

    @Test
    fun pair() {
        val p = tuple(1, 2).pair()
        assert(p.first == 1)
        assert(p.second == 2)
    }

    @Test
    fun tupleOfPair() {
        val t = Pair(1, 2).tuple()
        assert(t._1 == 1)
        assert(t._2 == 2)
    }

    @Test
    fun tupleOfEntry() {
        val e: Map.Entry<Int, Int> = mapOf(Pair(1, 2)).entries.iterator().next()
        val t = e.tuple()
        assert(t._1 == 1)
        assert(t._2 == 2)
    }

    @Test
    fun components1() {
        val (v1) = tuple(1)
        assert(v1 == 1)
    }

    @Test
    fun components2() {
        val (v1, v2) = tuple(1, 2)
        assert(v1 == 1)
        assert(v2 == 2)
    }

    @Test
    fun components3() {
        val (v1, v2, v3) = tuple(1, 2, 3)
        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
    }

    @Test
    fun components4() {
        val (v1, v2, v3, v4) = tuple(1, 2, 3, 4)
        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
        assert(v4 == 4)
    }

    @Test
    fun components5() {
        val (v1, v2, v3, v4, v5) = tuple(1, 2, 3, 4, 5)
        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
        assert(v4 == 4)
        assert(v5 == 5)
    }

    @Test
    fun components6() {
        val (v1, v2, v3, v4, v5, v6) = tuple(1, 2, 3, 4, 5, 6)
        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
        assert(v4 == 4)
        assert(v5 == 5)
        assert(v6 == 6)
    }

    @Test
    fun components7() {
        val (v1, v2, v3, v4, v5, v6, v7) = tuple(1, 2, 3, 4, 5, 6, 7)
        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
        assert(v4 == 4)
        assert(v5 == 5)
        assert(v6 == 6)
        assert(v7 == 7)
    }

    @Test
    fun components8() {
        val (v1, v2, v3, v4, v5, v6, v7, v8) = tuple(1, 2, 3, 4, 5, 6, 7, 8)
        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
        assert(v4 == 4)
        assert(v5 == 5)
        assert(v6 == 6)
        assert(v7 == 7)
        assert(v8 == 8)
    }

    @Test
    fun plusOperations() {
        val t0 = emptyTuple()
        val t1 = tuple(1)
        val t2 = tuple(1, "two")
        val p2 = Pair(1, "two")
        val t3 = tuple(1, "two", 3)
        val p3 = Triple(1, "two", 3)
        val t4 = tuple(1, "two", 3, "four")
        val t5 = tuple(1, "two", 3, "four", 5.0)
        val t6 = tuple(1, "two", 3, "four", 5.0, some(6))
        val t7 = tuple(1, "two", 3, "four", 5.0, some(6), some("seven"))
        val t8 = tuple(1, "two", 3, "four", 5.0, some(6), some("seven"), 8)

        assert(t0 + t0 == t0)
        assert(t0 + t1 == t1)
        assert(t1 + t0 == t1)
        assert(t0 + t2 == t2)
        assert(t0 + p2 == t2)
        assert(t2 + t0 == t2)
        assert(p2 + t0 == t2)
        assert(t0 + t3 == t3)
        assert(t0 + p3 == t3)
        assert(t3 + t0 == t3)
        assert(p3 + t0 == t3)
        assert(t0 + t4 == t4)
        assert(t4 + t0 == t4)
        assert(t0 + t5 == t5)
        assert(t5 + t0 == t5)
        assert(t0 + t6 == t6)
        assert(t6 + t0 == t6)
        assert(t0 + t7 == t7)
        assert(t7 + t0 == t7)
        assert(t0 + t8 == t8)
        assert(t8 + t0 == t8)

        assert(t1 + tuple("two") == t2)
        assert(t1 + tuple("two", 3) == t3)
        assert(t1 + Pair("two", 3) == t3)
        assert(t1 + tuple("two", 3, "four") == t4)
        assert(t1 + Triple("two", 3, "four") == t4)
        assert(t1 + tuple("two", 3, "four", 5.0) == t5)
        assert(t1 + tuple("two", 3, "four", 5.0, some(6)) == t6)
        assert(t1 + tuple("two", 3, "four", 5.0, some(6), some("seven")) == t7)
        assert(t1 + tuple("two", 3, "four", 5.0, some(6), some("seven"), 8) == t8)

        assert(t2 + tuple(3) == t3)
        assert(t2 + tuple(3, "four") == t4)
        assert(t2 + Pair(3, "four") == t4)
        assert(t2 + tuple(3, "four", 5.0) == t5)
        assert(t2 + Triple(3, "four", 5.0) == t5)
        assert(t2 + tuple(3, "four", 5.0, some(6)) == t6)
        assert(t2 + tuple(3, "four", 5.0, some(6), some("seven")) == t7)
        assert(t2 + tuple(3, "four", 5.0, some(6), some("seven"), 8) == t8)

        assert(p2 + tuple(3) == t3)
        assert(p2 + tuple(3, "four") == t4)
        assert(p2 + tuple(3, "four", 5.0) == t5)
        assert(p2 + Triple(3, "four", 5.0) == t5)
        assert(p2 + tuple(3, "four", 5.0, some(6)) == t6)
        assert(p2 + tuple(3, "four", 5.0, some(6), some("seven")) == t7)
        assert(p2 + tuple(3, "four", 5.0, some(6), some("seven"), 8) == t8)

        assert(t3 + tuple("four") == t4)
        assert(t3 + tuple("four", 5.0) == t5)
        assert(t3 + Pair("four", 5.0) == t5)
        assert(t3 + tuple("four", 5.0, some(6)) == t6)
        assert(t3 + Triple("four", 5.0, some(6)) == t6)
        assert(t3 + tuple("four", 5.0, some(6), some("seven")) == t7)
        assert(t3 + tuple("four", 5.0, some(6), some("seven"), 8) == t8)

        assert(p3 + tuple("four") == t4)
        assert(p3 + tuple("four", 5.0) == t5)
        assert(p3 + tuple("four", 5.0, some(6)) == t6)
        assert(p3 + tuple("four", 5.0, some(6), some("seven")) == t7)
        assert(p3 + tuple("four", 5.0, some(6), some("seven"), 8) == t8)

        assert(t4 + tuple(5.0) == t5)
        assert(t4 + tuple(5.0, some(6)) == t6)
        assert(t4 + Pair(5.0, some(6)) == t6)
        assert(t4 + tuple(5.0, some(6), some("seven")) == t7)
        assert(t4 + Triple(5.0, some(6), some("seven")) == t7)
        assert(t4 + tuple(5.0, some(6), some("seven"), 8) == t8)

        assert(t5 + tuple(some(6)) == t6)
        assert(t5 + tuple(some(6), some("seven")) == t7)
        assert(t5 + Pair(some(6), some("seven")) == t7)
        assert(t5 + tuple(some(6), some("seven"), 8) == t8)
        assert(t5 + Triple(some(6), some("seven"), 8) == t8)

        assert(t6 + tuple(some("seven")) == t7)
        assert(t6 + tuple(some("seven"), 8) == t8)
        assert(t6 + Pair(some("seven"), 8) == t8)

        assert(t7 + tuple(8) == t8)
    }
}